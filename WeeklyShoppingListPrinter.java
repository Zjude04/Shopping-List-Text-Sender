package org.example;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author zjude
 */
public class ShoppingListSender {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */

    public static final String Account_SID = "null";
    public static final String Auth_TOKEN = "null";

    public static void main(String[] args) throws Exception {

        Twilio.init(Account_SID, Auth_TOKEN);
        String myPhoneNumber = "+null";
        String TwilioPhoneNumber = "null";

        String messageBody = "This is a test message from Twilio";



        String excelSheetPath = "C:\\Users\\zjude\\OneDrive\\Desktop\\ShoppingList.csv";
        String line;
        String[] values;
        String[][] dataTable;
        String[] header = {"Item", "Unit", "Amount", "Cost"};
        String TableLine = "--------------------------------------";

        try {
            FileInputStream file = new FileInputStream(excelSheetPath);
            Scanner scan = new Scanner(file);
            int numRows = 0;
            int numCols = 0;

            //This is for running until all the data is scanned from the csv file and printed to the console
            while (scan.hasNextLine()) {
                numRows++;
                line = scan.nextLine();
                values = line.split(","); //values is seperating all the data from csv into individual strings
                numCols = Math.max(numCols, values.length);


            }
            scan.close();
            dataTable = new String[numRows][numCols];

            scan = new Scanner(new File(excelSheetPath));

            int row = 0;
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                String[] columns = line.split(",");



                for (int col = 0; col < columns.length; col++) {
                    dataTable[row][col] = columns[col];
                }
                row++;

            }

            String TheTable = "---------------------------------------------------";


            for(int CurrentRow = 0; CurrentRow < numRows; CurrentRow++){
                TheTable += "\n";
                for(int CurrentCol = 0; CurrentCol < numCols; CurrentCol++) {
                    TheTable += dataTable[CurrentRow][CurrentCol] + "   |   ";
                }
            }


            TheTable += "\n" + "---------------------------------------------------";
            Message message = Message.creator(new PhoneNumber(myPhoneNumber), new PhoneNumber(TwilioPhoneNumber), TheTable).create();

            scan.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}

