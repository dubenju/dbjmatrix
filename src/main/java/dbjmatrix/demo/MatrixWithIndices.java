package dbjmatrix.demo;
import org.ejml.data.DMatrix;
import org.ejml.simple.SimpleMatrix;

import java.util.Random;

public class MatrixWithIndices {
    public static void main(String[] args) {
        SimpleMatrix simpleMatrix = SimpleMatrix.random_DDRM(5, 5, 1, 9, new Random());

        printMatrixWithIndices(simpleMatrix.getDDRM(), 5, 5);
    }

    public static void printMatrixWithIndices(DMatrix matrix, int numChar, int precision) {
        String format = "%" + numChar + "." + precision + "f "; // default format

        StringBuilder columnIndexes = new StringBuilder();
        columnIndexes.append("    "); //skips first 4 chars

        // Append column indices
        for (int i = 0; i < matrix.getNumCols(); i++) {
            columnIndexes.append(i + 1);

            // Print spaces till next column
            for (int j = 0; j < String.format(format, matrix.get(0, i)).length() - 1; j++) {
                columnIndexes.append(" ");
            }
        }

        // Print column indices
        System.out.println(columnIndexes.toString());

        // Print horizontal dotted line
        System.out.println("  " + columnIndexes.toString().replaceAll(".", "-").substring(3));

        // Print rows
        for (int y = 0; y < matrix.getNumRows(); ++y) {

            // Prints row's index with 'border' (vertical line)
            System.out.print((y + 1) + " | ");

            // Print matrix values
            for (int x = 0; x < matrix.getNumCols(); ++x) {
                System.out.printf(format, matrix.get(y, x));
            }

            // Breaks line
            System.out.println();
        }
    }
}
