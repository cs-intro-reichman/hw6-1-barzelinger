import java.awt.Color;

/**
 * Demonstrates the morphing operation featured by Runigram.java. 
 * The program recieves three command-line arguments: a string representing the name
 * of the PPM file of a source image, a string representing the name of the PPM file
 * of a target image, and the number of morphing steps (an int). 
 * For example, to morph the cake into ironman in 50 steps, use:
 * java Editor3 cake.ppm ironman.ppm 50
 * Note: There is no need to scale the target image to the size of the source
 * image, since Runigram.morph performs this action.
 */
public class Editor3 {

	public static void main (String[] args) {
		
		// get the file names and the amount of steps to morph them.
		String source = args[0];
		String target = args[1];
		int n = Integer.parseInt(args[2]);

		// read and create new images of the source and target.
		Color[][] sourceImage = Runigram.read(source);
		Color[][] targetImage = Runigram.read(target);

		// morph both images and display it.
		Runigram.morph(sourceImage, targetImage, n);
	}
}
