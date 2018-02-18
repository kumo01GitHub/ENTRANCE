package board;

public class Tile {
	public static final int BLANK = -1;

	private int panelIndex;
	private boolean dummy;

	public Tile() {
		panelIndex = BLANK;
		dummy = true;
	}

	public Tile(int arg0, boolean arg1) {
		panelIndex = arg0;
		dummy = arg1;
	}

	public boolean isDummy() {
		return dummy;
	}

	public int getPanelIndex() {
		return panelIndex;
	}

}
