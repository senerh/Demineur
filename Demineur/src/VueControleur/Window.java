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

import javax.swing.BorderFactory;
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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import Modele.*;

public class Window extends JFrame implements Observer
{
	private Game game;
	private JComponent grid;
	private JLabel lblFlags;
	private JLabel lblTime;
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
        JMenuItem mi = new JMenuItem("Facile");
        mi.addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				lblFlags.setText("Nouvelle partie facile !");
				remove(grid);
				DSquare = new Dimension(70,70);
				createResources(DSquare);
				buildGrid(new Game(10, 10, 15));
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
				DSquare = new Dimension(45,45);
				createResources(DSquare);
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
				DSquare = new Dimension(35,35);
				createResources(DSquare);
				buildGrid(new Game(20, 20, 50));
				validate();
			}
        });
        m.add(mi);
        
        jm.add(m);
        
        setJMenuBar(jm);
        
        setTitle("Demineur");
        setSize(700, 700);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        lblFlags = new JLabel();
        lblFlags.setBorder(new EmptyBorder(20, 0, 0, 20));
        
        lblTime = new JLabel("0");
        lblTime.setBorder(new EmptyBorder(20, 20, 0, 0));
        
        panel.add(lblFlags, BorderLayout.EAST);
        panel.add(lblTime, BorderLayout.WEST);
        
        add(panel, BorderLayout.PAGE_START);
        setResizable(false);
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
	
    private void createResources(Dimension dSquare2) {
    	
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
}
