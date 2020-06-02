package clog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Executor {
	
	public static int second = 0;
	public static ExecutorService ex = Executors.newFixedThreadPool(5);

	public static void writeLog() {
		int second = 0;
		for(;;) {
			second++;
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				break;
			}
			if(second % 10 == 0) {
				log.info(second + "秒経過");
				break;
			}
		}
	}

	public static void exitLine() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader  br = new BufferedReader(isr);
		String str = "";
		for(;;) {
			str = br.readLine();
			if(str != null){
				if(str.equals("exit")) {
					System.out.println("終了しました。");
					ex.shutdownNow();
					br.close();
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		ex.submit(Executor::writeLog);

		Executor.exitLine();
	}
}