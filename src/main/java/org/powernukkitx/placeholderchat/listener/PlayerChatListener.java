package org.powernukkitx.placeholderchat.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.utils.Config;
import org.powernukkitx.placeholderapi.PlaceholderAPI;
import org.powernukkitx.placeholderchat.PlaceholderChat;

import java.util.HashMap;

public class PlayerChatListener implements Listener {

    private final String chatmessage;
    private final HashMap<Player, String> messageCache = new HashMap<>();

    public PlayerChatListener() {
        Config config = PlaceholderChat.get().getConfig();
        this.chatmessage = config.getString("chatmessage");
        PlaceholderAPI.get().register("message", (player, strings) -> messageCache.getOrDefault(player, ""));
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChat(PlayerChatEvent event) {
        if(!event.isCancelled()) {
            Player player = event.getPlayer();
            this.messageCache.put(player, event.getMessage());
            event.setFormat(PlaceholderAPI.get().processPlaceholders(event.getPlayer(), chatmessage));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        messageCache.remove(event.getPlayer());
    }
}
