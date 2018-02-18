package common;

public class HtmlUtil {
	public static String escape(String string) {
		if (string == null) {
			return string;
		} else {
			string = string.replaceAll("&", "&amp;");
			string = string.replaceAll("<", "&lt;");
			string = string.replaceAll(">", "&gt;");
			string = string.replaceAll("\"", "&quot;");
			string = string.replaceAll("'", "&#39;");
		}

		return string;
	}

	public static String escapeQuot(String string) {
		if (string == null) {
			return string;
		} else {
			string = string.replaceAll("\"", "&quot;");
			string = string.replaceAll("'", "&#39;");
		}

		return string;
	}
}
