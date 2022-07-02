package model;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
	private List<Player> list;
	public Scoreboard() {
		list = new ArrayList<Player>();
	}
	public void onPlayerStart(Player player) {
		if(!list.contains(player)) {
			player.setScore(0);
			list.add(player);
		}
	}
	public void onPlayerDie(Player player) {
		if(list.contains(player)) {
			list.remove(player);
		}
	}
}
