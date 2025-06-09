package project;

public class Item {
    private String itemName;
    private int price;
    private ItemState state;

    public Item() {
        this.state = ItemState.AVAILABLE;
    }

    public Item(String itemName, int price) {
        this.state = ItemState.AVAILABLE;
        this.itemName = itemName;
        this.price = price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public void changePrice(int price) {
        this.price = price;
    }

    public ItemState getState() {
        return this.state;
    }

    public void changeState(ItemState state) {
        this.state = state;
    }

}
