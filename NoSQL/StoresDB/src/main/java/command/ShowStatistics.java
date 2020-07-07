package command;

import data.MongoStorage;

public class ShowStatistics implements Command {
    @Override
    public void execute(String command, MongoStorage storage) {
        storage.showStatistics();
    }
}
