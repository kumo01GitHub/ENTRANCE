package board;

import java.util.ArrayList;

import common.ArrayUtil;

public class ShowTable {
	private ArrayList<Panel> panels;
	public static final int WIDTH = 3;
	private ArrayList<Tile> occupy;

	public ShowTable(ArrayList<PanelDTO> arg0) {
		panels = new ArrayList<Panel>();
		for (int i = 0; i < arg0.size(); i++) {
			panels.add(new Panel(arg0.get(i)));
		}
		puzzle();
	}

	private boolean isBlank(int index) {
		if (occupy.size() <= index) {
			return true;
		} else if (occupy.get(index) == null) {
			return true;
		} else if (occupy.get(index).getPanelIndex() == Tile.BLANK) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isBlank(int[] index) {
		for (int i = 0; i < index.length; i++) {
			if (!isBlank(index[i])) {
				return false;
			}
		}
		return true;
	}

	private int nextBlank(int index) {
		int next = index + 1;
		boolean find = false;

		while (!find) {
			if (isBlank(next)) {
				find = true;
			} else {
				next++;
			}
		}

		return next;
	}

	private int[] expand(Panel panel, int index) {
		int[] location = new int[panel.getWidth() * panel.getHeight()];

		for (int i = 0; i < panel.getHeight(); i++) {
			for (int j = 0; j < panel.getWidth(); j++) {
				location[(i * panel.getWidth()) + j] = (index + (i * WIDTH)) + j;
			}
		}

		return location;
	}

	private boolean isLocatable(Panel panel, int index) {
		if (((index % WIDTH) + panel.getWidth() - 1 < WIDTH) && isBlank(expand(panel, index))) {
			return true;
		} else {
			return false;
		}
	}

	private void locate(Panel panel, int index) {
		int[] location = expand(panel, index);

		int max = ArrayUtil.max(location);
		while (occupy.size() <= max) {
			for (int i = 0; i < WIDTH; i++) {
				occupy.add(new Tile());
			}
		}

		occupy.set(location[0], new Tile(panels.indexOf(panel), false));
		for (int i = 1; i < location.length; i++) {
			occupy.set(location[i], new Tile(panels.indexOf(panel), true));
		}
	}

	private void puzzle() {
		occupy = new ArrayList<Tile>();
		int next = 0;
		for (int i = 0; i < panels.size(); i++) {
			Panel panel = panels.get(i);
			int n = next;
			while (!isLocatable(panel, n)) {
				n = nextBlank(n);
			}
			locate(panel, n);
			next = nextBlank(next - 1);
		}
	}

	public ArrayList<Panel> getRow(int index) {
		ArrayList<Panel> row = new ArrayList<Panel>();

		for (int i = 0; i < WIDTH; i++) {
			Tile tile = occupy.get(index * WIDTH + i);
			if (tile == null) {
				continue;
			} else if (tile.getPanelIndex() == Tile.BLANK) {
				continue;
			} else if (tile.isDummy()) {
				continue;
			} else {
				row.add(panels.get(tile.getPanelIndex()));
			}
		}

		return row;
	}

	public int getHeight() {
		return occupy.size() / WIDTH;
	}

}
