package VueControleur;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Modele.Game;
import Modele.Square;

public class SquareView extends JButton implements Observer
{
	private Game game;
	private Square square;
	
	private static final Icon FLAG = new ImageIcon(ClassLoader.getSystemResource("drapeau.png"));
	
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
                if(e.getButton() == MouseEvent.BUTTON3)
                {
                	square.setFlag();
                }
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                	game.discover(square);
                }
            }
            /*
            public void mouseEntered(MouseEvent e)
            {
            	super.mouseEntered(e);
            	if (!square.isMarked())
            		setBackground(Color.GRAY);
            }
            
            public void mouseExited(MouseEvent e)
            {
            	super.mouseExited(e);
            	if (!square.isMarked())
            		setBackground(Color.WHITE);
            }
            */
        });
    }

	public void update(Observable arg0, Object arg1)
	{
		if (square.isDiscovered())
		{
			if (square.isMine())
			{
				setBackground(Color.RED);
			}
			else
			{
				setBackground(Color.WHITE);
				if (square.getNbMines() != 0)
				{
					setText("" + square.getNbMines());
				}
			}
		}
		else
		{
			if (square.isMarked())
			{
		        setIcon(FLAG);
			}
			else
			{
				setBackground(Color.GRAY);
				setIcon(null);
			}
		}
	}
}
