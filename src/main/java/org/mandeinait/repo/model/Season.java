package org.mandeinait.repo.model;

import java.util.Date;
import java.util.List;

public class Season {

	private final String name;
	
	private Date startDate;
	private Date endDate;
	private List<Result> matches;

	public Season(String name) {
		this.name = name;
	}

	public Season(String name, Date startDate, Date endDate, List<Result> matches) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.matches = matches;
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

	public List<Result> getMatches() {
		return matches;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setMatches(List<Result> matches) {
		this.matches = matches;
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

	public void elaborateResults() {
		// TODO Auto-generated method stub

	}
}
