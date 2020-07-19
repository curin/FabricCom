FabricCom

This is a fabric mod meant to facilitate intermod communication.

Below is how to setup a reciever to recieve FabricCom messages:

package com.azuredrake.examplemod; 

import com.azuredrake.fabriccom;

import net.fabricmc.api.ModInitializer; 

import org.apache.logging.log4j.Level;

import org.apache.logging.log4j.LogManager; 

import org.apache.logging.log4j.Logger; 


import java.util.function.Function; 

 
public class ExampleMod implements ModInitializer { 

 
    public ExampleMod ()
    
    { 
    
        Function<FabricCommunication, Integer> fPtr = (FabricCommunication com) -> {return TestReceiver(com);}; 
        
        FabricCommunicator.RegisterReceiver(MOD_ID, fPtr); 
        
    } 
    
 
 
    public static Logger LOGGER = LogManager.getLogger(); 
    
 
    public static final String MOD_ID = "example"; 
    
 
    @Override 
    
    public void onInitialize() { 
    
        //Initialize 
        
    } 
    
 
    public static void log(Level level, String message){ 
    
        LOGGER.log(level, "[" + MOD_NAME + "] " + message); 
        
    } 
    
 
    public Integer TestReceiver(FabricCommunication com) 
    
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

 

The return value does not matter and simply is there out of necessity in java.

 
