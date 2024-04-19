package main.BasicClasses;

public class Item {
    private String type;
    private int count;

    public Item(String type, int count) {
        this.type = type;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setQuantity(int count) {
        this.count = count;
    }

    public void use(Player player) {
    }


}