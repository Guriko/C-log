package clog;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;

class ExecutorTest extends Executor{
	
	private StandardInputSnatcher in = new StandardInputSnatcher();
	public static ExecutorService ex = Executors.newFixedThreadPool(5);
	
//	@Test @SneakyThrows
//	void executorTest() {
//		System.setIn(in);
//		in.inputln("Hello");
//		in.inputln("exit");
//		main(new String[0]);
//	}
	
	@Test @SneakyThrows
	void timeExecutorTest() {
		//in.inputln("Hello");
		System.setIn(in);
		Executor.ex.submit(() -> {
			try {
				main(new String[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		Thread.sleep(1 * 15000);
		in.inputln("exit");
		
		Thread.sleep(1 * 5000);
	}
	
	
	public static class StandardInputSnatcher extends InputStream {

	    private StringBuilder buffer = new StringBuilder();
	    private static String crlf = System.getProperty("line.separator");

	    public void inputln(String str) {
	        buffer.append(str).append(crlf);
	    }

	    @Override
	    public int read() throws IOException {
	        if (buffer.length() == 0) {
	            return -1;
	        }
	        int result = buffer.charAt(0);
	        buffer.deleteCharAt(0);
	        return result;
	    }
	}
}
