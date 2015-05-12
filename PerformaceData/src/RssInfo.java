import java.util.ArrayList;
import java.util.List;

public class RssInfo {

	private static float getTotalRSSInfo(String sn, String RSSInfo, int RSSIndex,String PackageName) {
		float rss = 0;
		int start = 0;
		int rss_index = 0;
		String[] character = null;
		List dataList = new ArrayList();
		List dataList2 = new ArrayList();

		try {
			String data[] = RSSInfo.split("\\s+");
			for (int i = 0; i < data.length; i++) {
				dataList.add(data[i]);
			}

			for (int i = 0; i < dataList.indexOf(PackageName); i++) {
				if (dataList.get(i).toString().contains("K")) {
					dataList2.add(dataList.get(i));
				}
			}
			rss = Float.parseFloat((dataList2.get(1).toString().substring(0,
					dataList2.get(1).toString().length() - 1)));

		} catch (Exception ex) {
			rss = 0;
		}
		return rss;

	}

	public static int getRSSIndexInfo(String rssInfo) {

		float rss = 0;
		int start = 0;
		int rss_index = 0;
		String[] character = null;

		int begin_index = rssInfo.indexOf("PID");
		int end_index = rssInfo.indexOf("Name");
		String line = rssInfo.substring(begin_index, end_index);
		String test[] = line.split("\\s+");
		for (int i = 0; i < test.length; i++) {

			if (test[i].equals("RSS")) {

				rss_index = i;
			}
		}

		return rss_index;

	}

	public static int getRssIndex(String sn) {
		String TOP_RSSINFO = "/Users/monkey/Documents/dev_tool/adt-bundle-mac-x86_64-20140702/sdk/platform-tools/adb -s "
				+ sn + " shell top";
		String rssInfo = ADBShell.sendADB(TOP_RSSINFO, 5000);
		int rssData = getRSSIndexInfo(rssInfo);
		return rssData;
	}

	public static float getRssData(String sn, String packageName, int RssIndex) {
		String TOP_RSSINFO = "/Users/monkey/Documents/dev_tool/adt-bundle-mac-x86_64-20140702/sdk/platform-tools/adb -s "
				+ sn + " shell top | grep " + packageName;
		String rssInfo = ADBShell.sendADB(TOP_RSSINFO, 5000);

		float rssData = getTotalRSSInfo(sn, rssInfo, RssIndex,packageName);
		return rssData;
	}
}
