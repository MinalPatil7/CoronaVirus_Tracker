package io.springboot.main.models;

public class LocationStats {
	
	private String state;
	private String country;
	private int latesttotalcases;
	private int difffromprevDay;
	
	public int getDifffromprevDay() {
		return difffromprevDay;
	}

	public void setDifffromprevDay(int difffromprevDay) {
		this.difffromprevDay = difffromprevDay;
	}


	public LocationStats() {
		// TODO Auto-generated constructor stub
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getLatesttotalcases() {
		return latesttotalcases;
	}

	public void setLatesttotalcases(int latesttotalcases) {
		this.latesttotalcases = latesttotalcases;
	}

	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", latesttotalcases=" + latesttotalcases
				+ "]";
	}

		
	
	
	

}
