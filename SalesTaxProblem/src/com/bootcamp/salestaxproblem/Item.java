package com.bootcamp.salestaxproblem;

public class Item {
    public static final boolean NON_TAXABLE = false;
    public static final boolean TAXABLE = true;

    private String name;
    private double price;

    private boolean taxable;
    private boolean imported;


    public Item(String name, double price, boolean isTaxable) {
        this.name = name;
        this.price = price;
        this.taxable = isTaxable;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getFinalPrice() {
        return price + salesTax() + importDuty();
    }

    private double importDuty() {
        return imported ? 0.05 * price : 0;
    }

    private double salesTax() {
        return taxable ? 0.1 * price : 0;
    }

    public boolean isTaxable() {
        return taxable;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public boolean isImported() {
        return imported;
    }
}
