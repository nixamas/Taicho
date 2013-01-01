package interfaces;


/**
 * This class is implemented by the different legal move enums 
 * 			(LevelOneLegalMoves.java, LevelTwoLegalMoves.java, LevelThreeLegalMoves.java)
 * allows us to create an array of MoveManager classes and then fill it with 
 * any of the legal moves just by figuring out what rank the character
 * @author Ryan
 *
 */
public interface MoveManager {
	public Object[] getMoves();
	public int getMove(int i);
	public int getNumVal();
}
