import command.CommandExecutor;
import data.MongoStorage;
import java.util.Scanner;

public class StoresDB {
    private static Scanner scanner;

    public static void main(String[] args) {
        MongoStorage storage = new MongoStorage();
        storage.dbInit();

        scanner = new Scanner(System.in);
        for (;;) {
            String command = scanner.nextLine();
            CommandExecutor.execute(command, storage);
        }
    }
}
