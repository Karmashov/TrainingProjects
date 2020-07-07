package main.controller;

public class EntityNotFoundException extends RuntimeException
{
    private String message;

    public static EntityNotFoundException createWithMessage(String message)
    {
        return new EntityNotFoundException(message);
    }

    private EntityNotFoundException(String message)
    {
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
