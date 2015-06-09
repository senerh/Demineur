package Modele;

import java.util.Timer;
import java.util.TimerTask;

public class Chrono extends TimerTask
{
	Timer timer;
	int time;
	
	public Chrono()
	{
		time = 0;
		timer = new Timer();
		timer.schedule(this, 1000);
	}
	
	@Override
	public void run()
	{
		time++;
		timer.schedule(this, 1000);
	}
	
	public void reset()
	{
		timer.cancel();
		time = 0;
		timer.schedule(this, 1000);
	}
}
