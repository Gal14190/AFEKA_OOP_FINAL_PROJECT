//Gal Ashkenazi 315566752 - Final Test
package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Create the windows application for this program.
 */
public class WindowApplicationView {
	private BorderPane root;
	private Stage stage;
	private boolean darkMode;
	
	private int width, height;
	
	/**
	 * c'tor
	 * @param _stage application stage
	 * @param _width width window
	 * @param _height height window
	 */
	public WindowApplicationView(Stage _stage, int _width, int _height) {
		this.stage = _stage;
		this.width = _width;
		this.height = _height;
		this.darkMode = false;
		
		root = new BorderPane();
	}
	
	/**
	 * Show the window application
	 * @param left show part left
	 * @param right show part right
	 * @param Top show part top
	 * @param gameField show the game field in the center
	 */
	public void show(Pane left, Pane right, Pane Top, Pane gameField) {
		this.root.setLeft(left);
		this.root.setRight(right);
		this.root.setTop(Top);
		this.root.setCenter(gameField);
		this.root.getStyleClass().add("lightMode_root");
		
		Scene scene = new Scene(this.root, this.width, this.height);
		scene.getStylesheets().add(getClass().getResource("styleSheet.css").toString());
		this.stage.setTitle("Final Project - Colors Game");
		this.stage.setScene(scene);
		this.stage.setResizable(false);
		this.stage.show();
	}
	
	/**
	 * Set dark gray background window
	 */
	public void setDarkBackground() {
		if(!this.darkMode) {
			this.darkMode = true;
			this.root.getStyleClass().add("darkMode_root");
			this.root.getStyleClass().remove("lightMode_root");
		}
		else {
			this.darkMode = false;
			this.root.getStyleClass().remove("darkMode_root");
			this.root.getStyleClass().add("lightMode_root");
		}
	}

	public boolean isDarkMode() {
		return darkMode;
	}
}
