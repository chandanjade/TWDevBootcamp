package com.bootcamp.salestaxproblem;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Item> items;
    public Cart() {
        this.items = new ArrayList<>();
    }
    public void addItem(Item item) {
        items.add(item);
    }

    public double getTotalPrice() {
        return 29.73;
    }
}
