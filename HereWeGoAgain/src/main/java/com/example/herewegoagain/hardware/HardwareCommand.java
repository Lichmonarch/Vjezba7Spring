package com.example.herewegoagain.hardware;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class HardwareCommand {

    @NotBlank(message = "Index must be entered.")
    private String index;

    @NotBlank(message = "Name must be entered.")
    private String name;

    @NotNull(message = "Price must be entered.")
    private Double price;

    @NotBlank(message = "Type must be entered.")
    private String type;

    @NotNull(message = "Stock must be entered.")
    private Integer inStock;

    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }

    public Double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }
}