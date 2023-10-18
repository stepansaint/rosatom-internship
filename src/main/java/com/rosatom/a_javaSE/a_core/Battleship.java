package com.rosatom.a_javaSE.a_core;

import java.util.function.Supplier;

/**
 * Represents the Battleship game.
 *
 *  @param <T> type of {@link BattleshipPlayer}
 */
abstract class Battleship<T extends BattleshipPlayer> {
    private final T firstBattleshipPlayer;
    private final T secondBattleshipPlayer;

    Battleship(Supplier<T> firstSupplier, Supplier<T> secondSupplier) {
        this.firstBattleshipPlayer = firstSupplier.get();
        this.secondBattleshipPlayer = secondSupplier.get();
    }

    /**
     * Starts the game.
     */
    void start() {
        while (!hasWinner()) {
            playerMakesMove(firstBattleshipPlayer, secondBattleshipPlayer);
            playerMakesMove(secondBattleshipPlayer, firstBattleshipPlayer);
        }
    }

    /**
     * Determines if there's a winner between players.
     * 
     * @return <code>true</code> if there's a winner, otherwise <code>false</code>
     */
    abstract boolean hasWinner();

    /**
     * Represents the game logic of the turn pass.
     *
     * @param firstPlayer player that makes a move
     * @param secondPlayer <code>firstPlayer</code>'s opponent
     */
    void playerMakesMove(BattleshipPlayer firstPlayer, BattleshipPlayer secondPlayer) {
        MoveResult result = null;
        do {
            result = firstPlayer.makeMove(secondPlayer);
        } while (result == MoveResult.HIT);
    }

    T getFirstBattleshipPlayer() {
        return firstBattleshipPlayer;
    }

    T getSecondBattleshipPlayer() {
        return secondBattleshipPlayer;
    }
}
