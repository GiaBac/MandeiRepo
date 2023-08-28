package org.mandeinait.repo.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PlayerRepository {

	private final Map<String, Player> repo;

	public PlayerRepository() {
		repo = new HashMap<>();
	}

	public void add(Player p) {
		repo.put(p.getName(), p);
	}

	public Player get(String playerName) {

		String normalizedName = Player.getNormalizedPlayerName(playerName);

		return repo.get(normalizedName);
	}

	public String toString() {
		return repo.values().toString();
	}

	public Collection<Player> getAll() {
		return repo.values();
	}
}
