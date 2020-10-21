package test;

import model.SudokuSolver;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class testSudoku {

    private SudokuSolver sudokuSolver;

    @Before
    public void setUp() {
        sudokuSolver = new SudokuSolver();
    }

    @Test
    public void testEmptySudoku() throws Exception {
        sudokuSolver.solve();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                assertTrue("Same number should not exist at same row or column!",
                        sudokuSolver.nrExistInRowColumn(r, c, sudokuSolver.getNr(r, c)));
                assertTrue("Same number should not exist at 3x3 region!",
                        sudokuSolver.nrExistInRegion(r, c, sudokuSolver.getNr(r, c)));
            }
        }
    }

    @Test
    public void testFigure11() throws Exception {
        sudokuSolver.setNumber(0, 2, 8);
        sudokuSolver.setNumber(0, 5, 9);
        sudokuSolver.setNumber(0, 7, 6);
        sudokuSolver.setNumber(0, 8, 2);
        sudokuSolver.setNumber(1, 8, 5);
        sudokuSolver.setNumber(2, 0, 1);
        sudokuSolver.setNumber(2, 2, 2);
        sudokuSolver.setNumber(2, 3, 5);
        sudokuSolver.setNumber(3, 3, 2);
        sudokuSolver.setNumber(3, 4, 1);
        sudokuSolver.setNumber(3, 7, 9);
        sudokuSolver.setNumber(4, 1, 5);
        sudokuSolver.setNumber(4, 6, 6);
        sudokuSolver.setNumber(5, 0, 6);
        sudokuSolver.setNumber(5, 7, 2);
        sudokuSolver.setNumber(5, 8, 8);
        sudokuSolver.setNumber(6, 0, 4);
        sudokuSolver.setNumber(6, 1, 1);
        sudokuSolver.setNumber(6, 3, 6);
        sudokuSolver.setNumber(6, 5, 8);
        sudokuSolver.setNumber(7, 0, 8);
        sudokuSolver.setNumber(7, 1, 6);
        sudokuSolver.setNumber(7, 4, 3);
        sudokuSolver.setNumber(7, 6, 1);
        sudokuSolver.setNumber(8, 6, 4);
        assertTrue("Should return True!",sudokuSolver.solve());
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                assertTrue("Same number should not exist at same row or column!",
                        sudokuSolver.nrExistInRowColumn(r, c, sudokuSolver.getNr(r, c)));
                assertTrue("Same number should not exist at 3x3 region!",
                        sudokuSolver.nrExistInRegion(r, c, sudokuSolver.getNr(r, c)));
            }
        }
//        System.out.println(sudokuSolver);
    }

    @Test
    public void unsolvableSudoku() throws Exception {
        sudokuSolver.setNumber(0, 0, 3);
        sudokuSolver.setNumber(0, 1, 7);
        sudokuSolver.setNumber(0, 2, 8);
        sudokuSolver.setNumber(0, 5, 9);
        sudokuSolver.setNumber(0, 7, 6);
        sudokuSolver.setNumber(0, 8, 2);
        sudokuSolver.setNumber(1, 8, 5);
        sudokuSolver.setNumber(2, 0, 1);
        sudokuSolver.setNumber(2, 2, 2);
        sudokuSolver.setNumber(2, 3, 5);
        sudokuSolver.setNumber(3, 3, 2);
        sudokuSolver.setNumber(3, 4, 1);
        sudokuSolver.setNumber(3, 7, 9);
        sudokuSolver.setNumber(4, 1, 5);
        sudokuSolver.setNumber(4, 6, 6);
        sudokuSolver.setNumber(5, 0, 6);
        sudokuSolver.setNumber(5, 7, 2);
        sudokuSolver.setNumber(5, 8, 8);
        sudokuSolver.setNumber(6, 0, 4);
        sudokuSolver.setNumber(6, 1, 1);
        sudokuSolver.setNumber(6, 3, 6);
        sudokuSolver.setNumber(6, 5, 8);
        sudokuSolver.setNumber(7, 0, 8);
        sudokuSolver.setNumber(7, 1, 6);
        sudokuSolver.setNumber(7, 4, 3);
        sudokuSolver.setNumber(7, 6, 1);
        sudokuSolver.setNumber(8, 6, 4);
        assertFalse("Should return False!", sudokuSolver.solve());

        assertEquals("Empty boxes should be 0!", sudokuSolver.getNr(0, 3), 0);
        assertEquals("Empty boxes should be 0!", sudokuSolver.getNr(1, 0), 0);
    }

    @Test
    public void testNrExistAtRowCol() {
        sudokuSolver.setNumber(0, 0, 1);
        sudokuSolver.setNumber(0, 1, 2);
        sudokuSolver.setNumber(0, 2, 3);
        sudokuSolver.setNumber(0, 3, 4);
        assertTrue("Same number should not exist at same row!",
                sudokuSolver.nrExistInRowColumn(0, 4, 4));
        assertFalse("Same number should not exist at same row!",
                sudokuSolver.nrExistInRowColumn(0, 4, 5));

        assertTrue("Same number should not exist at same column!",
                sudokuSolver.nrExistInRowColumn(5, 0, 1));
        assertFalse("Same number should not exist at same column!",
                sudokuSolver.nrExistInRowColumn(5, 0, 5));
    }

    @Test
    public void testNrExistAtRegion() {
        sudokuSolver.setNumber(0, 0, 1);
        sudokuSolver.setNumber(0, 1, 2);
        sudokuSolver.setNumber(0, 2, 3);
        sudokuSolver.setNumber(1, 0, 4);
        assertTrue("Same number should not exist at same 3x3 Region!",
                sudokuSolver.nrExistInRegion(2, 2, 4));
        assertFalse("Same number should not exist at same 3x3 Region!",
                sudokuSolver.nrExistInRegion(2, 2, 5));
    }
}
