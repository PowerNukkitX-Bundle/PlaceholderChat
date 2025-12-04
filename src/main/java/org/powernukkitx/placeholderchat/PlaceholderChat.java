package org.powernukkitx.placeholderchat;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import org.powernukkitx.placeholderchat.listener.PlayerChatListener;
import org.powernukkitx.placeholderchat.tasks.PlayerNametagTask;

public class PlaceholderChat extends PluginBase {

    private static PlaceholderChat INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
        Server.getInstance().getScheduler().scheduleRepeatingTask(new PlayerNametagTask(), 1);
    }

    public static PlaceholderChat get() {
        return INSTANCE;
    }
}
