package com.tictactoe;

public class Player {

	private String name;

	private char playerSymbol;

	public Cell getMove(Field field) {
		return null;
	}

	public void setPlayerSymbol(char symbol) {
        playerSymbol = symbol;
	}

	public void setPlayerName(String name) {
        this.name = name;
	}

    public char getPlayerSymbol() {
        return playerSymbol;
    }

    public String getName() {
        return name;
    }
}
