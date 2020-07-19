package com.azuredrake.fabriccom;

public class FabricCommunication 
{
    public FabricCommunication() {}

    public FabricCommunication(String sender, String message)
    {
        Sender = sender;
        Message = message;
    }

    public FabricCommunication(String sender, String message, Object data)
    {
        Sender = sender;
        Message = message;
        Data = data;
    }

    public String Sender;
    public String Message;
    public Object Data;
}