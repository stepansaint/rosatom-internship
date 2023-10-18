package com.rosatom.a_javaSE.c_io_serialization_generics;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Scanner;

/// FOR TEST PURPOSES ONLY ///
class Test {
    public static void main(String[] args) {
        ConsoleFileManager manager = ConsoleFileManager.getInstance();
        manager.start();
    }
}
//////////////////////////////

/**
 * Represents a CLI file manager.
 * Works with files that has the TXT extension.
 */
public class ConsoleFileManager {

    private static final ConsoleFileManager CONSOLE_FILE_MANAGER = new ConsoleFileManager();

    private ConsoleFileManager() {
    }

    public static ConsoleFileManager getInstance() {
        return CONSOLE_FILE_MANAGER;
    }

    /**
     * Starts a CLI file manager.
     * Uses stdin and stdout.
     */
    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nPlease, type your query:");
                String query = scanner.nextLine();

                switch (query) {
                    case "exit" -> {
                        return;
                    }
                    case "-h" -> {
                        showUsageInformation();
                    }
                    default -> {
                        if (query.matches("\\S+ -\\S+ ?(\".+\")?")) { // has 2 or 3 correct parts of the query
                            processQuery(query.split(" -| |\"\"", 3));
                        } else {
                            System.err.println("\n\nInvalid query. To get more information about the usage, please, type -h.\n");
                        }
                    }
                }
            }
        }
    }

    private void showUsageInformation() {
        System.out.println("\n\nUsage: ");
        System.out.println("1. Type the absolute path to your file with .txt extension\n\t(e.g., /home/Users/violeta22/my_folder/secrets.txt)");
        System.out.println("2. Hit the space and type the flag with a dash before: -create, -read, -update, -delete\n\t(e.g., -read)");
        System.out.println("3. (Optional) If you specified -update flag, you should hit the space and type the new text in these quotes \"\" (the text should not be empty) to append to the file\n\t(e.g., \"my new secret is... \")");
        System.out.println("\nIf you want to exit the program, type exit.");
        System.out.println("\nExamples of a whole query:");
        System.out.println("/home/Users/violeta22/my_folder/secrets.txt -read");
        System.out.println("/home/Users/violeta22/my_folder/secrets.txt -update \"hello!!\"\n");
    }

    private void processQuery(String[] queryParts) {
        try {
            Path path = Path.of(queryParts[0].trim());
            FileManagerAction action = FileManagerAction.valueOf(queryParts[1].trim().toUpperCase());
            String text = (queryParts.length == 3 ? queryParts[2].trim() : "");

            if (!queryPartsValid(path, action, text)) {
                return;
            }

            doAction(path, action, text);
        } catch (IllegalArgumentException e) {
            System.err.println("\nSorry, but you specified an invalid flag.");
        } catch (FileAlreadyExistsException e) {
            System.err.println("\nSorry, but you provided a file that already exists.");
        } catch (NoSuchFileException e) {
            System.err.println("\nSorry, but there is no such file to work with.");
        } catch (IOException e) {
            System.err.println("\nSorry, but there was an error while working with a file.\nPlease, check, whether you provided a correct path.");
        }
    }

    private boolean queryPartsValid(Path path, FileManagerAction action, String text) {
        if (!path.isAbsolute() || !path.toString().endsWith(".txt")) {
            System.err.println("\nSorry, but you specified not an absolute path to your file with TXT extension.");
            return false;
        }

        if (action == FileManagerAction.UPDATE && !text.matches("\".+\"")) {
            System.err.println("\nSorry, but you didn't provide a text to append to a file.");
            return false;
        }

        return true;
    }

    private void doAction(Path path, FileManagerAction action, String text) throws IOException {
        switch (action) {
            case CREATE -> {
                Files.createFile(path);
                System.out.println("\nYou successfully created a new file.");
            }
            case READ -> {
                Files.readAllLines(path, StandardCharsets.UTF_8)
                        .forEach(System.out::println);
                System.out.println("\nYou successfully read your file.");
            }
            case UPDATE -> {
                Files.writeString(path, text, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                System.out.println("\nYou successfully updated a file.");
            }
            case DELETE -> {
                Files.delete(path);
                System.out.println("\nYou successfully deleted a file.");
            }
        }
    }
}

enum FileManagerAction {
    CREATE,
    READ,
    UPDATE,
    DELETE
}
