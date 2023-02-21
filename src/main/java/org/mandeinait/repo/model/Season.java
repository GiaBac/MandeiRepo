package org.mandeinait.repo.model;

import java.util.Date;

public class Season {

	private final String name;
	private final Date startDate;
	private final Date endDate;

	public Season(String name, Date startDate, Date endDate) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
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
