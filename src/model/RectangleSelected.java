//Gal Ashkenazi 315566752 - Final Test
package model;

import javafx.scene.paint.Color;
/**
 * Holder for squares selected for rectangle
 * @author GalAs
 *
 */
public class RectangleSelected {
	private Color color;
	private int posX, posY;
	private boolean selected;
	
	/**
	 * c'tor
	 * @param _color
	 * @param _posX
	 * @param _posY
	 */
	public RectangleSelected(Color _color, int _posX, int _posY) {
		this.color = _color;
		this.posX = _posX;
		this.posY = _posY;
		this.selected = true;
	}

	//getters and setters
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public boolean getSelected() {
		return this.selected;
	}

	public  void setSelected(boolean value) {
		this.selected = value;
	}
	
	
}
