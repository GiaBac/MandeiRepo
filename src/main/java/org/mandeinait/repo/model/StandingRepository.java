package org.mandeinait.repo.model;

import java.util.HashMap;
import java.util.Map;

public class StandingRepository {

	Map<Season, Standing> repo;

	public StandingRepository() {
		repo = new HashMap<>();
	}

	public void add(Standing s) {
		repo.put(s.getSeason(), s);
	}

	public Standing get(Season s) {
		return repo.get(s);
	}
}
