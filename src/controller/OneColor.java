//Gal Ashkenazi 315566752 - Final Test
package controller;

import java.util.Random;

/**
 * Return one color for all of the squares
 * @author GalAs
 *
 */
public class OneColor extends SquareColor {

	private Random rand;
	private int number;
	
	/**
	 * c'tor
	 */
	public OneColor() {
		super();
		
		rand = new Random();
		next(1);
	}
	
	@Override
	public int index(int level) {
		return number;
	}

	@Override
	public void next(int level) {
		int num = -1;
		
		do {
			num = rand.nextInt(3 * level);
		} while(num == this.number);
		
		this.number = num;
		
	}

}
