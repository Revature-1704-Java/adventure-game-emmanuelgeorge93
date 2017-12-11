package com.mycompany.app;

public class Command {
    private String commandWord; //action to take
    private String secondWord; //target of that action

    public Command(String firstWord, String secondWord) {
        commandWord = firstWord;
        this.secondWord = secondWord;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    //if the commandword is an approved command word return false
    public boolean isUnknown() {
        return (commandWord == null);
    }

    //as long as we have a target for the action
    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}