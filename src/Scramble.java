/**
 * @author      Viktor Ahlstroem
 * @version     0.1 
 * @since       10/2/2015
 */
import java.util.*;
public class Scramble {
	public static class ScrambleException extends Exception {
		public ScrambleException(String message) {
			super(message); // ScrambleException is empty because it will be thrown for multiple reasons.
		}
	}
	public static String scrambleNormal(int l, String turnSet, String modSet) throws ScrambleException {
		if (50 < l) {
			throw new ScrambleException("Scramble too large!"); // Throw ScrambleException if given length is greater than 50
		}
		if (10 > l) {
			throw new ScrambleException("Scramble too small!"); // Throw ScrambleException if given length is less than 10
		}
                // Create the needed ArrayLists
		ArrayList < String > turnsList = new ArrayList < > ();
		ArrayList < String > scrambleTurns = new ArrayList < > ();
		ArrayList < String > turnModifiers = new ArrayList < > ();
		String lastTurn, currTurn, toInsert, currModifier;
		Random random = new Random();
		turnsList.addAll(Arrays.asList(turnSet.split("\\s+")));
		if ("defaultSet".equals(modSet)) {
			turnModifiers.add("\'");
			turnModifiers.add("2");
			turnModifiers.add("");
		} else {
			turnModifiers.addAll(Arrays.asList(modSet.split("\\s+")));
		}
		for (int i = 0; i < l; i++) {
			if (i != 0) {
				lastTurn = scrambleTurns.get(i - 1).split("(?!^)")[0];
			} else {
				lastTurn = null;
			}
			currTurn = turnsList.get(random.nextInt(turnsList.size()));
			while (currTurn.equals(lastTurn)) {
				currTurn = turnsList.get(random.nextInt(turnsList.size()));
			}
			currModifier = turnModifiers.get(random.nextInt(turnModifiers.size()));
			toInsert = currTurn + currModifier;
			scrambleTurns.add(toInsert);

		}
		String returnString = "";

		for (String x: scrambleTurns) {
			returnString += x + "  ";
		}
		return returnString;
	}
}