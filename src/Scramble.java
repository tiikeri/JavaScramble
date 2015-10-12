/**
 * @author      Viktor Ahlstroem
 * @version     0.3
 * @since       10/2/2015
 */
import java.util.*;
public class Scramble {
	public static class ScrambleException extends Exception {
		public ScrambleException(String message) {
			super(message);                                                         // ScrambleException is empty because it will be thrown for multiple reasons.
		}
	}
	public static String scrambleNormal(int scrambleLength, String turnSet, String modSet) throws ScrambleException {
		if (100 < scrambleLength) {
			throw new ScrambleException("Scramble too large!");                     // Throw ScrambleException if given length is greater than 100
		}
		if (10 > scrambleLength) {
			throw new ScrambleException("Scramble too small!");                     // Throw ScrambleException if given length is less than 10
		}
                // Create the needed ArrayLists
		ArrayList < String > turnsList = new ArrayList < > ();
		ArrayList < String > scrambleTurns = new ArrayList < > ();
		ArrayList < String > turnModifiers = new ArrayList < > ();
                // Initialize the strings we will need
		String lastTurn, currTurn, toInsert, currModifier;
		Random random = new Random();
		turnsList.addAll(Arrays.asList(turnSet.split("\\s+")));
		if ("defaultSet".equals(modSet)) {                                              // If the caller specifies that it wants to use the default set of modifiers, use them
			turnModifiers.add("\'");
			turnModifiers.add("2");
			turnModifiers.add("");
		} else {                                                                        // Else, use what the caller specified
			turnModifiers.addAll(Arrays.asList(modSet.split("\\s+")));
                        turnModifiers.add("");
		}
		for (int i = 0; i < scrambleLength; i++) {                                      // Loop however many times the caller wants it to
			if (i != 0) {                                                           // Prevent having to catch an ArrayIndexOutOfBounds exception
				lastTurn = scrambleTurns.get(i - 1).split("(?!^)")[0];          // Get the turn that was before the current number without its modifiers
			} else {
				lastTurn = null;                                                // lastTurn must equal something
			}
			currTurn = turnsList.get(random.nextInt(turnsList.size()));             // Choose a random number in the bounds of the list and retrieve element at the index
			while (currTurn.equals(lastTurn)) {                                     // Prevention of redundant turns like R R2; choose another turn until it's different from last turn
				currTurn = turnsList.get(random.nextInt(turnsList.size()));
			}
			currModifier = turnModifiers.get(random.nextInt(turnModifiers.size())); // Choose a random number in the bounds of the list and retrieve element
			toInsert = currTurn + currModifier;                                     // Concatenate the turn and the modifier together
			scrambleTurns.add(toInsert);                                            // Add the finished product to the scramble ArrayList

		}
		String returnString = "";                                                       // Initialize returnString so we can add to it

		for (String x: scrambleTurns) {
			returnString += x + "  ";                                               // Concatenate the current index turn with some whitespace so it will be human-readable
		}
		return returnString;                                                            // Return the final string
	}
}