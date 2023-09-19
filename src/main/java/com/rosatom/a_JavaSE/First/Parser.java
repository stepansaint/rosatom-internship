package com.rosatom.a_JavaSE.First;

/**
 * Represents a utility class for {@link Battleship}.
 */
class Parser {
    private Parser() {}

    /**
     * Returns the parsed <code>coordinateName</code> from <code>from</code>.
     *
     * @param coordinateName name of the coordinate to find
     * @param from <code>String</code> to be parsed
     * @return parsed <code>coordinateName</code> from <code>from</code>
     * @throws IllegalArgumentException if there's no such coordinate in the game
     */
    static int parseCoordinate(String coordinateName, String from) throws IllegalArgumentException {
        return switch (coordinateName) {
            case "x" -> Integer.parseInt(from, 2, 3, 10);
            case "y" -> Integer.parseInt(from, 0, 1, 10);
            default -> throw new IllegalArgumentException("There's no such coordinate "
                    + coordinateName + " at the game");
        };
    }
}
