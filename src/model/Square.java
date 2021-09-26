//Gal Ashkenazi 315566752 - Final Test
package model;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Tool game
 * @author GalAs
 *
 */
public class Square {
	private Color color;
	private boolean smallSize;
	private EventHandler<MouseEvent> event;

	/**
	 * c'tor
	 * @param colorNumber
	 * @param _smallSize
	 * @param _event
	 */
	public Square(Color _color, boolean _smallSize, EventHandler<MouseEvent> _event) {
		this.color = _color;
		this.smallSize = _smallSize;
		this.event = _event;
	}

	// getters and setters
	
	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isSmallSize() {
		return smallSize;
	}

	public void setSmallSize(boolean smallSize) {
		this.smallSize = smallSize;
	}
	
	public EventHandler<MouseEvent> getEvent() {
		return event;
	}

	public void setEvent(EventHandler<MouseEvent> event) {
		this.event = event;
	}
}
