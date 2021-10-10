package org.rtkcraft.Peek.commands;




import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.rtkcraft.Peek.Main;

public class PeekCommand implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private Main plugin;
	HashMap<Player, Double[]> lista;

	

	public PeekCommand(Main plugin, HashMap<Player, Double[]> lista){
		this.plugin=plugin;
		plugin.getCommand("peek").setExecutor(this);
		this.lista=lista;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


		if (!(sender instanceof Player)) {																// checks if console uses command
			sender.sendMessage("Only players can execute this command.");
			return true;
		} else {	


			Player p = (Player) sender;
			
			if (lista.containsKey(p)==false) {
				lista.put(p, new Double[] {1d, (double) p.getLocation().getX(), (double) p.getLocation().getY(), (double) p.getLocation().getZ(), (double) p.getLocation().getYaw(), (double) p.getLocation().getPitch()} );
				p.setGameMode(GameMode.SPECTATOR);
	    		sender.sendMessage("Peek mode on");
	    		return true;
			} else{ 
				
		    	if (lista.get(p)[0]==1d) {

		    		
		    //		double x1 = lista.get(p)[1];
		    	//	double y1 = lista.get(p)[2];
		    	//	double z1 = lista.get(p)[3];
		    	//	float yaw1 = lista.get(p)[4].floatValue();
		    	//	float pitch1 = lista.get(p)[5].floatValue();

		    		
		    		double x2 = p.getLocation().getX();
		    		double y2 = p.getLocation().getY();
		    		double z2 = p.getLocation().getZ();
		    		float yaw2 = p.getLocation().getYaw();
		    		float pitch2 = p.getLocation().getPitch();
		    	    Location loc = new Location (p.getWorld(),x2,y2,z2, yaw2, pitch2);
	
			        	
			        //	Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new BukkitRunnable() {
			        		
				    	 //   int i=0;
			        	//	@Override
			        	//	public void run() {
			        		//	i++;

			        		

			        		//	loc.setX(x2-((x2-x1)*(i/30)));
			        		//	loc.setY(y2-((y2-y1)*(i/30)));
			        		//	loc.setZ(z2-((z2-z1)*(i/30)));
			        		//	loc.setYaw(yaw2-((yaw2-yaw1)*(i/30)));
			        		//	loc.setPitch(pitch2-((pitch2-pitch1)*i/30));;
							
							//p.teleport(loc);
		        			//if (i>31) {
		        		//		this.cancel();
		        		//	}
			        	//	}
			        		
			        //	},0L,20L);
			        	


			            
			       

			

					
			    		loc.setX(lista.get(p)[1]);
			    		loc.setY(lista.get(p)[2]);
			    		loc.setZ(lista.get(p)[3]);
			    		loc.setYaw(lista.get(p)[4].floatValue());
			    		loc.setPitch(lista.get(p)[5].floatValue());
			    		
						lista.get(p)[0]=0d;
						p.teleport(loc);
			    		p.setGameMode(GameMode.SURVIVAL);
						sender.sendMessage("Peek mode off");	  
	                
	                return true;
		    	
		    	} else { 
					lista.get(p)[0]=1d;
					lista.get(p)[1]=(double) p.getLocation().getX();
					lista.get(p)[2]=(double) p.getLocation().getY();
					lista.get(p)[3]=(double) p.getLocation().getZ();
					lista.get(p)[4]=(double) p.getLocation().getYaw();
					lista.get(p)[5]=(double) p.getLocation().getPitch();
					p.setGameMode(GameMode.SPECTATOR);

		    		sender.sendMessage("Peek mode on");
    		
	    		return true;
		    
		    	}
		    }
		    
		}
		    
		    
		    
		
	}
	

	

}
