package org.mandeinait.repo.model;

import java.util.HashMap;
import java.util.Map;

/*Model the standing about one season*/
public class Standing {
	private final Season season;
	private final Map<String, PlayerStanding> standing;

	public Standing(Season season) {
		this.season = season;
		standing = new HashMap<>();
	}

	public Season getSeason() {
		return season;
	}

	public Map<String, PlayerStanding> getStanding() {
		return standing;
	}
}

class PlayerStanding {
	private final Player p;
	private final int points;

	public PlayerStanding(Player p, int points) {
		this.p = p;
		this.points = points;
	}

	public Player getP() {
		return p;
	}

	public int getPoints() {
		return points;
	}

}
