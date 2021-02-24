package shoeShop;

/**
 * Created by Katri Vidén
 * Date: 2021-02-22
 * Time: 10:41
 * Project: inlamningShoeShop
 * Copyright: MIT
 */
public class Shoe {

    private int id;
    private String name;
    private int size;
    private String color;
    private int price;
    private int inStock;

    public Shoe(int id, String name, int size, String color, int price, int inStock) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.color = color;
        this.price = price;
        this.inStock = inStock;
    }

    public Shoe() {
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }
}
