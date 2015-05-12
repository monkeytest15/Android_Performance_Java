import java.util.ArrayList;
import java.util.List;

public class VssInfo {

	private static float getTotalVSSInfo(String sn, String VSSInfo,
			int VSSIndex, String PackageName) {
		float vss = 0;
		List dataList = new ArrayList();
		List dataList2 = new ArrayList();

		try {
			String data[] = VSSInfo.split("\\s+");
			for (int i = 0; i < data.length; i++) {
				dataList.add(data[i]);
			}

			for (int i = 0; i < dataList.indexOf(PackageName); i++) {
				if (dataList.get(i).toString().contains("K")) {
					dataList2.add(dataList.get(i));
				}
			}
			vss = Float.parseFloat((dataList2.get(0).toString().substring(0,
					dataList2.get(0).toString().length() - 1)));
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

		float vssData = getTotalVSSInfo(sn, cpuInfo, VssIndex,packageName);
		return vssData;
	}
}
