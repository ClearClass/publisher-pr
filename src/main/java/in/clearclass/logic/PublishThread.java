package in.clearclass.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import in.clearclass.entity.Message;

public class PublishThread extends Thread {
	private static final RestTemplate restTemplate = new RestTemplate();
	private static final String url = "http://localhost:8080/message";
	private static final Logger logger = LogManager.getLogger(PublishThread.class);
	private static final long pause = 15000L;
	
	@Override
	public void run(){
		logger.debug("new thread started..");
		for (int i = 0; i < 4; i++) {
			Message msg = MessageGenerator.get();
			logger.info("msg for sending: " + msg);
			try {
				Message resp = restTemplate.postForObject(url, msg, Message.class);
				logger.info("received: " + resp);
			} catch (Exception e) {
				logger.error(e);
			}
			try {
				Thread.sleep(pause);
			} catch (InterruptedException e) {
				logger.error(e);
			}
		}
		logger.debug("thread ended..");
	}
}