package com.mycompany.app;

public class CommandWords {
    private static final String[] validCommands = {
        //commands without spaces were an attempt to allow 3 word commands using append 
        //reverted to using "_" as a blank space 
        "go", "help", "look", "quit"
    };

    //CTOR
    public CommandWords() {
        // nothing to do at the moment...
    }

    //go through list of valid commands and compare them to aString if they match we have a valid command
    //return at one point, set a boolean if true or false
    //done this way for easier flow of method, remove multiple return statements
    public boolean isCommand(String aString) {
        boolean isC = false;
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                isC = true;
        }
        // if we get here, the string was not found in the commands
        return isC;
    }

    //print all command to the user
    public void showAll() {
        for(String command: validCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }

    //print all command to the user, trying to create dupliacte of showAll as a normal for loop
    public void showAllCommands() {
        for(int i = 0; i < validCommands.length; i++) {
            System.out.print(validCommands[i] + "  ");
        }
        System.out.println();
    }
}