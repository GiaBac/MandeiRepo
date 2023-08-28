package org.mandeinait.repo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

	// From Alias to normalized name
	private static Map<String, String> aliasToNormalizedName;

	static {
		aliasToNormalizedName = new HashMap<>();
		aliasToNormalizedName.put("alessio", "ale giusti");
		aliasToNormalizedName.put("giusti", "ale giusti");
		aliasToNormalizedName.put("crisciani", "benza");
		aliasToNormalizedName.put("bachi", "bachi sr");
		aliasToNormalizedName.put("giacomo", "bachi sr");
		aliasToNormalizedName.put("barba", "barbieri");
		aliasToNormalizedName.put("barsa", "barsanti");
		aliasToNormalizedName.put("giancarlo", "bachi jr");
		aliasToNormalizedName.put("caneschino", "caneschi a.");
		aliasToNormalizedName.put("cecco", "ceccarelli");
		aliasToNormalizedName.put("dario", "dario l.");
		aliasToNormalizedName.put("lau", "dario l.");
		aliasToNormalizedName.put("e.micali", "e. micali");
		aliasToNormalizedName.put("enrico m.", "e. micali");
		aliasToNormalizedName.put("enrico", "e. micali");
		aliasToNormalizedName.put("micali", "e. micali");
		aliasToNormalizedName.put("elia", "elia aielli");
		aliasToNormalizedName.put("beppe m.", "b. micali");
		aliasToNormalizedName.put("beppe", "b. micali");
		aliasToNormalizedName.put("fagio", "ale fagio");
		aliasToNormalizedName.put("fagioli", "ale fagio");
		aliasToNormalizedName.put("gabri*", "gabri");
		aliasToNormalizedName.put("gabry", "gabri");
		aliasToNormalizedName.put("gimmy", "iodice");
		aliasToNormalizedName.put("iada", "iadarola");
		aliasToNormalizedName.put("iardarola", "iadarola");
		aliasToNormalizedName.put("igli", "jgli");
		aliasToNormalizedName.put("poldo*", "poldo");
		aliasToNormalizedName.put("rudolf", "ridolfi");
		aliasToNormalizedName.put("tommino", "giuliano t.");
		aliasToNormalizedName.put("sini", "ale sini");
		aliasToNormalizedName.put("simo", "caneschi s.");
		aliasToNormalizedName.put("simone", "caneschi s.");
	}

	private final String name;
	private final String firstSeasonAppareance;
	private final Map<Season, List<Result>> matchPlayed;

	public Player(String name, String seasonName) {
		if (name == null) {
			throw new NullPointerException("Player name cannot be null");
		}

		String normalizedName = getNormalizedPlayerName(name);

		this.name = normalizedName;
		this.firstSeasonAppareance = seasonName;

		matchPlayed = new HashMap<>();
	}

	public static String getNormalizedPlayerName(String name) {
		String cleanName = name.toLowerCase().trim();
		return aliasToNormalizedName.getOrDefault(cleanName, cleanName);
	}

	public void addMatch(Season season, Result result) {
		matchPlayed.computeIfAbsent(season, k -> new ArrayList<>()).add(result);
	}

	public String getName() {
		return name;
	}

	public Map<Season, List<Result>> getMatches() {
		return matchPlayed;
	}

	public String toString() {
		return "<Name:" + name + ", Season:" + firstSeasonAppareance;
	}

	public boolean equals(Object o) {
		return name.equals(o);
	}

	public int hashCode() {
		return name.hashCode();
	}
}
