package beans;

import java.io.Serializable;
import java.util.List;

import dao.OrderDAOImpl;
import exeption.CustomFileNotFoundExeption;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numberOfOrder;
	private String showTime;
	private List<String> placesOrder;


	/**
	 * 
	 */
	public Order() {
	}

	/**
	 * 
	 * @param showTime
	 * @param placesOrder
	 * @throws CustomFileNotFoundExeption
	 */
	public Order(String showTime, List<String> placesOrder) throws CustomFileNotFoundExeption {
		this.numberOfOrder = OrderDAOImpl.getInstance().setInitialCount();
		this.showTime = showTime;
		this.placesOrder = placesOrder;
	}



	/**
	 * @return the showTime
	 */
	public String getShowTime() {
		return showTime;
	}


	/**
	 * @param showTime
	 *          the showTime to set
	 */
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}


	/**
	 * @return the numberOfOrder
	 */
	public int getNumberOfOrder() {
		return numberOfOrder;
	}

	/**
	 * @param numberOfOrder
	 *          the numberOfOrder to set
	 */
	public void setNumberOfOrder(int numberOfOrder) {
		this.numberOfOrder = numberOfOrder;
	}

	/**
	 * @return the placesOrder
	 */
	public List<String> getPlacesOrder() {
		return placesOrder;
	}

	/**
	 * @param placesOrder
	 *          the placesOrder to set
	 */
	public void setPlacesOrder(List<String> placesOrder) {
		this.placesOrder = placesOrder;
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
		result = prime * result + numberOfOrder;
		result = prime * result + ((placesOrder == null) ? 0 : placesOrder.hashCode());
		result = prime * result + ((showTime == null) ? 0 : showTime.hashCode());
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
		if (!(obj instanceof Order)) {
			return false;
		}
		Order other = (Order) obj;
		if (numberOfOrder != other.numberOfOrder) {
			return false;
		}
		if (placesOrder == null) {
			if (other.placesOrder != null) {
				return false;
			}
		} else if (!placesOrder.equals(other.placesOrder)) {
			return false;
		}
		if (showTime == null) {
			if (other.showTime != null) {
				return false;
			}
		} else if (!showTime.equals(other.showTime)) {
			return false;
		}
		return true;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String placeForFile = "";
		for (String place : placesOrder) {
			placeForFile = (placeForFile + place + " ");
		}
		return (numberOfOrder + " " + showTime + " " + placeForFile);
	}

}

