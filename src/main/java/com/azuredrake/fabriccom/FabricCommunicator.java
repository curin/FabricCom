package com.azuredrake.fabriccom;

import java.util.function.Function;

import org.apache.logging.log4j.Level;

import java.util.Hashtable;

public class FabricCommunicator 
{
    private static Hashtable<String, Function<FabricCommunication, Integer>> Recievers = new Hashtable<String, Function<FabricCommunication, Integer>>();
    public static void RegisterReciever(String modID, Function<FabricCommunication, Integer> reciever)
    {
        Recievers.put(modID, reciever);
    }

    public static void Send(String modID, String recipient, String message)
    {
        Send(recipient, new FabricCommunication(modID, message));
    }

    public static void Send(String modID, String recipient, String message, Object data)
    {
        Send(recipient, new FabricCommunication(modID, message, data));
    }

    public static void Send(String recipient, FabricCommunication communication)
    {
        if (Recievers.containsKey(recipient))
        {
            Recievers.get(recipient).apply(communication);
        }
        else
        {
            FabricCom.log(Level.WARN, "No reciever for mod " + recipient + "registered yet");
        }
    }

    public static void SendGlobal(String modID, String message)
    {
        SendGlobal(new FabricCommunication(modID, message));
    }

    public static void SendGlobal(String modID, String message, Object data)
    {
        SendGlobal(new FabricCommunication(modID, message, data));
    }

    public static void SendGlobal(FabricCommunication communication)
    {
        for (Function<FabricCommunication, Integer> reciever : Recievers.values())
        {
            reciever.apply(communication);
        }
    }
}