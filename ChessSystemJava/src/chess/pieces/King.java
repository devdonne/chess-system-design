package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getRows()];

        Position p = new Position(0, 0);
        //right
        colorPossibleMoves(p, mat, 0, 1);
        //left
        colorPossibleMoves(p, mat, 0, -1);
        //above
        colorPossibleMoves(p, mat, -1, 0);
        //below
        colorPossibleMoves(p, mat, 1, 0);
        //nw
        colorPossibleMoves(p, mat, -1, -1);
        //ne
        colorPossibleMoves(p, mat, -1, 1);
        //sw
        colorPossibleMoves(p, mat, 1, -1);
        //se
        colorPossibleMoves(p, mat, 1, 1);
        return mat;
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

    private void colorPossibleMoves(Position p, boolean[][] mat, int row, int column) {
        p.setValues(position.getRow() + row, position.getColumn() + column);

        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }

}
