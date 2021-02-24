package shoeShop;

/**
 * Created by Katri Vidén
 * Date: 2021-02-22
 * Time: 15:48
 * Project: inlamningShoeShop
 * Copyright: MIT
 */
public class Category {

    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
