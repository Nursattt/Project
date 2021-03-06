package com.company;

public class Item {
    private Long id;
    private String name;
    private Long price;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public Long getPrice() {return price;}
    public void setPrice(Long price) {this.price = price;}

    public Item( Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Id=" + id + ", Name=" + name + ", Price=" + price;
    }
}