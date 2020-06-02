//package clog;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.concurrent.Executors;
//
//import lombok.val;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class Executor {
//	public static int second = 0;
//
//	public static void main(String[] args) throws InterruptedException, IOException {
//		InputStreamReader isr = new InputStreamReader(System.in);
//		BufferedReader  br = new BufferedReader(isr);
//
//		val ex = Executors.newFixedThreadPool(5);
//
//		ex.submit(()-> {
//			int second = 0;
//			for(;;) {
//				second++;
//				Thread.sleep(1 * 1000);
//				if(second % 10 == 0) {
//					log.info(second + "秒経過");
//				}
//			}
//		});
//		String str = "";
//		for(;;) {
//			str = br.readLine();
//			System.out.println(str);
//			if(str.equals("exit")) {
//				System.out.println("終了しました。");
//				br.close();
//				ex.shutdownNow();
//				break;
//			}
//		}
//	}
//
//}