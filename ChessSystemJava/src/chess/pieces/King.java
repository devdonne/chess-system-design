package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.*;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean testRookCastling(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p instanceof Rook && p.getColor() == getColor() && getMoveCount() == 0;
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

        // #special move castling
        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            // #special move castling kingside rook
            Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(posT1)) {
                Position p1 = new Position(position.getRow(), position.getColumn() + 1);
                Position p2 = new Position(position.getRow(), position.getColumn() + 2);

                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
                    mat[position.getRow()][position.getColumn() + 2] = true;
                }
            }

            // #special move castling queen gside rook
            Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(posT2)) {
                Position p1 = new Position(position.getRow(), position.getColumn() - 1);
                Position p2 = new Position(position.getRow(), position.getColumn() - 2);
                Position p3 = new Position(position.getRow(), position.getColumn() - 3);

                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
                    mat[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        }
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
