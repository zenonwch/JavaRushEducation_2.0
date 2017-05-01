package com.javarush.task.task38.task3805;

public class ConnectionMock implements Connection {
    @Override
    public void connect() throws WrongDataException, ConnectionException {
        throw new ConnectionException("test");
    }

    @Override
    public void write(final Object data) throws WrongDataException, ConnectionException {
    }

    @Override
    public Object read() throws WrongDataException, ConnectionException {
        return null;
    }

    @Override
    public void disconnect() throws WrongDataException, ConnectionException {

    }
}
