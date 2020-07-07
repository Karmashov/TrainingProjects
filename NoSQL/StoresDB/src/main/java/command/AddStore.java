package command;

import data.MongoStorage;

public class AddStore implements Command {
    @Override
    public void execute(String command, MongoStorage storage) {
        String[] data = command.split(" ", 2);
        storage.addStorage(data[1]);
    }
}
