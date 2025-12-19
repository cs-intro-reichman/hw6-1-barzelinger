import java.awt.Color;

/**
 * Demonstrates a visual morphing effect that gradually turns a colored image
 * into its grayscale (black and white) version.
 *
 * The program receives two command-line arguments: the name of a PPM image file
 * and an integer that determines how many steps the transformation will take.
 *
 * For example, running:
 *     java Editor4 thor.ppm 50
 * will smoothly transform the colored Thor image into a grayscale image
 * over 50 animation steps.
 */

public class Editor4 {

    public static void main (String[] args){

        // get the file name and the amount of steps to morph it.
        String fileName = args[0];
        int n = Integer.parseInt(args[1]);

        // read and create new image of the source.
        Color[][] imageIn = Runigram.read(fileName);
        // create new grayscaled image of the source image.
        Color[][] imageOut = Runigram.grayScaled(imageIn);
        
        // morph rhe source image and the grayscaled image and display it.
		Runigram.morph(imageIn, imageOut, n);
    }
    

}
