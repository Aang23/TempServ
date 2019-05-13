package com.aang23.tempserv;

import java.net.InetSocketAddress;
import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.server.ServerInfo;
import net.kyori.text.TextComponent;
import net.kyori.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.NonNull;

public class CommandTempServ implements Command {

    @Override
    public void execute(@NonNull CommandSource source, String[] args) {
        if (args.length > 0) {
            if (args[0].equals("add") && args.length == 4) {
                ServerInfo toRegister = new ServerInfo(args[1],
                        new InetSocketAddress(args[2], Integer.parseInt(args[3])));
                TempServ.registeredServers.put(args[1], toRegister);
                TempServ.server.registerServer(toRegister);

                source.sendMessage(
                        TextComponent.of("Registered server " + args[1] + " with adress " + args[2] + ":" + args[3])
                                .color(TextColor.GREEN));
            } else if (args[0].equals("del") && args.length == 2) {
                TempServ.server.unregisterServer(TempServ.registeredServers.get(args[1]));
                source.sendMessage(TextComponent.of("Deleted server " + args[1]).color(TextColor.GREEN));
            } else if (args[0].equals("list")) {
                for (ServerInfo current : TempServ.registeredServers.values()) {
                    source.sendMessage(
                            TextComponent.of("Server " + current.getName() + " : " + current.getAddress().toString())
                                    .color(TextColor.YELLOW));
                }
            } else
                source.sendMessage(TextComponent
                        .of("Usage : /tempserv add [name] [adress] [port] or /tempserv del [name] or /tempserv list")
                        .color(TextColor.RED));
        } else
            source.sendMessage(TextComponent
                    .of("Usage : /tempserv add [name] [adress] [port] or /tempserv del [name] or /tempserv list")
                    .color(TextColor.RED));
    }
}