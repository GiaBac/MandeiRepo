package org.mandeinait.repo.model;

import java.util.HashMap;
import java.util.Map;

public class PlayerRepository {

	Map<String, Player> repo;

	public PlayerRepository() {
		repo = new HashMap<>();
	}

	public void add(Player p) {
		repo.put(p.getName(), p);
	}

	public Player get(String playerName) {
		return repo.get(playerName);
	}
}
