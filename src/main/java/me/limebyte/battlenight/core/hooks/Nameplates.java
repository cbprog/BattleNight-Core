package me.limebyte.battlenight.core.hooks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import me.limebyte.battlenight.core.BattleNight;

import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.UnknownDependencyException;

public class Nameplates {
    public static boolean init(BattleNight plugin) {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        if (pm.getPlugin("TagAPI") == null) {
            BattleNight.log.info("TagAPI not found.  Installing...");
            try {
                install(plugin, pm);
            } catch (Exception e) {
                BattleNight.log.info("Failed to install TagAPI.  Disabling...");
                return false;
            } finally {
                BattleNight.log.info("TagAPI installed successfully.");
            }
        }
        return true;
    }

    private static void install(BattleNight plugin, PluginManager pm) throws UnknownDependencyException, InvalidPluginException, InvalidDescriptionException {
        File tagAPI = new File(plugin.getDataFolder().getParent(), "TagAPI.jar");
        copyResource("TagAPI.jar", tagAPI);
        load(tagAPI);
    }

    private static void load(File file) throws UnknownDependencyException, InvalidPluginException, InvalidDescriptionException {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.loadPlugin(file);
        pm.enablePlugin(pm.getPlugin("TagAPI"));
    }

    public static void copyResource(String resource, File file) {
        InputStream in = BattleNight.instance.getResource(resource);
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
