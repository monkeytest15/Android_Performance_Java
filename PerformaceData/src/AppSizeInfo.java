public class AppSizeInfo {

	private static String[] getTotalAppSizeInfo(String sn, String AppSizeInfo) {
		float size = 0;
		int start = 0;
		String[] character = null;
		String[] results = new String[3];

		try {
			int index = AppSizeInfo.lastIndexOf("\n");
			if (index >= 0) {
				String line = AppSizeInfo.substring(0, index);

				// get last line
				index = line.lastIndexOf("\n");
				line = AppSizeInfo.substring(index + 2, line.length());
				character = line.split("\\s+");

				// totalsize = cachesize + datasize + codesize;

				// System.out.println("testetst" + character[2]); 1~3
				for (int i = 0; i < results.length; i++) {
					results[i] = character[i + 1];
				}

			}

		} catch (Exception ex) {
			size = 0;
		}
		return results;

	}

	public static String[] getAppSizeData(String sn, String packageName) {
		String TOP_SIZEINFO = "/Users/monkey/Documents/dev_tool/adt-bundle-mac-x86_64-20140702/sdk/platform-tools/adb -s "
				+ sn + " logcat | grep " + "APP_SIZE";
		String AppSizeInfo = ADBShell.sendADB(TOP_SIZEINFO, 5000);

		String[] AppSizeData = getTotalAppSizeInfo(sn, AppSizeInfo);
		return AppSizeData;
	}
}
