package com.aang23.tempserv;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.ServerInfo;
import org.slf4j.Logger;
import java.util.HashMap;
import java.util.Map;

@Plugin(id = "tempserv", name = "TempServ", version = "1.0", description = "A plugin", authors = { "Aang23" })
public class TempServ {
    public static ProxyServer server;
    public static Logger logger;
    public static Map<String, ServerInfo> registeredServers = new HashMap<String, ServerInfo>();

    @Inject
    public TempServ(ProxyServer lserver, CommandManager commandManager, EventManager eventManager, Logger llogger) {

        server = lserver;
        logger = llogger;
        logger.info("Loading TempServ");
        CommandMeta meta = commandManager.metaBuilder("tempserv")
                .build();
        commandManager.register(meta, new CommandTempServ());
    }
}
