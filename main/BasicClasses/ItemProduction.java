package main.BasicClasses;

public class ItemProduction {
    public Item produceItem(String type, int quantity) {
        Item item = new Item(type, quantity);
        return item;
    }
}