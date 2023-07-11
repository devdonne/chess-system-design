package chess;

import boardgame.Piece;
import boardgame.Position;

public class Played {

    private Position source;
    private Position target;
    private ChessMatch chessMatch;
    private Piece capturedPiece;

    public Played(Position source, Position target, ChessMatch chessMatch, Piece capturedPiece) {
        this.source = source;
        this.target = target;
        this.chessMatch = chessMatch;
        this.capturedPiece = capturedPiece;
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }

    public ChessMatch getChessMatch() {
        return chessMatch;
    }

    public Piece getCapturedPiece() {
        return capturedPiece;
    }

}
