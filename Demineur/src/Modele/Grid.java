package Modele;


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
				grid[i][j] = new Square(i, j);
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

	public Square getSquare(int x, int y)
	{
		return grid[x][y];
	}
}
