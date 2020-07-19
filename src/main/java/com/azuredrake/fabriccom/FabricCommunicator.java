package com.azuredrake.fabriccom;

import java.util.function.Function;

import org.apache.logging.log4j.Level;

import java.util.Hashtable;

public class FabricCommunicator 
{
    private static Hashtable<String, Function<FabricCommunication, Integer>> Receivers = new Hashtable<String, Function<FabricCommunication, Integer>>();
    public static void RegisterReceiver(String modID, Function<FabricCommunication, Integer> reciever)
    {
        Receivers.put(modID, reciever);
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
        if (Receivers.containsKey(recipient))
        {
            Receivers.get(recipient).apply(communication);
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
        for (Function<FabricCommunication, Integer> reciever : Receivers.values())
        {
            reciever.apply(communication);
        }
    }
}