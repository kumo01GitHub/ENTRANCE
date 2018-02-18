package board;

import java.sql.Date;

public class Panel {
	private PanelDTO dto;
	private int width;
	private int height;

	public Panel(PanelDTO arg0) {
		dto = arg0;
		if (dto == null) {
			width = 0;
			height = 0;
		} else {
			switch (dto.getSize()) {
			case 'S':
				width = 1;
				height = 1;
				break;
			case 'W':
				width = 2;
				height = 1;
				break;
			case 'L':
				width = 2;
				height = 2;
				break;
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getKey() {
		return dto.getKey();
	}

	public Date getCreatedDay() {
		return dto.getCreatedDay();
	}

	public Date getLastUpdated() {
		return dto.getLastUpdated();
	}

	public char getSize() {
		return dto.getSize();
	}

	public String getPosting() {
		return dto.getPosting();
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (o.getClass() != this.getClass()) {
			return false;
		} else if (((Panel) o).getKey() == getKey()) {
			return true;
		} else {
			return false;
		}
	}

}
