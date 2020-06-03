package clog;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;

public class MethodTest extends Executor {
	
	private StandardInputSnatcher in = new StandardInputSnatcher();
	public ExecutorService ex = Executors.newFixedThreadPool(5);
	
	@Test @SneakyThrows
	void exitLineTest() {
		System.setIn(in);
		ex.submit(Executor::writeLog);
		in.inputln("foot");
		in.inputln("exit");
		Executor.exitLine(ex);
	}
	
	@Test @SneakyThrows
	void writeLogTest() {
		Thread.sleep(5 * 1000);
		ex.submit(Executor::writeLog);
		Thread.sleep(11 * 1000);
		ex.shutdown();
		
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

