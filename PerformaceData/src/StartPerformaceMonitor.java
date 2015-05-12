import java.util.HashMap;

public class StartPerformaceMonitor implements Runnable {

	private String sn = "";
	private String PackageName = "";
	private String filePath = "";
	private int cpu_index = 0;
	private int vss_index = 0;
	private int rss_index = 0;

	private HashMap<String, String> resultList = new HashMap();

	private boolean isRunning = true;

	public StartPerformaceMonitor(String filePath, String sn, String PackageName) {
		this.sn = sn;
		this.PackageName = PackageName;
		this.filePath = filePath;
	}

	public void stop() {
		this.isRunning = false;

	}

	@Override
	public void run() {
		cpu_index = CpuInfo.getCpuIndex(this.sn);
		vss_index = VssInfo.getVssIndex(this.sn);
		rss_index = RssInfo.getRssIndex(this.sn);

		String anyproxyData = "";
		try {
			anyproxyData = AnyproxyInfo.StartCommand();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		while (this.isRunning) {
			try {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// anyproxy

				anyproxyData = AnyproxyInfo.getALLData();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			resultList.put("anyproxyData", anyproxyData + "");
			float pss = PssInfo.getPssData(this.sn, this.PackageName);
			resultList.put("PssMemory", pss + "kb");
			System.out.println("pss内存占用率" + pss + "kb");
			// traffic
			String traffic = TrafficInfo.getTrafficData(this.sn,
					this.PackageName);
			resultList.put("traffic", traffic);

			System.out.println("应用网络消耗流量："
					+ traffic.substring(0, traffic.length() - 2) + "kb");
			// totalsize = cachesize + datasize + codesize;

			String[] size = AppSizeInfo.getAppSizeData(this.sn,
					this.PackageName);
			resultList.put("cachesize", size[0]);
			resultList.put("datasize", size[1]);
			resultList.put("codesize", size[2]);
			System.out.println("应用缓存占用量："
					+ size[0].substring(0, size[0].length() - 2) + "kb"
					+ "应用数据占用量：" + size[1] + "kb" + "应用代码占用量" + size[2] + "kb");

			// cpu

			float cpu = CpuInfo
					.getCpuData(this.sn, this.PackageName, cpu_index);
			resultList.put("CPU", cpu + "%");

			System.out.println("cpu占用率" + cpu + "%");
			// mem
			float vss = VssInfo
					.getVssData(this.sn, this.PackageName, vss_index);
			resultList.put("VssMemory", vss + "kb");

			System.out.println("vss内存占用率" + vss + "kb");

			float rss = RssInfo
					.getRssData(this.sn, this.PackageName, rss_index);
			resultList.put("RssMemory", rss + "kb");

			System.out.println("rss内存占用率" + rss + "kb");

			try {
				Thread.sleep(4000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// write
			fileUtils.writeFile(this.filePath, JsonUtil.toJson(resultList)
					+ "\n", true);

		}
	}

	public static void main(String[] args) {

		StartPerformaceMonitor monitor = new StartPerformaceMonitor(
				"/Users/monkey/Documents/open_source/test.txt",
				"4df7849e72accf05", "com.android.chrome");
		monitor.run();

	}

}
