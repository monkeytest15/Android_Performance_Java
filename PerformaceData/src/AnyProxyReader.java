import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * 
 * This class is designed for get error info
 */
class AnyProxyReader extends Thread {
	private InputStream is;
	private String line = "";

	AnyProxyReader(InputStream is) {
		this.is = is;
	}

	public String getEvent() {
		return line;
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String temp = "";
			while ((temp = br.readLine()) != null) {
				DataPool.setBuffer(temp + "\n");
			}
		} catch (IOException e) {
			line = "";
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
