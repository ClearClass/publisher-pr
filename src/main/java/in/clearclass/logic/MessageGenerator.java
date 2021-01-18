package in.clearclass.logic;

import java.time.Instant;
import java.util.Random;

import in.clearclass.entity.Message;
import in.clearclass.entity.MessageType;

public class MessageGenerator {
	private static final int msisdn = new Random().nextInt(100000);
	
	public static Message get(){
		long timestamp = Instant.now().getEpochSecond();
		return new Message(msisdn, MessageType.random(), timestamp);
	}
}