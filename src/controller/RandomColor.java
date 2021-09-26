//Gal Ashkenazi 315566752 - Final Test
package controller;

import java.util.Random;

/**
 * Return random color for all of the squares
 * @author GalAs
 *
 */
public class RandomColor extends SquareColor {

	/**
	 * c'tor
	 */
	public RandomColor() {
		super();
	}
	
	@Override
	public int index(int level) {
		Random rand = new Random();
		
		return rand.nextInt(level * super.minColor);
	}

	@Override
	public void next(int level) {
		// do nothing
	}

}
