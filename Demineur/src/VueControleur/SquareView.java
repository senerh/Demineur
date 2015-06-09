package VueControleur;

import java.awt.Color;
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
        
        game = _game;
        
        square = _square;
        
        square.addObserver(this);
        
        setBackground(Color.GRAY);
        
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
				setBackground(Color.RED);
				setIcon(Ressources.MINE);
			}
			else
			{
				setBackground(Color.WHITE);
				if (square.getNbMines() != 0)
				{
					setText("" + square.getNbMines());
					switch (square.getNbMines())
					{
					case 1:
						setForeground(Color.BLUE);
						break;
					case 2:
						setForeground(Color.GREEN);
						break;
					case 3:
						setForeground(Color.RED);
						break;
					case 4:
						setForeground(Color.BLACK);
						break;
					case 5:
						setForeground(Color.DARK_GRAY);
						break;
					case 6:
						setForeground(Color.ORANGE);
						break;
					case 7:
						setForeground(Color.PINK);
						break;
					case 8:
						setForeground(Color.MAGENTA);
						break;
					default:
						break;
					}
				}
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
				setBackground(Color.GRAY);
				setIcon(null);
			}
		}
	}
}
