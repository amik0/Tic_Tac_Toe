package com.tictactoe;

import java.util.Scanner;

public class Human extends Player {

    private boolean backRequest = false;

    public boolean isBackRequest() {
        return backRequest;
    }

    public void backRequestOK() {
        backRequest = false;
    }

	public Cell getMove(Field field) {
        Cell move = new Cell();
        System.out.print("Enter your move (1-" +  (field.fieldSize * field.fieldSize) + "): ");
        Scanner scan = new Scanner(System.in);
        int userValue = scan.nextInt();
        if (userValue == 0) {
            backRequest = true;
            return null;
        }
        if (userValue > 0 && userValue < (field.fieldSize * field.fieldSize + 1)) {
            move.x = (userValue - 1) % field.fieldSize;
            move.y = (userValue - 1) / field.fieldSize;
            if (field.getCell(move.x, move.y) == field.fieldFiller) {
                return move;
            }
        }
        return null;
	}

    public void wrongMoveWarning() {
        System.out.println("Incorrect value!");
    }

}
