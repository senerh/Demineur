package VueControleur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Modele.Game;
import Modele.Square;

public class SquareView extends JButton implements Observer
{
	private Game game;
	private Square square;
	private static ExecutorService executor = Executors.newFixedThreadPool(1);

	public SquareView(Game _game, Square _square)
    {
        super();
        this.
        game = _game;
        
        square = _square;
        
        square.addObserver(this);
        
        setBackground(Color.GRAY);
        setIcon(Ressources.SQUARE);
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                super.mouseClicked(e);
                if (!game.isWon() && !game.isLost())
                {
                	if(e.getButton() == MouseEvent.BUTTON3)
                    {
                		executor.execute(new Runnable()
                        {
        					@Override
        					public void run()
        					{
        						game.setFlag(square);
        					}
                        	
                        });
                    }
                    if (e.getButton() == MouseEvent.BUTTON1)
                    {
                    	executor.execute(new Runnable()
                        {
        					@Override
        					public void run()
        					{
        						game.discover(square);
        					}
                        	
                        });
                    }
                }
            }
        });
    }

	public void update(Observable arg0, Object arg1)
	{
		if (square.isDiscovered())
		{
			if (square.isMine())
			{
				//setBackground(Color.RED);
				setIcon(Ressources.MINE);
			}
			else
			{
				setBackground(Color.WHITE);
				//if (square.getNbMines() != 0)
				//{
					//setText("" + square.getNbMines());
					switch (square.getNbMines())
					{
					case 1:
						setIcon(Ressources.SQUARE1);
						break;
					case 2:
						setIcon(Ressources.SQUARE2);
						break;
					case 3:
						setIcon(Ressources.SQUARE3);
						break;
					case 4:
						setIcon(Ressources.SQUARE4);
						break;
					case 5:
						setIcon(Ressources.SQUARE5);
						break;
					case 6:
						setIcon(Ressources.SQUARE6);
						break;
					case 7:
						setIcon(Ressources.SQUARE7);
						break;
					case 8:
						setIcon(Ressources.SQUARE8);
						break;
					default:
						setIcon(Ressources.SQUAREVIDE);
						break;
					}
				//}
			}
		}
		else
		{
			if (square.isMarked())
			{
		        setIcon(Ressources.FLAG);
			}
			else
			{
				setIcon(Ressources.SQUARE);

			}
		}
	}
}
