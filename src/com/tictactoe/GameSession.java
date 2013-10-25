package com.tictactoe;

public class GameSession {

	private Player player1;

	private Player player2;

	private Field field;

	private void refreshScreen() {
        System.out.println();
        for (int j = 0; j < field.fieldSize; j++) {
            for (int i = 0; i < field.fieldSize; i++) {
                System.out.print("[" + field.getCell(i, j) + "]");
            }
            System.out.println();
        }
        System.out.println();
    }

	public void play() {
        field = new Field();
        preparePlayers();
        refreshScreen();
        char winSymbol;
        boolean turnPlayer = true;
        do {
            Cell move;
            do {
                if (turnPlayer) {
                    move = player1.getMove(field);
                } else {
                    move = player2.getMove(field);
                }
            } while (move == null);
            field.setCell(move.x, move.y, turnPlayer ? player1.getPlayerSymbol() : player2.getPlayerSymbol());
            refreshScreen();
            turnPlayer = !turnPlayer;
            winSymbol = VictoryChecker.victoryCode(field, player1.getPlayerSymbol(), player2.getPlayerSymbol());
        } while (winSymbol == VictoryChecker.CONTINUE_SYMBOL);
        if (winSymbol == VictoryChecker.DRAW_SYMBOL) {
            System.out.println("!!!DRAW!!!");
        } else {
            System.out.println("Winner is " +
                    ((winSymbol == player1.getPlayerSymbol()) ? player1.getName() : player2.getName()) +"!!!");
        }
    }

	private void preparePlayers() {
        player1 = new AI(7);
        player1.setPlayerName("AI 1");
        player1.setPlayerSymbol('X');
        ((AI)player1).setEnemySymbol('0');
        //System.out.println("You are " + player1.getName() + ". Your symbol is \"" + player1.getPlayerSymbol() + "\"");
        player2 = new AI(9);
        player2.setPlayerName("AI 2");
        player2.setPlayerSymbol('0');
        ((AI)player2).setEnemySymbol('X');
     //   System.out.println("You are " + player2.getName() + ". Your symbol is \"" + player2.getPlayerSymbol() + "\"");
        System.out.println("\"X\" moves first!");
    }

}
