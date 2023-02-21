package org.mandeinait.repo.model;

import java.util.HashMap;
import java.util.Map;

public class SeasonRepository {

	private final Map<String, Season> repo;

	public SeasonRepository() {
		repo = new HashMap<>();
	}

	public void add(Season s) {
		repo.put(s.getName(), s);
	}

	public Season get(String seasonName) {
		return repo.get(seasonName);
	}
}
