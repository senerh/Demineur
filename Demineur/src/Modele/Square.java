package Modele;

import java.util.Observable;

public class Square extends Observable
{
	private int x;
	private int y;
	private boolean isMarked;
	
	public Square(int _x, int _y)
	{
		x = _x;
		y = _y;
		isMarked = false;
	}
	
	public void mark()
	{
		isMarked = true;
		notifier();
	}
	
	public boolean isMarked()
	{
		return isMarked;
	}
	
	
	public void notifier()
	{
		setChanged();
	    notifyObservers();
	}

	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}

	public void unMark()
	{
		isMarked = false;
		notifier();
	}
}
