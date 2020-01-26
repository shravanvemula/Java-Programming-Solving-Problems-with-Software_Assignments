import edu.duke.*;

import java.io.File;

public class GrayImages
{


    /**
     *
     * Converting images to GrayScale
     * @param inImage
     * @return
     */

    public ImageResource makeGray(ImageResource inImage){


        ImageResource outImage=new ImageResource(inImage.getWidth(),inImage.getHeight());

        for(Pixel pixel:outImage.pixels()){

            Pixel inPixel=inImage.getPixel(pixel.getX(),pixel.getY());

            int avg=(inPixel.getRed()+inPixel.getBlue()+inPixel.getGreen())/3;

            pixel.setBlue(avg);
            pixel.setRed(avg);
            pixel.setGreen(avg);

        }
        return outImage;

    }

    /**
     * Selecting Multiple Images and converting to GrayScale and saving them with
     * a new file name
     * For example, if the original file was named lion.png,
     *  the new image would be a grayscale image and be named gray-lion.png.
     */


    public void savingMultipleImages() {
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {

            ImageResource image = new ImageResource(f);
            ImageResource grayImage = makeGray(image);
            String fname = image.getFileName();
            String newName = "gray-" + fname;

            grayImage.setFileName(newName);

            grayImage.save();

        }
    }

    public static void main(String args[])
    {

        GrayImages obj=new GrayImages();
        obj.savingMultipleImages();
    }



}
