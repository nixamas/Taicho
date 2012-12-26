package gameparts;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This panel lets two users play checkers against each other. Red always starts
 * the game. If a player can jump an opponent's piece, then the player must
 * jump. When a player can make no more moves, the game ends.
 * 
 * The class has a main() routine that lets it be run as a stand-alone
 * application. The application just opens a window that uses an object of type
 * Checkers as its content pane.
 * 
 * There is also a nested class, Checker.Applet, that can be used as an applet
 * version of the program. The applet size should be 350-by-250 (or very close
 * to that).
 * 
 */
public class ProgramMain extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JButton unstackBtn;

	/**
	 * Main routine makes it possible to run Checkers as a stand-alone
	 * application. Opens a window showing a Checkers panel; the program ends
	 * when the user closes the window.
	 */
	public static void main(String[] args) {
		System.out.println("MAIN");
		JFrame window = new JFrame("Taicho");
		ProgramMain content = new ProgramMain();
		window.setContentPane(content);
		window.pack();
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation((screensize.width - window.getWidth()) / 2,
				(screensize.height - window.getHeight()) / 2);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		System.err.println("b4 setVisible");
		window.setVisible(true);
		System.err.println("after setVisible");
		
	}

	/**
	 * The constructor creates the Board (which in turn creates and manages the
	 * buttons and message label), adds all the components, and sets the bounds
	 * of the components. A null layout is used. (This is the only thing that is
	 * done in the main Checkers class.)
	 */
	public ProgramMain() {
		System.out.println("Taicho constructor");
		setLayout(null); // I will do the layout myself.

		this.setBackground(Color.BLACK); // Black background.
		
		unstackBtn = new JButton("Un-Stack");

		Board board = new Board(unstackBtn);	//create a new game board
										//inside the constructor there is a value to set the size of the window
		
		setPreferredSize(new Dimension(board.getBoardProperties().getBoardLength()+10, board.getBoardProperties().getBoardWidth()+100));
				
		System.err.println("b4 adding board");
		add(board);
		System.err.println("after adding board");
		
//		unstackBtn.setText("HELLO");
		add(unstackBtn);
		unstackBtn.setBounds(10, board.getBoardProperties().getBoardWidth()+20, 100, 50);
		/*
		 * Set the position and size of each component by calling its
		 * setBounds() method.
		 */

		board.setBounds(10, 10, board.getBoardProperties().getBoardLength(), board.getBoardProperties().getBoardWidth()); // Note: size MUST be 164-by-164 !
//		message.setBounds(0, 200, 350, 30);

	} // end constructor

} // end class Checkers