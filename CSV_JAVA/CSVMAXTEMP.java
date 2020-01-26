import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
/**
  *Questions are commented above each method
*/

public class CSVMAXTEMP {

    /*
        * Write a method named coldestHourInFile that has one parameter, a CSVParser named parser.
        * This method returns the CSVRecord with the coldest temperature in the file and
        * thus all the information about the coldest temperature, such as the hour of the coldest temperature.
    */
    public CSVRecord coldestHourInFile(CSVParser parser){

        CSVRecord coldestSoFar=null;

        for(CSVRecord currentRecord:parser){
            coldestSoFar=getColdestOfTwo(currentRecord,coldestSoFar);
        }
        return coldestSoFar;

    }

    public CSVRecord getColdestOfTwo(CSVRecord currentRecord,CSVRecord coldestSoFar){

       // String currentTempString=currentRecord.get("TemperatureF");




           if (coldestSoFar == null) {
               coldestSoFar = currentRecord;
           } else {


               double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
               double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));

               if (currentTemp < coldestTemp && currentTemp!=-9999) {
                   coldestSoFar = currentRecord;
               }

           }

        return coldestSoFar;
    }

    /*
        * You should also write a void method named testColdestHourInFile() to test this method and
        * print out information about that coldest temperature, such as the time of its occurrence.
    */
    void testColdestHourFile(){

        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();

        CSVRecord coldest=coldestHourInFile(parser);

        System.out.println("Coldest Temperature is "+coldest.get("TemperatureF")+" at "+coldest.get("DateUTC"));
    }
    /*
        * Write the method fileWithColdestTemperature that has no parameters.
        * This method should return a string that is the name of the file from
        * selected files that has the coldest temperature.
    */
    String fileWithColdestTemperatue(){
        DirectoryResource dr=new DirectoryResource();
        String fileName="";
        CSVRecord coldestSoFar=null;
        for(File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVParser parser=fr.getCSVParser();

            CSVRecord currentRecord=coldestHourInFile(parser);



            if(coldestSoFar==null){
                coldestSoFar=currentRecord;
                fileName=f.getName();
            }
            else{
                double currentTemp=Double.parseDouble(currentRecord.get("TemperatureF"));
                double coldestTemp=Double.parseDouble(coldestSoFar.get("TemperatureF"));

                if(currentTemp<coldestTemp){
                    coldestSoFar=currentRecord;
                    fileName=f.getName();
                }

            }


        }

        return fileName;


    }
    /*
        * You should also write a void method named testFileWithColdestTemperature() to test this method.
        * Note that after determining the filename, you could call the method coldestHourInFile
        * to determine the coldest temperature on that day.
    */
    void testFileWithColdestTemperature(){

        String coldestFile=fileWithColdestTemperatue();
        System.out.println("Coldest day was in the file "+coldestFile);

        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();

        CSVRecord coldestRecord=coldestHourInFile(parser);

        System.out.println("Coldest Temperature on that day was "+coldestRecord.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest were ");

        parser=fr.getCSVParser();
        for(CSVRecord record:parser){
            System.out.println(record.get("DateUTC")+" "+record.get("TemperatureF"));
        }

    }


    /*

     * Write a method named lowestHumidityInFile that has one parameter, a CSVParser named parser.
     * This method returns the CSVRecord that has the lowest humidity.
     * If there is a tie, then return the first such record that was found.

     */

    CSVRecord lowestHumidityInFile(CSVParser parser){


        CSVRecord lowestSoFar=null;

        for(CSVRecord currentRecord:parser){
            lowestSoFar=getLowestOfTwo(currentRecord,lowestSoFar);
        }
        return lowestSoFar;
    }
    /*
        * To find the lowest humidity in two records
        * Note that sometimes there is not a number in the Humidity column
        * but instead there is the string “N/A”.
    */
    public CSVRecord getLowestOfTwo(CSVRecord currentRecord,CSVRecord lowestSoFar)
    {
        if(lowestSoFar==null)
        {
            lowestSoFar=currentRecord;
        }
        else
            {


                String currentHumiditytring=currentRecord.get("Humidity");
                String lowestHumidityString=lowestSoFar.get("Humidity");

                if(!(lowestHumidityString.equals("N/A") || currentHumiditytring.equals("N/A")))
                {

                    double currentHumidity = Double.parseDouble(currentRecord.get("Humidity"));
                    double lowestHumidity = Double.parseDouble(lowestSoFar.get("Humidity"));

                    if (currentHumidity < lowestHumidity) {
                        lowestSoFar = currentRecord;
                    }
                }

            }
        return lowestSoFar;
    }
    /*
        * You should also write a void method named testLowestHumidityInFile() to test this method
        * and then prints the lowest humidity AND the time the lowest humidity occurred.
    */
    void testLowestHumidityFile()
    {

                FileResource fr = new FileResource();
                CSVParser parser = fr.getCSVParser();
                CSVRecord csv = lowestHumidityInFile(parser);

                System.out.println("Lowest Huimdity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));

    }

    /*
        * Write the method lowestHumidityInManyFiles that has no parameters.
        * This method returns a CSVRecord that has the lowest humidity over all the files.
        * If there is a tie, then return the first such record that was found.
    */

    CSVRecord lowestHumidityInManyFiles()
    {
        DirectoryResource dr=new DirectoryResource();

        CSVRecord lowestSoFar=null;
        for(File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVParser parser=fr.getCSVParser();

            CSVRecord currentRecord=lowestHumidityInFile(parser);

            lowestSoFar=getLowestOfTwo(currentRecord,lowestSoFar);


                }
        return lowestSoFar;

    }

    /*
        *  You should also write a void method named testLowestHumidityInManyFiles() to test this method
        * and to print the lowest humidity AND the time the lowest humidity occurred.
        * Be sure to test this method on two files so you can check if it is working correctly.
    */

    void testLowestHumidityInManyFiles(){


        CSVRecord csv=lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }


    /*
        * Write the method averageTemperatureInFile that has one parameter, a CSVParser named parser.
        * This method returns a double that represents the average temperature in the file.
    */
    double averageTemperatureInFile(CSVParser parser){

        int numberOfRecords=0;
        double currentTemp=0.0;
        double sum=0.0;

        for(CSVRecord record:parser){

            currentTemp=Double.parseDouble(record.get("TemperatureF"));
            sum=sum+currentTemp;
            numberOfRecords++;
        }

        return sum/numberOfRecords;


    }

    /*
        * You should also write a void method named testAverageTemperatureInFile() to test this method.
     */
    void testAverageTemperatureInFile(){
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();

        double avgTemp=averageTemperatureInFile(parser);

        System.out.println("Average Temperature in File is "+avgTemp);



    }
    /*

    * Write the method averageTemperatureWithHighHumidityInFile that has two parameters, a CSVParser named parser
    * and an integer named value. This method returns a double that represents the average temperature of only those
    * temperatures when the humidity was greater than or equal to value.

     */


    double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){

        int numberOfRecords=0;
        double currentTemp=0.0;
        double sum=0.0;


        for(CSVRecord record:parser){

            int currHumidity=Integer.parseInt(record.get("Humidity"));
            if(currHumidity>=value) {
                currentTemp = Double.parseDouble(record.get("TemperatureF"));
                sum = sum + currentTemp;
                numberOfRecords++;
            }
        }

        if(sum==0.0){
            return 0.0;
        }
        return sum/numberOfRecords;

    }

    /*
        * You should also write a void method named
        * testAverageTemperatureWithHighHumidityInFile() to test this method.
     */

    void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();

        double avgTemp=averageTemperatureWithHighHumidityInFile(parser,80);

        if(avgTemp==0.0){
            System.out.println("No Temperatures with that humidity");

        }
        else
            System.out.println("Average Temperature with High High Humidity in File is "+avgTemp);



    }

    public static  void main(String args[])
    {

        CSVMAXTEMP obj=new CSVMAXTEMP();
        //obj.testColdestHourFile();
        //obj.testFileWithColdestTemperature();
        //obj.testLowestHumidityFile();
       //obj.testLowestHumidityInManyFiles();
        //obj.testAverageTemperatureInFile();
       obj.testAverageTemperatureWithHighHumidityInFile();


    }



}
