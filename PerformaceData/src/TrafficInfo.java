public class TrafficInfo {

	private static String getTotalTrafficInfo(String sn, String trafficInfo) {
		String traffic = null;
		int start = 0;
		String[] character = null;

		try {

			int index = trafficInfo.lastIndexOf("\n");
			if (index >= 0) {
				String line = trafficInfo.substring(0, index);

				// get last line
				index = line.lastIndexOf("\n");
				line = trafficInfo.substring(index + 2, line.length());
				character = line.split("\\s+");

				// System.out.println("test" + character[1]);
				// cpu = Float.parseFloat(data[CpuIndex + 1].substring(0,
				// data[CpuIndex + 1].length() - 1));
				traffic = character[1];
			}
		} catch (Exception ex) {
			traffic = "1";
		}
		return traffic;

	}

	public static String getTrafficData(String sn, String packageName) {
		String TRAFFIC_INFO = "/Users/monkey/Documents/dev_tool/adt-bundle-mac-x86_64-20140702/sdk/platform-tools/adb -s "
				+ sn + " logcat | grep " + packageName + "Traffic";
		String trafficInfo = ADBShell.sendADB(TRAFFIC_INFO, 5000);

		String TrafficData = getTotalTrafficInfo(sn, trafficInfo);
		return TrafficData;
	}
}
