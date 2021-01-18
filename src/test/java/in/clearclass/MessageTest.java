package in.clearclass;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import in.clearclass.entity.Message;
import in.clearclass.entity.MessageType;

public class MessageTest {
	final int numOfObj = 100000;
	final int poolSize = 100;
	@Test
	public void generationIdTest() throws InterruptedException, ExecutionException{
		ExecutorService es = Executors.newFixedThreadPool(poolSize);
		
		List<Future<Message>> obFutureList = new ArrayList<>(numOfObj);
		for (int i = 0; i < numOfObj; i++)
			obFutureList.add(es.submit(()->new Message(1, MessageType.PURCHASE, null)));
		
		Set<Long> idSet = new HashSet<>(numOfObj);
		for (Future<Message> obFuture : obFutureList)
			assertTrue(idSet.add(obFuture.get().getId()));
		
		es.shutdown();
	}
}