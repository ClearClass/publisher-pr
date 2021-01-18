package in.clearclass;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.clearclass.logic.PublishThread;

public class Main{
	private static final Logger logger = LogManager.getLogger(Main.class);
	public static void main(String[] args) throws InterruptedException{
		logger.debug("main thread started..");
		
		// массив для хранения потоковых объектов
		ArrayList<Thread> ths = new ArrayList<>();
		for (int i = 0; i < 5; i++)
			ths.add(new PublishThread());
		
		// запускаем потоки ..
		ths.stream().forEach(th->th.start());
		
		// ожидаем завершения всех потоков
		for (Thread th : ths)
			th.join();
	
		logger.debug("main thread ended..");
	}
}