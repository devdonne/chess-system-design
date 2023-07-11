package application;

import chess.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Run {

    public static void main(String args[]) {
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList();

        Scanner sc = new Scanner(System.in);

        while (!chessMatch.getCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);

                System.out.println("");

                System.out.println("Source:");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);

                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println("");
                System.out.println("Target: ");

                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                if (capturedPiece != null) {
                    captured.add(capturedPiece);
                }
                
                if (chessMatch.getPromoted() != null){
                    System.out.println("Enter piece for promotion (B/N/R/Q): ");
                    String type = sc.next();
                    
                    chessMatch.replacePromotedPiece(type);
                }
            } catch (ChessException | InputMismatchException | IllegalStateException e) {
                System.out.println(e.getMessage());
                System.out.println();
                System.out.println("Write anything and press Enter to continue");
                sc.next();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
}
