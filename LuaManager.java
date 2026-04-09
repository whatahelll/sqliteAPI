package zombie.Lua;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.sun.jna.platform.win32.COM.tlb.imp.TlbConst;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import fmod.fmod.EmitterType;
import fmod.fmod.FMODAudio;
import fmod.fmod.FMODDebugEventPlayer;
import fmod.fmod.FMODManager;
import fmod.fmod.FMODSoundBank;
import fmod.fmod.FMODSoundEmitter;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.Vector;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.lang3.SystemProperties;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.luaj.kahluafork.compiler.FuncState;
import org.lwjglx.input.Controller;
import org.lwjglx.input.Controllers;
import org.lwjglx.input.KeyCodes;
import org.lwjglx.input.Keyboard;
import org.slf4j.Marker;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import se.krka.kahlua.converter.KahluaConverterManager;
import se.krka.kahlua.integration.LuaCaller;
import se.krka.kahlua.integration.LuaReturn;
import se.krka.kahlua.integration.LuaSuccess;
import se.krka.kahlua.integration.annotations.LuaMethod;
import se.krka.kahlua.integration.expose.LuaJavaClassExposer;
import se.krka.kahlua.integration.expose.LuaJavaInvoker;
import se.krka.kahlua.j2se.J2SEPlatform;
import se.krka.kahlua.j2se.KahluaTableImpl;
import se.krka.kahlua.luaj.compiler.LuaCompiler;
import se.krka.kahlua.vm.Coroutine;
import se.krka.kahlua.vm.JavaFunction;
import se.krka.kahlua.vm.KahluaTable;
import se.krka.kahlua.vm.KahluaTableIterator;
import se.krka.kahlua.vm.KahluaThread;
import se.krka.kahlua.vm.KahluaUtil;
import se.krka.kahlua.vm.LuaCallFrame;
import se.krka.kahlua.vm.LuaClosure;
import se.krka.kahlua.vm.Platform;
import zombie.AmbientStreamManager;
import zombie.BaseAmbientStreamManager;
import zombie.BaseSoundManager;
import zombie.CombatManager;
import zombie.DummySoundManager;
import zombie.GameSounds;
import zombie.GameTime;
import zombie.GameWindow;
import zombie.IndieGL;
import zombie.MapGroups;
import zombie.SandboxOptions;
import zombie.SoundManager;
import zombie.SystemDisabler;
import zombie.UsedFromLua;
import zombie.VirtualZombieManager;
import zombie.WorldSoundManager;
import zombie.ZombieSpawnRecorder;
import zombie.ZomboidFileSystem;
import zombie.ai.GameCharacterAIBrain;
import zombie.ai.MapKnowledge;
import zombie.ai.sadisticAIDirector.SleepingEvent;
import zombie.ai.states.AttackState;
import zombie.ai.states.BurntToDeath;
import zombie.ai.states.ClimbDownSheetRopeState;
import zombie.ai.states.ClimbOverFenceState;
import zombie.ai.states.ClimbOverWallState;
import zombie.ai.states.ClimbSheetRopeState;
import zombie.ai.states.ClimbThroughWindowState;
import zombie.ai.states.CloseWindowState;
import zombie.ai.states.CrawlingZombieTurnState;
import zombie.ai.states.FakeDeadAttackState;
import zombie.ai.states.FakeDeadZombieState;
import zombie.ai.states.FishingState;
import zombie.ai.states.FitnessState;
import zombie.ai.states.IdleState;
import zombie.ai.states.LungeState;
import zombie.ai.states.OpenWindowState;
import zombie.ai.states.PathFindState;
import zombie.ai.states.PlayerActionsState;
import zombie.ai.states.PlayerAimState;
import zombie.ai.states.PlayerEmoteState;
import zombie.ai.states.PlayerExtState;
import zombie.ai.states.PlayerFallDownState;
import zombie.ai.states.PlayerFallingState;
import zombie.ai.states.PlayerGetUpState;
import zombie.ai.states.PlayerHitReactionPVPState;
import zombie.ai.states.PlayerHitReactionState;
import zombie.ai.states.PlayerKnockedDown;
import zombie.ai.states.PlayerOnGroundState;
import zombie.ai.states.PlayerSitOnFurnitureState;
import zombie.ai.states.PlayerSitOnGroundState;
import zombie.ai.states.PlayerStrafeState;
import zombie.ai.states.SmashWindowState;
import zombie.ai.states.StaggerBackState;
import zombie.ai.states.SwipeStatePlayer;
import zombie.ai.states.ThumpState;
import zombie.ai.states.WalkTowardState;
import zombie.ai.states.ZombieFallDownState;
import zombie.ai.states.ZombieGetDownState;
import zombie.ai.states.ZombieGetUpState;
import zombie.ai.states.ZombieIdleState;
import zombie.ai.states.ZombieOnGroundState;
import zombie.ai.states.ZombieReanimateState;
import zombie.ai.states.ZombieSittingState;
import zombie.ai.states.player.PlayerMilkAnimalState;
import zombie.ai.states.player.PlayerMovementState;
import zombie.ai.states.player.PlayerPetAnimalState;
import zombie.ai.states.player.PlayerShearAnimalState;
import zombie.asset.Asset;
import zombie.asset.AssetPath;
import zombie.audio.BaseSoundBank;
import zombie.audio.BaseSoundEmitter;
import zombie.audio.DummySoundBank;
import zombie.audio.DummySoundEmitter;
import zombie.audio.GameSound;
import zombie.audio.GameSoundClip;
import zombie.audio.MusicIntensityConfig;
import zombie.audio.MusicIntensityEvent;
import zombie.audio.MusicIntensityEvents;
import zombie.audio.MusicThreatConfig;
import zombie.audio.MusicThreatStatus;
import zombie.audio.MusicThreatStatuses;
import zombie.audio.parameters.ParameterRoomType;
import zombie.basements.Basements;
import zombie.basements.BasementsV1;
import zombie.buildingRooms.BuildingRoomsEditor;
import zombie.characterTextures.BloodBodyPartType;
import zombie.characterTextures.BloodClothingType;
import zombie.characters.AttachedItems.AttachedItem;
import zombie.characters.AttachedItems.AttachedItems;
import zombie.characters.AttachedItems.AttachedLocation;
import zombie.characters.AttachedItems.AttachedLocationGroup;
import zombie.characters.AttachedItems.AttachedLocations;
import zombie.characters.AttachedItems.AttachedWeaponDefinitions;
import zombie.characters.BodyDamage.BodyDamage;
import zombie.characters.BodyDamage.BodyPart;
import zombie.characters.BodyDamage.BodyPartType;
import zombie.characters.BodyDamage.Fitness;
import zombie.characters.BodyDamage.Metabolics;
import zombie.characters.BodyDamage.Nutrition;
import zombie.characters.BodyDamage.Thermoregulator;
import zombie.characters.Capability;
import zombie.characters.CharacterActionAnims;
import zombie.characters.CharacterSoundEmitter;
import zombie.characters.CharacterStat;
import zombie.characters.CharacterTimedActions.LuaTimedAction;
import zombie.characters.CharacterTimedActions.LuaTimedActionNew;
import zombie.characters.CheatType;
import zombie.characters.DummyCharacterSoundEmitter;
import zombie.characters.Faction;
import zombie.characters.HairOutfitDefinitions;
import zombie.characters.HaloTextHelper;
import zombie.characters.IsoDummyCameraCharacter;
import zombie.characters.IsoGameCharacter;
import zombie.characters.IsoPlayer;
import zombie.characters.IsoSurvivor;
import zombie.characters.IsoZombie;
import zombie.characters.Moodles.Moodle;
import zombie.characters.Moodles.Moodles;
import zombie.characters.MoveDeltaModifiers;
import zombie.characters.NetworkUser;
import zombie.characters.NetworkUsers;
import zombie.characters.PlayerCraftHistory;
import zombie.characters.Position3D;
import zombie.characters.Role;
import zombie.characters.Roles;
import zombie.characters.Safety;
import zombie.characters.SafetySystemManager;
import zombie.characters.Stats;
import zombie.characters.SurvivorDesc;
import zombie.characters.SurvivorFactory;
import zombie.characters.UnderwearDefinition;
import zombie.characters.WornItems.BodyLocation;
import zombie.characters.WornItems.BodyLocationGroup;
import zombie.characters.WornItems.BodyLocations;
import zombie.characters.WornItems.WornItem;
import zombie.characters.WornItems.WornItems;
import zombie.characters.ZombiesZoneDefinition;
import zombie.characters.action.ActionGroup;
import zombie.characters.animals.AnimalAllele;
import zombie.characters.animals.AnimalChunk;
import zombie.characters.animals.AnimalDefinitions;
import zombie.characters.animals.AnimalGene;
import zombie.characters.animals.AnimalGenomeDefinitions;
import zombie.characters.animals.AnimalManagerWorker;
import zombie.characters.animals.AnimalPartsDefinitions;
import zombie.characters.animals.AnimalTracks;
import zombie.characters.animals.IsoAnimal;
import zombie.characters.animals.VirtualAnimal;
import zombie.characters.animals.behavior.BaseAnimalBehavior;
import zombie.characters.animals.datas.AnimalBreed;
import zombie.characters.animals.datas.AnimalData;
import zombie.characters.professions.CharacterProfessionDefinition;
import zombie.characters.skills.PerkFactory;
import zombie.characters.traits.CharacterTraitDefinition;
import zombie.characters.traits.CharacterTraits;
import zombie.characters.traits.ObservationFactory;
import zombie.chat.ChatBase;
import zombie.chat.ChatManager;
import zombie.chat.ChatMessage;
import zombie.chat.ServerChatMessage;
import zombie.combat.CombatConfig;
import zombie.combat.CombatConfigCategory;
import zombie.combat.CombatConfigKey;
import zombie.config.BooleanConfigOption;
import zombie.config.ConfigOption;
import zombie.config.DoubleConfigOption;
import zombie.config.EnumConfigOption;
import zombie.config.IntegerConfigOption;
import zombie.config.StringConfigOption;
import zombie.core.ActionManager;
import zombie.core.BoxedStaticValues;
import zombie.core.Clipboard;
import zombie.core.Color;
import zombie.core.Colors;
import zombie.core.Core;
import zombie.core.FishingAction;
import zombie.core.GameMode;
import zombie.core.GameVersion;
import zombie.core.ImmutableColor;
import zombie.core.IndieFileLoader;
import zombie.core.Language;
import zombie.core.NetTimedAction;
import zombie.core.PerformanceSettings;
import zombie.core.SpriteRenderer;
import zombie.core.TransactionManager;
import zombie.core.Translator;
import zombie.core.WordsFilter;
import zombie.core.fonts.AngelCodeFont;
import zombie.core.input.Input;
import zombie.core.logger.ExceptionLogger;
import zombie.core.logger.LoggerManager;
import zombie.core.logger.ZLogger;
import zombie.core.math.PZMath;
import zombie.core.network.ByteBufferWriter;
import zombie.core.opengl.RenderThread;
import zombie.core.physics.Bullet;
import zombie.core.physics.WorldSimulation;
import zombie.core.properties.IsoObjectChange;
import zombie.core.properties.IsoPropertyType;
import zombie.core.properties.PropertyContainer;
import zombie.core.raknet.UdpConnection;
import zombie.core.raknet.VoiceManager;
import zombie.core.random.Rand;
import zombie.core.random.RandLua;
import zombie.core.skinnedmodel.ModelManager;
import zombie.core.skinnedmodel.advancedanimation.AnimNodeAssetManager;
import zombie.core.skinnedmodel.advancedanimation.AnimationSet;
import zombie.core.skinnedmodel.advancedanimation.debug.AnimatorDebugMonitor;
import zombie.core.skinnedmodel.model.ItemModelRenderer;
import zombie.core.skinnedmodel.model.Model;
import zombie.core.skinnedmodel.model.ModelAssetManager;
import zombie.core.skinnedmodel.model.WorldItemModelDrawer;
import zombie.core.skinnedmodel.population.BeardStyle;
import zombie.core.skinnedmodel.population.BeardStyles;
import zombie.core.skinnedmodel.population.ClothingDecalGroup;
import zombie.core.skinnedmodel.population.ClothingDecals;
import zombie.core.skinnedmodel.population.ClothingItem;
import zombie.core.skinnedmodel.population.DefaultClothing;
import zombie.core.skinnedmodel.population.HairStyle;
import zombie.core.skinnedmodel.population.HairStyles;
import zombie.core.skinnedmodel.population.Outfit;
import zombie.core.skinnedmodel.population.OutfitManager;
import zombie.core.skinnedmodel.population.VoiceStyle;
import zombie.core.skinnedmodel.population.VoiceStyles;
import zombie.core.skinnedmodel.runtime.RuntimeAnimationScript;
import zombie.core.skinnedmodel.visual.AnimalVisual;
import zombie.core.skinnedmodel.visual.HumanVisual;
import zombie.core.skinnedmodel.visual.ItemVisual;
import zombie.core.skinnedmodel.visual.ItemVisuals;
import zombie.core.stash.Stash;
import zombie.core.stash.StashBuilding;
import zombie.core.stash.StashSystem;
import zombie.core.textures.ColorInfo;
import zombie.core.textures.NinePatchTexture;
import zombie.core.textures.Texture;
import zombie.core.textures.TextureDraw;
import zombie.core.textures.TextureID;
import zombie.core.textures.VideoTexture;
import zombie.core.znet.GameServerDetails;
import zombie.core.znet.ISteamWorkshopCallback;
import zombie.core.znet.ServerBrowser;
import zombie.core.znet.SteamFriend;
import zombie.core.znet.SteamFriends;
import zombie.core.znet.SteamRemotePlay;
import zombie.core.znet.SteamUGCDetails;
import zombie.core.znet.SteamUser;
import zombie.core.znet.SteamUtils;
import zombie.core.znet.SteamWorkshop;
import zombie.core.znet.SteamWorkshopItem;
import zombie.debug.BooleanDebugOption;
import zombie.debug.DebugCSVExport;
import zombie.debug.DebugCSVExportFirearms;
import zombie.debug.DebugCSVExportFluidContainers;
import zombie.debug.DebugCSVExportMoveableTiles;
import zombie.debug.DebugLog;
import zombie.debug.DebugOptions;
import zombie.debug.DebugType;
import zombie.debug.LineDrawer;
import zombie.debug.LogSeverity;
import zombie.debug.objects.ObjectDebuggerLua;
import zombie.entity.Component;
import zombie.entity.ComponentType;
import zombie.entity.EntityBucket;
import zombie.entity.Family;
import zombie.entity.GameEntity;
import zombie.entity.GameEntityFactory;
import zombie.entity.GameEntityManager;
import zombie.entity.GameEntityType;
import zombie.entity.MetaEntity;
import zombie.entity.components.attributes.Attribute;
import zombie.entity.components.attributes.AttributeContainer;
import zombie.entity.components.attributes.AttributeInstance;
import zombie.entity.components.attributes.AttributeType;
import zombie.entity.components.attributes.AttributeUtil;
import zombie.entity.components.attributes.AttributeValueType;
import zombie.entity.components.attributes.EnumStringObj;
import zombie.entity.components.build.BuildLogic;
import zombie.entity.components.contextmenuconfig.ContextMenuConfig;
import zombie.entity.components.crafting.BaseCraftingLogic;
import zombie.entity.components.crafting.CraftBench;
import zombie.entity.components.crafting.CraftLogic;
import zombie.entity.components.crafting.CraftLogicUILogic;
import zombie.entity.components.crafting.CraftMode;
import zombie.entity.components.crafting.CraftRecipeComponent;
import zombie.entity.components.crafting.CraftRecipeMonitor;
import zombie.entity.components.crafting.CraftUtil;
import zombie.entity.components.crafting.DryingCraftLogic;
import zombie.entity.components.crafting.FluidMatchMode;
import zombie.entity.components.crafting.FurnaceLogic;
import zombie.entity.components.crafting.InputFlag;
import zombie.entity.components.crafting.ItemApplyMode;
import zombie.entity.components.crafting.MashingLogic;
import zombie.entity.components.crafting.OutputFlag;
import zombie.entity.components.crafting.StartMode;
import zombie.entity.components.crafting.recipe.CraftRecipeData;
import zombie.entity.components.crafting.recipe.CraftRecipeListNode;
import zombie.entity.components.crafting.recipe.CraftRecipeListNodeCollection;
import zombie.entity.components.crafting.recipe.CraftRecipeManager;
import zombie.entity.components.crafting.recipe.CraftRecipeSort;
import zombie.entity.components.crafting.recipe.HandcraftLogic;
import zombie.entity.components.crafting.recipe.InputItemNode;
import zombie.entity.components.crafting.recipe.ItemDataList;
import zombie.entity.components.crafting.recipe.OutputMapper;
import zombie.entity.components.fluids.Fluid;
import zombie.entity.components.fluids.FluidCategory;
import zombie.entity.components.fluids.FluidConsume;
import zombie.entity.components.fluids.FluidContainer;
import zombie.entity.components.fluids.FluidFilter;
import zombie.entity.components.fluids.FluidProperties;
import zombie.entity.components.fluids.FluidSample;
import zombie.entity.components.fluids.FluidType;
import zombie.entity.components.fluids.FluidUtil;
import zombie.entity.components.fluids.PoisonEffect;
import zombie.entity.components.fluids.PoisonInfo;
import zombie.entity.components.fluids.SealedFluidProperties;
import zombie.entity.components.lua.LuaComponent;
import zombie.entity.components.parts.Parts;
import zombie.entity.components.resources.Resource;
import zombie.entity.components.resources.ResourceBlueprint;
import zombie.entity.components.resources.ResourceChannel;
import zombie.entity.components.resources.ResourceEnergy;
import zombie.entity.components.resources.ResourceFlag;
import zombie.entity.components.resources.ResourceFluid;
import zombie.entity.components.resources.ResourceIO;
import zombie.entity.components.resources.ResourceItem;
import zombie.entity.components.resources.ResourceType;
import zombie.entity.components.resources.Resources;
import zombie.entity.components.script.EntityScriptInfo;
import zombie.entity.components.signals.Signals;
import zombie.entity.components.sounds.CraftBenchSounds;
import zombie.entity.components.spriteconfig.SpriteConfig;
import zombie.entity.components.spriteconfig.SpriteConfigManager;
import zombie.entity.components.spriteconfig.SpriteOverlayConfig;
import zombie.entity.components.test.TestComponent;
import zombie.entity.components.ui.UiConfig;
import zombie.entity.debug.EntityDebugTest;
import zombie.entity.debug.EntityDebugTestType;
import zombie.entity.energy.Energy;
import zombie.entity.energy.EnergyType;
import zombie.entity.events.ComponentEvent;
import zombie.entity.events.ComponentEventType;
import zombie.entity.events.EntityEvent;
import zombie.entity.events.EntityEventType;
import zombie.entity.meta.MetaTagComponent;
import zombie.entity.util.Array;
import zombie.entity.util.BitSet;
import zombie.entity.util.GameEntityUtil;
import zombie.entity.util.ImmutableArray;
import zombie.entity.util.assoc.AssocArray;
import zombie.entity.util.assoc.AssocEnumArray;
import zombie.erosion.ErosionConfig;
import zombie.erosion.ErosionData;
import zombie.erosion.ErosionMain;
import zombie.erosion.season.ErosionSeason;
import zombie.gameStates.AnimationViewerState;
import zombie.gameStates.AttachmentEditorState;
import zombie.gameStates.ChooseGameInfo;
import zombie.gameStates.ConnectToServerState;
import zombie.gameStates.DebugChunkState;
import zombie.gameStates.DebugGlobalObjectState;
import zombie.gameStates.GameLoadingState;
import zombie.gameStates.GameState;
import zombie.gameStates.IngameState;
import zombie.gameStates.LoadingQueueState;
import zombie.gameStates.MainScreenState;
import zombie.gameStates.SeamEditorState;
import zombie.gameStates.SpriteModelEditorState;
import zombie.gameStates.TermsOfServiceState;
import zombie.gameStates.TileGeometryState;
import zombie.gizmo.Gizmos;
import zombie.gizmo.RotateGizmo;
import zombie.gizmo.TransformMode;
import zombie.gizmo.TranslateGizmo;
import zombie.globalObjects.CGlobalObject;
import zombie.globalObjects.CGlobalObjectSystem;
import zombie.globalObjects.CGlobalObjects;
import zombie.globalObjects.SGlobalObject;
import zombie.globalObjects.SGlobalObjectSystem;
import zombie.globalObjects.SGlobalObjects;
import zombie.input.GameKeyboard;
import zombie.input.JoypadManager;
import zombie.input.Mouse;
import zombie.inventory.FixingManager;
import zombie.inventory.InventoryItem;
import zombie.inventory.InventoryItemFactory;
import zombie.inventory.ItemContainer;
import zombie.inventory.ItemPickerJava;
import zombie.inventory.ItemSpawner;
import zombie.inventory.RecipeManager;
import zombie.inventory.recipemanager.RecipeMonitor;
import zombie.inventory.types.AlarmClock;
import zombie.inventory.types.AlarmClockClothing;
import zombie.inventory.types.AnimalInventoryItem;
import zombie.inventory.types.Clothing;
import zombie.inventory.types.ComboItem;
import zombie.inventory.types.Drainable;
import zombie.inventory.types.DrainableComboItem;
import zombie.inventory.types.Food;
import zombie.inventory.types.HandWeapon;
import zombie.inventory.types.InventoryContainer;
import zombie.inventory.types.Key;
import zombie.inventory.types.KeyRing;
import zombie.inventory.types.Literature;
import zombie.inventory.types.Moveable;
import zombie.inventory.types.Radio;
import zombie.inventory.types.WeaponPart;
import zombie.inventory.types.WeaponType;
import zombie.iso.BentFences;
import zombie.iso.BrokenFences;
import zombie.iso.BuildingDef;
import zombie.iso.CellLoader;
import zombie.iso.ContainerOverlays;
import zombie.iso.FishSchoolManager;
import zombie.iso.ISWorldObjectContextMenuLogic;
import zombie.iso.IsoButcherHook;
import zombie.iso.IsoCamera;
import zombie.iso.IsoCell;
import zombie.iso.IsoChunk;
import zombie.iso.IsoChunkMap;
import zombie.iso.IsoDepthHelper;
import zombie.iso.IsoDirectionSet;
import zombie.iso.IsoDirections;
import zombie.iso.IsoGridSquare;
import zombie.iso.IsoHeatSource;
import zombie.iso.IsoLightSource;
import zombie.iso.IsoLot;
import zombie.iso.IsoLuaMover;
import zombie.iso.IsoMarkers;
import zombie.iso.IsoMetaCell;
import zombie.iso.IsoMetaChunk;
import zombie.iso.IsoMetaGrid;
import zombie.iso.IsoMovingObject;
import zombie.iso.IsoObject;
import zombie.iso.IsoObjectPicker;
import zombie.iso.IsoPuddles;
import zombie.iso.IsoPushableObject;
import zombie.iso.IsoUtils;
import zombie.iso.IsoWaterGeometry;
import zombie.iso.IsoWorld;
import zombie.iso.LightingJNI;
import zombie.iso.LosUtil;
import zombie.iso.MetaObject;
import zombie.iso.NewMapBinaryFile;
import zombie.iso.PlayerCamera;
import zombie.iso.RoomDef;
import zombie.iso.SearchMode;
import zombie.iso.SliceY;
import zombie.iso.SpriteDetails.IsoFlagType;
import zombie.iso.SpriteDetails.IsoObjectType;
import zombie.iso.SpriteModel;
import zombie.iso.TileOverlays;
import zombie.iso.Vector2;
import zombie.iso.Vector3;
import zombie.iso.WorldMarkers;
import zombie.iso.WorldObjectContextMenuConstant;
import zombie.iso.areas.DesignationZone;
import zombie.iso.areas.DesignationZoneAnimal;
import zombie.iso.areas.IsoBuilding;
import zombie.iso.areas.IsoRoom;
import zombie.iso.areas.NonPvpZone;
import zombie.iso.areas.SafeHouse;
import zombie.iso.areas.isoregion.IsoRegionLogType;
import zombie.iso.areas.isoregion.IsoRegions;
import zombie.iso.areas.isoregion.IsoRegionsLogger;
import zombie.iso.areas.isoregion.IsoRegionsRenderer;
import zombie.iso.areas.isoregion.data.DataCell;
import zombie.iso.areas.isoregion.data.DataChunk;
import zombie.iso.areas.isoregion.regions.IsoChunkRegion;
import zombie.iso.areas.isoregion.regions.IsoWorldRegion;
import zombie.iso.fboRenderChunk.FBORenderAreaHighlights;
import zombie.iso.fboRenderChunk.FBORenderCell;
import zombie.iso.fboRenderChunk.FBORenderChunk;
import zombie.iso.fboRenderChunk.FBORenderTracerEffects;
import zombie.iso.objects.IsoAnimalTrack;
import zombie.iso.objects.IsoBarbecue;
import zombie.iso.objects.IsoBarricade;
import zombie.iso.objects.IsoBrokenGlass;
import zombie.iso.objects.IsoCarBatteryCharger;
import zombie.iso.objects.IsoClothingDryer;
import zombie.iso.objects.IsoClothingWasher;
import zombie.iso.objects.IsoCombinationWasherDryer;
import zombie.iso.objects.IsoCompost;
import zombie.iso.objects.IsoCurtain;
import zombie.iso.objects.IsoDeadBody;
import zombie.iso.objects.IsoDoor;
import zombie.iso.objects.IsoFeedingTrough;
import zombie.iso.objects.IsoFire;
import zombie.iso.objects.IsoFireManager;
import zombie.iso.objects.IsoFireplace;
import zombie.iso.objects.IsoGenerator;
import zombie.iso.objects.IsoHutch;
import zombie.iso.objects.IsoJukebox;
import zombie.iso.objects.IsoLightSwitch;
import zombie.iso.objects.IsoMannequin;
import zombie.iso.objects.IsoMolotovCocktail;
import zombie.iso.objects.IsoRadio;
import zombie.iso.objects.IsoStackedWasherDryer;
import zombie.iso.objects.IsoStove;
import zombie.iso.objects.IsoTelevision;
import zombie.iso.objects.IsoThumpable;
import zombie.iso.objects.IsoTrap;
import zombie.iso.objects.IsoTree;
import zombie.iso.objects.IsoWaveSignal;
import zombie.iso.objects.IsoWheelieBin;
import zombie.iso.objects.IsoWindow;
import zombie.iso.objects.IsoWindowFrame;
import zombie.iso.objects.IsoWorldInventoryObject;
import zombie.iso.objects.IsoZombieGiblets;
import zombie.iso.objects.ObjectRenderEffects;
import zombie.iso.objects.RainManager;
import zombie.iso.objects.interfaces.BarricadeAble;
import zombie.iso.sprite.IsoSprite;
import zombie.iso.sprite.IsoSpriteGrid;
import zombie.iso.sprite.IsoSpriteInstance;
import zombie.iso.sprite.IsoSpriteManager;
import zombie.iso.weather.ClimateColorInfo;
import zombie.iso.weather.ClimateForecaster;
import zombie.iso.weather.ClimateHistory;
import zombie.iso.weather.ClimateManager;
import zombie.iso.weather.ClimateMoon;
import zombie.iso.weather.ClimateValues;
import zombie.iso.weather.Temperature;
import zombie.iso.weather.ThunderStorm;
import zombie.iso.weather.WeatherPeriod;
import zombie.iso.weather.WorldFlares;
import zombie.iso.weather.fog.ImprovedFog;
import zombie.iso.weather.fx.IsoWeatherFX;
import zombie.iso.worldgen.WorldGenParams;
import zombie.iso.worldgen.WorldGenUtils;
import zombie.iso.zones.Trigger;
import zombie.iso.zones.VehicleZone;
import zombie.iso.zones.Zone;
import zombie.modding.ActiveMods;
import zombie.modding.ActiveModsFile;
import zombie.modding.ModUtilsJava;
import zombie.network.Account;
import zombie.network.ConnectionManager;
import zombie.network.CoopMaster;
import zombie.network.DBBannedIP;
import zombie.network.DBBannedSteamID;
import zombie.network.DBResult;
import zombie.network.DBTicket;
import zombie.network.DesktopBrowser;
import zombie.network.GameClient;
import zombie.network.GameServer;
import zombie.network.NetChecksum;
import zombie.network.NetworkAIParams;
import zombie.network.PVPLogTool;
import zombie.network.PZNetKahluaTableImpl;
import zombie.network.PacketTypes;
import zombie.network.Server;
import zombie.network.ServerOptions;
import zombie.network.ServerSettings;
import zombie.network.ServerSettingsManager;
import zombie.network.ServerWorldDatabase;
import zombie.network.Userlog;
import zombie.network.WarManager;
import zombie.network.anticheats.AntiCheatCapability;
import zombie.network.chat.ChatServer;
import zombie.network.chat.ChatType;
import zombie.network.fields.ContainerID;
import zombie.network.packets.BodyPartSyncPacket;
import zombie.network.packets.INetworkPacket;
import zombie.network.packets.NetTimedActionPacket;
import zombie.network.packets.SyncPlayerStatsPacket;
import zombie.network.packets.VariableSyncPacket;
import zombie.network.packets.character.AnimalCommandPacket;
import zombie.network.server.AnimEventEmulator;
import zombie.network.statistics.StatusManager;
import zombie.network.statistics.data.GameStatistic;
import zombie.network.statistics.data.NetworkStatistic;
import zombie.network.statistics.data.PerformanceStatistic;
import zombie.pathfind.PathFindBehavior2;
import zombie.popman.ZombiePopulationManager;
import zombie.popman.ZombiePopulationRenderer;
import zombie.popman.animal.AnimalInstanceManager;
import zombie.popman.animal.AnimalSynchronizationManager;
import zombie.profanity.ProfanityFilter;
import zombie.radio.ChannelCategory;
import zombie.radio.RadioAPI;
import zombie.radio.RadioData;
import zombie.radio.StorySounds.DataPoint;
import zombie.radio.StorySounds.EventSound;
import zombie.radio.StorySounds.SLSoundManager;
import zombie.radio.StorySounds.StorySound;
import zombie.radio.StorySounds.StorySoundEvent;
import zombie.radio.ZomboidRadio;
import zombie.radio.devices.DeviceData;
import zombie.radio.devices.DevicePresets;
import zombie.radio.devices.PresetEntry;
import zombie.radio.devices.WaveSignalDevice;
import zombie.radio.media.MediaData;
import zombie.radio.media.RecordedMedia;
import zombie.radio.scripting.DynamicRadioChannel;
import zombie.radio.scripting.RadioBroadCast;
import zombie.radio.scripting.RadioChannel;
import zombie.radio.scripting.RadioLine;
import zombie.radio.scripting.RadioScript;
import zombie.radio.scripting.RadioScriptManager;
import zombie.randomizedWorld.RandomizedWorldBase;
import zombie.randomizedWorld.randomizedBuilding.RBBar;
import zombie.randomizedWorld.randomizedBuilding.RBBarn;
import zombie.randomizedWorld.randomizedBuilding.RBBasic;
import zombie.randomizedWorld.randomizedBuilding.RBBurnt;
import zombie.randomizedWorld.randomizedBuilding.RBBurntCorpse;
import zombie.randomizedWorld.randomizedBuilding.RBBurntFireman;
import zombie.randomizedWorld.randomizedBuilding.RBCafe;
import zombie.randomizedWorld.randomizedBuilding.RBClinic;
import zombie.randomizedWorld.randomizedBuilding.RBDorm;
import zombie.randomizedWorld.randomizedBuilding.RBGunstoreSiege;
import zombie.randomizedWorld.randomizedBuilding.RBHairSalon;
import zombie.randomizedWorld.randomizedBuilding.RBHeatBreakAfternoon;
import zombie.randomizedWorld.randomizedBuilding.RBJackieJaye;
import zombie.randomizedWorld.randomizedBuilding.RBJoanHartford;
import zombie.randomizedWorld.randomizedBuilding.RBJudge;
import zombie.randomizedWorld.randomizedBuilding.RBKateAndBaldspot;
import zombie.randomizedWorld.randomizedBuilding.RBLooted;
import zombie.randomizedWorld.randomizedBuilding.RBMayorWestPoint;
import zombie.randomizedWorld.randomizedBuilding.RBNolans;
import zombie.randomizedWorld.randomizedBuilding.RBOffice;
import zombie.randomizedWorld.randomizedBuilding.RBOther;
import zombie.randomizedWorld.randomizedBuilding.RBPileOCrepe;
import zombie.randomizedWorld.randomizedBuilding.RBPizzaWhirled;
import zombie.randomizedWorld.randomizedBuilding.RBPoliceSiege;
import zombie.randomizedWorld.randomizedBuilding.RBReverend;
import zombie.randomizedWorld.randomizedBuilding.RBSafehouse;
import zombie.randomizedWorld.randomizedBuilding.RBSchool;
import zombie.randomizedWorld.randomizedBuilding.RBShopLooted;
import zombie.randomizedWorld.randomizedBuilding.RBSpiffo;
import zombie.randomizedWorld.randomizedBuilding.RBStripclub;
import zombie.randomizedWorld.randomizedBuilding.RBTrashed;
import zombie.randomizedWorld.randomizedBuilding.RBTwiggy;
import zombie.randomizedWorld.randomizedBuilding.RBWoodcraft;
import zombie.randomizedWorld.randomizedBuilding.RandomizedBuildingBase;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSBandPractice;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSBanditRaid;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSBathroomZed;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSBedroomZed;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSBleach;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSCorpsePsycho;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSDeadDrunk;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSDevouredByRats;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSFootballNight;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSGrouchos;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSGunmanInBathroom;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSGunslinger;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSHenDo;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSHockeyPsycho;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSHouseParty;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSPokerNight;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSPoliceAtHouse;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSPrisonEscape;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSPrisonEscapeWithPolice;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSRPGNight;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSRatInfested;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSRatKing;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSRatWar;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSResourceGarage;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSSkeletonPsycho;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSSpecificProfession;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSStagDo;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSStudentNight;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSSuicidePact;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSTinFoilHat;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSZombieLockedBathroom;
import zombie.randomizedWorld.randomizedDeadSurvivor.RDSZombiesEating;
import zombie.randomizedWorld.randomizedDeadSurvivor.RandomizedDeadSurvivorBase;
import zombie.randomizedWorld.randomizedVehicleStory.RVSAmbulanceCrash;
import zombie.randomizedWorld.randomizedVehicleStory.RVSAnimalOnRoad;
import zombie.randomizedWorld.randomizedVehicleStory.RVSAnimalTrailerOnRoad;
import zombie.randomizedWorld.randomizedVehicleStory.RVSBanditRoad;
import zombie.randomizedWorld.randomizedVehicleStory.RVSBurntCar;
import zombie.randomizedWorld.randomizedVehicleStory.RVSCarCrash;
import zombie.randomizedWorld.randomizedVehicleStory.RVSCarCrashCorpse;
import zombie.randomizedWorld.randomizedVehicleStory.RVSCarCrashDeer;
import zombie.randomizedWorld.randomizedVehicleStory.RVSChangingTire;
import zombie.randomizedWorld.randomizedVehicleStory.RVSConstructionSite;
import zombie.randomizedWorld.randomizedVehicleStory.RVSCrashHorde;
import zombie.randomizedWorld.randomizedVehicleStory.RVSDeadEnd;
import zombie.randomizedWorld.randomizedVehicleStory.RVSFlippedCrash;
import zombie.randomizedWorld.randomizedVehicleStory.RVSHerdOnRoad;
import zombie.randomizedWorld.randomizedVehicleStory.RVSPlonkies;
import zombie.randomizedWorld.randomizedVehicleStory.RVSPoliceBlockade;
import zombie.randomizedWorld.randomizedVehicleStory.RVSPoliceBlockadeShooting;
import zombie.randomizedWorld.randomizedVehicleStory.RVSRegionalProfessionVehicle;
import zombie.randomizedWorld.randomizedVehicleStory.RVSRichJerk;
import zombie.randomizedWorld.randomizedVehicleStory.RVSRoadKill;
import zombie.randomizedWorld.randomizedVehicleStory.RVSRoadKillSmall;
import zombie.randomizedWorld.randomizedVehicleStory.RVSTrailerCrash;
import zombie.randomizedWorld.randomizedVehicleStory.RVSUtilityVehicle;
import zombie.randomizedWorld.randomizedVehicleStory.RandomizedVehicleStoryBase;
import zombie.randomizedWorld.randomizedZoneStory.RZJackieJaye;
import zombie.randomizedWorld.randomizedZoneStory.RZSAttachedAnimal;
import zombie.randomizedWorld.randomizedZoneStory.RZSBBQParty;
import zombie.randomizedWorld.randomizedZoneStory.RZSBaseball;
import zombie.randomizedWorld.randomizedZoneStory.RZSBeachParty;
import zombie.randomizedWorld.randomizedZoneStory.RZSBurntWreck;
import zombie.randomizedWorld.randomizedZoneStory.RZSBuryingCamp;
import zombie.randomizedWorld.randomizedZoneStory.RZSCampsite;
import zombie.randomizedWorld.randomizedZoneStory.RZSCharcoalBurner;
import zombie.randomizedWorld.randomizedZoneStory.RZSDean;
import zombie.randomizedWorld.randomizedZoneStory.RZSDuke;
import zombie.randomizedWorld.randomizedZoneStory.RZSEscapedAnimal;
import zombie.randomizedWorld.randomizedZoneStory.RZSEscapedHerd;
import zombie.randomizedWorld.randomizedZoneStory.RZSFishingTrip;
import zombie.randomizedWorld.randomizedZoneStory.RZSForestCamp;
import zombie.randomizedWorld.randomizedZoneStory.RZSForestCampEaten;
import zombie.randomizedWorld.randomizedZoneStory.RZSFrankHemingway;
import zombie.randomizedWorld.randomizedZoneStory.RZSHermitCamp;
import zombie.randomizedWorld.randomizedZoneStory.RZSHillbillyHoedown;
import zombie.randomizedWorld.randomizedZoneStory.RZSHogWild;
import zombie.randomizedWorld.randomizedZoneStory.RZSHunterCamp;
import zombie.randomizedWorld.randomizedZoneStory.RZSKirstyKormick;
import zombie.randomizedWorld.randomizedZoneStory.RZSMurderScene;
import zombie.randomizedWorld.randomizedZoneStory.RZSMusicFest;
import zombie.randomizedWorld.randomizedZoneStory.RZSMusicFestStage;
import zombie.randomizedWorld.randomizedZoneStory.RZSNastyMattress;
import zombie.randomizedWorld.randomizedZoneStory.RZSOccultActivity;
import zombie.randomizedWorld.randomizedZoneStory.RZSOldFirepit;
import zombie.randomizedWorld.randomizedZoneStory.RZSOldShelter;
import zombie.randomizedWorld.randomizedZoneStory.RZSOrphanedFawn;
import zombie.randomizedWorld.randomizedZoneStory.RZSRangerSmith;
import zombie.randomizedWorld.randomizedZoneStory.RZSRockerParty;
import zombie.randomizedWorld.randomizedZoneStory.RZSSadCamp;
import zombie.randomizedWorld.randomizedZoneStory.RZSSexyTime;
import zombie.randomizedWorld.randomizedZoneStory.RZSSirTwiggy;
import zombie.randomizedWorld.randomizedZoneStory.RZSSurvivalistCamp;
import zombie.randomizedWorld.randomizedZoneStory.RZSTragicPicnic;
import zombie.randomizedWorld.randomizedZoneStory.RZSTrapperCamp;
import zombie.randomizedWorld.randomizedZoneStory.RZSVanCamp;
import zombie.randomizedWorld.randomizedZoneStory.RZSWasteDump;
import zombie.randomizedWorld.randomizedZoneStory.RZSWaterPump;
import zombie.randomizedWorld.randomizedZoneStory.RandomizedZoneStoryBase;
import zombie.savefile.AccountDBHelper;
import zombie.savefile.ClientPlayerDB;
import zombie.savefile.PlayerDBHelper;
import zombie.savefile.SavefileNaming;
import zombie.scripting.ScriptManager;
import zombie.scripting.ScriptType;
import zombie.scripting.entity.ComponentScript;
import zombie.scripting.entity.GameEntityScript;
import zombie.scripting.entity.GameEntityTemplate;
import zombie.scripting.entity.components.attributes.AttributesScript;
import zombie.scripting.entity.components.contextmenuconfig.ContextMenuConfigScript;
import zombie.scripting.entity.components.crafting.CraftBenchScript;
import zombie.scripting.entity.components.crafting.CraftLogicScript;
import zombie.scripting.entity.components.crafting.CraftRecipe;
import zombie.scripting.entity.components.crafting.CraftRecipeComponentScript;
import zombie.scripting.entity.components.crafting.FurnaceLogicScript;
import zombie.scripting.entity.components.crafting.InputScript;
import zombie.scripting.entity.components.crafting.MashingLogicScript;
import zombie.scripting.entity.components.crafting.OutputScript;
import zombie.scripting.entity.components.crafting.WallCoveringConfigScript;
import zombie.scripting.entity.components.fluids.FluidContainerScript;
import zombie.scripting.entity.components.lua.LuaComponentScript;
import zombie.scripting.entity.components.parts.PartsScript;
import zombie.scripting.entity.components.signals.SignalsScript;
import zombie.scripting.entity.components.spriteconfig.SpriteConfigScript;
import zombie.scripting.entity.components.test.TestComponentScript;
import zombie.scripting.entity.components.ui.UiConfigScript;
import zombie.scripting.itemConfig.ItemConfig;
import zombie.scripting.logic.ItemCodeOnCreate;
import zombie.scripting.logic.ItemCodeOnTest;
import zombie.scripting.logic.RecipeCodeOnCooked;
import zombie.scripting.logic.RecipeCodeOnCreate;
import zombie.scripting.logic.RecipeCodeOnEat;
import zombie.scripting.logic.RecipeCodeOnTest;
import zombie.scripting.objects.ActionSoundTime;
import zombie.scripting.objects.AmmoType;
import zombie.scripting.objects.AnimationsMesh;
import zombie.scripting.objects.BaseScriptObject;
import zombie.scripting.objects.Brochure;
import zombie.scripting.objects.CharacterProfession;
import zombie.scripting.objects.CharacterTrait;
import zombie.scripting.objects.CraftRecipeGroup;
import zombie.scripting.objects.DigType;
import zombie.scripting.objects.EnergyDefinitionScript;
import zombie.scripting.objects.EvolvedRecipe;
import zombie.scripting.objects.Fixing;
import zombie.scripting.objects.Flier;
import zombie.scripting.objects.FluidDefinitionScript;
import zombie.scripting.objects.FluidFilterScript;
import zombie.scripting.objects.GameSoundScript;
import zombie.scripting.objects.Item;
import zombie.scripting.objects.ItemBodyLocation;
import zombie.scripting.objects.ItemFilterScript;
import zombie.scripting.objects.ItemKey;
import zombie.scripting.objects.ItemRecipe;
import zombie.scripting.objects.ItemTag;
import zombie.scripting.objects.ItemType;
import zombie.scripting.objects.MannequinScript;
import zombie.scripting.objects.ModelAttachment;
import zombie.scripting.objects.ModelKey;
import zombie.scripting.objects.ModelScript;
import zombie.scripting.objects.MoodleType;
import zombie.scripting.objects.MovableRecipe;
import zombie.scripting.objects.Newspaper;
import zombie.scripting.objects.PhysicsShapeScript;
import zombie.scripting.objects.Recipe;
import zombie.scripting.objects.Registries;
import zombie.scripting.objects.Registry;
import zombie.scripting.objects.ResourceLocation;
import zombie.scripting.objects.ScriptModule;
import zombie.scripting.objects.SoundTimelineScript;
import zombie.scripting.objects.StringListScript;
import zombie.scripting.objects.TimedActionScript;
import zombie.scripting.objects.UniqueRecipe;
import zombie.scripting.objects.VehiclePartModel;
import zombie.scripting.objects.VehicleScript;
import zombie.scripting.objects.VehicleTemplate;
import zombie.scripting.objects.WallCoveringType;
import zombie.scripting.objects.WeaponCategory;
import zombie.scripting.objects.WeaponReloadType;
import zombie.scripting.objects.XuiColorsScript;
import zombie.scripting.objects.XuiConfigScript;
import zombie.scripting.objects.XuiLayoutScript;
import zombie.scripting.objects.XuiSkinScript;
import zombie.scripting.ui.TextAlign;
import zombie.scripting.ui.VectorPosAlign;
import zombie.scripting.ui.XuiAutoApply;
import zombie.scripting.ui.XuiLuaStyle;
import zombie.scripting.ui.XuiManager;
import zombie.scripting.ui.XuiReference;
import zombie.scripting.ui.XuiScript;
import zombie.scripting.ui.XuiScriptType;
import zombie.scripting.ui.XuiSkin;
import zombie.scripting.ui.XuiTableScript;
import zombie.scripting.ui.XuiVarType;
import zombie.seams.SeamManager;
import zombie.seating.SeatingManager;
import zombie.spnetwork.SinglePlayerClient;
import zombie.spriteModel.SpriteModelManager;
import zombie.text.templating.ReplaceProviderCharacter;
import zombie.text.templating.TemplateText;
import zombie.tileDepth.TileDepthTexture;
import zombie.tileDepth.TileDepthTextureAssignmentManager;
import zombie.tileDepth.TileDepthTextureManager;
import zombie.tileDepth.TileDepthTextures;
import zombie.tileDepth.TileGeometryManager;
import zombie.tileDepth.TilesetDepthTexture;
import zombie.ui.ActionProgressBar;
import zombie.ui.AtomUI;
import zombie.ui.AtomUIMap;
import zombie.ui.AtomUIText;
import zombie.ui.AtomUITextEntry;
import zombie.ui.AtomUITexture;
import zombie.ui.Clock;
import zombie.ui.ModalDialog;
import zombie.ui.MoodlesUI;
import zombie.ui.NewHealthPanel;
import zombie.ui.ObjectTooltip;
import zombie.ui.RadarPanel;
import zombie.ui.RadialMenu;
import zombie.ui.RadialProgressBar;
import zombie.ui.SpeedControls;
import zombie.ui.TextDrawObject;
import zombie.ui.TextManager;
import zombie.ui.UI3DModel;
import zombie.ui.UIDebugConsole;
import zombie.ui.UIElement;
import zombie.ui.UIFont;
import zombie.ui.UIManager;
import zombie.ui.UITextBox2;
import zombie.ui.UITransition;
import zombie.ui.VehicleGauge;
import zombie.util.AddCoopPlayer;
import zombie.util.PZCalendar;
import zombie.util.PublicServerUtil;
import zombie.util.StringUtils;
import zombie.util.Type;
import zombie.util.list.PZArrayList;
import zombie.util.list.PZArrayUtil;
import zombie.util.list.PZUnmodifiableList;
import zombie.vehicles.BaseVehicle;
import zombie.vehicles.EditVehicleState;
import zombie.vehicles.UI3DScene;
import zombie.vehicles.VehicleDoor;
import zombie.vehicles.VehicleEngineRPM;
import zombie.vehicles.VehicleLight;
import zombie.vehicles.VehicleManager;
import zombie.vehicles.VehiclePart;
import zombie.vehicles.VehicleType;
import zombie.vehicles.VehicleWindow;
import zombie.vehicles.VehiclesDB2;
import zombie.world.moddata.ModData;
import zombie.worldMap.UIWorldMap;
import zombie.Lua.LuaSQLite;

/* JADX INFO: loaded from: projectzomboid.jar:zombie/Lua/LuaManager.class */
public final class LuaManager {
    public static KahluaTable env;
    public static KahluaThread thread;
    public static KahluaThread debugthread;
    public static Exposer exposer;
    public static boolean checksumDone;
    private static KahluaArrayConverter arrayConverter;
    public static KahluaConverterManager converterManager = new KahluaConverterManager();
    public static J2SEPlatform platform = new J2SEPlatform();
    public static LuaCaller caller = new LuaCaller(converterManager);
    public static LuaCaller debugcaller = new LuaCaller(converterManager);
    public static ArrayList<String> loaded = new ArrayList<>();
    private static final HashSet<String> loading = new HashSet<>();
    public static HashMap<String, Object> loadedReturn = new HashMap<>();
    public static ArrayList<String> loadList = new ArrayList<>();
    private static final ArrayList<String> paths = new ArrayList<>();
    private static final HashMap<String, Object> luaFunctionMap = new HashMap<>();
    private static final HashMap<String, Object> luaTableMap = new HashMap<>();
    private static final HashMap<String, VideoTexture> videoTextures = new HashMap<>();
    private static final HashSet<KahluaTable> s_wiping = new HashSet<>();

    public static void outputTable(KahluaTable t, int nTabs) {
    }

    private static void wipeRecurse(KahluaTable table) {
        if (table.isEmpty() || s_wiping.contains(table)) {
            return;
        }
        s_wiping.add(table);
        KahluaTableIterator it = table.iterator();
        while (it.advance()) {
            Object value = it.getValue();
            if (value instanceof KahluaTable) {
                KahluaTable table1 = (KahluaTable) value;
                wipeRecurse(table1);
            }
        }
        s_wiping.remove(table);
        table.wipe();
    }

    public static void init() {
        loaded.clear();
        loading.clear();
        loadedReturn.clear();
        paths.clear();
        luaFunctionMap.clear();
        luaTableMap.clear();
        platform = new J2SEPlatform();
        if (env != null) {
            s_wiping.clear();
            wipeRecurse(env);
        }
        env = platform.newEnvironment();
        converterManager = new KahluaConverterManager();
        if (thread != null) {
            thread.reset = true;
        }
        thread = new KahluaThread(platform, env);
        debugthread = new KahluaThread(platform, env);
        thread.debugOwnerThread = Thread.currentThread();
        debugthread.debugOwnerThread = Thread.currentThread();
        UIManager.defaultthread = thread;
        caller = new LuaCaller(converterManager);
        debugcaller = new LuaCaller(converterManager);
        if (exposer != null) {
            exposer.destroy();
        }
        exposer = new Exposer(converterManager, platform, env);
        loaded = new ArrayList<>();
        checksumDone = false;
        GameClient.checksum = "";
        GameClient.checksumValid = false;
        KahluaNumberConverter.install(converterManager);
        arrayConverter = new KahluaArrayConverter(platform, converterManager);
        arrayConverter.install();
        LuaEventManager.register(platform, env);
        LuaHookManager.register(platform, env);
        CoopMaster.instance.register(platform, env);
        if (VoiceManager.instance != null) {
            VoiceManager.instance.LuaRegister(platform, env);
        }
        exposer.exposeAll();
        exposer.typeMap.put("function", LuaClosure.class);
        exposer.typeMap.put("table", KahluaTable.class);
        outputTable(env, 0);
    }

    public static void LoadDirBase(String sub) throws Exception {
        LoadDirBase(sub, false);
    }

    public static void LoadDirBase(String sub, boolean onlyChecksum) throws Exception {
        String relPath = "media/lua/" + sub + "/";
        File fo = ZomboidFileSystem.instance.getMediaFile("lua" + File.separator + sub);
        if (!paths.contains(relPath)) {
            paths.add(relPath);
        }
        try {
            searchFolders(ZomboidFileSystem.instance.base.lowercaseUri, fo);
        } catch (IOException e) {
            ExceptionLogger.logException(e);
        }
        ArrayList<String> gameFiles = loadList;
        ArrayList<String> modFiles = new ArrayList<>();
        loadList = new ArrayList<>();
        ArrayList<String> root = ZomboidFileSystem.instance.getModIDs();
        for (int n = 0; n < root.size(); n++) {
            loadList.clear();
            ChooseGameInfo.Mod mod = ChooseGameInfo.getAvailableModDetails(root.get(n));
            if (mod != null) {
                String modDir = mod.getCommonDir();
                if (modDir != null) {
                    File modDirFile = new File(modDir);
                    URI lowercaseURI = new File(modDirFile.getCanonicalFile().getPath().toLowerCase(Locale.ENGLISH)).toURI();
                    File canonicalMedia = ZomboidFileSystem.instance.getCanonicalFile(modDirFile, "media");
                    File canonicalLua = ZomboidFileSystem.instance.getCanonicalFile(canonicalMedia, "lua");
                    File canonicalSubdir = ZomboidFileSystem.instance.getCanonicalFile(canonicalLua, sub);
                    try {
                        searchFolders(lowercaseURI, canonicalSubdir);
                    } catch (IOException e2) {
                        ExceptionLogger.logException(e2);
                    }
                }
                String modDir2 = mod.getVersionDir();
                if (modDir2 != null) {
                    File modDirFile2 = new File(modDir2);
                    URI lowercaseURI2 = new File(modDirFile2.getCanonicalFile().getPath().toLowerCase(Locale.ENGLISH)).toURI();
                    File canonicalMedia2 = ZomboidFileSystem.instance.getCanonicalFile(modDirFile2, "media");
                    File canonicalLua2 = ZomboidFileSystem.instance.getCanonicalFile(canonicalMedia2, "lua");
                    File canonicalSubdir2 = ZomboidFileSystem.instance.getCanonicalFile(canonicalLua2, sub);
                    try {
                        searchFolders(lowercaseURI2, canonicalSubdir2);
                    } catch (IOException e3) {
                        ExceptionLogger.logException(e3);
                    }
                }
                Collections.sort(loadList, String.CASE_INSENSITIVE_ORDER);
                PZArrayUtil.addAll(modFiles, loadList);
            }
        }
        Collections.sort(gameFiles, String.CASE_INSENSITIVE_ORDER);
        PZArrayUtil.addAll(gameFiles, modFiles);
        loadList.clear();
        loadList = gameFiles;
        HashSet<String> done = new HashSet<>();
        for (String s : loadList) {
            if (!done.contains(s)) {
                done.add(s);
                String absPath = ZomboidFileSystem.instance.getAbsolutePath(s);
                if (absPath == null) {
                    throw new IllegalStateException("couldn't find \"" + s + "\"");
                }
                if (!onlyChecksum) {
                    RunLua(absPath);
                }
                if (!checksumDone && !s.contains("SandboxVars.lua") && (GameServer.server || GameClient.client)) {
                    NetChecksum.checksummer.addFile(s, absPath);
                }
                CoopMaster.instance.update();
            }
        }
        loadList.clear();
    }

    public static void initChecksum() throws Exception {
        if (checksumDone) {
            return;
        }
        if (GameClient.client || GameServer.server) {
            NetChecksum.checksummer.reset(false);
        }
    }

    public static void finishChecksum() {
        if (GameServer.server) {
            GameServer.checksum = NetChecksum.checksummer.checksumToString();
            DebugLog.Lua.println("luaChecksum: " + GameServer.checksum);
        } else if (GameClient.client) {
            GameClient.checksum = NetChecksum.checksummer.checksumToString();
        } else {
            return;
        }
        NetChecksum.GroupOfFiles.finishChecksum();
        checksumDone = true;
    }

    public static void LoadDirBase() throws Exception {
        initChecksum();
        LoadDirBase("shared");
        LoadDirBase("client");
    }

    public static void searchFolders(URI base, File fo) throws IOException {
        if (fo.isDirectory()) {
            String[] internalNames = fo.list();
            for (String str : internalNames) {
                searchFolders(base, new File(fo.getCanonicalFile().getAbsolutePath() + File.separator + str));
            }
            return;
        }
        if (fo.getAbsolutePath().toLowerCase().endsWith(".lua")) {
            String relPath = ZomboidFileSystem.instance.getRelativeFile(base, fo.getAbsolutePath());
            loadList.add(relPath.toLowerCase(Locale.ENGLISH));
        }
    }

    public static String getLuaCacheDir() {
        String cacheDir = ZomboidFileSystem.instance.getCacheDir() + File.separator + "Lua";
        File cacheDirFile = new File(cacheDir);
        if (!cacheDirFile.exists()) {
            cacheDirFile.mkdir();
        }
        return cacheDir;
    }

    public static String getSandboxCacheDir() {
        String cacheDir = ZomboidFileSystem.instance.getCacheDir() + File.separator + "Sandbox Presets";
        File cacheDirFile = new File(cacheDir);
        if (!cacheDirFile.exists()) {
            cacheDirFile.mkdir();
        }
        return cacheDir;
    }

    public static boolean isIndieStoneUrl(String url) {
        return url != null && (url.equals("https://steamcommunity.com") || url.startsWith("https://steamcommunity.com/") || url.equals("https://projectzomboid.com") || url.startsWith("https://projectzomboid.com/") || url.equals("https://theindiestone.com") || url.startsWith("https://theindiestone.com/") || url.equals("https://pzwiki.net") || url.startsWith("https://pzwiki.net/"));
    }

    public static void fillContainer(ItemContainer container, IsoPlayer isoPlayer) {
        ItemPickerJava.fillContainer(container, isoPlayer);
    }

    public static void updateOverlaySprite(IsoObject obj) {
        ItemPickerJava.updateOverlaySprite(obj);
    }

    public static LuaClosure getDotDelimitedClosure(String path) {
        String[] split = path.split("\\.");
        KahluaTable tab = env;
        for (int n = 0; n < split.length - 1; n++) {
            tab = (KahluaTable) env.rawget(split[n]);
        }
        return (LuaClosure) tab.rawget(split[split.length - 1]);
    }

    public static IsoGridSquare AdjacentFreeTileFinder(IsoGridSquare test, IsoPlayer player) {
        KahluaTable adj = (KahluaTable) env.rawget("AdjacentFreeTileFinder");
        LuaClosure find = (LuaClosure) adj.rawget("Find");
        return (IsoGridSquare) caller.pcall(thread, (Object) find, test, player)[1];
    }

    public static Object RunLua(String filename) {
        return RunLua(filename, false);
    }

    /* JADX WARN: Finally extract failed */
    public static Object RunLua(String filename, boolean bRewriteEvents) {
        String filename1 = filename.replace("\\", "/");
        if (loading.contains(filename1)) {
            DebugLog.Lua.warn("recursive require(): %s", filename1);
            return null;
        }
        loading.add(filename1);
        try {
            Object objRunLuaInternal = RunLuaInternal(filename, bRewriteEvents);
            loading.remove(filename1);
            return objRunLuaInternal;
        } catch (Throwable th) {
            loading.remove(filename1);
            throw th;
        }
    }

    private static Object RunLuaInternal(String filename, boolean bRewriteEvents) {
        String filename2 = filename.replace("\\", "/");
        if (loaded.contains(filename2)) {
            return loadedReturn.get(filename2);
        }
        FuncState.currentFile = filename2.substring(filename2.lastIndexOf(47) + 1);
        FuncState.currentfullFile = filename2;
        String filename3 = ZomboidFileSystem.instance.getString(filename2.replace("\\", "/"));
        try {
            InputStreamReader isr = IndieFileLoader.getStreamReader(filename3);
            LuaCompiler.rewriteEvents = bRewriteEvents;
            try {
                BufferedReader bufferedReader = new BufferedReader(isr);
                try {
                    LuaClosure closure = LuaCompiler.loadis(bufferedReader, filename3.substring(filename3.lastIndexOf(47) + 1), env);
                    bufferedReader.close();
                    luaFunctionMap.clear();
                    luaTableMap.clear();
                    CraftRecipe.onLuaFileReloaded();
                    AttachedWeaponDefinitions.instance.dirty = true;
                    DefaultClothing.instance.dirty = true;
                    HairOutfitDefinitions.instance.dirty = true;
                    UnderwearDefinition.instance.dirty = true;
                    ZombiesZoneDefinition.dirty = true;
                    LuaReturn ret = caller.protectedCall(thread, closure, new Object[0]);
                    if (!ret.isSuccess()) {
                        Logger.getLogger(IsoWorld.class.getName()).log(Level.SEVERE, ret.getErrorString(), (Object) null);
                        if (ret.getJavaException() != null) {
                            Logger.getLogger(IsoWorld.class.getName()).log(Level.SEVERE, ret.getJavaException().toString(), (Object) null);
                        }
                        Logger.getLogger(IsoWorld.class.getName()).log(Level.SEVERE, ret.getLuaStackTrace(), (Object) null);
                    }
                    loaded.add(filename2);
                    Object retVal = (!ret.isSuccess() || ret.isEmpty()) ? null : ret.getFirst();
                    if (retVal != null) {
                        loadedReturn.put(filename2, retVal);
                    } else {
                        loadedReturn.remove(filename2);
                    }
                    LuaCompiler.rewriteEvents = false;
                    return retVal;
                } finally {
                }
            } catch (Exception ex) {
                Logger.getLogger(LuaManager.class.getName()).log(Level.SEVERE, "Error found in LUA file: " + filename3, (Object) null);
                ExceptionLogger.logException(ex);
                thread.debugException(ex);
                return null;
            }
        } catch (FileNotFoundException ex2) {
            ExceptionLogger.logException(ex2);
            return null;
        }
    }

    public static Object getFunctionObject(String functionName) {
        return getFunctionObject(functionName, DebugLog.General);
    }

    public static Object getFunctionObject(String functionName, DebugType logger) {
        Object functionObj;
        if (functionName == null || functionName.isEmpty()) {
            return null;
        }
        Object functionObj2 = luaFunctionMap.get(functionName);
        if (functionObj2 != null) {
            return functionObj2;
        }
        KahluaTable table1 = env;
        if (functionName.contains(".")) {
            String[] ss = functionName.split("\\.");
            for (int i = 0; i < ss.length - 1; i++) {
                Object objRawget = table1.rawget(ss[i]);
                if (!(objRawget instanceof KahluaTable)) {
                    if (logger != null) {
                        logger.error("no such function \"%s\"", functionName);
                        return null;
                    }
                    return null;
                }
                KahluaTable object = (KahluaTable) objRawget;
                table1 = object;
            }
            functionObj = table1.rawget(ss[ss.length - 1]);
        } else {
            functionObj = table1.rawget(functionName);
        }
        if ((functionObj instanceof JavaFunction) || (functionObj instanceof LuaClosure)) {
            luaFunctionMap.put(functionName, functionObj);
            return functionObj;
        }
        if (logger != null) {
            logger.error("no such function \"%s\"", functionName);
            return null;
        }
        return null;
    }

    public static Object getTableObject(String tableName) {
        return getTableObject(tableName, DebugType.General);
    }

    public static Object getTableObject(String tableName, DebugType logger) {
        Object tableObj;
        if (tableName == null || tableName.isEmpty()) {
            return null;
        }
        Object tableObj2 = luaTableMap.get(tableName);
        if (tableObj2 != null) {
            return tableObj2;
        }
        KahluaTable table1 = env;
        if (tableName.contains(".")) {
            String[] ss = tableName.split("\\.");
            for (int i = 0; i < ss.length - 1; i++) {
                Object objRawget = table1.rawget(ss[i]);
                if (!(objRawget instanceof KahluaTable)) {
                    if (logger != null) {
                        logger.error("no such table \"%s\"", tableName);
                        return null;
                    }
                    return null;
                }
                KahluaTable object = (KahluaTable) objRawget;
                table1 = object;
            }
            tableObj = table1.rawget(ss[ss.length - 1]);
        } else {
            tableObj = table1.rawget(tableName);
        }
        if (tableObj instanceof KahluaTable) {
            luaTableMap.put(tableName, tableObj);
            return tableObj;
        }
        if (logger != null) {
            logger.error("no such table \"%s\"", tableName);
            return null;
        }
        return null;
    }

    public static Object get(Object key) {
        return env.rawget(key);
    }

    public static void call(String func, Object param1) {
        caller.pcall(thread, env.rawget(func), param1);
    }

    private static void exposeKeyboardKeys(KahluaTable baseContainer) {
        Object obj = baseContainer.rawget("Keyboard");
        if (!(obj instanceof KahluaTable)) {
            return;
        }
        KahluaTable kb = (KahluaTable) obj;
        Field[] fields = Keyboard.class.getFields();
        try {
            for (Field field : fields) {
                if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()) && field.getType().equals(Integer.TYPE) && field.getName().startsWith("KEY_") && !field.getName().endsWith("WIN")) {
                    kb.rawset(field.getName(), Double.valueOf(field.getInt(null)));
                }
            }
        } catch (Exception e) {
        }
    }

    private static void exposeMouseButtons(KahluaTable baseContainer) {
        Object obj = baseContainer.rawget("Mouse");
        if (!(obj instanceof KahluaTable)) {
            return;
        }
        KahluaTable kb = (KahluaTable) obj;
        Field[] fields = Mouse.class.getFields();
        try {
            for (Field field : fields) {
                String name = field.getName();
                if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()) && field.getType().equals(Integer.TYPE) && (name.startsWith("BTN_") || name.equals("LMB") || name.equals("RMB") || name.equals("MMB"))) {
                    kb.rawset(name, Double.valueOf(field.getInt(null)));
                }
            }
        } catch (Exception e) {
        }
    }

    private static void exposeLuaCalendar() {
        KahluaTable luaCalendarTable = (KahluaTable) env.rawget("PZCalendar");
        if (luaCalendarTable == null) {
            return;
        }
        Field[] fields = Calendar.class.getFields();
        try {
            for (Field field : fields) {
                if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()) && field.getType().equals(Integer.TYPE)) {
                    luaCalendarTable.rawset(field.getName(), BoxedStaticValues.toDouble(field.getInt(null)));
                }
            }
        } catch (Exception e) {
        }
        env.rawset("Calendar", luaCalendarTable);
    }

    public static String getHourMinuteJava() {
        String minutes = String.valueOf(Calendar.getInstance().get(12));
        if (Calendar.getInstance().get(12) < 10) {
            minutes = "0" + minutes;
        }
        return Calendar.getInstance().get(11) + ":" + minutes;
    }

    public static void releaseAllVideoTextures() {
        for (VideoTexture videoTexture : videoTextures.values()) {
            videoTexture.closeAndDestroy();
        }
        videoTextures.clear();
    }

    public static KahluaTable copyTable(KahluaTable from) {
        return copyTable(null, from);
    }

    public static KahluaTable copyTable(KahluaTable to, KahluaTable from) {
        if (to == null) {
            to = platform.newTable();
        } else {
            to.wipe();
        }
        if (from == null || from.isEmpty()) {
            return to;
        }
        KahluaTableIterator it = from.iterator();
        while (it.advance()) {
            Object key = it.getKey();
            Object value = it.getValue();
            if (value instanceof KahluaTable) {
                KahluaTable kahluaTable = (KahluaTable) value;
                to.rawset(key, copyTable(null, kahluaTable));
            } else {
                to.rawset(key, value);
            }
        }
        return to;
    }

    public static StackTraceElement[] insertAnyLuaTraceElements(StackTraceElement[] stackTraceElementsRaw) {
        for (int i = 0; i < PZArrayUtil.lengthOf(stackTraceElementsRaw); i++) {
            if (KahluaThread.isLuaMainloop(stackTraceElementsRaw[i])) {
                StackTraceElement[] stackTraceElementsBefore = (StackTraceElement[]) PZArrayUtil.sub(stackTraceElementsRaw, 0, i);
                StackTraceElement[] luaStackTrace = getLuaStackStrace(null);
                StackTraceElement[] stackTraceElementsAfter = (StackTraceElement[]) PZArrayUtil.sub(stackTraceElementsRaw, i, stackTraceElementsRaw.length);
                return (StackTraceElement[]) PZArrayUtil.concat(stackTraceElementsBefore, (StackTraceElement[]) PZArrayUtil.concat(luaStackTrace, stackTraceElementsAfter));
            }
        }
        return stackTraceElementsRaw;
    }

    public static LuaCallFrame[] getLuaStackTraceCallFrames(Coroutine coroutine) {
        if (coroutine == null) {
            KahluaThread thread2 = UIManager.getDefaultThread();
            if (thread2 != null) {
                coroutine = thread2.getCurrentCoroutine();
            } else if (thread != null) {
                coroutine = thread.getCurrentCoroutine();
            }
        }
        if (coroutine == null) {
            return new LuaCallFrame[0];
        }
        int top = coroutine.getCallframeTop();
        if (top <= 0) {
            return new LuaCallFrame[0];
        }
        int count = 0;
        LuaCallFrame[] result = new LuaCallFrame[top];
        for (int i = top - 1; i >= 0; i--) {
            LuaCallFrame callFrame = coroutine.getCallFrame(i);
            int i2 = count;
            count++;
            result[i2] = callFrame;
        }
        return result;
    }

    public static String[] getLuaStackTraceStrings(Coroutine coroutine) {
        LuaCallFrame[] callFrames = getLuaStackTraceCallFrames(coroutine);
        String[] rawResult = (String[]) PZArrayUtil.arrayConvert(new String[callFrames.length], callFrames, (v0) -> {
            return KahluaUtil.rawTostring2(v0);
        });
        return (String[]) PZArrayUtil.filtered(rawResult, (v0) -> {
            return Objects.nonNull(v0);
        });
    }

    public static StackTraceElement[] getLuaStackStrace(Coroutine coroutine) {
        LuaCallFrame[] callFrames = getLuaStackTraceCallFrames(coroutine);
        StackTraceElement[] elements = (StackTraceElement[]) PZArrayUtil.arrayConvert(new StackTraceElement[callFrames.length], callFrames, (v0) -> {
            return KahluaUtil.rawToStackTraceElement(v0);
        });
        return (StackTraceElement[]) PZArrayUtil.filtered(elements, (v0) -> {
            return Objects.nonNull(v0);
        });
    }

    /* JADX INFO: loaded from: projectzomboid.jar:zombie/Lua/LuaManager$Exposer.class */
    public static final class Exposer extends LuaJavaClassExposer {
        private final HashSet<Class<?>> exposed;

        public Exposer(KahluaConverterManager manager, Platform platform, KahluaTable environment) {
            super(manager, platform, environment);
            this.exposed = new LinkedHashSet();
        }

        public void exposeAll() {
            setExposed(BufferedReader.class);
            setExposed(BufferedWriter.class);
            setExposed(DataInputStream.class);
            setExposed(DataOutputStream.class);
            setExposed(Double.class);
            setExposed(Long.class);
            setExposed(Float.class);
            setExposed(Integer.class);
            setExposed(Math.class);
            setExposed(Void.class);
            setExposed(SimpleDateFormat.class);
            setExposed(ArrayList.class);
            setExposed(List.class);
            setExposed(EnumMap.class);
            setExposed(HashMap.class);
            setExposed(HashSet.class);
            setExposed(LinkedHashMap.class);
            setExposed(LinkedList.class);
            setExposed(Locale.class);
            setExposed(Stack.class);
            setExposed(Vector.class);
            setExposed(Iterator.class);
            setExposed(EmitterType.class);
            setExposed(FMODAudio.class);
            setExposed(FMODDebugEventPlayer.class);
            setExposed(FMODSoundBank.class);
            setExposed(FMODSoundEmitter.class);
            setExposed(FMODDebugEventPlayer.class);
            setExposed(Vector2f.class);
            setExposed(Vector3f.class);
            setExposed(Position3D.class);
            setExposed(KahluaUtil.class);
            setExposed(DummySoundBank.class);
            setExposed(DummySoundEmitter.class);
            setExposed(BaseSoundEmitter.class);
            setExposed(GameSound.class);
            setExposed(GameSoundClip.class);
            setExposed(MusicIntensityConfig.class);
            setExposed(MusicIntensityEvent.class);
            setExposed(MusicIntensityEvents.class);
            setExposed(MusicThreatConfig.class);
            setExposed(MusicThreatStatus.class);
            setExposed(MusicThreatStatuses.class);
            setExposed(AttackState.class);
            setExposed(BurntToDeath.class);
            setExposed(ClimbDownSheetRopeState.class);
            setExposed(ClimbOverFenceState.class);
            setExposed(ClimbOverWallState.class);
            setExposed(ClimbSheetRopeState.class);
            setExposed(ClimbThroughWindowState.class);
            setExposed(CloseWindowState.class);
            setExposed(CrawlingZombieTurnState.class);
            setExposed(FakeDeadAttackState.class);
            setExposed(FakeDeadZombieState.class);
            setExposed(FishingState.class);
            setExposed(FitnessState.class);
            setExposed(IdleState.class);
            setExposed(LungeState.class);
            setExposed(OpenWindowState.class);
            setExposed(PathFindState.class);
            setExposed(PlayerActionsState.class);
            setExposed(PlayerAimState.class);
            setExposed(PlayerEmoteState.class);
            setExposed(PlayerExtState.class);
            setExposed(PlayerFallDownState.class);
            setExposed(PlayerFallingState.class);
            setExposed(PlayerGetUpState.class);
            setExposed(PlayerHitReactionPVPState.class);
            setExposed(PlayerHitReactionState.class);
            setExposed(PlayerKnockedDown.class);
            setExposed(PlayerOnGroundState.class);
            setExposed(PlayerSitOnFurnitureState.class);
            setExposed(PlayerSitOnGroundState.class);
            setExposed(PlayerStrafeState.class);
            setExposed(SmashWindowState.class);
            setExposed(StaggerBackState.class);
            setExposed(SwipeStatePlayer.class);
            setExposed(ThumpState.class);
            setExposed(WalkTowardState.class);
            setExposed(ZombieFallDownState.class);
            setExposed(ZombieGetDownState.class);
            setExposed(ZombieGetUpState.class);
            setExposed(ZombieIdleState.class);
            setExposed(ZombieOnGroundState.class);
            setExposed(ZombieReanimateState.class);
            setExposed(ZombieSittingState.class);
            setExposed(PlayerMovementState.class);
            setExposed(PlayerMilkAnimalState.class);
            setExposed(PlayerPetAnimalState.class);
            setExposed(PlayerShearAnimalState.class);
            setExposed(GameCharacterAIBrain.class);
            setExposed(MapKnowledge.class);
            setExposed(Basements.class);
            setExposed(BasementsV1.class);
            setExposed(BodyPartType.class);
            setExposed(BodyPart.class);
            setExposed(BodyDamage.class);
            setExposed(Thermoregulator.class);
            setExposed(Thermoregulator.ThermalNode.class);
            setExposed(Metabolics.class);
            setExposed(Fitness.class);
            setExposed(GameKeyboard.class);
            setExposed(LuaTimedAction.class);
            setExposed(LuaTimedActionNew.class);
            setExposed(Moodle.class);
            setExposed(Moodles.class);
            setExposed(PerkFactory.class);
            setExposed(PerkFactory.Perk.class);
            setExposed(PerkFactory.Perks.class);
            setExposed(ObservationFactory.class);
            setExposed(ObservationFactory.Observation.class);
            setExposed(CharacterTraitDefinition.class);
            setExposed(CharacterProfessionDefinition.class);
            setExposed(IsoDummyCameraCharacter.class);
            setExposed(Stats.class);
            setExposed(CharacterStat.class);
            setExposed(SurvivorDesc.class);
            setExposed(SurvivorFactory.class);
            setExposed(SurvivorFactory.SurvivorType.class);
            setExposed(IsoGameCharacter.class);
            setExposed(AnimalPartsDefinitions.class);
            setExposed(IsoAnimal.class);
            setExposed(AnimalData.class);
            setExposed(AnimalBreed.class);
            setExposed(BaseAnimalBehavior.class);
            setExposed(AnimalAllele.class);
            setExposed(AnimalGene.class);
            setExposed(AnimalGenomeDefinitions.class);
            setExposed(AnimalDefinitions.class);
            setExposed(IsoGameCharacter.Location.class);
            setExposed(IsoGameCharacter.PerkInfo.class);
            setExposed(IsoGameCharacter.XP.class);
            setExposed(CharacterTraits.class);
            setExposed(IsoPlayer.class);
            setExposed(PlayerCraftHistory.class);
            setExposed(IsoSurvivor.class);
            setExposed(IsoZombie.class);
            setExposed(CharacterActionAnims.class);
            setExposed(HaloTextHelper.class);
            setExposed(HaloTextHelper.ColorRGB.class);
            setExposed(MoveDeltaModifiers.class);
            setExposed(BloodBodyPartType.class);
            setExposed(CombatConfig.class);
            setExposed(CombatConfigKey.class);
            setExposed(CombatConfigCategory.class);
            setExposed(Clipboard.class);
            setExposed(AngelCodeFont.class);
            setExposed(ZLogger.class);
            setExposed(IsoPropertyType.class);
            setExposed(IsoObjectChange.class);
            setExposed(PropertyContainer.class);
            setExposed(ClothingItem.class);
            setExposed(AnimatorDebugMonitor.class);
            setExposed(RuntimeAnimationScript.class);
            setExposed(ColorInfo.class);
            setExposed(NinePatchTexture.class);
            setExposed(Texture.class);
            setExposed(VideoTexture.class);
            setExposed(SteamFriend.class);
            setExposed(SteamUGCDetails.class);
            setExposed(SteamWorkshopItem.class);
            setExposed(Color.class);
            setExposed(Colors.class);
            setExposed(Colors.ColNfo.class);
            setExposed(Colors.ColorSet.class);
            setExposed(Core.class);
            setExposed(GameVersion.class);
            setExposed(ImmutableColor.class);
            setExposed(Language.class);
            setExposed(PerformanceSettings.class);
            setExposed(SpriteRenderer.class);
            setExposed(Translator.class);
            setExposed(GameMode.class);
            setExposed(PZMath.class);
            setExposed(DebugLog.class);
            setExposed(DebugOptions.class);
            setExposed(BooleanDebugOption.class);
            setExposed(DebugType.class);
            setExposed(LogSeverity.class);
            setExposed(ObjectDebuggerLua.class);
            setExposed(DebugCSVExport.class);
            setExposed(DebugCSVExportFirearms.class);
            setExposed(DebugCSVExportFluidContainers.class);
            setExposed(DebugCSVExportMoveableTiles.class);
            setExposed(Component.class);
            setExposed(ComponentType.class);
            setExposed(EntityBucket.class);
            setExposed(Family.class);
            setExposed(GameEntity.class);
            setExposed(GameEntityFactory.class);
            setExposed(GameEntityType.class);
            setExposed(MetaEntity.class);
            setExposed(Attribute.class);
            setExposed(AttributeContainer.class);
            setExposed(AttributeInstance.class);
            setExposed(AttributeInstance.Bool.class);
            setExposed(AttributeInstance.String.class);
            setExposed(AttributeInstance.Numeric.class);
            setExposed(AttributeInstance.Float.class);
            setExposed(AttributeInstance.Double.class);
            setExposed(AttributeInstance.Byte.class);
            setExposed(AttributeInstance.Short.class);
            setExposed(AttributeInstance.Int.class);
            setExposed(AttributeInstance.Long.class);
            setExposed(AttributeInstance.Enum.class);
            setExposed(AttributeInstance.EnumSet.class);
            setExposed(AttributeInstance.EnumStringSet.class);
            setExposed(AttributeType.class);
            setExposed(AttributeType.Bool.class);
            setExposed(AttributeType.String.class);
            setExposed(AttributeType.Numeric.class);
            setExposed(AttributeType.Float.class);
            setExposed(AttributeType.Double.class);
            setExposed(AttributeType.Byte.class);
            setExposed(AttributeType.Short.class);
            setExposed(AttributeType.Int.class);
            setExposed(AttributeType.Long.class);
            setExposed(AttributeType.Enum.class);
            setExposed(AttributeType.EnumSet.class);
            setExposed(AttributeType.EnumStringSet.class);
            setExposed(AttributeValueType.class);
            setExposed(AttributeUtil.class);
            setExposed(EnumStringObj.class);
            setExposed(ContextMenuConfig.class);
            setExposed(CraftRecipeData.class);
            setExposed(CraftRecipeData.CacheData.class);
            setExposed(CraftRecipeData.InputScriptData.class);
            setExposed(CraftRecipeData.OutputScriptData.class);
            setExposed(CraftRecipeManager.class);
            setExposed(CraftRecipeSort.class);
            setExposed(CraftRecipeListNode.class);
            setExposed(CraftRecipeListNode.CraftRecipeListNodeType.class);
            setExposed(CraftRecipeListNode.CraftRecipeListNodeExpandedState.class);
            setExposed(CraftRecipeListNodeCollection.class);
            setExposed(CraftRecipeGroup.class);
            setExposed(HandcraftLogic.class);
            setExposed(HandcraftLogic.CachedRecipeInfo.class);
            setExposed(InputItemNode.class);
            setExposed(ItemDataList.class);
            setExposed(OutputMapper.class);
            setExposed(CraftBench.class);
            setExposed(CraftLogic.class);
            setExposed(CraftLogicUILogic.class);
            setExposed(DryingCraftLogic.class);
            setExposed(FurnaceLogic.class);
            setExposed(CraftMode.class);
            setExposed(CraftRecipeMonitor.class);
            setExposed(CraftUtil.class);
            setExposed(FluidMatchMode.class);
            setExposed(InputFlag.class);
            setExposed(ItemApplyMode.class);
            setExposed(MashingLogic.class);
            setExposed(OutputFlag.class);
            setExposed(StartMode.class);
            setExposed(BaseCraftingLogic.class);
            setExposed(BuildLogic.class);
            setExposed(BaseCraftingLogic.CachedRecipeInfo.class);
            setExposed(CraftRecipeComponent.class);
            setExposed(CraftRecipeComponentScript.class);
            setExposed(ItemBodyLocation.class);
            setExposed(RecipeCodeOnCreate.class);
            setExposed(RecipeCodeOnTest.class);
            setExposed(RecipeCodeOnCooked.class);
            setExposed(RecipeCodeOnEat.class);
            setExposed(ItemCodeOnCreate.class);
            setExposed(ItemCodeOnTest.class);
            setExposed(WallCoveringConfigScript.class);
            setExposed(Fluid.class);
            setExposed(FluidType.class);
            setExposed(FluidCategory.class);
            setExposed(FluidFilter.class);
            setExposed(FluidFilter.FilterType.class);
            setExposed(FluidConsume.class);
            setExposed(FluidContainer.class);
            setExposed(FluidProperties.class);
            setExposed(FluidSample.class);
            setExposed(FluidUtil.class);
            setExposed(PoisonEffect.class);
            setExposed(PoisonInfo.class);
            setExposed(SealedFluidProperties.class);
            setExposed(LuaComponent.class);
            setExposed(Parts.class);
            setExposed(Resource.class);
            setExposed(ResourceBlueprint.class);
            setExposed(ResourceChannel.class);
            setExposed(ResourceEnergy.class);
            setExposed(ResourceFlag.class);
            setExposed(ResourceFluid.class);
            setExposed(ResourceIO.class);
            setExposed(ResourceItem.class);
            setExposed(Resources.class);
            setExposed(ResourceType.class);
            setExposed(EntityScriptInfo.class);
            setExposed(Signals.class);
            setExposed(CraftBenchSounds.class);
            setExposed(SpriteConfig.class);
            setExposed(SpriteOverlayConfig.class);
            setExposed(SpriteConfigManager.class);
            setExposed(SpriteConfigManager.FaceInfo.class);
            setExposed(SpriteConfigManager.ObjectInfo.class);
            setExposed(SpriteConfigManager.TileInfo.class);
            setExposed(TestComponent.class);
            setExposed(UiConfig.class);
            setExposed(Energy.class);
            setExposed(EnergyType.class);
            setExposed(ComponentEvent.class);
            setExposed(ComponentEventType.class);
            setExposed(EntityEvent.class);
            setExposed(EntityEventType.class);
            setExposed(MetaTagComponent.class);
            setExposed(EntityDebugTest.class);
            setExposed(EntityDebugTestType.class);
            setExposed(AssocArray.class);
            setExposed(AssocEnumArray.class);
            setExposed(Array.class);
            setExposed(BitSet.class);
            setExposed(GameEntityUtil.class);
            setExposed(ImmutableArray.class);
            setExposed(ErosionConfig.class);
            setExposed(ErosionConfig.Debug.class);
            setExposed(ErosionConfig.Season.class);
            setExposed(ErosionConfig.Seeds.class);
            setExposed(ErosionConfig.Time.class);
            setExposed(ErosionMain.class);
            setExposed(ErosionSeason.class);
            setExposed(AnimationViewerState.class);
            setExposed(AnimationViewerState.BooleanDebugOption.class);
            setExposed(AttachmentEditorState.class);
            setExposed(ChooseGameInfo.Mod.class);
            setExposed(DebugChunkState.class);
            setExposed(DebugChunkState.BooleanDebugOption.class);
            setExposed(DebugGlobalObjectState.class);
            setExposed(GameLoadingState.class);
            setExposed(LoadingQueueState.class);
            setExposed(MainScreenState.class);
            setExposed(SeamEditorState.class);
            setExposed(SeamEditorState.BooleanDebugOption.class);
            setExposed(SpriteModelEditorState.class);
            setExposed(SpriteModelEditorState.BooleanDebugOption.class);
            setExposed(TermsOfServiceState.class);
            setExposed(TileGeometryState.class);
            setExposed(TileGeometryState.BooleanDebugOption.class);
            setExposed(Gizmos.class);
            setExposed(RotateGizmo.class);
            setExposed(TransformMode.class);
            setExposed(TranslateGizmo.class);
            setExposed(CGlobalObject.class);
            setExposed(CGlobalObjects.class);
            setExposed(CGlobalObjectSystem.class);
            setExposed(SGlobalObject.class);
            setExposed(SGlobalObjects.class);
            setExposed(SGlobalObjectSystem.class);
            setExposed(Mouse.class);
            setExposed(RecipeMonitor.class);
            setExposed(AnimalInventoryItem.class);
            setExposed(AlarmClock.class);
            setExposed(AlarmClockClothing.class);
            setExposed(Clothing.class);
            setExposed(Clothing.ClothingPatch.class);
            setExposed(Clothing.ClothingPatchFabricType.class);
            setExposed(ComboItem.class);
            setExposed(Drainable.class);
            setExposed(DrainableComboItem.class);
            setExposed(Food.class);
            setExposed(HandWeapon.class);
            setExposed(InventoryContainer.class);
            setExposed(Key.class);
            setExposed(KeyRing.class);
            setExposed(Literature.class);
            setExposed(Moveable.class);
            setExposed(Radio.class);
            setExposed(WeaponPart.class);
            setExposed(ItemContainer.class);
            setExposed(ItemPickerJava.class);
            setExposed(ItemPickerJava.KeyNamer.class);
            setExposed(ItemSpawner.class);
            setExposed(InventoryItem.class);
            setExposed(FixingManager.class);
            setExposed(RecipeManager.class);
            setExposed(IsoRegions.class);
            setExposed(IsoRegionsLogger.class);
            setExposed(IsoRegionsLogger.IsoRegionLog.class);
            setExposed(IsoRegionLogType.class);
            setExposed(DataCell.class);
            setExposed(DataChunk.class);
            setExposed(IsoChunkRegion.class);
            setExposed(IsoWorldRegion.class);
            setExposed(IsoRegionsRenderer.class);
            setExposed(IsoRegionsRenderer.BooleanDebugOption.class);
            setExposed(IsoBuilding.class);
            setExposed(IsoRoom.class);
            setExposed(SafeHouse.class);
            setExposed(IsoButcherHook.class);
            setExposed(ISWorldObjectContextMenuLogic.class);
            setExposed(FBORenderTracerEffects.class);
            setExposed(FBORenderChunk.class);
            setExposed(BarricadeAble.class);
            setExposed(IsoBarbecue.class);
            setExposed(IsoBarricade.class);
            setExposed(IsoBrokenGlass.class);
            setExposed(IsoClothingDryer.class);
            setExposed(IsoClothingWasher.class);
            setExposed(IsoCombinationWasherDryer.class);
            setExposed(IsoStackedWasherDryer.class);
            setExposed(IsoCurtain.class);
            setExposed(IsoCarBatteryCharger.class);
            setExposed(IsoDeadBody.class);
            setExposed(IsoDoor.class);
            setExposed(IsoFire.class);
            setExposed(IsoFireManager.class);
            setExposed(IsoFireplace.class);
            setExposed(IsoFeedingTrough.class);
            setExposed(IsoHutch.class);
            setExposed(IsoHutch.NestBox.class);
            setExposed(IsoGenerator.class);
            setExposed(IsoJukebox.class);
            setExposed(IsoLightSwitch.class);
            setExposed(IsoMannequin.class);
            setExposed(IsoMolotovCocktail.class);
            setExposed(IsoWaveSignal.class);
            setExposed(IsoRadio.class);
            setExposed(IsoTelevision.class);
            setExposed(IsoStackedWasherDryer.class);
            setExposed(IsoStove.class);
            setExposed(IsoThumpable.class);
            setExposed(IsoTrap.class);
            setExposed(IsoTree.class);
            setExposed(IsoWheelieBin.class);
            setExposed(IsoWindow.class);
            setExposed(IsoWindowFrame.class);
            setExposed(IsoWorldInventoryObject.class);
            setExposed(IsoZombieGiblets.class);
            setExposed(RainManager.class);
            setExposed(ObjectRenderEffects.class);
            setExposed(HumanVisual.class);
            setExposed(AnimalVisual.class);
            setExposed(ItemVisual.class);
            setExposed(ItemVisuals.class);
            setExposed(IsoSprite.class);
            setExposed(IsoSpriteInstance.class);
            setExposed(IsoSpriteManager.class);
            setExposed(IsoSpriteGrid.class);
            setExposed(IsoFlagType.class);
            setExposed(IsoObjectType.class);
            setExposed(ClimateManager.class);
            setExposed(ClimateManager.DayInfo.class);
            setExposed(ClimateManager.ClimateFloat.class);
            setExposed(ClimateManager.ClimateColor.class);
            setExposed(ClimateManager.ClimateBool.class);
            setExposed(WeatherPeriod.class);
            setExposed(WeatherPeriod.WeatherStage.class);
            setExposed(WeatherPeriod.StrLerpVal.class);
            setExposed(ClimateManager.AirFront.class);
            setExposed(ThunderStorm.class);
            setExposed(ThunderStorm.ThunderCloud.class);
            setExposed(IsoWeatherFX.class);
            setExposed(Temperature.class);
            setExposed(ClimateColorInfo.class);
            setExposed(ClimateValues.class);
            setExposed(ClimateForecaster.class);
            setExposed(ClimateForecaster.DayForecast.class);
            setExposed(ClimateForecaster.ForecastValue.class);
            setExposed(ClimateHistory.class);
            setExposed(WorldFlares.class);
            setExposed(WorldFlares.Flare.class);
            setExposed(ImprovedFog.class);
            setExposed(ClimateMoon.class);
            setExposed(IsoPuddles.class);
            setExposed(IsoPuddles.PuddlesFloat.class);
            setExposed(BentFences.class);
            setExposed(BrokenFences.class);
            setExposed(ContainerOverlays.class);
            setExposed(IsoChunk.class);
            setExposed(BuildingDef.class);
            setExposed(IsoCamera.class);
            setExposed(IsoCell.class);
            setExposed(IsoChunkMap.class);
            setExposed(IsoDirections.class);
            setExposed(IsoDirectionSet.class);
            setExposed(IsoGridSquare.class);
            setExposed(IsoHeatSource.class);
            setExposed(IsoLightSource.class);
            setExposed(IsoLot.class);
            setExposed(IsoLuaMover.class);
            setExposed(IsoMetaChunk.class);
            setExposed(IsoMetaCell.class);
            setExposed(IsoMetaGrid.class);
            setExposed(Trigger.class);
            setExposed(VehicleZone.class);
            setExposed(Zone.class);
            setExposed(IsoMovingObject.class);
            setExposed(IsoObject.class);
            setExposed(IsoObjectPicker.class);
            setExposed(IsoPushableObject.class);
            setExposed(IsoUtils.class);
            setExposed(IsoWorld.class);
            setExposed(LosUtil.class);
            setExposed(MetaObject.class);
            setExposed(RoomDef.class);
            setExposed(RoomDef.RoomRect.class);
            setExposed(SpriteModel.class);
            setExposed(SliceY.class);
            setExposed(TileOverlays.class);
            setExposed(Vector2.class);
            setExposed(Vector3.class);
            setExposed(WorldMarkers.class);
            setExposed(WorldMarkers.DirectionArrow.class);
            setExposed(WorldMarkers.GridSquareMarker.class);
            setExposed(WorldMarkers.PlayerHomingPoint.class);
            setExposed(SearchMode.class);
            setExposed(SearchMode.PlayerSearchMode.class);
            setExposed(SearchMode.SearchModeFloat.class);
            setExposed(IsoMarkers.class);
            setExposed(IsoMarkers.IsoMarker.class);
            setExposed(FishSchoolManager.class);
            setExposed(WorldGenUtils.class);
            setExposed(WorldGenParams.class);
            setExposed(LuaEventManager.class);
            setExposed(MapObjects.class);
            setExposed(ActiveMods.class);
            setExposed(PVPLogTool.class);
            setExposed(PVPLogTool.PVPEvent.class);
            setExposed(NetworkAIParams.class);
            setExposed(Server.class);
            setExposed(Account.class);
            setExposed(ServerOptions.class);
            setExposed(ServerOptions.BooleanServerOption.class);
            setExposed(ServerOptions.DoubleServerOption.class);
            setExposed(ServerOptions.IntegerServerOption.class);
            setExposed(ServerOptions.StringServerOption.class);
            setExposed(ServerOptions.TextServerOption.class);
            setExposed(ServerOptions.EnumServerOption.class);
            setExposed(ServerSettings.class);
            setExposed(ServerSettingsManager.class);
            setExposed(ContainerID.class);
            setExposed(ContainerID.ContainerType.class);
            setExposed(WarManager.class);
            setExposed(WarManager.War.class);
            setExposed(WarManager.State.class);
            setExposed(ZombiePopulationRenderer.class);
            setExposed(ZombiePopulationRenderer.BooleanDebugOption.class);
            setExposed(RadioAPI.class);
            setExposed(DeviceData.class);
            setExposed(DevicePresets.class);
            setExposed(PresetEntry.class);
            setExposed(WaveSignalDevice.class);
            setExposed(ZomboidRadio.class);
            setExposed(RadioData.class);
            setExposed(RadioScriptManager.class);
            setExposed(RadioChannel.class);
            setExposed(DynamicRadioChannel.class);
            setExposed(RadioBroadCast.class);
            setExposed(RadioLine.class);
            setExposed(RadioScript.class);
            setExposed(RadioScript.ExitOption.class);
            setExposed(ChannelCategory.class);
            setExposed(SLSoundManager.class);
            setExposed(StorySound.class);
            setExposed(StorySoundEvent.class);
            setExposed(EventSound.class);
            setExposed(DataPoint.class);
            setExposed(RecordedMedia.class);
            setExposed(MediaData.class);
            setExposed(MediaData.MediaLineData.class);
            setExposed(ActionSoundTime.class);
            setExposed(AmmoType.class);
            setExposed(CharacterTrait.class);
            setExposed(CharacterProfession.class);
            setExposed(ItemTag.class);
            setExposed(MoodleType.class);
            setExposed(Registry.class);
            setExposed(Registries.class);
            setExposed(ResourceLocation.class);
            setExposed(Newspaper.class);
            setExposed(Brochure.class);
            setExposed(Flier.class);
            setExposed(GameEntityScript.class);
            setExposed(GameEntityTemplate.class);
            setExposed(ComponentScript.class);
            setExposed(AttributesScript.class);
            setExposed(ContextMenuConfigScript.class);
            setExposed(ContextMenuConfigScript.EntryScript.class);
            setExposed(CraftBenchScript.class);
            setExposed(CraftLogicScript.class);
            setExposed(CraftRecipe.class);
            setExposed(CraftRecipe.RequiredSkill.class);
            setExposed(InputScript.class);
            setExposed(MashingLogicScript.class);
            setExposed(FurnaceLogicScript.class);
            setExposed(OutputScript.class);
            setExposed(FluidContainerScript.class);
            setExposed(FluidContainerScript.FluidScript.class);
            setExposed(LuaComponentScript.class);
            setExposed(PartsScript.class);
            setExposed(SignalsScript.class);
            setExposed(SpriteConfigScript.class);
            setExposed(SpriteConfigScript.FaceScript.class);
            setExposed(SpriteConfigScript.TileScript.class);
            setExposed(SpriteConfigScript.XRow.class);
            setExposed(SpriteConfigScript.ZLayer.class);
            setExposed(TestComponentScript.class);
            setExposed(UiConfigScript.class);
            setExposed(ItemConfig.class);
            setExposed(AnimationsMesh.class);
            setExposed(BaseScriptObject.class);
            setExposed(EnergyDefinitionScript.class);
            setExposed(EvolvedRecipe.class);
            setExposed(Fixing.class);
            setExposed(Fixing.Fixer.class);
            setExposed(Fixing.FixerSkill.class);
            setExposed(FluidDefinitionScript.class);
            setExposed(FluidFilterScript.class);
            setExposed(GameSoundScript.class);
            setExposed(Item.class);
            setExposed(ItemType.class);
            setExposed(ItemRecipe.class);
            setExposed(ItemFilterScript.class);
            setExposed(MannequinScript.class);
            setExposed(ModelAttachment.class);
            setExposed(ModelScript.class);
            setExposed(MovableRecipe.class);
            setExposed(PhysicsShapeScript.class);
            setExposed(Recipe.class);
            setExposed(Recipe.RequiredSkill.class);
            setExposed(Recipe.Result.class);
            setExposed(Recipe.Source.class);
            setExposed(ScriptModule.class);
            setExposed(SoundTimelineScript.class);
            setExposed(StringListScript.class);
            setExposed(TimedActionScript.class);
            setExposed(UniqueRecipe.class);
            setExposed(VehiclePartModel.class);
            setExposed(VehicleScript.class);
            setExposed(VehicleScript.Area.class);
            setExposed(VehicleScript.Model.class);
            setExposed(VehicleScript.Part.class);
            setExposed(VehicleScript.Passenger.class);
            setExposed(VehicleScript.PhysicsShape.class);
            setExposed(VehicleScript.Position.class);
            setExposed(VehicleScript.Wheel.class);
            setExposed(VehicleTemplate.class);
            setExposed(WeaponCategory.class);
            setExposed(WallCoveringType.class);
            setExposed(XuiColorsScript.class);
            setExposed(XuiConfigScript.class);
            setExposed(XuiLayoutScript.class);
            setExposed(XuiSkinScript.class);
            setExposed(VectorPosAlign.class);
            setExposed(TextAlign.class);
            setExposed(XuiAutoApply.class);
            setExposed(XuiLuaStyle.class);
            setExposed(XuiLuaStyle.XuiVar.class);
            setExposed(XuiLuaStyle.XuiBoolean.class);
            setExposed(XuiLuaStyle.XuiColor.class);
            setExposed(XuiLuaStyle.XuiDouble.class);
            setExposed(XuiLuaStyle.XuiFontType.class);
            setExposed(XuiLuaStyle.XuiString.class);
            setExposed(XuiLuaStyle.XuiStringList.class);
            setExposed(XuiLuaStyle.XuiTexture.class);
            setExposed(XuiLuaStyle.XuiTranslateString.class);
            setExposed(XuiManager.class);
            setExposed(XuiReference.class);
            setExposed(XuiScript.class);
            setExposed(XuiTableScript.class);
            setExposed(XuiTableScript.XuiTableColumnScript.class);
            setExposed(XuiTableScript.XuiTableRowScript.class);
            setExposed(XuiTableScript.XuiTableCellScript.class);
            setExposed(XuiScript.XuiVar.class);
            setExposed(XuiScript.XuiBoolean.class);
            setExposed(XuiScript.XuiColor.class);
            setExposed(XuiScript.XuiDouble.class);
            setExposed(XuiScript.XuiFloat.class);
            setExposed(XuiScript.XuiFontType.class);
            setExposed(XuiScript.XuiFunction.class);
            setExposed(XuiScript.XuiInteger.class);
            setExposed(XuiScript.XuiSpacing.class);
            setExposed(XuiScript.XuiString.class);
            setExposed(XuiScript.XuiStringList.class);
            setExposed(XuiScript.XuiTexture.class);
            setExposed(XuiScript.XuiTextAlign.class);
            setExposed(XuiScript.XuiTranslateString.class);
            setExposed(XuiScript.XuiUnit.class);
            setExposed(XuiScript.XuiVector.class);
            setExposed(XuiScript.XuiVectorPosAlign.class);
            setExposed(XuiScriptType.class);
            setExposed(XuiSkin.class);
            setExposed(XuiSkin.EntityUiStyle.class);
            setExposed(XuiSkin.ComponentUiStyle.class);
            setExposed(XuiVarType.class);
            setExposed(ScriptManager.class);
            setExposed(ScriptType.class);
            setExposed(SeamManager.class);
            setExposed(SeatingManager.class);
            setExposed(SpriteModelManager.class);
            setExposed(TemplateText.class);
            setExposed(ReplaceProviderCharacter.class);
            setExposed(TileDepthTexture.class);
            setExposed(TileDepthTextureAssignmentManager.class);
            setExposed(TileDepthTextureManager.class);
            setExposed(TileDepthTextures.class);
            setExposed(TileGeometryManager.class);
            setExposed(TilesetDepthTexture.class);
            setExposed(ActionProgressBar.class);
            setExposed(Clock.class);
            setExposed(UIDebugConsole.class);
            setExposed(ModalDialog.class);
            setExposed(MoodlesUI.class);
            setExposed(NewHealthPanel.class);
            setExposed(ObjectTooltip.class);
            setExposed(ObjectTooltip.Layout.class);
            setExposed(ObjectTooltip.LayoutItem.class);
            setExposed(RadarPanel.class);
            setExposed(RadialMenu.class);
            setExposed(RadialProgressBar.class);
            setExposed(SpeedControls.class);
            setExposed(TextManager.class);
            setExposed(UI3DModel.class);
            setExposed(UIElement.class);
            setExposed(AtomUI.class);
            setExposed(AtomUIText.class);
            setExposed(AtomUITexture.class);
            setExposed(AtomUITextEntry.class);
            setExposed(AtomUIMap.class);
            setExposed(UIFont.class);
            setExposed(UITransition.class);
            setExposed(UIManager.class);
            setExposed(UITextBox2.class);
            setExposed(VehicleGauge.class);
            setExposed(TextDrawObject.class);
            setExposed(PZArrayList.class);
            setExposed(PZUnmodifiableList.class);
            setExposed(PZCalendar.class);
            setExposed(BaseVehicle.class);
            setExposed(EditVehicleState.class);
            setExposed(PathFindBehavior2.BehaviorResult.class);
            setExposed(PathFindBehavior2.class);
            setExposed(UI3DScene.class);
            setExposed(VehicleDoor.class);
            setExposed(VehicleEngineRPM.class);
            setExposed(VehicleLight.class);
            setExposed(VehiclePart.class);
            setExposed(VehicleType.class);
            setExposed(VehicleWindow.class);
            setExposed(AttachedItem.class);
            setExposed(AttachedItems.class);
            setExposed(AttachedLocation.class);
            setExposed(AttachedLocationGroup.class);
            setExposed(AttachedLocations.class);
            setExposed(WornItems.class);
            setExposed(WornItem.class);
            setExposed(BodyLocation.class);
            setExposed(BodyLocationGroup.class);
            setExposed(BodyLocations.class);
            setExposed(Role.class);
            setExposed(Capability.class);
            setExposed(NetworkUser.class);
            setExposed(CheatType.class);
            setExposed(DummySoundManager.class);
            setExposed(GameSounds.class);
            setExposed(GameTime.class);
            setExposed(GameWindow.class);
            setExposed(SandboxOptions.class);
            setExposed(SandboxOptions.BooleanSandboxOption.class);
            setExposed(SandboxOptions.DoubleSandboxOption.class);
            setExposed(SandboxOptions.StringSandboxOption.class);
            setExposed(SandboxOptions.EnumSandboxOption.class);
            setExposed(SandboxOptions.IntegerSandboxOption.class);
            setExposed(SoundManager.class);
            setExposed(SystemDisabler.class);
            setExposed(VirtualZombieManager.class);
            setExposed(WorldSoundManager.class);
            setExposed(WorldSoundManager.WorldSound.class);
            setExposed(DummyCharacterSoundEmitter.class);
            setExposed(CharacterSoundEmitter.class);
            setExposed(SoundManager.AmbientSoundEffect.class);
            setExposed(BaseAmbientStreamManager.class);
            setExposed(AmbientStreamManager.class);
            setExposed(Nutrition.class);
            setExposed(SleepingEvent.class);
            setExposed(IsoCompost.class);
            setExposed(Userlog.class);
            setExposed(Userlog.UserlogType.class);
            setExposed(ConfigOption.class);
            setExposed(BooleanConfigOption.class);
            setExposed(DoubleConfigOption.class);
            setExposed(EnumConfigOption.class);
            setExposed(IntegerConfigOption.class);
            setExposed(StringConfigOption.class);
            setExposed(Faction.class);
            setExposed(GlobalObject.LuaFileWriter.class);
            setExposed(Keyboard.class);
            setExposed(DBResult.class);
            setExposed(NonPvpZone.class);
            setExposed(DesignationZoneAnimal.class);
            setExposed(AnimalTracks.class);
            setExposed(IsoAnimalTrack.class);
            setExposed(AnimalChunk.class);
            setExposed(VirtualAnimal.class);
            setExposed(DesignationZone.class);
            setExposed(DBTicket.class);
            setExposed(DBBannedIP.class);
            setExposed(DBBannedSteamID.class);
            setExposed(StashSystem.class);
            setExposed(StashBuilding.class);
            setExposed(Stash.class);
            setExposed(RandomizedWorldBase.class);
            setExposed(RandomizedBuildingBase.class);
            setExposed(RBBurntFireman.class);
            setExposed(RBBasic.class);
            setExposed(RBBurnt.class);
            setExposed(RBOther.class);
            setExposed(RBStripclub.class);
            setExposed(RBSchool.class);
            setExposed(RBSpiffo.class);
            setExposed(RBPizzaWhirled.class);
            setExposed(RBOffice.class);
            setExposed(RBHairSalon.class);
            setExposed(RBClinic.class);
            setExposed(RBPileOCrepe.class);
            setExposed(RBCafe.class);
            setExposed(RBBar.class);
            setExposed(RBLooted.class);
            setExposed(RBSafehouse.class);
            setExposed(RBBurntCorpse.class);
            setExposed(RBShopLooted.class);
            setExposed(RBKateAndBaldspot.class);
            setExposed(RBGunstoreSiege.class);
            setExposed(RBPoliceSiege.class);
            setExposed(RBHeatBreakAfternoon.class);
            setExposed(RBTrashed.class);
            setExposed(RBBarn.class);
            setExposed(RBDorm.class);
            setExposed(RBNolans.class);
            setExposed(RBJackieJaye.class);
            setExposed(RBReverend.class);
            setExposed(RBTwiggy.class);
            setExposed(RBWoodcraft.class);
            setExposed(RBJoanHartford.class);
            setExposed(RBJudge.class);
            setExposed(RBMayorWestPoint.class);
            setExposed(RandomizedDeadSurvivorBase.class);
            setExposed(RDSZombiesEating.class);
            setExposed(RDSBleach.class);
            setExposed(RDSDeadDrunk.class);
            setExposed(RDSGunmanInBathroom.class);
            setExposed(RDSGunslinger.class);
            setExposed(RDSZombieLockedBathroom.class);
            setExposed(RDSBanditRaid.class);
            setExposed(RDSBandPractice.class);
            setExposed(RDSBathroomZed.class);
            setExposed(RDSBedroomZed.class);
            setExposed(RDSFootballNight.class);
            setExposed(RDSHenDo.class);
            setExposed(RDSStagDo.class);
            setExposed(RDSStudentNight.class);
            setExposed(RDSPokerNight.class);
            setExposed(RDSSuicidePact.class);
            setExposed(RDSPrisonEscape.class);
            setExposed(RDSPrisonEscapeWithPolice.class);
            setExposed(RDSSkeletonPsycho.class);
            setExposed(RDSCorpsePsycho.class);
            setExposed(RDSSpecificProfession.class);
            setExposed(RDSPoliceAtHouse.class);
            setExposed(RDSHouseParty.class);
            setExposed(RDSTinFoilHat.class);
            setExposed(RDSHockeyPsycho.class);
            setExposed(RDSDevouredByRats.class);
            setExposed(RDSRPGNight.class);
            setExposed(RDSRatInfested.class);
            setExposed(RDSRatKing.class);
            setExposed(RDSRatWar.class);
            setExposed(RDSResourceGarage.class);
            setExposed(RDSGrouchos.class);
            setExposed(RandomizedVehicleStoryBase.class);
            setExposed(RVSCarCrash.class);
            setExposed(RVSBanditRoad.class);
            setExposed(RVSAmbulanceCrash.class);
            setExposed(RVSCrashHorde.class);
            setExposed(RVSCarCrashCorpse.class);
            setExposed(RVSPoliceBlockade.class);
            setExposed(RVSPoliceBlockadeShooting.class);
            setExposed(RVSBurntCar.class);
            setExposed(RVSConstructionSite.class);
            setExposed(RVSUtilityVehicle.class);
            setExposed(RVSChangingTire.class);
            setExposed(RVSFlippedCrash.class);
            setExposed(RVSTrailerCrash.class);
            setExposed(RVSCarCrashDeer.class);
            setExposed(RVSDeadEnd.class);
            setExposed(RVSRegionalProfessionVehicle.class);
            setExposed(RVSRoadKill.class);
            setExposed(RVSRoadKillSmall.class);
            setExposed(RVSAnimalOnRoad.class);
            setExposed(RVSHerdOnRoad.class);
            setExposed(RVSAnimalTrailerOnRoad.class);
            setExposed(RVSRichJerk.class);
            setExposed(RVSPlonkies.class);
            setExposed(RandomizedZoneStoryBase.class);
            setExposed(RZSForestCamp.class);
            setExposed(RZSForestCampEaten.class);
            setExposed(RZSBuryingCamp.class);
            setExposed(RZSBeachParty.class);
            setExposed(RZSFishingTrip.class);
            setExposed(RZSBBQParty.class);
            setExposed(RZSHunterCamp.class);
            setExposed(RZSSexyTime.class);
            setExposed(RZSTrapperCamp.class);
            setExposed(RZSBaseball.class);
            setExposed(RZSMusicFestStage.class);
            setExposed(RZSMusicFest.class);
            setExposed(RZSBurntWreck.class);
            setExposed(RZSHermitCamp.class);
            setExposed(RZSHillbillyHoedown.class);
            setExposed(RZSHogWild.class);
            setExposed(RZSRockerParty.class);
            setExposed(RZSSadCamp.class);
            setExposed(RZSSurvivalistCamp.class);
            setExposed(RZSVanCamp.class);
            setExposed(RZSEscapedAnimal.class);
            setExposed(RZSEscapedHerd.class);
            setExposed(RZSAttachedAnimal.class);
            setExposed(RZSOrphanedFawn.class);
            setExposed(RZSNastyMattress.class);
            setExposed(RZSWasteDump.class);
            setExposed(RZSMurderScene.class);
            setExposed(RZSTragicPicnic.class);
            setExposed(RZSRangerSmith.class);
            setExposed(RZSOccultActivity.class);
            setExposed(RZSWaterPump.class);
            setExposed(RZSOldFirepit.class);
            setExposed(RZSOldShelter.class);
            setExposed(RZSCampsite.class);
            setExposed(RZSCharcoalBurner.class);
            setExposed(RZSDean.class);
            setExposed(RZSDuke.class);
            setExposed(RZSFrankHemingway.class);
            setExposed(RZSKirstyKormick.class);
            setExposed(RZSSirTwiggy.class);
            setExposed(RZJackieJaye.class);
            setExposed(MapGroups.class);
            setExposed(BeardStyles.class);
            setExposed(BeardStyle.class);
            setExposed(HairStyles.class);
            setExposed(HairStyle.class);
            setExposed(VoiceStyles.class);
            setExposed(VoiceStyle.class);
            setExposed(BloodClothingType.class);
            setExposed(WeaponType.class);
            setExposed(WeaponReloadType.class);
            setExposed(IsoWaterGeometry.class);
            setExposed(ModData.class);
            setExposed(WorldMarkers.class);
            setExposed(SyncPlayerStatsPacket.class);
            setExposed(BodyPartSyncPacket.class);
            setExposed(ChatMessage.class);
            setExposed(ChatBase.class);
            setExposed(ServerChatMessage.class);
            setExposed(Safety.class);
            setExposed(NetTimedAction.class);
            setExposed(NetTimedActionPacket.class);
            if (Core.debug) {
                setExposed(Field.class);
                setExposed(Method.class);
                setExposed(Coroutine.class);
            }
            BuildingRoomsEditor.setExposed(this);
            UIWorldMap.setExposed(this);
            if (Core.debug) {
                try {
                    exposeMethod(Class.class, Class.class.getMethod("getName", new Class[0]), LuaManager.env);
                    exposeMethod(Class.class, Class.class.getMethod("getSimpleName", new Class[0]), LuaManager.env);
                } catch (NoSuchMethodException ex) {
                    DebugType.General.printException(ex, LogSeverity.Error);
                }
            }
            setExposed(ItemKey.class);
            setExposed(ItemKey.AlarmClock.class);
            setExposed(ItemKey.AlarmClockClothing.class);
            setExposed(ItemKey.Animal.class);
            setExposed(ItemKey.Clothing.class);
            setExposed(ItemKey.Container.class);
            setExposed(ItemKey.Drainable.class);
            setExposed(ItemKey.Food.class);
            setExposed(ItemKey.Key.class);
            setExposed(ItemKey.Literature.class);
            setExposed(ItemKey.Map.class);
            setExposed(ItemKey.Moveable.class);
            setExposed(ItemKey.Normal.class);
            setExposed(ItemKey.Radio.class);
            setExposed(ItemKey.Weapon.class);
            setExposed(ItemKey.WeaponPart.class);
            setExposed(ModelKey.class);
            setExposed(DigType.class);
            setExposed(LuaSQLite.class);
            for (Class<?> clazz : this.exposed) {
                exposeLikeJavaRecursively(clazz, LuaManager.env);
            }
            exposeGlobalFunctions(new GlobalObject());
            LuaManager.exposeKeyboardKeys(LuaManager.env);
            LuaManager.exposeMouseButtons(LuaManager.env);
            LuaManager.exposeLuaCalendar();
        }

        public void setExposed(Class<?> clazz) {
            this.exposed.add(clazz);
        }

        @Override // se.krka.kahlua.integration.expose.LuaJavaClassExposer
        public boolean shouldExpose(Class<?> clazz) {
            if (clazz == null) {
                return false;
            }
            return this.exposed.contains(clazz);
        }
    }

    /* JADX INFO: loaded from: projectzomboid.jar:zombie/Lua/LuaManager$GlobalObject.class */
    public static class GlobalObject {
        private static FileOutputStream outStream;
        private static FileInputStream inStream;
        private static FileReader inFileReader;
        private static BufferedReader inBufferedReader;
        private static long timeLastRefresh;
        private static final TimSortComparator timSortComparator;
        static final /* synthetic */ boolean $assertionsDisabled;

        static {
            $assertionsDisabled = !LuaManager.class.desiredAssertionStatus();
            timSortComparator = new TimSortComparator();
        }

        @LuaMethod(name = "loadVehicleModel", global = true)
        public static Model loadVehicleModel(String name, String loc, String tex) {
            return loadZomboidModel(name, loc, tex, "vehicle", true);
        }

        @LuaMethod(name = "loadStaticZomboidModel", global = true)
        public static Model loadStaticZomboidModel(String name, String loc, String tex) {
            return loadZomboidModel(name, loc, tex, null, true);
        }

        @LuaMethod(name = "loadSkinnedZomboidModel", global = true)
        public static Model loadSkinnedZomboidModel(String name, String loc, String tex) {
            return loadZomboidModel(name, loc, tex, null, false);
        }

        @LuaMethod(name = "loadZomboidModel", global = true)
        public static Model loadZomboidModel(String name, String mesh, String tex, String shader, boolean bStatic) {
            try {
                if (mesh.startsWith("/")) {
                    mesh = mesh.substring(1);
                }
                if (tex.startsWith("/")) {
                    tex = tex.substring(1);
                }
                if (StringUtils.isNullOrWhitespace(shader)) {
                    shader = ModelScript.DEFAULT_SHADER_NAME;
                }
                if ("vehicle".equals(shader) && (!Core.getInstance().getPerfReflectionsOnLoad() || Core.getInstance().getUseOpenGL21())) {
                    shader = shader + "_noreflect";
                }
                Model model = ModelManager.instance.tryGetLoadedModel(mesh, tex, bStatic, shader, false);
                if (model != null) {
                    return model;
                }
                ModelManager.instance.setModelMetaData(name, mesh, tex, shader, bStatic);
                Model.ModelAssetParams assetParams = new Model.ModelAssetParams();
                assetParams.isStatic = bStatic;
                assetParams.meshName = mesh;
                assetParams.shaderName = shader;
                assetParams.textureName = tex;
                assetParams.textureFlags = ModelManager.instance.getTextureFlags();
                Model model2 = (Model) ModelAssetManager.instance.load(new AssetPath(name), assetParams);
                if (model2 != null) {
                    ModelManager.instance.putLoadedModel(mesh, tex, bStatic, shader, model2);
                }
                return model2;
            } catch (Exception e) {
                DebugType.General.printException(e, LogSeverity.Error, "LuaManager.loadZomboidModel> Exception thrown loading model: " + name + " mesh:" + mesh + " tex:" + tex + " shader:" + shader + " isStatic:" + bStatic, new Object[0]);
                return null;
            }
        }

        @LuaMethod(name = "setModelMetaData", global = true)
        public static void setModelMetaData(String name, String mesh, String tex, String shader, boolean bStatic) {
            if (mesh.startsWith("/")) {
                mesh = mesh.substring(1);
            }
            if (tex.startsWith("/")) {
                tex = tex.substring(1);
            }
            ModelManager.instance.setModelMetaData(name, mesh, tex, shader, bStatic);
        }

        @LuaMethod(name = "reloadModelsMatching", global = true)
        public static void reloadModelsMatching(String meshName) {
            ModelManager.instance.reloadModelsMatching(meshName);
        }

        @LuaMethod(name = "getSLSoundManager", global = true)
        public static SLSoundManager getSLSoundManager() {
            return null;
        }

        @LuaMethod(name = "getRadioAPI", global = true)
        public static RadioAPI getRadioAPI() {
            if (RadioAPI.hasInstance()) {
                return RadioAPI.getInstance();
            }
            return null;
        }

        @LuaMethod(name = "getRadioTranslators", global = true)
        public static ArrayList<String> getRadioTranslators(Language language) {
            return RadioData.getTranslatorNames(language);
        }

        @LuaMethod(name = "getTranslatorCredits", global = true)
        public static ArrayList<String> getTranslatorCredits(Language language) {
            File file = new File(ZomboidFileSystem.instance.getString("media/lua/shared/Translate/" + language.name() + "/credits.txt"));
            try {
                FileReader fr = new FileReader(file);
                try {
                    BufferedReader br = new BufferedReader(fr);
                    try {
                        ArrayList<String> result = new ArrayList<>();
                        while (true) {
                            String line = br.readLine();
                            if (line != null) {
                                if (!StringUtils.isNullOrWhitespace(line)) {
                                    result.add(line.trim());
                                }
                            } else {
                                br.close();
                                fr.close();
                                return result;
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            br.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    try {
                        fr.close();
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                    }
                    throw th3;
                }
            } catch (FileNotFoundException e) {
                return null;
            } catch (Exception ex) {
                ExceptionLogger.logException(ex);
                return null;
            }
        }

        @LuaMethod(name = "getBehaviourDebugPlayer", global = true)
        public static IsoGameCharacter getBehaviourDebugPlayer() {
            return null;
        }

        @LuaMethod(name = "setBehaviorStep", global = true)
        public static void setBehaviorStep(boolean b) {
        }

        @LuaMethod(name = "getPuddlesManager", global = true)
        public static IsoPuddles getPuddlesManager() {
            return IsoPuddles.getInstance();
        }

        @LuaMethod(name = "getAllAnimalsDefinitions", global = true)
        public static ArrayList<AnimalDefinitions> getAllAnimalsDefinitions() {
            return AnimalDefinitions.getAnimalDefsArray();
        }

        @LuaMethod(name = "setPuddles", global = true)
        public static void setPuddles(float initialPuddles) {
            IsoPuddles.PuddlesFloat pfloat = IsoPuddles.getInstance().getPuddlesFloat(3);
            pfloat.setEnableAdmin(true);
            pfloat.setAdminValue(initialPuddles);
            IsoPuddles.PuddlesFloat pfloat2 = IsoPuddles.getInstance().getPuddlesFloat(1);
            pfloat2.setEnableAdmin(true);
            pfloat2.setAdminValue(PZMath.clamp_01(initialPuddles * 1.2f));
        }

        @LuaMethod(name = "fastfloor", global = true)
        public static float fastfloor(float coord) {
            return PZMath.fastfloor(coord);
        }

        @LuaMethod(name = "getZomboidRadio", global = true)
        public static ZomboidRadio getZomboidRadio() {
            if (ZomboidRadio.hasInstance()) {
                return ZomboidRadio.getInstance();
            }
            return null;
        }

        @LuaMethod(name = "getRandomUUID", global = true)
        public static String getRandomUUID() {
            return ModUtilsJava.getRandomUUID();
        }

        @LuaMethod(name = "sendItemListNet", global = true)
        public static boolean sendItemListNet(IsoPlayer sender, ArrayList<InventoryItem> items, IsoPlayer receiver, String transferID, String custom) {
            return ModUtilsJava.sendItemListNet(sender, items, receiver, transferID, custom);
        }

        @LuaMethod(name = "convertToPZNetTable", global = true)
        public static KahluaTable convertToPZNetTable(KahluaTable table) {
            KahluaTable netTable = new PZNetKahluaTableImpl(new LinkedHashMap());
            KahluaTableIterator it = table.iterator();
            while (it.advance()) {
                netTable.rawset(it.getKey(), it.getValue());
            }
            return netTable;
        }

        @LuaMethod(name = "instanceof", global = true)
        public static boolean instof(Object obj, String name) {
            if (obj == null) {
                return false;
            }
            if (LuaManager.exposer.typeMap.containsKey(name)) {
                return LuaManager.exposer.typeMap.get(name).isInstance(obj);
            }
            if (name.equals("LuaClosure") && (obj instanceof LuaClosure)) {
                return true;
            }
            if (name.equals("LuaJavaInvoker") && (obj instanceof LuaJavaInvoker)) {
                return true;
            }
            if (name.equals("PZNetKahluaTableImpl") && (obj instanceof PZNetKahluaTableImpl)) {
                return true;
            }
            return name.equals("KahluaTableImpl") && (obj instanceof KahluaTableImpl);
        }

        @LuaMethod(name = "getClassSimpleName", global = true)
        public static String getClassSimpleName(Object object) {
            return object.getClass().getSimpleName();
        }

        @LuaMethod(name = "serverConnect", global = true)
        public static void serverConnect(String user, String pass, String server, String localIP, String port, String serverPassword, String serverName, boolean useSteamRelay, boolean doHash, int authtype, String secretKey) {
            ConnectionManager.getInstance().serverConnect(user, pass, server, localIP, port, serverPassword, serverName, useSteamRelay, doHash, authtype, secretKey);
        }

        @LuaMethod(name = "serverConnectCoop", global = true)
        public static void serverConnectCoop(String serverSteamID) {
            ConnectionManager.getInstance().serverConnectCoop(serverSteamID);
        }

        @LuaMethod(name = "sendPing", global = true)
        public static void sendPing() {
            if (GameClient.client) {
                ByteBufferWriter bb = GameClient.connection.startPingPacket();
                PacketTypes.doPingPacket(bb);
                bb.putLong(System.currentTimeMillis());
                GameClient.connection.endPingPacket();
            }
        }

        @LuaMethod(name = "connectionManagerLog", global = true)
        public static void connectionManagerLog(String event, String message) {
            ConnectionManager.log(event, message, GameClient.connection);
        }

        @LuaMethod(name = "forceDisconnect", global = true)
        public static void forceDisconnect() {
            if (GameClient.connection != null) {
                GameClient.connection.forceDisconnect("lua-force-disconnect");
            }
        }

        @LuaMethod(name = "checkPermissions", global = true)
        public static boolean checkPermissions(IsoPlayer player, Capability capability) {
            UdpConnection connection;
            if (GameServer.server && player != null && capability != null && (connection = GameServer.getConnectionFromPlayer(player)) != null) {
                return AntiCheatCapability.validate(connection, capability);
            }
            return true;
        }

        @LuaMethod(name = "backToSinglePlayer", global = true)
        public static void backToSinglePlayer() {
            if (GameClient.client) {
                GameClient.instance.doDisconnect("going back to single-player");
                GameClient.client = false;
                timeLastRefresh = 0L;
            }
        }

        @LuaMethod(name = "isIngameState", global = true)
        public static boolean isIngameState() {
            return GameWindow.states.current == IngameState.instance;
        }

        @LuaMethod(name = "getPerformanceLocal", global = true)
        public static KahluaTable getPerformanceLocal() {
            return PerformanceStatistic.getInstance().getLocalTable();
        }

        @LuaMethod(name = "getNetworkLocal", global = true)
        public static KahluaTable getNetworkLocal() {
            return NetworkStatistic.getInstance().getLocalTable();
        }

        @LuaMethod(name = "getGameLocal", global = true)
        public static KahluaTable getGameLocal() {
            return GameStatistic.getInstance().getLocalTable();
        }

        @LuaMethod(name = "getPerformanceRemote", global = true)
        public static KahluaTable getPerformanceRemote() {
            return PerformanceStatistic.getInstance().getRemoteTable();
        }

        @LuaMethod(name = "getNetworkRemote", global = true)
        public static KahluaTable getNetworkRemote() {
            return NetworkStatistic.getInstance().getRemoteTable();
        }

        @LuaMethod(name = "getGameRemote", global = true)
        public static KahluaTable getGameRemote() {
            return GameStatistic.getInstance().getRemoteTable();
        }

        @LuaMethod(name = "toggleStatisticsTransmission", global = true)
        public static void toggleStatisticsTransmission() {
            INetworkPacket.send(PacketTypes.PacketType.Statistics, new Object[0]);
        }

        @LuaMethod(name = "getMPStatus", global = true)
        public static KahluaTable getMPStatus() {
            return StatusManager.getInstance().getTable();
        }

        @LuaMethod(name = "canConnect", global = true)
        public static boolean canConnect() {
            return GameClient.instance.canConnect();
        }

        @LuaMethod(name = "getReconnectCountdownTimer", global = true)
        public static String getReconnectCountdownTimer() {
            return GameClient.instance.getReconnectCountdownTimer();
        }

        @LuaMethod(name = "sendAnimalGenome", global = true)
        public static void sendAnimalGenome(IsoAnimal animal) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.AnimalCommand, AnimalCommandPacket.Type.UpdateGenome, animal);
            }
        }

        @LuaMethod(name = "addAnimal", global = true)
        public static IsoAnimal addAnimal(IsoCell cell, int x, int y, int z, String animalType, AnimalBreed breed, boolean skeleton) {
            return new IsoAnimal(cell, x, y, z, animalType, breed, skeleton);
        }

        @LuaMethod(name = "addAnimal", global = true)
        public static IsoAnimal addAnimal(IsoCell cell, int x, int y, int z, String animalType, AnimalBreed breed) {
            return new IsoAnimal(cell, x, y, z, animalType, breed);
        }

        @LuaMethod(name = "removeAnimal", global = true)
        public static void removeAnimal(int id) {
            IsoAnimal animal = getAnimal(id);
            if (animal != null) {
                animal.remove();
            } else {
                AnimalSynchronizationManager.getInstance().delete((short) id);
            }
        }

        @LuaMethod(name = "getFakeAttacker", global = true)
        public static IsoGameCharacter getFakeAttacker() {
            return IsoWorld.instance.currentCell.getFakeZombieForHit();
        }

        @LuaMethod(name = "sendHitPlayer", global = true)
        public static void sendHitPlayer(IsoPlayer target, String damage, String range) {
            if (Core.debug && GameClient.client) {
                InventoryItem inventoryItem = IsoPlayer.getInstance().getPrimaryHandItem() == null ? IsoPlayer.getInstance().bareHands : IsoPlayer.getInstance().getPrimaryHandItem();
                GameClient.sendPlayerHit(IsoPlayer.getInstance(), target, (HandWeapon) inventoryItem, Float.parseFloat(damage), false, Float.parseFloat(range), false, null, false, false, false);
            }
        }

        @LuaMethod(name = "sendHitVehicle", global = true)
        public static void sendHitVehicle(IsoGameCharacter target, String damage, boolean isTargetHitFromBehind, String vehicleDamage, String vehicleSpeed, boolean isVehicleHitFromBehind) {
            BaseVehicle vehicle;
            if (GameClient.client && (vehicle = IsoPlayer.getInstance().getNearVehicle()) != null) {
                GameClient.sendVehicleHit(IsoPlayer.getInstance(), target, vehicle, Float.parseFloat(damage), isTargetHitFromBehind, Integer.parseInt(vehicleDamage), Float.parseFloat(vehicleSpeed), isVehicleHitFromBehind);
            }
        }

        @LuaMethod(name = "requestUsers", global = true)
        public static void requestUsers() {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.RequestNetworkUsers, new Object[0]);
            }
        }

        @LuaMethod(name = "requestPVPEvents", global = true)
        public static void requestPVPEvents() {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.PVPEvents, false);
            }
        }

        @LuaMethod(name = "clearPVPEvents", global = true)
        public static void clearPVPEvents() {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.PVPEvents, true);
            }
        }

        @LuaMethod(name = "getUsers", global = true)
        public static ArrayList<NetworkUser> getUsers() {
            return NetworkUsers.instance.getUsers();
        }

        @LuaMethod(name = "networkUserAction", global = true)
        public static void networkUserAction(String action, String username, String additionArgument) {
            INetworkPacket.send(PacketTypes.PacketType.NetworkUserAction, action, username, additionArgument);
        }

        @LuaMethod(name = "banUnbanUserAction", global = true)
        public static void banUnbanUserAction(String action, String username, String additionArgument) {
            INetworkPacket.send(PacketTypes.PacketType.BanUnbanUserAction, action, username, additionArgument);
        }

        @LuaMethod(name = "teleportUserAction", global = true)
        public static void teleportUserAction(String action, String username, String additionArgument) {
            INetworkPacket.send(PacketTypes.PacketType.TeleportUserAction, action, username, additionArgument);
        }

        @LuaMethod(name = "teleportToHimUserAction", global = true)
        public static void teleportToHimUserAction(String action, String username, String additionArgument) {
            INetworkPacket.send(PacketTypes.PacketType.TeleportToHimUserAction, action, username, additionArgument);
        }

        @LuaMethod(name = "requestRoles", global = true)
        public static void requestRoles() {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.RequestRoles, new Object[0]);
            }
        }

        @LuaMethod(name = "getRoles", global = true)
        public static ArrayList<Role> getRoles() {
            return Roles.getRoles();
        }

        @LuaMethod(name = "getCapabilities", global = true)
        public static ArrayList<Capability> getCapabilities() {
            ArrayList<Capability> res = new ArrayList<>();
            Collections.addAll(res, Capability.values());
            return res;
        }

        @LuaMethod(name = "addRole", global = true)
        public static void addRole(String name) {
            Roles.addRole(name);
        }

        @LuaMethod(name = "setupRole", global = true)
        public static void setupRole(Role role, String description, Color color, KahluaTable capabilitiesRaw) {
            ArrayList<Capability> capabilities = new ArrayList<>();
            KahluaTableIterator it = capabilitiesRaw.iterator();
            while (it.advance()) {
                if (((Boolean) it.getValue()).booleanValue()) {
                    capabilities.add((Capability) it.getKey());
                }
            }
            Roles.setupRole(role.getName(), description, color, capabilities);
        }

        @LuaMethod(name = "deleteRole", global = true)
        public static void deleteRole(String name) {
            Roles.deleteRole(name, IsoPlayer.getInstance().getUsername());
        }

        @LuaMethod(name = "setDefaultRoleFor", global = true)
        public static void setDefaultRoleFor(String defaultId, String roleName) {
            Roles.setDefaultRoleFor(defaultId, roleName);
        }

        @LuaMethod(name = "moveRole", global = true)
        public static void moveRole(byte dir, String roleName) {
            Roles.moveRole(dir, roleName);
        }

        @LuaMethod(name = "getWarNearest", global = true)
        public static WarManager.War getWarNearest() {
            return WarManager.getWarNearest(IsoPlayer.getInstance());
        }

        @LuaMethod(name = "getWars", global = true)
        public static ArrayList<WarManager.War> getWars() {
            return WarManager.getWarRelevent(IsoPlayer.getInstance());
        }

        @LuaMethod(name = "getHutch", global = true)
        public static IsoHutch getHutch(int x, int y, int z) {
            return IsoHutch.getHutch(x, y, z);
        }

        @LuaMethod(name = "getAnimal", global = true)
        public static IsoAnimal getAnimal(int id) {
            return AnimalInstanceManager.getInstance().get((short) id);
        }

        @LuaMethod(name = "sendAddAnimalFromHandsInTrailer", global = true)
        public static void sendAddAnimalFromHandsInTrailer(IsoAnimal animal, IsoPlayer player, BaseVehicle vehicle) {
            if (GameServer.server) {
                INetworkPacket.sendToRelative(PacketTypes.PacketType.AnimalCommand, vehicle.getX(), vehicle.getY(), AnimalCommandPacket.Type.AddAnimalFromHandsInTrailer, animal, player, vehicle, null);
            }
        }

        @LuaMethod(name = "sendAddAnimalFromHandsInTrailer", global = true)
        public static void sendAddAnimalFromHandsInTrailer(IsoDeadBody animal, IsoPlayer player, BaseVehicle vehicle) {
            if (GameServer.server) {
                INetworkPacket.sendToRelative(PacketTypes.PacketType.AnimalCommand, vehicle.getX(), vehicle.getY(), AnimalCommandPacket.Type.AddAnimalFromHandsInTrailer, animal, player, vehicle, null);
            }
        }

        @LuaMethod(name = "sendAddAnimalInTrailer", global = true)
        public static void sendAddAnimalInTrailer(IsoAnimal animal, IsoPlayer player, BaseVehicle vehicle) {
            if (GameServer.server) {
                INetworkPacket.sendToRelative(PacketTypes.PacketType.AnimalCommand, vehicle.getX(), vehicle.getY(), AnimalCommandPacket.Type.AddAnimalInTrailer, animal, player, vehicle, null);
            }
        }

        @LuaMethod(name = "sendAddAnimalInTrailer", global = true)
        public static void sendAddAnimalInTrailer(IsoDeadBody animal, IsoPlayer player, BaseVehicle vehicle) {
            if (GameServer.server) {
                INetworkPacket.sendToRelative(PacketTypes.PacketType.AnimalCommand, vehicle.getX(), vehicle.getY(), AnimalCommandPacket.Type.AddAnimalInTrailer, animal, player, vehicle, null);
            }
        }

        @LuaMethod(name = "sendRemoveAnimalFromTrailer", global = true)
        public static void sendRemoveAnimalFromTrailer(IsoAnimal animal, IsoPlayer player, BaseVehicle vehicle) {
            if (GameServer.server) {
                INetworkPacket.sendToRelative(PacketTypes.PacketType.AnimalCommand, vehicle.getX(), vehicle.getY(), AnimalCommandPacket.Type.RemoveAnimalFromTrailer, animal, player, vehicle, null);
            }
        }

        @LuaMethod(name = "sendRemoveAndGrabAnimalFromTrailer", global = true)
        public static void sendRemoveAndGrabAnimalFromTrailer(IsoAnimal animal, IsoPlayer player, BaseVehicle vehicle, AnimalInventoryItem item) {
            if (GameServer.server) {
                INetworkPacket.sendToRelative(PacketTypes.PacketType.AnimalCommand, vehicle.getX(), vehicle.getY(), AnimalCommandPacket.Type.RemoveAndGrabAnimalFromTrailer, animal, player, vehicle, item);
            }
        }

        @LuaMethod(name = "sendRemoveAndGrabAnimalFromTrailer", global = true)
        public static void sendRemoveAndGrabAnimalFromTrailer(IsoDeadBody animal, IsoPlayer player, BaseVehicle vehicle, AnimalInventoryItem item) {
            if (GameServer.server) {
                INetworkPacket.sendToRelative(PacketTypes.PacketType.AnimalCommand, vehicle.getX(), vehicle.getY(), AnimalCommandPacket.Type.RemoveAndGrabAnimalFromTrailer, animal, player, vehicle, item);
            }
        }

        @LuaMethod(name = "sendPickupAnimal", global = true)
        public static void sendPickupAnimal(IsoAnimal animal, IsoPlayer player, AnimalInventoryItem item) {
            if (GameServer.server) {
                INetworkPacket.sendToRelative(PacketTypes.PacketType.AnimalCommand, player.getX(), player.getY(), AnimalCommandPacket.Type.PickupAnimal, animal, player, item);
            }
        }

        @LuaMethod(name = "sendButcherAnimal", global = true)
        public static void sendButcherAnimal(IsoDeadBody body, IsoPlayer player) {
            if (GameServer.server) {
                INetworkPacket.sendToRelative(PacketTypes.PacketType.AnimalCommand, body.getX(), body.getY(), AnimalCommandPacket.Type.ButcherAnimal, body, player);
            }
        }

        @LuaMethod(name = "sendFeedAnimalFromHand", global = true)
        public static void sendFeedAnimalFromHand(IsoAnimal animal, IsoPlayer player, InventoryItem item) {
            if (GameServer.server) {
                INetworkPacket.sendToRelative(PacketTypes.PacketType.AnimalCommand, player.getX(), player.getY(), AnimalCommandPacket.Type.FeedAnimalFromHand, animal, player, item);
            }
        }

        @LuaMethod(name = "sendHutchGrabAnimal", global = true)
        public static void sendHutchGrabAnimal(IsoAnimal animal, IsoPlayer player, IsoObject object, InventoryItem item) {
            if (GameServer.server) {
                INetworkPacket.sendToRelative(PacketTypes.PacketType.AnimalCommand, player.getX(), player.getY(), AnimalCommandPacket.Type.HutchGrabAnimal, animal, player, object, item);
            }
        }

        @LuaMethod(name = "sendHutchGrabCorpseAction", global = true)
        public static void sendHutchGrabCorpseAction(IsoAnimal animal, IsoPlayer player, IsoObject object, InventoryItem item) {
            if (GameServer.server) {
                INetworkPacket.sendToRelative(PacketTypes.PacketType.AnimalCommand, player.getX(), player.getY(), AnimalCommandPacket.Type.HutchGrabCorpseAction, animal, player, object, item);
            }
        }

        @LuaMethod(name = "sendHutchRemoveAnimalAction", global = true)
        public static void sendHutchRemoveAnimalAction(IsoAnimal animal, IsoPlayer player, IsoObject object) {
            if (GameServer.server) {
                INetworkPacket.sendToRelative(PacketTypes.PacketType.AnimalCommand, player.getX(), player.getY(), AnimalCommandPacket.Type.HutchRemoveAnimal, animal, player, object);
            }
        }

        @LuaMethod(name = "sendCorpse", global = true)
        public static void sendCorpse(IsoDeadBody body) {
            if (GameServer.server) {
                GameServer.sendCorpse(body);
            }
        }

        @LuaMethod(name = "getAllItems", global = true)
        public static ArrayList<Item> getAllItems() {
            return ScriptManager.instance.getAllItems();
        }

        @LuaMethod(name = "scoreboardUpdate", global = true)
        public static void scoreboardUpdate() {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.ScoreboardUpdate, new Object[0]);
            }
        }

        @LuaMethod(name = "save", global = true)
        public static void save(boolean doCharacter) {
            try {
                GameWindow.save(doCharacter);
            } catch (Throwable t) {
                ExceptionLogger.logException(t);
            }
        }

        @LuaMethod(name = "saveGame", global = true)
        public static void saveGame() {
            save(true);
        }

        @LuaMethod(name = "getAllRecipes", global = true)
        public static ArrayList<Recipe> getAllRecipes() {
            return new ArrayList<>(ScriptManager.instance.getAllRecipes());
        }

        @LuaMethod(name = "requestUserlog", global = true)
        public static void requestUserlog(String user) {
            if (GameClient.client) {
                GameClient.instance.requestUserlog(user);
            }
        }

        @LuaMethod(name = "addUserlog", global = true)
        public static void addUserlog(String user, String type, String text) {
            if (GameClient.client) {
                GameClient.instance.addUserlog(user, type, text);
            }
        }

        @LuaMethod(name = "removeUserlog", global = true)
        public static void removeUserlog(String user, String type, String text) {
            if (GameClient.client) {
                GameClient.instance.removeUserlog(user, type, text);
            }
        }

        @LuaMethod(name = "tabToX", global = true)
        public static String tabToX(String a, int tabX) {
            return a + org.apache.commons.lang3.StringUtils.SPACE.repeat(Math.max(0, tabX - a.length()));
        }

        @LuaMethod(name = "istype", global = true)
        public static boolean isType(Object obj, String name) {
            if (LuaManager.exposer.typeMap.containsKey(name)) {
                Class<?> c = LuaManager.exposer.typeMap.get(name);
                return c.equals(obj.getClass());
            }
            return false;
        }

        @LuaMethod(name = "isoToScreenX", global = true)
        public static float isoToScreenX(int player, float x, float y, float z) {
            PlayerCamera camera = IsoCamera.cameras[player];
            float fixDX = camera.fixJigglyModelsSquareX;
            float fixDY = camera.fixJigglyModelsSquareY;
            float ret = IsoUtils.XToScreen(x + fixDX, y + fixDY, z, 0) - camera.getOffX();
            return IsoCamera.getScreenLeft(player) + (ret / camera.zoom);
        }

        @LuaMethod(name = "isoToScreenY", global = true)
        public static float isoToScreenY(int player, float x, float y, float z) {
            PlayerCamera camera = IsoCamera.cameras[player];
            float fixDX = camera.fixJigglyModelsSquareX;
            float fixDY = camera.fixJigglyModelsSquareY;
            float ret = IsoUtils.YToScreen(x + fixDX, y + fixDY, z, 0) - camera.getOffY();
            return IsoCamera.getScreenTop(player) + (ret / camera.zoom);
        }

        @LuaMethod(name = "screenToIsoX", global = true)
        public static float screenToIsoX(int player, float x, float y, float z) {
            float zoom = Core.getInstance().getZoom(player);
            return IsoCamera.cameras[player].XToIso((x - IsoCamera.getScreenLeft(player)) * zoom, (y - IsoCamera.getScreenTop(player)) * zoom, z);
        }

        @LuaMethod(name = "screenToIsoY", global = true)
        public static float screenToIsoY(int player, float x, float y, float z) {
            float zoom = Core.getInstance().getZoom(player);
            return IsoCamera.cameras[player].YToIso((x - IsoCamera.getScreenLeft(player)) * zoom, (y - IsoCamera.getScreenTop(player)) * zoom, z);
        }

        @LuaMethod(name = "getAmbientStreamManager", global = true)
        public static BaseAmbientStreamManager getAmbientStreamManager() {
            return AmbientStreamManager.instance;
        }

        @LuaMethod(name = "getSleepingEvent", global = true)
        public static SleepingEvent getSleepingEvent() {
            return SleepingEvent.instance;
        }

        @LuaMethod(name = "setPlayerButtonsActive", global = true)
        public static void setPlayerButtonsActive(int id, boolean bActive) {
            IsoPlayer.players[id].setJoypadButtonsActive(bActive);
        }

        @LuaMethod(name = "setIgnoreInputsForDirection", global = true)
        public static void setIgnoreInputsForDirection(int id, boolean bActive) {
            IsoPlayer.players[id].setIgnoreInputsForDirection(bActive);
        }

        @LuaMethod(name = "setJoypadIgnoreAim", global = true)
        public static void setJoypadIgnoreAim(int id, boolean bActive) {
            IsoPlayer.players[id].setJoypadIgnoreAim(bActive);
        }

        @LuaMethod(name = "setJoypadIgnoreAimUntilCentered", global = true)
        public static void setJoypadIgnoreAimUntilCentered(int id, boolean bActive) {
            IsoPlayer.players[id].setJoypadIgnoreAimUntilCentered(bActive);
        }

        @LuaMethod(name = "setActivePlayer", global = true)
        public static void setActivePlayer(int id) {
            if (GameClient.client) {
                return;
            }
            IsoPlayer.setInstance(IsoPlayer.players[id]);
            IsoCamera.setCameraCharacter(IsoPlayer.getInstance());
        }

        @LuaMethod(name = "getPlayer", global = true)
        public static IsoPlayer getPlayer() {
            return IsoPlayer.getInstance();
        }

        @LuaMethod(name = "getNumActivePlayers", global = true)
        public static int getNumActivePlayers() {
            return IsoPlayer.numPlayers;
        }

        @LuaMethod(name = "playServerSound", global = true)
        public static void playServerSound(String sound, IsoGridSquare sq) {
            GameServer.PlayWorldSoundServer(sound, false, sq, 0.2f, 5.0f, 1.1f, true);
        }

        @LuaMethod(name = "getMaxActivePlayers", global = true)
        public static int getMaxActivePlayers() {
            return 4;
        }

        @LuaMethod(name = "getPlayerScreenLeft", global = true)
        public static int getPlayerScreenLeft(int player) {
            return IsoCamera.getScreenLeft(player);
        }

        @LuaMethod(name = "getPlayerScreenTop", global = true)
        public static int getPlayerScreenTop(int player) {
            return IsoCamera.getScreenTop(player);
        }

        @LuaMethod(name = "getPlayerScreenWidth", global = true)
        public static int getPlayerScreenWidth(int player) {
            return IsoCamera.getScreenWidth(player);
        }

        @LuaMethod(name = "getPlayerScreenHeight", global = true)
        public static int getPlayerScreenHeight(int player) {
            return IsoCamera.getScreenHeight(player);
        }

        @LuaMethod(name = "getPlayerByOnlineID", global = true)
        public static IsoPlayer getPlayerByOnlineID(int id) {
            if (GameServer.server) {
                return GameServer.IDToPlayerMap.get(Short.valueOf((short) id));
            }
            if (GameClient.client) {
                return GameClient.IDToPlayerMap.get(Short.valueOf((short) id));
            }
            return null;
        }

        @LuaMethod(name = "initUISystem", global = true)
        public static void initUISystem() {
            UIManager.init();
            LuaEventManager.triggerEvent("OnCreatePlayer", 0, IsoPlayer.players[0]);
        }

        @LuaMethod(name = "getPerformance", global = true)
        public static PerformanceSettings getPerformance() {
            return PerformanceSettings.instance;
        }

        @LuaMethod(name = "getWorldSoundManager", global = true)
        public static WorldSoundManager getWorldSoundManager() {
            return WorldSoundManager.instance;
        }

        @LuaMethod(name = "getAnimalChunk", global = true)
        public static AnimalChunk getAnimalChunk(int x, int y) {
            return AnimalManagerWorker.getInstance().getAnimalChunk(x, y);
        }

        @LuaMethod(name = "AddWorldSound", global = true)
        public static void AddWorldSound(IsoPlayer player, int radius, int volume) {
            WorldSoundManager.instance.addSound(null, PZMath.fastfloor(player.getX()), PZMath.fastfloor(player.getY()), PZMath.fastfloor(player.getZ()), radius, volume, false);
        }

        @LuaMethod(name = "AddNoiseToken", global = true)
        public static void AddNoiseToken(IsoGridSquare sq, int radius) {
        }

        @LuaMethod(name = "pauseSoundAndMusic", global = true)
        public static void pauseSoundAndMusic() {
            DebugType.ExitDebug.debugln("pauseSoundAndMusic 1");
            SoundManager.instance.pauseSoundAndMusic();
            DebugType.ExitDebug.debugln("pauseSoundAndMusic 2");
        }

        @LuaMethod(name = "resumeSoundAndMusic", global = true)
        public static void resumeSoundAndMusic() {
            SoundManager.instance.resumeSoundAndMusic();
        }

        @LuaMethod(name = "isDemo", global = true)
        public static boolean isDemo() {
            return false;
        }

        @LuaMethod(name = "getTimeInMillis", global = true)
        public static long getTimeInMillis() {
            return System.currentTimeMillis();
        }

        @LuaMethod(name = "getCurrentCoroutine", global = true)
        public static Coroutine getCurrentCoroutine() {
            return LuaManager.thread.getCurrentCoroutine();
        }

        @LuaMethod(name = "reloadLuaFile", global = true)
        public static Object reloadLuaFile(String filename) {
            String filename1 = filename.replace("\\", "/");
            LuaManager.loaded.remove(filename1);
            return LuaManager.RunLua(filename, true);
        }

        @LuaMethod(name = "reloadServerLuaFile", global = true)
        public static Object reloadServerLuaFile(String filename) {
            if (!GameServer.server) {
                return null;
            }
            String filename2 = ZomboidFileSystem.instance.getCacheDir() + File.separator + "Server" + File.separator + filename;
            LuaManager.loaded.remove(filename2);
            return LuaManager.RunLua(filename2, true);
        }

        @LuaMethod(name = "setSpawnRegion", global = true)
        public static void setSpawnRegion(String spawnRegionName) {
            if (GameClient.client) {
                IsoWorld.instance.setSpawnRegion(spawnRegionName);
            }
        }

        @LuaMethod(name = "getServerSpawnRegions", global = true)
        public static KahluaTable getServerSpawnRegions() {
            if (!GameClient.client) {
                return null;
            }
            return GameClient.instance.getServerSpawnRegions();
        }

        @LuaMethod(name = "getServerOptions", global = true)
        public static ServerOptions getServerOptions() {
            return ServerOptions.instance;
        }

        @LuaMethod(name = "getServerName", global = true)
        public static String getServerName() {
            if (GameServer.server) {
                return GameServer.serverName;
            }
            if (GameClient.client) {
                return GameClient.serverName;
            }
            return "";
        }

        @LuaMethod(name = "getServerIP", global = true)
        public static String getServerIP() {
            if (GameServer.server) {
                return GameServer.ipCommandline == null ? GameServer.ip : GameServer.ipCommandline;
            }
            if (GameClient.client) {
                return GameClient.ip;
            }
            return "";
        }

        @LuaMethod(name = "getServerPort", global = true)
        public static String getServerPort() {
            if (GameServer.server) {
                return String.valueOf(GameServer.defaultPort);
            }
            if (GameClient.client) {
                return String.valueOf(GameClient.port);
            }
            return "";
        }

        @LuaMethod(name = "isShowConnectionInfo", global = true)
        public static boolean isShowConnectionInfo() {
            return NetworkAIParams.isShowConnectionInfo();
        }

        @LuaMethod(name = "setShowConnectionInfo", global = true)
        public static void setShowConnectionInfo(boolean enabled) {
            NetworkAIParams.setShowConnectionInfo(enabled);
        }

        @LuaMethod(name = "isShowServerInfo", global = true)
        public static boolean isShowServerInfo() {
            return NetworkAIParams.isShowServerInfo();
        }

        @LuaMethod(name = "setShowServerInfo", global = true)
        public static void setShowServerInfo(boolean enabled) {
            NetworkAIParams.setShowServerInfo(enabled);
        }

        @LuaMethod(name = "getSpecificPlayer", global = true)
        public static IsoPlayer getSpecificPlayer(int player) {
            return IsoPlayer.players[player];
        }

        @LuaMethod(name = "getCameraOffX", global = true)
        public static float getCameraOffX() {
            return IsoCamera.getOffX();
        }

        @LuaMethod(name = "getLatestSave", global = true)
        public static KahluaTable getLatestSave() {
            KahluaTable table = LuaManager.platform.newTable();
            try {
                BufferedReader br = new BufferedReader(new FileReader(ZomboidFileSystem.instance.getCacheDir() + File.separator + "latestSave.ini"));
                int i = 1;
                while (true) {
                    try {
                        String line = br.readLine();
                        if (line != null) {
                            table.rawset(i, line);
                            i++;
                        } else {
                            br.close();
                            return table;
                        }
                    } catch (Exception e) {
                        return table;
                    }
                }
            } catch (FileNotFoundException e2) {
                return table;
            }
        }

        @LuaMethod(name = "isCurrentExecutionPoint", global = true)
        public static boolean isCurrentExecutionPoint(String file, int line) {
            int i = LuaManager.thread.currentCoroutine.getCallframeTop() - 1;
            if (i < 0) {
                i = 0;
            }
            LuaCallFrame f = LuaManager.thread.currentCoroutine.getCallFrame(i);
            return f.closure != null && f.closure.prototype.lines[f.pc] == line && file.equals(f.closure.prototype.filename);
        }

        @LuaMethod(name = "toggleBreakOnChange", global = true)
        public static void toggleBreakOnChange(KahluaTable table, Object key) {
            if (Core.debug) {
                LuaManager.thread.toggleBreakOnChange(table, key);
            }
        }

        @LuaMethod(name = "isDebugEnabled", global = true)
        public static boolean isDebugEnabled() {
            return Core.debug;
        }

        @LuaMethod(name = "toggleBreakOnRead", global = true)
        public static void toggleBreakOnRead(KahluaTable table, Object key) {
            if (Core.debug) {
                LuaManager.thread.toggleBreakOnRead(table, key);
            }
        }

        @LuaMethod(name = "toggleBreakpoint", global = true)
        public static void toggleBreakpoint(String file, int line) {
            String file2 = file.replace("\\", "/");
            if (Core.debug) {
                LuaManager.thread.breakpointToggle(file2, line);
            }
        }

        @LuaMethod(name = "sendVisual", global = true)
        public static void sendVisual(IsoPlayer player) {
            if (GameClient.client) {
                GameClient.instance.sendVisual(player);
            }
        }

        @LuaMethod(name = "sendSyncPlayerFields", global = true)
        public static void sendSyncPlayerFields(IsoPlayer player, byte syncParams) {
            if (GameServer.server) {
                GameServer.sendSyncPlayerFields(player, syncParams);
            }
        }

        @LuaMethod(name = "sendClothing", global = true)
        public static void sendClothing(IsoPlayer player, ItemBodyLocation location, InventoryItem item) {
            if (GameServer.server) {
                GameServer.sendSyncClothing(player, location, item);
            }
        }

        @LuaMethod(name = "syncVisuals", global = true)
        public static void syncVisuals(IsoPlayer player) {
            if (GameServer.server) {
                GameServer.syncVisuals(player);
            }
        }

        @LuaMethod(name = "sendEquip", global = true)
        public static void sendEquip(IsoPlayer player) {
            if (GameServer.server) {
                player.updateHandEquips();
            }
        }

        @LuaMethod(name = "sendDamage", global = true)
        public static void sendDamage(IsoPlayer player) {
            if (GameServer.server) {
                INetworkPacket.send(player, PacketTypes.PacketType.PlayerDamage, player);
            }
        }

        @LuaMethod(name = "sendPlayerEffects", global = true)
        public static void sendPlayerEffects(IsoPlayer player) {
            if (GameServer.server) {
                INetworkPacket.send(player, PacketTypes.PacketType.PlayerEffects, player);
            }
        }

        @LuaMethod(name = "sendItemStats", global = true)
        public static void sendItemStats(InventoryItem item) {
            if (GameServer.server) {
                GameServer.sendItemStats(item);
            }
        }

        @LuaMethod(name = "hasDataReadBreakpoint", global = true)
        public static boolean hasDataReadBreakpoint(KahluaTable table, Object key) {
            return LuaManager.thread.hasReadDataBreakpoint(table, key);
        }

        @LuaMethod(name = "hasDataBreakpoint", global = true)
        public static boolean hasDataBreakpoint(KahluaTable table, Object key) {
            return LuaManager.thread.hasDataBreakpoint(table, key);
        }

        @LuaMethod(name = "hasBreakpoint", global = true)
        public static boolean hasBreakpoint(String file, int line) {
            return LuaManager.thread.hasBreakpoint(file, line);
        }

        @LuaMethod(name = "getLoadedLuaCount", global = true)
        public static int getLoadedLuaCount() {
            return LuaManager.loaded.size();
        }

        @LuaMethod(name = "getLoadedLua", global = true)
        public static String getLoadedLua(int n) {
            return LuaManager.loaded.get(n);
        }

        @LuaMethod(name = "isServer", global = true)
        public static boolean isServer() {
            return GameServer.server;
        }

        @LuaMethod(name = "isServerSoftReset", global = true)
        public static boolean isServerSoftReset() {
            return GameServer.server && GameServer.softReset;
        }

        @LuaMethod(name = "isClient", global = true)
        public static boolean isClient() {
            return GameClient.client;
        }

        @LuaMethod(name = "isMultiplayer", global = true)
        public static boolean isMultiplayer() {
            return GameClient.client || GameServer.server;
        }

        @LuaMethod(name = "canSeePlayerStats", global = true)
        public static boolean canSeePlayerStats() {
            return GameClient.canSeePlayerStats();
        }

        @Deprecated
        @LuaMethod(name = "getAccessLevel", global = true)
        public static String getAccessLevel() {
            return GameClient.connection.getRole().getName();
        }

        @Deprecated
        @LuaMethod(name = "haveAccess", global = true)
        public static boolean haveAccess(String access) {
            try {
                Capability capability = Capability.valueOf(access);
                return GameClient.connection.getRole().hasCapability(capability);
            } catch (Exception e) {
                DebugLog.General.printException(e, "access=" + access, LogSeverity.Error);
                return false;
            }
        }

        @LuaMethod(name = "getOnlinePlayers", global = true)
        public static ArrayList<IsoPlayer> getOnlinePlayers() {
            if (GameServer.server) {
                return GameServer.getPlayers();
            }
            if (GameClient.client) {
                return GameClient.instance.getPlayers();
            }
            return new ArrayList<>();
        }

        @LuaMethod(name = "getDebug", global = true)
        public static boolean getDebug() {
            return Core.debug || (GameServer.server && GameServer.debug);
        }

        @LuaMethod(name = "getCameraOffY", global = true)
        public static float getCameraOffY() {
            return IsoCamera.getOffY();
        }

        @LuaMethod(name = "createRegionFile", global = true)
        public static KahluaTable createRegionFile() {
            KahluaTable table = LuaManager.platform.newTable();
            String mapName = IsoWorld.instance.getMap();
            if (mapName.equals("DEFAULT")) {
                MapGroups mapGroups = new MapGroups();
                mapGroups.createGroups();
                if (mapGroups.getNumberOfGroups() != 1) {
                    throw new RuntimeException("GameMap is DEFAULT but there are multiple worlds to choose from");
                }
                mapGroups.setWorld(0);
                mapName = IsoWorld.instance.getMap();
            }
            String[] lotDirs = mapName.split(";");
            int count = 1;
            for (String str : lotDirs) {
                String lotDir = str.trim();
                if (!lotDir.isEmpty()) {
                    File file = new File(ZomboidFileSystem.instance.getString("media/maps/" + lotDir + "/spawnpoints.lua"));
                    if (file.exists()) {
                        KahluaTable region = LuaManager.platform.newTable();
                        region.rawset("name", lotDir);
                        region.rawset("file", "media/maps/" + lotDir + "/spawnpoints.lua");
                        table.rawset(count, region);
                        count++;
                    }
                }
            }
            return table;
        }

        @LuaMethod(name = "getMapDirectoryTable", global = true)
        public static KahluaTable getMapDirectoryTable() {
            String[] internalNames;
            String[] internalNames2;
            KahluaTable table = LuaManager.platform.newTable();
            String[] internalNames3 = ZomboidFileSystem.instance.getMediaFile("maps").list();
            if (internalNames3 == null) {
                return table;
            }
            int count = 1;
            for (String item : internalNames3) {
                if (!item.equals("challengemaps")) {
                    table.rawset(count, item);
                    count++;
                }
            }
            for (String modId : ZomboidFileSystem.instance.getModIDs()) {
                ChooseGameInfo.Mod mod = null;
                try {
                    mod = ChooseGameInfo.getAvailableModDetails(modId);
                } catch (Exception e) {
                }
                if (mod != null) {
                    File fo = new File(mod.getCommonDir() + "/media/maps/");
                    if (fo.exists() && (internalNames2 = fo.list()) != null) {
                        for (String item2 : internalNames2) {
                            ChooseGameInfo.Map mapDetails = ChooseGameInfo.getMapDetails(item2);
                            if (mapDetails.getLotDirectories() != null && !mapDetails.getLotDirectories().isEmpty() && !item2.equals("challengemaps")) {
                                table.rawset(count, item2);
                                count++;
                            }
                        }
                    }
                    File fo2 = new File(mod.getVersionDir() + "/media/maps/");
                    if (fo2.exists() && (internalNames = fo2.list()) != null) {
                        for (String item3 : internalNames) {
                            ChooseGameInfo.Map mapDetails2 = ChooseGameInfo.getMapDetails(item3);
                            if (mapDetails2.getLotDirectories() != null && !mapDetails2.getLotDirectories().isEmpty() && !item3.equals("challengemaps")) {
                                table.rawset(count, item3);
                                count++;
                            }
                        }
                    }
                }
            }
            return table;
        }

        @LuaMethod(name = "deleteSave", global = true)
        public static void deleteSave(String folder) {
            if (StringUtils.containsDoubleDot(folder)) {
                DebugLog.Lua.warn("relative paths not allowed");
                return;
            }
            File toRemove = new File(ZomboidFileSystem.instance.getSaveDirSub(folder));
            String[] internalNames = toRemove.list();
            if (internalNames == null) {
                return;
            }
            for (String str : internalNames) {
                File fileDelete = new File(ZomboidFileSystem.instance.getSaveDirSub(folder + File.separator + str));
                if (fileDelete.isDirectory()) {
                    deleteSave(folder + File.separator + fileDelete.getName());
                }
                fileDelete.delete();
            }
            toRemove.delete();
        }

        @LuaMethod(name = "sendPlayerExtraInfo", global = true)
        public static void sendPlayerExtraInfo(IsoPlayer p) {
            GameClient.sendPlayerExtraInfo(p);
        }

        @LuaMethod(name = "getServerAddressFromArgs", global = true)
        public static String getServerAddressFromArgs() {
            if (System.getProperty("args.server.connect") != null) {
                String connectStr = System.getProperty("args.server.connect");
                System.clearProperty("args.server.connect");
                return connectStr;
            }
            return null;
        }

        @LuaMethod(name = "getServerPasswordFromArgs", global = true)
        public static String getServerPasswordFromArgs() {
            if (System.getProperty("args.server.password") != null) {
                String connectStr = System.getProperty("args.server.password");
                System.clearProperty("args.server.password");
                return connectStr;
            }
            return null;
        }

        @Deprecated
        @LuaMethod(name = "getServerListFile", global = true)
        public static String getServerListFile() {
            return SteamUtils.isSteamModeEnabled() ? "ServerListSteam.txt" : "ServerList.txt";
        }

        @LuaMethod(name = "addServerToAccountList", global = true)
        public static void addServerToAccountList(Server server) {
            AccountDBHelper.getInstance().saveNewServer(server);
        }

        @LuaMethod(name = "updateServerToAccountList", global = true)
        public static void updateServerToAccountList(Server server) {
            AccountDBHelper.getInstance().updateServer(server);
        }

        @LuaMethod(name = "deleteServerToAccountList", global = true)
        public static void deleteServerToAccountList(Server server) {
            AccountDBHelper.getInstance().deleteServer(server);
        }

        @LuaMethod(name = "addAccountToAccountList", global = true)
        public static void addAccountToAccountList(Server server, Account account) {
            AccountDBHelper.getInstance().saveNewAccount(server, account);
        }

        @LuaMethod(name = "updateAccountToAccountList", global = true)
        public static void updateAccountToAccountList(Account account) {
            AccountDBHelper.getInstance().updateAccount(account);
        }

        @LuaMethod(name = "deleteAccountToAccountList", global = true)
        public static void deleteAccountToAccountList(Account account) {
            AccountDBHelper.getInstance().deleteAccount(account);
        }

        @LuaMethod(name = "getServerList", global = true)
        public static KahluaTable getServerList() {
            List<Server> result = AccountDBHelper.getInstance().getServerList();
            KahluaTable table = LuaManager.platform.newTable();
            int count = 1;
            for (int i = 0; i < result.size(); i++) {
                Server server = result.get(i);
                Double n = Double.valueOf(count);
                table.rawset(n, server);
                count++;
            }
            return table;
        }

        @LuaMethod(name = "ping", global = true)
        public static void ping(String username, String pwd, String ip, String port, boolean doHash) {
            ConnectionManager.getInstance().ping(username, pwd, ip, port, doHash);
        }

        @LuaMethod(name = "getCustomizationData", global = true)
        public static void getCustomizationData(String username, String pwd, String ip, String port, String serverPassword, String serverName, boolean doHash) {
            ConnectionManager.getInstance().getCustomizationData(username, pwd, ip, port, serverPassword, serverName, doHash);
        }

        @LuaMethod(name = "getCombatConfig", global = true)
        public static CombatConfig getCombatConfig() {
            return CombatManager.getInstance().getCombatConfig();
        }

        @LuaMethod(name = "stopPing", global = true)
        public static void stopPing() {
            ConnectionManager.getInstance().stopPing();
        }

        @LuaMethod(name = "transformIntoKahluaTable", global = true)
        public static KahluaTable transformIntoKahluaTable(HashMap<Object, Object> map) {
            KahluaTable table = LuaManager.platform.newTable();
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                table.rawset(entry.getKey(), entry.getValue());
            }
            return table;
        }

        @LuaMethod(name = "getSaveDirectory", global = true)
        public static ArrayList<File> getSaveDirectory(String folder) {
            File fo = new File(folder + File.separator);
            if (!fo.exists() && !Core.getInstance().isNoSave()) {
                fo.mkdir();
            }
            String[] internalNames = fo.list();
            if (internalNames == null) {
                return null;
            }
            ArrayList<File> savedGames = new ArrayList<>();
            for (String str : internalNames) {
                File file = new File(folder + File.separator + str);
                if (file.isDirectory()) {
                    savedGames.add(file);
                }
            }
            return savedGames;
        }

        @LuaMethod(name = "getFullSaveDirectoryTable", global = true)
        public static KahluaTable getFullSaveDirectoryTable() {
            KahluaTable table = LuaManager.platform.newTable();
            File fo = new File(ZomboidFileSystem.instance.getSaveDir() + File.separator);
            if (!fo.exists()) {
                fo.mkdir();
            }
            String[] internalNames = fo.list();
            if (internalNames == null) {
                return table;
            }
            ArrayList<File> savedGames = new ArrayList<>();
            for (int i = 0; i < internalNames.length; i++) {
                File file = new File(ZomboidFileSystem.instance.getSaveDir() + File.separator + internalNames[i]);
                if (file.isDirectory() && !"Multiplayer".equals(internalNames[i])) {
                    ArrayList<File> tb = getSaveDirectory(ZomboidFileSystem.instance.getSaveDir() + File.separator + internalNames[i]);
                    savedGames.addAll(tb);
                }
            }
            savedGames.sort(Comparator.comparingLong((File v0) -> {
                return v0.lastModified();
            }).reversed());
            int count = 1;
            for (int i2 = 0; i2 < savedGames.size(); i2++) {
                File file2 = savedGames.get(i2);
                String item = getSaveName(file2);
                Double n = Double.valueOf(count);
                table.rawset(n, item);
                count++;
            }
            return table;
        }

        public static String getSaveName(File file) {
            Path path = file.toPath();
            String[] pathComponents = (String[]) StreamSupport.stream(path.spliterator(), false).map((v0) -> {
                return v0.toString();
            }).toArray(x$0 -> {
                return new String[x$0];
            });
            return pathComponents[pathComponents.length - 2] + File.separator + file.getName();
        }

        @LuaMethod(name = "getSaveDirectoryTable", global = true)
        public static KahluaTable getSaveDirectoryTable() {
            return LuaManager.platform.newTable();
        }

        @LuaMethod(name = "getCurrentSaveName", global = true)
        public static String getCurrentSaveName() {
            return ZomboidFileSystem.instance.getCurrentSaveDir();
        }

        public static List<String> getMods() {
            List<String> result = new ArrayList<>();
            ZomboidFileSystem.instance.getAllModFolders(result);
            return result;
        }

        @LuaMethod(name = "doChallenge", global = true)
        public static void doChallenge(KahluaTable challenge) {
            Core.getInstance().setGameMode(challenge.rawget("gameMode").toString());
            Core.challengeId = challenge.rawget(ItemCodeOnCreate.PRINT_MEDIA_ID).toString();
            Core.lastStand = Core.gameMode.equals("LastStand");
            Core.getInstance().setChallenge(true);
            getWorld().setMap(challenge.getString("world"));
            IsoWorld.instance.setWorld(Integer.toString(Rand.Next(100000000)));
            getWorld().doChunkMapUpdate = false;
        }

        @LuaMethod(name = "doTutorial", global = true)
        public static void doTutorial(KahluaTable tutorial) {
            Core.getInstance().setGameMode("Tutorial");
            Core.lastStand = false;
            Core.challengeId = null;
            Core.getInstance().setChallenge(false);
            Core.tutorial = true;
            getWorld().setMap(tutorial.getString("world"));
            getWorld().doChunkMapUpdate = false;
        }

        @LuaMethod(name = "setMinMaxZombiesPerChunk", global = true)
        public static void setMinMaxZombiesPerChunk(float min, float max) {
            float min2 = PZMath.clamp(min, 0.0f, 255.0f);
            float max2 = PZMath.clamp(max, 0.0f, 255.0f);
            ZombiePopulationManager.instance.setZombiesMinPerChunk(min2);
            ZombiePopulationManager.instance.setZombiesMaxPerChunk(max2);
        }

        @LuaMethod(name = "deleteAllGameModeSaves", global = true)
        public static void deleteAllGameModeSaves(String gameMode) {
            String oldMode = Core.gameMode;
            Core.getInstance().setGameMode(gameMode);
            Path path = Paths.get(ZomboidFileSystem.instance.getGameModeCacheDir(), new String[0]);
            if (!Files.exists(path, new LinkOption[0])) {
                Core.getInstance().setGameMode(oldMode);
                return;
            }
            try {
                Files.walkFileTree(path, new FileVisitor<Path>() { // from class: zombie.Lua.LuaManager.GlobalObject.1
                    @Override // java.nio.file.FileVisitor
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override // java.nio.file.FileVisitor
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override // java.nio.file.FileVisitor
                    public FileVisitResult visitFileFailed(Path file, IOException exc) {
                        DebugType.General.printException(exc, LogSeverity.Error);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override // java.nio.file.FileVisitor
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException ex) {
                DebugType.General.printException(ex, LogSeverity.Error);
            }
            Core.getInstance().setGameMode(oldMode);
        }

        @LuaMethod(name = "sledgeDestroy", global = true)
        public static void sledgeDestroy(IsoObject object) {
            if (GameClient.client) {
                GameClient.destroy(object);
            }
        }

        @LuaMethod(name = "getBannedIPs", global = true)
        public static void getBannedIPs() {
            if (GameClient.client) {
                GameClient.getBannedIPs();
            }
        }

        @LuaMethod(name = "getBannedSteamIDs", global = true)
        public static void getBannedSteamIDs() {
            if (GameClient.client) {
                GameClient.getBannedSteamIDs();
            }
        }

        @LuaMethod(name = "getTickets", global = true)
        public static void getTickets(String author) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.ViewTickets, author);
            }
        }

        @LuaMethod(name = "addTicket", global = true)
        public static void addTicket(String author, String message, int ticketID) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.AddTicket, author, message, Integer.valueOf(ticketID));
            }
        }

        @LuaMethod(name = "viewedTicket", global = true)
        public static void viewedTicket(String author, int ticketID) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.ViewedTicket, author, Integer.valueOf(ticketID));
            }
        }

        @LuaMethod(name = "removeTicket", global = true)
        public static void removeTicket(int ticketID) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.RemoveTicket, Integer.valueOf(ticketID));
            }
        }

        @LuaMethod(name = "sendFactionInvite", global = true)
        public static void sendFactionInvite(Faction faction, IsoPlayer host, String invited) {
            if (GameClient.client) {
                GameClient.sendFactionInvite(faction, host, invited);
            }
        }

        @LuaMethod(name = "acceptFactionInvite", global = true)
        public static void acceptFactionInvite(Faction faction, String host) {
            if (GameClient.client) {
                GameClient.acceptFactionInvite(faction, host);
            }
        }

        @LuaMethod(name = "sendFactionStatsChange", global = true)
        public static void sendFactionStatsChange(IsoPlayer player) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.FactionStats, player);
            }
        }

        @LuaMethod(name = "sendSafehouseInvite", global = true)
        public static void sendSafehouseInvite(SafeHouse safehouse, IsoPlayer host, String invited) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.SafehouseInvite, safehouse, host, invited);
            }
        }

        @LuaMethod(name = "acceptSafehouseInvite", global = true)
        public static void acceptSafehouseInvite(SafeHouse safehouse, String host, IsoPlayer invited, boolean isAccepted) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.SafehouseAccept, safehouse, host, invited, Boolean.valueOf(isAccepted));
            }
        }

        @LuaMethod(name = "sendSafehouseChangeMember", global = true)
        public static void sendSafehouseChangeMember(SafeHouse safehouse, String player) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.SafehouseChangeMember, safehouse, player);
            }
        }

        @LuaMethod(name = "sendSafehouseChangeOwner", global = true)
        public static void sendSafehouseChangeOwner(SafeHouse safehouse, String username) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.SafehouseChangeOwner, safehouse, username);
            }
        }

        @LuaMethod(name = "sendSafehouseChangeRespawn", global = true)
        public static void sendSafehouseChangeRespawn(SafeHouse safehouse, String player, boolean doRemove) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.SafehouseChangeRespawn, safehouse, player, Boolean.valueOf(doRemove));
            }
        }

        @LuaMethod(name = "sendSafehouseChangeTitle", global = true)
        public static void sendSafehouseChangeTitle(SafeHouse safehouse, String title) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.SafehouseChangeTitle, safehouse, title);
            }
        }

        @LuaMethod(name = "sendSafezoneClaim", global = true)
        public static void sendSafezoneClaim(String username, int x, int y, int h, int w, String title) {
            if (GameClient.client) {
                IsoPlayer player = GameClient.instance.getPlayerFromUsername(username);
                INetworkPacket.send(PacketTypes.PacketType.SafezoneClaim, player, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(h), Integer.valueOf(w), title);
            }
        }

        @LuaMethod(name = "sendSafehouseClaim", global = true)
        public static void sendSafehouseClaim(IsoGridSquare square, IsoPlayer player, String title) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.SafehouseClaim, square, player, title);
            }
        }

        @LuaMethod(name = "sendSafehouseRelease", global = true)
        public static void sendSafehouseRelease(SafeHouse safehouse) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.SafehouseRelease, safehouse);
            }
        }

        @LuaMethod(name = "createHordeFromTo", global = true)
        public static void createHordeFromTo(float spawnX, float spawnY, float targetX, float targetY, int count) {
            ZombiePopulationManager.instance.createHordeFromTo(PZMath.fastfloor(spawnX), PZMath.fastfloor(spawnY), PZMath.fastfloor(targetX), PZMath.fastfloor(targetY), count);
        }

        @LuaMethod(name = "createHordeInAreaTo", global = true)
        public static void createHordeInAreaTo(int spawnX, int spawnY, int spawnW, int spawnH, int targetX, int targetY, int count) {
            ZombiePopulationManager.instance.createHordeInAreaTo(spawnX, spawnY, spawnW, spawnH, targetX, targetY, count);
        }

        @LuaMethod(name = "spawnHorde", global = true)
        public static void spawnHorde(float x, float y, float x2, float y2, float z, int count) {
            for (int spawned = 0; spawned < count; spawned++) {
                VirtualZombieManager.instance.choices.clear();
                IsoGridSquare g = IsoWorld.instance.currentCell.getGridSquare(Rand.Next(x, x2), Rand.Next(y, y2), z);
                if (g != null) {
                    VirtualZombieManager.instance.choices.add(g);
                    IsoZombie zombie2 = VirtualZombieManager.instance.createRealZombieAlways(IsoDirections.getRandom(), false);
                    zombie2.dressInRandomOutfit();
                    ZombieSpawnRecorder.instance.record(zombie2, "LuaManager.spawnHorde");
                }
            }
        }

        @LuaMethod(name = "createZombie", global = true)
        public static IsoZombie createZombie(float x, float y, float z, SurvivorDesc desc, int palette, IsoDirections dir) {
            VirtualZombieManager.instance.choices.clear();
            IsoGridSquare g = IsoWorld.instance.currentCell.getGridSquare(x, y, z);
            VirtualZombieManager.instance.choices.add(g);
            IsoZombie zombie2 = VirtualZombieManager.instance.createRealZombieAlways(dir, false);
            ZombieSpawnRecorder.instance.record(zombie2, "LuaManager.createZombie");
            return zombie2;
        }

        @LuaMethod(name = "triggerEvent", global = true)
        public static void triggerEvent(String event) {
            LuaEventManager.triggerEvent(event);
        }

        @LuaMethod(name = "triggerEvent", global = true)
        public static void triggerEvent(String event, Object param) {
            LuaEventManager.triggerEventGarbage(event, param);
        }

        @LuaMethod(name = "triggerEvent", global = true)
        public static void triggerEvent(String event, Object param, Object param2) {
            LuaEventManager.triggerEventGarbage(event, param, param2);
        }

        @LuaMethod(name = "triggerEvent", global = true)
        public static void triggerEvent(String event, Object param, Object param2, Object param3) {
            LuaEventManager.triggerEventGarbage(event, param, param2, param3);
        }

        @LuaMethod(name = "triggerEvent", global = true)
        public static void triggerEvent(String event, Object param, Object param2, Object param3, Object param4) {
            LuaEventManager.triggerEventGarbage(event, param, param2, param3, param4);
        }

        @LuaMethod(name = "debugLuaTable", global = true)
        public static void debugLuaTable(Object param, int depth) {
            if (depth <= 1 && (param instanceof KahluaTable)) {
                KahluaTable t = (KahluaTable) param;
                KahluaTableIterator i = t.iterator();
                String tab = "";
                for (int n = 0; n < depth; n++) {
                    tab = tab + "\t";
                }
                do {
                    Object a = i.getKey();
                    Object b = i.getValue();
                    if (a != null) {
                        if (b != null) {
                            DebugLog.Lua.debugln(tab + String.valueOf(a) + " : " + String.valueOf(b));
                        }
                        if (b instanceof KahluaTable) {
                            debugLuaTable(b, depth + 1);
                        }
                    }
                } while (i.advance());
                if (t.getMetatable() != null) {
                    debugLuaTable(t.getMetatable(), depth);
                }
            }
        }

        @LuaMethod(name = "debugLuaTable", global = true)
        public static void debugLuaTable(Object param) {
            debugLuaTable(param, 0);
        }

        @LuaMethod(name = "sendItemsInContainer", global = true)
        public static void sendItemsInContainer(IsoObject obj, ItemContainer container) {
            GameServer.sendItemsInContainer(obj, container == null ? obj.getContainer() : container);
        }

        @LuaMethod(name = "getModDirectoryTable", global = true)
        public static KahluaTable getModDirectoryTable() {
            KahluaTable table = LuaManager.platform.newTable();
            List<String> stories = getMods();
            int count = 1;
            for (int i = 0; i < stories.size(); i++) {
                String item = stories.get(i);
                Double n = Double.valueOf(count);
                table.rawset(n, item);
                count++;
            }
            return table;
        }

        @LuaMethod(name = "getModInfoByID", global = true)
        public static ChooseGameInfo.Mod getModInfoByID(String modID) {
            try {
                return ChooseGameInfo.getModDetails(modID);
            } catch (Exception ex) {
                DebugType.General.printException(ex, LogSeverity.Error);
                return null;
            }
        }

        @LuaMethod(name = "getModInfo", global = true)
        public static ChooseGameInfo.Mod getModInfo(String modDir) {
            try {
                return ChooseGameInfo.readModInfo(modDir);
            } catch (Exception ex) {
                ExceptionLogger.logException(ex);
                return null;
            }
        }

        @LuaMethod(name = "getMapFoldersForMod", global = true)
        public static ArrayList<String> getMapFoldersForMod(String modID) {
            DirectoryStream<Path> dstrm;
            try {
                ChooseGameInfo.Mod modInfo = ChooseGameInfo.getModDetails(modID);
                if (modInfo == null) {
                    return null;
                }
                ArrayList<String> result = null;
                String mapFolder = modInfo.getCommonDir() + File.separator + "media" + File.separator + "maps";
                File file = new File(mapFolder);
                if (file.exists() && file.isDirectory()) {
                    dstrm = Files.newDirectoryStream(file.toPath());
                    try {
                        for (Path path : dstrm) {
                            if (Files.isDirectory(path, new LinkOption[0]) && new File(mapFolder + File.separator + path.getFileName().toString() + File.separator + "map.info").exists()) {
                                if (result == null) {
                                    result = new ArrayList<>();
                                }
                                result.add(path.getFileName().toString());
                            }
                        }
                        if (dstrm != null) {
                            dstrm.close();
                        }
                    } finally {
                    }
                }
                String mapFolder2 = modInfo.getVersionDir() + File.separator + "media" + File.separator + "maps";
                File file2 = new File(mapFolder2);
                if (file2.exists() && file2.isDirectory()) {
                    dstrm = Files.newDirectoryStream(file2.toPath());
                    try {
                        for (Path path2 : dstrm) {
                            if (Files.isDirectory(path2, new LinkOption[0]) && new File(mapFolder2 + File.separator + path2.getFileName().toString() + File.separator + "map.info").exists()) {
                                if (result == null) {
                                    result = new ArrayList<>();
                                }
                                result.add(path2.getFileName().toString());
                            }
                        }
                        if (dstrm != null) {
                            dstrm.close();
                        }
                    } finally {
                    }
                }
                return result;
            } catch (Exception ex) {
                DebugType.General.printException(ex, LogSeverity.Error);
                return null;
            }
        }

        @LuaMethod(name = "spawnpointsExistsForMod", global = true)
        public static boolean spawnpointsExistsForMod(String modID, String mapFolder) {
            try {
                ChooseGameInfo.Mod modInfo = ChooseGameInfo.getModDetails(modID);
                if (modInfo == null) {
                    return false;
                }
                String pathCommon = modInfo.getCommonDir() + File.separator + "media" + File.separator + "maps" + File.separator + mapFolder + File.separator + "spawnpoints.lua";
                String pathVersion = modInfo.getVersionDir() + File.separator + "media" + File.separator + "maps" + File.separator + mapFolder + File.separator + "spawnpoints.lua";
                File commonFile = new File(pathCommon);
                File versionFile = new File(pathVersion);
                if (!commonFile.exists()) {
                    if (!versionFile.exists()) {
                        return false;
                    }
                }
                return true;
            } catch (Exception ex) {
                DebugType.General.printException(ex, LogSeverity.Error);
                return false;
            }
        }

        @LuaMethod(name = "getFileSeparator", global = true)
        public static String getFileSeparator() {
            return File.separator;
        }

        @LuaMethod(name = "getScriptManager", global = true)
        public static ScriptManager getScriptManager() {
            return ScriptManager.instance;
        }

        @LuaMethod(name = "checkSaveFolderExists", global = true)
        public static boolean checkSaveFolderExists(String f) {
            File file = new File(ZomboidFileSystem.instance.getSaveDir() + File.separator + f);
            return file.exists();
        }

        @LuaMethod(name = "getAbsoluteSaveFolderName", global = true)
        public static String getAbsoluteSaveFolderName(String f) {
            File file = new File(ZomboidFileSystem.instance.getSaveDir() + File.separator + f);
            return file.getAbsolutePath();
        }

        @LuaMethod(name = "checkSaveFileExists", global = true)
        public static boolean checkSaveFileExists(String f) {
            return new File(ZomboidFileSystem.instance.getFileNameInCurrentSave(f)).exists();
        }

        @LuaMethod(name = "checkSavePlayerExists", global = true)
        public static boolean checkSavePlayerExists() {
            if (GameClient.client) {
                return ClientPlayerDB.getInstance() != null && ClientPlayerDB.getInstance().clientLoadNetworkPlayer() && ClientPlayerDB.getInstance().isAliveMainNetworkPlayer();
            }
            return PlayerDBHelper.isPlayerAlive(ZomboidFileSystem.instance.getCurrentSaveDir(), 1);
        }

        @LuaMethod(name = "cacheFileExists", global = true)
        public static boolean cacheFileExists(String filename) {
            String str = filename.replace("/", File.separator);
            File file = new File(ZomboidFileSystem.instance.getCacheDir() + File.separator + "Lua" + File.separator + str.replace("\\", File.separator));
            return file.exists();
        }

        @LuaMethod(name = "fileExists", global = true)
        public static boolean fileExists(String filename) {
            String str = filename.replace("/", File.separator);
            File file = new File(ZomboidFileSystem.instance.getString(str.replace("\\", File.separator)));
            return file.exists();
        }

        @LuaMethod(name = "serverFileExists", global = true)
        public static boolean serverFileExists(String filename) {
            String str = filename.replace("/", File.separator);
            File file = new File(ZomboidFileSystem.instance.getCacheDir() + File.separator + "Server" + File.separator + str.replace("\\", File.separator));
            return file.exists();
        }

        @LuaMethod(name = "takeScreenshot", global = true)
        public static void takeScreenshot() {
            Core.getInstance().TakeFullScreenshot(null);
        }

        @LuaMethod(name = "takeScreenshot", global = true)
        public static void takeScreenshot(String fileName) {
            Core.getInstance().TakeFullScreenshot(fileName);
        }

        @LuaMethod(name = "checkStringPattern", global = true)
        public static boolean checkStringPattern(String pattern) {
            return !pattern.contains("[");
        }

        @LuaMethod(name = "instanceItem", global = true)
        public static InventoryItem instanceItem(Item item) {
            return InventoryItemFactory.CreateItem(item.moduleDotType);
        }

        @LuaMethod(name = "instanceItem", global = true)
        public static InventoryItem instanceItem(String item) {
            return InventoryItemFactory.CreateItem(item);
        }

        @LuaMethod(name = "instanceItem", global = true)
        public static InventoryItem instanceItem(String item, float useDelta) {
            return InventoryItemFactory.CreateItem(item, useDelta);
        }

        @LuaMethod(name = "instanceItem", global = true)
        public static InventoryItem instanceItem(ItemKey item) {
            return InventoryItemFactory.CreateItem(item);
        }

        @LuaMethod(name = "createNewScriptItem", global = true)
        public static Item createNewScriptItem(String base, String name, String display, String type, String icon) {
            Item item = new Item();
            item.setModule(ScriptManager.instance.getModule(base));
            item.getModule().items.getScriptMap().put(name, item);
            item.icon = "Item_" + icon;
            item.displayName = display;
            item.name = name;
            item.moduleDotType = item.getModule().name + "." + name;
            try {
                item.setItemType(ItemType.get(ResourceLocation.of(type)));
            } catch (Exception e) {
            }
            return item;
        }

        @LuaMethod(name = "cloneItemType", global = true)
        public static Item cloneItemType(String newName, String oldName) {
            Item olItem = ScriptManager.instance.FindItem(oldName);
            Item item = new Item();
            item.setModule(olItem.getModule());
            item.getModule().items.getScriptMap().put(newName, item);
            return item;
        }

        @LuaMethod(name = "moduleDotType", global = true)
        public static String moduleDotType(String module, String type) {
            return StringUtils.moduleDotType(module, type);
        }

        @LuaMethod(name = "require", global = true)
        public static Object require(String f) {
            String fCopy = f;
            if (!fCopy.endsWith(".lua")) {
                fCopy = fCopy + ".lua";
            }
            for (int n = 0; n < LuaManager.paths.size(); n++) {
                String relPath = LuaManager.paths.get(n);
                String absPath = ZomboidFileSystem.instance.getAbsolutePath(relPath + fCopy);
                if (absPath != null) {
                    return LuaManager.RunLua(ZomboidFileSystem.instance.getString(absPath));
                }
            }
            DebugLog.Lua.warn("require(\"" + f + "\") failed");
            return null;
        }

        @LuaMethod(name = "getRenderer", global = true)
        public static SpriteRenderer getRenderer() {
            return SpriteRenderer.instance;
        }

        @LuaMethod(name = "getGameTime", global = true)
        public static GameTime getGameTime() {
            return GameTime.instance;
        }

        @LuaMethod(name = "getMaxPlayers", global = true)
        public static Double getMaxPlayers() {
            return Double.valueOf(GameClient.connection.getMaxPlayers());
        }

        @LuaMethod(name = "callLua", global = true)
        public static void callLua(String func, Object param1) {
            LuaManager.caller.pcall(LuaManager.thread, LuaManager.env.rawget(func), param1);
        }

        @LuaMethod(name = "callLuaReturn", global = true)
        public static ArrayList<Object> callLuaReturn(String func, ArrayList<Object> params) {
            if (params == null) {
                params = new ArrayList<>();
            }
            ArrayList<Object> result = new ArrayList<>();
            LuaReturn luareturn = LuaManager.caller.protectedCall(LuaManager.thread, LuaManager.env.rawget(func), params);
            if (luareturn instanceof LuaSuccess) {
                LuaSuccess success = (LuaSuccess) luareturn;
                result.addAll(success);
            }
            return result;
        }

        @LuaMethod(name = "callLuaBool", global = true)
        public static Boolean callLuaBool(String func, Object params) {
            return LuaManager.caller.pcallBoolean(LuaManager.thread, LuaManager.env.rawget(func), params, null);
        }

        @LuaMethod(name = "getWorld", global = true)
        public static IsoWorld getWorld() {
            return IsoWorld.instance;
        }

        @LuaMethod(name = "getCell", global = true)
        public static IsoCell getCell() {
            return IsoWorld.instance.getCell();
        }

        @LuaMethod(name = "getCellSizeInChunks", global = true)
        public static Double getCellSizeInChunks() {
            return BoxedStaticValues.toDouble(32.0d);
        }

        @LuaMethod(name = "getCellSizeInSquares", global = true)
        public static Double getCellSizeInSquares() {
            return BoxedStaticValues.toDouble(256.0d);
        }

        @LuaMethod(name = "getChunkSizeInSquares", global = true)
        public static Double getChunkSizeInSquares() {
            return BoxedStaticValues.toDouble(8.0d);
        }

        @LuaMethod(name = "getMinimumWorldLevel", global = true)
        public static Double getMinimumWorldLevel() {
            return BoxedStaticValues.toDouble(-32.0d);
        }

        @LuaMethod(name = "getMaximumWorldLevel", global = true)
        public static Double getMaximumWorldLevel() {
            return BoxedStaticValues.toDouble(31.0d);
        }

        @LuaMethod(name = "getSandboxOptions", global = true)
        public static SandboxOptions getSandboxOptions() {
            return SandboxOptions.instance;
        }

        @LuaMethod(name = "getFileOutput", global = true)
        public static DataOutputStream getFileOutput(String filename) {
            if (StringUtils.containsDoubleDot(filename)) {
                DebugLog.Lua.warn("relative paths not allowed");
                return null;
            }
            String str = (LuaManager.getLuaCacheDir() + File.separator + filename).replace("/", File.separator).replace("\\", File.separator);
            String dir = str.substring(0, str.lastIndexOf(File.separator));
            File f = new File(dir.replace("\\", "/"));
            if (!f.exists()) {
                f.mkdirs();
            }
            File outFile = new File(str);
            try {
                outStream = new FileOutputStream(outFile);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LuaManager.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
            }
            return new DataOutputStream(outStream);
        }

        @LuaMethod(name = "getLastStandPlayersDirectory", global = true)
        public static String getLastStandPlayersDirectory() {
            return "LastStand";
        }

        @LuaMethod(name = "getLastStandPlayerFileNames", global = true)
        public static List<String> getLastStandPlayerFileNames() {
            List<String> result = new ArrayList<>();
            String str = LuaManager.getLuaCacheDir() + File.separator + getLastStandPlayersDirectory();
            File outFile = new File(str.replace("/", File.separator).replace("\\", File.separator));
            if (!outFile.exists()) {
                outFile.mkdir();
            }
            for (File file : outFile.listFiles()) {
                if (!file.isDirectory() && file.getName().endsWith(".txt")) {
                    result.add(getLastStandPlayersDirectory() + File.separator + file.getName());
                }
            }
            return result;
        }

        @Deprecated
        @LuaMethod(name = "getAllSavedPlayers", global = true)
        public static List<BufferedReader> getAllSavedPlayers() throws IOException {
            List<BufferedReader> result = new ArrayList<>();
            String str = LuaManager.getLuaCacheDir() + File.separator + getLastStandPlayersDirectory();
            File outFile = new File(str.replace("/", File.separator).replace("\\", File.separator));
            if (!outFile.exists()) {
                outFile.mkdir();
            }
            for (File file : outFile.listFiles()) {
                result.add(new BufferedReader(new FileReader(file)));
            }
            return result;
        }

        @LuaMethod(name = "getSandboxPresets", global = true)
        public static List<String> getSandboxPresets() {
            List<String> result = new ArrayList<>();
            String str = LuaManager.getSandboxCacheDir();
            File outFile = new File(str);
            if (!outFile.exists()) {
                outFile.mkdir();
            }
            for (File file : outFile.listFiles()) {
                if (file.getName().endsWith(".cfg")) {
                    result.add(file.getName().replace(".cfg", ""));
                }
            }
            Collections.sort(result);
            return result;
        }

        @LuaMethod(name = "deleteSandboxPreset", global = true)
        public static void deleteSandboxPreset(String name) {
            if (StringUtils.containsDoubleDot(name)) {
                DebugLog.Lua.warn("relative paths not allowed");
                return;
            }
            String fileName = LuaManager.getSandboxCacheDir() + File.separator + name + ".cfg";
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
        }

        @LuaMethod(name = "getFileReader", global = true)
        public static BufferedReader getFileReader(String filename, boolean createIfNull) throws IOException {
            if (StringUtils.containsDoubleDot(filename)) {
                DebugLog.Lua.warn("relative paths not allowed");
                return null;
            }
            String str = LuaManager.getLuaCacheDir() + File.separator + filename;
            File outFile = new File(str.replace("/", File.separator).replace("\\", File.separator));
            if (!outFile.exists() && createIfNull) {
                outFile.createNewFile();
            }
            if (outFile.exists()) {
                BufferedReader reader = null;
                try {
                    FileInputStream fis = new FileInputStream(outFile);
                    InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                    reader = new BufferedReader(isr);
                } catch (IOException ex) {
                    Logger.getLogger(LuaManager.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                }
                return reader;
            }
            return null;
        }

        @LuaMethod(name = "getModFileReader", global = true)
        public static BufferedReader getModFileReader(String modId, String filename, boolean createIfNull) throws IOException {
            if (filename.isEmpty() || StringUtils.containsDoubleDot(filename) || new File(filename).isAbsolute()) {
                return null;
            }
            String str = ZomboidFileSystem.instance.getCacheDir() + File.separator + "mods" + File.separator + filename;
            if (modId != null) {
                ChooseGameInfo.Mod mod = ChooseGameInfo.getModDetails(modId);
                if (mod == null) {
                    return null;
                }
                str = mod.getVersionDir() + File.separator + filename;
                File modFile = new File(str);
                if (!modFile.exists()) {
                    str = mod.getCommonDir() + File.separator + filename;
                }
            }
            String str2 = str.replace("/", File.separator).replace("\\", File.separator);
            File outFile = new File(str2);
            if (!outFile.exists() && createIfNull) {
                String dir = str2.substring(0, str2.lastIndexOf(File.separator));
                File f = new File(dir);
                if (!f.exists()) {
                    f.mkdirs();
                }
                outFile.createNewFile();
            }
            if (outFile.exists()) {
                BufferedReader reader = null;
                try {
                    FileInputStream fis = new FileInputStream(outFile);
                    InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                    reader = new BufferedReader(isr);
                } catch (IOException ex) {
                    Logger.getLogger(LuaManager.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                }
                return reader;
            }
            return null;
        }

        @LuaMethod(name = "listFilesInZomboidLuaDirectory", global = true)
        public static ArrayList<String> listFilesInZomboidLuaDirectory(String directory) throws IOException {
            if (directory == null || StringUtils.containsDoubleDot(directory) || new File(directory).isAbsolute()) {
                return null;
            }
            ArrayList<String> result = new ArrayList<>();
            String absPath = ZomboidFileSystem.instance.getCacheDir() + File.separator + "Lua" + File.separator + directory;
            listFilesInDirectoryAux(absPath, result);
            return result;
        }

        @LuaMethod(name = "listFilesInModDirectory", global = true)
        public static ArrayList<String> listFilesInModDirectory(String modID, String directory) throws IOException {
            ChooseGameInfo.Mod mod;
            if (StringUtils.isNullOrWhitespace(modID) || directory == null || StringUtils.containsDoubleDot(directory) || new File(directory).isAbsolute() || (mod = ChooseGameInfo.getModDetails(modID)) == null) {
                return null;
            }
            ArrayList<String> result = new ArrayList<>();
            String absPath = mod.getCommonDir() + File.separator + directory;
            listFilesInDirectoryAux(absPath, result);
            String absPath2 = mod.getVersionDir() + File.separator + directory;
            listFilesInDirectoryAux(absPath2, result);
            return result;
        }

        private static void listFilesInDirectoryAux(String absPath, ArrayList<String> result) throws IOException {
            File[] files = new File(absPath.replace("/", File.separator).replace("\\", File.separator)).listFiles();
            if (files == null) {
                return;
            }
            for (File file : files) {
                if (!file.isDirectory()) {
                    result.add(file.getCanonicalFile().getName());
                }
            }
        }

        public static void refreshAnimSets(boolean reload) {
            if (reload) {
                try {
                    AnimationSet.Reset();
                    for (Asset asset : AnimNodeAssetManager.instance.getAssetTable().values()) {
                        AnimNodeAssetManager.instance.reload(asset);
                    }
                } catch (Exception ex) {
                    ExceptionLogger.logException(ex);
                    return;
                }
            }
            AnimationSet.GetAnimationSet("player", true);
            AnimationSet.GetAnimationSet("player-vehicle", true);
            AnimationSet.GetAnimationSet("zombie", true);
            AnimationSet.GetAnimationSet("zombie-crawler", true);
            for (int i = 0; i < IsoPlayer.numPlayers; i++) {
                IsoPlayer player = IsoPlayer.players[i];
                if (player != null) {
                    player.advancedAnimator.OnAnimDataChanged(reload);
                }
            }
            if (IsoWorld.instance.currentCell != null) {
                for (IsoZombie zombie2 : IsoWorld.instance.currentCell.getZombieList()) {
                    zombie2.advancedAnimator.OnAnimDataChanged(reload);
                }
            }
            for (IsoMovingObject movingObject : IsoWorld.instance.currentCell.getObjectList()) {
                if (movingObject instanceof IsoAnimal) {
                    IsoAnimal animal = (IsoAnimal) movingObject;
                    animal.advancedAnimator.OnAnimDataChanged(reload);
                }
            }
        }

        public static void reloadActionGroups() {
            try {
                ActionGroup.reloadAll();
            } catch (Exception e) {
            }
        }

        @LuaMethod(name = "getModFileWriter", global = true)
        public static LuaFileWriter getModFileWriter(String modId, String filename, boolean createIfNull, boolean append) {
            ChooseGameInfo.Mod mod;
            if (filename.isEmpty() || StringUtils.containsDoubleDot(filename) || new File(filename).isAbsolute() || (mod = ChooseGameInfo.getModDetails(modId)) == null) {
                return null;
            }
            String str = (mod.getCommonDir() + File.separator + filename).replace("/", File.separator).replace("\\", File.separator);
            String dir = str.substring(0, str.lastIndexOf(File.separator));
            File f = new File(dir);
            if (!f.exists()) {
                f.mkdirs();
            }
            File outFile = new File(str);
            if (!outFile.exists() && createIfNull) {
                try {
                    outFile.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(LuaManager.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                }
            }
            PrintWriter output = null;
            try {
                FileOutputStream fos = new FileOutputStream(outFile, append);
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                output = new PrintWriter(osw);
            } catch (IOException ex2) {
                Logger.getLogger(LuaManager.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex2);
            }
            return new LuaFileWriter(output);
        }

        @LuaMethod(name = "updateFire", global = true)
        public static void updateFire() {
            IsoFireManager.Update();
        }

        @LuaMethod(name = "deletePlayerFromDatabase", global = true)
        public static void deletePlayerFromDatabase(String savedir, String player, String world) {
            try {
                ServerWorldDatabase.instance.connect();
                ServerWorldDatabase.instance.removeUser(player, world);
                ServerWorldDatabase.instance.close();
                PlayerDBHelper.removePlayer(ZomboidFileSystem.instance.getSaveDir() + File.separator + savedir, player, world);
            } catch (SQLException e) {
                DebugType.General.printException(e, LogSeverity.Error);
            }
        }

        @LuaMethod(name = "checkPlayerExistsInDatabase", global = true)
        public static boolean checkPlayerExistsInDatabase(String savedir, String player, String world) {
            boolean result = false;
            try {
                ServerWorldDatabase.instance.connect();
                boolean result2 = ServerWorldDatabase.instance.containsUser(player, world);
                ServerWorldDatabase.instance.close();
                result = result2 | PlayerDBHelper.containsNetworkPlayer(ZomboidFileSystem.instance.getSaveDir() + File.separator + savedir, player, world);
            } catch (SQLException e) {
                DebugType.General.printException(e, LogSeverity.Error);
            }
            return result;
        }

        @LuaMethod(name = "deletePlayerSave", global = true)
        public static void deletePlayerSave(String fileName) {
            String str = LuaManager.getLuaCacheDir() + File.separator + getLastStandPlayersDirectory() + File.separator + "player" + fileName + ".txt";
            File playerfile = new File(str.replace("/", File.separator).replace("\\", File.separator));
            playerfile.delete();
        }

        @LuaMethod(name = "getControllerCount", global = true)
        public static int getControllerCount() {
            return GameWindow.GameInput.getControllerCount();
        }

        @LuaMethod(name = "isControllerConnected", global = true)
        public static boolean isControllerConnected(int index) {
            return index >= 0 && index <= GameWindow.GameInput.getControllerCount() && GameWindow.GameInput.getController(index) != null;
        }

        @LuaMethod(name = "getControllerGUID", global = true)
        public static String getControllerGUID(int joypad) {
            Controller controller;
            if (joypad >= 0 && joypad < GameWindow.GameInput.getControllerCount() && (controller = GameWindow.GameInput.getController(joypad)) != null) {
                return controller.getGUID();
            }
            return "???";
        }

        @LuaMethod(name = "getControllerName", global = true)
        public static String getControllerName(int joypad) {
            Controller controller;
            if (joypad >= 0 && joypad < GameWindow.GameInput.getControllerCount() && (controller = GameWindow.GameInput.getController(joypad)) != null) {
                return controller.getGamepadName();
            }
            return "???";
        }

        @LuaMethod(name = "getControllerAxisCount", global = true)
        public static int getControllerAxisCount(int c) {
            Controller controller;
            if (c < 0 || c >= GameWindow.GameInput.getControllerCount() || (controller = GameWindow.GameInput.getController(c)) == null) {
                return 0;
            }
            return controller.getAxisCount();
        }

        @LuaMethod(name = "getControllerAxisValue", global = true)
        public static float getControllerAxisValue(int c, int axis) {
            Controller controller;
            if (c < 0 || c >= GameWindow.GameInput.getControllerCount() || (controller = GameWindow.GameInput.getController(c)) == null || axis < 0 || axis >= controller.getAxisCount()) {
                return 0.0f;
            }
            return controller.getAxisValue(axis);
        }

        @LuaMethod(name = "getControllerDeadZone", global = true)
        public static float getControllerDeadZone(int c, int axis) {
            if (c < 0 || c >= GameWindow.GameInput.getControllerCount() || axis < 0 || axis >= GameWindow.GameInput.getAxisCount(c)) {
                return 0.0f;
            }
            return JoypadManager.instance.getDeadZone(c, axis);
        }

        @LuaMethod(name = "setControllerDeadZone", global = true)
        public static void setControllerDeadZone(int c, int axis, float value) {
            if (c < 0 || c >= GameWindow.GameInput.getControllerCount() || axis < 0 || axis >= GameWindow.GameInput.getAxisCount(c)) {
                return;
            }
            JoypadManager.instance.setDeadZone(c, axis, value);
        }

        @LuaMethod(name = "saveControllerSettings", global = true)
        public static void saveControllerSettings(int c) {
            if (c < 0 || c >= GameWindow.GameInput.getControllerCount()) {
                return;
            }
            JoypadManager.instance.saveControllerSettings(c);
        }

        @LuaMethod(name = "getControllerButtonCount", global = true)
        public static int getControllerButtonCount(int c) {
            Controller controller;
            if (c < 0 || c >= GameWindow.GameInput.getControllerCount() || (controller = GameWindow.GameInput.getController(c)) == null) {
                return 0;
            }
            return controller.getButtonCount();
        }

        @LuaMethod(name = "getControllerPovX", global = true)
        public static float getControllerPovX(int c) {
            Controller controller;
            if (c < 0 || c >= GameWindow.GameInput.getControllerCount() || (controller = GameWindow.GameInput.getController(c)) == null) {
                return 0.0f;
            }
            return controller.getPovX();
        }

        @LuaMethod(name = "getControllerPovY", global = true)
        public static float getControllerPovY(int c) {
            Controller controller;
            if (c < 0 || c >= GameWindow.GameInput.getControllerCount() || (controller = GameWindow.GameInput.getController(c)) == null) {
                return 0.0f;
            }
            return controller.getPovY();
        }

        @LuaMethod(name = "reloadControllerConfigFiles", global = true)
        public static void reloadControllerConfigFiles() {
            JoypadManager.instance.reloadControllerFiles();
        }

        @LuaMethod(name = "isJoypadPressed", global = true)
        public static boolean isJoypadPressed(int joypad, int button) {
            return GameWindow.GameInput.isButtonPressedD(button, joypad);
        }

        @LuaMethod(name = "isJoypadDown", global = true)
        public static boolean isJoypadDown(int joypad) {
            return JoypadManager.instance.isDownPressed(joypad);
        }

        @LuaMethod(name = "isJoypadLTPressed", global = true)
        public static boolean isJoypadLTPressed(int joypad) {
            return JoypadManager.instance.isLTPressed(joypad);
        }

        @LuaMethod(name = "isJoypadRTPressed", global = true)
        public static boolean isJoypadRTPressed(int joypad) {
            return JoypadManager.instance.isRTPressed(joypad);
        }

        @LuaMethod(name = "isJoypadLeftStickButtonPressed", global = true)
        public static boolean isJoypadLeftStickButtonPressed(int joypad) {
            return JoypadManager.instance.isL3Pressed(joypad);
        }

        @LuaMethod(name = "isJoypadRightStickButtonPressed", global = true)
        public static boolean isJoypadRightStickButtonPressed(int joypad) {
            return JoypadManager.instance.isR3Pressed(joypad);
        }

        @LuaMethod(name = "getJoypadAimingAxisX", global = true)
        public static float getJoypadAimingAxisX(int joypad) {
            return JoypadManager.instance.getAimingAxisX(joypad);
        }

        @LuaMethod(name = "getJoypadAimingAxisY", global = true)
        public static float getJoypadAimingAxisY(int joypad) {
            return JoypadManager.instance.getAimingAxisY(joypad);
        }

        @LuaMethod(name = "getJoypadMovementAxisX", global = true)
        public static float getJoypadMovementAxisX(int joypad) {
            return JoypadManager.instance.getMovementAxisX(joypad);
        }

        @LuaMethod(name = "getJoypadMovementAxisY", global = true)
        public static float getJoypadMovementAxisY(int joypad) {
            return JoypadManager.instance.getMovementAxisY(joypad);
        }

        @LuaMethod(name = "getJoypadAButton", global = true)
        public static int getJoypadAButton(int joypad) {
            JoypadManager.Joypad joypadData = JoypadManager.instance.getFromControllerID(joypad);
            if (joypadData != null) {
                return joypadData.getAButton();
            }
            return -1;
        }

        @LuaMethod(name = "getJoypadBButton", global = true)
        public static int getJoypadBButton(int joypad) {
            JoypadManager.Joypad joypadData = JoypadManager.instance.getFromControllerID(joypad);
            if (joypadData != null) {
                return joypadData.getBButton();
            }
            return -1;
        }

        @LuaMethod(name = "getJoypadXButton", global = true)
        public static int getJoypadXButton(int joypad) {
            JoypadManager.Joypad joypadData = JoypadManager.instance.getFromControllerID(joypad);
            if (joypadData != null) {
                return joypadData.getXButton();
            }
            return -1;
        }

        @LuaMethod(name = "getJoypadYButton", global = true)
        public static int getJoypadYButton(int joypad) {
            JoypadManager.Joypad joypadData = JoypadManager.instance.getFromControllerID(joypad);
            if (joypadData != null) {
                return joypadData.getYButton();
            }
            return -1;
        }

        @LuaMethod(name = "getJoypadLBumper", global = true)
        public static int getJoypadLBumper(int joypad) {
            JoypadManager.Joypad joypadData = JoypadManager.instance.getFromControllerID(joypad);
            if (joypadData != null) {
                return joypadData.getLBumper();
            }
            return -1;
        }

        @LuaMethod(name = "getJoypadRBumper", global = true)
        public static int getJoypadRBumper(int joypad) {
            JoypadManager.Joypad joypadData = JoypadManager.instance.getFromControllerID(joypad);
            if (joypadData != null) {
                return joypadData.getRBumper();
            }
            return -1;
        }

        @LuaMethod(name = "getJoypadBackButton", global = true)
        public static int getJoypadBackButton(int joypad) {
            JoypadManager.Joypad joypadData = JoypadManager.instance.getFromControllerID(joypad);
            if (joypadData != null) {
                return joypadData.getBackButton();
            }
            return -1;
        }

        @LuaMethod(name = "getJoypadStartButton", global = true)
        public static int getJoypadStartButton(int joypad) {
            JoypadManager.Joypad joypadData = JoypadManager.instance.getFromControllerID(joypad);
            if (joypadData != null) {
                return joypadData.getStartButton();
            }
            return -1;
        }

        @LuaMethod(name = "getJoypadLeftStickButton", global = true)
        public static int getJoypadLeftStickButton(int joypad) {
            JoypadManager.Joypad joypadData = JoypadManager.instance.getFromControllerID(joypad);
            if (joypadData != null) {
                return joypadData.getL3();
            }
            return -1;
        }

        @LuaMethod(name = "getJoypadRightStickButton", global = true)
        public static int getJoypadRightStickButton(int joypad) {
            JoypadManager.Joypad joypadData = JoypadManager.instance.getFromControllerID(joypad);
            if (joypadData != null) {
                return joypadData.getR3();
            }
            return -1;
        }

        @LuaMethod(name = "wasMouseActiveMoreRecentlyThanJoypad", global = true)
        public static boolean wasMouseActiveMoreRecentlyThanJoypad() {
            if (IsoPlayer.players[0] == null) {
                JoypadManager.Joypad joypad = GameWindow.activatedJoyPad;
                return joypad == null || joypad.isDisabled() || JoypadManager.instance.getLastActivity(joypad.getID()) < Mouse.lastActivity;
            }
            int joypadBind = IsoPlayer.players[0].getJoypadBind();
            return joypadBind == -1 || JoypadManager.instance.getLastActivity(joypadBind) < Mouse.lastActivity;
        }

        @LuaMethod(name = "activateJoypadOnSteamDeck", global = true)
        public static void activateJoypadOnSteamDeck() {
            if (GameWindow.activatedJoyPad == null) {
                JoypadManager.instance.isAPressed(0);
                if (JoypadManager.instance.joypadList.isEmpty()) {
                    return;
                } else {
                    GameWindow.activatedJoyPad = JoypadManager.instance.joypadList.get(0);
                }
            }
            if (IsoPlayer.getInstance() != null) {
                LuaEventManager.triggerEvent("OnJoypadActivate", Integer.valueOf(GameWindow.activatedJoyPad.getID()));
            } else {
                LuaEventManager.triggerEvent("OnJoypadActivateUI", Integer.valueOf(GameWindow.activatedJoyPad.getID()));
            }
        }

        @LuaMethod(name = "reactivateJoypadAfterResetLua", global = true)
        public static boolean reactivateJoypadAfterResetLua() {
            if (GameWindow.activatedJoyPad != null) {
                LuaEventManager.triggerEvent("OnJoypadActivateUI", Integer.valueOf(GameWindow.activatedJoyPad.getID()));
                return true;
            }
            return false;
        }

        @LuaMethod(name = "isJoypadConnected", global = true)
        public static boolean isJoypadConnected(int index) {
            return JoypadManager.instance.isJoypadConnected(index);
        }

        private static void addPlayerToWorld(int player, IsoPlayer playerObj, boolean newPlayer) {
            if (IsoPlayer.players[player] != null) {
                IsoPlayer.players[player].getEmitter().stopAll();
                IsoPlayer.players[player].getEmitter().unregister();
                IsoPlayer.players[player].updateUsername();
                IsoPlayer.players[player].setSceneCulled(true);
                IsoPlayer.players[player] = null;
            }
            playerObj.playerIndex = player;
            if (GameClient.client && player != 0 && playerObj.serverPlayerIndex != 1) {
                ClientPlayerDB.getInstance().forgetPlayer(playerObj.serverPlayerIndex);
            }
            if (GameClient.client && player != 0 && playerObj.serverPlayerIndex == 1) {
                playerObj.serverPlayerIndex = ClientPlayerDB.getInstance().getNextServerPlayerIndex();
            }
            if (player == 0) {
                playerObj.sqlId = 1;
            }
            if (newPlayer) {
                playerObj.applyTraits(IsoWorld.instance.getLuaTraits());
                CharacterProfessionDefinition characterProfessionDefinition = CharacterProfessionDefinition.getCharacterProfessionDefinition(playerObj.getDescriptor().getCharacterProfession());
                for (String recipe : characterProfessionDefinition.getGrantedRecipes()) {
                    playerObj.getKnownRecipes().add(recipe);
                }
                List<CharacterTrait> characterTraits = IsoWorld.instance.getLuaTraits();
                for (CharacterTrait characterTrait : characterTraits) {
                    CharacterTraitDefinition trait = CharacterTraitDefinition.getCharacterTraitDefinition(characterTrait);
                    if (trait != null && !trait.getGrantedRecipes().isEmpty()) {
                        for (String recipe2 : trait.getGrantedRecipes()) {
                            playerObj.getKnownRecipes().add(recipe2);
                        }
                    }
                }
                playerObj.setDir(IsoDirections.SE);
                LuaEventManager.triggerEvent("OnNewGame", playerObj, playerObj.getCurrentSquare());
            }
            IsoPlayer.numPlayers = Math.max(IsoPlayer.numPlayers, player + 1);
            IsoWorld.instance.addCoopPlayers.add(new AddCoopPlayer(playerObj, newPlayer));
            if (player == 0) {
                IsoPlayer.setInstance(playerObj);
            }
        }

        @LuaMethod(name = "toInt", global = true)
        public static int toInt(double val) {
            return PZMath.fastfloor(val);
        }

        @LuaMethod(name = "getClientUsername", global = true)
        public static String getClientUsername() {
            if (GameClient.client) {
                return GameClient.username;
            }
            return null;
        }

        @LuaMethod(name = "setPlayerJoypad", global = true)
        public static void setPlayerJoypad(int player, int joypad, IsoPlayer playerObj, String username, boolean allowNewPlayer) {
            if (allowNewPlayer && (IsoPlayer.players[player] == null || IsoPlayer.players[player].isDead())) {
                boolean newPlayer = playerObj == null;
                if (playerObj == null) {
                    IsoPlayer oldInst = IsoPlayer.getInstance();
                    IsoWorld world = IsoWorld.instance;
                    int x = world.getLuaPosX();
                    int y = world.getLuaPosY();
                    int z = world.getLuaPosZ();
                    DebugLog.Lua.debugln("coop player spawning at " + x + "," + y + "," + z);
                    playerObj = new IsoPlayer(world.currentCell, GameClient.client ? null : world.getLuaPlayerDesc(), x, y, z);
                    IsoPlayer.setInstance(oldInst);
                    world.currentCell.getAddList().remove(playerObj);
                    world.currentCell.getObjectList().remove(playerObj);
                    playerObj.saveFileName = IsoPlayer.getUniqueFileName();
                }
                if (GameClient.client) {
                    if (username != null) {
                        if (!$assertionsDisabled && player == 0) {
                            throw new AssertionError();
                        }
                        playerObj.username = username;
                        playerObj.getModData().rawset("username", username);
                    } else {
                        if (!$assertionsDisabled && player != 0) {
                            throw new AssertionError();
                        }
                        playerObj.username = GameClient.username;
                    }
                }
                addPlayerToWorld(player, playerObj, newPlayer);
            }
            if (playerObj != null) {
                playerObj.setJoypadBind(joypad);
                JoypadManager.instance.assignJoypad(joypad, player);
            }
        }

        @LuaMethod(name = "setPlayerMouse", global = true)
        public static void setPlayerMouse(IsoPlayer playerObj) {
            boolean newPlayer = playerObj == null;
            if (playerObj == null) {
                IsoPlayer oldInst = IsoPlayer.getInstance();
                IsoWorld world = IsoWorld.instance;
                int x = world.getLuaPosX();
                int y = world.getLuaPosY();
                int z = world.getLuaPosZ();
                DebugLog.Lua.debugln("coop player spawning at " + x + "," + y + "," + z);
                playerObj = new IsoPlayer(world.currentCell, GameClient.client ? null : world.getLuaPlayerDesc(), x, y, z);
                IsoPlayer.setInstance(oldInst);
                world.currentCell.getAddList().remove(playerObj);
                world.currentCell.getObjectList().remove(playerObj);
                playerObj.saveFileName = null;
            }
            if (GameClient.client) {
                playerObj.username = GameClient.username;
            }
            addPlayerToWorld(0, playerObj, newPlayer);
        }

        @LuaMethod(name = "revertToKeyboardAndMouse", global = true)
        public static void revertToKeyboardAndMouse() {
            JoypadManager.instance.revertToKeyboardAndMouse();
        }

        @LuaMethod(name = "revertToKeyboardAndMouseFromMainMenu", global = true)
        public static void revertToKeyboardAndMouseFromMainMenu() {
            JoypadManager.instance.revertToKeyboardAndMouseFromMainMenu();
        }

        @LuaMethod(name = "isJoypadUp", global = true)
        public static boolean isJoypadUp(int joypad) {
            return JoypadManager.instance.isUpPressed(joypad);
        }

        @LuaMethod(name = "isJoypadLeft", global = true)
        public static boolean isJoypadLeft(int joypad) {
            return JoypadManager.instance.isLeftPressed(joypad);
        }

        @LuaMethod(name = "isJoypadRight", global = true)
        public static boolean isJoypadRight(int joypad) {
            return JoypadManager.instance.isRightPressed(joypad);
        }

        @LuaMethod(name = "isJoypadLBPressed", global = true)
        public static boolean isJoypadLBPressed(int joypad) {
            return JoypadManager.instance.isLBPressed(joypad);
        }

        @LuaMethod(name = "isJoypadRBPressed", global = true)
        public static boolean isJoypadRBPressed(int joypad) {
            return JoypadManager.instance.isRBPressed(joypad);
        }

        @LuaMethod(name = "getButtonCount", global = true)
        public static int getButtonCount(int joypad) {
            Controller controller;
            if (joypad < 0 || joypad >= GameWindow.GameInput.getControllerCount() || (controller = GameWindow.GameInput.getController(joypad)) == null) {
                return 0;
            }
            return controller.getButtonCount();
        }

        @LuaMethod(name = "setDebugToggleControllerPluggedIn", global = true)
        public static void setDebugToggleControllerPluggedIn(int index) {
            Controllers.setDebugToggleControllerPluggedIn(index);
        }

        @LuaMethod(name = "lineSeparator", global = true)
        public static String lineSeparator() {
            return System.lineSeparator();
        }

        @LuaMethod(name = "getFileWriter", global = true)
        public static LuaFileWriter getFileWriter(String filename, boolean createIfNull, boolean append) {
            if (StringUtils.containsDoubleDot(filename)) {
                DebugLog.Lua.warn("relative paths not allowed");
                return null;
            }
            String str = (LuaManager.getLuaCacheDir() + File.separator + filename).replace("/", File.separator).replace("\\", File.separator);
            String dir = str.substring(0, str.lastIndexOf(File.separator));
            File f = new File(dir.replace("\\", "/"));
            if (!f.exists()) {
                f.mkdirs();
            }
            File outFile = new File(str);
            if (!outFile.exists() && createIfNull) {
                try {
                    outFile.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(LuaManager.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                }
            }
            PrintWriter output = null;
            try {
                FileOutputStream fos = new FileOutputStream(outFile, append);
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                output = new PrintWriter(osw);
            } catch (IOException ex2) {
                Logger.getLogger(LuaManager.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex2);
            }
            return new LuaFileWriter(output);
        }

        @LuaMethod(name = "getSandboxFileWriter", global = true)
        public static LuaFileWriter getSandboxFileWriter(String filename, boolean createIfNull, boolean append) {
            if (StringUtils.containsDoubleDot(filename)) {
                DebugLog.Lua.warn("relative paths not allowed");
                return null;
            }
            String str = (LuaManager.getSandboxCacheDir() + File.separator + filename).replace("/", File.separator).replace("\\", File.separator);
            String dir = str.substring(0, str.lastIndexOf(File.separator));
            File f = new File(dir.replace("\\", "/"));
            if (!f.exists()) {
                f.mkdirs();
            }
            File outFile = new File(str);
            if (!outFile.exists() && createIfNull) {
                try {
                    outFile.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(LuaManager.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                }
            }
            PrintWriter output = null;
            try {
                FileOutputStream fos = new FileOutputStream(outFile, append);
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                output = new PrintWriter(osw);
            } catch (IOException ex2) {
                Logger.getLogger(LuaManager.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex2);
            }
            return new LuaFileWriter(output);
        }

        @LuaMethod(name = "createStory", global = true)
        public static void createStory(String storyName) {
            Core.getInstance().setGameMode(storyName);
            String str = ZomboidFileSystem.instance.getGameModeCacheDir();
            String str2 = str.replace("/", File.separator).replace("\\", File.separator);
            int gameCount = 1;
            boolean find = false;
            while (!find) {
                File f = new File(str2 + File.separator + "Game" + gameCount);
                if (!f.exists()) {
                    find = true;
                } else {
                    gameCount++;
                }
            }
            Core.gameSaveWorld = "newstory";
        }

        @LuaMethod(name = "createWorld", global = true)
        public static void createWorld(String worldName) {
            if (worldName == null || worldName.isEmpty()) {
                worldName = "blah";
            }
            String worldName2 = sanitizeWorldName(worldName);
            String str = (ZomboidFileSystem.instance.getGameModeCacheDir() + File.separator + worldName2 + File.separator).replace("/", File.separator).replace("\\", File.separator);
            String dir = str.substring(0, str.lastIndexOf(File.separator)).replace("\\", "/");
            File f = new File(dir);
            if (!f.exists() && !Core.getInstance().isNoSave()) {
                f.mkdirs();
            }
            if (!Core.getInstance().isNoSave()) {
                SavefileNaming.ensureSubdirectoriesExist(dir);
            }
            Core.gameSaveWorld = worldName2;
        }

        @LuaMethod(name = "sanitizeWorldName", global = true)
        public static String sanitizeWorldName(String worldName) {
            return worldName.replace(org.apache.commons.lang3.StringUtils.SPACE, IsoRegions.FILE_SEP).replace("/", "").replace("\\", "").replace("?", "").replace(Marker.ANY_MARKER, "").replace("<", "").replace(">", "").replace(":", "").replace("|", "").trim();
        }

        @LuaMethod(name = "forceChangeState", global = true)
        public static void forceChangeState(GameState state) {
            GameWindow.states.forceNextState(state);
        }

        @LuaMethod(name = "endFileOutput", global = true)
        public static void endFileOutput() {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(LuaManager.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                }
            }
            outStream = null;
        }

        @LuaMethod(name = "getFileInput", global = true)
        public static DataInputStream getFileInput(String filename) {
            if (StringUtils.containsDoubleDot(filename)) {
                DebugLog.Lua.warn("relative paths not allowed");
                return null;
            }
            String str = LuaManager.getLuaCacheDir() + File.separator + filename;
            File outFile = new File(str.replace("/", File.separator).replace("\\", File.separator));
            if (outFile.exists()) {
                try {
                    inStream = new FileInputStream(outFile);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LuaManager.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                }
                return new DataInputStream(inStream);
            }
            return null;
        }

        @LuaMethod(name = "getGameFilesInput", global = true)
        public static DataInputStream getGameFilesInput(String filename) {
            String str = filename.replace("/", File.separator).replace("\\", File.separator);
            if (!ZomboidFileSystem.instance.isKnownFile(str)) {
                return null;
            }
            File outFile = new File(ZomboidFileSystem.instance.getString(str));
            if (outFile.exists()) {
                try {
                    inStream = new FileInputStream(outFile);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LuaManager.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                }
                return new DataInputStream(inStream);
            }
            return null;
        }

        @LuaMethod(name = "getGameFilesTextInput", global = true)
        public static BufferedReader getGameFilesTextInput(String filename) {
            if (!Core.getInstance().getDebug()) {
                return null;
            }
            String str = filename.replace("/", File.separator).replace("\\", File.separator);
            if (!ZomboidFileSystem.instance.isKnownFile(str)) {
                return null;
            }
            File inFile = new File(ZomboidFileSystem.instance.getString(str));
            if (inFile.exists()) {
                try {
                    inFileReader = new FileReader(inFile);
                    inBufferedReader = new BufferedReader(inFileReader);
                    return inBufferedReader;
                } catch (FileNotFoundException ex) {
                    ExceptionLogger.logException(ex);
                    return null;
                }
            }
            return null;
        }

        @LuaMethod(name = "endTextFileInput", global = true)
        public static void endTextFileInput() {
            if (inBufferedReader != null) {
                try {
                    inBufferedReader.close();
                    inFileReader.close();
                } catch (IOException ex) {
                    Logger.getLogger(LuaManager.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                }
            }
            inBufferedReader = null;
            inFileReader = null;
        }

        @LuaMethod(name = "endFileInput", global = true)
        public static void endFileInput() {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(LuaManager.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                }
            }
            inStream = null;
        }

        @LuaMethod(name = "getLineNumber", global = true)
        public static int getLineNumber(LuaCallFrame c) {
            if (c.closure == null) {
                return 0;
            }
            int line = c.pc;
            if (line < 0) {
                line = 0;
            }
            if (line >= c.closure.prototype.lines.length) {
                line = c.closure.prototype.lines.length - 1;
            }
            return c.closure.prototype.lines[line];
        }

        @LuaMethod(name = "ZombRand", global = true)
        public static double ZombRand(double max) {
            if (max == Const.default_value_double) {
                return Const.default_value_double;
            }
            if (max < Const.default_value_double) {
                return -RandLua.INSTANCE.Next(-((long) max));
            }
            return RandLua.INSTANCE.Next((long) max);
        }

        @LuaMethod(name = "ZombRandBetween", global = true)
        public static double ZombRandBetween(double min, double max) {
            return RandLua.INSTANCE.Next((long) min, (long) max);
        }

        @LuaMethod(name = "ZombRand", global = true)
        public static double ZombRand(double min, double max) {
            return RandLua.INSTANCE.Next((int) min, (int) max);
        }

        @LuaMethod(name = "ZombRandFloat", global = true)
        public static float ZombRandFloat(float min, float max) {
            return RandLua.INSTANCE.Next(min, max);
        }

        @LuaMethod(name = "getShortenedFilename", global = true)
        public static String getShortenedFilename(String str) {
            return str.substring(str.indexOf("lua/") + 4);
        }

        @LuaMethod(name = "isKeyDown", global = true)
        public static boolean isKeyDown(int key) {
            return GameKeyboard.isKeyDown(key);
        }

        @LuaMethod(name = "isKeyDown", global = true)
        public static boolean isKeyDown(String keyName) {
            return GameKeyboard.isKeyDown(keyName);
        }

        @LuaMethod(name = "wasKeyDown", global = true)
        public static boolean wasKeyDown(int key) {
            return GameKeyboard.wasKeyDown(key);
        }

        @LuaMethod(name = "wasKeyDown", global = true)
        public static boolean wasKeyDown(String keyName) {
            return GameKeyboard.wasKeyDown(keyName);
        }

        @LuaMethod(name = "isKeyPressed", global = true)
        public static boolean isKeyPressed(int key) {
            return GameKeyboard.isKeyPressed(key);
        }

        @LuaMethod(name = "isKeyPressed", global = true)
        public static boolean isKeyPressed(String keyName) {
            return GameKeyboard.isKeyPressed(keyName);
        }

        @LuaMethod(name = "getBaseSoundBank", global = true)
        public static BaseSoundBank getBaseSoundBank() {
            return BaseSoundBank.instance;
        }

        @LuaMethod(name = "getFMODSoundBank", global = true)
        public static BaseSoundBank getFMODSoundBank() {
            return FMODSoundBank.instance;
        }

        @LuaMethod(name = "isSoundPlaying", global = true)
        public static boolean isSoundPlaying(Object sound) {
            if (sound instanceof Double) {
                Double d = (Double) sound;
                return FMODManager.instance.isPlaying(d.longValue());
            }
            return false;
        }

        @LuaMethod(name = "stopSound", global = true)
        public static void stopSound(long sound) {
            FMODManager.instance.stopSound(sound);
        }

        @LuaMethod(name = "isShiftKeyDown", global = true)
        public static boolean isShiftKeyDown() {
            return GameKeyboard.isKeyDown(42) || GameKeyboard.isKeyDown(54);
        }

        @LuaMethod(name = "isCtrlKeyDown", global = true)
        public static boolean isCtrlKeyDown() {
            return GameKeyboard.isKeyDown(29) || GameKeyboard.isKeyDown(157);
        }

        @LuaMethod(name = "isAltKeyDown", global = true)
        public static boolean isAltKeyDown() {
            return GameKeyboard.isKeyDown(56) || GameKeyboard.isKeyDown(184);
        }

        @LuaMethod(name = "isMetaKeyDown", global = true)
        public static boolean isMetaKeyDown() {
            return GameKeyboard.isKeyDown(219) || GameKeyboard.isKeyDown(220);
        }

        @LuaMethod(name = "setZoomLevels", global = true)
        public static void setZoomLevels(Double... zooms) {
            Core.getInstance().offscreenBuffer.setZoomLevels(zooms);
        }

        @LuaMethod(name = "getCore", global = true)
        public static Core getCore() {
            return Core.getInstance();
        }

        @LuaMethod(name = "getGameVersion", global = true)
        public static String getGameVersion() {
            return Core.getInstance().getGameVersion().toString();
        }

        @LuaMethod(name = "getBreakModGameVersion", global = true)
        public static GameVersion getBreakModGameVersion() {
            return Core.getInstance().getBreakModGameVersion();
        }

        @LuaMethod(name = "getSquare", global = true)
        public static IsoGridSquare getSquare(double x, double y, double z) {
            return IsoCell.getInstance().getGridSquare(x, y, z);
        }

        @LuaMethod(name = "getDebugOptions", global = true)
        public static DebugOptions getDebugOptions() {
            return DebugOptions.instance;
        }

        @LuaMethod(name = "setShowPausedMessage", global = true)
        public static void setShowPausedMessage(boolean b) {
            DebugType.ExitDebug.debugln("setShowPausedMessage 1");
            UIManager.setShowPausedMessage(b);
            DebugType.ExitDebug.debugln("setShowPausedMessage 2");
        }

        @LuaMethod(name = "getFilenameOfCallframe", global = true)
        public static String getFilenameOfCallframe(LuaCallFrame c) {
            if (c.closure == null) {
                return null;
            }
            return c.closure.prototype.filename;
        }

        @LuaMethod(name = "getFilenameOfClosure", global = true)
        public static String getFilenameOfClosure(LuaClosure c) {
            if (c == null) {
                return null;
            }
            return c.prototype.filename;
        }

        @LuaMethod(name = "getFirstLineOfClosure", global = true)
        public static int getFirstLineOfClosure(LuaClosure c) {
            if (c == null) {
                return 0;
            }
            return c.prototype.lines[0];
        }

        @LuaMethod(name = "getLocalVarCount", global = true)
        public static int getLocalVarCount(Coroutine c) {
            LuaCallFrame f = c.currentCallFrame();
            if (f == null) {
                return 0;
            }
            return f.getLocalVarCount();
        }

        @LuaMethod(name = "getLocalVarCount", global = true)
        public static int getLocalVarCount(LuaCallFrame callFrame) {
            return callFrame.getLocalVarCount();
        }

        @LuaMethod(name = "isSystemLinux", global = true)
        public static boolean isSystemLinux() {
            return (isSystemMacOS() || isSystemWindows()) ? false : true;
        }

        @LuaMethod(name = "isSystemMacOS", global = true)
        public static boolean isSystemMacOS() {
            return System.getProperty(SystemProperties.OS_NAME).contains("OS X");
        }

        @LuaMethod(name = "isSystemWindows", global = true)
        public static boolean isSystemWindows() {
            return System.getProperty(SystemProperties.OS_NAME).startsWith("Win");
        }

        @LuaMethod(name = "isModActive", global = true)
        public static boolean isModActive(ChooseGameInfo.Mod mod) {
            String modID = mod.getDir();
            if (!StringUtils.isNullOrWhitespace(mod.getId())) {
                modID = mod.getId();
            }
            return ZomboidFileSystem.instance.getModIDs().contains(modID);
        }

        @LuaMethod(name = "openUrl", global = true)
        public static void openURl(String url) {
            if (!LuaManager.isIndieStoneUrl(url)) {
                return;
            }
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    URI uri = new URI(url);
                    desktop.browse(uri);
                    return;
                } catch (Exception e) {
                    ExceptionLogger.logException(e);
                    return;
                }
            }
            DesktopBrowser.openURL(url);
        }

        @LuaMethod(name = "isDesktopOpenSupported", global = true)
        public static boolean isDesktopOpenSupported() {
            if (!Desktop.isDesktopSupported()) {
                return false;
            }
            return Desktop.getDesktop().isSupported(Desktop.Action.OPEN);
        }

        @LuaMethod(name = "showFolderInDesktop", global = true)
        public static void showFolderInDesktop(String folder) {
            File file = new File(folder);
            if (!file.exists() || !file.isDirectory()) {
                return;
            }
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.OPEN)) {
                try {
                    desktop.open(file);
                } catch (Exception e) {
                    ExceptionLogger.logException(e);
                }
            }
        }

        @LuaMethod(name = "getActivatedMods", global = true)
        public static ArrayList<String> getActivatedMods() {
            return ZomboidFileSystem.instance.getModIDs();
        }

        @LuaMethod(name = "toggleModActive", global = true)
        public static void toggleModActive(ChooseGameInfo.Mod mod, boolean active) {
            String modID = mod.getDir();
            if (!StringUtils.isNullOrWhitespace(mod.getId())) {
                modID = mod.getId();
            }
            ActiveMods.getById("default").setModActive(modID, active);
        }

        @LuaMethod(name = "saveModsFile", global = true)
        public static void saveModsFile() {
            ZomboidFileSystem.instance.saveModsFile();
        }

        private static void deleteSavefileFilesMatching(File folder, String regex) {
            DirectoryStream.Filter<Path> filter = entry -> {
                return entry.getFileName().toString().matches(regex);
            };
            try {
                DirectoryStream<Path> dstrm = Files.newDirectoryStream(folder.toPath(), filter);
                try {
                    for (Path path : dstrm) {
                        System.out.println("DELETE " + String.valueOf(path));
                        Files.deleteIfExists(path);
                    }
                    if (dstrm != null) {
                        dstrm.close();
                    }
                } finally {
                }
            } catch (Exception ex) {
                ExceptionLogger.logException(ex);
            }
        }

        private static void deleteSavefileFilesMatchingInSubdirectories(File folder, String regex) {
            DirectoryStream.Filter<Path> filter = entry -> {
                return entry.getFileName().toString().matches(regex);
            };
            try {
                DirectoryStream<Path> outerStream = Files.newDirectoryStream(folder.toPath(), (DirectoryStream.Filter<? super Path>) x$0 -> {
                    return Files.isDirectory(x$0, new LinkOption[0]);
                });
                try {
                    for (Path outerPath : outerStream) {
                        DirectoryStream<Path> dstrm = Files.newDirectoryStream(outerPath, filter);
                        try {
                            for (Path path : dstrm) {
                                System.out.println("DELETE " + String.valueOf(path));
                                Files.deleteIfExists(path);
                            }
                            if (dstrm != null) {
                                dstrm.close();
                            }
                        } catch (Throwable th) {
                            if (dstrm != null) {
                                try {
                                    dstrm.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            }
                            throw th;
                        }
                    }
                    if (outerStream != null) {
                        outerStream.close();
                    }
                } finally {
                }
            } catch (Exception ex) {
                ExceptionLogger.logException(ex);
            }
        }

        @LuaMethod(name = "manipulateSavefile", global = true)
        public static void manipulateSavefile(String folder, String action) {
            if (StringUtils.isNullOrWhitespace(folder) || StringUtils.containsDoubleDot(folder)) {
                return;
            }
            String absolutePath = ZomboidFileSystem.instance.getSaveDir() + File.separator + folder;
            File file = new File(absolutePath);
            if (!file.exists() || !file.isDirectory()) {
                return;
            }
            switch (action) {
                case "DeleteAPopXYBin":
                    deleteSavefileFilesMatching(new File(file, SavefileNaming.SUBDIR_APOP), "apop_-?[0-9]+_-?[0-9]+\\.bin");
                    return;
                case "DeleteChunkDataXYBin":
                    deleteSavefileFilesMatching(new File(file, SavefileNaming.SUBDIR_CHUNKDATA), "chunkdata_-?[0-9]+_-?[0-9]+\\.bin");
                    return;
                case "DeleteEntityDataBin":
                    deleteSavefileFilesMatching(file, "entity_data.bin");
                    return;
                case "DeleteMapXYBin":
                    deleteSavefileFilesMatchingInSubdirectories(new File(file, SavefileNaming.SUBDIR_MAP), "-?[0-9]+\\.bin");
                    return;
                case "DeleteMapAnimalsBin":
                    deleteSavefileFilesMatching(file, "map_animals\\.bin");
                    return;
                case "DeleteMapBasementsBin":
                    deleteSavefileFilesMatching(file, "map_basements\\.bin");
                    return;
                case "DeleteMapMetaBin":
                    deleteSavefileFilesMatching(file, "map_meta\\.bin");
                    return;
                case "DeleteMapTBin":
                    deleteSavefileFilesMatching(file, "map_t\\.bin");
                    return;
                case "DeleteMapZoneBin":
                    deleteSavefileFilesMatching(file, "map_zone\\.bin");
                    return;
                case "DeletePlayersDB":
                    deleteSavefileFilesMatching(file, "players\\.db");
                    return;
                case "DeletePlayerBuildingsBin":
                    deleteSavefileFilesMatching(file, "player_buildings\\.bin");
                    return;
                case "DeleteReanimatedBin":
                    deleteSavefileFilesMatching(file, "reanimated\\.bin");
                    return;
                case "DeleteVehiclesDB":
                    deleteSavefileFilesMatching(file, "vehicles\\.db");
                    return;
                case "DeleteZOutfitsBin":
                    deleteSavefileFilesMatching(file, "z_outfits\\.bin");
                    return;
                case "DeleteZPopVirtualBin":
                    deleteSavefileFilesMatching(new File(file, SavefileNaming.SUBDIR_ZPOP), "zpop_virtual\\.bin");
                    return;
                case "DeleteZPopXYBin":
                    deleteSavefileFilesMatching(new File(file, SavefileNaming.SUBDIR_ZPOP), "zpop_[0-9]+_[0-9]+\\.bin");
                    return;
                case "WriteModsDotTxt":
                    ActiveMods activeMods = ActiveMods.getById("currentGame");
                    ActiveModsFile activeModsFile = new ActiveModsFile();
                    activeModsFile.write(absolutePath + File.separator + "mods.txt", activeMods);
                    return;
                default:
                    throw new IllegalArgumentException("unknown action \"" + action + "\"");
            }
        }

        @LuaMethod(name = "getLocalVarName", global = true)
        public static String getLocalVarName(Coroutine c, int n) {
            LuaCallFrame f = c.currentCallFrame();
            return f.getLocalVarName(n);
        }

        @LuaMethod(name = "getLocalVarName", global = true)
        public static String getLocalVarName(LuaCallFrame callFrame, int n) {
            return callFrame.getLocalVarName(n);
        }

        @LuaMethod(name = "getLocalVarStack", global = true)
        public static int getLocalVarStack(Coroutine c, int n) {
            LuaCallFrame f = c.currentCallFrame();
            return f.getLocalVarStackIndex(n);
        }

        @LuaMethod(name = "getLocalVarStackIndex", global = true)
        public static int getLocalVarStackIndex(LuaCallFrame callFrame, int n) {
            return callFrame.getLocalVarStackIndex(n);
        }

        @LuaMethod(name = "getCallframeTop", global = true)
        public static int getCallframeTop(Coroutine c) {
            return c.getCallframeTop();
        }

        @LuaMethod(name = "getCoroutineTop", global = true)
        public static int getCoroutineTop(Coroutine c) {
            return c.getTop();
        }

        @LuaMethod(name = "getCoroutineObjStack", global = true)
        public static Object getCoroutineObjStack(Coroutine c, int n) {
            return c.getObjectFromStack(n);
        }

        @LuaMethod(name = "getCoroutineObjStackWithBase", global = true)
        public static Object getCoroutineObjStackWithBase(Coroutine c, int n) {
            return c.getObjectFromStack(n - c.currentCallFrame().localBase);
        }

        @LuaMethod(name = "localVarName", global = true)
        public static String localVarName(Coroutine c, int n) {
            return "";
        }

        @LuaMethod(name = "getCoroutineCallframeStack", global = true)
        public static LuaCallFrame getCoroutineCallframeStack(Coroutine c, int n) {
            return c.getCallFrame(n);
        }

        @LuaMethod(name = "getLuaStackTrace", global = true)
        public static ArrayList<String> getLuaStackTrace() {
            ArrayList<String> result = new ArrayList<>();
            Collections.addAll(result, LuaManager.getLuaStackTraceStrings(null));
            return result;
        }

        @LuaMethod(name = "createTile", global = true)
        public static void createTile(String tile, IsoGridSquare square) {
            synchronized (IsoSpriteManager.instance.namedMap) {
                IsoSprite spr = IsoSpriteManager.instance.namedMap.get(tile);
                if (spr == null) {
                    return;
                }
                int x = 0;
                int y = 0;
                int z = 0;
                if (square != null) {
                    x = square.getX();
                    y = square.getY();
                    z = square.getZ();
                }
                CellLoader.DoTileObjectCreation(spr, spr.getTileType(), square, IsoWorld.instance.currentCell, x, y, z, tile);
                if (square != null) {
                    square.invalidateRenderChunkLevel(64L);
                }
            }
        }

        @LuaMethod(name = "getNumClassFunctions", global = true)
        public static int getNumClassFunctions(Object o) {
            if (!Core.debug) {
                throw new IllegalStateException("Not in debug");
            }
            return o.getClass().getDeclaredMethods().length;
        }

        @LuaMethod(name = "getClassFunction", global = true)
        public static Method getClassFunction(Object o, int i) {
            if (!Core.debug) {
                throw new IllegalStateException("Not in debug");
            }
            return o.getClass().getDeclaredMethods()[i];
        }

        @LuaMethod(name = "getNumClassFields", global = true)
        public static int getNumClassFields(Object o) {
            if (!Core.debug) {
                throw new IllegalStateException("Not in debug");
            }
            return o.getClass().getDeclaredFields().length;
        }

        @LuaMethod(name = "getClassField", global = true)
        public static Field getClassField(Object o, int i) {
            if (!Core.debug) {
                throw new IllegalStateException("Not in debug");
            }
            Field m = o.getClass().getDeclaredFields()[i];
            try {
                m.setAccessible(true);
            } catch (InaccessibleObjectException e) {
            }
            return m;
        }

        @LuaMethod(name = "getDirectionTo", global = true)
        public static IsoDirections getDirectionTo(IsoGameCharacter chara, IsoObject objTarget) {
            return IsoDirections.fromAngle(objTarget.getX() - chara.getX(), objTarget.getY() - chara.getY());
        }

        @LuaMethod(name = "translatePointXInOverheadMapToWindow", global = true)
        public static float translatePointXInOverheadMapToWindow(float x, UIElement ui, float zoom, float xpos) {
            IngameState.draww = ui.getWidth().intValue();
            return IngameState.translatePointX(x, xpos, zoom, 0.0f);
        }

        @LuaMethod(name = "translatePointYInOverheadMapToWindow", global = true)
        public static float translatePointYInOverheadMapToWindow(float y, UIElement ui, float zoom, float ypos) {
            IngameState.drawh = ui.getHeight().intValue();
            return IngameState.translatePointY(y, ypos, zoom, 0.0f);
        }

        @LuaMethod(name = "translatePointXInOverheadMapToWorld", global = true)
        public static float translatePointXInOverheadMapToWorld(float x, UIElement ui, float zoom, float xpos) {
            IngameState.draww = ui.getWidth().intValue();
            return IngameState.invTranslatePointX(x, xpos, zoom, 0.0f);
        }

        @LuaMethod(name = "translatePointYInOverheadMapToWorld", global = true)
        public static float translatePointYInOverheadMapToWorld(float y, UIElement ui, float zoom, float ypos) {
            IngameState.drawh = ui.getHeight().intValue();
            return IngameState.invTranslatePointY(y, ypos, zoom, 0.0f);
        }

        @LuaMethod(name = "drawOverheadMap", global = true)
        public static void drawOverheadMap(UIElement ui, int level, float zoom, float xpos, float ypos) {
            IngameState.renderDebugOverhead2(getCell(), level, zoom, ui.getAbsoluteX().intValue(), ui.getAbsoluteY().intValue(), xpos, ypos, ui.getWidth().intValue(), ui.getHeight().intValue());
        }

        @LuaMethod(name = "assaultPlayer", global = true)
        public static void assaultPlayer() {
            if (!$assertionsDisabled) {
                throw new AssertionError();
            }
        }

        @LuaMethod(name = "isoRegionsRenderer", global = true)
        public static IsoRegionsRenderer isoRegionsRenderer() {
            return new IsoRegionsRenderer();
        }

        @LuaMethod(name = "zpopNewRenderer", global = true)
        public static ZombiePopulationRenderer zpopNewRenderer() {
            return new ZombiePopulationRenderer();
        }

        @LuaMethod(name = "zpopSpawnTimeToZero", global = true)
        public static void zpopSpawnTimeToZero(int cellX, int cellY) {
            ZombiePopulationManager.instance.dbgSpawnTimeToZero(cellX, cellY);
        }

        @LuaMethod(name = "zpopClearZombies", global = true)
        public static void zpopClearZombies(int cellX, int cellY) {
            ZombiePopulationManager.instance.dbgClearZombies(cellX, cellY);
        }

        @LuaMethod(name = "zpopSpawnNow", global = true)
        public static void zpopSpawnNow(int cellX, int cellY) {
            ZombiePopulationManager.instance.dbgSpawnNow(cellX, cellY);
        }

        @LuaMethod(name = "addVirtualZombie", global = true)
        public static void addVirtualZombie(int x, int y) {
        }

        @LuaMethod(name = "luaDebug", global = true)
        public static void luaDebug() {
            try {
                throw new Exception("LuaDebug");
            } catch (Exception e) {
                DebugType.General.printException(e, LogSeverity.Error);
            }
        }

        @LuaMethod(name = "setAggroTarget", global = true)
        public static void setAggroTarget(int id, int x, int y) {
            ZombiePopulationManager.instance.setAggroTarget(id, x, y);
        }

        @LuaMethod(name = "debugFullyStreamedIn", global = true)
        public static void debugFullyStreamedIn(int x, int y) {
            IngameState.instance.debugFullyStreamedIn(x, y);
        }

        @LuaMethod(name = "getClassFieldVal", global = true)
        public static Object getClassFieldVal(Object o, Field field) {
            if (!Core.debug) {
                throw new IllegalStateException("Not in debug");
            }
            try {
                return field.get(o);
            } catch (Exception e) {
                return "<private>";
            }
        }

        @LuaMethod(name = "getMethodParameter", global = true)
        public static String getMethodParameter(Method o, int i) {
            if (!Core.debug) {
                throw new IllegalStateException("Not in debug");
            }
            return o.getParameterTypes()[i].getSimpleName();
        }

        @LuaMethod(name = "getMethodParameterCount", global = true)
        public static int getMethodParameterCount(Method o) {
            if (!Core.debug) {
                throw new IllegalStateException("Not in debug");
            }
            return o.getParameterTypes().length;
        }

        @LuaMethod(name = "breakpoint", global = true)
        public static void breakpoint() {
        }

        @LuaMethod(name = "getLuaDebuggerErrorCount", global = true)
        public static int getLuaDebuggerErrorCount() {
            return KahluaThread.errorCount;
        }

        @LuaMethod(name = "getLuaDebuggerErrors", global = true)
        public static ArrayList<String> getLuaDebuggerErrors() {
            return new ArrayList<>(KahluaThread.m_errors_list);
        }

        @LuaMethod(name = "doLuaDebuggerAction", global = true)
        public static void doLuaDebuggerAction(String action) {
            UIManager.luaDebuggerAction = action;
        }

        @LuaMethod(name = "isQuitCooldown", global = true)
        public static boolean isQuitCooldown() {
            return SafetySystemManager.getCooldown(GameClient.connection) > 0.0f;
        }

        @LuaMethod(name = "getGameSpeed", global = true)
        public static int getGameSpeed() {
            if (UIManager.getSpeedControls() != null) {
                return UIManager.getSpeedControls().getCurrentGameSpeed();
            }
            return 0;
        }

        @LuaMethod(name = "setGameSpeed", global = true)
        public static void setGameSpeed(int newSpeed) {
            DebugType.ExitDebug.debugln("setGameSpeed 1");
            if (UIManager.getSpeedControls() == null) {
                DebugType.ExitDebug.debugln("setGameSpeed 2");
            } else {
                UIManager.getSpeedControls().SetCurrentGameSpeed(newSpeed);
                DebugType.ExitDebug.debugln("setGameSpeed 3");
            }
        }

        @LuaMethod(name = "stepForward", global = true)
        public static void stepForward() {
            if (UIManager.getSpeedControls() == null) {
                return;
            }
            UIManager.getSpeedControls().stepForward();
        }

        @LuaMethod(name = "isGamePaused", global = true)
        public static boolean isGamePaused() {
            return GameTime.isGamePaused();
        }

        @LuaMethod(name = "getMouseXScaled", global = true)
        public static int getMouseXScaled() {
            return Mouse.getX();
        }

        @LuaMethod(name = "getMouseYScaled", global = true)
        public static int getMouseYScaled() {
            return Mouse.getY();
        }

        @LuaMethod(name = "getMouseX", global = true)
        public static int getMouseX() {
            return Mouse.getXA();
        }

        @LuaMethod(name = "setMouseXY", global = true)
        public static void setMouseXY(int x, int y) {
            Mouse.setXY(x, y);
        }

        @LuaMethod(name = "isMouseButtonDown", global = true)
        public static boolean isMouseButtonDown(int number) {
            return Mouse.isButtonDown(number);
        }

        @LuaMethod(name = "isMouseButtonPressed", global = true)
        public static boolean isMouseButtonPressed(int number) {
            return Mouse.isButtonPressed(number);
        }

        @LuaMethod(name = "getMouseY", global = true)
        public static int getMouseY() {
            return Mouse.getYA();
        }

        @LuaMethod(name = "getSoundManager", global = true)
        public static BaseSoundManager getSoundManager() {
            return SoundManager.instance;
        }

        @LuaMethod(name = "getLastPlayedDate", global = true)
        public static String getLastPlayedDate(String filename) {
            File file = new File(ZomboidFileSystem.instance.getSaveDir() + File.separator + filename);
            if (!file.exists()) {
                return Translator.getText("UI_LastPlayed") + "???";
            }
            Date d = new Date(file.lastModified());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String dateString = sdf.format(d);
            return Translator.getText("UI_LastPlayed") + dateString;
        }

        @LuaMethod(name = "getTextureFromSaveDir", global = true)
        public static Texture getTextureFromSaveDir(String filename, String saveName) {
            if (StringUtils.containsDoubleDot(filename) || StringUtils.containsDoubleDot(saveName)) {
                DebugLog.Lua.warn("relative paths not allowed");
                return null;
            }
            TextureID.useFiltering = true;
            String path = ZomboidFileSystem.instance.getSaveDirSub(saveName + File.separator + filename);
            Texture tex = Texture.getSharedTexture(path);
            TextureID.useFiltering = false;
            return tex;
        }

        @LuaMethod(name = "getSaveInfo", global = true)
        public static KahluaTable getSaveInfo(String saveDir) {
            if (!saveDir.contains(File.separator)) {
                saveDir = IsoWorld.instance.getGameMode() + File.separator + saveDir;
            }
            KahluaTable kahluaTableNewTable = LuaManager.platform.newTable();
            File file = new File(ZomboidFileSystem.instance.getSaveDir() + File.separator + saveDir);
            if (file.exists()) {
                Date d = new Date(file.lastModified());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String dateString = sdf.format(d);
                kahluaTableNewTable.rawset("lastPlayed", dateString);
                String[] pathComponents = saveDir.split(Pattern.quote(File.separator));
                kahluaTableNewTable.rawset("saveDir", saveDir);
                kahluaTableNewTable.rawset("saveName", file.getName());
                kahluaTableNewTable.rawset("gameMode", pathComponents[pathComponents.length - 2]);
            }
            File file2 = new File(ZomboidFileSystem.instance.getSaveDir() + File.separator + saveDir + File.separator + "map_ver.bin");
            if (file2.exists()) {
                try {
                    FileInputStream inStream2 = new FileInputStream(file2);
                    try {
                        DataInputStream input = new DataInputStream(inStream2);
                        try {
                            int worldVersion = input.readInt();
                            kahluaTableNewTable.rawset("worldVersion", Double.valueOf(worldVersion));
                            try {
                                String mapName = GameWindow.ReadString(input);
                                if (mapName.equals("DEFAULT")) {
                                    mapName = "Muldraugh, KY";
                                }
                                kahluaTableNewTable.rawset("mapName", mapName);
                            } catch (Exception e) {
                            }
                            if (worldVersion < 244) {
                                try {
                                    String difficulty = GameWindow.ReadString(input);
                                    kahluaTableNewTable.rawset("difficulty", difficulty);
                                } catch (Exception e2) {
                                }
                                input.close();
                                inStream2.close();
                            } else {
                                input.close();
                                inStream2.close();
                            }
                        } catch (Throwable th) {
                            try {
                                input.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    } finally {
                    }
                } catch (Exception ex) {
                    ExceptionLogger.logException(ex);
                }
            }
            String path = ZomboidFileSystem.instance.getSaveDir() + File.separator + saveDir + File.separator + "mods.txt";
            ActiveMods activeMods = new ActiveMods(saveDir);
            ActiveModsFile activeModsFile = new ActiveModsFile();
            if (activeModsFile.read(path, activeMods)) {
                kahluaTableNewTable.rawset("activeMods", activeMods);
            }
            String absolutePath = ZomboidFileSystem.instance.getSaveDir() + File.separator + saveDir;
            kahluaTableNewTable.rawset("playerAlive", Boolean.valueOf(PlayerDBHelper.isPlayerAlive(absolutePath, 1)));
            KahluaTable playersTable = LuaManager.platform.newTable();
            try {
                ArrayList<Object> players = PlayerDBHelper.getPlayers(absolutePath);
                for (int i = 0; i < players.size(); i += 3) {
                    Double sqlID = (Double) players.get(i);
                    String playerName = (String) players.get(i + 1);
                    Boolean isDead = (Boolean) players.get(i + 2);
                    KahluaTable playerTable = LuaManager.platform.newTable();
                    playerTable.rawset("sqlID", sqlID);
                    playerTable.rawset("name", playerName);
                    playerTable.rawset("isDead", isDead);
                    playersTable.rawset((i / 3) + 1, playerTable);
                }
            } catch (Exception ex2) {
                ExceptionLogger.logException(ex2);
            }
            kahluaTableNewTable.rawset("players", playersTable);
            return kahluaTableNewTable;
        }

        @LuaMethod(name = "renameSavefile", global = true)
        public static boolean renameSaveFile(String gameMode, String oldName, String newName) {
            if (gameMode == null || gameMode.contains("/") || gameMode.contains("\\") || gameMode.contains(File.separator) || StringUtils.containsDoubleDot(gameMode) || oldName == null || oldName.contains("/") || oldName.contains("\\") || oldName.contains(File.separator) || StringUtils.containsDoubleDot(oldName) || newName == null || newName.contains("/") || newName.contains("\\") || newName.contains(File.separator) || StringUtils.containsDoubleDot(newName)) {
                return false;
            }
            String worldName = sanitizeWorldName(newName);
            if (!worldName.equals(newName) || worldName.startsWith(".") || worldName.endsWith(".") || !new File(ZomboidFileSystem.instance.getSaveDirSub(gameMode)).exists()) {
                return false;
            }
            Path oldPath = FileSystems.getDefault().getPath(ZomboidFileSystem.instance.getSaveDirSub(gameMode + File.separator + oldName), new String[0]);
            Path newPath = FileSystems.getDefault().getPath(ZomboidFileSystem.instance.getSaveDirSub(gameMode + File.separator + worldName), new String[0]);
            try {
                Files.move(oldPath, newPath, new CopyOption[0]);
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        @LuaMethod(name = "setSavefilePlayer1", global = true)
        public static void setSavefilePlayer1(String gameMode, String saveDir, int sqlID) {
            String absolutePath = ZomboidFileSystem.instance.getSaveDirSub(gameMode + File.separator + saveDir);
            try {
                PlayerDBHelper.setPlayer1(absolutePath, sqlID);
            } catch (Exception ex) {
                ExceptionLogger.logException(ex);
            }
        }

        @LuaMethod(name = "getServerSavedWorldVersion", global = true)
        public static int getServerSavedWorldVersion(String saveFolder) {
            File inFile = new File(ZomboidFileSystem.instance.getSaveDir() + File.separator + saveFolder + File.separator + "map_t.bin");
            if (inFile.exists()) {
                try {
                    FileInputStream inStream2 = new FileInputStream(inFile);
                    try {
                        DataInputStream input = new DataInputStream(inStream2);
                        try {
                            byte b1 = input.readByte();
                            byte b2 = input.readByte();
                            byte b3 = input.readByte();
                            byte b4 = input.readByte();
                            if (b1 == 71 && b2 == 77 && b3 == 84 && b4 == 77) {
                                int i = input.readInt();
                                input.close();
                                inStream2.close();
                                return i;
                            }
                            input.close();
                            inStream2.close();
                            return 1;
                        } catch (Throwable th) {
                            try {
                                input.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    } finally {
                    }
                } catch (Exception ex) {
                    DebugType.General.printException(ex, LogSeverity.Error);
                    return 0;
                }
            }
            return 0;
        }

        @LuaMethod(name = "getZombieInfo", global = true)
        public static KahluaTable getZombieInfo(IsoZombie zombie2) {
            KahluaTable result = LuaManager.platform.newTable();
            if (zombie2 == null) {
                return result;
            }
            result.rawset("OnlineID", Short.valueOf(zombie2.onlineId));
            result.rawset("RealX", Float.valueOf(zombie2.realx));
            result.rawset("RealY", Float.valueOf(zombie2.realy));
            result.rawset("X", Float.valueOf(zombie2.getX()));
            result.rawset("Y", Float.valueOf(zombie2.getY()));
            result.rawset("TargetX", Float.valueOf(zombie2.networkAi.targetX));
            result.rawset("TargetY", Float.valueOf(zombie2.networkAi.targetY));
            result.rawset("PathLength", Float.valueOf(zombie2.getPathFindBehavior2().getPathLength()));
            result.rawset("TargetLength", Double.valueOf(Math.sqrt(((zombie2.getX() - zombie2.getPathFindBehavior2().getTargetX()) * (zombie2.getX() - zombie2.getPathFindBehavior2().getTargetX())) + ((zombie2.getY() - zombie2.getPathFindBehavior2().getTargetY()) * (zombie2.getY() - zombie2.getPathFindBehavior2().getTargetY())))));
            result.rawset("clientActionState", zombie2.getActionStateName());
            result.rawset("clientAnimationState", zombie2.getAnimationStateName());
            result.rawset("finderProgress", zombie2.getFinder().progress.name());
            result.rawset("usePathFind", Boolean.toString(zombie2.networkAi.usePathFind));
            result.rawset("owner", zombie2.getOwner().getUserName());
            zombie2.networkAi.debugInterfaceActive = true;
            return result;
        }

        @LuaMethod(name = "getPlayerInfo", global = true)
        public static KahluaTable getPlayerInfo(IsoPlayer player) {
            KahluaTable result = LuaManager.platform.newTable();
            if (player == null) {
                return result;
            }
            long t = GameTime.getServerTime() / 1000000;
            result.rawset("OnlineID", Short.valueOf(player.onlineId));
            result.rawset("RealX", Float.valueOf(player.realx));
            result.rawset("RealY", Float.valueOf(player.realy));
            result.rawset("X", Float.valueOf(player.getX()));
            result.rawset("Y", Float.valueOf(player.getY()));
            result.rawset("TargetX", Float.valueOf(player.networkAi.targetX));
            result.rawset("TargetY", Float.valueOf(player.networkAi.targetY));
            result.rawset("TargetT", Integer.valueOf(player.networkAi.targetZ));
            result.rawset("ServerT", Long.valueOf(t));
            result.rawset("PathLength", Float.valueOf(player.getPathFindBehavior2().getPathLength()));
            result.rawset("TargetLength", Double.valueOf(Math.sqrt(((player.getX() - player.getPathFindBehavior2().getTargetX()) * (player.getX() - player.getPathFindBehavior2().getTargetX())) + ((player.getY() - player.getPathFindBehavior2().getTargetY()) * (player.getY() - player.getPathFindBehavior2().getTargetY())))));
            result.rawset("clientActionState", player.getActionStateName());
            result.rawset("clientAnimationState", player.getAnimationStateName());
            result.rawset("finderProgress", player.getFinder().progress.name());
            result.rawset("usePathFind", Boolean.toString(player.networkAi.usePathFind));
            return result;
        }

        @LuaMethod(name = "getMapInfo", global = true)
        public static KahluaTable getMapInfo(String mapDir) {
            if (mapDir.contains(";")) {
                mapDir = mapDir.split(";")[0];
            }
            ChooseGameInfo.Map map = ChooseGameInfo.getMapDetails(mapDir);
            if (map == null) {
                return null;
            }
            KahluaTable table = LuaManager.platform.newTable();
            table.rawset(WorldObjectContextMenuConstant.DESCRIPTION, map.getDescription());
            table.rawset("dir", map.getDirectory());
            KahluaTable table2 = LuaManager.platform.newTable();
            for (String lot : map.getLotDirectories()) {
                table2.rawset(Double.valueOf(1.0d), lot);
            }
            table.rawset("lots", table2);
            table.rawset("thumb", map.getThumbnail());
            table.rawset("title", map.getTitle());
            table.rawset("worldmap", map.getWorldmap());
            table.rawset("spawnSelectImagePyramid", map.getSpawnSelectImagePyramid());
            table.rawset("zoomX", BoxedStaticValues.toDouble(map.getZoomX()));
            table.rawset("zoomY", BoxedStaticValues.toDouble(map.getZoomY()));
            table.rawset("zoomS", BoxedStaticValues.toDouble(map.getZoomS()));
            table.rawset("demoVideo", map.getDemoVideo());
            return table;
        }

        @LuaMethod(name = "getVehicleInfo", global = true)
        public static KahluaTable getVehicleInfo(BaseVehicle vehicle) {
            if (vehicle == null) {
                return null;
            }
            KahluaTable table = LuaManager.platform.newTable();
            table.rawset("name", vehicle.getScript().getName());
            table.rawset("weight", Float.valueOf(vehicle.getMass()));
            table.rawset("speed", Float.valueOf(vehicle.getMaxSpeed()));
            table.rawset("frontEndDurability", Integer.toString(vehicle.frontEndDurability));
            table.rawset("rearEndDurability", Integer.toString(vehicle.rearEndDurability));
            table.rawset("currentFrontEndDurability", Integer.toString(vehicle.currentFrontEndDurability));
            table.rawset("currentRearEndDurability", Integer.toString(vehicle.currentRearEndDurability));
            table.rawset("engine_running", Boolean.valueOf(vehicle.isEngineRunning()));
            table.rawset("engine_started", Boolean.valueOf(vehicle.isEngineStarted()));
            table.rawset("engine_quality", Integer.valueOf(vehicle.getEngineQuality()));
            table.rawset("engine_loudness", Integer.valueOf(vehicle.getEngineLoudness()));
            table.rawset("engine_power", Integer.valueOf(vehicle.getEnginePower()));
            table.rawset("battery_isset", Boolean.valueOf(vehicle.getBattery() != null));
            table.rawset("battery_charge", Float.valueOf(vehicle.getBatteryCharge()));
            table.rawset("gas_amount", Float.valueOf(vehicle.getPartById("GasTank").getContainerContentAmount()));
            table.rawset("gas_capacity", Integer.valueOf(vehicle.getPartById("GasTank").getContainerCapacity()));
            VehiclePart doorFrontLeft = vehicle.getPartById("DoorFrontLeft");
            table.rawset("doorleft_exist", Boolean.valueOf(doorFrontLeft != null));
            if (doorFrontLeft != null) {
                table.rawset("doorleft_open", Boolean.valueOf(doorFrontLeft.getDoor().isOpen()));
                table.rawset("doorleft_locked", Boolean.valueOf(doorFrontLeft.getDoor().isLocked()));
                table.rawset("doorleft_lockbroken", Boolean.valueOf(doorFrontLeft.getDoor().isLockBroken()));
                VehicleWindow windowFrontLeft = doorFrontLeft.findWindow();
                table.rawset("windowleft_exist", Boolean.valueOf(windowFrontLeft != null));
                if (windowFrontLeft != null) {
                    table.rawset("windowleft_open", Boolean.valueOf(windowFrontLeft.isOpen()));
                    table.rawset("windowleft_health", Integer.valueOf(windowFrontLeft.getHealth()));
                }
            }
            VehiclePart doorFrontRight = vehicle.getPartById("DoorFrontRight");
            table.rawset("doorright_exist", Boolean.valueOf(doorFrontRight != null));
            if (doorFrontLeft != null) {
                table.rawset("doorright_open", Boolean.valueOf(doorFrontRight.getDoor().isOpen()));
                table.rawset("doorright_locked", Boolean.valueOf(doorFrontRight.getDoor().isLocked()));
                table.rawset("doorright_lockbroken", Boolean.valueOf(doorFrontRight.getDoor().isLockBroken()));
                VehicleWindow windowFrontRight = doorFrontRight.findWindow();
                table.rawset("windowright_exist", Boolean.valueOf(windowFrontRight != null));
                if (windowFrontRight != null) {
                    table.rawset("windowright_open", Boolean.valueOf(windowFrontRight.isOpen()));
                    table.rawset("windowright_health", Integer.valueOf(windowFrontRight.getHealth()));
                }
            }
            table.rawset("headlights_set", Boolean.valueOf(vehicle.hasHeadlights()));
            table.rawset("headlights_on", Boolean.valueOf(vehicle.getHeadlightsOn()));
            if (vehicle.getPartById("Heater") != null) {
                table.rawset((Object) "heater_isset", (Object) true);
                Object active = vehicle.getPartById("Heater").getModData().rawget("active");
                if (active == null) {
                    table.rawset((Object) "heater_on", (Object) false);
                } else {
                    table.rawset("heater_on", Boolean.valueOf(active == Boolean.TRUE));
                }
            } else {
                table.rawset((Object) "heater_isset", (Object) false);
            }
            return table;
        }

        @LuaMethod(name = "getLotDirectories", global = true)
        public static ArrayList<String> getLotDirectories() {
            return IsoWorld.instance.metaGrid.getLotDirectories();
        }

        @LuaMethod(name = "useTextureFiltering", global = true)
        public static void useTextureFiltering(boolean bUse) {
            TextureID.useFiltering = bUse;
        }

        @LuaMethod(name = "getTexture", global = true)
        public static Texture getTexture(String filename) {
            return Texture.getSharedTexture(filename);
        }

        @LuaMethod(name = "tryGetTexture", global = true)
        public static Texture tryGetTexture(String filename) {
            return Texture.trygetTexture(filename);
        }

        @LuaMethod(name = "sendSecretKey", global = true)
        public static void sendSecretKey(String username, String pwd, String ip, int port, String serverPassword, boolean doHash, int authType, String secretKey) {
            ConnectionManager.getInstance().sendSecretKey(username, pwd, ip, port, serverPassword, doHash, authType, secretKey);
        }

        @LuaMethod(name = "stopSendSecretKey", global = true)
        public static void stopSendSecretKey() {
            GameClient.sendQR = false;
        }

        @LuaMethod(name = "generateSecretKey", global = true)
        public static String generateSecretKey() {
            return GameClient.instance.generateSecretKey();
        }

        @LuaMethod(name = "sendGoogleAuth", global = true)
        public static void sendGoogleAuth(String username, String code) {
            INetworkPacket.send(PacketTypes.PacketType.GoogleAuth, username, code);
        }

        @LuaMethod(name = "createQRCodeTex", global = true)
        public static Texture createQRCodeTex(String user, String key) throws IOException, WriterException {
            String barCodeData = GameClient.instance.getQR(user, key);
            BitMatrix matrix = new MultiFormatWriter().encode(barCodeData, BarcodeFormat.QR_CODE, 180, 180);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
            BufferedImage finalImage = new BufferedImage(180, 180, 1);
            Graphics2D g = (Graphics2D) finalImage.getGraphics();
            g.drawImage(image, 0, 0, (ImageObserver) null);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(finalImage, "PNG", os);
            byte[] bytes = os.toByteArray();
            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            try {
                BufferedInputStream bufferedIs = new BufferedInputStream(is, bytes.length);
                Texture tex = new Texture("QRCode", bufferedIs, false);
                return tex;
            } catch (Exception e) {
                DebugLog.General.println("Texture creation failed!");
                return null;
            }
        }

        @LuaMethod(name = "getVideo", global = true)
        public static VideoTexture getVideo(String filename, int width, int height) {
            VideoTexture videoTexture = VideoTexture.getOrCreate(filename, width, height);
            if (videoTexture != null) {
                LuaManager.videoTextures.put(filename, videoTexture);
            }
            return videoTexture;
        }

        @LuaMethod(name = "getTextManager", global = true)
        public static TextManager getTextManager() {
            return TextManager.instance;
        }

        @LuaMethod(name = "setProgressBarValue", global = true)
        public static void setProgressBarValue(IsoPlayer player, int value) {
            if (player.isLocalPlayer()) {
                UIManager.getProgressBar(player.getIndex()).setValue(value);
            }
        }

        @LuaMethod(name = "getText", global = true)
        public static String getText(String txt) {
            return Translator.getText(txt);
        }

        @LuaMethod(name = "getText", global = true)
        public static String getText(String txt, Object arg1) {
            return Translator.getText(txt, arg1);
        }

        @LuaMethod(name = "getText", global = true)
        public static String getText(String txt, Object arg1, Object arg2) {
            return Translator.getText(txt, arg1, arg2);
        }

        @LuaMethod(name = "getText", global = true)
        public static String getText(String txt, Object arg1, Object arg2, Object arg3) {
            return Translator.getText(txt, arg1, arg2, arg3);
        }

        @LuaMethod(name = "getText", global = true)
        public static String getText(String txt, Object arg1, Object arg2, Object arg3, Object arg4) {
            return Translator.getText(txt, arg1, arg2, arg3, arg4);
        }

        @LuaMethod(name = "getTextOrNull", global = true)
        public static String getTextOrNull(String txt) {
            return Translator.getTextOrNull(txt);
        }

        @LuaMethod(name = "getTextOrNull", global = true)
        public static String getTextOrNull(String txt, Object arg1) {
            return Translator.getTextOrNull(txt, arg1);
        }

        @LuaMethod(name = "getTextOrNull", global = true)
        public static String getTextOrNull(String txt, Object arg1, Object arg2) {
            return Translator.getTextOrNull(txt, arg1, arg2);
        }

        @LuaMethod(name = "getTextOrNull", global = true)
        public static String getTextOrNull(String txt, Object arg1, Object arg2, Object arg3) {
            return Translator.getTextOrNull(txt, arg1, arg2, arg3);
        }

        @LuaMethod(name = "getTextOrNull", global = true)
        public static String getTextOrNull(String txt, Object arg1, Object arg2, Object arg3, Object arg4) {
            return Translator.getTextOrNull(txt, arg1, arg2, arg3, arg4);
        }

        @LuaMethod(name = "getItemText", global = true)
        public static String getItemText(String txt) {
            return Translator.getDisplayItemName(txt);
        }

        @LuaMethod(name = "getRadioText", global = true)
        public static String getRadioText(String txt) {
            return Translator.getRadioText(txt);
        }

        @LuaMethod(name = "getTextMediaEN", global = true)
        public static String getTextMediaEN(String txt) {
            return Translator.getTextMediaEN(txt);
        }

        @LuaMethod(name = "getItemNameFromFullType", global = true)
        public static String getItemNameFromFullType(String fullType) {
            if (DebugOptions.instance.asset.checkItemTexAndNames.getValue()) {
                return "ItemNameFromFullType";
            }
            return Translator.getItemNameFromFullType(fullType);
        }

        @LuaMethod(name = "getItem", global = true)
        public static Item getItem(String itemType) {
            return InventoryItemFactory.getItem(itemType, true);
        }

        @LuaMethod(name = "getItemStaticModel", global = true)
        public static String getItemStaticModel(String itemType) {
            Item item = getItem(itemType);
            if (item == null) {
                return null;
            }
            return item.getStaticModel();
        }

        @LuaMethod(name = "isItemFood", global = true)
        public static boolean isItemFood(String itemType) {
            Item item = getItem(itemType);
            return item != null && item.isItemType(ItemType.FOOD);
        }

        @LuaMethod(name = "getItemFoodType", global = true)
        public static String getItemFoodType(String itemType) {
            if (DebugOptions.instance.asset.checkItemTexAndNames.getValue()) {
                return "ItemFoodType";
            }
            Item item = getItem(itemType);
            if (item != null) {
                return item.foodType;
            }
            return null;
        }

        @LuaMethod(name = "isItemFresh", global = true)
        public static boolean isItemFresh(String itemType, float age) {
            Item item;
            return (DebugOptions.instance.asset.checkItemTexAndNames.getValue() || (item = getItem(itemType)) == null || age >= ((float) item.daysFresh)) ? false : true;
        }

        @LuaMethod(name = "getItemCount", global = true)
        public static int getItemCount(String itemType) {
            if (DebugOptions.instance.asset.checkItemTexAndNames.getValue()) {
                return 101;
            }
            Item item = getItem(itemType);
            if (item != null) {
                return item.getCount();
            }
            return 0;
        }

        @LuaMethod(name = "getItemWeight", global = true)
        public static float getItemWeight(String itemType) {
            if (DebugOptions.instance.asset.checkItemTexAndNames.getValue()) {
                return 101.0f;
            }
            Item item = getItem(itemType);
            if (item != null) {
                return item.isItemType(ItemType.WEAPON) ? item.getWeaponWeight() : item.getActualWeight();
            }
            return 0.0f;
        }

        @LuaMethod(name = "getItemActualWeight", global = true)
        public static float getItemActualWeight(String itemType) {
            if (DebugOptions.instance.asset.checkItemTexAndNames.getValue()) {
                return 101.0f;
            }
            Item item = getItem(itemType);
            if (item != null) {
                return item.getActualWeight();
            }
            return 0.0f;
        }

        @LuaMethod(name = "getItemConditionMax", global = true)
        public static int getItemConditionMax(String itemType) {
            Item item = getItem(itemType);
            if (item != null) {
                return item.getConditionMax();
            }
            return 0;
        }

        @LuaMethod(name = "getItemEvolvedRecipeName", global = true)
        public static String getItemEvolvedRecipeName(String itemType) {
            Item item = getItem(itemType);
            if (item != null) {
                return item.evolvedRecipeName;
            }
            return null;
        }

        @LuaMethod(name = "hasItemTag", global = true)
        public static boolean hasItemTag(String itemType, ItemTag itemTag) {
            Item item = getItem(itemType);
            if (item != null) {
                return item.hasTag(itemTag);
            }
            return false;
        }

        @LuaMethod(name = "getItemDisplayName", global = true)
        public static String getItemDisplayName(String itemType) {
            if (DebugOptions.instance.asset.checkItemTexAndNames.getValue()) {
                return "ItemDisplayName";
            }
            Item item = getItem(itemType);
            if (item != null) {
                return item.getDisplayName();
            }
            return itemType;
        }

        @LuaMethod(name = "getItemName", global = true)
        public static String getItemName(String itemType) {
            if (DebugOptions.instance.asset.checkItemTexAndNames.getValue()) {
                return "ItemName";
            }
            Item item = getItem(itemType);
            if (item != null) {
                String displayName = item.getDisplayName();
                if (item.vehicleType > 0) {
                    return Translator.getText("IGUI_ItemNameMechanicalType", displayName, Translator.getText("IGUI_VehicleType_" + item.vehicleType));
                }
                return displayName;
            }
            return itemType;
        }

        @LuaMethod(name = "getItemTextureName", global = true)
        public static String getItemTextureName(String itemType) {
            if (DebugOptions.instance.asset.checkItemTexAndNames.getValue()) {
                return "ItemTextureName";
            }
            Texture inventoryItemTexture = getItemTex(itemType);
            if (inventoryItemTexture != null) {
                return inventoryItemTexture.getName();
            }
            return null;
        }

        private static String getItemTextureColor(Item item, String param) {
            if (DebugOptions.instance.asset.checkItemTexAndNames.getValue()) {
                return "ItemTextureColor";
            }
            String color = "";
            if (!item.getPaletteChoices().isEmpty() || param != null) {
                String palette = item.getPaletteChoices().get(Rand.Next(item.getPaletteChoices().size()));
                if (param != null) {
                    palette = param;
                }
                color = "_" + palette.replace(item.getPalettesStart(), "");
            }
            return color;
        }

        @LuaMethod(name = "getAndFindNearestTracks", global = true)
        public static ArrayList<AnimalTracks> getAndFindNearestTracks(IsoGameCharacter chr) {
            if (GameClient.client) {
                GameClient.instance.sendGetAnimalTracks(chr);
                return null;
            }
            if (!GameServer.server) {
                return AnimalTracks.getAndFindNearestTracks(chr);
            }
            return null;
        }

        @LuaMethod(name = "getItemTex", global = true)
        public static Texture getItemTex(String itemType) {
            if (DebugOptions.instance.asset.checkItemTexAndNames.getValue()) {
                return Texture.trygetTexture("media/textures/Foraging/question_mark.png");
            }
            Item item = getItem(itemType);
            if (item != null) {
                Texture texture = null;
                if (item.isItemType(ItemType.ALARM_CLOCK) || item.isItemType(ItemType.ANIMAL) || item.isItemType(ItemType.DRAINABLE) || item.isItemType(ItemType.FOOD) || item.isItemType(ItemType.LITERATURE) || item.isItemType(ItemType.MAP) || item.isItemType(ItemType.MOVEABLE) || item.isItemType(ItemType.NORMAL) || item.isItemType(ItemType.WEAPON)) {
                    texture = item.normalTexture;
                } else if (item.isItemType(ItemType.ALARM_CLOCK_CLOTHING) || item.isItemType(ItemType.CLOTHING)) {
                    texture = Texture.trygetTexture("Item_" + item.getIcon().replace(".png", "") + getItemTextureColor(item, null));
                } else if (item.isItemType(ItemType.CONTAINER) || item.isItemType(ItemType.KEY) || item.isItemType(ItemType.RADIO) || item.isItemType(ItemType.WEAPON_PART)) {
                    texture = Texture.trygetTexture("Item_" + item.getIcon());
                }
                return texture;
            }
            return null;
        }

        @LuaMethod(name = "getRecipeDisplayName", global = true)
        public static String getRecipeDisplayName(String name) {
            return Translator.getRecipeName(name);
        }

        @LuaMethod(name = "getMyDocumentFolder", global = true)
        public static String getMyDocumentFolder() {
            return Core.getMyDocumentFolder();
        }

        @LuaMethod(name = "getSpriteManager", global = true)
        public static IsoSpriteManager getSpriteManager(String sprite) {
            return IsoSpriteManager.instance;
        }

        @LuaMethod(name = "getSprite", global = true)
        public static IsoSprite getSprite(String sprite) {
            return IsoSpriteManager.instance.getSprite(sprite);
        }

        @LuaMethod(name = "getServerModData", global = true)
        public static void getServerModData() {
            INetworkPacket.send(PacketTypes.PacketType.GetModData, new Object[0]);
        }

        @LuaMethod(name = "isXBOXController", global = true)
        public static boolean isXBOXController() {
            for (int m = 0; m < GameWindow.GameInput.getControllerCount(); m++) {
                Controller controller = GameWindow.GameInput.getController(m);
                if (controller != null && controller.getGamepadName().contains("XBOX 360")) {
                    return true;
                }
            }
            return false;
        }

        @LuaMethod(name = "isPlaystationController", global = true)
        public static boolean isPlaystationController(int id) {
            Controller controller = GameWindow.GameInput.getController(id);
            if (controller == null) {
                return false;
            }
            String joystickName = controller.getJoystickName();
            return joystickName.contains("Playstation") || joystickName.contains("Dualshock");
        }

        @LuaMethod(name = "sendClientCommand", global = true)
        public static void sendClientCommand(String module, String command, KahluaTable args) {
            if (GameClient.client && GameClient.ingame) {
                GameClient.instance.sendClientCommand(null, module, command, args);
            } else {
                if (GameServer.server) {
                    throw new IllegalStateException("can't call this function on the server");
                }
                SinglePlayerClient.sendClientCommand(null, module, command, args);
            }
        }

        @LuaMethod(name = "sendClientCommand", global = true)
        public static void sendClientCommand(IsoPlayer player, String module, String command, KahluaTable args) {
            if (GameServer.server) {
                LuaEventManager.triggerEvent("OnClientCommand", module, command, player, args);
                return;
            }
            if (player == null || !player.isLocalPlayer()) {
                return;
            }
            if (GameClient.client && GameClient.ingame) {
                GameClient.instance.sendClientCommand(player, module, command, args);
            } else {
                if (GameServer.server) {
                    throw new IllegalStateException("can't call this function on the server");
                }
                SinglePlayerClient.sendClientCommand(player, module, command, args);
            }
        }

        @LuaMethod(name = "sendServerCommand", global = true)
        public static void sendServerCommand(String module, String command, KahluaTable args) {
            if (GameServer.server) {
                GameServer.sendServerCommand(module, command, args);
            }
        }

        @LuaMethod(name = "sendServerCommand", global = true)
        public static void sendServerCommand(IsoPlayer player, String module, String command, KahluaTable args) {
            if (GameServer.server) {
                GameServer.sendServerCommand(player, module, command, args);
            }
        }

        @LuaMethod(name = "sendServerCommandV", global = true)
        public void sendServerCommandV(String module, String command, Object... values) {
            if (GameServer.server) {
                GameServer.sendServerCommandV(module, command, values);
            }
        }

        @LuaMethod(name = "sendClientCommandV", global = true)
        public void sendClientCommandV(IsoPlayer player, String module, String command, Object... values) {
            if (GameClient.client) {
                GameClient.instance.sendClientCommandV(player, module, command, values);
            }
        }

        @LuaMethod(name = "addVariableToSyncList", global = true)
        public static void addVariableToSyncList(String key) {
            VariableSyncPacket.syncedVariables.add(key);
        }

        @LuaMethod(name = "getOnlineUsername", global = true)
        public static String getOnlineUsername() {
            return IsoPlayer.getInstance().getDisplayName();
        }

        @LuaMethod(name = "isValidUserName", global = true)
        public static boolean isValidUserName(String user) {
            return ServerWorldDatabase.isValidUserName(user);
        }

        @LuaMethod(name = "getHourMinute", global = true)
        public static String getHourMinute() {
            return LuaManager.getHourMinuteJava();
        }

        @LuaMethod(name = "SendCommandToServer", global = true)
        public static void SendCommandToServer(String command) {
            GameClient.SendCommandToServer(command);
        }

        @LuaMethod(name = "isAdmin", global = true)
        public static boolean isAdmin() {
            return GameClient.client && GameClient.connection.getRole() == Roles.getDefaultForAdmin();
        }

        @Deprecated
        @LuaMethod(name = "canModifyPlayerScoreboard", global = true)
        public static boolean canModifyPlayerScoreboard() {
            return GameClient.client && GameClient.connection.getRole().hasCapability(Capability.CanModifyPlayerStatsInThePlayerStatsUI);
        }

        @Deprecated
        @LuaMethod(name = "isAccessLevel", global = true)
        public static boolean isAccessLevel(String accessLevel) {
            if (GameClient.client) {
                return GameClient.connection.getRole().getName().equals(accessLevel);
            }
            return false;
        }

        @LuaMethod(name = "sendHumanVisual", global = true)
        public static void sendHumanVisual(IsoPlayer player) {
            if (GameServer.server) {
                INetworkPacket.sendToRelative(PacketTypes.PacketType.HumanVisual, player.square.x, player.square.y, player);
            }
        }

        @LuaMethod(name = "stopFire", global = true)
        public static void stopFire(Object obj) {
            if (GameServer.server) {
                if (obj instanceof IsoPlayer) {
                    IsoPlayer isoPlayer = (IsoPlayer) obj;
                    isoPlayer.sendObjectChange(IsoObjectChange.STOP_BURNING);
                } else if (obj instanceof IsoZombie) {
                    IsoZombie isoZombie = (IsoZombie) obj;
                    isoZombie.StopBurning();
                } else if (obj instanceof IsoGridSquare) {
                    IsoGridSquare isoGridSquare = (IsoGridSquare) obj;
                    isoGridSquare.stopFire();
                    INetworkPacket.sendToRelative(PacketTypes.PacketType.StopFire, isoGridSquare.getX(), isoGridSquare.getY(), obj);
                }
            }
        }

        @LuaMethod(name = "sortBrowserList", global = true)
        public static KahluaTable sortBrowserList(KahluaTableImpl table, String sortType, boolean sortDown, KahluaTableImpl filterTable) {
            return GameClient.sortBrowserList(table, sortType, sortDown, filterTable);
        }

        @LuaMethod(name = "getGameClient", global = true)
        public static GameClient getGameClient() {
            return GameClient.instance;
        }

        @LuaMethod(name = "sendRequestInventory", global = true)
        public static void sendRequestInventory(int id, String username) {
            INetworkPacket.send(PacketTypes.PacketType.PlayerInventory, Short.valueOf((short) id), username);
        }

        @LuaMethod(name = "InvMngGetItem", global = true)
        public static void InvMngGetItem(long itemId, String itemType, int playerID, String username) {
            GameClient.invMngRequestItem((int) itemId, itemType, (short) playerID, username);
        }

        @LuaMethod(name = "InvMngRemoveItem", global = true)
        public static void InvMngRemoveItem(long itemId, int playerID, String username) {
            GameClient.invMngRequestRemoveItem((int) itemId, (short) playerID, username);
        }

        @LuaMethod(name = "InvMngUpdateItem", global = true)
        public static void InvMngUpdateItem(InventoryItem item, int playerID) {
            GameClient.invMngRequestUpdateItem(item, (short) playerID);
        }

        @LuaMethod(name = "getConnectedPlayers", global = true)
        public static ArrayList<IsoPlayer> getConnectedPlayers() {
            return GameClient.instance.getConnectedPlayers();
        }

        @LuaMethod(name = "getPlayerFromUsername", global = true)
        public static IsoPlayer getPlayerFromUsername(String username) {
            return GameClient.instance.getPlayerFromUsername(username);
        }

        @LuaMethod(name = "isCoopHost", global = true)
        public static boolean isCoopHost() {
            return GameClient.connection != null && GameClient.connection.isCoopHost;
        }

        @LuaMethod(name = "setAdmin", global = true)
        public static void setAdmin() {
            if (!CoopMaster.instance.isRunning()) {
                return;
            }
            String lvl = Roles.getDefaultForAdmin().getName();
            if (GameClient.connection.getRole() == Roles.getDefaultForAdmin()) {
                lvl = "";
            }
            GameClient.connection.setRole(Roles.getDefaultForAdmin());
            IsoPlayer.getInstance().setRole(GameClient.connection.getRole());
            GameClient.SendCommandToServer("/setaccesslevel \"" + IsoPlayer.getInstance().username + "\" \"" + (lvl.isEmpty() ? "none" : lvl) + "\"");
            if ((lvl.isEmpty() && IsoPlayer.getInstance().isInvisible()) || (lvl.equals("admin") && !IsoPlayer.getInstance().isInvisible())) {
                GameClient.SendCommandToServer("/invisible");
            }
        }

        @LuaMethod(name = "addWarningPoint", global = true)
        public static void addWarningPoint(String user, String reason, int amount) {
            if (GameClient.client) {
                GameClient.instance.addWarningPoint(user, reason, amount);
            }
        }

        @LuaMethod(name = "disconnect", global = true)
        public static void disconnect() {
            GameClient.connection.forceDisconnect("lua-disconnect");
        }

        @LuaMethod(name = "writeLog", global = true)
        public static void writeLog(String loggerName, String logs) {
            LoggerManager.getLogger(loggerName).write(logs);
        }

        @LuaMethod(name = "doKeyPress", global = true)
        public static void doKeyPress(boolean doIt) {
            GameKeyboard.doLuaKeyPressed = doIt;
        }

        @LuaMethod(name = "getEvolvedRecipes", global = true)
        public static Stack<EvolvedRecipe> getEvolvedRecipes() {
            return ScriptManager.instance.getAllEvolvedRecipes();
        }

        @LuaMethod(name = "getZone", global = true)
        public static Zone getZone(int x, int y, int z) {
            return IsoWorld.instance.metaGrid.getZoneAt(x, y, z);
        }

        @LuaMethod(name = "getZones", global = true)
        public static ArrayList<Zone> getZones(int x, int y, int z) {
            return IsoWorld.instance.metaGrid.getZonesAt(x, y, z);
        }

        @LuaMethod(name = "getVehicleZoneAt", global = true)
        public static VehicleZone getVehicleZoneAt(int x, int y, int z) {
            return IsoWorld.instance.metaGrid.getVehicleZoneAt(x, y, z);
        }

        @LuaMethod(name = "getCellMinX", global = true)
        public static int getCellMinX() {
            return IsoWorld.instance.metaGrid.getMinX();
        }

        @LuaMethod(name = "getCellMaxX", global = true)
        public static int getCellMaxX() {
            return IsoWorld.instance.metaGrid.getMaxX();
        }

        @LuaMethod(name = "getCellMinY", global = true)
        public static int getCellMinY() {
            return IsoWorld.instance.metaGrid.getMinY();
        }

        @LuaMethod(name = "getCellMaxY", global = true)
        public static int getCellMaxY() {
            return IsoWorld.instance.metaGrid.getMaxY();
        }

        @LuaMethod(name = "replaceWith", global = true)
        public static String replaceWith(String toReplace, String regex, String by) {
            return toReplace.replaceFirst(regex, by);
        }

        @LuaMethod(name = "getTimestamp", global = true)
        public static long getTimestamp() {
            return System.currentTimeMillis() / 1000;
        }

        @LuaMethod(name = "getTimestampMs", global = true)
        public static long getTimestampMs() {
            return System.currentTimeMillis();
        }

        @LuaMethod(name = "forceSnowCheck", global = true)
        public static void forceSnowCheck() {
            ErosionMain.getInstance().snowCheck();
        }

        @LuaMethod(name = "getGametimeTimestamp", global = true)
        public static long getGametimeTimestamp() {
            return GameTime.instance.getCalender().getTimeInMillis() / 1000;
        }

        @LuaMethod(name = "canInviteFriends", global = true)
        public static boolean canInviteFriends() {
            if (GameClient.client && SteamUtils.isSteamModeEnabled()) {
                return CoopMaster.instance.isRunning() || !GameClient.coopInvite;
            }
            return false;
        }

        @LuaMethod(name = "inviteFriend", global = true)
        public static void inviteFriend(String steamID) {
            if (CoopMaster.instance.isRunning()) {
                CoopMaster.instance.sendMessage("invite-add", steamID);
            }
            SteamFriends.InviteUserToGame(SteamUtils.convertStringToSteamID(steamID), "+connect " + GameClient.ip + ":" + GameClient.port);
        }

        @LuaMethod(name = "getFriendsList", global = true)
        public static KahluaTable getFriendsList() {
            KahluaTable table = LuaManager.platform.newTable();
            if (!getSteamModeActive().booleanValue()) {
                return table;
            }
            List<SteamFriend> result = SteamFriends.GetFriendList();
            int count = 1;
            for (int i = 0; i < result.size(); i++) {
                SteamFriend friend = result.get(i);
                Double n = Double.valueOf(count);
                table.rawset(n, friend);
                count++;
            }
            return table;
        }

        @LuaMethod(name = "getSteamModeActive", global = true)
        public static Boolean getSteamModeActive() {
            return Boolean.valueOf(SteamUtils.isSteamModeEnabled());
        }

        @LuaMethod(name = "getStreamModeActive", global = true)
        public static Boolean getStreamModeActive() {
            return Boolean.valueOf(Core.getInstance().getOptionStreamerMode());
        }

        @LuaMethod(name = "getRemotePlayModeActive", global = true)
        public static Boolean getRemotePlayModeActive() {
            return Boolean.valueOf(SteamRemotePlay.GetSessionCount() > 0);
        }

        @LuaMethod(name = "isValidSteamID", global = true)
        public static boolean isValidSteamID(String s) {
            if (s == null || s.isEmpty()) {
                return false;
            }
            return SteamUtils.isValidSteamID(s);
        }

        @LuaMethod(name = "getCurrentUserSteamID", global = true)
        public static String getCurrentUserSteamID() {
            if (SteamUtils.isSteamModeEnabled() && !GameServer.server) {
                return SteamUser.GetSteamIDString();
            }
            return null;
        }

        @LuaMethod(name = "getCurrentUserProfileName", global = true)
        public static String getCurrentUserProfileName() {
            if (SteamUtils.isSteamModeEnabled() && !GameServer.server) {
                return SteamFriends.GetFriendPersonaName(SteamUser.GetSteamID());
            }
            return null;
        }

        @LuaMethod(name = "getSteamScoreboard", global = true)
        public static boolean getSteamScoreboard() {
            if (SteamUtils.isSteamModeEnabled() && GameClient.client) {
                return ServerOptions.instance.steamScoreboard.getValue();
            }
            return false;
        }

        @LuaMethod(name = "isSteamOverlayEnabled", global = true)
        public static boolean isSteamOverlayEnabled() {
            return SteamUtils.isOverlayEnabled();
        }

        @LuaMethod(name = "activateSteamOverlayToWorkshop", global = true)
        public static void activateSteamOverlayToWorkshop() {
            if (SteamUtils.isOverlayEnabled()) {
                SteamFriends.ActivateGameOverlayToWebPage("steam://url/SteamWorkshopPage/108600");
            }
        }

        @LuaMethod(name = "activateSteamOverlayToWorkshopUser", global = true)
        public static void activateSteamOverlayToWorkshopUser() {
            if (SteamUtils.isOverlayEnabled()) {
                SteamFriends.ActivateGameOverlayToWebPage("steam://url/SteamIDCommunityFilesPage/" + SteamUser.GetSteamIDString() + "/108600");
            }
        }

        @LuaMethod(name = "activateSteamOverlayToWorkshopItem", global = true)
        public static void activateSteamOverlayToWorkshopItem(String itemID) {
            if (SteamUtils.isOverlayEnabled() && SteamUtils.isValidSteamID(itemID)) {
                SteamFriends.ActivateGameOverlayToWebPage("steam://url/CommunityFilePage/" + itemID);
            }
        }

        @LuaMethod(name = "activateSteamOverlayToWebPage", global = true)
        public static void activateSteamOverlayToWebPage(String url) {
            if (LuaManager.isIndieStoneUrl(url) && SteamUtils.isOverlayEnabled()) {
                SteamFriends.ActivateGameOverlayToWebPage(url);
            }
        }

        @LuaMethod(name = "getSteamProfileNameFromSteamID", global = true)
        public static String getSteamProfileNameFromSteamID(String steamID) {
            if (SteamUtils.isSteamModeEnabled() && GameClient.client) {
                long id = SteamUtils.convertStringToSteamID(steamID);
                if (id != -1) {
                    return SteamFriends.GetFriendPersonaName(id);
                }
                return null;
            }
            return null;
        }

        @LuaMethod(name = "getSteamAvatarFromSteamID", global = true)
        public static Texture getSteamAvatarFromSteamID(String steamID) {
            if (SteamUtils.isSteamModeEnabled() && GameClient.client) {
                long id = SteamUtils.convertStringToSteamID(steamID);
                if (id != -1) {
                    return Texture.getSteamAvatar(id);
                }
                return null;
            }
            return null;
        }

        @LuaMethod(name = "getSteamIDFromUsername", global = true)
        public static String getSteamIDFromUsername(String username) {
            IsoPlayer player;
            if (SteamUtils.isSteamModeEnabled() && GameClient.client && (player = GameClient.instance.getPlayerFromUsername(username)) != null) {
                return SteamUtils.convertSteamIDToString(player.getSteamID());
            }
            return null;
        }

        @LuaMethod(name = "resetRegionFile", global = true)
        public static void resetRegionFile() {
            ServerOptions.getInstance().resetRegionFile();
        }

        @LuaMethod(name = "getSteamProfileNameFromUsername", global = true)
        public static String getSteamProfileNameFromUsername(String username) {
            IsoPlayer player;
            if (SteamUtils.isSteamModeEnabled() && GameClient.client && (player = GameClient.instance.getPlayerFromUsername(username)) != null) {
                return SteamFriends.GetFriendPersonaName(player.getSteamID());
            }
            return null;
        }

        @LuaMethod(name = "getSteamAvatarFromUsername", global = true)
        public static Texture getSteamAvatarFromUsername(String username) {
            IsoPlayer player;
            if (SteamUtils.isSteamModeEnabled() && GameClient.client && (player = GameClient.instance.getPlayerFromUsername(username)) != null) {
                return Texture.getSteamAvatar(player.getSteamID());
            }
            return null;
        }

        @LuaMethod(name = "getSteamWorkshopStagedItems", global = true)
        public static ArrayList<SteamWorkshopItem> getSteamWorkshopStagedItems() {
            if (SteamUtils.isSteamModeEnabled()) {
                return SteamWorkshop.instance.loadStagedItems();
            }
            return null;
        }

        @LuaMethod(name = "getSteamWorkshopItemIDs", global = true)
        public static ArrayList<String> getSteamWorkshopItemIDs() {
            if (SteamUtils.isSteamModeEnabled()) {
                ArrayList<String> result = new ArrayList<>();
                String[] folders = SteamWorkshop.instance.GetInstalledItemFolders();
                if (folders == null) {
                    return result;
                }
                for (String str : folders) {
                    String id = SteamWorkshop.instance.getIDFromItemInstallFolder(str);
                    if (id != null) {
                        result.add(id);
                    }
                }
                return result;
            }
            return null;
        }

        @LuaMethod(name = "getSteamWorkshopItemMods", global = true)
        public static ArrayList<ChooseGameInfo.Mod> getSteamWorkshopItemMods(String itemIDStr) {
            if (SteamUtils.isSteamModeEnabled()) {
                long itemID = SteamUtils.convertStringToSteamID(itemIDStr);
                if (itemID > 0) {
                    return ZomboidFileSystem.instance.getWorkshopItemMods(itemID);
                }
                return null;
            }
            return null;
        }

        @LuaMethod(name = "isSteamRunningOnSteamDeck", global = true)
        public static boolean isSteamRunningOnSteamDeck() {
            if (SteamUtils.isSteamModeEnabled()) {
                return SteamUtils.isRunningOnSteamDeck();
            }
            return false;
        }

        @LuaMethod(name = "showSteamGamepadTextInput", global = true)
        public static boolean showSteamGamepadTextInput(boolean password, boolean multiLine, String description, int maxChars, String existingText) {
            if (SteamUtils.isSteamModeEnabled()) {
                return SteamUtils.showGamepadTextInput(password, multiLine, description, maxChars, existingText);
            }
            return false;
        }

        @LuaMethod(name = "showSteamFloatingGamepadTextInput", global = true)
        public static boolean showSteamFloatingGamepadTextInput(boolean multiLine, int x, int y, int width, int height) {
            if (SteamUtils.isSteamModeEnabled()) {
                return SteamUtils.showFloatingGamepadTextInput(multiLine, x, y, width, height);
            }
            return false;
        }

        @LuaMethod(name = "isFloatingGamepadTextInputVisible", global = true)
        public static boolean isFloatingGamepadTextInputVisible() {
            if (SteamUtils.isSteamModeEnabled()) {
                return SteamUtils.isFloatingGamepadTextInputVisible();
            }
            return false;
        }

        @LuaMethod(name = "sendPlayerStatsChange", global = true)
        public static void sendPlayerStatsChange(IsoPlayer player) {
            if (GameClient.client) {
                GameClient.instance.sendChangedPlayerStats(player);
            }
        }

        @LuaMethod(name = "sendPersonalColor", global = true)
        public static void sendPersonalColor(IsoPlayer player) {
            if (GameClient.client) {
                GameClient.instance.sendPersonalColor(player);
            }
        }

        @LuaMethod(name = "requestTrading", global = true)
        public static void requestTrading(IsoPlayer you, IsoPlayer other) {
            GameClient.instance.requestTrading(you, other);
        }

        @LuaMethod(name = "acceptTrading", global = true)
        public static void acceptTrading(IsoPlayer you, IsoPlayer other, boolean accept) {
            GameClient.instance.acceptTrading(you, other, accept);
        }

        @LuaMethod(name = "tradingUISendAddItem", global = true)
        public static void tradingUISendAddItem(IsoPlayer you, IsoPlayer other, InventoryItem item) {
            GameClient.instance.tradingUISendAddItem(you, other, item);
        }

        @LuaMethod(name = "tradingUISendRemoveItem", global = true)
        public static void tradingUISendRemoveItem(IsoPlayer you, IsoPlayer other, InventoryItem item) {
            GameClient.instance.tradingUISendRemoveItem(you, other, item);
        }

        @LuaMethod(name = "tradingUISendUpdateState", global = true)
        public static void tradingUISendUpdateState(IsoPlayer you, IsoPlayer other, int state) {
            GameClient.instance.tradingUISendUpdateState(you, other, state);
        }

        @LuaMethod(name = "sendWarManagerUpdate", global = true)
        public static void sendWarManagerUpdate(int onlineID, String attacker, WarManager.State state) {
            INetworkPacket.send(PacketTypes.PacketType.WarStateSync, Integer.valueOf(onlineID), attacker, state);
        }

        @LuaMethod(name = "getTwoLetters", global = true)
        public static String getTwoLetters(String input) {
            String cleaned = input.replaceAll("[^a-zA-Z\\s]", "");
            String[] words = cleaned.split("\\s+");
            char letter1 = '-';
            char letter2 = ' ';
            if (words.length >= 1 && !words[0].isEmpty()) {
                letter1 = words[0].charAt(0);
            }
            if (words.length >= 2 && !words[1].isEmpty()) {
                letter2 = words[1].charAt(0);
            }
            return ("" + letter1 + letter2).toUpperCase();
        }

        private static boolean isPunctuation(char c) {
            return c == '.' || c == ',' || c == '!' || c == '?' || c == ';' || c == ':';
        }

        private static int findBestSplitPoint(String input, int maxSize) {
            for (int i = maxSize; i > 0; i--) {
                if (i < input.length() && Character.isWhitespace(input.charAt(i))) {
                    return i;
                }
            }
            for (int i2 = maxSize; i2 > 0; i2--) {
                if (i2 < input.length() && isPunctuation(input.charAt(i2))) {
                    return i2 + 1;
                }
            }
            return -1;
        }

        @LuaMethod(name = "splitString", global = true)
        public static KahluaTable splitString(String input, int maxSize) {
            KahluaTable table = LuaManager.platform.newTable();
            if (input == null) {
                table.rawset(0, "");
                table.rawset(1, "");
                return table;
            }
            if (input.length() <= maxSize) {
                table.rawset(0, input);
                table.rawset(1, "");
                return table;
            }
            int splitPoint = findBestSplitPoint(input, maxSize);
            if (splitPoint == -1) {
                splitPoint = maxSize;
            }
            String firstPart = input.substring(0, splitPoint).trim();
            String secondPart = input.substring(splitPoint).trim();
            if (secondPart.length() > maxSize) {
                secondPart = secondPart.substring(0, maxSize);
            }
            table.rawset(0, firstPart);
            table.rawset(1, secondPart);
            return table;
        }

        @LuaMethod(name = "querySteamWorkshopItemDetails", global = true)
        public static void querySteamWorkshopItemDetails(ArrayList<String> itemIDs, LuaClosure functionObj, Object arg1) {
            if (itemIDs == null || functionObj == null) {
                throw new NullPointerException();
            }
            if (itemIDs.isEmpty()) {
                if (arg1 == null) {
                    LuaManager.caller.pcall(LuaManager.thread, (Object) functionObj, "Completed", new ArrayList());
                    return;
                } else {
                    LuaManager.caller.pcall(LuaManager.thread, (Object) functionObj, arg1, "Completed", new ArrayList());
                    return;
                }
            }
            new ItemQuery(itemIDs, functionObj, arg1);
        }

        @LuaMethod(name = "connectToServerStateCallback", global = true)
        public static void connectToServerStateCallback(String button) {
            if (ConnectToServerState.instance != null) {
                ConnectToServerState.instance.FromLua(button);
            }
        }

        @LuaMethod(name = "getPublicServersList", global = true)
        public static KahluaTable getPublicServersList() {
            KahluaTable table = LuaManager.platform.newTable();
            if (!SteamUtils.isSteamModeEnabled() && !PublicServerUtil.isEnabled()) {
                return table;
            }
            if (System.currentTimeMillis() - timeLastRefresh < 60000) {
                return table;
            }
            List<Server> result = new ArrayList<>();
            try {
                if (getSteamModeActive().booleanValue()) {
                    ServerBrowser.RefreshInternetServers();
                    List<GameServerDetails> steamServers = ServerBrowser.GetServerList();
                    for (GameServerDetails steamServer : steamServers) {
                        Server newServer = new Server();
                        newServer.setName(steamServer.name);
                        newServer.setDescription(steamServer.gameDescription);
                        newServer.setSteamId(Long.toString(steamServer.steamId));
                        newServer.setPing(Integer.toString(steamServer.ping));
                        newServer.setPlayers(Integer.toString(steamServer.numPlayers));
                        newServer.setMaxPlayers(Integer.toString(steamServer.maxPlayers));
                        newServer.setOpen(true);
                        newServer.setIp(steamServer.address);
                        newServer.setPort(steamServer.port);
                        newServer.setMods(steamServer.tags);
                        newServer.setVersion(Core.getInstance().getVersionNumber());
                        newServer.setLastUpdate(1);
                        result.add(newServer);
                    }
                    System.out.printf("%d servers\n", Integer.valueOf(steamServers.size()));
                } else {
                    StringBuilder buffer = new StringBuilder();
                    try {
                        URL url = new URL(PublicServerUtil.webSite + "servers.xml");
                        URLConnection urlConnection = url.openConnection();
                        urlConnection.setConnectTimeout(10000);
                        urlConnection.setReadTimeout(10000);
                        InputStreamReader ipsr = new InputStreamReader(urlConnection.getInputStream());
                        BufferedReader br = new BufferedReader(ipsr);
                        while (true) {
                            String line = br.readLine();
                            if (line == null) {
                                break;
                            }
                            buffer.append(line).append('\n');
                        }
                        br.close();
                        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                        Document doc = dBuilder.parse(new InputSource(new StringReader(buffer.toString())));
                        doc.getDocumentElement().normalize();
                        NodeList nList = doc.getElementsByTagName("server");
                        for (int temp = 0; temp < nList.getLength(); temp++) {
                            Node nNode = nList.item(temp);
                            if (nNode.getNodeType() == 1) {
                                Element eElement = (Element) nNode;
                                Server newServer2 = new Server();
                                newServer2.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                                if (eElement.getElementsByTagName("desc").getLength() != 0 && !"".equals(eElement.getElementsByTagName("desc").item(0).getTextContent())) {
                                    newServer2.setDescription(eElement.getElementsByTagName("desc").item(0).getTextContent());
                                }
                                newServer2.setIp(eElement.getElementsByTagName("ip").item(0).getTextContent());
                                newServer2.setPort(Integer.parseInt(eElement.getElementsByTagName("port").item(0).getTextContent()));
                                newServer2.setPlayers(eElement.getElementsByTagName("players").item(0).getTextContent());
                                newServer2.setMaxPlayers(eElement.getElementsByTagName("maxPlayers").item(0).getTextContent());
                                if (eElement.getElementsByTagName("version").getLength() != 0) {
                                    newServer2.setVersion(eElement.getElementsByTagName("version").item(0).getTextContent());
                                }
                                newServer2.setOpen(eElement.getElementsByTagName(AbstractCircuitBreaker.PROPERTY_NAME).item(0).getTextContent().equals(TlbConst.TYPELIB_MAJOR_VERSION_SHELL));
                                int lastUp = Integer.parseInt(eElement.getElementsByTagName("lastUpdate").item(0).getTextContent());
                                if (eElement.getElementsByTagName("mods").getLength() != 0 && !"".equals(eElement.getElementsByTagName("mods").item(0).getTextContent())) {
                                    newServer2.setMods(eElement.getElementsByTagName("mods").item(0).getTextContent());
                                }
                                newServer2.setLastUpdate(PZMath.fastfloor((getTimestamp() - ((long) lastUp)) / 60));
                                NodeList nl = eElement.getElementsByTagName("password");
                                newServer2.setPasswordProtected(nl.getLength() != 0 && nl.item(0).getTextContent().equals(TlbConst.TYPELIB_MAJOR_VERSION_SHELL));
                                result.add(newServer2);
                            }
                        }
                    } catch (Exception e) {
                        return null;
                    }
                }
                int count = 1;
                for (int i = 0; i < result.size(); i++) {
                    Server server = result.get(i);
                    Double n = Double.valueOf(count);
                    table.rawset(n, server);
                    count++;
                }
                timeLastRefresh = Calendar.getInstance().getTimeInMillis();
                return table;
            } catch (Exception e2) {
                DebugType.General.printException(e2, LogSeverity.Error);
                return null;
            }
        }

        @LuaMethod(name = "steamRequestInternetServersList", global = true)
        public static void steamRequestInternetServersList() {
            ServerBrowser.RefreshInternetServers();
        }

        @LuaMethod(name = "steamReleaseInternetServersRequest", global = true)
        public static void steamReleaseInternetServersRequest() {
            ServerBrowser.Release();
        }

        @LuaMethod(name = "steamRequestInternetServersCount", global = true)
        public static int steamRequestInternetServersCount() {
            return ServerBrowser.GetServerCount();
        }

        @LuaMethod(name = "steamGetInternetServerDetails", global = true)
        public static Server steamGetInternetServerDetails(int index) {
            if (!ServerBrowser.IsRefreshing()) {
                return null;
            }
            Server server = GameServer.steamGetInternetServerDetails(ServerBrowser.GetServerDetails(index));
            if (server.isHosted() || !server.isPublic()) {
                return null;
            }
            return server;
        }

        @LuaMethod(name = "steamRequestServerRules", global = true)
        public static boolean steamRequestServerRules(String host, int port) {
            return ServerBrowser.RequestServerRules(host, port);
        }

        @LuaMethod(name = "getHostByName", global = true)
        public static String getHostByName(String hostname) {
            try {
                InetAddress ipAddress = InetAddress.getByName(hostname);
                return ipAddress.getHostAddress();
            } catch (UnknownHostException e) {
                return null;
            }
        }

        @LuaMethod(name = "steamRequestServerDetails", global = true)
        public static boolean steamRequestServerDetails(String host, int port) {
            if (host != null) {
                return ServerBrowser.QueryServer(host, port);
            }
            return false;
        }

        @LuaMethod(name = "isPublicServerListAllowed", global = true)
        public static boolean isPublicServerListAllowed() {
            if (isDebugEnabled()) {
                return true;
            }
            if (SteamUtils.isSteamModeEnabled()) {
                return isSteamServerBrowserEnabled();
            }
            return PublicServerUtil.isEnabled();
        }

        @LuaMethod(name = "isSteamServerBrowserEnabled", global = true)
        public static boolean isSteamServerBrowserEnabled() {
            return true;
        }

        @LuaMethod(name = "testSound", global = true)
        public static void testSound() {
            float x = Mouse.getX();
            float y = Mouse.getY();
            int z = IsoPlayer.getInstance().getZi();
            int gridX = (int) IsoUtils.XToIso(x, y, z);
            int gridY = (int) IsoUtils.YToIso(x, y, z);
            AmbientStreamManager.Ambient a = new AmbientStreamManager.Ambient("Meta/House Alarm", gridX, gridY, 50.0f, 1.0f);
            a.trackMouse = true;
            ((AmbientStreamManager) AmbientStreamManager.instance).ambient.add(a);
        }

        @LuaMethod(name = "getFMODEventPathList", global = true)
        public static ArrayList<String> getFMODEventPathList() {
            return FMODManager.instance.getEventPathList();
        }

        @LuaMethod(name = "debugSetRoomType", global = true)
        public static void debugSetRoomType(Double roomType) {
            ParameterRoomType.setRoomType(roomType.intValue());
        }

        @LuaMethod(name = "copyTable", global = true)
        public static KahluaTable copyTable(KahluaTable table) {
            return LuaManager.copyTable(table);
        }

        @LuaMethod(name = "copyTable", global = true)
        public static KahluaTable copyTable(KahluaTable to, KahluaTable from) {
            return LuaManager.copyTable(to, from);
        }

        @LuaMethod(name = "renderIsoCircle", global = true)
        public static void renderIsoCircle(float x, float y, float z, float radius, float r, float g, float b, float a, int thickness) {
            double d = Const.default_value_double;
            while (true) {
                double theta = d;
                if (theta < 6.283185307179586d) {
                    float x0 = x + (radius * ((float) Math.cos(theta)));
                    float y0 = y + (radius * ((float) Math.sin(theta)));
                    float x1 = x + (radius * ((float) Math.cos(theta + 0.3490658503988659d)));
                    float y1 = y + (radius * ((float) Math.sin(theta + 0.3490658503988659d)));
                    float sx0 = IsoUtils.XToScreenExact(x0, y0, z, 0);
                    float sy0 = IsoUtils.YToScreenExact(x0, y0, z, 0);
                    float sx1 = IsoUtils.XToScreenExact(x1, y1, z, 0);
                    float sy1 = IsoUtils.YToScreenExact(x1, y1, z, 0);
                    LineDrawer.drawLine(sx0, sy0, sx1, sy1, r, g, b, a, thickness);
                    d = theta + 0.3490658503988659d;
                } else {
                    return;
                }
            }
        }

        @LuaMethod(name = "renderIsoRect", global = true)
        public static void renderIsoRect(float x, float y, float z, float radius, float r, float g, float b, float a, int thickness) {
            float sx0 = IsoUtils.XToScreenExact(x - 1.0f, y - 1.0f, z, 0);
            float sy0 = IsoUtils.YToScreenExact(x - 1.0f, y - 1.0f, z, 0);
            float sx1 = IsoUtils.XToScreenExact((x - 1.0f) + radius, y - 1.0f, z, 0);
            float sy1 = IsoUtils.YToScreenExact(x - 1.0f, (y - 1.0f) + radius, z, 0);
            LineDrawer.drawLine(sx0, sy0, sx1, sy1, r, g, b, a, thickness);
            float sx02 = IsoUtils.XToScreenExact(x - 1.0f, y - 1.0f, z, 0);
            float sy02 = IsoUtils.YToScreenExact(x - 1.0f, y - 1.0f, z, 0);
            float sx12 = IsoUtils.XToScreenExact((x - 1.0f) - radius, y - 1.0f, z, 0);
            float sy12 = IsoUtils.YToScreenExact(x - 1.0f, (y - 1.0f) + radius, z, 0);
            LineDrawer.drawLine(sx02, sy02, sx12, sy12, r, g, b, a, thickness);
            float sx03 = IsoUtils.XToScreenExact((x - 1.0f) + radius, (y - 1.0f) + radius, z, 0);
            float sy03 = IsoUtils.YToScreenExact((x - 1.0f) + radius, (y - 1.0f) + radius, z, 0);
            float sx13 = IsoUtils.XToScreenExact((x - 1.0f) - radius, y - 1.0f, z, 0);
            float sy13 = IsoUtils.YToScreenExact(x - 1.0f, (y - 1.0f) + radius, z, 0);
            LineDrawer.drawLine(sx03, sy03, sx13, sy13, r, g, b, a, thickness);
            float sx04 = IsoUtils.XToScreenExact((x - 1.0f) + radius, (y - 1.0f) + radius, z, 0);
            float sy04 = IsoUtils.YToScreenExact((x - 1.0f) + radius, (y - 1.0f) + radius, z, 0);
            float sx14 = IsoUtils.XToScreenExact((x - 1.0f) + radius, y - 1.0f, z, 0);
            float sy14 = IsoUtils.YToScreenExact(x - 1.0f, (y - 1.0f) + radius, z, 0);
            LineDrawer.drawLine(sx04, sy04, sx14, sy14, r, g, b, a, thickness);
        }

        @LuaMethod(name = "renderLine", global = true)
        public static void renderLine(float x, float y, float z, float tx, float ty, float tz, float r, float g, float b, float a) {
            LineDrawer.addLine(x, y, z, tx, ty, tz, r, g, b, a);
        }

        @LuaMethod(name = "configureLighting", global = true)
        public static void configureLighting(float darkStep) {
            if (LightingJNI.init) {
                LightingJNI.configure(darkStep);
            }
        }

        @LuaMethod(name = "invalidateLighting", global = true)
        public static void invalidateLighting() {
            for (int pn = 0; pn < IsoPlayer.numPlayers; pn++) {
                LosUtil.cachecleared[pn] = true;
            }
            IsoGridSquare.setRecalcLightTime(-1.0f);
            GameTime.getInstance().lightSourceUpdate = 100.0f;
        }

        @LuaMethod(name = "testHelicopter", global = true)
        public static void testHelicopter() {
            if (GameClient.client) {
                GameClient.SendCommandToServer("/chopper start");
            } else {
                IsoWorld.instance.helicopter.pickRandomTarget();
            }
        }

        @LuaMethod(name = "endHelicopter", global = true)
        public static void endHelicopter() {
            if (GameClient.client) {
                GameClient.SendCommandToServer("/chopper stop");
            } else {
                IsoWorld.instance.helicopter.deactivate();
            }
        }

        @LuaMethod(name = "getServerSettingsManager", global = true)
        public static ServerSettingsManager getServerSettingsManager() {
            return ServerSettingsManager.instance;
        }

        @LuaMethod(name = "rainConfig", global = true)
        public static void rainConfig(String cmd, int arg) {
            if ("alpha".equals(cmd)) {
                IsoWorld.instance.currentCell.setRainAlpha(arg);
            }
            if ("intensity".equals(cmd)) {
                IsoWorld.instance.currentCell.setRainIntensity(arg);
            }
            if ("speed".equals(cmd)) {
                IsoWorld.instance.currentCell.setRainSpeed(arg);
            }
            if ("reloadTextures".equals(cmd)) {
                IsoWorld.instance.currentCell.reloadRainTextures();
            }
        }

        @LuaMethod(name = "sendSwitchSeat", global = true)
        public static void sendSwitchSeat(BaseVehicle vehicle, IsoGameCharacter chr, int seatFrom, int seatTo) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.VehicleSwitchSeat, vehicle, chr, Integer.valueOf(seatFrom), Integer.valueOf(seatTo));
            }
        }

        @LuaMethod(name = "getVehicleById", global = true)
        public static BaseVehicle getVehicleById(int id) {
            return VehicleManager.instance.getVehicleByID((short) id);
        }

        @LuaMethod(name = "removeVehicle", global = true)
        public static void removeVehicle(IsoPlayer player, BaseVehicle baseVehicle) {
            if (baseVehicle != null) {
                if (GameClient.client) {
                    GameClient.instance.sendClientCommandV(player, "vehicle", "remove", "vehicle", Short.valueOf(baseVehicle.getId()));
                } else if (!GameServer.server) {
                    baseVehicle.permanentlyRemove();
                }
            }
        }

        @LuaMethod(name = "removeAllVehicles", global = true)
        public static void removeAllVehicles(IsoPlayer player) {
            if (GameClient.client) {
                GameClient.SendCommandToServer("/remove vehicles");
            } else if (!GameServer.server) {
                VehicleManager.instance.removeVehicles(player);
            }
        }

        @LuaMethod(name = "addBloodSplat", global = true)
        public void addBloodSplat(IsoGridSquare sq, int nbr) {
            for (int i = 0; i < nbr; i++) {
                sq.getChunk().addBloodSplat(sq.x + Rand.Next(-0.5f, 0.5f), sq.y + Rand.Next(-0.5f, 0.5f), sq.z, Rand.Next(8));
            }
        }

        @LuaMethod(name = "addBloodSplat", global = true)
        public void addBloodSplat(IsoGridSquare sq, int nbr, float xoffset, float yoffset) {
            for (int i = 0; i < nbr; i++) {
                sq.getChunk().addBloodSplat(sq.x + xoffset, sq.y + yoffset, sq.z, Rand.Next(20));
            }
        }

        @LuaMethod(name = "addCarCrash", global = true)
        public static void addCarCrash() {
            IsoChunk chunk;
            Zone zone;
            IsoGridSquare sq = IsoPlayer.getInstance().getCurrentSquare();
            if (sq == null || (chunk = sq.getChunk()) == null || (zone = sq.getZone()) == null || !chunk.canAddRandomCarCrash(zone, true)) {
                return;
            }
            sq.chunk.addRandomCarCrash(zone, true);
        }

        @LuaMethod(name = "createRandomDeadBody", global = true)
        public static IsoDeadBody createRandomDeadBody(IsoGridSquare square, int blood) {
            if (square == null) {
                return null;
            }
            ItemPickerJava.ItemPickerRoom all = ItemPickerJava.rooms.get("all");
            IsoGameCharacter chr = new RandomizedBuildingBase.HumanCorpse(IsoWorld.instance.getCell(), square.x, square.y, square.z);
            chr.setDir(IsoDirections.getRandom());
            chr.setDescriptor(SurvivorFactory.CreateSurvivor());
            chr.setFemale(chr.getDescriptor().isFemale());
            chr.initWornItems("Human");
            chr.initAttachedItems("Human");
            Outfit outfit = chr.getRandomDefaultOutfit();
            chr.dressInNamedOutfit(outfit.name);
            chr.initSpritePartsEmpty();
            chr.Dressup(chr.getDescriptor());
            for (int b = 0; b < blood; b++) {
                chr.addBlood(null, false, true, false);
            }
            IsoDeadBody body = new IsoDeadBody(chr, true);
            ItemPickerJava.fillContainerType(all, body.getContainer(), chr.isFemale() ? "inventoryfemale" : "inventorymale", null);
            LuaEventManager.triggerEvent("OnFillContainer", "Random Dead Body", body.getContainer().getType(), body.getContainer());
            return body;
        }

        @LuaMethod(name = "addZombieSitting", global = true)
        public void addZombieSitting(int x, int y, int z) {
            IsoGridSquare sq = IsoCell.getInstance().getGridSquare(x, y, z);
            if (sq == null) {
                return;
            }
            VirtualZombieManager.instance.choices.clear();
            VirtualZombieManager.instance.choices.add(sq);
            IsoZombie zombie2 = VirtualZombieManager.instance.createRealZombieAlways(IsoDirections.getRandom(), false);
            zombie2.dressInRandomOutfit = true;
            ZombiePopulationManager.instance.sitAgainstWall(zombie2, sq);
        }

        @LuaMethod(name = "addZombiesEating", global = true)
        public void addZombiesEating(int x, int y, int z, int totalZombies, boolean skeletonBody) {
            IsoGridSquare sq = IsoCell.getInstance().getGridSquare(x, y, z);
            if (sq == null) {
                return;
            }
            VirtualZombieManager.instance.choices.clear();
            VirtualZombieManager.instance.choices.add(sq);
            IsoZombie zombie2 = VirtualZombieManager.instance.createRealZombieAlways(IsoDirections.getRandom(), false);
            zombie2.setX(sq.x);
            zombie2.setY(sq.y);
            zombie2.setFakeDead(false);
            zombie2.setHealth(0.0f);
            if (!skeletonBody) {
                zombie2.dressInRandomOutfit();
                for (int i = 0; i < 10; i++) {
                    zombie2.addHole(null);
                    zombie2.addBlood(null, false, true, false);
                }
                zombie2.DoZombieInventory();
            }
            zombie2.setSkeleton(skeletonBody);
            if (skeletonBody) {
                zombie2.getHumanVisual().setSkinTextureIndex(2);
            }
            IsoDeadBody body = new IsoDeadBody(zombie2, true);
            VirtualZombieManager.instance.createEatingZombies(body, totalZombies);
        }

        @LuaMethod(name = "addZombiesInOutfitArea", global = true)
        public ArrayList<IsoZombie> addZombiesInOutfitArea(int x1, int y1, int x2, int y2, int z, int totalZombies, String outfit, Integer femaleChance) {
            ArrayList<IsoZombie> result = new ArrayList<>();
            for (int i = 0; i < totalZombies; i++) {
                result.addAll(addZombiesInOutfit(Rand.Next(x1, x2), Rand.Next(y1, y2), z, 1, outfit, femaleChance));
            }
            return result;
        }

        @LuaMethod(name = "addZombiesInOutfit", global = true)
        public static ArrayList<IsoZombie> addZombiesInOutfit(int x, int y, int z, int totalZombies, String outfit, Integer femaleChance) {
            return addZombiesInOutfit(x, y, z, totalZombies, outfit, femaleChance, false, false, false, false, false, false, 1.0f);
        }

        @LuaMethod(name = "addZombiesInOutfit", global = true)
        public static ArrayList<IsoZombie> addZombiesInOutfit(int x, int y, int z, int totalZombies, String outfit, Integer femaleChance, boolean isCrawler, boolean isFallOnFront, boolean isFakeDead, boolean isKnockedDown, boolean isInvulnerable, boolean isSitting, float health) {
            return addZombiesInOutfit(x, y, z, totalZombies, outfit, femaleChance, isCrawler, isFallOnFront, isFakeDead, isKnockedDown, isInvulnerable, isSitting, health, false);
        }

        @LuaMethod(name = "addZombiesInOutfit", global = true)
        public static ArrayList<IsoZombie> addZombiesInOutfit(int x, int y, int z, int totalZombies, String outfit, Integer femaleChance, boolean isCrawler, boolean isFallOnFront, boolean isFakeDead, boolean isKnockedDown, boolean isInvulnerable, boolean isSitting, float health, boolean isAnimRecording) {
            return addZombiesInOutfit(x, y, z, totalZombies, outfit, femaleChance, isCrawler, isFallOnFront, isFakeDead, isKnockedDown, isInvulnerable, isSitting, health, isAnimRecording, 0.0f);
        }

        @LuaMethod(name = "addZombiesInOutfit", global = true)
        public static ArrayList<IsoZombie> addZombiesInOutfit(int x, int y, int z, int totalZombies, String outfit, Integer femaleChance, boolean isCrawler, boolean isFallOnFront, boolean isFakeDead, boolean isKnockedDown, boolean isInvulnerable, boolean isSitting, float health, boolean isAnimRecording, float heightOffset) {
            return addZombiesInOutfit(x, y, z, totalZombies, outfit, femaleChance, isCrawler, isFallOnFront, isFakeDead, isKnockedDown, isInvulnerable, isSitting, health, isAnimRecording, heightOffset, false);
        }

        @LuaMethod(name = "addZombiesInOutfit", global = true)
        public static ArrayList<IsoZombie> addZombiesInOutfit(int x, int y, int z, int totalZombies, String outfit, Integer femaleChance, boolean isCrawler, boolean isFallOnFront, boolean isFakeDead, boolean isKnockedDown, boolean isInvulnerable, boolean isSitting, float health, boolean isAnimRecording, float heightOffset, boolean isRagdolling) {
            ArrayList<IsoZombie> result = new ArrayList<>();
            if (IsoWorld.getZombiesDisabled() && !Core.getInstance().getDebug()) {
                DebugLog.General.warn("Cannot spawn.");
                return result;
            }
            IsoGridSquare sq = IsoCell.getInstance().getGridSquare(x, y, z);
            if (sq == null) {
                DebugLog.General.warn("No IsoSquare selected. Cannot spawn.");
                return result;
            }
            for (int j = 0; j < totalZombies; j++) {
                if (health <= 0.0f) {
                    DebugLog.General.warn("Spawning corpse.");
                    sq.getChunk().AddCorpses(x / 8, y / 8);
                } else {
                    VirtualZombieManager.instance.choices.clear();
                    VirtualZombieManager.instance.choices.add(sq);
                    IsoZombie zombie2 = VirtualZombieManager.instance.createRealZombieAlways(IsoDirections.getRandom(), false);
                    if (zombie2 == null) {
                        DebugLog.General.warn("Failed to spawn.");
                    } else {
                        if (femaleChance != null) {
                            zombie2.setFemaleEtc(Rand.Next(100) < femaleChance.intValue());
                        }
                        DebugType debugType = DebugLog.General;
                        Object[] objArr = new Object[10];
                        objArr[0] = zombie2.isFemale() ? "Female" : "Male";
                        objArr[1] = Objects.requireNonNullElse(outfit, "No Outfit");
                        objArr[2] = isCrawler ? ", Crawler, " : "";
                        objArr[3] = Boolean.valueOf(isFallOnFront);
                        objArr[4] = Boolean.valueOf(isFakeDead);
                        objArr[5] = Boolean.valueOf(isKnockedDown);
                        objArr[6] = Boolean.valueOf(isSitting);
                        objArr[7] = Boolean.valueOf(isAnimRecording);
                        objArr[8] = Float.valueOf(heightOffset);
                        objArr[9] = Boolean.valueOf(isRagdolling);
                        debugType.warn("Spawning new %s Zed, Dressed in %s%s, isFallOnFront:%s, isFakeDead:%s, isKnockedDown:%s, isSitting:%s, isAnimRecording:%s, heightOffset:%f, isRagdolling:%s", objArr);
                        if (outfit != null) {
                            zombie2.dressInPersistentOutfit(outfit);
                            zombie2.dressInRandomOutfit = false;
                        } else {
                            zombie2.dressInRandomOutfit = true;
                        }
                        zombie2.lunger = true;
                        zombie2.setKnockedDown(isKnockedDown);
                        if (isCrawler) {
                            zombie2.setCrawler(true);
                            zombie2.setCanWalk(false);
                            zombie2.setOnFloor(true);
                            zombie2.setKnockedDown(true);
                            zombie2.setCrawlerType(1);
                            zombie2.DoZombieStats();
                        }
                        zombie2.setFakeDead(isFakeDead);
                        zombie2.setFallOnFront(isFallOnFront);
                        zombie2.setGodMod(isInvulnerable);
                        zombie2.setHealth(health);
                        if (isSitting) {
                            zombie2.setSitAgainstWall(true);
                        }
                        zombie2.setAnimRecorderActive(isAnimRecording, true);
                        if (heightOffset != 0.0f) {
                            zombie2.setZ(zombie2.getZ() + heightOffset);
                            zombie2.setbFalling(true);
                        }
                        if (isRagdolling) {
                            zombie2.setKnockedDown(true);
                            zombie2.setUsePhysicHitReaction(true);
                        }
                        result.add(zombie2);
                    }
                }
            }
            ZombieSpawnRecorder.instance.record(result, GlobalObject.class.getSimpleName());
            return result;
        }

        @LuaMethod(name = "addZombiesInBuilding", global = true)
        public ArrayList<IsoZombie> addZombiesInBuilding(BuildingDef def, int totalZombies, String outfit, RoomDef room, Integer femaleChance) {
            IsoGridSquare sq;
            boolean randomizeRoom = room == null;
            ArrayList<IsoZombie> result = new ArrayList<>();
            if (IsoWorld.getZombiesDisabled()) {
                return result;
            }
            if (room == null) {
                room = def.getRandomRoom(6);
            }
            int min = 2;
            int max = room.area / 2;
            if (totalZombies == 0) {
                if (SandboxOptions.instance.zombies.getValue() == 1) {
                    max += 4;
                } else if (SandboxOptions.instance.zombies.getValue() == 2) {
                    max += 3;
                } else if (SandboxOptions.instance.zombies.getValue() == 3) {
                    max += 2;
                } else if (SandboxOptions.instance.zombies.getValue() == 5) {
                    max -= 4;
                }
                if (max > 8) {
                    max = 8;
                }
                if (max < 2) {
                    max = 2 + 1;
                }
            } else {
                min = totalZombies;
                max = totalZombies;
            }
            int rand = Rand.Next(min, max);
            for (int j = 0; j < rand && (sq = RandomizedBuildingBase.getRandomSpawnSquare(room)) != null; j++) {
                VirtualZombieManager.instance.choices.clear();
                VirtualZombieManager.instance.choices.add(sq);
                IsoZombie zombie2 = VirtualZombieManager.instance.createRealZombieAlways(IsoDirections.getRandom(), false);
                if (zombie2 != null) {
                    if (femaleChance != null) {
                        zombie2.setFemaleEtc(Rand.Next(100) < femaleChance.intValue());
                    }
                    if (outfit != null) {
                        zombie2.dressInPersistentOutfit(outfit);
                        zombie2.dressInRandomOutfit = false;
                    } else {
                        zombie2.dressInRandomOutfit = true;
                    }
                    result.add(zombie2);
                    if (randomizeRoom) {
                        room = def.getRandomRoom(6);
                    }
                }
            }
            ZombieSpawnRecorder.instance.record(result, getClass().getSimpleName());
            return result;
        }

        @LuaMethod(name = "addVehicleDebug", global = true)
        public static BaseVehicle addVehicleDebug(String scriptName, IsoDirections dir, Integer skinIndex, IsoGridSquare sq) {
            float angle;
            if (dir == null) {
                dir = IsoDirections.getRandom();
            }
            BaseVehicle v = new BaseVehicle(IsoWorld.instance.currentCell);
            if (!StringUtils.isNullOrEmpty(scriptName)) {
                v.setScriptName(scriptName);
                v.setScript();
                if (skinIndex != null) {
                    v.setSkinIndex(skinIndex.intValue());
                }
            }
            v.setDir(dir);
            float angle2 = dir.toAngle() + 3.1415927f + Rand.Next(-0.2f, 0.2f);
            while (true) {
                angle = angle2;
                if (angle <= 6.283185307179586d) {
                    break;
                }
                angle2 = (float) (((double) angle) - 6.283185307179586d);
            }
            v.savedRot.setAngleAxis(angle, 0.0f, 1.0f, 0.0f);
            v.jniTransform.setRotation(v.savedRot);
            v.setX(sq.x);
            v.setY(sq.y);
            v.setZ(sq.z);
            if (IsoChunk.doSpawnedVehiclesInInvalidPosition(v)) {
                v.setSquare(sq);
                sq.chunk.vehicles.add(v);
                v.chunk = sq.chunk;
                v.addToWorld();
                VehiclesDB2.instance.addVehicle(v);
            }
            v.setGeneralPartCondition(1.3f, 10.0f);
            v.rust = 0.0f;
            return v;
        }

        @LuaMethod(name = "addVehicle", global = true)
        public static BaseVehicle addVehicle(String script, int x, int y, int z) {
            BaseVehicle v = null;
            if (GameClient.client) {
                GameClient.SendCommandToServer(String.format("/addvehicle %s", ScriptManager.instance.getRandomVehicleScript().getName()));
            } else if (!GameServer.server) {
                if (!StringUtils.isNullOrWhitespace(script) && ScriptManager.instance.getVehicle(script) == null) {
                    DebugLog.Lua.warn("No such vehicle script \"" + script + "\"");
                    return null;
                }
                ArrayList<VehicleScript> scripts = ScriptManager.instance.getAllVehicleScripts();
                if (scripts.isEmpty()) {
                    DebugLog.Lua.warn("No vehicle scripts defined");
                    return null;
                }
                WorldSimulation.instance.create();
                v = new BaseVehicle(IsoWorld.instance.currentCell);
                if (StringUtils.isNullOrWhitespace(script)) {
                    VehicleScript vehicleScript = (VehicleScript) PZArrayUtil.pickRandom((List) scripts);
                    script = vehicleScript.getFullName();
                }
                v.setScriptName(script);
                if (x != 0 && y != 0) {
                    v.setX(x);
                    v.setY(y);
                    v.setZ(z);
                } else {
                    v.setX(IsoPlayer.getInstance().getX());
                    v.setY(IsoPlayer.getInstance().getY());
                    v.setZ(0.0f);
                }
                if (IsoChunk.doSpawnedVehiclesInInvalidPosition(v)) {
                    v.setSquare(IsoPlayer.getInstance().getSquare());
                    v.square.chunk.vehicles.add(v);
                    v.chunk = v.square.chunk;
                    v.addToWorld();
                    VehiclesDB2.instance.addVehicle(v);
                } else {
                    DebugLog.Lua.error("ERROR: I can not spawn the vehicle. Invalid position. Try to change position.");
                }
            }
            return v;
        }

        @LuaMethod(name = "attachTrailerToPlayerVehicle", global = true)
        public static void attachTrailerToPlayerVehicle(int playerIndex) {
            IsoPlayer player = IsoPlayer.players[playerIndex];
            IsoGridSquare square = player.getCurrentSquare();
            BaseVehicle vehicleA = player.getVehicle();
            if (vehicleA == null) {
                vehicleA = addVehicleDebug("Base.OffRoad", IsoDirections.N, 0, square);
                vehicleA.repair();
                player.getInventory().AddItem(vehicleA.createVehicleKey());
            }
            BaseVehicle vehicleB = addVehicleDebug("Base.Trailer", IsoDirections.N, 0, IsoWorld.instance.currentCell.getGridSquare(square.x, square.y + 5, square.z));
            vehicleB.repair();
            vehicleA.addPointConstraint(player, vehicleB, "trailer", "trailer");
        }

        @LuaMethod(name = "getKeyName", global = true)
        public static String getKeyName(int key) {
            return Input.getKeyName(key);
        }

        @LuaMethod(name = "getKeyCode", global = true)
        public static int getKeyCode(String keyName) {
            return Input.getKeyCode(keyName);
        }

        @LuaMethod(name = "queueCharEvent", global = true)
        public static void queueCharEvent(String eventChar) {
            RenderThread.queueInvokeOnRenderContext(() -> {
                GameKeyboard.getEventQueuePolling().addCharEvent(eventChar.charAt(0));
            });
        }

        @LuaMethod(name = "queueKeyEvent", global = true)
        public static void queueKeyEvent(int lwjglKeyCode) {
            RenderThread.queueInvokeOnRenderContext(() -> {
                int glfwKeyCode = KeyCodes.toGlfwKey(lwjglKeyCode);
                GameKeyboard.getEventQueuePolling().addKeyEvent(glfwKeyCode, 1);
                GameKeyboard.getEventQueuePolling().addKeyEvent(glfwKeyCode, 0);
            });
        }

        @LuaMethod(name = "addAllVehicles", global = true)
        public static void addAllVehicles() {
            addAllVehicles(vehicleScript -> {
                return (vehicleScript.getName().contains("Smashed") || vehicleScript.getName().contains("Burnt")) ? false : true;
            });
        }

        @LuaMethod(name = "addAllBurntVehicles", global = true)
        public static void addAllBurntVehicles() {
            addAllVehicles(vehicleScript -> {
                return vehicleScript.getName().contains("Burnt");
            });
        }

        @LuaMethod(name = "addAllSmashedVehicles", global = true)
        public static void addAllSmashedVehicles() {
            addAllVehicles(vehicleScript -> {
                return vehicleScript.getName().contains("Smashed");
            });
        }

        public static void addAllVehicles(Predicate<VehicleScript> predicate) {
            ArrayList<VehicleScript> scripts = ScriptManager.instance.getAllVehicleScripts();
            scripts.sort(Comparator.comparing((v0) -> {
                return v0.getName();
            }));
            float x = IsoWorld.instance.currentCell.chunkMap[0].getWorldXMinTiles() + 5;
            float y = IsoPlayer.getInstance().getY();
            for (int i = 0; i < scripts.size(); i++) {
                VehicleScript script = scripts.get(i);
                if (script.getModel() != null && predicate.test(script) && IsoWorld.instance.currentCell.getGridSquare(x, y, Const.default_value_double) != null) {
                    WorldSimulation.instance.create();
                    BaseVehicle vehicle = new BaseVehicle(IsoWorld.instance.currentCell);
                    vehicle.setScriptName(script.getFullName());
                    vehicle.setX(x);
                    vehicle.setY(y);
                    vehicle.setZ(0.0f);
                    if (IsoChunk.doSpawnedVehiclesInInvalidPosition(vehicle)) {
                        vehicle.setSquare(IsoPlayer.getInstance().getSquare());
                        vehicle.square.chunk.vehicles.add(vehicle);
                        vehicle.chunk = vehicle.square.chunk;
                        vehicle.addToWorld();
                        VehiclesDB2.instance.addVehicle(vehicle);
                        IsoChunk.addFromCheckedVehicles(vehicle);
                    } else {
                        DebugLog.Lua.warn(script.getName() + " not spawned, position invalid");
                    }
                    x += 4.0f;
                    if (x > IsoWorld.instance.currentCell.chunkMap[0].getWorldXMaxTiles() - 5) {
                        x = IsoWorld.instance.currentCell.chunkMap[0].getWorldXMinTiles() + 5;
                        y += 8.0f;
                    }
                }
            }
        }

        @LuaMethod(name = "addPhysicsObject", global = true)
        public static BaseVehicle addPhysicsObject() {
            int id = Bullet.addPhysicsObject(getPlayer().getX(), getPlayer().getY());
            IsoMovingObject obj = new IsoPushableObject(IsoWorld.instance.getCell(), IsoPlayer.getInstance().getCurrentSquare(), IsoSpriteManager.instance.getSprite("trashcontainers_01_16"));
            WorldSimulation.instance.physicsObjectMap.put(Integer.valueOf(id), obj);
            return null;
        }

        @LuaMethod(name = "toggleVehicleRenderToTexture", global = true)
        public static void toggleVehicleRenderToTexture() {
            BaseVehicle.renderToTexture = !BaseVehicle.renderToTexture;
        }

        @LuaMethod(name = "reloadSoundFiles", global = true)
        public static void reloadSoundFiles() {
            ScriptManager.instance.ReloadScripts(EnumSet.of(ScriptType.Sound));
        }

        @LuaMethod(name = "getAnimationViewerState", global = true)
        public static AnimationViewerState getAnimationViewerState() {
            return AnimationViewerState.instance;
        }

        @LuaMethod(name = "getAttachmentEditorState", global = true)
        public static AttachmentEditorState getAttachmentEditorState() {
            return AttachmentEditorState.instance;
        }

        @LuaMethod(name = "getEditVehicleState", global = true)
        public static EditVehicleState getEditVehicleState() {
            return EditVehicleState.instance;
        }

        @LuaMethod(name = "getSpriteModelEditorState", global = true)
        public static SpriteModelEditorState getSpriteModelEditorState() {
            return SpriteModelEditorState.instance;
        }

        @LuaMethod(name = "showAnimationViewer", global = true)
        public static void showAnimationViewer() {
            IngameState.instance.showAnimationViewer = true;
        }

        @LuaMethod(name = "showAttachmentEditor", global = true)
        public static void showAttachmentEditor() {
            IngameState.instance.showAttachmentEditor = true;
        }

        @LuaMethod(name = "showChunkDebugger", global = true)
        public static void showChunkDebugger() {
            IngameState.instance.showChunkDebugger = true;
        }

        @LuaMethod(name = "getTileGeometryState", global = true)
        public static TileGeometryState getTileGeometryState() {
            return TileGeometryState.instance;
        }

        @LuaMethod(name = "showGlobalObjectDebugger", global = true)
        public static void showGlobalObjectDebugger() {
            IngameState.instance.showGlobalObjectDebugger = true;
        }

        @LuaMethod(name = "showSeamEditor", global = true)
        public static void showSeamEditor() {
            IngameState.instance.showSeamEditor = true;
        }

        @LuaMethod(name = "getSeamEditorState", global = true)
        public static SeamEditorState getSeamEditorState() {
            return SeamEditorState.instance;
        }

        @LuaMethod(name = "showSpriteModelEditor", global = true)
        public static void showSpriteModelEditor() {
            IngameState.instance.showSpriteModelEditor = true;
        }

        @LuaMethod(name = "showVehicleEditor", global = true)
        public static void showVehicleEditor(String scriptName) {
            IngameState.instance.showVehicleEditor = StringUtils.isNullOrWhitespace(scriptName) ? "" : scriptName;
        }

        @LuaMethod(name = "showWorldMapEditor", global = true)
        public static void showWorldMapEditor(String value) {
            IngameState.instance.showWorldMapEditor = StringUtils.isNullOrWhitespace(value) ? "" : value;
        }

        @LuaMethod(name = "reloadVehicles", global = true)
        public static void reloadVehicles() {
            try {
                ScriptManager.instance.ReloadScripts(EnumSet.of(ScriptType.Vehicle, ScriptType.VehicleTemplate));
                BaseVehicle.LoadAllVehicleTextures();
                for (BaseVehicle vehicle : IsoWorld.instance.currentCell.vehicles) {
                    vehicle.scriptReloaded();
                }
            } catch (Exception ex) {
                ExceptionLogger.logException(ex);
            }
        }

        @LuaMethod(name = "reloadEngineRPM", global = true)
        public static void reloadEngineRPM() {
            try {
                ScriptManager.instance.ReloadScripts(EnumSet.of(ScriptType.VehicleEngineRPM));
            } catch (Exception ex) {
                ExceptionLogger.logException(ex);
            }
        }

        @LuaMethod(name = "reloadXui", global = true)
        public static void reloadXui() {
            try {
                ScriptManager.instance.ReloadScripts(XuiManager.XUI_SCRIPT_TYPES);
            } catch (Exception ex) {
                ExceptionLogger.logException(ex);
            }
        }

        @LuaMethod(name = "reloadScripts", global = true)
        public static void reloadScripts(ScriptType type) {
            try {
                if (XuiManager.XUI_SCRIPT_TYPES.contains(type)) {
                    reloadXui();
                    return;
                }
                if (type == ScriptType.Vehicle || type == ScriptType.VehicleTemplate) {
                    reloadVehicles();
                } else if (type == ScriptType.Entity || type == ScriptType.EntityTemplate) {
                    ScriptManager.instance.ReloadScripts(EnumSet.of(ScriptType.Entity, ScriptType.EntityTemplate));
                } else {
                    ScriptManager.instance.ReloadScripts(type);
                }
            } catch (Exception ex) {
                ExceptionLogger.logException(ex);
            }
        }

        @LuaMethod(name = "reloadEntityScripts", global = true)
        public static void reloadEntityScripts() {
            try {
                EnumSet<ScriptType> types = EnumSet.noneOf(ScriptType.class);
                types.addAll(XuiManager.XUI_SCRIPT_TYPES);
                types.add(ScriptType.EntityTemplate);
                types.add(ScriptType.Entity);
                types.add(ScriptType.ItemConfig);
                types.add(ScriptType.ItemFilter);
                types.add(ScriptType.CraftRecipe);
                types.add(ScriptType.FluidFilter);
                types.add(ScriptType.StringList);
                types.add(ScriptType.EnergyDefinition);
                types.add(ScriptType.FluidDefinition);
                types.add(ScriptType.Item);
                DebugLog.General.println("Reloading entity related scripts: " + String.valueOf(types));
                ScriptManager.instance.ReloadScripts(types);
            } catch (Exception ex) {
                ExceptionLogger.logException(ex);
            }
        }

        @LuaMethod(name = "reloadEntitiesDebug", global = true)
        public static void reloadEntitiesDebug() {
            try {
                GameEntityManager.reloadDebug();
            } catch (Exception ex) {
                ExceptionLogger.logException(ex);
            }
        }

        @LuaMethod(name = "reloadEntityDebug", global = true)
        public static void reloadEntityDebug(GameEntity entity) {
            try {
                GameEntityManager.reloadDebugEntity(entity);
            } catch (Exception ex) {
                ExceptionLogger.logException(ex);
            }
        }

        @LuaMethod(name = "reloadEntityFromScriptDebug", global = true)
        public static void reloadEntityFromScriptDebug(GameEntity entity) {
            try {
                GameEntityManager.reloadEntityFromScriptDebug(entity);
            } catch (Exception ex) {
                ExceptionLogger.logException(ex);
            }
        }

        @LuaMethod(name = "getIsoEntitiesDebug", global = true)
        public static ArrayList<GameEntity> getIsoEntitiesDebug() {
            try {
                return GameEntityManager.getIsoEntitiesDebug();
            } catch (Exception ex) {
                DebugType.General.printException(ex, LogSeverity.Error);
                return null;
            }
        }

        @LuaMethod(name = "proceedPM", global = true)
        public static String proceedPM(String command) {
            Matcher m1 = Pattern.compile("(\"[^\"]*\\s+[^\"]*\"|[^\"]\\S*)\\s(.+)").matcher(command.trim());
            if (m1.matches()) {
                String username = m1.group(1);
                String msg = m1.group(2);
                String username2 = username.replaceAll("\"", "");
                ChatManager.getInstance().sendWhisperMessage(username2, msg);
                return username2;
            }
            ChatManager.getInstance().addMessage("Error", getText("IGUI_Commands_Whisper"));
            return "";
        }

        @LuaMethod(name = "processSayMessage", global = true)
        public static void processSayMessage(String message) {
            if (message == null || message.isEmpty()) {
                return;
            }
            ChatManager.getInstance().sendMessageToChat(ChatType.say, message.trim());
        }

        @LuaMethod(name = "processGeneralMessage", global = true)
        public static void processGeneralMessage(String message) {
            if (message == null || message.isEmpty()) {
                return;
            }
            ChatManager.getInstance().sendMessageToChat(ChatType.general, message.trim());
        }

        @LuaMethod(name = "processShoutMessage", global = true)
        public static void processShoutMessage(String message) {
            if (message == null || message.isEmpty()) {
                return;
            }
            ChatManager.getInstance().sendMessageToChat(ChatType.shout, message.trim());
        }

        @LuaMethod(name = "proceedFactionMessage", global = true)
        public static void ProceedFactionMessage(String message) {
            if (message == null || message.isEmpty()) {
                return;
            }
            ChatManager.getInstance().sendMessageToChat(ChatType.faction, message.trim());
        }

        @LuaMethod(name = "processSafehouseMessage", global = true)
        public static void ProcessSafehouseMessage(String message) {
            if (message == null || message.isEmpty()) {
                return;
            }
            ChatManager.getInstance().sendMessageToChat(ChatType.safehouse, message.trim());
        }

        @LuaMethod(name = "processAdminChatMessage", global = true)
        public static void ProcessAdminChatMessage(String message) {
            if (message == null || message.isEmpty()) {
                return;
            }
            ChatManager.getInstance().sendMessageToChat(ChatType.admin, message.trim());
        }

        @LuaMethod(name = "showWrongChatTabMessage", global = true)
        public static void showWrongChatTabMessage(int actualTabID, int rightTabID, String chatCommand) {
            String actualTabName = ChatManager.getInstance().getTabName((short) actualTabID);
            String rightTabName = ChatManager.getInstance().getTabName((short) rightTabID);
            String msg = Translator.getText("UI_chat_wrong_tab", actualTabName, rightTabName, chatCommand);
            ChatManager.getInstance().showServerChatMessage(msg);
        }

        @LuaMethod(name = "focusOnTab", global = true)
        public static void focusOnTab(Short id) {
            ChatManager.getInstance().focusOnTab(id);
        }

        @LuaMethod(name = "updateChatSettings", global = true)
        public static void updateChatSettings(String fontSize, boolean showTimestamp, boolean showTitle) {
            ChatManager.getInstance().updateChatSettings(fontSize, showTimestamp, showTitle);
        }

        @LuaMethod(name = "checkPlayerCanUseChat", global = true)
        public static Boolean checkPlayerCanUseChat(String chatCommand) {
            ChatType chat;
            switch (chatCommand.trim()) {
                case "/all":
                    chat = ChatType.general;
                    break;
                case "/a":
                case "/admin":
                    chat = ChatType.admin;
                    break;
                case "/s":
                case "/say":
                    chat = ChatType.say;
                    break;
                case "/y":
                case "/yell":
                    chat = ChatType.shout;
                    break;
                case "/f":
                case "/faction":
                    chat = ChatType.faction;
                    break;
                case "/sh":
                case "/safehouse":
                    chat = ChatType.safehouse;
                    break;
                case "/w":
                case "/whisper":
                    chat = ChatType.whisper;
                    break;
                case "/radio":
                case "/r":
                    chat = ChatType.radio;
                    break;
                default:
                    chat = ChatType.notDefined;
                    DebugLog.Lua.warn("Chat command not found");
                    break;
            }
            return ChatManager.getInstance().isPlayerCanUseChat(chat);
        }

        @LuaMethod(name = "reloadVehicleTextures", global = true)
        public static void reloadVehicleTextures(String scriptName) {
            VehicleScript script = ScriptManager.instance.getVehicle(scriptName);
            if (script == null) {
                DebugLog.Lua.warn("no such vehicle script");
                return;
            }
            for (int i = 0; i < script.getSkinCount(); i++) {
                VehicleScript.Skin skin = script.getSkin(i);
                if (skin.texture != null) {
                    Texture.reload("media/textures/" + skin.texture + ".png");
                }
                if (skin.textureRust != null) {
                    Texture.reload("media/textures/" + skin.textureRust + ".png");
                }
                if (skin.textureMask != null) {
                    Texture.reload("media/textures/" + skin.textureMask + ".png");
                }
                if (skin.textureLights != null) {
                    Texture.reload("media/textures/" + skin.textureLights + ".png");
                }
                if (skin.textureDamage1Overlay != null) {
                    Texture.reload("media/textures/" + skin.textureDamage1Overlay + ".png");
                }
                if (skin.textureDamage1Shell != null) {
                    Texture.reload("media/textures/" + skin.textureDamage1Shell + ".png");
                }
                if (skin.textureDamage2Overlay != null) {
                    Texture.reload("media/textures/" + skin.textureDamage2Overlay + ".png");
                }
                if (skin.textureDamage2Shell != null) {
                    Texture.reload("media/textures/" + skin.textureDamage2Shell + ".png");
                }
                if (skin.textureShadow != null) {
                    Texture.reload("media/textures/" + skin.textureShadow + ".png");
                }
            }
        }

        @LuaMethod(name = "useStaticErosionRand", global = true)
        public static void useStaticErosionRand(boolean use) {
            ErosionData.staticRand = use;
        }

        @LuaMethod(name = "getClimateManager", global = true)
        public static ClimateManager getClimateManager() {
            return ClimateManager.getInstance();
        }

        @LuaMethod(name = "getClimateMoon", global = true)
        public static ClimateMoon getClimateMoon() {
            return ClimateMoon.getInstance();
        }

        @LuaMethod(name = "getWorldMarkers", global = true)
        public static WorldMarkers getWorldMarkers() {
            return WorldMarkers.instance;
        }

        @LuaMethod(name = "getIsoMarkers", global = true)
        public static IsoMarkers getIsoMarkers() {
            return IsoMarkers.instance;
        }

        @LuaMethod(name = "getErosion", global = true)
        public static ErosionMain getErosion() {
            return ErosionMain.getInstance();
        }

        @LuaMethod(name = "getAllOutfits", global = true)
        public static ArrayList<String> getAllOutfits(boolean female) {
            ArrayList<String> result = new ArrayList<>();
            ModelManager.instance.create();
            if (OutfitManager.instance == null) {
                return result;
            }
            ArrayList<Outfit> outfits = female ? OutfitManager.instance.femaleOutfits : OutfitManager.instance.maleOutfits;
            for (Outfit outfit : outfits) {
                result.add(outfit.name);
            }
            Collections.sort(result);
            return result;
        }

        @LuaMethod(name = "getAllVehicles", global = true)
        public static ArrayList<String> getAllVehicles() {
            return (ArrayList) ScriptManager.instance.getAllVehicleScripts().stream().map((v0) -> {
                return v0.getFullName();
            }).sorted().collect(Collectors.toCollection(ArrayList::new));
        }

        @LuaMethod(name = "getAllHairStyles", global = true)
        public static ArrayList<String> getAllHairStyles(boolean female) {
            ArrayList<String> result = new ArrayList<>();
            if (HairStyles.instance == null) {
                return result;
            }
            ArrayList<HairStyle> styles = new ArrayList<>(female ? HairStyles.instance.femaleStyles : HairStyles.instance.maleStyles);
            styles.sort((o1, o2) -> {
                if (o1.name.isEmpty()) {
                    return -1;
                }
                if (o2.name.isEmpty()) {
                    return 1;
                }
                String name1 = getText("IGUI_Hair_" + o1.name);
                String name2 = getText("IGUI_Hair_" + o2.name);
                return name1.compareTo(name2);
            });
            for (HairStyle style : styles) {
                result.add(style.name);
            }
            return result;
        }

        @LuaMethod(name = "getHairStylesInstance", global = true)
        public static HairStyles getHairStylesInstance() {
            return HairStyles.instance;
        }

        @LuaMethod(name = "getBeardStylesInstance", global = true)
        public static BeardStyles getBeardStylesInstance() {
            return BeardStyles.instance;
        }

        @LuaMethod(name = "getAllBeardStyles", global = true)
        public static ArrayList<String> getAllBeardStyles() {
            ArrayList<String> result = new ArrayList<>();
            if (BeardStyles.instance == null) {
                return result;
            }
            ArrayList<BeardStyle> styles = new ArrayList<>(BeardStyles.instance.styles);
            styles.sort((o1, o2) -> {
                if (o1.name.isEmpty()) {
                    return -1;
                }
                if (o2.name.isEmpty()) {
                    return 1;
                }
                String name1 = getText("IGUI_Beard_" + o1.name);
                String name2 = getText("IGUI_Beard_" + o2.name);
                return name1.compareTo(name2);
            });
            for (BeardStyle style : styles) {
                result.add(style.name);
            }
            return result;
        }

        @LuaMethod(name = "getVoiceStylesInstance", global = true)
        public static VoiceStyles getVoiceStylesInstance() {
            return VoiceStyles.instance;
        }

        @LuaMethod(name = "getAllVoiceStyles", global = true)
        public static ArrayList<VoiceStyle> getAllVoiceStyles() {
            ArrayList<VoiceStyle> result = new ArrayList<>();
            if (VoiceStyles.instance == null) {
                return result;
            }
            ArrayList<VoiceStyle> styles = new ArrayList<>(VoiceStyles.instance.styles);
            styles.sort((o1, o2) -> {
                if (o1.name.isEmpty()) {
                    return -1;
                }
                if (o2.name.isEmpty()) {
                    return 1;
                }
                String name1 = getText("IGUI_Voice_" + o1.name);
                String name2 = getText("IGUI_Voice_" + o2.name);
                return name1.compareTo(name2);
            });
            for (VoiceStyle style : styles) {
                if (!style.name.isEmpty()) {
                    result.add(style);
                }
            }
            return result;
        }

        @LuaMethod(name = "getAllItemsForBodyLocation", global = true)
        public static KahluaTable getAllItemsForBodyLocation(String bodyLocation) {
            ItemBodyLocation bodyLocation1;
            KahluaTable result = LuaManager.platform.newTable();
            if (StringUtils.isNullOrWhitespace(bodyLocation)) {
                return result;
            }
            int index = 1;
            ArrayList<Item> items = ScriptManager.instance.getAllItems();
            for (Item item : items) {
                if (!StringUtils.isNullOrWhitespace(item.getClothingItem()) && ((bodyLocation1 = ItemBodyLocation.get(ResourceLocation.of(bodyLocation))) == item.getBodyLocation() || bodyLocation1 == item.canBeEquipped)) {
                    int i = index;
                    index++;
                    result.rawset(i, item.getFullName());
                }
            }
            return result;
        }

        @LuaMethod(name = "getAllDecalNamesForItem", global = true)
        public static ArrayList<String> getAllDecalNamesForItem(InventoryItem item) {
            ArrayList<String> result = new ArrayList<>();
            if (item == null || ClothingDecals.instance == null) {
                return result;
            }
            ClothingItem clothingItem = item.getClothingItem();
            if (clothingItem == null) {
                return result;
            }
            String groupName = clothingItem.getDecalGroup();
            if (StringUtils.isNullOrWhitespace(groupName)) {
                return result;
            }
            ClothingDecalGroup group = ClothingDecals.instance.FindGroup(groupName);
            if (group == null) {
                return result;
            }
            group.getDecals(result);
            return result;
        }

        @LuaMethod(name = "screenZoomIn", global = true)
        public void screenZoomIn() {
        }

        @LuaMethod(name = "screenZoomOut", global = true)
        public void screenZoomOut() {
        }

        @LuaMethod(name = "addSound", global = true)
        public void addSound(IsoObject source, int x, int y, int z, int radius, int volume) {
            WorldSoundManager.instance.addSound(source, x, y, z, radius, volume);
        }

        @LuaMethod(name = "sendPlaySound", global = true)
        public void sendPlaySound(String sound, boolean loop, IsoMovingObject object) {
            if (GameServer.server) {
                byte flags = loop ? (byte) 1 : (byte) 0;
                INetworkPacket.sendToRelative(PacketTypes.PacketType.PlaySound, (int) object.getX(), (int) object.getY(), sound, Byte.valueOf(flags), object);
            }
        }

        @LuaMethod(name = "sendIconFound", global = true)
        public void sendIconFound(IsoPlayer player, String type, float distanceTraveled) {
            if (GameClient.client) {
                GameClient.sendForageItemFound(player, type, distanceTraveled);
            } else {
                LuaEventManager.triggerEvent("OnItemFound", player, type, Float.valueOf(distanceTraveled));
            }
        }

        @LuaMethod(name = "getLoosingXpValue", global = true)
        public int getLoosingXpValue() {
            if (Core.debug && DebugOptions.instance.cheat.player.fastLooseXp.getValue()) {
                return -100;
            }
            return -1;
        }

        @LuaMethod(name = "getLoosingXpTick", global = true)
        public int getLoosingXpTick(Object timer) {
            if (Core.debug) {
                DebugLog.Multiplayer.noise("20000 < %s < 31000: ", timer.toString());
                if (DebugOptions.instance.cheat.player.fastLooseXp.getValue()) {
                    return 30000;
                }
                return 10;
            }
            return 10;
        }

        @LuaMethod(name = "addXpNoMultiplier", global = true)
        public void addXpNoMultiplier(IsoPlayer player, PerkFactory.Perk perk, float amount) {
            if (!player.isExistInTheWorld()) {
                return;
            }
            if (GameServer.server) {
                GameServer.addXp(player, perk, amount, true);
            } else if (!GameClient.client) {
                player.getXp().AddXP(perk, amount, true, false, false);
            }
        }

        @LuaMethod(name = "addXp", global = true)
        public void addXp(IsoPlayer player, PerkFactory.Perk perk, float amount) {
            if (!player.isExistInTheWorld()) {
                return;
            }
            if (GameServer.server) {
                GameServer.addXp(player, perk, amount);
            } else if (!GameClient.client) {
                player.getXp().AddXP(perk, amount);
            }
        }

        @LuaMethod(name = "addXpMultiplier", global = true)
        public void addXpMultiplier(IsoPlayer player, PerkFactory.Perk perk, float multiplier, int minLevel, int maxLevel) {
            if (!player.isExistInTheWorld()) {
                return;
            }
            if (GameServer.server) {
                GameServer.addXpMultiplier(player, perk, multiplier, minLevel, maxLevel);
            } else if (!GameClient.client) {
                player.getXp().addXpMultiplier(perk, multiplier, minLevel, maxLevel);
            }
        }

        @LuaMethod(name = "syncBodyPart", global = true)
        public void syncBodyPart(BodyPart bodyPart, long syncParams) {
            if (GameServer.server) {
                IsoGameCharacter parentChar = bodyPart.getParentChar();
                if (parentChar instanceof IsoPlayer) {
                    IsoPlayer parentChar2 = (IsoPlayer) parentChar;
                    INetworkPacket.send(parentChar2, PacketTypes.PacketType.BodyPartSync, bodyPart, Long.valueOf(syncParams));
                }
            }
        }

        @LuaMethod(name = "syncPlayerStats", global = true)
        public void syncPlayerStats(IsoPlayer player, int syncParams) {
            if (GameServer.server && player.isExistInTheWorld()) {
                INetworkPacket.send(player, PacketTypes.PacketType.SyncPlayerStats, player, Integer.valueOf(syncParams));
            }
        }

        @LuaMethod(name = "sendPlayerStat", global = true)
        public void sendPlayerStat(IsoPlayer player, CharacterStat stat) {
            if (GameClient.client && player.getRole() != null && player.getRole().hasCapability(Capability.CanModifyBodyStats)) {
                INetworkPacket.send(PacketTypes.PacketType.SyncPlayerStats, player, Integer.valueOf(SyncPlayerStatsPacket.getBitMaskForStat(stat)));
            }
        }

        @LuaMethod(name = "sendPlayerNutrition", global = true)
        public void sendPlayerNutrition(IsoPlayer player) {
            if (GameClient.client && player.getRole() != null && player.getRole().hasCapability(Capability.CanModifyBodyStats)) {
                INetworkPacket.send(PacketTypes.PacketType.SyncPlayerStats, player, -1);
            }
        }

        @LuaMethod(name = "SyncXp", global = true)
        public void SyncXp(IsoPlayer player) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.PlayerXp, player);
            }
        }

        @LuaMethod(name = "checkServerName", global = true)
        public String checkServerName(String name) {
            String error = ProfanityFilter.getInstance().validateString(name, true, true, true);
            if (!StringUtils.isNullOrEmpty(error)) {
                return Translator.getText("UI_BadWordCheck", error);
            }
            return null;
        }

        @LuaMethod(name = "Render3DItem", global = true)
        public void Render3DItem(InventoryItem item, IsoGridSquare sq, float xoffset, float yoffset, float zoffset, float rotation) {
            ItemModelRenderer.RenderStatus status;
            if (item == null || sq == null || (status = WorldItemModelDrawer.renderMain(item, sq, sq, xoffset, yoffset, zoffset, 0.0f, rotation, true)) == ItemModelRenderer.RenderStatus.Loading || status == ItemModelRenderer.RenderStatus.Ready) {
                return;
            }
            String str = item.getTex().getName();
            if (item.isUseWorldItem()) {
                str = item.getWorldTexture();
            }
            try {
                if (Texture.getSharedTexture(str) == null) {
                    str = item.getTex().getName();
                }
            } catch (Exception e) {
                str = "media/inventory/world/WItem_Sack.png";
            }
            Texture tex = Texture.getSharedTexture(str);
            if (tex == null) {
                return;
            }
            float scaleX = 1.0f;
            float scaleY = 1.0f;
            if (item.getScriptItem() == null) {
                float texW = tex.getWidthOrig();
                float texH = tex.getHeightOrig();
                float width = 16 * Core.tileScale;
                float height = 16 * Core.tileScale;
                if (texW > 0.0f && texH > 0.0f && width > 0.0f && height > 0.0f) {
                    float rw = (height * texW) / texH;
                    float rh = (width * texH) / texW;
                    boolean useHeight = rw <= width;
                    if (useHeight) {
                        width = rw;
                    } else {
                        height = rh;
                    }
                    scaleX = width / texW;
                    scaleY = height / texH;
                }
            } else {
                float f = item.getScriptItem().scaleWorldIcon * (Core.tileScale / 2.0f);
                scaleY = f;
                scaleX = f;
            }
            float sx = IsoUtils.XToScreen(xoffset, yoffset, zoffset, 0) - IsoCamera.frameState.offX;
            float sy = IsoUtils.YToScreen(xoffset, yoffset, zoffset, 0) - IsoCamera.frameState.offY;
            float dx = (tex.getWidthOrig() * scaleX) / 2.0f;
            float dy = ((tex.getHeightOrig() * scaleY) * 3.0f) / 4.0f;
            if (PerformanceSettings.fboRenderChunk) {
                SpriteRenderer.instance.StartShader(0, IsoCamera.frameState.playerIndex);
                IndieGL.glDepthMask(false);
                IndieGL.enableDepthTest();
                IndieGL.glDepthFunc(515);
                TextureDraw.nextZ = (IsoDepthHelper.getSquareDepthData(PZMath.fastfloor(IsoCamera.frameState.camCharacterX), PZMath.fastfloor(IsoCamera.frameState.camCharacterY), xoffset + 0.25f, yoffset + 0.25f, zoffset).depthStart * 2.0f) - 1.0f;
            }
            tex.render(sx - dx, sy - dy, tex.getWidth(), tex.getHeight(), 1.0f, 1.0f, 1.0f, 1.0f, null);
        }

        @LuaMethod(name = "getContainerOverlays", global = true)
        public ContainerOverlays getContainerOverlays() {
            return ContainerOverlays.instance;
        }

        @LuaMethod(name = "getTileOverlays", global = true)
        public TileOverlays getTileOverlays() {
            return TileOverlays.instance;
        }

        @LuaMethod(name = "NewMapBinaryFile", global = true)
        public void NewMapBinaryFile(String cmd) throws IOException {
            if ("TEST".equals(cmd)) {
                NewMapBinaryFile.SpawnBasement("basement1", PZMath.fastfloor(IsoPlayer.getInstance().getX()), PZMath.fastfloor(IsoPlayer.getInstance().getY()));
            }
        }

        @LuaMethod(name = "getAverageFPS", global = true)
        public Double getAverageFSP() {
            float fps = GameWindow.averageFPS;
            if (!PerformanceSettings.instance.isFramerateUncapped()) {
                fps = Math.min(fps, PerformanceSettings.getLockFPS());
            }
            return BoxedStaticValues.toDouble(PZMath.fastfloor(fps));
        }

        @LuaMethod(name = "getCPUTime", global = true)
        public long getCPUTime() {
            return GameWindow.getUpdateTime() / 1000000;
        }

        @LuaMethod(name = "getGPUTime", global = true)
        public long getGPUTime() {
            return RenderThread.getRenderTime() / 1000000;
        }

        @LuaMethod(name = "getCPUWait", global = true)
        public long getCPUWait() {
            return SpriteRenderer.getWaitTime() / 1000000;
        }

        @LuaMethod(name = "getGPUWait", global = true)
        public long getGPUWait() {
            return RenderThread.getWaitTime() / 1000000;
        }

        @LuaMethod(name = "getServerFPS", global = true)
        public int getServerFPS() {
            return 10;
        }

        @LuaMethod(name = "createItemTransaction", global = true)
        public static byte createItemTransaction(IsoPlayer player, InventoryItem item, ItemContainer src, ItemContainer dst) {
            if (GameClient.client) {
                return TransactionManager.createItemTransaction(player, item, src, dst);
            }
            return (byte) 0;
        }

        @Deprecated
        @LuaMethod(name = "createItemTransactionWithPosData", global = true)
        public static byte createItemTransactionWithPosData(IsoPlayer player, InventoryItem item, ItemContainer src, ItemContainer dst, String direction, float posX, float posY, float posZ) {
            IsoDirections dir = direction == null ? IsoDirections.N : IsoDirections.valueOf(direction);
            if (GameClient.client) {
                return TransactionManager.createItemTransaction(player, item, src, dst, dir, posX, posY, posZ);
            }
            return (byte) 0;
        }

        @LuaMethod(name = "changeItemTypeTransaction", global = true)
        public static byte changeItemTypeTransaction(IsoPlayer player, InventoryItem item, String itemType) {
            if (GameClient.client && item != null && !StringUtils.isNullOrEmpty(itemType)) {
                return TransactionManager.changeItemTypeTransaction(player, item, item.getContainer(), item.getContainer(), itemType);
            }
            return (byte) 0;
        }

        @LuaMethod(name = "removeItemTransaction", global = true)
        public static void removeItemTransaction(byte id, boolean isCanceled) {
            if (GameClient.client) {
                TransactionManager.removeItemTransaction(id, isCanceled);
            }
        }

        @LuaMethod(name = "isItemTransactionConsistent", global = true)
        public static boolean isItemTransactionConsistent(InventoryItem item, ItemContainer src, ItemContainer dst, String extra, IsoPlayer player) {
            if (GameClient.client) {
                int itemId = -1;
                if (item != null) {
                    itemId = item.id;
                }
                if (src.getType().equals("floor") && item.getWorldItem() != null) {
                    itemId = -1;
                }
                return TransactionManager.isConsistent(itemId, item, src, dst, extra, null, player) == 0;
            }
            return true;
        }

        @LuaMethod(name = "isItemTransactionDone", global = true)
        public static boolean isItemTransactionDone(byte id) {
            if (GameClient.client && id != 0) {
                return TransactionManager.isDone(id);
            }
            return true;
        }

        @LuaMethod(name = "isItemTransactionRejected", global = true)
        public static boolean isItemTransactionRejected(byte id) {
            if (GameClient.client && id != 0) {
                return TransactionManager.isRejected(id);
            }
            return true;
        }

        @LuaMethod(name = "getItemTransactionDuration", global = true)
        public static int getItemTransactionDuration(byte id) {
            if (GameClient.client && id != 0) {
                return TransactionManager.getDuration(id) / 20;
            }
            return -1;
        }

        @LuaMethod(name = "isActionDone", global = true)
        public static boolean isActionDone(byte id) {
            if (GameClient.client && id != 0) {
                return ActionManager.isDone(id);
            }
            return true;
        }

        @LuaMethod(name = "isActionRejected", global = true)
        public static boolean isActionRejected(byte id) {
            if (GameClient.client && id != 0) {
                return ActionManager.isRejected(id);
            }
            return true;
        }

        @LuaMethod(name = "getActionDuration", global = true)
        public static int getActionDuration(byte id) {
            if (GameClient.client && id != 0) {
                return ActionManager.getDuration(id) / 20;
            }
            return -1;
        }

        @LuaMethod(name = "removeAction", global = true)
        public static void removeAction(byte id, boolean isCanceled) {
            if (GameClient.client) {
                ActionManager.remove(id, isCanceled);
            }
        }

        @LuaMethod(name = "emulateAnimEvent", global = true)
        public static void emulateAnimEvent(NetTimedAction action, long duration, String event, String parameter) {
            if (GameServer.server) {
                AnimEventEmulator.getInstance().create(action, duration, false, event, parameter);
            }
        }

        @LuaMethod(name = "emulateAnimEventOnce", global = true)
        public static void emulateAnimEventOnce(NetTimedAction action, long duration, String event, String parameter) {
            if (GameServer.server) {
                AnimEventEmulator.getInstance().create(action, duration, true, event, parameter);
            }
        }

        @LuaMethod(name = "detectBadWords", global = true)
        public static boolean detectBadWords(String text) {
            return WordsFilter.getInstance().detectBadWords(text);
        }

        @LuaMethod(name = "profanityFilterCheck", global = true)
        public static boolean profanityFilterCheck(String text) {
            String newText = ProfanityFilter.getInstance().validateString(text);
            return !StringUtils.isNullOrEmpty(newText);
        }

        @LuaMethod(name = "showDebugInfoInChat", global = true)
        public static void showDebugInfoInChat(String msg) {
            if (GameClient.client && DebugLog.isLogEnabled(DebugType.Action, LogSeverity.Trace)) {
                ChatManager.getInstance().showServerChatMessage(msg);
            }
        }

        @LuaMethod(name = "createBuildAction", global = true)
        public static byte createBuildAction(IsoPlayer player, float x, float y, float z, boolean north, String spriteName, KahluaTable item) {
            if (GameClient.client) {
                String objectType = item.getMetatable().getString("Type");
                if (GameClient.client && DebugLog.isLogEnabled(DebugType.Action, LogSeverity.Trace)) {
                    ChatManager.getInstance().showServerChatMessage(" BUILD createBuildAction objectType:" + objectType + " spriteName:" + spriteName + " north:" + (north ? "true" : "false"));
                }
                return ActionManager.getInstance().createBuildAction(player, x, y, z, north, spriteName, item);
            }
            return (byte) 0;
        }

        @LuaMethod(name = "startFishingAction", global = true)
        public static byte startFishingAction(IsoPlayer player, InventoryItem item, IsoGridSquare sq, KahluaTable bobber) {
            if (GameClient.client) {
                return ActionManager.getInstance().createFishingAction(player, item, sq, bobber);
            }
            return (byte) 0;
        }

        @LuaMethod(name = "syncItemActivated", global = true)
        public static void syncItemActivated(IsoPlayer player, InventoryItem item) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.SyncItemActivated, player, Integer.valueOf(item.getID()), Boolean.valueOf(item.isActivated()));
            } else if (GameServer.server) {
                INetworkPacket.send(player, PacketTypes.PacketType.SyncItemActivated, player, Integer.valueOf(item.getID()), Boolean.valueOf(item.isActivated()));
            }
        }

        @LuaMethod(name = "syncItemModData", global = true)
        public void syncItemModData(IsoPlayer player, InventoryItem item) {
            if (GameServer.server) {
                INetworkPacket.sendToRelative(PacketTypes.PacketType.SyncItemModData, (int) player.getX(), (int) player.getY(), item);
            }
        }

        @LuaMethod(name = "syncItemFields", global = true)
        public void syncItemFields(IsoPlayer player, InventoryItem item) {
            if (GameClient.client) {
                INetworkPacket.send(PacketTypes.PacketType.SyncItemFields, player, item);
            } else if (GameServer.server) {
                INetworkPacket.send(player, PacketTypes.PacketType.SyncItemFields, player, item);
            }
        }

        @LuaMethod(name = "syncHandWeaponFields", global = true)
        public void syncHandWeaponFields(IsoPlayer player, HandWeapon item) {
            if (GameServer.server) {
                INetworkPacket.send(player, PacketTypes.PacketType.SyncHandWeaponFields, player, item);
            }
        }

        @LuaMethod(name = "getPickedUpFish", global = true)
        public InventoryItem getPickedUpFish(IsoPlayer player) {
            if (GameServer.server) {
                return FishingAction.getPickedUpFish(player);
            }
            return null;
        }

        @LuaMethod(name = "sendAddItemToContainer", global = true)
        public static void sendAddItemToContainer(ItemContainer container, InventoryItem item) {
            if (GameServer.server) {
                GameServer.sendAddItemToContainer(container, item);
            }
        }

        @LuaMethod(name = "sendAddItemsToContainer", global = true)
        public static void sendAddItemsToContainer(ItemContainer container, ArrayList<InventoryItem> items) {
            if (GameServer.server) {
                GameServer.sendAddItemsToContainer(container, items);
            }
        }

        @LuaMethod(name = "sendAttachedItem", global = true)
        public static void sendAttachedItem(IsoGameCharacter character, String location, InventoryItem item) {
            if (GameServer.server) {
                INetworkPacket.sendToRelative(PacketTypes.PacketType.GameCharacterAttachedItem, character.getX(), character.getY(), character, location, item);
            }
        }

        @LuaMethod(name = "sendReplaceItemInContainer", global = true)
        public static void sendReplaceItemInContainer(ItemContainer container, InventoryItem oldItem, InventoryItem newItem) {
            if (GameServer.server) {
                GameServer.sendReplaceItemInContainer(container, oldItem, newItem);
            }
        }

        @LuaMethod(name = "sendRemoveItemFromContainer", global = true)
        public static void sendRemoveItemFromContainer(ItemContainer container, InventoryItem item) {
            if (GameServer.server) {
                GameServer.sendRemoveItemFromContainer(container, item);
            }
            if (GameClient.client) {
                GameClient.sendRemoveItemFromContainer(container, item);
            }
        }

        @LuaMethod(name = "sendRemoveItemsFromContainer", global = true)
        public static void sendRemoveItemsFromContainer(ItemContainer container, ArrayList<InventoryItem> items) {
            if (GameServer.server) {
                GameServer.sendRemoveItemsFromContainer(container, items);
            }
        }

        @LuaMethod(name = "replaceItemInContainer", global = true)
        public static void replaceItemInContainer(ItemContainer container, InventoryItem oldItem, InventoryItem newItem) {
            IsoPlayer player;
            if (!GameServer.server && !GameClient.client && (player = (IsoPlayer) container.getParent()) != null) {
                ActionManager.getInstance().replaceObjectInQueuedActions(player, oldItem, newItem);
            }
        }

        @LuaMethod(name = "log", global = true)
        public static void log(DebugType type, String message) {
            type.debugln(message);
        }

        @LuaMethod(name = "teleportPlayers", global = true)
        public static void teleportPlayers(IsoPlayer player) {
            if (GameClient.client) {
                for (IsoPlayer isoPlayer : GameClient.IDToPlayerMap.values()) {
                    if (!isoPlayer.isInRange(player, IsoChunkMap.chunkGridWidth)) {
                        INetworkPacket.send(PacketTypes.PacketType.TeleportUserAction, "Teleport", isoPlayer.getUsername(), "");
                    }
                }
            }
        }

        @LuaMethod(name = "checkModsNeedUpdate", global = true)
        public static void checkModsNeedUpdate(UdpConnection connection) {
            DebugLog.Mod.println("CheckModsNeedUpdate: Checking...");
            if (SteamUtils.isSteamModeEnabled() && isServer()) {
                ArrayList<String> workshopIDs = getSteamWorkshopItemIDs();
                new ItemQueryJava(workshopIDs, connection);
            }
        }

        @LuaMethod(name = "getSearchMode", global = true)
        public static SearchMode getSearchMode() {
            return SearchMode.getInstance();
        }

        @LuaMethod(name = "transmitBigWaterSplash", global = true)
        public static void transmitBigWaterSplash(int x, int y, float dx, float dy) {
            if (GameClient.client) {
                GameClient.sendBigWaterSplash(x, y, dx, dy);
            }
            if (GameServer.server) {
                GameServer.transmitBigWaterSplash(x, y, dx, dy);
            }
        }

        @LuaMethod(name = "addAreaHighlight", global = true)
        public static void addAreaHighlight(int x1, int y1, int x2, int y2, int z, float r, float g, float b, float a) {
            FBORenderAreaHighlights.getInstance().addHighlight(x1, y1, x2, y2, z, r, g, b, a);
        }

        @LuaMethod(name = "addAreaHighlightForPlayer", global = true)
        public static void addAreaHighlightForPlayer(int playerIndex, int x1, int y1, int x2, int y2, int z, float r, float g, float b, float a) {
            FBORenderAreaHighlights.getInstance().addHighlightForPlayer(playerIndex, x1, y1, x2, y2, z, r, g, b, a);
        }

        @LuaMethod(name = "configRoomFade", global = true)
        public static void configRoomFade(float seconds, float percent) {
            FBORenderCell.blackedOutRoomFadeDurationMs = PZMath.clamp((long) Math.ceil(seconds * 1000.0f), 0L, 10000L);
            FBORenderCell.blackedOutRoomFadeBlackness = PZMath.clamp(percent, 0.0f, 1.0f);
        }

        /* JADX INFO: loaded from: projectzomboid.jar:zombie/Lua/LuaManager$GlobalObject$TimSortComparator.class */
        private static final class TimSortComparator implements Comparator<Object> {
            Object comp;

            private TimSortComparator() {
            }

            @Override // java.util.Comparator
            public int compare(Object c1, Object c2) {
                if (Objects.equals(c1, c2)) {
                    return 0;
                }
                Boolean b = LuaManager.thread.pcallBoolean(this.comp, c1, c2);
                return b == Boolean.TRUE ? -1 : 1;
            }
        }

        @LuaMethod(name = "timSort", global = true)
        public static void timSort(KahluaTable table, Object functionObject) {
            KahluaTableImpl kahluaTable = (KahluaTableImpl) Type.tryCastTo(table, KahluaTableImpl.class);
            if (kahluaTable == null || kahluaTable.len() < 2 || functionObject == null) {
                return;
            }
            timSortComparator.comp = functionObject;
            Object[] array = kahluaTable.delegate.values().toArray();
            Arrays.sort(array, timSortComparator);
            for (int i = 0; i < array.length; i++) {
                kahluaTable.rawset(i + 1, array[i]);
                array[i] = null;
            }
        }

        @LuaMethod(name = "javaListRemoveAt", global = true)
        public static Object javaListRemoveAt(List<?> javaList, int index) {
            if (javaList == null) {
                return null;
            }
            return javaList.remove(index);
        }

        @LuaMethod(name = "sendDebugStory", global = true)
        public static void sendDebugStory(IsoGridSquare square, int type, String name) {
            INetworkPacket.send(PacketTypes.PacketType.DebugStory, square, Integer.valueOf(type), name);
        }

        @LuaMethod(name = "displayLUATable", global = true)
        public static void displayLUATable(KahluaTable table) {
            DebugLog.Lua.debugln(WorldGenUtils.INSTANCE.displayTable(table));
        }

        @LuaMethod(name = "timersShowMean", global = true)
        public static void showTimers(String clazzStr) {
            WorldGenUtils.INSTANCE.showTimers(clazzStr);
        }

        @LuaMethod(name = "timersShowTotal", global = true)
        public static void showTimersTotal(String clazzStr) {
            WorldGenUtils.INSTANCE.showTimersTotal(clazzStr);
        }

        @LuaMethod(name = "timersReset", global = true)
        public static void resetTimers(String clazzStr) {
            WorldGenUtils.INSTANCE.resetTimers(clazzStr);
        }

        @LuaMethod(name = "timerGetKept", global = true)
        public static void getTimerKept(String clazzStr, String field) {
            WorldGenUtils.INSTANCE.getTimerKept(clazzStr, field);
        }

        @LuaMethod(name = "getCheatTypes", global = true)
        public static List<CheatType> getCheatTypes() {
            return Arrays.asList(CheatType.values());
        }

        /* JADX INFO: loaded from: projectzomboid.jar:zombie/Lua/LuaManager$GlobalObject$LuaFileWriter.class */
        @UsedFromLua
        public static final class LuaFileWriter {
            private final PrintWriter writer;

            public LuaFileWriter(PrintWriter writer) {
                this.writer = writer;
            }

            public void write(String str) throws IOException {
                this.writer.write(str);
            }

            public void writeln(String str) {
                this.writer.write(str);
                this.writer.write(System.lineSeparator());
            }

            public void close() throws IOException {
                this.writer.close();
            }
        }

        /* JADX INFO: loaded from: projectzomboid.jar:zombie/Lua/LuaManager$GlobalObject$ItemQuery.class */
        private static final class ItemQuery implements ISteamWorkshopCallback {
            private final LuaClosure functionObj;
            private final Object arg1;
            private final long handle;

            public ItemQuery(ArrayList<String> itemIDs, LuaClosure functionObj, Object arg1) {
                this.functionObj = functionObj;
                this.arg1 = arg1;
                long[] itemIDsArray = new long[itemIDs.size()];
                int count = 0;
                for (int i = 0; i < itemIDs.size(); i++) {
                    long id = SteamUtils.convertStringToSteamID(itemIDs.get(i));
                    if (id != -1) {
                        int i2 = count;
                        count++;
                        itemIDsArray[i2] = id;
                    }
                }
                this.handle = SteamWorkshop.instance.CreateQueryUGCDetailsRequest(itemIDsArray, this);
                if (this.handle == 0) {
                    SteamWorkshop.instance.RemoveCallback(this);
                    if (arg1 == null) {
                        LuaManager.caller.pcall(LuaManager.thread, functionObj, "NotCompleted");
                    } else {
                        LuaManager.caller.pcall(LuaManager.thread, (Object) functionObj, arg1, "NotCompleted");
                    }
                }
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemCreated(long itemID, boolean bUserNeedsToAcceptWorkshopLegalAgreement) {
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemNotCreated(int result) {
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemUpdated(boolean bUserNeedsToAcceptWorkshopLegalAgreement) {
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemNotUpdated(int result) {
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemSubscribed(long itemID) {
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemNotSubscribed(long itemID, int result) {
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemDownloaded(long itemID) {
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemNotDownloaded(long itemID, int result) {
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemQueryCompleted(long handle, int numResults) {
                if (handle != this.handle) {
                    return;
                }
                SteamWorkshop.instance.RemoveCallback(this);
                ArrayList<SteamUGCDetails> detailsList = new ArrayList<>();
                for (int i = 0; i < numResults; i++) {
                    SteamUGCDetails details = SteamWorkshop.instance.GetQueryUGCResult(handle, i);
                    if (details != null) {
                        detailsList.add(details);
                    }
                }
                SteamWorkshop.instance.ReleaseQueryUGCRequest(handle);
                if (this.arg1 == null) {
                    LuaManager.caller.pcall(LuaManager.thread, (Object) this.functionObj, "Completed", detailsList);
                } else {
                    LuaManager.caller.pcall(LuaManager.thread, (Object) this.functionObj, this.arg1, "Completed", detailsList);
                }
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemQueryNotCompleted(long handle, int result) {
                if (handle != this.handle) {
                    return;
                }
                SteamWorkshop.instance.RemoveCallback(this);
                SteamWorkshop.instance.ReleaseQueryUGCRequest(handle);
                if (this.arg1 == null) {
                    LuaManager.caller.pcall(LuaManager.thread, this.functionObj, "NotCompleted");
                } else {
                    LuaManager.caller.pcall(LuaManager.thread, (Object) this.functionObj, this.arg1, "NotCompleted");
                }
            }
        }

        /* JADX INFO: loaded from: projectzomboid.jar:zombie/Lua/LuaManager$GlobalObject$ItemQueryJava.class */
        private static final class ItemQueryJava implements ISteamWorkshopCallback {
            private final long handle;
            private final UdpConnection connection;

            public ItemQueryJava(ArrayList<String> itemIDs, UdpConnection connection) {
                this.connection = connection;
                long[] itemIDsArray = new long[itemIDs.size()];
                int count = 0;
                for (int i = 0; i < itemIDs.size(); i++) {
                    long id = SteamUtils.convertStringToSteamID(itemIDs.get(i));
                    if (id != -1) {
                        int i2 = count;
                        count++;
                        itemIDsArray[i2] = id;
                    }
                }
                this.handle = SteamWorkshop.instance.CreateQueryUGCDetailsRequest(itemIDsArray, this);
                if (this.handle == 0) {
                    SteamWorkshop.instance.RemoveCallback(this);
                    inform("CheckModsNeedUpdate: Check not completed");
                }
            }

            private void inform(String message) {
                if (this.connection != null) {
                    ChatServer.getInstance().sendMessageToServerChat(this.connection, message);
                }
                DebugLog.Mod.println(message);
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemCreated(long itemID, boolean bUserNeedsToAcceptWorkshopLegalAgreement) {
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemNotCreated(int result) {
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemUpdated(boolean bUserNeedsToAcceptWorkshopLegalAgreement) {
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemNotUpdated(int result) {
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemSubscribed(long itemID) {
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemNotSubscribed(long itemID, int result) {
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemDownloaded(long itemID) {
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemNotDownloaded(long itemID, int result) {
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemQueryCompleted(long handle, int numResults) {
                if (handle != this.handle) {
                    return;
                }
                SteamWorkshop.instance.RemoveCallback(this);
                for (int i = 0; i < numResults; i++) {
                    SteamUGCDetails details = SteamWorkshop.instance.GetQueryUGCResult(handle, i);
                    if (details != null) {
                        long id = details.getID();
                        long itemState = SteamWorkshop.instance.GetItemState(id);
                        if (SteamWorkshopItem.ItemState.Installed.and(itemState) && SteamWorkshopItem.ItemState.NeedsUpdate.not(itemState) && details.getTimeCreated() != 0 && details.getTimeUpdated() != SteamWorkshop.instance.GetItemInstallTimeStamp(id)) {
                            itemState |= (long) SteamWorkshopItem.ItemState.NeedsUpdate.getValue();
                        }
                        if (SteamWorkshopItem.ItemState.NeedsUpdate.and(itemState)) {
                            inform("CheckModsNeedUpdate: Mods need update");
                            SteamWorkshop.instance.ReleaseQueryUGCRequest(handle);
                            return;
                        }
                    }
                }
                inform("CheckModsNeedUpdate: Mods updated");
                SteamWorkshop.instance.ReleaseQueryUGCRequest(handle);
            }

            @Override // zombie.core.znet.ISteamWorkshopCallback
            public void onItemQueryNotCompleted(long handle, int result) {
                if (handle != this.handle) {
                    return;
                }
                SteamWorkshop.instance.RemoveCallback(this);
                SteamWorkshop.instance.ReleaseQueryUGCRequest(handle);
                inform("CheckModsNeedUpdate: Check not completed");
            }
        }
    }
}
