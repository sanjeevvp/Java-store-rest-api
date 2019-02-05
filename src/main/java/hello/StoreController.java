package hello;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    private ArrayList<Item> store = new ArrayList<Item>();
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/item/add")
    public Response addItem(@RequestParam(value="name", defaultValue="New Item") String name) {
        if(containsItemByName(name)){
            return new Response(409, "Conflict! Item already exists", "");
        }
        else{
            Item item = new Item(counter.incrementAndGet(), name);
            store.add(item);
            return new Response(200, "OK", item);
        }
    }

    @RequestMapping("/item/stock/add")
    public Response addNewStock(@RequestParam(value="id") int id, @RequestParam(value="stock") int newStock) {
        if(addStock(id, newStock)){
            return new Response(200, "OK", null);
        }
        else{
            return new Response(404, "No Item with that ID", null);
        }
    }

    @RequestMapping("/item/stock/set")
    public Response setNewStock(@RequestParam(value="id") int id, @RequestParam(value="stock") int newStock) {
        if(setStock(id, newStock)){
            return new Response(200, "OK", null);
        }
        else{
            return new Response(404, "No Item with that ID", null);
        }
    }

    @RequestMapping("/item/stock/remove")
    public Response removeNewStock(@RequestParam(value="id") int id, @RequestParam(value="stock") int stockToRemove) {
        if(removeStock(id, stockToRemove)){
            return new Response(200, "OK", null);
        }
        else{
            return new Response(404, "No Item with that ID", null);
        }
    }

    public Boolean addStock (int id, int newStock) {
        for(Item i: store) {
            if (i.getId() == id) {
                i.addStock(newStock);
                return true;
            }
        }
        return false;
    }

    public Boolean setStock (int id, int newStock) {
        for(Item i: store) {
            if (i.getId() == id) {
                i.setStock(newStock);
                return true;
            }
        }
        return false;
    }

    public Boolean removeStock (int id, int newStock) {
        for(Item i: store) {
            if (i.getId() == id) {
                i.removeStock(newStock);
                return true;
            }
        }
        return false;
    }

    public Boolean containsItemByName(String name){
        for(Item i: store){
            if(i.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Boolean containsItemById(int id){
        for(Item i: store){
            if(i.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping("/items")
    public ArrayList<Item> listItems() {
        return store;
    }
}