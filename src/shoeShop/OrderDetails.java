package shoeShop;

/**
 * Created by Katri Vidén
 * Date: 2021-02-22
 * Time: 16:32
 * Project: inlamningShoeShop
 * Copyright: MIT
 */
public class OrderDetails {

    private int id;
    private int quantity;
    private int priceEach;

    public OrderDetails(int id, int quantity, int priceEach) {
        this.id = id;
        this.quantity = quantity;
        this.priceEach = priceEach;
    }

    public OrderDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
