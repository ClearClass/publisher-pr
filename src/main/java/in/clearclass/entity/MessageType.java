package in.clearclass.entity;

import java.util.Random;

public enum MessageType {
	PURCHASE, SUBSCRIPTION;
	
	private static Random r = new Random();
	public static MessageType random(){
		return r.nextInt(2)==0? MessageType.PURCHASE : MessageType.SUBSCRIPTION;
	}
}