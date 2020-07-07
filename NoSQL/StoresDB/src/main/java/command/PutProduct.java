package command;

import data.MongoStorage;

public class PutProduct implements Command {
    @Override
    public void execute(String command, MongoStorage storage) {
        String[] data = command.split(" ");
        storage.putProduct(data[1], data[2]);
    }
}
