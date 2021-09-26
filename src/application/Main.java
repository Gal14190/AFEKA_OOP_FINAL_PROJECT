//Gal Ashkenazi 315566752 - Final Test
package application;
	
import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import view.View;


/**
 * ## BUILD
 * 
 * Created at 17/09/2021.
 * Tested on Afeka vLab2021 (JavaSE-1.8) at 19/08/2021.
 *
 * GitHub: https://github.com/Gal14190/AFEKA_OOP_FINAL_PROJECT
 *
 * ## ABOUT "COLORS GAME"
 * 
 * "ColorGame" created for the Final Test in OOP Afeka course 2021.
 * With this program you will play thinking game by selecting 4 squares in the same color.
 * The selected need to create rectangle.
 * You can scoring points by choosing the biggest rectangle you can find, more rectangle more points. 
 * 
 * ## ABOUT THE PROGRAM:
 * 
 * This program based on OOP course. using MVC, class, function, objects, read and write file, 
 * error handle (throws Exception), polymorphism, abstract and super class, extends, javaFX, 
 * arrays, list, sort, event, generic, math, etc. 
 * 
 * ## EXTRA FEATURES:
 *  
 * - Welcome screen. 
 * - Two modes to play: RANDOM COLOR and ONE COLOR.
 * - Dark Mode.
 * - Style sheets document (.css file).
 * - New buttons design.
 * - Logger. Print history on eclipse console when rectangle selected and print coordinates.
 * 
 * ## HOW TO OPERATE ON AFEKA'S COMPUTERS OR AFEKA vLAB2021:
 * 
 * Follow:
 * 1. Change the project JR properties work with JavaSE-1.8.
 * 2. Delete moudle-info.java.
 * 3. Ignore javafx errors.
 * 
 * @author GalAs
 *
 */

/**
 * The main class is MVC initialization 
 */
public class Main extends Application {
	
	@Override
	public void start(Stage stage) {
		//MVC initialization
		Model model = new Model();
		View view = new View();
		Controller controller = new Controller(model, view);
		
		// start the application
		controller.start(stage);
		
	}
	
	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {	
		launch(args);
	}
}
