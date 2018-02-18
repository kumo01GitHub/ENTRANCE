package me;

import common.DTO;

public class AccountDTO extends DTO {
	private String title;
	private String url;
	private String id;
	private String password;
	private String email;
	private String memo;

	public AccountDTO(int arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) {
		super(arg0);
		setTitle(arg1);
		setUrl(arg2);
		setId(arg3);
		setPassword(arg4);
		setEmail(arg5);
		setMemo(arg6);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
