package shoeShop;

/**
 * Created by Katri Vidén
 * Date: 2021-02-24
 * Time: 13:51
 * Project: inlamningShoeShop
 * Copyright: MIT
 */
public class CartItem {

    private String date;
    private String brand;
    private String name;
    private int size;
    private String color;
    private int quantity;
    private int priceEach;
    private int total;

    public CartItem(String date, String brand, String name, int size, String color, int priceEach, int quantity) {
        this.date = date;
        this.brand = brand;
        this.name = name;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.priceEach = priceEach;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(int priceEach) {
        this.priceEach = priceEach;
    }

    public int getTotal() {
        return priceEach * quantity;
    }

}
