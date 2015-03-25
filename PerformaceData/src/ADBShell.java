import java.io.IOException;

public class ADBShell extends Thread {
	public static synchronized String sendADB(String cmd, int timeout) {
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(cmd);
			ADBReader reader = new ADBReader(process.getInputStream());
			reader.start();

			long start_time = System.currentTimeMillis();

			while (true) {
				try {
					int value = process.exitValue();
					break;
				} catch (IllegalThreadStateException e1) {
					long end_time = System.currentTimeMillis();
					if ((end_time - start_time) < timeout) {
						Thread.sleep(5000);
					} else {
						// System.out.println("process destroy");
						process.destroy();
						break;
					}
				}
			}

			String event = reader.getEvent();
			// System.out.println(event);
			if (!event.trim().equals(""))
				return event;
			// process.waitFor();
		} catch (IOException e) {
			System.out.println("Cannot run program");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public static synchronized String sendCommand(String cmd)
			throws InterruptedException {
		Process process = null;
		String event = "";
		try {
			process = Runtime.getRuntime().exec(cmd);
			AnyProxyReader reader = new AnyProxyReader(process.getInputStream());
			reader.start();

			Thread.sleep(3000);

			event = reader.getEvent();

		} catch (IOException e) {
			System.out.println("Cannot run program");
			e.printStackTrace();
		}

		return event;
	}

}
