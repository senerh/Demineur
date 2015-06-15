package VueControleur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

import Modele.*;

public class Window extends JFrame implements Observer
{
	private Game game;
	private JComponent grid;
	private JLabel lblFlags;
	private JLabel lblTime;
	private JLabel lblPlayer;
	private Timer timer;
	private int time;
	public static Dimension DSquare;

    public Window(Game _game)
    {
        super();
    	DSquare = new Dimension(70,70);
    	createResources(DSquare);
    	
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
    		lblTime.setText("Temps : " + time);
    	}
	}

	public void build()
    {
        //JMenu jm = new JMenu();
        JMenuBar jm = new JMenuBar();
        
        JMenu m = new JMenu("Nouvelle Partie");
        JMenu mj1 = new JMenu("1 Joueur");
        JMenu mj2 = new JMenu("2 Joueurs");
        
        mj1.add(createGame("Facile", 10, 10, 15, false));
        mj1.add(createGame("Moyen", 15, 15, 20, false));
        mj1.add(createGame("Difficile", 20, 20, 50, false));
        
        mj2.add(createGame("Facile", 10, 10, 15, true));
        mj2.add(createGame("Moyen", 15, 15, 20, true));
        mj2.add(createGame("Difficile", 20, 20, 50, true));
        
        m.add(mj1);
        m.add(mj2);
        
        jm.add(m);
        
        setJMenuBar(jm);
        
        setTitle("Demineur");
        setSize(700, 700);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        lblFlags = new JLabel();
        lblFlags.setBorder(new EmptyBorder(20, 0, 0, 20));
        
        lblTime = new JLabel("Temps : 0");
        lblTime.setBorder(new EmptyBorder(20, 20, 0, 0));
        
        lblPlayer = new JLabel("");
        lblPlayer.setBorder(new EmptyBorder(20, 250, 0, 0));
        
        panel.add(lblFlags, BorderLayout.EAST);
        panel.add(lblTime, BorderLayout.WEST);
        panel.add(lblPlayer, BorderLayout.CENTER);
        
        add(panel, BorderLayout.PAGE_START);
        setResizable(false);
    }

	@Override
	public void update(Observable arg0, Object arg1)
	{
		if (game.isWon())
		{
			if (game.isTwoPlayers())
			{
				JOptionPane.showMessageDialog(null,"Bravo, Joueur " + game.getCurrentPlayer() +" a gagné en " + time + "s. !");
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Bravo, vous avez gagné en " + time + "s. !");
			}
		}
		else if (game.isLost())
		{
			if (game.isTwoPlayers())
			{
				JOptionPane.showMessageDialog(null,"Dommage, Joueur " + game.getCurrentPlayer() +" a perdu en " + time + "s. !");
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Dommage, vous avez perdu en " + time + "s. !");
			}
		}
		else
		{
			if (game.isTwoPlayers())
			{
				lblPlayer.setText("Joueur " + game.getCurrentPlayer());
			}
			lblFlags.setText("" + (game.getNbMines() - game.getNbFlags()));
		}
	}
	
	private void buildGrid(Game _game)
	{
		time = 0;
		game = _game;
		game.addObserver(this);
		
		lblPlayer.setText("");
		lblFlags.setText("" + game.getNbMines());
		grid = new JPanel (new GridLayout(game.getGrid().getHeight(), game.getGrid().getWidth()));
		grid.setMinimumSize(new Dimension(400,400));
		grid.setMaximumSize(new Dimension(400,400));
		grid.setPreferredSize(new Dimension(400,400));
        //Border blackline = BorderFactory.createLineBorder(Color.WHITE,1);
        grid.setBorder(new EmptyBorder(20, 20, 20, 20));
        for(int i = 0; i<game.getGrid().getWidth(); i++)
        {
        	for (int j=0; j<game.getGrid().getHeight(); j++)
        	{
        		JButton ptest = new SquareView(game, game.getGrid().getSquare(i, j));
        		ptest.setHorizontalTextPosition(SwingConstants.CENTER);
                //ptest.setBorder(blackline);
                grid.add(ptest);
        	}
        }
        add(grid);
	}
	
    private void createResources(Dimension dSquare2)
    {
    	Ressources.FLAG = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("drapeau.png")).getImage().getScaledInstance(DSquare.height, DSquare.width, Image.SCALE_SMOOTH));
		Ressources.MINE = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("mine.png")).getImage().getScaledInstance(DSquare.height, DSquare.width, Image.SCALE_SMOOTH));
		Ressources.SQUARE = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Square.png")).getImage().getScaledInstance(DSquare.height, DSquare.width, Image.SCALE_SMOOTH));
		Ressources.SQUAREVIDE = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("SquareVide.png")).getImage().getScaledInstance(DSquare.height, DSquare.width, Image.SCALE_SMOOTH));
		Ressources.SQUARE1 = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Square1.png")).getImage().getScaledInstance(DSquare.height, DSquare.width, Image.SCALE_SMOOTH));
		Ressources.SQUARE2 = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Square2.png")).getImage().getScaledInstance(DSquare.height, DSquare.width, Image.SCALE_SMOOTH));
		Ressources.SQUARE3 = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Square3.png")).getImage().getScaledInstance(DSquare.height, DSquare.width, Image.SCALE_SMOOTH));
		Ressources.SQUARE4 = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Square4.png")).getImage().getScaledInstance(DSquare.height, DSquare.width, Image.SCALE_SMOOTH));
		Ressources.SQUARE5 = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Square5.png")).getImage().getScaledInstance(DSquare.height, DSquare.width, Image.SCALE_SMOOTH));
		Ressources.SQUARE6 = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Square6.png")).getImage().getScaledInstance(DSquare.height, DSquare.width, Image.SCALE_SMOOTH));
		Ressources.SQUARE7 = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Square7.png")).getImage().getScaledInstance(DSquare.height, DSquare.width, Image.SCALE_SMOOTH));
		Ressources.SQUARE8 = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Square8.png")).getImage().getScaledInstance(DSquare.height, DSquare.width, Image.SCALE_SMOOTH));
    }
    
    private JMenuItem createGame(String name, final int width, final int height, final int nbMines, final boolean isTwoPlayers)
    {
    	JMenuItem mi = new JMenuItem(name);
        mi.addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				lblFlags.setText("Nouvelle partie moyenne !");
				remove(grid);
				DSquare = new Dimension(700/width, 700/height);
				createResources(DSquare);
				buildGrid(new Game(width, height, nbMines, isTwoPlayers));
				if (isTwoPlayers)
				{
					lblPlayer.setText("Joueur 1");
				}
				validate();
			}
        });
        
        return mi;
    }
}
