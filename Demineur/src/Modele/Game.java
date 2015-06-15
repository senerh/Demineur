package Modele;

import java.util.Observable;

public class Game extends Observable
{
	private Grid grid;
	private int nbMines;
	private int nbFlags;
	private boolean isWon;
	private boolean isLost;
	private int currentPlayer;
	private boolean isTwoPlayers;
	
	public Game()
	{
		this(10, 10, 10, false);
	}
	
	public Game(int _width, int _height, int _nbMines, boolean _isTwoPlayers)
	{
		isTwoPlayers = _isTwoPlayers;
		currentPlayer = 1;
		nbMines = _nbMines;
		grid = new Grid(_width, _height, nbMines);
		nbFlags = 0;
		isWon = false;
		isLost = false;
	}
	
	public Grid getGrid()
	{
		return grid;
	}
	
	public void discover(Square square)
	{
		if (!square.isMarked())
		{
			if (square.isMine())
			{
				isLost = true;
				square.discover();
				grid.discoverMines();
			}
			else
			{
				if (square.getNbMines() > 0)
				{
					square.discover();
				}
				else
				{
					square.discover();
					square.discoverNeighbours();
				}
				
				if (grid.getNbUndiscoveredSquares() == getNbMines())
				{
					isWon = true;
				}
				else
				{
					switchPlayer();
				}
			}
			notifier();
		}
	}

	private void switchPlayer()
	{
		if (currentPlayer == 1)
			currentPlayer = 2;
		else
			currentPlayer = 1;
	}

	public void setFlag(Square square)
	{
		if (!square.isDiscovered())
		{
			if (square.isMarked())
			{
				nbFlags--;
				square.unMark();
				notifier();
			}
			else
			{
				if (nbFlags < nbMines)
				{
					nbFlags++;
					square.mark();
					notifier();
				}
			}
		}
	}
	
	private void notifier()
	{
		setChanged();
	    notifyObservers();
	}
	
	public int getNbMines()
	{
		return nbMines;
	}
	
	public int getNbFlags()
	{
		return nbFlags;
	}
	
	public boolean isWon()
	{
		return isWon;
	}
	
	public boolean isLost()
	{
		return isLost;
	}
	
	public boolean isTwoPlayers()
	{
		return isTwoPlayers;
	}
	
	public int getCurrentPlayer()
	{
		return currentPlayer;
	}
}
