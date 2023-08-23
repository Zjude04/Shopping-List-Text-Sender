package weeklyshoppinglistprinter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author zjude
 */
public class WeeklyShoppingListPrinter {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        String excelSheetPath = "C:/Users/zjude/OneDrive/ShoppingList.csv";
        String line;
        String[] values;
        String[][] dataTable;
        String TableLine = "--------------------------------------";

        try {
            FileInputStream file = new FileInputStream(excelSheetPath);
            Scanner scan = new Scanner(file);
            System.out.println("This is you shopping list for the week");
            int numRows = 0;
            int numCols = 0;

            //This is for running until all the data is scanned from the csv file and printed to the console
            while (scan.hasNextLine()) {
                numRows++;
                line = scan.nextLine(); 
                values = line.split(","); //values is seperating all the data from csv into individual strings 
                numCols = Math.max(numCols, values.length);

                //For loop runs through the data from file and prints out data 
                for (int i = 0; i < values.length - 1; i++) {
                    System.out.println(TableLine);
                    System.out.println(values[i]);
                }

            }
            scan.close();
            dataTable = new String[numRows][numCols];
            System.out.println(TableLine);

//            String[] dataHold = {"", ""};
//            String numbersHolder = "";
//
//            JFrame frame = new JFrame("Array Data Display");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(300, 200);
//
//            for (int i = 0; i < dataHold.length; i++) {
//                numbersHolder += dataHold[i] + " ";
//            }
//
//            JTextArea textArea = new JTextArea(numbersHolder);
//            textArea.setEditable(false);
//
//            frame.add(textArea);
//            frame.setVisible(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
