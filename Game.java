import javax.swing.JFrame;
import java.awt.Toolkit;

/*
Kormah T Dorko
ID: 010810293
Assignment4
9/19/21
 */
public class Game extends JFrame
{
Model model;
View view;
Controller controller;

	public Game()
	{
		 model = new Model();
		 controller = new Controller(model);
		 view = new View(controller,model);
		view.addMouseListener(controller);


		this.setTitle("The Sin of Pride");
		this.setSize(800, 800);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(controller);
	}
public void run(){
	while(true)
	{
		controller.update();
		model.update();
		view.repaint(); // Indirectly calls View.paintComponent
		Toolkit.getDefaultToolkit().sync(); // Updates screen

		// Go to sleep for 50 miliseconds
		try
		{
			Thread.sleep(50);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

	}
}
	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();

	}
}
