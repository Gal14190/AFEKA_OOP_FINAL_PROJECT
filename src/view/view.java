//Gal Ashkenazi 315566752 - Final Test
package view;

import java.util.ArrayList;

import model.Square;
import model.TopPlayers;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Handle the all views in this program.
 * @author GalAs
 *
 */
public class View {
	public static enum GAME_FIELD_SCREEN {START, SCORE_TABLE, ENTER_NAME, GAME};
	
	private Square[][] arrSquare;
	private SquareView[][] gameTools;
	private ArrayList<TopPlayers> topPlayers;
	WindowApplicationView application;
	
	private VBox vbGame;
	private Text txtScoreValue, txtLevelValue, txtScore, txtLevel, txtSetName, txtGameTitle, txtTop10;
	private Button btnNewGame, btnStartGame, btnSubmit, btnCancel, btnDarkMode;
	private RadioButton rbLevel1, rbLevel2, rbLevel3;
	private TextField tbPlayerName;
	private CheckBox cbRandomColor;
	
	/**
	 * c'tor
	 */
	public View() {
			// setup view
		
			btnDarkMode = new Button("Dark Mode");
			btnDarkMode.getStyleClass().add("darkMode_btn");
			
			btnNewGame = new Button("New Game");
			btnNewGame.getStyleClass().add("newGame_btn");
			
			btnStartGame = new Button("Start game");
			btnStartGame.getStyleClass().add("startGame_btn");
			
			btnSubmit = new Button("Submit");

			btnCancel = new Button("Cancel"); 
			btnCancel.getStyleClass().add("cancel_btn");
			
			ToggleGroup tgRadioButtons = new ToggleGroup();
			rbLevel1 = new RadioButton("Level 1");
			rbLevel1.setSelected(true);
			rbLevel1.setToggleGroup(tgRadioButtons);
			
			rbLevel2 = new RadioButton("Level 2");
			rbLevel2.setToggleGroup(tgRadioButtons);
			
			rbLevel3 = new RadioButton("Level 3");
			rbLevel3.setToggleGroup(tgRadioButtons);
			
			cbRandomColor = new CheckBox("Random Color");
			cbRandomColor.setSelected(true);
			
			tbPlayerName = new TextField();
			
			txtSetName = new Text("Enter your name");
			
			txtTop10 = new Text("-- TOP 10 --");
			txtTop10.getStyleClass().add("top10_txt");
			
			txtGameTitle = new Text("Colors Game");
			txtScore = new Text("Score: ");
			txtLevel = new Text("Level: ");
			
			vbGame = new VBox();
	}

	/**
	 * Show the window application
	 * @param stage
	 */
	public void show(Stage stage) {
		try {	
			// show the title "Colors Game" in the top
			VBox vbTop = new VBox();
			vbTop.setAlignment(Pos.CENTER);
			vbTop.getChildren().addAll(txtGameTitle);
			
			// start the program with the start screen
			vbGame.getChildren().add(gameField(GAME_FIELD_SCREEN.START));
			disableButton_newGame(true);
			
			// create and show the window application
			application = new WindowApplicationView(stage, 600, 500);
			application.show(controlPanel(), scorePanel(), vbTop, vbGame);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Collection of 4 type of screen.
	 * Start, Score table, Enter name and the game.
	 * 
	 * @param screen - type of screen to show
	 * @return
	 */
	public Pane gameField(GAME_FIELD_SCREEN screen) {
		VBox vb = new VBox();
		vb.getStyleClass().add("gameField");
		
		switch (screen) {
		case START:
			vb.getChildren().addAll(btnStartGame, cbRandomColor, rbLevel1, rbLevel2, rbLevel3); // show start game button
			break;
		case SCORE_TABLE: 
			// show score table and start game button
			VBox vbTableScore = new VBox();
			vbTableScore.setAlignment(Pos.CENTER);
			
			// show top 10 table
			TopPlayersTableView topPlayersView = new TopPlayersTableView(this.topPlayers);
			TableView<TopPlayers> tvTopPlayers = topPlayersView.show();
			
			// view title, table, start game button and radio buttons
			vbTableScore.getChildren().addAll(txtTop10, tvTopPlayers, btnStartGame, cbRandomColor, rbLevel1, rbLevel2, rbLevel3);
			
			vb.getChildren().add(vbTableScore);
			break;
		case ENTER_NAME: 
			// show insert name field and submit button 
			VBox vbEnterName = new VBox();
			vbEnterName.setMaxSize(200, 200);
			
			tbPlayerName.clear();
			
			HBox vhButtons = new HBox();
			vhButtons.setAlignment(Pos.CENTER);
			vhButtons.getChildren().addAll(btnSubmit, btnCancel);
			
			vbEnterName.getChildren().addAll(txtSetName, tbPlayerName, vhButtons);
			vb.getChildren().add(vbEnterName);
			break;
		case GAME: 
			VBox vbGame = new VBox();
			vbGame.setAlignment(Pos.CENTER);
			
			gameTools = new SquareView[arrSquare.length][arrSquare[0].length];
			
			// show the squares
			for(int i = 0; i < arrSquare.length; i++) {
				HBox vhRowSquares = new HBox(); 
				vhRowSquares.setMaxSize(0, 0);
				for(int j = 0; j < arrSquare[0].length; j++) {
					Square squareDetails = arrSquare[i][j];
					gameTools[i][j] = new SquareView(vhRowSquares, 
													squareDetails.getColor(), 
													squareDetails.isSmallSize());
					gameTools[i][j].draw(); // draw the square
					gameTools[i][j].setOnAction(squareDetails.getEvent());
				}
				vbGame.getChildren().addAll(vhRowSquares);
			}
			
			vb.getChildren().add(vbGame);
			break;
		}
		
		return vb;
	}
	
	/**
	 * Show the control panel.
	 * Include new game button and radio buttons the select level game
	 * @return
	 */
	private Pane controlPanel() {
		VBox vb = new VBox();
		vb.setAlignment(Pos.CENTER_LEFT);
		vb.setPadding(new Insets(10));
	
		vb.getChildren().addAll(btnNewGame, btnDarkMode);
		
		return vb;
	}
	
	/**
	 * Show score panel.
	 * Include score and current level.
	 * @return
	 */
	private Pane scorePanel() {
		VBox vb = new VBox();
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(10));
		
		HBox hbScore = new HBox();
		txtScoreValue = new Text("000");
		hbScore.getChildren().addAll(txtScore, txtScoreValue);
		
		HBox hbLevel = new HBox();
		txtLevelValue = new Text("000");		
		hbLevel.getChildren().addAll(txtLevel, txtLevelValue);
		
		vb.getChildren().addAll(hbScore, hbLevel);
		return vb;
	}
	
	/**
	 * Update score
	 * 
	 * @param value
	 */
	public void setScore(int value) {
		this.txtScoreValue.setText(Integer.toString(value));
	}
	
	/**
	 * Update level
	 * 
	 * @param value
	 */
	public void setLevel(int value) {
		this.txtLevelValue.setText(Integer.toString(value));
	}
	
	/**
	 * Get player name field
	 * @return
	 */
	public String getPlayerName() {
		return tbPlayerName.getText();
	}
	
	/**
	 * Select screen type the display
	 * @param screen
	 */
	public void updateGameField(GAME_FIELD_SCREEN screen) {
		vbGame.getChildren().clear();
		vbGame.getChildren().add(gameField(screen));
	}
	
	/**
	 * Update square array the display
	 * @param _arrSquare
	 */
	public void updateColorArray(model.Square[][] _arrSquare) {
		this.arrSquare = _arrSquare;
	}
	
	/**
	 * Set event to new game button
	 * @param event
	 */
	public void setClickListener_newGame(EventHandler<ActionEvent> event) {
		btnNewGame.setOnAction(event);
	}
	
	/**
	 * Set event to start game button
	 * @param event
	 */
	public void setClickListener_startGame(EventHandler<ActionEvent> event) {
		btnStartGame.setOnAction(event);
	}
	
	/**
	 * Set event to submit button
	 * @param event
	 */
	public void setClickListener_submitScore(EventHandler<ActionEvent> event) {
		btnSubmit.setOnAction(event);
	}
	
	/**
	 * Set event to cancel button
	 * @param event
	 */
	public void setClickListener_cancel(EventHandler<ActionEvent> event) {
		btnCancel.setOnAction(event);
	}
	
	/**
	 * Set event to level radio button
	 * @param event
	 */
	public void setClickListener_levelToggle(int rb, ChangeListener<Boolean> event) {
		if(rb == 1)
			rbLevel1.selectedProperty().addListener(event);
		else if(rb == 2)
			rbLevel2.selectedProperty().addListener(event);
		else if(rb == 3)
			rbLevel3.selectedProperty().addListener(event);
	}
	
	/**
	 * Set dark mode event
	 * @param event
	 */
	public void setClickListener_darkMode(EventHandler<ActionEvent> event) {
		btnDarkMode.setOnAction(event);
	}
	
	/**
	 * onClick event on the game tools
	 * @param event
	 */
	public boolean setOnClick_gameTool(int posX, int posY) {
		return gameTools[posX][posY].select();
	}
	
	/**
	 * Update the game tools display
	 * @param newArrSquare
	 */
	public void updateGameToolArray(model.Square[][] newArrSquare) {
		this.updateColorArray(newArrSquare);
		this.updateGameField(GAME_FIELD_SCREEN.GAME);
	}
	
	/**
	 * Update the TOP 10 players list
	 * @param _topPlayers
	 */
	public void updateTopPlayers(ArrayList<TopPlayers> _topPlayers) {
		this.topPlayers = _topPlayers;
	}
	
	/**
	 * Disable new game button
	 * @param value
	 */
	public void disableButton_newGame(boolean value) {
		btnNewGame.setDisable(value);
	}
	
	public boolean randomModeGame() {
		return cbRandomColor.isSelected();
	}
	
	/**
	 * Set dark mode
	 */
	public void setDarkMode() {
		if(!application.isDarkMode()) {
			application.setDarkBackground();
			
			txtGameTitle.getStyleClass().add("darkMode_txt");
			txtTop10.getStyleClass().add("darkMode_txt");
			
			rbLevel1.getStyleClass().add("darkMode_txt");
			rbLevel2.getStyleClass().add("darkMode_txt");
			rbLevel3.getStyleClass().add("darkMode_txt");
			cbRandomColor.getStyleClass().add("darkMode_txt");
			
			txtScore.getStyleClass().add("darkMode_txt");
			txtLevel.getStyleClass().add("darkMode_txt");
			txtScoreValue.getStyleClass().add("darkMode_txt");
			txtLevelValue.getStyleClass().add("darkMode_txt");
			txtSetName.getStyleClass().add("darkMode_txt");
			
			btnDarkMode.setText("Light Mode");
			btnDarkMode.getStyleClass().remove("darkMode_btn");
			btnDarkMode.getStyleClass().add("lightMode_btn");
		} else {
			application.setDarkBackground();
			
			txtGameTitle.getStyleClass().remove("darkMode_txt");
			txtTop10.getStyleClass().remove("darkMode_txt");
			
			rbLevel1.getStyleClass().remove("darkMode_txt");
			rbLevel2.getStyleClass().remove("darkMode_txt");
			rbLevel3.getStyleClass().remove("darkMode_txt");
			cbRandomColor.getStyleClass().remove("darkMode_txt");
			
			txtScore.getStyleClass().remove("darkMode_txt");
			txtLevel.getStyleClass().remove("darkMode_txt");
			txtScoreValue.getStyleClass().remove("darkMode_txt");
			txtLevelValue.getStyleClass().remove("darkMode_txt");
			txtSetName.getStyleClass().remove("darkMode_txt");
			
			btnDarkMode.setText("Dark Mode");
			btnDarkMode.getStyleClass().add("darkMode_btn");
			btnDarkMode.getStyleClass().remove("lightMode_btn");
		}
	}
}
