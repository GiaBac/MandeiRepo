package org.mandeinait.repo.model;

import java.util.Date;

public class Result {

	private final Team t1;
	private final Team t2;

	private final Date date;
	private final int autogol;

	public Result(Date date, Team t1, Team t2, int autogol) {
		this.date = date;
		this.t1 = t1;
		this.t2 = t2;
		this.autogol = autogol;
	}

	public Team getT1() {
		return t1;
	}

	public Team getT2() {
		return t2;
	}

	public int getAutogol() {
		return autogol;
	}

	public Date getDate() {
		return date;
	}
}
