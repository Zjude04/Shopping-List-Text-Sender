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
                String line = scan.nextLine();
                values = line.split(","); //values is seperating all the data from csv into individual strings 
                numCols = Math.max(numCols, values.length);

                //For loop runs through the data from file and prints out data 
//                for (int i = 0; i < values.length - 1; i++) {
//                    System.out.println(TableLine);
//                    System.out.println(values[i] + values[i + 1]);
//                }
            }
            scan.close();
            System.out.println(TableLine);
            dataTable = new String[numRows][numCols]; //2D array that is the size of the data set
            scan = new Scanner(new File(excelSheetPath));

            int row = 0;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] columns = line.split(",");
                
                for (int col = 0; col < columns.length; col++) {
                    dataTable[row][col] = columns[col];
                }
                row++;
            }

            for (String[] rowData : dataTable) {
                for (String cell : rowData) {
                    System.out.println(cell);
                }

            }
            scan.close();

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
