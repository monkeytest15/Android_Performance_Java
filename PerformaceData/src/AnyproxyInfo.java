public class AnyproxyInfo {

	private static String getTotalRespondInfo(String AnyProxyInfo) {
		String anyproxy_data = "";
		int start = 0;
		String[] character = null;
		boolean isTime = true;
		long diff_time = 0;

		try {

			int start_index = AnyProxyInfo.indexOf("startTime");
			int req_index = AnyProxyInfo.indexOf("reqBody");
			int end_index = AnyProxyInfo.indexOf("endTime");
			int statusCode_index = AnyProxyInfo.indexOf("statusCode");

			if (start_index != -1 && end_index != -1) {
				String start_time = AnyProxyInfo.substring(start_index,
						req_index);
				String end_time = AnyProxyInfo.substring(end_index,
						statusCode_index);
				int index1 = start_time.indexOf(":");
				int index2 = start_time.indexOf(",");
				int index3 = end_time.indexOf(":");
				int index4 = start_time.indexOf(",");
				String time1 = start_time.substring(index1 + 1, index2);
				String time2 = end_time.substring(index3 + 1, index4 - 2);
				diff_time = Long.parseLong(time2) - Long.parseLong(time1);

				System.out.println("H5网络响应时间：" + diff_time+"ms");
			}
			isTime = false;

			String data[] = AnyProxyInfo.split("\\s+");

		} catch (Exception ex) {
			anyproxy_data = "0";
		}
		return anyproxy_data;

	}

	public static String getALLData() throws InterruptedException {
		String AnyproxyInfo = DataPool.getBuffer();
		String anyproxyData = getTotalRespondInfo(AnyproxyInfo);
		return anyproxyData;
	}

	public static String StartCommand() throws InterruptedException {
		String Anyproxy_cmd = "/usr/local/bin/node /usr/local/lib/node_modules/anyproxy/bin.js --rule /Users/monkey/Documents/open_source/alipay_UIAutomation/rule_zhi.js";
		String AnyproxyInfo = ADBShell.sendCommand(Anyproxy_cmd);

		return AnyproxyInfo;
	}
}
