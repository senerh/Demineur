import javax.swing.SwingUtilities;

import Modele.*;
import VueControleur.*;

public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
			public void run()
			{
				Grid grid = new Grid(10, 10);
				Window fenetre = new Window(grid);
				fenetre.setVisible(true);
			}
		});
    }
}
