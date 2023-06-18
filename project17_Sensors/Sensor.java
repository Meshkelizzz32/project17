package project13;
import java.util.*;
import java.util.concurrent.*;

class RadCounter{
	private int count=0;
	private Random rand=new Random();
	public synchronized int increment() {
		return count++;
	}
	public synchronized int getValues() {
		return count;
	}
}

public class Sensor implements Runnable{
public static RadCounter counter=new RadCounter();
private static List<Sensor> sensList=new ArrayList<>(); 
private int num=0;
private final int id;
private static volatile boolean canceled=false;
public static void cancel() {
	canceled=true;
}
public Sensor(int id) {
	this.id = 0;
	sensList.add(this);
	
}
	
	public void run() {
		while(!canceled) {
			synchronized(this) {
				++num;
			}
			System.out.println(this+" Total: "+counter.increment());
			try {
				TimeUnit.MILLISECONDS.sleep(30);
			}catch(InterruptedException e) {
				System.out.println("Sleep... Interrupted exc.");
			}
		}
		System.out.println("Stopping "+this);
	}
	public synchronized int values() {
		return num;
	}
	public static int totalCount() {
		return counter.getValues();
		}
	public static int sumSensors() {
		int sum=0;
		for(Sensor sens : sensList)
			sum += sens.values();
	return sum;	
	}
	public String toString() {
		return "Sensor "+id+" : "+values();
	}
}
