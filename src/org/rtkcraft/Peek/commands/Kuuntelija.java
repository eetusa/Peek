package org.rtkcraft.Peek.commands;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.rtkcraft.Peek.Main;

public class Kuuntelija implements  Listener{
	private Main plugin;
	public HashMap<Player, Double[]> lista;
	
	public Kuuntelija (Main plugin, HashMap<Player, Double[]> lista) {
		this.plugin=plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.getPlayer().sendMessage(ChatColor.RED+""+"Tervetuloa"+ChatColor.YELLOW+""+ChatColor.BOLD+" RTKcraft"+ChatColor.RESET+""+ChatColor.DARK_GREEN+"-serverille!");
		e.getPlayer().sendMessage(ChatColor.WHITE+"Käytettävissä olevat komennot: /peek");
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		plugin.lopetaPelaaja(e.getPlayer());
		
	}
}
