package org.mandeinait.repo.model;

public class Player {

	private final String name;

	public Player(String name) {
		if (name == null) {
			throw new NullPointerException("Player name cannot be null");
		}

		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}

	public boolean equals(Object o) {
		return name.equals(o);
	}

	public int hashCode() {
		return name.hashCode();
	}
}
