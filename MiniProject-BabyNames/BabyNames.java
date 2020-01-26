import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class BabyNames {


    /**
     * Write a method totalBirths  to  print the number of girls names ,
     * the number of boys names and the total names in the file.
     *
     * @param parser
     */
    void totalBirths(){


        FileResource fr=new FileResource();

        CSVParser parser=fr.getCSVParser(false);

        int numOfGirls=0,numOfBoys=0,totalNames=0;
            for(CSVRecord record:parser){

                if(record.get(1).equals("F")){
                    numOfGirls++;
                }
                else
                    numOfBoys++;

                totalNames++;


            }

            System.out.println("Number Of Girl Names is "+numOfGirls);
            System.out.println("Number Of Boy Names is "+numOfBoys);
            System.out.println("Total Number Of Names is "+totalNames);

        }

    /**
     *Write the method named getRank that has three parameters: an integer named year, a string named name, and a string named gender (F for female and M for male).
     * This method returns the rank of the name in the file for the given gender, where rank 1 is the name with the largest number of births.
     * If the name is not in the file, then -1 is returned.
     * For example, in the file "yob2012short.csv", given the name Mason, the year 2012 and the gender ‘M’,
     * the number returned is 2, as Mason is the boys name with the second highest number of births.
     *

     * @param year
     * @param name
     * @param gender
     * @return
     */

        int getRank(int year,String name,String gender){

            System.out.println("Select the File yob"+year+"short.csv");
            FileResource fr=new FileResource();

            CSVParser parser=fr.getCSVParser(false);

            int rank=0;


            for(CSVRecord record:parser){

                if(record.get(1).equals(gender)){
                    rank++;
                }

                if(record.get(0).equals(name)&&record.get(1).equals(gender)){

                    return rank;
                }

            }

            return -1;

        }

    /**
     * Write the method named getName that has three parameters: an integer named year, an integer named rank, and a string named gender (F for female and M for male).
     * This method returns the name of the person in the file at this rank, for the given gender,
     * where rank 1 is the name with the largest number of births.
     * If the rank does not exist in the file, then “NO NAME” is returned.
     *
     * @param year
     * @param rank
     * @param gender
     * @return
     */
        String getName(int year,int rank,String gender){

            System.out.println("Select the File yob"+year+"short.csv");

            FileResource fr=new FileResource();

            CSVParser parser=fr.getCSVParser(false);

            int rankInFile=0;


            for(CSVRecord record:parser){

                if(record.get(1).equals(gender)){
                    rankInFile++;
                }

                if(rankInFile==rank&&record.get(1).equals(gender)){

                    return record.get(0);
                }

            }

            return "No Name";


        }

    /**
     * What would your name be if you were born in a different year? Write the void method named whatIsNameInYear that has four parameters:
     * a string name, an integer named year representing the year that name was born, an integer named newYear and a string named gender
     * This method determines what name would have been named if they were born in a different year,
     * based on the same popularity. That is, you should determine the rank of name in the year they were born,
     * and then print the name born in newYear that is at the same rank and same gender.
     * For example, using the files "yob2012short.csv" and "yob2014short.csv", notice that in 2012 Isabella is the third most popular girl's name. If Isabella was born in 2014 instead,
     * she would have been named Sophia, the third most popular girl's name that year.

     * @param name
     * @param year
     * @param gender
     * @param newYear
     */

        void whatIsNameInYear(String name,int year,String gender,int newYear)
        {


            System.out.println("Select the File yob"+year+"short.csv");

            int rankOfGivenName=0;
            FileResource fr1=new FileResource();

            for(CSVRecord record:fr1.getCSVParser(false)){

                if(record.get(1).equals(gender)) {
                    rankOfGivenName++;
                }


                if(record.get(0).equals(name)&&record.get(1).equals(gender)){
                    break;
                }

            }
            System.out.println("Now,Select the File yob"+newYear+"short.csv");


            int rankInFile=0;
            FileResource fr2=new FileResource();
            CSVRecord requiredRecord=null;

            for(CSVRecord record:fr2.getCSVParser(false)) {
                if (record.get(1).equals(gender)) {

                    rankInFile++;
                    if (rankInFile == rankOfGivenName) {
                        requiredRecord=record;
                        break;
                    }
                }
            }

            System.out.println(name+" born in "+year+" would be "+requiredRecord.get(0)+" if she was born in "+newYear);



        }

    /**
     * Write the method yearOfHighestRank that has two parameters: a string name, and a string named gender.
     * This method selects a range of files to process and returns an integer, the year with the highest rank
     * for the name and gender. If the name and gender are not in any of the selected files, it should return -1.
     * For example, calling yearOfHighestRank with name Mason and gender ‘M’ and selecting the three test files above results in returning the year 2012.
     * That is because Mason was ranked the 2nd most popular name in 2012, ranked 4th in 2013 and ranked 3rd in 2014.
     * His highest ranking was in 2012.
     *
     *
     * @param name
     * @param gender
     * @return
     */


    int yearOfHighestRank(String name,String gender) {


            int currentRank = 0, highestRank =Integer.MAX_VALUE ;
            String fileNameHavingHighestRank = "";
            DirectoryResource dr = new DirectoryResource();

            for (File f : dr.selectedFiles()) {

                FileResource fr = new FileResource(f);
                CSVParser parser = fr.getCSVParser(false);

                currentRank = getRankInFile(parser, name, gender);



                if (currentRank < highestRank) {
                    highestRank = currentRank;
                    fileNameHavingHighestRank = f.getName();
                }

            }

            String yearHavingHighestRankString=extractYearFromFileName(fileNameHavingHighestRank);

            return Integer.parseInt(yearHavingHighestRankString);


        }

    /**
     *
     * To find the rank of given name and gender using CSVParser
     * @param parser
     * @param name
     * @param gender
     * @return
     */
        int getRankInFile(CSVParser parser,String name,String gender){



            int rank=0;


            for(CSVRecord record:parser){

                if(record.get(1).equals(gender)){
                    rank++;
                }

                if(record.get(0).equals(name)&&record.get(1).equals(gender)){

                    return rank;
                }

            }

            return -1;



        }

    /**
     *
     * Extracting year(2012) from the filename(yob2012.csv) using ascii values
     * @param fileName
     * @return
     */
    String extractYearFromFileName(String fileName){

            int i;
            String year = "";
            for (i = 0; i < fileName.length(); i++) {

                int ascii_value = fileName.charAt(i);


                if (ascii_value >= 48 && ascii_value <= 57) {


                    year = year + fileName.charAt(i);

                }


            }

            return year;




        }

    /**
     * Write the method getAverageRank that has two parameters: a string name, and a string named gender (F for female and M for male).
     * This method selects a range of files to process and returns a double representing the average rank of the name and gende over the selected files.
     * It should return -1.0 if the name is not ranked in any of the selected files.
     * @param name
     * @param gender
     * @return
     */



    double getAverageRank(String name,String gender){

            int currentRank=0,sumOfRanks=0,countOfFiles=0;

            DirectoryResource dr = new DirectoryResource();

            for (File f : dr.selectedFiles()) {

                FileResource fr = new FileResource(f);
                CSVParser parser = fr.getCSVParser(false);


               currentRank=getRankInFile(parser,name,gender);
               if(currentRank==-1){
                   return -1.0;
               }
                sumOfRanks =sumOfRanks+currentRank;
                countOfFiles++;

                }

            return (double)sumOfRanks/(countOfFiles);

        }

    /**
     * Write the method getTotalBirthsRankedHigher that has three parameters: an integer named year, a string named name, and a string named gender (
     *  This method returns an integer, the total number of births of those names with the same gender and same year who are ranked higher than name.
     *  For example, if getTotalBirthsRankedHigher accesses the "yob2012short.csv" file with name set to “Ethan”, gender set to “M”, and year set to 2012,
     *  then this method should return 15, since Jacob has 8 births and Mason has 7 births, and those are the only two ranked higher than Ethan.
     *
     * @param year
     * @param name
     * @param gender
     * @return
     */

        int getTotalBirthsRankedHigher(int year,String name,String gender){

            System.out.println("Select the File yob"+year+"short.csv");
            FileResource fr=new FileResource();
            CSVParser parser=fr.getCSVParser(false);

            int rankOfGivenName = getRankInFile(parser,name,gender);

            parser=fr.getCSVParser(false);

            int sumOfBirths=0,currentRank=0;

            for(CSVRecord record:parser){
                if(record.get(1).equals(gender)){
                    currentRank++;
                    if(currentRank<rankOfGivenName){
                        sumOfBirths=sumOfBirths+Integer.parseInt(record.get(2));
                    }

                }

            }
            return sumOfBirths;



        }






        public static void main(String args[]) {

            BabyNames obj = new BabyNames();
            obj.totalBirths();


            int rank=obj.getRank(2012,"Mason","M");
            if(rank!=-1)
                System.out.println("Rank of Mason with the Gender M in the Year 2012 is "+rank);
            else
                System.out.println("No Mason with the Gender M in the Year 2012");



            String name = obj.getName(2012, 1, "M");
            if (!name.equals("No Name"))
                System.out.println("Name of baby with the Gender M in the Year 2012 having the rank 1 is " + name);
            else
                System.out.println(name);


            obj.whatIsNameInYear("Owen",1974,"F",2014);

            int year = obj.yearOfHighestRank("Mich","M");

            System.out.println("Year having Highest Rank of Mason of Gender M "+year);

           double avgRank=obj.getAverageRank("Robert","M");
            System.out.printf("Average Rank of jacob is %.2f",avgRank);


           int sumOfBirhtsRankedHigher=obj.getTotalBirthsRankedHigher(2012,"Ethan","M");
           System.out.println("Sum Of Births Ranked Higher than Ethan:"+sumOfBirhtsRankedHigher);

        }




}
