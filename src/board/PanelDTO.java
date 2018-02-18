package board;

import java.sql.Date;

import common.DTO;

public class PanelDTO extends DTO {
	private Date createdDay;
	private Date lastUpdated;
	private String posting;
	private char size;

	public PanelDTO(int arg0, Date arg1, Date arg2, String arg3, char arg4) {
		super(arg0);
		setCreatedDay(arg1);
		setLastUpdated(arg2);
		setPosting(arg3);
		setSize(arg4);
	}

	public Date getCreatedDay() {
		return createdDay;
	}

	public void setCreatedDay(Date createdDay) {
		this.createdDay = createdDay;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getPosting() {
		return posting;
	}

	public void setPosting(String posting) {
		this.posting = posting;
	}

	public char getSize() {
		return size;
	}

	public void setSize(char size) {
		this.size = size;
	}
}
