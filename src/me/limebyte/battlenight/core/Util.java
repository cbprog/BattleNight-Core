package me.limebyte.battlenight.core;

import me.limebyte.battlenight.core.Battle.Team;
import me.limebyte.battlenight.core.Configuration.PlayerData;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Util {
    
    // Get Main Class
    public static BattleNight plugin;
    public Util(BattleNight instance) {
        plugin = instance;
    }
	
    public String locationToString(Location loc) {
    	String w = loc.getWorld().getName();
    	double x = loc.getBlockX() + 0.5;
    	int y = loc.getBlockY();
    	double z = loc.getBlockZ() + 0.5;
    	return w + "," + x + "," + y + "," + z;
    }
    
    public Location locationFromString(String s) {
    	String part[] = s.split(",");
    	World w = Bukkit.getServer().getWorld(part[0]);
    	double x = Double.parseDouble(part[1]);
    	int y = Integer.parseInt(part[2]);
    	double z = Double.parseDouble(part[3]);
    	return new Location(w, x, y, z);
    }
	
    
    ////////////////////
    //  Chat Related  //
    ////////////////////
    
    public void tellPlayer(Player p, Tracks.Track t) {
        p.sendMessage(t.getMessage());
    }
    
    public void tellPlayers(Player[] p, Tracks.Track t) {
        for (Player aP : p) {
            aP.sendMessage(t.getMessage());
        }
    }
    
    ////////////////////
    //  Battle Util   //
    ////////////////////
    
   public void preparePlayer(Player p, Team t, Location destination) {
	   PlayerData pd = plugin.getPlayerData();
	   pd.save(p);
	   pd.reset(p, t, destination);
   }
   
   public void restorePlayer(Player p, Team t, Location destination) {
	   PlayerData pd = plugin.getPlayerData();
	   pd.reset(p, t, destination);
	   pd.restore(p);
   }
   
   ////////////////////
   // PlayerListName //
   ////////////////////
   
   public void setPlayerListName(Player p, Team t) {
	   String pListName = "�7[BN] " + p.getName();
	   p.setPlayerListName((pListName.length() < 16) ? pListName : pListName.substring(0, 16));
   }
    
}