//Gal Ashkenazi 315566752 - Final Test
package controller;

import javafx.stage.Stage;
import model.Model;
import view.View;

/**
 * Handle functionality of this program.
 * Create events listener, random square color and calculate the algorithm of this game.
 */
public class Controller {
	private static Model model;
	private static View view;
	private static TopPlayerFileHandler fileHandler;
	private static EventListener eventListener;
	private static SquareColor squareColor;
	
	/**
	 * c'tor
	 * @param model 
	 * @param view
	 */
	public Controller(Model _model, View _view) {
		try {
			model = _model;
			view = _view;
			
			fileHandler = new TopPlayerFileHandler();
			eventListener = new EventListener(view, model, fileHandler);
			squareColor = new RandomColor();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Show the windows application and associate events to buttons
	 * @param stage application stage
	 */
	public void start(Stage stage) {
		try {	
			model.updateTopPlayersList(fileHandler.read()); // import top 10 players from file
			view.updateTopPlayers(model.getTopPlayersList()); // update top 10 player to view
			
			view.setClickListener_newGame(eventListener.eNewGame());	// new game button event
			view.setClickListener_startGame(eventListener.eStartGame()); // start game button event
			view.setClickListener_submitScore(eventListener.eSubmitName()); // submit name button event
			view.setClickListener_cancel(eventListener.eCancel());
			view.setClickListener_darkMode(eventListener.eDarkMode());
			
			// select level radio button event
			view.setClickListener_levelToggle(1, eventListener.eLevelToggle(1));
			view.setClickListener_levelToggle(2, eventListener.eLevelToggle(2));
			view.setClickListener_levelToggle(3, eventListener.eLevelToggle(3));
		
		view.show(stage); // show the window application
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Random colors for all square.
	 */
	public static void randColor() {
		int x,y;
		x=y=10; // 10x10 squares
		if(model.getGameDetails().isHardLevel()) x=y=20; // 20x20 squares if it's hard level (3)
		
		// set mode game
		if(view.randomModeGame()) squareColor = new RandomColor();
		else squareColor = new OneColor();
		
		model.initSquareArray(x, y); // new array
		
		for(int i = 0; i< x; i++) {
			for(int j = 0; j < y; j++) {
				final int posX = i;
				final int posY = j;
				
				// add square to the array
				model.getSquareArray()[i][j] = model.updateGameToolsArray(squareColor.select(model.getGameDetails().getLevel()), // random color
																		model.getGameDetails().isHardLevel(),	// true if it's hard level (3)
																		eventListener.eToolGame(model.getSelectList(), posX, posY)); // click event
			}
		}
	}
	
	/**
	 * Random colors for range square.
	 * @param hardLevel
	 * @param x_0
	 * @param x_1
	 * @param y_0
	 * @param y_1
	 */
	public static void randColor(int x_0, int x_1, int y_0, int y_1) {	
		squareColor.next(model.getGameDetails().getLevel());
		
		for(int i = x_0; i<= x_1; i++) {
			for(int j = y_0; j <= y_1; j++) {
				final int posX = i;
				final int posY = j;
				
				// add square to the array
				model.getSquareArray()[i][j] = model.updateGameToolsArray(squareColor.select(model.getGameDetails().getLevel()), // random color number
																		model.getGameDetails().isHardLevel(), // true if it's hard level (3)
																		eventListener.eToolGame(model.getSelectList(), posX, posY)); // click event
			}
		}
	}
	
	/**
	 * Check if was 4 clicks
	 * @return
	 */
	public static boolean isAllSelected() {
		boolean result = false;
		
		if(model.getSelectedSize() >= 4) result = true;
		
		return result;
	}
	
	/**
	 * Check if all the chooses is the same color
	 * @return
	 */
	public static boolean isSameColor() {
		boolean result = false;
		
		if(!isAllSelected()) return result;
		
		if(model.getSelectList().get(0).getColor() == model.getSelectList().get(1).getColor()
			&& model.getSelectList().get(1).getColor() == model.getSelectList().get(2).getColor()
			&& model.getSelectList().get(2).getColor() == model.getSelectList().get(3).getColor()) {
			result = true;
		}
				
		return result;
	}
	
	/**
	 * Check if the rectangle is valid and calculate the area
	 * @param points [1][0] - x_0, [1][1] - x_1, [0][0] - y_0, [0][1] - y_1
	 * @return
	 */
	public static int areaRectangle(int[][] points) {
		int result = -1;
		
		if(!isAllSelected()) return result; // check if all the selected square is the same color
		
		boolean validX = false, validY = false;
		int deletaX = 0;
		int coupleX = 0;
			
		int deletaY = 0;
		int coupleY = 0;
			
		// check if it's rectangle by find the squares in the same row and columns and calculate the delta
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				// check for rows valid
				if(i != coupleX && i != j && model.getSelectList().get(i).getPosX() == model.getSelectList().get(j).getPosX()) {
					coupleX = j;
					deletaX = Math.abs(model.getSelectList().get(i).getPosY() - model.getSelectList().get(j).getPosY()) - deletaX;
						
					if(model.getSelectList().get(i).getPosY() < model.getSelectList().get(j).getPosY()) {
						points[0][0]= model.getSelectList().get(i).getPosY();
						points[0][1] = model.getSelectList().get(j).getPosY();
					} else {
						points[0][0]= model.getSelectList().get(j).getPosY();
						points[0][1] = model.getSelectList().get(i).getPosY();
					}
						
					// if the delta between two points is the same (difference is 0) the rows is valid
					if(deletaX == 0) validX = true;
				}
				
				// check for columns valid
				if(i != coupleY && i != j && model.getSelectList().get(i).getPosY() == model.getSelectList().get(j).getPosY()) {
					coupleY = j;
					deletaY = Math.abs(model.getSelectList().get(i).getPosX() - model.getSelectList().get(j).getPosX()) - deletaY;
						
					if(model.getSelectList().get(i).getPosX() < model.getSelectList().get(j).getPosX()) {
						points[1][0]= model.getSelectList().get(i).getPosX();
						points[1][1] = model.getSelectList().get(j).getPosX();
					} else {
						points[1][0]= model.getSelectList().get(j).getPosX();
						points[1][1] = model.getSelectList().get(i).getPosX();
					}
						
					// if the delta between two points is the same (difference is 0) the columns is valid
					if(deletaY == 0) validY = true;
				}
			}
		}
		
		// calculate the area if the rows and columns valid
		if(validX && validY) 
			result = (points[1][1] - points[1][0] + 1) * (points[0][1] - points[0][0] + 1); // calculate area
		
		return result;
	}
	
	/**
	 * Check if the player is in the TOP 10
	 * @param score
	 * @return true - is in the TOP 10. false - is not in the TOP 10.
	 */
	public static boolean isTop10Player(int score) {
		boolean result = false;
		
		if(model.getTopPlayersList().size() < 10) return true;
		
		for (model.TopPlayers topPlayers : model.getTopPlayersList()) {
			if(score > topPlayers.getScore()) {
				return true;
			}
		}
		
		return result;
	}
}
