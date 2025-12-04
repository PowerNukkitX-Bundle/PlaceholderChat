package org.powernukkitx.placeholderchat.tasks;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.PluginTask;
import cn.nukkit.utils.Config;
import org.powernukkitx.placeholderapi.PlaceholderAPI;
import org.powernukkitx.placeholderchat.PlaceholderChat;

public class PlayerNametagTask extends PluginTask<PlaceholderChat> {

    private final String nametag;

    public PlayerNametagTask() {
        super(PlaceholderChat.get());
        Config config = PlaceholderChat.get().getConfig();
        this.nametag = config.getString("nametag");
    }

    @Override
    public void onRun(int i) {
        for(Player player : Server.getInstance().getOnlinePlayers().values()) {
            String name = PlaceholderAPI.get().processPlaceholders(player, nametag);
            if(!player.getNameTag().equals(name)) {
                player.setNameTag(name);
            }
        }
    }
}
