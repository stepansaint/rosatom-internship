package com.rosatom.a_JavaSE.First;

import java.util.Objects;

/**
 * Represents the player at {@link Battleship}.
 */
abstract class BattleshipPlayer {
    private final byte[][] playingField;
    private final String playerName;

    BattleshipPlayer(byte[][] playingField, String playerName) {
        this.playingField = Objects.requireNonNull(playingField);
        this.playerName = Objects.requireNonNullElse(playerName, "UnknownPlayer#" + Math.random());
    }

    /**
     * Represents the game logic of the move.
     *
     * @param opponent current opponent, which takes this move
     * @return result of this move
     * @see MoveResult
     */
    abstract MoveResult makeMove(BattleshipPlayer opponent);

    byte[][] getPlayingField() {
        return playingField;
    }

    String getPlayerName() {
        return playerName;
    }

    @Override
    public String toString() {
        return playerName;
    }
}
