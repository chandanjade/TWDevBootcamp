package com.bootcamp.salestaxproblem;

import org.junit.Assert;
import org.junit.Test;

public class ItemTest {

    @Test
    public void createNewNonTaxableItem() {
        Item item = new Item("book", 12.49, Item.NON_TAXABLE);
        Assert.assertEquals("book", item.getName());
        Assert.assertEquals(12.49, item.getPrice(),0.0);
    }

    @Test
    public void getFinalPriceForNonTaxableItem() {
        Item item = new Item("book", 12.49, Item.NON_TAXABLE);
        Assert.assertEquals(12.49, item.getFinalPrice(), 0.0);
    }

    @Test
    public void createNewTaxableItem() {
        boolean isTaxble = true;
        Item item = new Item("music", 14.99, isTaxble);
        Assert.assertEquals(true, item.isTaxable());
    }

    @Test
    public void getFinalPriceForTaxableItem() {
        Item item = new Item("music", 14.99, Item.TAXABLE);
        Assert.assertEquals(16.49, item.getFinalPrice(), 0.05);
    }

    @Test
    public void createNewImportedItem() {
        Item item = new Item("book", 12.49, Item.NON_TAXABLE);
        item.setImported(true);
        Assert.assertEquals(true, item.isImported());
    }

    @Test
    public void getFinalPriceForImportedItem() {
        Item item = new Item("book", 12.49, Item.NON_TAXABLE);
        item.setImported(true);
        Assert.assertEquals(13.11, item.getFinalPrice(), 0.05);
    }

    @Test
    public void getFinalPriceForImportedTaxableItem() {
        Item item = new Item("Music", 14.99, Item.TAXABLE);
        item.setImported(true);
        Assert.assertEquals(17.24, item.getFinalPrice(), 0.05);
    }

    @Test
    public void getFinalPriceForListOfItems() {
        Item book = new Item("book", 12.49, Item.NON_TAXABLE);
        Item music = new Item("Music", 14.99, Item.TAXABLE);
        music.setImported(true);
        Cart cart = new Cart();
        cart.addItem(book);
        cart.addItem(music);
        Assert.assertEquals(29.73, cart.getTotalPrice(), 0.05);
    }

    @Test
    public void getFinalPriceForListOfItems2() {
        Item book = new Item("book", 12.49, Item.NON_TAXABLE);
        Item music = new Item("Music", 14.99, Item.TAXABLE);
        Item chocolate = new Item("chocolate", 10, Item.NON_TAXABLE);
        music.setImported(true);
        Cart cart = new Cart();
        cart.addItem(book);
        cart.addItem(music);
        cart.addItem(chocolate);
        Assert.assertEquals(39.73, cart.getTotalPrice(), 0.05);
    }
}
