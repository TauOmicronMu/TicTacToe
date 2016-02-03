package example;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class NoughtsCrossesComponent extends JPanel
{
	public NoughtsCrossesComponent(NoughtsCrosses game)
	{
		super();
		NoughtsCrossesModel model = new NoughtsCrossesModel(game);
		
		BoardView board = new BoardView(model);
		ButtonPanel controls = new ButtonPanel(model);
		
		model.addObserver(board);
		
		setLayout(new BorderLayout());
		
		add(board, BorderLayout.CENTER);
		add(controls, BorderLayout.SOUTH);
	}
}
