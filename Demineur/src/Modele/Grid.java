package Modele;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Grid
{
	private int width;
	private int height;
	private Square[][] grid;
	
	public Grid(int _width, int _height, int nbMines)
	{
		width = _width;
		height = _height;
		
		grid = new Square[width][height];
		
		for (int i=0; i<width; i++)
		{
			for (int j=0; j<height; j++)
			{
				grid[i][j] = new Square();
			}
		}
		
		Random r = new Random();
		
		int i_random;
		int j_random;
		for (int i=0; i<nbMines; i++)
		{
			do
			{
				i_random = r.nextInt(width);
				j_random = r.nextInt(height);
			} while(grid[i_random][j_random].isMine());
			grid[i_random][j_random].setMine();
		}
		
		for (int x=0; x<width; x++)
		{
			for (int y=0; y<height; y++)
			{
				if (x<width-1)
				{
					grid[x][y].addNeighbour(grid[x+1][y]);
					if (y<height-1)
					{
						grid[x][y].addNeighbour(grid[x+1][y+1]);
					}
					if (y>0)
					{
						grid[x][y].addNeighbour(grid[x+1][y-1]);
					}
				}
				if (x>0)
				{
					grid[x][y].addNeighbour(grid[x-1][y]);
					if (y<height-1)
					{
						grid[x][y].addNeighbour(grid[x-1][y+1]);
					}
					if (y>0)
					{
						grid[x][y].addNeighbour(grid[x-1][y-1]);
					}
				}
				if (y<height-1)
				{
					grid[x][y].addNeighbour(grid[x][y+1]);
				}
				if (y>0)
				{
					grid[x][y].addNeighbour(grid[x][y-1]);
				}
			}
		}
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getSize()
	{
		return width*height;
	}

	public Square getSquare(int x, int y)
	{
		return grid[x][y];
	}
}
