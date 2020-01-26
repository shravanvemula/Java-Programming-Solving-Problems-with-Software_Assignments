package StringsSecondAssignments;


/**
 * Create a new Java Class named Part3 in the StringsSecondAssignments project.

 */
public class Part3
{
    /**
     * Copy your methods from Part1 to find one gene and print all genes.

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
     * Write the method named countGenes that has a String parameter named dna representing a string of DNA.
     * This method returns the number of genes found in dna. For example the call countGenes(“ATGTAAGATGCCCTAGT”) returns 2,
     * finding the gene ATGTAA first and then the gene ATGCCCTAG. Hint: This is very similar to finding all genes and printing them,
     * except that instead of printing all the genes you will count them.

     */
     public int countGenes(String dna){
         
         int startIdx=0,count=0;
         String Gene="";
         while(true){
             Gene=findGene(dna,startIdx);
             if(Gene.length()==0){
                 
                 break;
                }
                count++;
      
             startIdx=dna.indexOf(Gene)+Gene.length();
            }
            return count;
        }

    /**
     * Write the void method named testCountGenes that has no parameters.
     * This method calls countGenes with many example strings and prints the result for each.
     * You should create several examples with different numbers of genes to test your code.

     */
    public void testCountGenes(){
           
           String dna1="ATGATCTAATTTATGCTGCAACGGTGAAGA";
           int count=countGenes(dna1);
           System.out.println("Genes in "+dna1+ " are "+count);
           
           
           dna1="ATGATCATAAGAAGATAATAGTAGAGGGCCATGTAA";
            count=countGenes(dna1);
           System.out.println("Genes in "+dna1+ " are "+count);
           
           dna1="ATGTAAGATGCCCTAGT";
            count=countGenes(dna1);
           System.out.println("Genes in "+dna1+ " are "+count);
           
           
           
        }

        public static void main(String args[])
        {

        Part3 obj=new Part3();
        obj.testCountGenes();
        }
}
