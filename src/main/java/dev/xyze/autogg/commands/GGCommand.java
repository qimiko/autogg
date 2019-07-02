package dev.xyze.autogg.commands;


import com.google.common.collect.Iterables;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import pw._2pi.autogg.util.ConfigUtil;

import java.util.Map;

import static com.mojang.brigadier.arguments.StringArgumentType.*;
import static net.minecraft.server.command.CommandManager.*;
import static com.mojang.brigadier.arguments.IntegerArgumentType.*;
import pw._2pi.autogg.gg.AutoGG;

import java.util.List;
import java.util.stream.Stream;

// fabric chat api is very undocumented, especially on the argument side of things
public class GGCommand {

    // since fabric has such little api that I literally don't know how to do colors
    //private String ColorCharacter = "§";

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {

        dispatcher.register(literal("autogg")
                .executes(ctx -> {
                    AutoGG.getInstance().getMinecraft().inGameHud.getChatHud().addMessage(new LiteralText("§cUsage: /autogg [delay <seconds> | toggle]"));
                    return 0;
                })
                .then(argument("option", greedyString())
                        .executes(ctx -> {
                            switch(getString(ctx, "option")) {
                                case "delay":
                                    AutoGG.getInstance().getMinecraft().inGameHud.getChatHud().addMessage(new LiteralText("AutoGG delay is currently " + AutoGG.getInstance().getDelay()));
                                    return 1;
                                case "toggle":
                                    AutoGG.getInstance().setToggled();
                                    AutoGG.getInstance().getMinecraft().inGameHud.getChatHud().addMessage(new LiteralText("AutoGG is now " + (AutoGG.getInstance().isToggled() ? "§2on" : "§4off")));
                                    return 1;
                                default:
                                    ctx.getSource().sendFeedback(new LiteralText("§cUsage: /autogg [delay <seconds> | toggle]"), true);
                                    return 0;
                            }
                        }).then(argument("delay", integer(1,5)).executes(ctx -> {
                            if (getString(ctx, "option") == "delay") {
                                AutoGG.getInstance().setDelay(getInteger(ctx, "delay"));
                                ConfigUtil.setConfigDelay();
                                ctx.getSource().sendFeedback(new LiteralText("§7AutoGG delay is now" + AutoGG.getInstance().getDelay()), true);
                                return 1;
                            } else {
                                AutoGG.getInstance().getMinecraft().inGameHud.getChatHud().addMessage(new LiteralText("§cUsage: /autogg [delay <seconds> | toggle]"));
                                return 0;
                            }
                        }))));
    }
}