package access;

import common.DTO;

public class BookmarkDTO extends DTO {
	private String title;
	private String url;
	private int category;

	public BookmarkDTO(int arg0, String arg1, String arg2, int category) {
		super(arg0);
		setTitle(arg1);
		setUrl(arg2);
		setCategory(category);
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

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

}
