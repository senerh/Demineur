package VueControleur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
        
        build();
        buildGrid(_game);
        
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
        mi.addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				label.setText("Nouvelle partie facile !");
				remove(grid);
				buildGrid(new Game(10, 10, 5));
				validate();
			}
        });
        m.add(mi);
        
        mi = new JMenuItem("Moyen");
        mi.addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				label.setText("Nouvelle partie moyenne !");
				remove(grid);
				buildGrid(new Game(15, 15, 20));
				validate();
			}
        });
        m.add(mi);
        
        mi = new JMenuItem("Difficile");
        mi.addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				label.setText("Nouvelle partie difficile !");
				remove(grid);
				buildGrid(new Game(20, 20, 50));
				validate();
			}
        });
        m.add(mi);
        
        jm.add(m);
        
        setJMenuBar(jm);
        
        setTitle("Demineur");
        setSize(400, 400);
        label = new JLabel("Démineur du futur !!!");
        JPanel panel = new JPanel();
        panel.add(label);
        add(panel, BorderLayout.PAGE_START);
        //setContentPane(pan);
    }

	@Override
	public void update(Observable arg0, Object arg1)
	{
		if (game.isWon())
		{
			JOptionPane.showMessageDialog(null,"Bravo ;) !"); 
			label.setText("Vous avez gagné !");
		}
		else if (game.isLost())
		{
			JOptionPane.showMessageDialog(null,"Dommage ;( !"); 
			label.setText("Vous avez perdu :'( !");
		}
		else
		{
			label.setText("Il vous reste " + (game.getNbMines() - game.getNbFlags()) + " drapeaux.");
		}
	}
	
	private void buildGrid(Game _game)
	{
		game = _game;
		game.addObserver(this);
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
        add(grid);
	}
}
