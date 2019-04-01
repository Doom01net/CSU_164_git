package P8.src; //TODO remove this before submitting (last)
// P8 Assignment
// Author: Bogdan A. Vasilchenko
// Date:   03/05/2019
// Class:  CS164
// Email:  vba@cs.colostate.edu
import java.util.BitSet;
/**
 *
 * Objectives of this Assignment

    Implement a set of methods that allow a GUI object to use your class,
    instantiate and call a supplied class to read and write images,
    declare and use 2D arrays to store images, and
    manipulate the data in 2D arrays to transform images.

**/

/**
 *  Description

    The purpose of the assignment is to write a Java class that can be called by
    a user interface program to unscramble images in the Portable GreyMap (PGM) format.
    To do this you need to write an object called Transform that inherits from an interface
    and implements all methods in that interface. It also instantiates and calls methods in PictureLibrary.java,
    and is in turn called from a graphical user interface (GUI) class called ImageProgram.java. Both
    of these are supplied below.
 */



/** Testing: The decode method can be tested with Decode.pgm.
 *  The swap method can be tested with Swap.pgm. The mirror method can be tested with Mirror.pgm.
 *  The exchange method can be tested with Exchange.pgm. In all cases, the restored image should be identical to Cam.pgm.
 *  After unscrambling an image you can write it to the disk, and compare it to Cam.pgm using the Linux diff command,
 *  thus all students can verify that their code is perfect before submission! NOTE: We may test your code with an image
 *  file that has a different size and contents than the provided test file, so do not hardcode anything. Please follow
 *  the usual rules for submitting Java programs.
 */

//import com.sun.xml.internal.bind.v2.TODO;

public class Transforms implements ImageInterface {


    private PictureLibrary library;
    private int imageWidth = 0;
    private int imageHeight = 0;
    private int[][] data;

    public Transforms(){
        //Instantiate PictureLibrary object
        library = new PictureLibrary();

    }


    @Override
    public void readImage(String inFile) {
        try {
            // Calls to readPGM or writePGM and associated code here
            library.readPGM(inFile);
            data = library.getData();
            imageHeight = library.getHeight();
            imageWidth = library.getWidth();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void writeImage(String outFile) {
        try {
            library.setData(data);
            library.writePGM(outFile);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    @Override
    public int[][] imageData() {
        return data;
    }



    @Override
    public void decode() {

        for (int i = 0; i < data.length; i ++){

            for (int j = 0; j < data[i].length; j++){

                //each pixel has had the upper four bits negated
                int originalPixel = data[i][j];
                int upperValue = originalPixel / 16;
                int lowerValue = originalPixel % 16;
                int newUpperValue = 15 - upperValue;
                int newPixel = (newUpperValue * 16) + lowerValue;
                data[i][j] = newPixel;



            }
        }

        /** Calling decode restores an image in which each pixel has had the upper four bits negated.
         * To unscramble the image, your code should negate them again. You can use bitwise operators or
         * the following algorithm: To get the upper bits, divide the pixel by 16. To get the lower bits,
         * modulo the pixel by 16. The resulting upper and lower values should be in the range 0..15. Negate
         * the upper bits as follows: upper = 15 - upper; Then put the bits back together by multiplying the upper
         * bits by 16 and adding the result to lower. Here's an example for your testing:

         original pixel = 115 = 0b01110011
         upper value = 115 / 16 = 7 = 0b0111 (upper four bits of original)
         lower value = 115 % 16 = 3 = 0b0011 (lower four bits of original)
         negate upper value = 15 - 7 = 8 = 0b1000
         new pixel = (upper * 16) + lower = (8 * 16) + 3 = 131 = 0b10000011 **/
    }

    @Override
    public void swap() {


        for(int i = 0; i < data.length; i ++) {
            for (int j = 0; j < data[i].length; j++) {


                int originalPixel = data[i][j];

                int upperTwoBits = originalPixel & (int)0b11000000;
                int lowerTwoBits = originalPixel & (int)0b00000011;
                int middleFourBits = originalPixel & (int)0b00111100;
                int newPixel = (lowerTwoBits << 6) | middleFourBits | (upperTwoBits >> 6);

                data[i][j] = newPixel;




            }

        }
    }

    @Override
    public void mirror() {

        //int n = 0;

        for (int i = 0; i < data.length / 2; i ++) {

            int[] swap = data[i];
            data[i] = data[data.length - 1 - i];
            data[data.length - 1 - i] = swap;


        }


    }

    @Override
    public void exchange() {
        /** Calling exchange swaps the image area defined by a rectangle
         *  with width 150 and height 300 starting at row index 10 and column
         *  index 10 for a rectangle of the same size starting at row index 10,
         *  and column index 310.
         *
         */
        for (int i = 10; i < 310; i ++) {

            for (int j = 10; j < 160; j++) {

                int swap = data[i][j];
                data[i][j] = data[i][j + 300];
                data[i][j + 300] = swap;


            }

        }
    }


}
