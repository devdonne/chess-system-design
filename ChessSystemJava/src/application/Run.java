package application;

import chess.*;
import java.util.Scanner;

public class Run {
    public static void main(String args[]){
        ChessMatch chessMatch = new ChessMatch();
        
        while (true){
            UI.printBoard(chessMatch.getPieces());
            
            System.out.println("");
            
            System.out.println("Source:");
            ChessPosition source = UI.readChessPosition(new Scanner(System.in));
            
            System.out.println("");
            System.out.println("Target: ");
            
            ChessPosition target = UI.readChessPosition(new Scanner(System.in));
            
            ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
        }
        
        
    }
}
