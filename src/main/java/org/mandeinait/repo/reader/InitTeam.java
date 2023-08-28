package org.mandeinait.repo.reader;

import org.mandeinait.repo.model.Team;

public class InitTeam {
	private final short teamColor;
	private final Team team;

	public InitTeam(short teamColor, Team team) {
		this.teamColor = teamColor;
		this.team = team;
	}

	public short getTeamColor() {
		return teamColor;
	}

	public Team getTeam() {
		return team;
	}
}
