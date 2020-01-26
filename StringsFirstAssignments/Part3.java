package StringsFirstAssignments;


/**
 *  Create a new Java Class named Part3 in the StringsFirstAssignments project.
 *  Put the following methods in this class.
 */
public class Part3
{
    /**
        * Write the method named twoOccurrences that has two String parameters named stringa and stringb.
        * This method returns true if stringa appears at least twice in stringb, otherwise it returns false.
     */
    public boolean twoOccurences(String a,String b)
    {
        int idx1=b.indexOf(a);
        if(idx1==-1)
        {
            return false;
        }
        int idx2=b.indexOf(a,idx1+a.length());
        if(idx2==-1)
        {
            return false;
        }
        return true;
    }

    /**
     * Write the method named lastPart that has two String parameters named stringa and stringb.
     * This method finds the first occurrence of stringa in stringb, and returns the part of stringb that follows stringa.
     * If stringa does not occur in stringb, then return stringb.

     */

    public String lastPart(String a,String b)
    {
        int idx=b.indexOf(a);
        if(idx==-1){
            return b;
        }
        String res=b.substring(idx+a.length());
        return res;
    }
    /**
        * Write the void method named testing that has no parameters. This method should
        * call twoOccurrences on several pairs of strings and print the strings and
        * the result of calling twoOccurrences (true or false) for each pair.

     */
     public void testing(){
         boolean res=twoOccurences("by","A story by Abby");
         System.out.println(res);
         res=twoOccurences("a","banana");
         System.out.println(res);
         res=twoOccurences("atg","ctgatgta");
         System.out.println(res);
         
         String res1=lastPart("a","banana");
         System.out.println(res1);
         res1=lastPart("zoo","forest");
         System.out.println(res1);
         



    }

    public  static void main(String args[]){
        Part3 obj=new Part3();
        obj.testing();
    }
}
