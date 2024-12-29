<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Solved Sudoku</title>
    <link rel="stylesheet" href="style.css"> <!-- Optional CSS -->
</head>
<body>
    <div class="container">
        <h1>Solved Sudoku</h1>
        <table id="sudoku-grid">
            <%
                int[][] solvedBoard = (int[][]) request.getAttribute("solvedBoard");
                for (int i = 0; i < 9; i++) {
                    out.println("<tr>");
                    for (int j = 0; j < 9; j++) {
                        out.println("<td>" + solvedBoard[i][j] + "</td>");
                    }
                    out.println("</tr>");
                }
            %>
        </table>
        <a href="index.html">Solve another puzzle</a>
    </div>
</body>
</html>