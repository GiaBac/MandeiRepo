package org.mandeinait.repo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	public String toString() {
		return "{Result in date:" + date + ", Team1:" + t1 + "Team2:" + t2 + "}";
	}

	public boolean validate() {
		System.out.println("Validate result: " + date);

		boolean isValid = true;
		List<String> errStr = new ArrayList<>();

		// Check team size
		if (t1.getPlayers().size() != 5 || t2.getPlayers().size() != 5) {
			errStr.add("ERROR in this result: team size is wrong! " + toString());
			isValid = false;
		}

		// Check team member
		for (Player t1Player : t1.getPlayers()) {
			if (t2.getPlayers().contains(t1Player)) {
				errStr.add("ERROR in this result: same member in both team! " + toString());
				isValid = false;
			}
		}

		// Check date
		Date dateMin = new Date(2000 - 1900, 01, 01);
		Date dateMax = new Date(2100 - 1900, 01, 01);
		if (date == null || date.after(dateMax) || date.before(dateMin)) {
			errStr.add("ERROR in this result: date is out of range!\n" + toString());
			isValid = false;
		}

		if (isValid) {
			System.out.println("Validation SUCCEED!");
		} else {
			System.out.println("Validation FAIL! Errors:" + errStr);
		}

		return isValid;
	}
}
