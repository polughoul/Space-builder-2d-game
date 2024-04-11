package main.BasicClasses;

public class Resource {
    private String type;
    private int counts;

    public Resource(String type, int counts) {
        this.type = type;
        this.counts = counts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }
}
