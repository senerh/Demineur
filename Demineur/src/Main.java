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
				Game game = new Game();
				Window window = new Window(game);
				window.setVisible(true);
			}
		});
    }
}
