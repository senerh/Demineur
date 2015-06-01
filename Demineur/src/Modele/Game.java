package Modele;

public class Game
{
	private Grid grid;
	private int nbMines;
	private int nbFlags;
	
	public Game()
	{
		nbMines = 10;
		nbFlags = 0;
		grid = new Grid(10, 10, nbMines);
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
				//perdu
				square.discover();
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
			}
		}
	}

	public void setFlag(Square square)
	{
		if (square.isMarked())
		{
			nbFlags--;
			square.unMark();
		}
		else
		{
			if (nbFlags < nbMines)
			{
				nbFlags++;
				square.mark();
			}
		}
	}
}
