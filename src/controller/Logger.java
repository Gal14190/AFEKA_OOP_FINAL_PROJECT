//Gal Ashkenazi 315566752 - Final Test
package controller;

/**
 * Log printer
 * @author GalAs
 *
 */
public class Logger {
	public static void log(int x_0, int x_1, int y_0, int y_1, int area, int level) {
		int sizeY, sizeX;
		sizeY = x_1 - x_0 + 1;
		sizeX = y_1 - y_0 + 1;
		
		System.out.printf("level %d: rectangle -\n\t area: %d\n\t coordinate: (x0, x1, y0, y1) = (%d, %d, %d, %d)\n\t size: %dx%d\n", level, area, y_0, y_1, x_0, x_1, sizeX, sizeY);
	}
}
