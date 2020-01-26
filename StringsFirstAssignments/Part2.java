package StringsFirstAssignments;

    /**
        * Create a new Java Class named Part2 in the StringsFirstAssignments project.
    */

public class Part2
{

    /**
     * Copy and paste the  methods findSimpleGene
     * from the Part1 class into the Part2 class.

     */

    
    public String findSimpleGene(String dna,String startCodon,String stopCodon)
    {
        String result="";
        String dnaUpper=dna.toUpperCase();
        int sIdx=dnaUpper.indexOf(startCodon);
        if(sIdx==-1){
            return "";
        }
        int eIdx=dnaUpper.indexOf(stopCodon,sIdx+3);
        if(eIdx==-1)
        {
            return "";
        }
        result=dnaUpper.substring(sIdx,eIdx+3);
        
        if(result.length()%3==0)
        {
            char ch=dna.charAt(0);
            
            if(Character.isUpperCase(ch))
                return result;
            else
                return result.toLowerCase();
        }
        return "";
        
    }

    /**
       * Copy and paste the  methods testSimpleGene from the Part1 class into the Part2 class.
     */


    public void testSimpleGene()
    {
        String dna1="CCCATGTCAAATGTATAACTG";
        String dna2="ATGGGTTAAGTC”";
        String dna3="gatgctataat";
        String dna4="cccatgtcaatgtattaactg";
        String res=findSimpleGene(dna1,"ATG","TAA");
        System.out.println("The Gene is "+res);
        res=findSimpleGene(dna2,"ATG","TAA");
        System.out.println("The Gene is "+res);
        res=findSimpleGene(dna3,"ATG","TAA");
        System.out.println("The Gene is "+res);
        res=findSimpleGene(dna4,"ATG","TAA");
        System.out.println("The Gene is "+res);
        
    }

    public static void main(String args[]){

        Part2 obj=new Part2();
        obj.testSimpleGene();
    }
}
