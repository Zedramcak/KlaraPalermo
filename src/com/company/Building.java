package com.company;

import java.util.ArrayList;

public class Building {

    private String nameOfTheBuilding;
    private ArrayList<Character> charactersInBuilding;
    private ArrayList<Item> itemsInBuilding;



    private ArrayList<Building> accessibleBuildings;

    public Building(String nameOfTheBuilding){
        this.nameOfTheBuilding = nameOfTheBuilding;

        charactersInBuilding = new ArrayList<>();
        itemsInBuilding = new ArrayList<>();
        accessibleBuildings = new ArrayList<>();
    }

    public String exploreItem(int index){
        return itemsInBuilding().get(index).getDescriptionOfTheItemWhenFound();
    }

    public Item takeAnItem(int index){
        Item takenItem = itemsInBuilding().get(index-1);
        itemsInBuilding().remove(index-1);
        return takenItem;
    }


    public void removeCharacterFromBuilding(){getCharactersInBuilding().clear();}

    public String getNameOfTheBuilding(){
        return nameOfTheBuilding;
    }

    public int numberOfConnectedBuilding(){
        return accessibleBuildings.size();
    }

    public Building moveToConnectedBuilding(int index){
        return accessibleBuildings.get(index);
    }

    public boolean areThereItems(){ return !itemsInBuilding.isEmpty(); }

    public int numberOfItems() {return itemsInBuilding.size(); }

    public ArrayList<Item> itemsInBuilding(){
        return itemsInBuilding;
    }

    public boolean isSomeoneInTheBuilding(){
        return !charactersInBuilding.isEmpty();
    }

    public ArrayList<Character> getCharactersInBuilding(){
        return charactersInBuilding;
    }

    //setup methods
    public void addCharacter(Character character){
        charactersInBuilding.add(character);
    }

    public void addItem(Item item){
        itemsInBuilding.add(item);
    }

    public void addConnectedBuilding(Building newConnectedBuilding){
        accessibleBuildings.add(newConnectedBuilding);
    }

    public ArrayList<Item> getItemsInBuilding() {
        return itemsInBuilding;
    }

    public ArrayList<Building> getAccessibleBuildings() {
        return accessibleBuildings;
    }
}
