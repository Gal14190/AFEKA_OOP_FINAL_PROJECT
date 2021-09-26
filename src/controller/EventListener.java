//Gal Ashkenazi 315566752 - Final Test
package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Model;
import model.RectangleSelected;
import view.View;
import view.View.GAME_FIELD_SCREEN;

/**
 * Contain the all click event listener.
 * @author GalAs
 *
 */
public class EventListener {
	private View view;
	private Model model;
	private TopPlayerFileHandler fileHandler;
	
	/**
	 * c'tor
	 * @param _view
	 * @param _model
	 * @param _fileHandler
	 */
	public EventListener(View _view, Model _model, TopPlayerFileHandler _fileHandler) {
		this.view = _view;
		this.model = _model;
		this.fileHandler = _fileHandler;
	}
	
	/**
	 * New game button event
	 * @return click event
	 */
	public EventHandler<ActionEvent> eNewGame() {
		return (new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				view.disableButton_newGame(true);
				// display player name field if is in the top 10 players. else show the score table
				if(Controller.isTop10Player(model.getGameDetails().getScore())) {
					view.updateGameField(GAME_FIELD_SCREEN.ENTER_NAME);
				}
				else {
					view.updateGameField(GAME_FIELD_SCREEN.SCORE_TABLE);
				}
				
			}
		});
	}
	
	/**
	 * Start game button event
	 * @return click event
	 */
	public EventHandler<ActionEvent> eStartGame() { 
		return (new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent arg0) {
				model.getGameDetails().setScore(0);  // reset score
				view.setScore(model.getGameDetails().getScore()); // show the score
				view.setLevel(model.getGameDetails().getLevel()); // show the current level
				
				// random squares colors
				Controller.randColor();
				
				// update and show the squares
				view.updateColorArray(model.getSquareArray());
				view.updateGameField(GAME_FIELD_SCREEN.GAME);
				view.disableButton_newGame(false);
			}
		});
	}
	
	/**
	 * Submit name button event
	 * @return event
	 */
	public EventHandler<ActionEvent> eSubmitName() { 
		return (new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent arg0) {
				String playerName = view.getPlayerName(); // get the player name
				String timeDate = LocalDate.now().toString(); // get the date
				
				// add the player to top players list and file if the name field is not empty
				if(playerName.length() > 0) {
					try {
						// add the player to the lists and file
						model.addTopPlyaersList(model.getGameDetails().getScore(), playerName, timeDate);
						fileHandler.update(model.getTopPlayersList());
						
						// update the score table
						model.getGameDetails().setScore(0); // reset score
						view.updateTopPlayers(model.getTopPlayersList());
						view.updateGameField(GAME_FIELD_SCREEN.SCORE_TABLE);						
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		});
	}
	
	/**
	 * Cancel submit name button event
	 * @return
	 */
	public EventHandler<ActionEvent> eCancel() { 
		return (new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent arg0) {
				model.getGameDetails().setScore(0);
				view.updateGameField(GAME_FIELD_SCREEN.SCORE_TABLE);
			}
		});
	}
	
	/**
	 * Select level radio button event.
	 * @param level
	 * @return
	 */
	public ChangeListener<Boolean> eLevelToggle(int level) {
		return (new ChangeListener<Boolean>() {
	
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if(arg0.getValue())
					model.getGameDetails().setLevel(level);
			}
		});
	}
	
	public EventHandler<ActionEvent> eDarkMode() {
		return (new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				view.setDarkMode();
				
			}
		});
	}
	
	/**
	 * Click event listener on the squares
	 * @param hardLevel
	 * @param posX
	 * @param posY
	 * @return event
	 */
	public EventHandler<MouseEvent> eToolGame(ArrayList<model.RectangleSelected> selectList ,int posX, int posY){ 
		return (new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// add or remove and square was clicked
				boolean status = view.setOnClick_gameTool(posX, posY);
				// add the square if it's was not choose. else remove
				if(status) {
					selectList.add(new RectangleSelected(model.getSquareArray()[posX][posY].getColor(), posX, posY));
				} else {
					// Search and remove the square
					for(int i = 0; i < selectList.size(); i++) {
						if(selectList.get(i).getPosX() == posX && selectList.get(i).getPosY() == posY) {
							selectList.remove(i);
							break;
						}
					}
				}
					
				// check if was 4 clicks
				if(Controller.isAllSelected()) {
					int area = -1;	
					// check if all the selected is in the same color
					if(Controller.isSameColor()) {
						int[][] points = new int[2][2];
						area = Controller.areaRectangle(points);	// check if it's rectangle and calculate area
						
						// add to the score and update colors squares if the rows and columns valid
						if(area != -1) {
							int playerScore = model.getGameDetails().getScore();
							playerScore += area; // add the area into the score
							
							model.getGameDetails().setScore(playerScore);
							view.setScore(playerScore); // show the new score
		
							Controller.randColor(points[1][0], points[1][1], points[0][0], points[0][1]); // random new colors
							view.updateGameToolArray(model.getSquareArray()); // update the view
							
							// print log
							Logger.log(points[1][0], points[1][1], points[0][0], points[0][1], area, model.getGameDetails().getLevel());
						}
					}
						
					// clean selected if was 4 clicks and the choose was not valid
					if(area == -1) {
						for(int i = 0; i < 4; i++) {
							view.setOnClick_gameTool(selectList.get(i).getPosX(), selectList.get(i).getPosY());
						}
					}
					selectList.clear(); // clear the selected
				}
			}
		});
	}
}
