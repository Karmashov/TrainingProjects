package command;

import data.MongoStorage;

public interface Command {
    void execute(String command, MongoStorage storage);
}
