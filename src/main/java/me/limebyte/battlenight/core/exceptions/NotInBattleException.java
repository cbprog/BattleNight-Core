package me.limebyte.battlenight.core.exceptions;

import org.bukkit.entity.Player;

import me.limebyte.battlenight.core.Util;
import me.limebyte.battlenight.core.managers.TrackManager.Track;

/**
 * Represents a NotInBattleException.
 * 
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
@SuppressWarnings("serial")
public class NotInBattleException extends RuntimeException {
    
    ////////////////////
    //  Constructors  //
    ////////////////////
    
    /**
     * Constructs a new NotInBattleException.
     */
    public NotInBattleException(Player player) {
        Util.tellPlayer(player, Track.PLAYER_NOT_FOUND);
        return;
    }
    
}