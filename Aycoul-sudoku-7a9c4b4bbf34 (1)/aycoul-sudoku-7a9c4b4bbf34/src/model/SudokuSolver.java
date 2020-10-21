package model;

import java.util.Arrays;

/**
 * Sudoku is a puzzle consisting of 9*9 boxes. Some boxes are initially filled in with numbers between 1 and 9.
 * This class finds a solution that meets the Sudoku rules.
 */
public class SudokuSolver {

    //9x9 matrix.
    int[][] matrix;

    /**
     * Setup a new 9*9 matrix with numbers that user selected.
     */
    public SudokuSolver (int[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * Initialize a new 9*9 empty matrix
     */
    public SudokuSolver () {
       this(new int[9][9]);
    }

    /**
     * Initialize boxes with numbers between 1 to 9.
     * @param x row
     * @param y column
     * @param nr number
     * @return True if it can place the number according to the Sudoku rules, otherwise return false.
     */
    public boolean setNumber(int x, int y, int nr) {
        if (x < 0 || y < 0 || x > 8 || y > 8 || nr < 0 || nr > 9) { // If x, y or nr are out of the range.
            return false;
        }
        if (nrExistInRowColumn(x, y, nr) || nrExistInRegion(x, y, nr)) {
            return false;
        }
        matrix[x][y] = nr;
        return true;
    }

    /**
     * Change an existing number at position x,y to 0.
     * @param x row
     * @param y column
     * @throws ArrayIndexOutOfBoundsException if x and/or y are out of range.
     */
    public void removeNumber(int x, int y) {
        if (x < 0 || y < 0 || x > 8 || y > 8 ) { // If x, y or nr are out of the range.
            throw new ArrayIndexOutOfBoundsException("Row or Column out of range!!");
        }
        matrix[x][y] = 0;
    }

    /**
     * Return the number at the position of x and y.
     * @param x row
     * @param y column
     * @return Return the number at the position of x and y.
     * @throws ArrayIndexOutOfBoundsException if x and/or y are out of range.
     */
    public int getNr(int x, int y) throws Exception {
        if (x < 0 || y < 0 || x > 8 || y > 8) { // If x, y or nr are out of the range.
            throw new ArrayIndexOutOfBoundsException("Row or Column out of range!!");
        }
        return matrix[x][y];
    }

    /**
     * Wrapper method which call another method to solve the Sudoku according to the rules.
     * @return True if it can solve the Sudoku otherwise it return False.
     */
    public boolean solve() {
        return solve(0,0);
    }

    /**
     * Solve the Sudoku with recursion.
     * @param x row
     * @param y column
     * @return True if it can solve the Sudoku otherwise it return False.
     */
    private boolean solve(int x, int y) {
        if (y == 9) { // Go to next line.
            y = 0;
            x++;
        }

        if (x == 9 && y == 0) { // Base condition to exit the recursion.
            return true;
        }

        if (x < 0 || y < 0 || x > 8 || y > 8) { // If x and y are out of the range.
            return false;
        }

        if (matrix[x][y] != 0) { // If a number exist at this box, moves to the next.
            if (solve(x, (y + 1)))
                return true;
        } else {
            for (int i = 1; i <= 9; i++) {
                if (!nrExistInRowColumn(x, y, i) &&
                        !nrExistInRegion(x, y, i)) {
                    matrix[x][y] = i;
                    if (solve(x, y + 1)) {
                        return true;
                    } else {
                        matrix[x][y] = 0;
                    }
                }
            }
        }
        return false;

// Test
//        int nr = 1;
//        while(nr <= 9) {
//            if (!nrExistInRowColumn(x, y, nr) && !nrExistInRegion(x, y, nr)){
//                break;
//            }
//            nr++;
//        }
//        if (nr >9) { // If it does not find the number for x,y position.
//            System.out.println("Nr bigger than 9:" + nr);
//            matrix[x][y] = 0;
//            solve(x, --y);
//            return false;
//        }
//
//        if (x == 8 && y == 8) {
//            matrix[x][y] = nr;
//            return true;
//        } else {
//            matrix[x][y] = nr;
//            if (solve(x , y + 1)){
//                return true;
//            }else {
//                matrix[x][y]  = 0;
//                return false;
//            }
//        }

    }

    /**
     * Check if the row or the column contains the nr.
     * @param x row
     * @param y column
     * @param nr number
     * @return True if same number exist at the same row/column.
     */
    public boolean nrExistInRowColumn(int x, int y, int nr) {
        if (x < 0 || y < 0 || x > 8 || y > 8) {
            return false;
        }
        for (int i = 0; i < 9; i++) {
            if (matrix[x][i] == nr) {
                return true;
            } else if (matrix[i][y] == nr) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the region contains the nr.
     * @param x row
     * @param y column
     * @param nr number
     * @return True if same number exist at the same 3*3 box.
     */
    public boolean nrExistInRegion(int x, int y, int nr) {
        if (x < 0 || y < 0 || x > 8 || y > 8) {
            return false;
        }
        int startX = x - (x%3);
        int startY = y - (y%3);
        for (int i = startY; i <= startY + 2; i++) {
            for (int j = startX; j <= startX + 2; j++) {
                if (matrix[j][i] == nr) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Convert the Sudoku matrix to a String.
     * @return 9x9 String matrix.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < matrix.length ; i++) {
            str.append(Arrays.toString(matrix[i]));
            str.append("\n");
        }
        return str.toString();
    }

}

//Test1
//        if (y == 9) { // Go to next line.
//                y = 0;
//                x++;
//                } else if (y == -1) { // Go to previous line
//                y = 8;
//                x--;
//                }
//                if (x < 0 || y < 0 || x > 8 || y > 8) {
//        return false;
//        }
//        else if (! (matrix[x][y] == 0)) {
//        return false;
//        }
//
//        int nr = 1;
//        while(nr <= 9) {
//        if (!nrExistInRowColumn(x, y, nr) && !nrExistInRegion(x, y, nr)){
//        break;
//        }
//        nr++;
//        }
//        if (nr >9) { // If it does not find the number for x,y position.
//        System.out.println("Nr bigger than 9:" + nr);
//        return false;
//        }
//
//        if (x == 8 && y == 8) {
//        matrix[x][y] = nr;
//        return true;
//        } else {
//        matrix[x][y] = nr;
//        if (solve(x, y - 1) || solve(x , y + 1)){
//        return true;
//        }else {
//        return false;
//        }
//        }

