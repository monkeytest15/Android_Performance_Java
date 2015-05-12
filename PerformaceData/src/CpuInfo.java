public class CpuInfo {

	private static float getTotalCPUInfo(String sn, String cpuInfo, int CpuIndex) {
		float cpu = 0;
		int start = 0;
		int cpu_index = 0;
		String[] character = null;

		try {
			String data[] = cpuInfo.split("\\s+");
			cpu = Float.parseFloat(data[CpuIndex].substring(0,
					data[CpuIndex].length() - 1));


		} catch (Exception ex) {
			cpu = 0;
		}
		return cpu;

	}

	public static int getCpuIndexInfo(String cpuInfo) {

		float cpu = 0;
		int start = 0;
		int cpu_index = 0;
		String[] character = null;

		int begin_index = cpuInfo.indexOf("PID");
		int end_index = cpuInfo.indexOf("Name");
		String line = cpuInfo.substring(begin_index, end_index);
		String test[] = line.split("\\s+");
		for (int i = 0; i < test.length; i++) {
			if (test[i].equals("CPU%")) {

				cpu_index = i;
			}
		}

		return cpu_index;

	}

	public static int getCpuIndex(String sn) {
		String TOP_CPUINFO = "/Users/monkey/Documents/dev_tool/adt-bundle-mac-x86_64-20140702/sdk/platform-tools/adb -s "
				+ sn + " shell top";
		String cpuInfo = ADBShell.sendADB(TOP_CPUINFO, 5000);
		int cpuData = getCpuIndexInfo(cpuInfo);
		return cpuData;
	}

	public static float getCpuData(String sn, String packageName, int CpuIndex) {
		String TOP_CPUINFO = "/Users/monkey/Documents/dev_tool/adt-bundle-mac-x86_64-20140702/sdk/platform-tools/adb -s "
				+ sn + " shell top | grep " + packageName;
		String cpuInfo = ADBShell.sendADB(TOP_CPUINFO, 5000);

		float cpuData = getTotalCPUInfo(sn, cpuInfo, CpuIndex);
		return cpuData;
	}
}
