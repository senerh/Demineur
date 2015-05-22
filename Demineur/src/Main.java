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
				Vue fenetre = new Vue(grid);
				fenetre.setVisible(true);
			}
		});
    }
}
