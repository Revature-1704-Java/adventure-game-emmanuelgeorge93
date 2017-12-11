package com.mycompany.app;

import java.util.Set;
import java.util.HashMap;

public class Room{
    private String name; //name of a room
    private String desc;
    private Room exits[]; //list of exits

    //CTOR
    public Room(){
        this.name = "Nothing";
        this.desc = "Nothingness";

        exits = new Room[4];
        for(int i = 0; i < 4; i++){
            exits[i] = null;
        }
    }

    //CTOR
    public Room(String name){
        this.name = name;
        this.desc = "Nothingness";
        
        exits = new Room[4];
        for(int i = 0; i < 4; i++){
            exits[i] = null;
        }
    }

    //CTOR
    public Room(String name, String desc){
        this.name = name;
        this.desc = desc;

        exits = new Room[4];
        for(int i = 0; i < 4; i++){
            exits[i] = null;
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return desc;
    }

    public void setExits(Room e1, Room e2, Room e3, Room e4){
        exits[0] = e1;
        exits[1] = e2;
        exits[2] = e3;
        exits[3] = e4;
    }

    public void setExit(int i, Room temp){
        exits[i] = temp;
    }

    public void getExits(){
       System.out.println(exits[0].getName() + " above.");
       System.out.println(exits[1].getName() + " to the right.");
       System.out.println(exits[2].getName() + " below.");
       System.out.println(exits[3].getName() + " to the left.");
    }

    public Room getExit(int i){
        return exits[i];
     }
}