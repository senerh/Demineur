package Modele;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Square extends Observable
{
	private boolean isMarked;
	private boolean isMine;
	private List<Square> listNeighbours;
	private boolean isDiscovered;
	
	public Square()
	{
		isMine = false;
		isMarked = false;
		listNeighbours = new LinkedList<Square>();
		isDiscovered = false;
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
	
	public boolean isMine()
	{
		return isMine;
	}
	
	public void notifier()
	{
		setChanged();
	    notifyObservers();
	}

	public void unMark()
	{
		isMarked = false;
		notifier();
	}

	public void setMine()
	{
		isMine = true;
	}

	public void addNeighbour(Square square)
	{
		listNeighbours.add(square);
	}
	
	public boolean isDiscovered()
	{
		return isDiscovered;
	}
	
	public void discoverNeighbours()
	{
		if (!isMine)
		{
			isDiscovered = true;
			for (Square s : listNeighbours)
			{
				if (!s.isDiscovered() && !s.isMarked)
				{
					if (s.getNbMines() == 0)
					{
						s.discoverNeighbours();
					}
					else
					{
						s.discover();
					}
				}
			}
			notifier();
		}
	}
	
	public int getNbMines()
	{
		int nb = 0;
		for (Square s : listNeighbours)
		{
			if (s.isMine())
				nb++;
		}
		return nb;
	}
	
	public void discover()
	{
		isDiscovered = true;
		notifier();
	}
}
