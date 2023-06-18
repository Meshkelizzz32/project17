package project13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
public static void main(String[] args) throws InterruptedException {
	ExecutorService ex=Executors.newCachedThreadPool();
	for(int i=0;i<3;i++)
		ex.execute(new Sensor(i));
	TimeUnit.SECONDS.sleep(3);
	Sensor.cancel();
	ex.shutdown();
	if(ex.awaitTermination(250, TimeUnit.MILLISECONDS))
		System.out.println("Some sensors weren't terminated");
	System.out.println("Total: "+Sensor.totalCount());
	System.out.println("Sum of Sensors: "+Sensor.sumSensors());
}
}
