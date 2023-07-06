package boardgame;

public abstract class Piece {

    protected Position position;
    private Board board;

    public Piece(Board board) {
        position = null;
        this.board = board;
    }

    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove() {
        boolean[][] mat = possibleMoves();

        for (boolean[] mat1 : mat) {
            for (int j = 0; j < mat.length; j++) {
                if (mat1[j] == true) {
                    return true;
                }
            }
        }
        return false;
    }
}
