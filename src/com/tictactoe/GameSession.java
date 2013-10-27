package com.tictactoe;

import java.util.Stack;

public class GameSession {

	private Player player1;

	private Player player2;

	private Stack<Field> fieldsList;

	private void refreshScreen() {
        System.out.println();
        for (int j = 0; j < fieldsList.peek().fieldSize; j++) {
            for (int i = 0; i < fieldsList.peek().fieldSize; i++) {
                System.out.print("[" + fieldsList.peek().getCell(i, j) + "]");
            }
            System.out.println();
        }
        System.out.println();
    }

	public void play() {
        fieldsList = new Stack<Field>();
        Field field = new Field();
        fieldsList.push(field);
        preparePlayers();
        refreshScreen();
        char winSymbol = VictoryChecker.CONTINUE_SYMBOL;
        Player currentPlayer;
        currentPlayer = player1;
        do {
            Cell move;
            move = currentPlayer.getMove(fieldsList.peek());
            if (move == null) {
                if (currentPlayer.isBackRequest()) {
                    fieldsList.pop();
                    if (!(fieldsList.empty())){
                        fieldsList.pop();
                    }
                    if (fieldsList.empty()) {
                        field = new Field();
                        fieldsList.push(field);
                    }
                    currentPlayer.backRequestOK();
                } else {
                    currentPlayer.wrongMoveWarning();
                }
                refreshScreen();
            } else {
                field = new Field(fieldsList.peek());
                field.setCell(move.x, move.y, currentPlayer.getPlayerSymbol());
                winSymbol = VictoryChecker.victoryCode(field, player1.getPlayerSymbol(), player2.getPlayerSymbol());
                fieldsList.push(field);
                refreshScreen();
                if (currentPlayer == player1) {
                    currentPlayer = player2;
                } else {
                    currentPlayer = player1;
                }
            }
        } while (winSymbol == VictoryChecker.CONTINUE_SYMBOL);
        if (winSymbol == VictoryChecker.DRAW_SYMBOL) {
            System.out.println("!!!DRAW!!!");
        } else {
            System.out.println("Winner is " +
                    ((winSymbol == player1.getPlayerSymbol()) ? player1.getName() : player2.getName()) +"!!!");
        }
    }

	private void preparePlayers() {
        player1 = new Human();
        player1.setPlayerName("Player 1");
        player1.setPlayerSymbol('X');
       // ((AI)player1).setEnemySymbol('0');
        System.out.println("You are " + player1.getName() + ". Your symbol is \"" + player1.getPlayerSymbol() + "\"");
        System.out.println("Cancel move - 0");
        player2 = new AI(3);
        player2.setPlayerName("AI");
        player2.setPlayerSymbol('0');
        ((AI)player2).setEnemySymbol('X');
     //   System.out.println("You are " + player2.getName() + ". Your symbol is \"" + player2.getPlayerSymbol() + "\"");
        System.out.println("\"X\" moves first!");
    }

}
