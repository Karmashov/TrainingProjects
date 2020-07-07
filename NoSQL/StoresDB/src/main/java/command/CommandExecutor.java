package command;

import data.MongoStorage;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("ДОБАВИТЬ_МАГАЗИН", new AddStore());
        commands.put("ДОБАВИТЬ_ТОВАР", new AddProduct());
        commands.put("ВЫСТАВИТЬ_ТОВАР", new PutProduct());
        commands.put("СТАТИСТИКА_ТОВАРОВ", new ShowStatistics());
    }

    private CommandExecutor(){
    }

    public static final void execute(String command, MongoStorage storage){
        String[] data = command.split(" ", 2);
        commands.get(data[0]).execute(command, storage);
    }
}
