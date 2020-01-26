
import edu.duke.*;
import java.lang.*;

/**
 *Create a new Java project named StringsThirdAssignments.
 * You can put all the classes for this programming exercise in this project
 * Create a new Java Class named Part1. Copy and paste the code from your Part1 class in your
 * StringsSecondAssignments project into this class.
 */


public class Part1 {

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


    public String findGene(String dna1,int startIdx){
        String dna="";
        dna=dna1.toUpperCase();
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
        if(Character.isLowerCase(dna1.charAt(0))){
            dna=dna1.toLowerCase();
        }
        return dna.substring(first,min+3);
    }

    /**
     *Create a method called getAllGenes. Instead of printing the genes found,
     * this method should create and return a StorageResource containing the genes found.
     */


    public StorageResource getAllGenes(String dna){

        StorageResource sr=new StorageResource();

        int startIdx=0;
        String Gene="";
        while(true){
            Gene=findGene(dna,startIdx);
            if(Gene.length()==0){

                break;
            }
            sr.add(Gene);
            startIdx=dna.indexOf(Gene)+Gene.length();
        }
        return sr;
    }


    public void printAllGenes(String dna){
        StorageResource Genes=getAllGenes(dna);
        System.out.println("Genes in "+dna);

        for(String gene:Genes.data()){
            System.out.println(gene);
        }
    }

    /**
     * Write the method cgRatio that has one String parameter dna, and returns the ratio of C’s and G’s in
     * dna as a fraction of the entire strand of DNA.
     * For example if the String were “ATGCCATAG,” then cgRatio would return 4/9 or .4444444.
     * @param dna
     * @return
     */
    public double cgRatio(String dna){
        int count=0,i;

        for(i=0;i<dna.length();i++){
            if(dna.charAt(i)=='C'||dna.charAt(i)=='G'||dna.charAt(i)=='c'||dna.charAt(i)=='g'){
                count++;
            }

        }
        //System.out.println("Count = "+count);

       return (double)count/dna.length();




    }

    /**
     *
     *Write the method cgRatio that has one String parameter dna, and returns the ratio of C’s and G’s in dna as a fraction of the entire strand of DNA.
     * For example if the String were “ATGCCATAG,” then cgRatio would return 4/9 or .4444444.
     *
     * @param dna
     * @return
     */
    public int countCTG(String dna){
        int count=0,i,start=0,idx=0;

       while(true){
           idx=dna.indexOf("CTG",start);
           if(idx==-1){
               break;
           }
           count++;
           start=idx+3;
       }

        return count;
    }

    /**
     * Write the void method processGenes that has one parameter sr, which is a StorageResource of strings. This method processes all the strings in sr to find out information about them. Specifically, it should:
     *
     *     print all the Strings in sr that are longer than 9 characters
     *     print the number of Strings in sr that are longer than 9 characters
     *     print the Strings in sr whose C-G-ratio is higher than 0.35
     *     print the number of strings in sr whose C-G-ratio is higher than 0.35
     *     print the length of the longest gene in sr
     *
     * @param sr
     */
    public void processGenes(StorageResource sr){
        int count60=0,countcg=0,max=0;
        for(String strings:sr.data()){
            if(strings.length()>60){
                System.out.println(strings+" is having greater than 9 characters.");
                count60++;
            }
            if(cgRatio(strings)>0.35){
                System.out.println(strings+" is having C-G Ratio greater than 0.35.");
                countcg++;
            }
            if(strings.length()>max){
                max=strings.length();
            }
        }
        System.out.println("Count of strings having greater than 60 characters:"+count60);
        System.out.println("Count of strings having C-G Ratio greater than 0.35:"+countcg);
        System.out.println("Length of the Longest String:"+max);
    }

    /**
     * Write a method testProcessGenes. This method will call your processGenes method on different test cases.
     * Think of five DNA strings to use as test cases. These should include: one DNA string that has some genes longer than 9 characters,
     * one DNA string that has no genes longer than 9 characters, one DNA string that has some genes whose C-G-ratio is higher than 0.35
     *  and one DNA string that has some genes whose C-G-ratio is lower than 0.35.
     *  Write code in testProcessGenes to call processGenes five times with StorageResources made from each of your five DNA string test cases.

     */

    public void testProcessGenes(){


        FileResource fr=new FileResource("brca1line.fa");
        String dna=fr.asString();
        StorageResource sr=getAllGenes(dna);


        System.out.println("Number of Genes:"+sr.size());
        processGenes(sr);

    }
    public static void main(String args[])
    {
        Part1 obj=new Part1();
        obj.printAllGenes("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        String dna="ATGCCATAG";
        System.out.println("cgRatio in "+dna+" is "+obj.cgRatio(dna));

        dna="ATGCTGGCCTATGCTGCTGAACTG";
        System.out.println("Count of CTG in "+dna+" is "+obj.countCTG(dna));

        obj.testProcessGenes();
    }

}
