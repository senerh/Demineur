package VueControleur;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.border.Border;

import Modele.*;

public class Window extends JFrame
{
	private Game game;
	private JComponent pan;
	
    public Window(Game _game)
    {
        super();
        
        game = _game;
        
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
        
        JMenu m = new JMenu("Jeu");
        
        JMenuItem mi = new JMenuItem("Nouvelle Partie");
        
        //ItemListener i = new Item
        
        m.add(mi);
        
        jm.add(m);
        
       
        
        setJMenuBar(jm);
        
        
        setTitle("Demineur de l'espace");
        setSize(400, 400);
        pan = new JPanel (new GridLayout(game.getGrid().getHeight(), game.getGrid().getWidth()));
        Border blackline = BorderFactory.createLineBorder(Color.black,1);
        
        for(int i = 0; i<game.getGrid().getWidth(); i++)
        {
        	for (int j=0; j<game.getGrid().getHeight(); j++)
        	{
        		JComponent ptest = new SquareView(game, game.getGrid().getSquare(i, j));
                ptest.setBorder(blackline);
                pan.add(ptest);
        	}
        }
        pan.setBorder(blackline);
        add(pan);
        //setContentPane(pan);
    }
}
