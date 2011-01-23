/**
 *
 *
 * @author Jose Rose
 * Dec 26, 2010
 */
package com.cristal.storm.prototype.client.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealRootPopupContentEvent;

/**
 * TODO: Add comments for CommandDigester
 *
 */
public class CommandDigester implements CommandHistory {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    private final List<String> commandStack;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    /**
     * 
     */
    @Inject
    public CommandDigester() {
        commandStack = new ArrayList<String>();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////
    public void parseCommand(String command) {
        // Add the new command
        commandStack.add(command);
        
        // Parse the new command

        
        // Dispatch the command if everything is ok
        dispatchCommand(command);
    }
    
    /**
     * This method should be able to dispatch commands to either the
     * server via {@link GenericAction} or through the client. 
     */
    public void dispatchCommand(String command) {
        //TODO: Dispatch a real command to the server or local machine
        
        // For now we only dispatch a command to local system. So locally create
        // a new CommandDispatcherView to display a dialog with sample dialog view.
        //final CommandHandlerView testCommandView = new CommandHandlerView(eventBus);
        //testCommandView.setCommand(command);
        //testCommandView.showDialog();
        
        //RevealRootPopupContentEvent.fire(eventBus, commandHandlerPresenter);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public Map<Integer, String> getLastCommand() {
        // 
        Map<Integer, String> lastCommandMap = new HashMap<Integer, String>();
        
        if (commandStack.isEmpty())
            return lastCommandMap;
        
        // Parse the last command
//        StringTokenizer st = new StringTokenizer(itr.next());
//        while (st.hasMoreTokens()) {
//            lastCommandMap.put(i, st.nextToken());
//            i++;
//        }

        int i = 0;
        int j = 0;
        int keyIndex = 0;
        String curCommand = commandStack.get(commandStack.size() - 1);
        String subCommand = new String();
        char [] commandChars = curCommand.toCharArray();
        while (i < curCommand.length()) {
            if (commandChars[i] != ' ') {
                j = i + 1;
                
                while (j < curCommand.length()) {
                    if (commandChars[j] == ' ') {
                        break;
                    }
                    j++;
                }

                subCommand = curCommand.substring(i, j);
                lastCommandMap.put(keyIndex, subCommand);
                keyIndex++;
                i = j;
            }
            i++;
        }
        
        return lastCommandMap;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}
