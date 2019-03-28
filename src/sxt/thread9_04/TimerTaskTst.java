package sxt.thread9_04;

import java.util.Timer;
import java.util.TimerTask;

// 任务调度: Timer 和TimerTask类
public class TimerTaskTst {
	public static void main(String[] args) {
		Timer timer = new Timer();

		timer.schedule(new MyTask(), 1000); // after 1000ms, execute
		timer.schedule(new MyTask(), 1000, 200); // after 1000ms, interval = 200ms, execute
	}
}

// 任务类
class MyTask extends TimerTask {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("放空大脑休息一会");
		}
		System.out.println("------end-------");
	}
}
