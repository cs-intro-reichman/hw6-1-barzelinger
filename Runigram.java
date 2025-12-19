import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);
		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		image = scaled(tinypic, 5, 5);
		System.out.println();
		print(image);
		

		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		for (int i = 0; i < numRows; i++){
			for (int j = 0; j < numCols; j++){
			int r = in.readInt();
			int g = in.readInt();
			int b = in.readInt();
			image[i][j] = new Color(r, g, b);
		}
	}
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		// goes over each pixel and uses the print function of single color and prints it.
		for (int i = 0; i < image.length; i++){
			for (int j = 0; j < image[0].length; j++){
				print(image[i][j]);
			}
			System.out.println();
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		// get amount of rows of the image.
		int row = image.length;
		// get the amount of columns of the image. 
		int col = image[0].length;

		// create a new Collor two dimensional array in the same dimansions of the original image.
		Color[][] flipped = new Color[row][col];

		// go over each pixel from the original image and flip each row in the new one.
		for (int i = 0; i < row; i++){
			for (int j = 0; j < col; j++){
				flipped[i][j] = image[i][col - 1 - j];
			}
		}
		return flipped;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		// get amount of rows of the image.
		int row = image.length;
		// get amount of columns of the image.
		int col = image[0].length;

		// create a new Collor two dimensional array in the same dimansions of the original image.
		Color[][] flipped = new Color[row][col];
		
		// go over each pixel from the original image and flip each column in the new one.
		for (int i = 0; i < row; i++){
			for (int j = 0; j < col; j++){
				flipped[i][j] = image[row - 1 - i][j];
			}
		}
		return flipped;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {

		// calculates the luminance of the RGB values of the given pixel.
		int lum = (int) (0.299 * pixel.getRed()  + 0.587 * pixel.getGreen() + 0.114 * pixel.getBlue());
		
		// save the new collor.
		Color gray = new Color(lum, lum, lum);

		return gray;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		
		// get amount of rows of the image.
		int row = image.length;
		// get amount of columns of the image.
		int col = image[0].length;

		// create a new Collor two dimensional array in the same dimansions of the original image.
		Color[][] grayscaled = new Color[row][col];

		// go over each pixel and use the luminance function on it.
		for (int i = 0; i < row; i++){
			for (int j = 0; j < col; j++){
				grayscaled[i][j] = luminance(image[i][j]);
			}
		}
		return grayscaled;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		// get amount of rows of the image.
		int row = image.length;
		// get amount of columns of the image.
		int col = image[0].length;

		// create a new Collor two dimensional array in the given dimensions.
		Color[][] scaledImg = new Color[height][width];
		
		// go over each pixel and scale it to fit the new dimensions.
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				int oldrow = i * row / height;
				int oldcol = j * col / width;
				scaledImg[i][j] = image[oldrow][oldcol];
			}
		}

		return scaledImg;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		// blend the RGB values of two colors.
		int newRed = (int) (alpha * c1.getRed() + (1 - alpha) * c2.getRed());
		int newGreen = (int) (alpha * c1.getGreen() + (1 - alpha) * c2.getGreen());
		int newBlue = (int) (alpha * c1.getBlue() + (1 - alpha) * c2.getBlue());
		
		// create a new color from the blended given colors.
		Color newColor = new Color(newRed, newGreen, newBlue);

		return newColor;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		
		// create a new Color two dimensional array with the same dimensions of the given image.
		Color[][] blenedColors = new Color[image2.length][image2[0].length];

		// gor over each pixel of both images and blend them.
		for (int i = 0; i < image1.length; i++){
			for (int j = 0; j < image1[0].length; j++){
				blenedColors[i][j] = blend(image1[i][j], image2[i][j], alpha);
			}
		}
		return blenedColors;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		
		// make sure the target image's dimensions are the same as the source image.
		Color[][] targetScaled = scaled(target, source[0].length, source.length);
		setCanvas(targetScaled);
		StdDraw.show();

		// go over each pixel of each image and gradually blend them together until we get the target image. 
		for (int i = 0; i <= n; i++){
			double alpha = (double) i / n; // the amount of steps it will take to motph the two images.
			Color[][] frame = blend(targetScaled, source, alpha);
			display(frame);
			StdDraw.pause(20);
		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

