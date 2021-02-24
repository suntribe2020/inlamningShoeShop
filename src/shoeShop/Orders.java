package shoeShop;

/**
 * Created by Katri Vidén
 * Date: 2021-02-22
 * Time: 16:26
 * Project: inlamningShoeShop
 * Copyright: MIT
 */
public class Orders {

    private int id;
    private String date;
    private int customerId;

    public Orders(int id, String date, int customerId) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
    }

    public Orders() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
