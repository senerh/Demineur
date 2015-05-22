package VueControleur;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modele.Square;

public class SquareView extends JPanel implements Observer
{
	private Square square;
	
	private JLabel image;
	
	public SquareView(Square _square)
    {
        super();
        
        square = _square;
        
        image = new JLabel();
        
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
                //grid.Mark(x, y);
            }
            
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
        });
    }

	public void update(Observable arg0, Object arg1)
	{
		if (square.isMarked())
		{
			image.setIcon(new ImageIcon("images/drapeau.png"));	
			//setBackground(Color.black);
			add(image);
			
		}
		else
		{
			setBackground(Color.white);
		}
	}
}
