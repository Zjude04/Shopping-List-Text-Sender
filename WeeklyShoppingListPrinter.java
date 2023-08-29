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
        String[] header = {"Item, Unit, Amount, Cost"};
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

            }
            scan.close();
            System.out.println(TableLine);
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

            //2 for loops to iterate through col and row and print the values stored in the data structure 
            for (String[] rowData : dataTable) {
                for (String cell : rowData) {
                    System.out.println(cell);
                }
                System.out.println();

            }
            printTable(dataTable, header, "Zane's Weekly Shopping List");
            scan.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void printTable(String[][] data, String[] header, String title) {
        String rowlines = "+";
        int colMax[] = new int[data[0].length];
        for (int j = 0; j < colMax.length; j++) {
            for (int i = 0; i < data.length; i++) {
                if (colMax[j] < data[i][j].length()) {
                    colMax[j] = data[i][j].length();
                }
            }
            if (header[j].length() > colMax[j]) {
                colMax[j] = header[j].length();
            }

        }

        //Builds the row lines based on the larges first column word lenght
        for (int j = 0; j < colMax.length; j++) {
            for (int i = 0; i < colMax[j] + 4; i++) {
                rowlines += "-";
            }
            rowlines += "+";
        }

        String[] formatter = new String[colMax.length];
        for (int col = 0; col < colMax.length; col++) {
            formatter[col] = "|  %" + colMax[col] + "s  ";
        }

        System.out.println(rowlines);
        int titleLength = 0;
        for (int length : colMax) {
            titleLength += length + 5;
        }
        titleLength -= 5;
        String titleFormat = "|  %" + titleLength + "s  |\n";
        System.out.printf(titleFormat, title);
        System.out.println(rowlines);

        for (int col = 0; col < header.length; col++) {
            System.out.printf(formatter[col], header[col]);
        }
        System.out.println("|");
        System.out.println(rowlines);

        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                System.out.printf(formatter[col], data[row][col]);

            }
            System.out.print("|\n");
            System.out.println(rowlines);

        }

    }

}
