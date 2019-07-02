package dev.xyze.autogg.commands;


import com.mojang.brigadier.CommandDispatcher;
import io.github.cottonmc.clientcommands.CottonClientCommandSource;
import net.minecraft.text.LiteralText;
import dev.xyze.autogg.util.ConfigUtil;
import dev.xyze.autogg.util.ConfigUtil.AutoGGConfig;
import static com.mojang.brigadier.arguments.StringArgumentType.*;
import static com.mojang.brigadier.arguments.IntegerArgumentType.*;
import static io.github.cottonmc.clientcommands.ArgumentBuilders.literal;
import static io.github.cottonmc.clientcommands.ArgumentBuilders.argument;

import pw._2pi.autogg.gg.AutoGG;

// too lazy to setup menu lol
public class GGCommand {
    public static void register(CommandDispatcher<CottonClientCommandSource> dispatcher) {
        dispatcher.register(
                literal("autogg")
                .then(
                        literal("delay")
                                .then(
                                        argument("seconds", integer(1,5))
                                .executes(ctx -> {
                                    AutoGG.getInstance().setDelay(getInteger(ctx, "seconds"));
                                    AutoGGConfig config = ConfigUtil.getConfig();
                                    config.delay = AutoGG.getInstance().getDelay();
                                    ConfigUtil.setConfig(config);
                                    ctx.getSource().sendFeedback(new LiteralText("§7AutoGG delay is now " + AutoGG.getInstance().getDelay()));
                                    return 1;
                                }))
                        .executes(ctx -> {
                            ctx.getSource().sendFeedback(new LiteralText("AutoGG delay is currently " + AutoGG.getInstance().getDelay()));
                            return 1;
                        })
                )
                .then(
                        literal("message")
                                .then(argument("message", greedyString()).executes(ctx -> {
                                    AutoGG.getInstance().setMessage(getString(ctx, "message"));
                                    AutoGGConfig config = ConfigUtil.getConfig();
                                    config.message = AutoGG.getInstance().getMessage();
                                    ConfigUtil.setConfig(config);
                                    ctx.getSource().sendFeedback(new LiteralText("§7AutoGG message is now " + AutoGG.getInstance().getMessage()));
                                    return 1;                                }))
                        .executes(ctx -> {
                            ctx.getSource().sendFeedback(new LiteralText("AutoGG message is currently " + AutoGG.getInstance().getMessage()));
                            return 1;
                        })
                )
                .then(
                        literal("toggle")
                .executes(ctx -> {
                    AutoGG.getInstance().setToggled();
                    ctx.getSource().sendFeedback(new LiteralText("AutoGG is now " + (AutoGG.getInstance().isToggled() ? "§2on" : "§4off")));
                    return 1;
                }))
                .executes(ctx -> {
                    ctx.getSource().sendFeedback(new LiteralText("AutoGG is currently " + (AutoGG.getInstance().isToggled() ? "§2on" : "§4off")));
                    return 1;
                })
        );
    }
}