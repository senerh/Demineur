package Modele;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Grid
{
	private int width;
	private int height;
	private Square[][] grid;
	
	public Grid(int _width, int _height)
	{
		width = _width;
		height = _height;
		
		grid = new Square[width][height];
		
		for (int i=0; i<width; i++)
		{
			for (int j=0; j<height; j++)
			{
				grid[i][j] = new Square(false);
			}
		}
		
		Random r = new Random();
		
		for (int i=0; i<50; i++)
		{
			grid[r.nextInt(width)][r.nextInt(height)].setMine();
		}
		
		for (int x=0; x<width; x++)
		{
			for (int y=0; y<height; y++)
			{
				if (x<width-1)
					grid[x][y].addNeighbour(grid[x+1][y]);
				if (x>0)
					grid[x][y].addNeighbour(grid[x-1][y]);
				if (y<height-1)
					grid[x][y].addNeighbour(grid[x][y+1]);
				if (y>0)
					grid[x][y].addNeighbour(grid[x][y-1]);
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
