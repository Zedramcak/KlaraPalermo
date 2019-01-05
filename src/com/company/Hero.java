package com.company;

import java.util.*;

public class Hero extends Character{

    private ArrayList<Item> inventory;


    public Hero(){
        super();
        name = "Sherif";
        inventory = new ArrayList<Item>();
    }

    public void addItemToInventory(Item item){
        inventory.add(item);
    }

    public void removeItemFromInventory (int item) {inventory.remove(item);}

    public String exploreAnItem(int indexOfItem){
        return inventory.get(indexOfItem).getDescriptionOfTheItemWhenInInventory();
    }

    public boolean isSomethingInInventory(){
        return !inventory.isEmpty();
    }

    public ArrayList<Item> getInventory(){
        return inventory;
    }

    public int sizeOfInventory() {return inventory.size();}

}
