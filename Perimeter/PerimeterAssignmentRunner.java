package perimeter;

import edu.duke.*;
import java.io.File;

/**
  *Questions are commented above each method
*/
public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    /*
        * Complete writing the method getNumPoints that has one parameter s that is
        * of type Shape. This method returns an integer that is the number of points in Shape s.
        * Add code in the method testPerimeter to call getNumPoints and to print the result.
     */
    public int getNumPoints (Shape s) {
        int count=0;
        
        for(Point curr : s.getPoints())
        {
            count++;
        }
        return count;
    }

    /*
        *  Complete writing the method getAverageLength that has one parameter s that is of type Shape.
        *  This method returns a number of type double that is the calculated average of all the sides’
        *  lengths in the Shape S.Add code in the method testPerimeter to call the method
        *  getAverageLength and to print out the result.
     */


    public double getAverageLength(Shape s) 
    {
        double p=getPerimeter(s);
        int count=getNumPoints(s);
 
  
        return p/count;
    }
    /*
        * Complete writing the method getLargestSide that has one parameter s that is of type Shape.
        * This method returns a number of type double that is the longest side in the Shape S.
        * Add code in the method testPerimeter to call the method getLargestSide and to print out the result.

     */

    public double getLargestSide(Shape s) {
        
        double largest=0.0;
        Point prev=s.getLastPoint();
        
        for(Point curr:s.getPoints())
        {
            double cDist=prev.distance(curr);
            if(cDist>largest){
                largest=cDist;
            }
            prev=curr;
        }
        return largest;
    }

    /*
       * Complete writing the method getLargestX that has one parameter s that is of type Shape.
       * This method returns a number of type double that is the largest x value over all the points in the Shape s.
       * Add code in the method testPerimeter to call the method getLargestX and to print out the result.

     */
    public int getLargestX(Shape s) {
        int largeX=0;
        
        for(Point curr:s.getPoints())
        {
          
            if(curr.getX()>largeX){
                largeX=curr.getX();
            }
            
        }
        return largeX;
    }

    /*
        * Complete writing the method getLargestPerimeterMultipleFiles that has no parameters.
        * It needs to return the the largest perimeter over all the shapes in the files you have selected.
     */
    public double getLargestPerimeterMultipleFiles() {
        
        DirectoryResource dr=new DirectoryResource();
        double large=0.0;
        for(File f : dr.selectedFiles())
        {
            FileResource fr=new FileResource(f);
            Shape s=new Shape(fr);
            double perimeter=getPerimeter(s);
            if(perimeter>=large)
            {
                large=perimeter;
            }
        }
        
            
        return large;
    }

    /*
        * Finish writing the method getFileWithLargestPerimeter that has no parameters.
        * This method should, like the getLargestPerimeterMultipleFiles method, create its own Directory Resource, except that this
        *  new method returns the File that has the largest such perimeter, so it has return type File.
     */

    public String getFileWithLargestPerimeter() {
        
        
        DirectoryResource dr=new DirectoryResource();
        double large=0.0;
        File temp = null; 
        
        for(File f:dr.selectedFiles())
        {
            FileResource fr=new FileResource(f);
            Shape s=new Shape(fr);
            
            double perim=getPerimeter(s);
            if(perim>=large)
            {
                large=perim;
                temp=f;
            }
        }
      
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.printf("\nperimeter = %.2f",length);
        int count=getNumPoints(s);
        System.out.println("Number of points = " + count);
        
        double avg=getAverageLength(s);
        System.out.printf("\nAverage Length = %.2f",avg);
         double large=getLargestSide(s);
        System.out.printf("\nlargest Side = %.2f",large);
        int largeX=getLargestX(s);
        System.out.println("largest X = "+largeX);


    }
    /*
       *  Finish writing the void method testPerimeterMultipleFiles to call getLargestPerimeterMultipleFiles
       *  and to print out the largest such perimeter. This method has no parameters and no return value.
     */
    
    public void testPerimeterMultipleFiles() {
        
        double large=getLargestPerimeterMultipleFiles();
        System.out.printf("Largest Perimeter Multiple Files = %.2f",large);
        
    }

    /*
        * Add code to the method testFileWithLargestPerimeter to call getFileWithLargestPerimeter
     */
    public void testFileWithLargestPerimeter() {
        String File_name=getFileWithLargestPerimeter();
        System.out.println("File Name of Largest Perimeter Multiple Files = "+File_name);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }
    /*
        *This method prints names of all files in a chosen folder that you can use to test your other methods
     */
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
