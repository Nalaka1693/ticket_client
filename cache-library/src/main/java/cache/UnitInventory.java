package cache;

import java.util.Date;

public class UnitInventory {
	/**
	 * unit inventory is to hold smallest grain of combined 
	 * attributes that can be used as a unique key in the cache
	 */
	UnitJourney unitJourney;
	Date day;
	boolean direction;
	
	
	public UnitInventory(UnitJourney unitJourney, Date day, boolean direction) {
		this.unitJourney = unitJourney;
		this.day = day;
		this.direction = direction;
	}


	public UnitJourney getUnitJourney() {
		return unitJourney;
	}


	public Date getDay() {
		return day;
	}


	public boolean getDirection() {
		return direction;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + (direction ? 1231 : 1237);
		result = prime * result + ((unitJourney == null) ? 0 : unitJourney.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitInventory other = (UnitInventory) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (direction != other.direction)
			return false;
		if (unitJourney == null) {
			if (other.unitJourney != null)
				return false;
		} else if (!unitJourney.equals(other.unitJourney))
			return false;
		return true;
	}
}

