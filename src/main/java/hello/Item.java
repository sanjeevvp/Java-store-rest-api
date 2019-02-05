package hello;

public class Item {

    private final long id;
    private int stock;
    private final String name;

    public Item(long id, String name) {
        this.id = id;
        this.name = name;
        this.stock = 0;
    }

    public long getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public void addStock(int newStock){
        stock = stock + newStock;
    }

    public void setStock(int newStock){
        stock = newStock;
    }

    public void removeStock(int stockToRemove){
        stock = stock - stockToRemove;
    }

    public String getName() {
        return name;
    }

    public String listStock(){
        return name+" "+stock;
    }
}