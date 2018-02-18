package common;

public class ArrayUtil {
	public static int max(int[] array) {
		if (array == null) {
			throw new NullPointerException();
		} else {
			int m = array[0];
			for (int i = 1; i < array.length; i++) {
				if (m < array[i]) {
					m = array[i];
				}
			}
			return m;
		}
	}

	public static int min(int[] array) {
		if (array == null) {
			throw new NullPointerException();
		} else {
			int m = array[0];
			for (int i = 1; i < array.length; i++) {
				if (array[i] < m) {
					m = array[i];
				}
			}
			return m;
		}
	}

}
