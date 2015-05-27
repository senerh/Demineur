package VueControleur;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modele.Square;

public class SquareView extends JButton implements Observer
{
	private Square square;
	
	private static final Icon FLAG = new ImageIcon(ClassLoader.getSystemResource("drapeau.png"));
	
	public SquareView(Square _square)
    {
        super();
        
        square = _square;
        
        square.addObserver(this);
        
        setBackground(Color.white);
        
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                super.mouseClicked(e);
                if(e.getButton() == MouseEvent.BUTTON3)
                {
                	if (square.isMarked())
                	{
                		square.unMark();
                	}
                	else
                	{
                		square.mark();
                	}
                }
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                	square.discover();
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
			if (square.getNbMines() == 0)
			{
				this.setBackground(Color.WHITE);
			}
			else
			{
				this.setText("" + square.getNbMines());
			}
		}
		else
		{
			if (square.isMarked())
			{
		        this.setIcon(FLAG);
			}
			else
			{
				this.setIcon(null);
			}
		}
		if (square.isMine())
		{
			setBackground(Color.RED);
		}
	}
}
