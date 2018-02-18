package access;

import common.DTO;

public class BookmarkCategoryDTO extends DTO {
	private String name;

	public BookmarkCategoryDTO(int arg0, String arg1) {
		super(arg0);
		setName(arg1);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
