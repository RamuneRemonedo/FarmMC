package tokyo.ramune.farmmc.command;

import org.bukkit.entity.Player;

import javax.annotation.Nonnull;


public interface SubCommand {

    @Nonnull
    String getSubCommand();

    @Nonnull String getDescription();

    void onCommand(@Nonnull Player player, String[] args);
}