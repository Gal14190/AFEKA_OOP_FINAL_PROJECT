//Gal Ashkenazi 315566752 - Final Test
package controller;

import javafx.scene.paint.Color;

/**
 * Super class of define color for squares.
 * @author GalAs
 *
 */
public abstract class SquareColor {

	// collection of all the colors in the game
	private Color[] type_color = {Color.WHITE, Color.BLUE, Color.BLACK, Color.PURPLE, Color.ORANGE, Color.YELLOW, Color.RED, Color.GREEN, Color.LIGHTBLUE};
	protected int minColor;
	
	/**
	 * c'tor
	 */
	public SquareColor() {
		minColor = 3;
	}
	
	/**
	 * Return the color index
	 * @param level
	 * @return
	 */
	public abstract int index(int level);
	/**
	 * Select new color
	 * @param level
	 */
	public abstract void next(int level);
	
	/**
	 * Return color.
	 * @param level
	 * @return
	 */
	public Color select(int level) {
		return type_color[index(level)];
	}
}
