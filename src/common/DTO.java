package common;

public class DTO {
	private int key;

	public DTO(int arg0) {
		setKey(arg0);
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (o.getClass() != this.getClass()) {
			return false;
		} else if (((DTO) o).getKey() == getKey()) {
			return true;
		} else {
			return false;
		}
	}

}
