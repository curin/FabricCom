package com.azuredrake.fabriccom;

import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;

public class FabricCom implements ModInitializer {

    public FabricCom()
    {
        Function<FabricCommunication, Integer> fPtr = (FabricCommunication com) -> {return TestReciever(com);};
        FabricCommunicator.RegisterReceiver("test", fPtr);
    }

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "fabriccom";
    public static final String MOD_NAME = "FabricComs";

    @Override
    public void onInitialize() {
        log(Level.INFO, "Communications are Live");
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }


    public Integer TestReciever(FabricCommunication com)
    {
        if (com.Data != null)
        {
            if (com.Message != null)
            {
                log(Level.INFO, "[Sender]=" + com.Sender + ", [Message]=" + com.Message + ", [Data]=" + com.Data.toString());
            }
            else
            {
                log(Level.INFO, "[Sender]=" + com.Sender + ", [Data]=" + com.Data.toString());
            }
        }
        else
        {
            if (com.Message != null)
            {
                log(Level.INFO, "[Sender]=" + com.Sender + ", [Message]=" + com.Message);
            }
            else
            {
                log(Level.INFO, "[Sender]=" + com.Sender);
            }
        }
        return 0;
    }
}