package Modele;

public class Game
{
	private Grid grid;
	
	public Game()
	{
		grid = new Grid(10, 10);
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
}
