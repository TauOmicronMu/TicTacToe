import javax.swing.JFrame;

public class NoughtsCrossesGUI 
{
	public static void main(String[] args)
	{
		NoughtsCrosses game = new NoughtsCrosses();
		
		NoughtsCrossesComponent comp = new NoughtsCrossesComponent(game);
		
		JFrame frame = new JFrame("Noughts and Crosses");
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(comp);
		
		frame.setVisible(true);
	}
}
