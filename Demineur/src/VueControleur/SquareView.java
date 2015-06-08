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
                	game.setFlag(square);
                }
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                	game.discover(square);
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
