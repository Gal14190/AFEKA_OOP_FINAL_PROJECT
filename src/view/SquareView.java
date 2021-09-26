//Gal Ashkenazi 315566752 - Final Test
package view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Draw squared
 * @author GalAs
 *
 */
public class SquareView {
	private Color color;
	private boolean smallSize;
	private Pane root;
	private Rectangle square;
	private boolean selected;
	
	/**
	 * c'tor
	 * @param _root
	 * @param _color
	 * @param _hardLevel
	 */
	public SquareView(Pane _root, Color _color, boolean _hardLevel) {
		this.color = _color;
		this.smallSize = _hardLevel;
		this.root = _root;
		this.selected = false;
		this.root = _root;
	}
	
	/**
	 * draw square
	 */
	public void draw() {
		int size = 40; // 40x40 if it's big
		if(this.smallSize) size = 20; // 20x20 if it's small
			
		this.square = new Rectangle(size, size);
		this.square.setFill(this.color);
		this.square.getStyleClass().add("square");
		this.root.getChildren().add(square);
	}
	
	/**
	 * Make border pink if the square selected
	 * @return
	 */
	public boolean select() {
		if(!selected) {
			selected = true;
			this.square.getStyleClass().add("selected");
		} else {
			selected = false;
			this.square.getStyleClass().remove("selected");
		}
		
		return selected;
	}
	
	/**
	 * Set onClick event
	 * @param event
	 */
	public void setOnAction(EventHandler<MouseEvent> event) {
		this.square.setOnMouseClicked(event);
	}
}
