package org.kehd.scoreboardextra.tablist;

import net.minecraft.server.v1_15_R1.ChatComponentText;
import net.minecraft.server.v1_15_R1.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.kehd.scoreboardextra.main.ScoreBoardExtraCore;
import org.kehd.scoreboardextra.settings.Settings;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewTabList implements Listener {


	@EventHandler
	public void onJoin(final PlayerJoinEvent event) {

		System.out.println(Settings.Tab_List_Header);

		new BukkitRunnable() {

			final Date now = new Date();
			final SimpleDateFormat date = new SimpleDateFormat("MMM dd, yyyy");
			final SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss a");

			@Override
			public void run() {
				final PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
				final Object header = new ChatComponentText((Settings.Tab_List_Header) +
						"\nOnline Players: " + ChatColor.AQUA + Bukkit.getServer().getOnlinePlayers().size() + "\n");

				final Object footer = new ChatComponentText( "\n" + "----------------------------------" + "\n" + "" +
						ChatColor.AQUA + "Discord: " + ChatColor.RESET + "discord.gg/3JjYG6" + "\n" + "----------------------------------" + "\n" +
						ChatColor.YELLOW + date.format(now) + " - " + time.format(now));

				try {
					final Field a = packet.getClass().getDeclaredField("header");
					a.setAccessible(true);
					final Field b = packet.getClass().getDeclaredField("footer");
					a.setAccessible(true);

					a.set(packet, header);
					b.set(packet, footer);

					for (final Player player : Bukkit.getOnlinePlayers()) {
						((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);

					}

				} catch (final NoSuchFieldException | IllegalAccessException ex) {
					ex.printStackTrace();
				}

			}
		}.runTaskTimer(ScoreBoardExtraCore.getPlugin(),0, 60 * 20);

	}

}
