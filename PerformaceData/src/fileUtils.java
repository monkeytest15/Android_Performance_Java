import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

///Users/monkey/Documents/open_source/alipay_UIAutomation

public class fileUtils {
	public static void writeFile(String path, String content, boolean append) {
		synchronized (fileUtils.class) {
			File file = new File(path);
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(file, append));
				writer.write(content);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e1) {
					}
				}
			}
		}
	}
}