public class VssInfo {

	private static float getTotalVSSInfo(String sn, String VSSInfo, int VSSIndex) {
		float vss = 0;
		int start = 0;
		int vss_index = 0;
		String[] character = null;

		try {
			String data[] = VSSInfo.split("\\s+");

			vss = Float.parseFloat(data[VSSIndex + 1].substring(0,
					data[VSSIndex + 1].length() - 1));

		} catch (Exception ex) {
			vss = 0;
		}
		return vss;

	}

	public static int getVSSIndexInfo(String vssInfo) {

		float vss = 0;
		int start = 0;
		int vss_index = 0;
		String[] character = null;

		int begin_index = vssInfo.indexOf("PID");
		int end_index = vssInfo.indexOf("Name");
		String line = vssInfo.substring(begin_index, end_index);
		String test[] = line.split("\\s+");
		for (int i = 0; i < test.length; i++) {
			if (test[i].equals("VSS")) {

				vss_index = i;
			}
		}

		return vss_index;

	}

	public static int getVssIndex(String sn) {
		String TOP_CPUINFO = "/Users/monkey/Documents/dev_tool/adt-bundle-mac-x86_64-20140702/sdk/platform-tools/adb -s "
				+ sn + " shell top";
		String cpuInfo = ADBShell.sendADB(TOP_CPUINFO, 5000);
		int vssData = getVSSIndexInfo(cpuInfo);
		return vssData;
	}

	public static float getVssData(String sn, String packageName, int VssIndex) {
		String TOP_CPUINFO = "/Users/monkey/Documents/dev_tool/adt-bundle-mac-x86_64-20140702/sdk/platform-tools/adb -s "
				+ sn + " shell top | grep " + packageName;
		String cpuInfo = ADBShell.sendADB(TOP_CPUINFO, 5000);

		float vssData = getTotalVSSInfo(sn, cpuInfo, VssIndex);
		return vssData;
	}
}
