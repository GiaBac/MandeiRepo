package org.mandeinait.repo.model;

import java.util.HashSet;
import java.util.Set;

public class Team {
	private final Set<Player> players;
	private final int goals;

	public Team(int goals) {
		players = new HashSet<Player>();
		this.goals = goals;
	}

	public void addPlayer(Player p) {
		players.add(p);
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public int getGoals() {
		return goals;
	}
}
