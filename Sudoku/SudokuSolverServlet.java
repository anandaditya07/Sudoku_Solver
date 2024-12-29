import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/solve")
public class SudokuSolverServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int[][] board = new int[9][9];

        // Retrieve input values from the form
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String cellValue = request.getParameter("cell-" + i + "-" + j);
                board[i][j] = (cellValue != null && !cellValue.isEmpty()) ? Integer.parseInt(cellValue) : 0;
            }
        }

        // Solve the Sudoku puzzle
        if (solveSudoku(board)) {
            // If solved, redirect back to the form with the solved board
            request.setAttribute("solvedBoard", board);
            request.getRequestDispatcher("result.jsp").forward(request, response);
        } else {
            // If not solvable, set an error message
            request.setAttribute("error", "This Sudoku puzzle cannot be solved.");
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }

    // Sudoku solving algorithm
    private boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) { // Find an empty cell
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num; // Place the number
                            if (solveSudoku(board)) { // Recursively solve
                                return true;
                            }
                            board[row][col] = 0; // Backtrack
                        }
                    }
                    return false; // No valid number found
                }
            }
        }
        return true; // Solved
    }

    private boolean isSafe(int[][] board, int row, int col, int num) {
        // Check row and column
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == num || board[x][col] == num) {
                return false;
            }
        }
        // Check 3x3 box
        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true; // Safe to place the number
    }
} 