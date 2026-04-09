package zombie.Lua;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import se.krka.kahlua.j2se.KahluaTableImpl;
import se.krka.kahlua.vm.KahluaTable;
import zombie.core.logger.ExceptionLogger;
import zombie.util.PZSQLUtils;

/**
 * API SQLite exposta ao Lua para mods do Project Zomboid.
 *
 * Uso em Lua (servidor ou cliente):
 *
 *   local db = LuaSQLite.open(getModFileDir("MeuMod") .. "/dados.db")
 *   db:exec("CREATE TABLE IF NOT EXISTS kv (key TEXT PRIMARY KEY, value TEXT)")
 *
 *   -- INSERT/UPDATE/DELETE sem parâmetros
 *   db:exec("INSERT OR REPLACE INTO kv VALUES ('hp', '100')")
 *
 *   -- INSERT/UPDATE/DELETE com parâmetros (evita SQL injection)
 *   db:execParams("INSERT OR REPLACE INTO kv VALUES (?, ?)", {"hp", "100"})
 *
 *   -- SELECT sem parâmetros
 *   local rows = db:query("SELECT * FROM kv")
 *   for i = 1, rows.n do
 *       print(rows[i].key, rows[i].value)
 *   end
 *
 *   -- SELECT com parâmetros
 *   local rows = db:queryParams("SELECT * FROM kv WHERE key = ?", {"hp"})
 *
 *   -- Transações
 *   db:beginTransaction()
 *   db:exec("INSERT INTO kv VALUES ('a','1')")
 *   db:exec("INSERT INTO kv VALUES ('b','2')")
 *   db:commit()   -- ou db:rollback()
 *
 *   db:close()
 *
 * Rows retornadas: KahluaTable indexada por inteiro (1..n), campo "n" com a
 * contagem. Cada row é uma KahluaTable com colunas indexadas pelo nome.
 * Números SQL chegam como Double no Lua (padrão Kahlua).
 */
public class LuaSQLite {

    private Connection conn;

    // -------------------------------------------------------------------------
    // Construção
    // -------------------------------------------------------------------------

    private LuaSQLite(Connection conn) {
        this.conn = conn;
    }

    /**
     * Abre (ou cria) um banco SQLite no caminho absoluto informado.
     * Retorna null em caso de falha (erro é logado no console do servidor).
     * Nenhum SQL é executado automaticamente — todo setup (PRAGMAs, CREATE TABLE, etc.)
     * fica a cargo do código Lua chamador.
     */
    public static LuaSQLite open(String path) {
        try {
            PZSQLUtils.init();
            // Cria diretórios pai se não existirem
            java.io.File file = new java.io.File(path);
            java.io.File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            return new LuaSQLite(PZSQLUtils.getConnection(path));
        } catch (Exception e) {
            ExceptionLogger.logException(e);
            return null;
        }
    }

    // -------------------------------------------------------------------------
    // DDL / DML sem parâmetros
    // -------------------------------------------------------------------------

    /**
     * Executa uma instrução SQL (CREATE, INSERT, UPDATE, DELETE...).
     * @return número de linhas afetadas, ou -1 em caso de erro.
     */
    public synchronized int exec(String sql) {
        try (Statement st = conn.createStatement()) {
            // execute() funciona para DDL (CREATE, DROP, PRAGMA) e DML (INSERT, UPDATE, DELETE)
            // executeUpdate() lança exceção em alguns drivers para DDL
            st.execute(sql);
            int n = st.getUpdateCount();
            return n < 0 ? 0 : n;
        } catch (SQLException e) {
            ExceptionLogger.logException(e);
            return -1;
        }
    }

    // -------------------------------------------------------------------------
    // DDL / DML com parâmetros (? placeholders)
    // -------------------------------------------------------------------------

    /**
     * Executa SQL com parâmetros posicionais.
     * @param sql  ex.: "INSERT INTO t VALUES (?, ?)"
     * @param params  KahluaTable Lua com índices 1-based: {val1, val2, ...}
     * @return número de linhas afetadas, ou -1 em caso de erro.
     */
    public synchronized int execParams(String sql, KahluaTable params) {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            bindParams(ps, params);
            return ps.executeUpdate();
        } catch (SQLException e) {
            ExceptionLogger.logException(e);
            return -1;
        }
    }

    // -------------------------------------------------------------------------
    // SELECT sem parâmetros
    // -------------------------------------------------------------------------

    /**
     * Executa um SELECT e retorna todas as linhas.
     * @return KahluaTable onde rows[i] é uma KahluaTable com os campos da linha,
     *         e rows.n contém a quantidade de linhas.
     */
    public synchronized KahluaTable query(String sql) {
        KahluaTableImpl results = new KahluaTableImpl(new HashMap<>());
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            fillResults(rs, results);
        } catch (SQLException e) {
            ExceptionLogger.logException(e);
        }
        return results;
    }

    // -------------------------------------------------------------------------
    // SELECT com parâmetros
    // -------------------------------------------------------------------------

    /**
     * Executa um SELECT com parâmetros posicionais.
     * @param sql    ex.: "SELECT * FROM t WHERE key = ?"
     * @param params KahluaTable Lua com índices 1-based
     */
    public synchronized KahluaTable queryParams(String sql, KahluaTable params) {
        KahluaTableImpl results = new KahluaTableImpl(new HashMap<>());
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            bindParams(ps, params);
            try (ResultSet rs = ps.executeQuery()) {
                fillResults(rs, results);
            }
        } catch (SQLException e) {
            ExceptionLogger.logException(e);
        }
        return results;
    }

    // -------------------------------------------------------------------------
    // Transações
    // -------------------------------------------------------------------------

    public synchronized boolean beginTransaction() {
        try {
            conn.setAutoCommit(false);
            return true;
        } catch (SQLException e) {
            ExceptionLogger.logException(e);
            return false;
        }
    }

    public synchronized boolean commit() {
        try {
            conn.commit();
            conn.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            ExceptionLogger.logException(e);
            return false;
        }
    }

    public synchronized boolean rollback() {
        try {
            conn.rollback();
            conn.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            ExceptionLogger.logException(e);
            return false;
        }
    }

    // -------------------------------------------------------------------------
    // Ciclo de vida
    // -------------------------------------------------------------------------

    public synchronized void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            ExceptionLogger.logException(e);
        }
    }

    public boolean isOpen() {
        try {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    // -------------------------------------------------------------------------
    // Helpers internos
    // -------------------------------------------------------------------------

    /**
     * Preenche a KahluaTable de resultados a partir de um ResultSet.
     * Índice "n" guarda a contagem; índices 1..n são as linhas.
     * Cada linha é indexada pelo nome da coluna (lower-case do nome real).
     */
    private void fillResults(ResultSet rs, KahluaTableImpl results) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rowIdx = 1;
        while (rs.next()) {
            KahluaTableImpl row = new KahluaTableImpl(new HashMap<>());
            for (int i = 1; i <= cols; i++) {
                String colName = meta.getColumnName(i);
                Object val = rs.getObject(i);
                // Kahlua/Lua só conhece Double para números
                if (val instanceof Integer) {
                    val = ((Integer) val).doubleValue();
                } else if (val instanceof Long) {
                    val = ((Long) val).doubleValue();
                } else if (val instanceof Float) {
                    val = ((Float) val).doubleValue();
                }
                row.rawset(colName, val);
            }
            results.rawset(rowIdx++, row);
        }
        // Campo "n" para que Lua possa iterar sem precisar de # (que não funciona em KahluaTable)
        results.rawset("n", (double) (rowIdx - 1));
    }

    /**
     * Vincula os parâmetros de uma KahluaTable Lua (1-based) a um PreparedStatement.
     * Tipos suportados: String, Double/número Lua, Boolean, null.
     */
    private void bindParams(PreparedStatement ps, KahluaTable params) throws SQLException {
        if (params == null) {
            return;
        }
        int i = 1;
        while (true) {
            Object val = params.rawget((double) i);
            if (val == null) {
                break;
            }
            if (val instanceof Double) {
                double d = (Double) val;
                // Preserva inteiros quando possível
                if (d == Math.floor(d) && !Double.isInfinite(d) && d >= Long.MIN_VALUE && d <= Long.MAX_VALUE) {
                    ps.setLong(i, (long) d);
                } else {
                    ps.setDouble(i, d);
                }
            } else if (val instanceof String) {
                ps.setString(i, (String) val);
            } else if (val instanceof Boolean) {
                ps.setBoolean(i, (Boolean) val);
            } else {
                ps.setObject(i, val);
            }
            i++;
        }
    }
}
