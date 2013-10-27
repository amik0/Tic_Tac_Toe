package com.tictactoe;

public abstract class Player {

	protected String name;

	protected char playerSymbol;

	public abstract Cell getMove(Field field);

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

    public abstract void wrongMoveWarning();

    protected boolean backRequest = false;

    public boolean isBackRequest() {
        return backRequest;
    }

    public void backRequestOK() {
        backRequest = false;
    }
}
