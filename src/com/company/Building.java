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


    public void inspectTheBuilding(){
        if (itemsInBuilding.isEmpty()){
            System.out.println("V budově není nic zajímavého");
        }
        else if(itemsInBuilding.size()==1){
            System.out.println("Šerif si všiml že v místnosti je podezřele vypadající " + itemsInBuilding.get(0).getNameOfTheItem());
        }
        else{
            System.out.println("Šerifa zaujali tyto předměty");
            for (Item item: itemsInBuilding ) {
                System.out.print(item.getNameOfTheItem() + " " );
            }
            System.out.println();
        }
    }

    public String exploreItem(int index){
        return itemsInBuilding.get(index).getDescriptionOfTheItemWhenFound();
    }

    public Item takeAnItem(int index){
        Item takenItem = itemsInBuilding.get(index-1);
        itemsInBuilding.remove(index-1);
        System.out.println("Šerif si vzal " + takenItem.getNameOfTheItem());
        return takenItem;
    }

    public void showConnectedBuilding(){
        System.out.println();
        System.out.println("Šerif se odsud mohl vydat do těchto budov:");
        for (int i = 0; i < accessibleBuildings.size(); i++) {

            System.out.printf("%d - %S\n",(i+1), accessibleBuildings.get(i).getNameOfTheBuilding());

        }
        System.out.println( accessibleBuildings.size()+1 + " - zůstat na místě");
    }

    public void removeCharacterFromBuilding(){charactersInBuilding.clear();}

    public String getNameOfTheBuilding(){
        return nameOfTheBuilding;
    }

    public int NumberOfConnectedBuilding(){
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
}
