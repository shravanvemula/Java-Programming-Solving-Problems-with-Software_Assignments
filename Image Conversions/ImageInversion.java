import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class ImageInversion
{


    /**
     *
     * Converting image it inverted image.
     * Write a program to create new images that are photographic negatives (or inverted images) of selected images
     *  In inverting an image, a pixel’s red, blue, and green components are modified to be the
     *  exact opposite within the 0 to 255 range. That is, if a pixel’s red, blue, and green values are (34, 198, 240),
     *  then that same pixel in the inverted image would have the red, blue and green values of (221, 57, 15).
     *  Note that 255 - 34 is 221, 255 - 198 is 57, and 255 - 240 is 15.
     * @param inImage
     * @return
     */

    public ImageResource invertImage(ImageResource inImage){


        ImageResource outImage=new ImageResource(inImage.getWidth(),inImage.getHeight());

        for(Pixel pixel:outImage.pixels()){

            Pixel inPixel=inImage.getPixel(pixel.getX(),pixel.getY());

            int newRedValue = 255-inPixel.getRed();
            int newGreenValue = 255-inPixel.getGreen();
            int newBlueValue = 255-inPixel.getBlue();


            pixel.setBlue(newBlueValue);
            pixel.setRed(newRedValue);
            pixel.setGreen(newGreenValue);

        }
        return outImage;

    }

    /**
     *  Save these new images with filenames that are related to the original images, such as adding “inverted-” in front of the old filename.
     */


    public void savingMultipleInvertedImages() {
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {

            ImageResource image = new ImageResource(f);
            ImageResource invertedImage = invertImage(image);
            String fname = image.getFileName();
            String newName = "inverted-" + fname;

            invertedImage.setFileName(newName);

            invertedImage.save();

        }
    }

    public static void main(String args[])
    {

        ImageInversion obj=new ImageInversion();
        obj.savingMultipleInvertedImages();
    }












}
