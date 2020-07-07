package command;

import data.MongoStorage;

public class AddProduct implements Command {
    @Override
    public void execute(String command, MongoStorage storage) {
        String[] data = command.split(" ");
        storage.addProduct(data[1], Integer.parseInt(data[2]));
    }
}
