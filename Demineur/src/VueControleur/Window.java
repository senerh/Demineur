package VueControleur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.border.EmptyBorder;

import Modele.*;

public class Window extends JFrame implements Observer
{
	private Game game;
	private JComponent grid;
	private JLabel lblFlags;
	private JLabel lblTime;
	private Timer timer;
	private int time;
	
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
        
        timer = new Timer();
        
        timer.scheduleAtFixedRate(new TimerTask() {
        	  @Override
        	  public void run() {
        		  displayTime();
        	  }
        	}, 1000, 1000);
    }
    
    private void displayTime()
	{
    	if (!game.isWon() && !game.isLost())
    	{
    		time++;
    		lblTime.setText("" + time);
    	}
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
				lblFlags.setText("Nouvelle partie facile !");
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
				lblFlags.setText("Nouvelle partie moyenne !");
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
				lblFlags.setText("Nouvelle partie difficile !");
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
        
        JPanel panel = new JPanel(new BorderLayout());
        
        lblFlags = new JLabel();
        lblFlags.setBorder(new EmptyBorder(20, 0, 0, 20));
        
        lblTime = new JLabel("0");
        lblTime.setBorder(new EmptyBorder(20, 20, 0, 0));
        
        panel.add(lblFlags, BorderLayout.EAST);
        panel.add(lblTime, BorderLayout.WEST);
        
        add(panel, BorderLayout.PAGE_START);
        //setContentPane(pan);
    }

	@Override
	public void update(Observable arg0, Object arg1)
	{
		if (game.isWon())
		{
			JOptionPane.showMessageDialog(null,"Bravo ;) !"); 
		}
		else if (game.isLost())
		{
			JOptionPane.showMessageDialog(null,"Dommage ;( !"); 
		}
		else
		{
			lblFlags.setText("" + (game.getNbMines() - game.getNbFlags()));
		}
	}
	
	private void buildGrid(Game _game)
	{
		time = 0;
		game = _game;
		game.addObserver(this);
		
		lblFlags.setText("" + game.getNbMines());
		
		grid = new JPanel (new GridLayout(game.getGrid().getHeight(), game.getGrid().getWidth()));
        Border blackline = BorderFactory.createLineBorder(Color.black,1);
        grid.setBorder(new EmptyBorder(20, 20, 20, 20));
        for(int i = 0; i<game.getGrid().getWidth(); i++)
        {
        	for (int j=0; j<game.getGrid().getHeight(); j++)
        	{
        		JComponent ptest = new SquareView(game, game.getGrid().getSquare(i, j));
                ptest.setBorder(blackline);
                grid.add(ptest);
        	}
        }
        add(grid);
	}
}
