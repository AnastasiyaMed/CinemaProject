package beans;

import java.io.Serializable;
import java.util.List;

public class Schedule implements Serializable {

	private static final long serialVersionUID = 1L;
	public static int count;
	private int showtimeID;
	private String name;
	private String time;
	private List<String> places;




	public Schedule(int showtimeID, String name, String time, List<String> places) {
		count++;
		this.showtimeID = count;
		this.name = name;
		this.time = time;
		this.places = places;
	}


	public Schedule() {
	}


	/**
	 * @return the showtimeID
	 */
	public int getShowtimeID() {
		return showtimeID;
	}

	/**
	 * @param showtimeID
	 *          the showtimeID to set
	 */
	public void setShowtimeID(int showtimeID) {
		this.showtimeID = showtimeID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *          the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *          the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}


	/**
	 * @return the places
	 */
	public List<String> getPlaces() {
		return places;
	}


	/**
	 * @param places
	 *          the places to set
	 */
	public void setPlaces(List<String> places) {
		this.places = places;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((places == null) ? 0 : places.hashCode());
		result = prime * result + showtimeID;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Schedule)) {
			return false;
		}
		Schedule other = (Schedule) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (places == null) {
			if (other.places != null) {
				return false;
			}
		} else if (!places.equals(other.places)) {
			return false;
		}
		if (showtimeID != other.showtimeID) {
			return false;
		}
		if (time == null) {
			if (other.time != null) {
				return false;
			}
		} else if (!time.equals(other.time)) {
			return false;
		}
		return true;
	}



	/*
	 * 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String placeForFile = "";
		for (String place : places) {
			placeForFile = (placeForFile + place + " ");
		}
		return (showtimeID + " " + name + " " + time + " " + placeForFile);
	}

}
