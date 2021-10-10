package org.rtkcraft.Peek;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.rtkcraft.Peek.commands.Kuuntelija;
import org.rtkcraft.Peek.commands.PeekCommand;


public class Main extends JavaPlugin{
	
	HashMap<Player, Double[]> lista = new HashMap<Player, Double[]>();
	int range = 30;
		@Override
		public void onEnable() {						

			new PeekCommand(this, lista);
			new Kuuntelija(this, lista);

			
	        BukkitScheduler scheduler = getServer().getScheduler(); // CHECK RANGE 
	        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
	            @Override
	            public void run() {
	                if (!lista.isEmpty()) {
						for (Entry<Player, Double[]> entry : lista.entrySet()) {
							if (entry.getValue()[0]==1d) {
								double entryx=entry.getKey().getLocation().getX();
								double entryy=entry.getKey().getLocation().getY();
								double entryz=entry.getKey().getLocation().getZ();
								float entryyaw = entry.getKey().getLocation().getYaw();
								float entrypitch = entry.getKey().getLocation().getPitch();
								
								if (entryx>entry.getValue()[1]+range) {																											// X
									Location loc = new Location(entry.getKey().getLocation().getWorld(),entry.getValue()[1]+(range-2), entryy, entryz, entryyaw, entrypitch);
									entry.getKey().teleport(loc);
									entry.getKey().sendMessage("Peek range is set to "+range);
								}
										
										
								if (entryx<entry.getValue()[1]-range) {
									Location loc = new Location(entry.getKey().getLocation().getWorld(),entry.getValue()[1]-(range-2), entryy, entryz, entryyaw, entrypitch);
									entry.getKey().teleport(loc);
									entry.getKey().sendMessage("Peek range is set to "+range);
								}
								
								if (entryy>entry.getValue()[2]+range) {																											// Y
									Location loc = new Location(entry.getKey().getLocation().getWorld(),entryx,entry.getValue()[2]+(range-2), entryz, entryyaw, entrypitch);
									entry.getKey().teleport(loc);
									entry.getKey().sendMessage("Peek range is set to "+range);
								}
										
										
								if (entryy<entry.getValue()[2]-range) {
									Location loc = new Location(entry.getKey().getLocation().getWorld(),entryx,entry.getValue()[2]-(range-2), entryz, entryyaw, entrypitch);
									entry.getKey().teleport(loc);
									entry.getKey().sendMessage("Peek range is set to "+range);
								}
								
								if (entryz>entry.getValue()[3]+range) {																											// Z
									Location loc = new Location(entry.getKey().getLocation().getWorld(),entryx, entryy, entry.getValue()[3]+(range-2), entryyaw, entrypitch);
									entry.getKey().teleport(loc);
									entry.getKey().sendMessage("Peek range is set to "+range);
								}
										
										
								if (entryz<entry.getValue()[3]-range) {
									Location loc = new Location(entry.getKey().getLocation().getWorld(),entryx, entryy,entry.getValue()[3]-(range-2), entryyaw, entrypitch);
									entry.getKey().teleport(loc);
									entry.getKey().sendMessage("Peek range is set to "+range);
								}
								
							}
						}
	                };
	            }
	        }, 0L, 20L);
			
		}
		
		@Override
		public void onDisable() {

			tarkistaPelaajat();
		}
		
		public void tarkistaPelaajat() {
			if (lista.isEmpty()==false) {
				for (Entry<Player, Double[]> entry : lista.entrySet()) {
					if (entry.getValue()[0]==1d) {
						System.out.println("Resetoidaan: ");
						System.out.println(""+entry.getKey()+"..");
						
						Location loc = new Location(entry.getKey().getLocation().getWorld(),entry.getValue()[1], entry.getValue()[2], entry.getValue()[3], entry.getValue()[4].floatValue(), entry.getValue()[5].floatValue());
						entry.getKey().teleport(loc);
						entry.getKey().setGameMode(GameMode.SURVIVAL);
					}
				}				
		}
			lista.clear();
		}
		
		public void lopetaPelaaja(Player p) {
			if (lista.isEmpty()==false) {
				if (lista.containsKey(p)) {
					if (lista.get(p)[0]==1d) {
						Location loc = new Location (p.getWorld(),lista.get(p)[1],lista.get(p)[2],lista.get(p)[3], lista.get(p)[4].floatValue(), lista.get(p)[5].floatValue());		    		
						lista.get(p)[0]=0d;
						p.teleport(loc);
						p.setGameMode(GameMode.SURVIVAL);
						System.out.println("Palautetaan pelaaja");
					}
				}
			}
		}
		
	
}

