package example;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.*;

public class ButtonPanel extends JPanel
{

	public ButtonPanel(NoughtsCrossesModel model)
	{
		super();
		
		JButton reset = new JButton("New Game");
		reset.addActionListener(e -> model.newGame());
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(e -> System.exit(0));
		
		add(reset);
		add(exit);
	}
}
