import java.util.Random;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class LoopThread implements Runnable{
	
	private Thread t;
	private int delay;
	private int variance;
	
	private long clickTime;
	private long previousTime;
	
	// Constructor
	public LoopThread(int delay, int variance) {
		this.delay = delay;
		this.variance = variance;
	}
	
	// Run loop
	public void run() {
		try {
			clickTime = System.currentTimeMillis();
			Robot bot;
			bot = new Robot();
			bot.delay(5000);
			while (Clicker.isActive) {
				Random rand = new Random();
				int randWait = rand.nextInt(variance + 1) + delay;
				int randMDownDelay = rand.nextInt(40) + 30;

				//previousTime = clickTime;
				//clickTime = System.currentTimeMillis();
				//System.out.println("Time between clicks: "+(clickTime - previousTime));
				
				bot.mousePress(InputEvent.BUTTON1_MASK);
				bot.delay(randMDownDelay);
				bot.mouseRelease(InputEvent.BUTTON1_MASK);
				bot.delay(randWait);

			}

		} catch (AWTException e) {
			e.printStackTrace();
		}

		
	}
	
	public void start () {
		if (t == null) {
			t = new Thread(this, "clicking");
		    t.start();
		}
	}

}
