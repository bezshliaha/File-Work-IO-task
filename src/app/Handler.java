package app;

import app.services.FileReadService;
import app.services.FileWriteService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import static app.utils.Constants.welcomeMessage;

public class Handler {

    private final FileReadService fileReadService = new FileReadService();
    private final FileWriteService fileWriteService = new FileWriteService();

    public void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(welcomeMessage);

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    doCreateAndWriteFile(sc);
                    break;
                case 2:
                    doReadFile(sc);
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void doReadFile(Scanner scanner) {
        System.out.println("Enter the file name (without extension): ");
        try {
            System.out.println("File content: \n" + fileReadService.read(scanner.nextLine()));
        } catch (FileNotFoundException e) {
            //прочитала, что для вывода ошибок лучше писать err а не out
            System.err.println("File not found");
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private void doCreateAndWriteFile(Scanner scanner) {
        System.out.println("Enter the file name (without extension): ");
        Path path;
        try {
            path = fileWriteService.createFile(scanner.nextLine());
        } catch (IOException e) {
            System.err.println("create file problem: %s".formatted(e.getMessage()));
            return;
        }

        System.out.println("Enter the content to write to the file: ");
        try {
            fileWriteService.writeToFile(path, scanner.nextLine());
        } catch (IOException e) {
            System.err.println("write to file problem: %s".formatted(e.getMessage()));
        }
    }
}