package StringsFirstAssignments;
import edu.duke.*;

/**
 * Create a new Java project named StringsFirstAssignments. You can put all the classes for this programming exercise in this project.
 * Create a new Java Class named Part4. The following methods go in this class.

 */
public class Part4
{


    /**
     * For each word, check to see if “youtube.com” is in it. If it is, find the double quote to the left and
     * right of the occurrence of “youtube.com” to identify the beginning and end of the URL.
     * Note, the double quotation mark is a special character in Java. To look for a double quote, look for (\”), since the backslash (\) character indicates we want the literal quotation marks (“) and not the Java character.
     * The string you search for would be written “\”” for one double quotation mark.
     * This method returns the last match from the start of the string up to the num position, so it is a good option for
     * finding the opening quotation mark of a string searching backward from “youtube.com.”

     */
    public void findURL() 
    {
       URLResource ur=new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html" );
       String res="",line1="";
       for(String line:ur.lines())
       {
           line1=line.toLowerCase();
           int idx=line1.indexOf("youtube.com");
           if(idx!=-1)
           {
               int start=line1.indexOf("\"",line1.indexOf("href"));
               int end=line1.indexOf(">",start);
               res=line.substring(start,end);
               System.out.println(res);   
            }
            
            
        }
    }
    public static void main(String args[]){
        Part4 obj=new Part4();
        obj.findURL();
    }
}
