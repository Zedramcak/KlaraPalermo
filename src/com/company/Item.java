package com.company;



public class Item {

    private String nameOfTheItem;
    private String descriptionOfTheItemWhenFound;
    private String descriptionOfTheItemWhenInInventory;

    public Item(String nameOfTheItem, String descriptionOfTheItemWhenFound, String descriptionOfTheItemWhenInInventory) {
        this.nameOfTheItem = nameOfTheItem;
        this.descriptionOfTheItemWhenFound = descriptionOfTheItemWhenFound;
        this.descriptionOfTheItemWhenInInventory = descriptionOfTheItemWhenInInventory;
    }

    public String getNameOfTheItem() {
        return nameOfTheItem;
    }

    public String getDescriptionOfTheItemWhenFound() {
        return descriptionOfTheItemWhenFound;
    }

    public String getDescriptionOfTheItemWhenInInventory() {
        return descriptionOfTheItemWhenInInventory;
    }

}
