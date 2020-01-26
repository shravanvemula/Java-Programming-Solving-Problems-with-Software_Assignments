package StringsFirstAssignments;


/**
 * Create a new Java project named StringsFirstAssignments. You can put all the classes for this programming exercise in this project.
 * Create a new Java Class named Part1. The following methods go in this class.

 */
public class Part1
{


    /**
     * Write the method findSimpleGene that has one String parameter dna, representing a string of DNA. This method does the following:
     * Finds the index position of the start codon “ATG”. If there is no “ATG”, return the empty string.
     * Finds the index position of the first stop codon “TAA” appearing after the “ATG” that was found. If there is no such “TAA”, return the empty string.
     * If the length of the substring between the “ATG” and “TAA” is a multiple of 3, then return the substring that starts with that “ATG” and ends with that “TAA”.
     */
    public String findSimpleGene(String dna)
    {
        String result="";
        int sIdx=dna.indexOf("ATG");
        if(sIdx==-1){
            return "";
        }
        int eIdx=dna.indexOf("TAA",sIdx+3);
        if(eIdx==-1)
        {
            return "";
        }
        result=dna.substring(sIdx,eIdx+3);
        
        if(result.length()%3==0)
        {
            return result;
        }
        return "";
        
    }
    /**
        * Write the void method testSimpleGene that has no parameters. You should create five DNA strings.
        * The strings should have specific test cases, such as: DNA with no “ATG”, DNA with no “TAA”, DNA with no “ATG” or “TAA”,
        * DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene), and DNA with ATG, TAA and
        * the substring between them is not a multiple of 3.

     */

    public void testSimpleGene()
    {
        String dna1="CCCATGTCAAATGTATAACTG";
        String dna2="CCCATGTCAAATGTACTG";
        String dna3="CCCTCAAATGTATAACTG";
        String dna4="AAATGCCCTAACTAGATTAAGAAACC";
        String res=findSimpleGene(dna1);
        System.out.println("The Gene is "+res);
        res=findSimpleGene(dna2);
        System.out.println("The Gene is "+res);
        res=findSimpleGene(dna3);
        System.out.println("The Gene is "+res);
        res=findSimpleGene(dna4);
        System.out.println("The Gene is "+res);
        
    }
    public static void main(String args[]){
        Part1 obj=new Part1();
        obj.testSimpleGene();
    }
}


