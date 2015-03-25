public class DataPool {
	private static StringBuffer buffer = new StringBuffer();
	// private final ReadWriteLock lock = new ReentrantReadWriteLock();// 读写锁
	static Lock lock = new Lock();

	public static synchronized String getBuffer() throws InterruptedException {
		lock.lock();
		String str = buffer.toString();
		buffer.setLength(0);
		lock.unlock();
		return str;
	}

	public static synchronized void setBuffer(String str)
			throws InterruptedException {
		lock.lock();

		buffer.append(str);
		lock.unlock();

	}
}
