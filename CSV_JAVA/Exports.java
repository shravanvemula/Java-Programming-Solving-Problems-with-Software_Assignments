import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

//questions are commented above each method
public class Exports {


    //    1. Write a method named tester that will create your CSVParser and
    //    call each of the methods below in parts 2, 3, 4, and 5.
    void tester()
    {
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();

        listExportsTwoProducts(parser,"gold","diamonds");

        parser=fr.getCSVParser();
        String info=countryInfo(parser,"Nauru");
        System.out.println("Country's Info "+info);
        info=countryInfo(parser,"India");
        System.out.println("Country's info "+info);

        parser=fr.getCSVParser();
        int countOfExporters=numberOfExporters(parser,"gold");
        System.out.println("Number of Countries exporting gold:"+countOfExporters);

        parser=fr.getCSVParser();

        bigExporters(parser,"$999,999,999,999");

    }
    //2.Write a method named countryInfo that has two parameters, parser is a CSVParser and country is a String.
    // This method returns a string of information about the country or returns “NOT FOUND” if there is no information.
    // The format of the string returned is the country, followed by “: “, followed by a list of the countries’ exports,
    // followed by “: “, followed by the countries export value.
    String countryInfo(CSVParser parser,String country){

        for(CSVRecord record:parser){

            if(record.get("Country").equals(country)){
                 return record.get("Country")+": " +record.get("Exports")+": "+record.get("Value (dollars)");
            }

        }
        return "NOT FOUND";

    }

    //3.Write a void method named listExportersTwoProducts that has three parameters, parser is a CSVParser,
    // exportItem1 is a String and exportItem2 is a String. This method prints the names of all the countries
    // that have both exportItem1 and exportItem2 as export items.
    void listExportsTwoProducts(CSVParser parser,String expoertItem1,String exportItem2)
    {

        System.out.println("Countries containing the exports "+expoertItem1+","+exportItem2+" are");
        for(CSVRecord record:parser)
        {

            String exports=record.get("Exports");

            if(exports.contains(expoertItem1)&&exports.contains(exportItem2))
            {
                String country=record.get("Country");
                System.out.println(country);
            }

        }
    }

    //Write a method named numberOfExporters, which has two parameters, parser is a CSVParser, and exportItem is a String.
    // This method returns the number of countries that export exportItem.

    int numberOfExporters(CSVParser parser,String exportItem){
        int count=0;
        for(CSVRecord record:parser){
            String export=record.get("Exports");
            if(export.indexOf(exportItem)!=-1){
                count++;
            }
        }
        return count;
    }

    //5.Write a void method named bigExporters that has two parameters, parser is a CSVParser,
    // and amount is a String in the format of a dollar sign, followed by an integer number
    // with a comma separator every three digits from the right.
    // An example of such a string might be “$400,000,000”. This method prints the names of countries
    // and their Value amount for all countries whose Value (dollars) string is longer than the amount string.
    // You do not need to parse either string value as an integer, just compare the lengths of the strings.

    void bigExporters(CSVParser parser,String amount){

        System.out.println("Big Exporters are");
        for(CSVRecord record:parser){

            String value=record.get("Value (dollars)");
            if(amount.length()<value.length()){
                System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
            }
        }
    }

    public static void main(String args[]){
        Exports obj=new Exports();
        obj.tester();
    }
}
