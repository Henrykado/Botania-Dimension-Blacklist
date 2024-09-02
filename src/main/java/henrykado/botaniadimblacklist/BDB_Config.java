package henrykado.botaniadimblacklist;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Tags.MODID)
@Config(modid = Tags.MODID)
public class BDB_Config {
    //@Config.RequiresMcRestart
    @Config.Comment("List of dimensions where flowers and mushrooms won't generate in (if blacklist), \n"
            + "or the only dimensions it will generate in (if whitelist, to make it a whitelist simply make the first entry an asterisk [*])")
    @Config.Name("World gen dimension id blacklist")
    public static String[] flowerDimensionBlacklist = {};

    @SubscribeEvent
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(Tags.MODID))
        {
            ConfigManager.sync(Tags.MODID, Config.Type.INSTANCE);
        }
    }
}
