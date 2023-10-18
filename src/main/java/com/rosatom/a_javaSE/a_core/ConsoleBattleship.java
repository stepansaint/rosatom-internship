package com.rosatom.a_javaSE.a_core;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Represents a specific {@link Battleship}, which goes in the user's console.
 */
public final class ConsoleBattleship extends Battleship<ConsoleBattleshipPlayer> {
    public ConsoleBattleship(String firstPlayerName, String secondPlayerName) {
        this(new ConsolePlayerSupplier(firstPlayerName), new ConsolePlayerSupplier(secondPlayerName));
    }

    private ConsoleBattleship(Supplier<ConsoleBattleshipPlayer> firstSupplier,
                              Supplier<ConsoleBattleshipPlayer> secondSupplier) {
        super(firstSupplier, secondSupplier);
    }

    @Override
    void start() {
        System.out.println("\n\n\nEnter the position to hit in form: ?x?. Then hit the enter.");
        System.out.println("Explanation: 9x0 - the most right and up cell.");
        super.start();
    }

    /**
     * Determines, if there's a user with <code>0</code> used cells
     * by all ships on the playing field.
     */
    @Override
    boolean hasWinner() {
        if (getFirstBattleshipPlayer().getUsedCells() == 0) {
            congratulatePlayer(getSecondBattleshipPlayer());
            return true;
        } else if (getSecondBattleshipPlayer().getUsedCells() == 0) {
            congratulatePlayer(getFirstBattleshipPlayer());
            return true;
        }

        return false;
    }

    private void congratulatePlayer(ConsoleBattleshipPlayer winner) {
        System.out.println("\n===========================");
        System.out.println("\t\t" + winner + " won!");
        System.out.println("===========================\n");
    }

    /**
     * Helps to make a certain {@link ConsoleBattleshipPlayer}
     * with a correctly filled playing field.
     */
    private static class ConsolePlayerSupplier implements Supplier<ConsoleBattleshipPlayer> {
        private final String playerName;
        private static final Scanner scanner;

        static {
            scanner = new Scanner(System.in);
            Runtime.getRuntime().addShutdownHook(new Thread(scanner::close));
        }

        private ConsolePlayerSupplier(String playerName) {
            this.playerName = playerName;
        }

        @Override
        public ConsoleBattleshipPlayer get() {
            byte[][] playingField = new byte[10][10];
            fillPlayingField(playingField);

            return new ConsoleBattleshipPlayer(playingField, playerName, scanner, 20);
        }

        private void fillPlayingField(byte[][] playingField) {
            System.out.println("\nThis is " + playerName + "'s playing field.");
            System.out.println("Enter the position to place your ship in form: ?x?. Then hit the enter.");
            System.out.println("Explanation: 9x0 - the most right and up cell.");

            final int shipTypes = 4;

            for (int shipSize = 1; shipSize <= shipTypes; shipSize++) {
                int shipsCount = shipTypes - shipSize + 1;

                while (shipsCount-- != 0) {
                    getCoordinatesForShip(playingField, shipSize).forEach((coordinate) -> {
                        int x = Parser.parseCoordinate("x", coordinate);
                        int y = Parser.parseCoordinate("y", coordinate);
                        playingField[x][y] = 1;
                    });
                }
            }
        }

        private Set<String> getCoordinatesForShip(byte[][] playingField, final int shipSize) {
            // show the player's playing field
            System.out.println("\n\t\t\t" + playerName + "'s playing field:\n");
            System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7\t8\t9");
            System.out.println("\t-------------------------------------");
            for (int row = 0; row < playingField.length; row++) {
                System.out.print(row + " |\t");
                for (int cell : playingField[row]) {
                    if (cell == 0) {
                        System.out.print("•\t");
                    } else {
                        System.out.print("▢\t");
                    }
                }
                System.out.println();
            }

            Set<String> coordinates = new LinkedHashSet<>(shipSize);
            String direction = getDirectionForShip(shipSize);

            do {
                System.out.println("\nPlease, type the coordinate for your " + shipSize + "-deck ship.");
                String coordinate = getOneCoordinate(playingField, direction, coordinates);
                coordinates.add(coordinate);
                System.out.println("Great!");
            } while (coordinates.size() != shipSize);

            return coordinates;
        }

        private String getDirectionForShip(int shipSize) {
            String direction = null;

            if (shipSize > 1) {
                System.out.println("\nPlease, choose the direction for your "
                        + shipSize + "-deck ship: vertical (type V) or horizontal (type H).");
                do {
                    direction = scanner.next();
                } while (!direction.matches("[VH]"));
            }

            return direction;
        }

        private String getOneCoordinate(byte[][] playingField, String direction, Set<String> coordinates) {
            String coordinate = null;

            do {
                coordinate = scanner.next();
            } while (!coordinate.matches("\\dx\\d")
                    || !isPossibleToPlace(playingField, coordinate, coordinates, direction));

            return coordinate;
        }

        private boolean isPossibleToPlace(byte[][] playingField, String coordinate,
                                          Set<String> coordinates, String direction) {
            final int x = Parser.parseCoordinate("x", coordinate);
            final int y = Parser.parseCoordinate("y", coordinate);

            // check the field's coordinates around 'coordinate'
            // (there should be no 1's)
            for (int i = Math.max(x - 1, 0); i <= Math.min(x + 1, 9); i++) {
                for (int j = Math.max(y - 1, 0); j <= Math.min(y + 1, 9); j++) {
                    if (playingField[i][j] == 1) {
                        return false;
                    }
                }
            }

            // check the old coordinates for the current ship
            // (the ship should be placed exactly in one column/row)
            if (direction != null) {
                Stream<String> coordinatesStream = coordinates.stream();
                switch (direction) {
                    case "H" -> {
                        if (coordinatesStream.anyMatch((old) ->
                                Parser.parseCoordinate("x", old) != x)) {
                            return false;
                        }
                    }
                    case "V" -> {
                        if (coordinatesStream.anyMatch((old) ->
                                Parser.parseCoordinate("y", old) != y)) {
                            return false;
                        }
                    }
                }
            }

            return true;
        }
    }
}