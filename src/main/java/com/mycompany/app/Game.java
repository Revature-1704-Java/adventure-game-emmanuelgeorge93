package com.mycompany.app;

import java.util.*;
import java.io.*;
import java.util.Scanner;

public class Game {
    private Parser parser;
    private Room currentRoom;
    private static Game GAME;
    private static boolean rflag;

    public static void main(String[] args) {
        //Game game = new Game();
        //

            Game game = getInstance();
            if(rflag == false)
                System.out.println("Well looks like we have to reload...");
            else
                game.play();
    }

    public static Game getInstance(){
        if(GAME == null){
            GAME = new Game();
        }

        return GAME;
    }
    
    //singleton of game becasue we only need one game running at a time
    private Game() {
        createRooms();
        parser = new Parser();
    }

//for testing we create 9 hardcoded rooms, rooms set in a 3*3 fashion, 
//some will lead to dead ends becasue outside playing area
    private void createRooms() {
        int n = 0; //n is number of rooms
        List<Room> roomList = new ArrayList<>();
        rflag = true; //read flag for making rooms

        //get the file name for a game save
        String filename = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter filename for Configuration --> ");
        filename = sc.nextLine();

        //read in from file
        try(BufferedReader fr = new BufferedReader(new FileReader(filename))){
            String nme, dsc; //name and desc for a room from file

            n = Integer.parseInt(fr.readLine()); //read in the number of rooms to make
            for(int i = 0; i < n; i++){
                nme = fr.readLine();
                dsc = fr.readLine();
                roomList.add(new Room(nme, dsc));
            }
        } catch (FileNotFoundException ex){
            System.out.println("404 File not found, please check spelling and file location.");
            rflag = false;
            //ex.printStackTrace();
         } catch (IOException ex) {
            System.out.println("There is a problem with the file.");
            rflag = false;
            //ex.printStackTrace();
         }

        if(rflag == true){
            //n is total number of rooms in the game for wach room we have 4 exits,
            //this loop sets each of the 4 exits for a room to its 4 neighbors,
            //if the room is on the edge of the map it will have that edge space set to nothing
            for(int i = 0; i < n; i++){
                //if exit number is less than the number of rooms and greater than or equal to 0,
                //room exists, set room as an exit, else set room exit as default room

                int a = i;

                //exit above room
                if((0 <= (a+3)) && ((a+3) < n)){
                    roomList.get(a).setExit(0, roomList.get(a+3));
                    System.out.println(a + "<--a||a+3--> " + (a+3));
                } else {
                    roomList.get(a).setExit(0, new Room());
                    System.out.println(a + "<--a||a+3--> " + (a+3));
                }
                a=i;

                //exit right of room
                if((0 <= (a+1)) && ((a+1) < n)){
                    roomList.get(a).setExit(1, roomList.get(a+1));
                    System.out.println(a + "<--a||a+3--> " + (a+1));
                } else {
                    roomList.get(a).setExit(1, new Room());
                    System.out.println(a + "<--a||a+3--> " + (a+1));
                }
                a=i;

                //exit below room
                if((0 <= (a-3)) && ((a-3) < n)){
                    roomList.get(a).setExit(2, roomList.get(a-3));
                    System.out.println(a + "<--a||a+3--> " + (a-3));
                } else {
                    roomList.get(a).setExit(2, new Room());
                    System.out.println(a + "<--a||a+3--> " + (a-3));
                }
                a=i;

                //exit left of room
                if((0 <= (a-1)) && ((a-1) < n)){
                    roomList.get(a).setExit(3, roomList.get(a-1));
                    System.out.println(a + "<--a||a+3--> " + (a-1));
                } else {
                    roomList.get(a).setExit(3, new Room());
                    System.out.println(a + "<--a||a+3--> " + (a-1));
                }
                a=i;
            }

            currentRoom = roomList.get(0);  // start game in left-botom most corner of the map
        }
    }

    //print welcome message, while not finished process next command from parser
    //when done finished is true, end loop print good bye
    public void play() {            
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    //message at start
    private void printWelcome() {
        System.out.println();
        System.out.println("This game does not conform to any known labels.");
        System.out.println("Type 'help' or not its up to you.");
        System.out.println(currentRoom.getDesc());
    }

    private boolean processCommand (Command command) {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I cant be bothered to parse that.");
            wantToQuit = false; //want to quit is false
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if (commandWord.equals("look")){
            currentRoom.getExits();
        }
        // else command not recognised.
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("Hearing your crys a dog hits you with an instruction manual.");
        parser.showCommands();
    }

    private void goRoom(Command command) {

        int f = -1;
        Room nextRoom = null;

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        //this returns a string if that string is left, right, down or up
        //we can set that to a number and go to that room
        String direction = command.getSecondWord();

        //this should give us the name of the first exit
        //if this is true this is the room we want to move to
        //so we move there else check next
        if (direction.equals(currentRoom.getExit(0).getName())){
            f = 0;
        } else if (direction.equals(currentRoom.getExit(1).getName())){
            f = 1;
        } else if (direction.equals(currentRoom.getExit(2).getName())){
            f = 2;
        } else if (direction.equals(currentRoom.getExit(3).getName())){
            f = 3;
        }
        
        // Try to leave current room.
        //Room nextRoom = currentRoom.getExit(direction);
        //if f is in the range of valid numbers
        if((f >=0) && (f < 4)){
            nextRoom = currentRoom.getExit(f);
        }

        //room must exist
        if (nextRoom == null) {
            System.out.println("404 room not found!");
        } else if (nextRoom.getName().equals("Nothing")){
            System.out.println("Please dont break the game I worked hard on this.");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getDesc());
        }
    }

    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("Dont give up " + command.getSecondWord() + "!");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
