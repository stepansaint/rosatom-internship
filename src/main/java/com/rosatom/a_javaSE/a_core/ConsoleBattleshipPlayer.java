package com.rosatom.a_javaSE.a_core;

import java.util.Objects;
import java.util.Scanner;

/**
 * Represents a certain player at {@link ConsoleBattleship}.
 */
final class ConsoleBattleshipPlayer extends BattleshipPlayer {
    private final Scanner scanner;
    private int usedCells; // amount of used cells on the playing field

    ConsoleBattleshipPlayer(byte[][] playingField, String name, Scanner scanner, int usedCells) {
        super(playingField, name);
        this.scanner = Objects.requireNonNull(scanner);
        this.usedCells = usedCells;
    }

    int getUsedCells() {
        return usedCells;
    }

    /**
     * Prompts the player to write a coordinate to hit.
     * @param opponent current opponent, which takes this move
     */
    @Override
    MoveResult makeMove(BattleshipPlayer opponent) {
        String targetCell = getTargetCell();
        int x = Parser.parseCoordinate("x", targetCell);
        int y = Parser.parseCoordinate("y", targetCell);

        ConsoleBattleshipPlayer enemy = (ConsoleBattleshipPlayer) opponent;

        if (enemy.getPlayingField()[x][y] != 0) { // there was a part of some ship
            enemy.getPlayingField()[x][y] = 0;
            enemy.usedCells--;
            System.out.println("You hit!");
            return MoveResult.HIT;
        } else {
            System.out.println("You missed.");
            return MoveResult.MISS;
        }
    }

    private String getTargetCell() {
        String targetCell = null;
        do {
            System.out.print("\n" + getPlayerName() + "'s next shot: ");
            targetCell = scanner.next();
        } while (!targetCell.matches("\\dx\\d"));

        return targetCell;
    }
}
