package VueControleur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.border.Border;

import Modele.*;

public class Window extends JFrame implements Observer
{
	private Game game;
	private JComponent grid;
	private JLabel label;
	
    public Window(Game _game)
    {
        super();
        
        game = _game;
        game.addObserver(this);
        
        build();
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent arg0)
            {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
    }
    
    public void build()
    {
        //JMenu jm = new JMenu();
        JMenuBar jm = new JMenuBar();
        
        JMenu m = new JMenu("Nouvelle Partie");
        
        JMenuItem mi = new JMenuItem("Facile");
        
        //ItemListener i = new Item
        
        m.add(mi);
        
        jm.add(m);
        
        setJMenuBar(jm);
        
        setTitle("Demineur de l'espace");
        setSize(400, 400);
        label = new JLabel("Démineur du futur !!!");
        JPanel panel = new JPanel();
        panel.add(label);
        grid = new JPanel (new GridLayout(game.getGrid().getHeight(), game.getGrid().getWidth()));
        Border blackline = BorderFactory.createLineBorder(Color.black,1);
        
        for(int i = 0; i<game.getGrid().getWidth(); i++)
        {
        	for (int j=0; j<game.getGrid().getHeight(); j++)
        	{
        		JComponent ptest = new SquareView(game, game.getGrid().getSquare(i, j));
                ptest.setBorder(blackline);
                grid.add(ptest);
        	}
        }
        grid.setBorder(blackline);
        
        add(panel, BorderLayout.PAGE_START);
        add(grid);
        //setContentPane(pan);
    }

	@Override
	public void update(Observable arg0, Object arg1)
	{
		if (game.isWon())
		{
			label.setText("Vous avez gagné !");
		}
		else if (game.isLost())
		{
			label.setText("Vous avez perdu :'( !");
		}
		else
		{
			label.setText("Il vous reste " + (game.getNbMines() - game.getNbFlags()) + " drapeaux.");
		}
	}
}
