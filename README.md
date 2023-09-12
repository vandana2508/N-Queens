# N-Queens
Solving NQueens Problem
Problem Definition:
N-Queens Problem:
The problem is based on how a queen can attack in chess. Queen can attack vertically,
horizontally and diagonally. The problem is, on an N*N chessboard, we have to place N
queens so that no two queens should be in the same row, same column and on the same
diagonal. This can be converted to a boolean satisfied expression and is satisfied if there
is a queen on the cell which makes it true, and false if the cell is empty. Since no queens
can attack each other, we use a to show the position of the N kings binary matrix (N*N).
To solve the n-queens problem, we use the miniSat SAT solver, which takes the CNF
file as input and gives an output that satisfies n-queens.
