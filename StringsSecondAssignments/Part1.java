package StringsSecondAssignments;


/**
 * Create a new Java project named StringsSecondAssignments.
 * You can put all the classes for this programming exercise in this project.
 * Create a new Java Class named Part1. The following methods go in this class.

 */
public class Part1
{

    /**
     * Write the method findStopCodon that has three parameters, a String parameter named dna, an integer parameter
     * named startIndex that represents where the first occurrence of ATG occurs in dna,
     * and a String parameter named stopCodon. This method returns the index of the first occurrence of stopCodon
     * that appears past startIndex and is a multiple of 3 away from startIndex.
     * If there is no such stopCodon, this method returns the length of the dna strand.
     */
    public int findStopCodon(String dna,int first,String stop)
    {
        int currIdx=dna.indexOf(stop,first+3);
        
        while(currIdx!=-1)
        {
            
            if((currIdx-first)%3==0)
            {
                return currIdx;
            }
            currIdx=dna.indexOf(stop,currIdx+1);
           
        }
        return dna.length();
            
        
    }

    /**
     * Write the void method testFindStopCodon that calls the method findStopCodon with several examples
     * and prints out the results to check if findStopCodon is working correctly.
     * Think about what types of examples you should check. For example, you may want
     * to check some strings of DNA that have genes and some that do not

     */
    public void testFindStopCodon()
    {
        
        String dna1="CAAATAAATTTAA";//valid
       
        int res=findStopCodon(dna1,3,"TAA");
        System.out.println(res);
       
        res=findStopCodon("CAAATGAATAAATAGGTATAA",3,"TAG");//valid
        System.out.println(res);
      
      
    }

    /**
     *Write the void method testFindGene that has no parameters. You should create five DNA strings.
     *  The strings should have specific test cases such as DNA with no “ATG”, DNA with “ATG” and one valid stop codon,
     *  DNA with “ATG” and multiple valid stop codons, DNA with “ATG” and no valid stop codons
     */
    public void  testFindGene()
    {
        String dna1="CAAATAAATTTAA";//not valid
        String res=findGene(dna1,0);
        System.out.println("DNA is "+dna1);
        System.out.println("Gene is "+res);
        
        dna1="CAAATGAATAAATAA";//valid
        res=findGene(dna1,0);
        System.out.println("DNA is "+dna1);
        System.out.println("Gene is "+res);
        
        dna1="CAAATGAATAAATAGGTATAA";//valid
        res=findGene(dna1,0);
        System.out.println("DNA is "+dna1);
        System.out.println("Gene is "+res);
      
       dna1="CAAATGAATAAATGGTATAA";//not valid
        res=findGene(dna1,0);
        System.out.println("DNA is "+dna1);
        System.out.println("Gene is "+res);
       
        
      dna1="AATGCTAACTAGCTGACTAAT";//valid
        res=findGene(dna1,0);
        System.out.println("DNA is "+dna1);
        System.out.println("Gene is "+res);
       
     
    }
/**


 * Write the method findGene that has one String parameter dna, representing a string of DNA. In this method you should do the following:
 * Find the index of the first occurrence of the start codon “ATG”. If there is no “ATG”, return the empty string.
 * Find the index of the first occurrence of the stop codon “TAA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”.
 * Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. Find the index of the first occurrence of the
 * stop codon “TGA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”.
 * Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away. If there is no valid stop codon and therefore no gene, return the empty string.
*/

 public String findGene(String dna,int startIdx){
        
        int first=dna.indexOf("ATG",startIdx);
        if(first==-1){
            return "";
        }
        int idx1=findStopCodon(dna,first,"TAA");
        int idx2=findStopCodon(dna,first,"TAG");
        int idx3=findStopCodon(dna,first,"TGA");
        
        int min=0;
        if(idx1<idx2&&idx1<idx3){
            min=idx1;
        }
        else if(idx2<idx3){
            min=idx2;
        }
        else{
            min=idx3;
          }
          
        if(min==dna.length()){
            return "";
        }
        return dna.substring(first,min+3);
     }

    /**
     * Write the void method printAllGenes
     * that has one String parameter dna, representing a string of DNA

     */
     public void  printAllGenes(String dna){
         
         int startIdx=0;
         String Gene="";
         while(true){
             Gene=findGene(dna,startIdx);
             if(Gene.length()==0){
                 
                 break;
                }
             System.out.println(Gene);
             startIdx=dna.indexOf(Gene)+Gene.length();
            }
        }
        
       public void testPrinting(){
           
           String dna1="ATGATCTAATTTATGCTGCAACGGTGAAGA";
           System.out.println("Genes in "+dna1);
           printAllGenes(dna1);
           
           dna1="ATGATCATAAGAAGATAATAGTAGAGGGCCATGTAA";
           System.out.println("Genes in "+dna1);
           printAllGenes(dna1);
        }


        public static void main(String args[]){
            Part1 obj=new Part1();

            obj.testPrinting();


        }
        
   
     
    }
        