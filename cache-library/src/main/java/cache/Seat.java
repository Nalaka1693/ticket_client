package cache;

public class Seat {
	/**
	 * Details of bus seat
	 */
	char row;
	int column;
	
	public Seat(int column, char row) {
		this.row = row;
		this.column = column;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return "[" + String.valueOf(column) + String.valueOf(row) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
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
		Seat other = (Seat) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
}
