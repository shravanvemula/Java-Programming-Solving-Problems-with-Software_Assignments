package StringsSecondAssignments;


/**
 * Create a new Java Class named Part2in the StringsSecondAssignments project
 */
public class Part2
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Part2
     */
    public Part2()
    {
        // initialise instance variables
        x = 0;
    }

    /**

     * Write the method named howMany that has two String parameters named stringa and stringb.
     * This method returns an integer indicating how many times stringa appears in stringb, where each occurrence of stringa must not overlap with another occurrence of it. For example, the call howMany(“GAA”, “ATGAACGAATTGAATC”) returns 3 as GAA occurs 3 times.
     * The call howMany(“AA”, “ATAAAA”) returns 2.Note that the AA’s found cannot overlap.

     */
    public int howMany(String a,String b)
    {
        int sIdx=0,curr=0;
        int count=0;
        while(true){
            curr=b.indexOf(a,sIdx);
            
            if(curr==-1)
            {  
                break;
            }
            count++;
            sIdx=curr+a.length();
        }
        return count;
            
    }
    /**
     * Write the void method testHowMany has no parameters.
     * Add code in here to call howMany with several examples and print the results.

     */
    public void testHowMany(){
            int res=howMany("GAA","ATGAACGAATTGAATC");
            System.out.println("Occurences of GAA in ATGAACGAATTGAATC is "+res);
            
            res=howMany("AA","ATAAAA") ;
            System.out.println("Occurences of AA in ATAAAA is "+res);
            
            res=howMany("hi","hello") ;
            System.out.println("Occurences of hi in hello  is "+res);
        }
            
}
