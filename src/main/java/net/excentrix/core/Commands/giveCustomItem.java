package net.excentrix.core.Commands;

import net.excentrix.core.utils.coreUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class giveCustomItem implements CommandExecutor {
    @NotNull
    private static ItemStack getPotato() {
        ItemStack potato = new ItemStack(Material.POISONOUS_POTATO);
        ItemMeta potatoMeta = potato.getItemMeta();
        TextComponent potatoName = Component.text("Potato")
                .color(NamedTextColor.WHITE)
                .decoration(TextDecoration.ITALIC, false);
        potatoMeta.displayName(potatoName);
        // Add the lore "You tried"
        List<Component> potatoLore = new ArrayList<>();
        potatoLore.add(Component.text("You tried")
                .color(NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false));
        potatoMeta.lore(potatoLore);
        potato.setItemMeta(potatoMeta);
        return potato;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (coreUtils.getRankInteger(commandSender.getName()) >= 3) {
            Player player = (Player) commandSender;
            if (strings.length == 0) {
                coreUtils.printUsage((Player) commandSender, "givecustomitem", "<ITEM_NAME> [QUANTITY]");
            } else {
                switch (strings[0].toUpperCase()) {
                    case "SWORD_OF_THE_UNIVERSE":
                        ItemStack SWORD_OF_THE_UNIVERSE = new ItemStack(Material.GOLDEN_SWORD);
                        ItemMeta swordOfTheUniverseMeta = SWORD_OF_THE_UNIVERSE.getItemMeta();

                        Component swordDisplayName = Component.text("")
                                .color(NamedTextColor.RED)
                                .decorate(TextDecoration.BOLD)

                                .append(Component.text("S").color(NamedTextColor.DARK_RED)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text("w").color(NamedTextColor.RED)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text("o").color(NamedTextColor.GOLD)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text("r").color(NamedTextColor.YELLOW)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text("d ").color(NamedTextColor.GREEN)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.empty())
                                .append(Component.text("o").color(NamedTextColor.AQUA)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text("f ").color(NamedTextColor.DARK_AQUA)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.empty())
                                .append(Component.text("t").color(NamedTextColor.BLUE)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text("h").color(NamedTextColor.LIGHT_PURPLE)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text("e ").color(NamedTextColor.BLUE)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.empty())
                                .append(Component.text("U").color(NamedTextColor.DARK_AQUA)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text("n").color(NamedTextColor.AQUA)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text("i").color(NamedTextColor.GREEN)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text("v").color(NamedTextColor.YELLOW)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text("e").color(NamedTextColor.GOLD)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text("r").color(NamedTextColor.RED)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text("s").color(NamedTextColor.DARK_RED)).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text("e").color(NamedTextColor.RED)).decoration(TextDecoration.ITALIC, false);
                        swordOfTheUniverseMeta.displayName(swordDisplayName);
                        swordOfTheUniverseMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        swordOfTheUniverseMeta.setUnbreakable(true);
                        List<Component> swordLore = new ArrayList<>();
                        swordLore.add(Component.text("Damage: ").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false).append(Component.text("+∞").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.LIGHT_PURPLE).decoration(TextDecoration.ITALIC, false)));
                        swordLore.add(Component.text("Skill: ").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false).append(Component.text("-∞").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.LIGHT_PURPLE).decoration(TextDecoration.ITALIC, false)));
                        swordLore.add(Component.text("Clout: ").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false).append(Component.text("+100").decoration(TextDecoration.ITALIC, false).color(NamedTextColor.LIGHT_PURPLE).decoration(TextDecoration.ITALIC, false)));
                        swordLore.add(Component.empty());
                        swordLore.add(Component.text("Sharpness L").color(NamedTextColor.BLUE).decoration(TextDecoration.ITALIC, false));
                        swordLore.add(Component.text("Increases melee damage dealt by ").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false).append(Component.text("250%").color(NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false)));
                        swordLore.add(Component.empty());
                        swordLore.add(Component.text("Oi you! Yes you. What are you").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
                        swordLore.add(Component.text("looking at? Yes, this sword has ∞").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
                        swordLore.add(Component.text("damage. Kinda overkill? I'm lazy").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
                        swordLore.add(Component.text("ok. ").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false).append(Component.text("(╯°□°)╯︵ ┻━┻").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)));
                        swordLore.add(Component.empty());
                        swordLore.add(Component.text("SPECIAL SWORD").color(NamedTextColor.RED).decoration(TextDecoration.BOLD, false));

                        swordOfTheUniverseMeta.lore(swordLore);
                        SWORD_OF_THE_UNIVERSE.setItemMeta(swordOfTheUniverseMeta);

                        if (strings.length == 2) {
                            try {
                                int quantity = Integer.parseInt(strings[1]);
                                SWORD_OF_THE_UNIVERSE.setAmount(quantity);
                            } catch (NumberFormatException exception) {
                                coreUtils.errorMessage((Player) commandSender, "Cannot process that request, retry that command again.");
                            }
                        }
                        player.getInventory().addItem(SWORD_OF_THE_UNIVERSE);
                        coreUtils.informativeMessage(player, "Poof!");
                        break;
                    default:
                        coreUtils.errorMessage((Player) commandSender, "That item doesn't exist!");
                        ItemStack potato = getPotato();
                        player.getInventory().addItem(potato);
                        break;
                }
            }
        } else coreUtils.noPerm((Player) commandSender);
        return true;
    }
}
