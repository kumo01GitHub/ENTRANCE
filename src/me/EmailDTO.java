package me;

import common.DTO;

public class EmailDTO extends DTO {
	private String name;
	private String address;
	private String memo;

	public EmailDTO(int arg0, String arg1, String arg2, String arg3) {
		super(arg0);
		setName(arg1);
		setAddress(arg2);
		setMemo(arg3);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
