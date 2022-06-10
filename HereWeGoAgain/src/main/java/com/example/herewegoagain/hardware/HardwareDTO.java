package com.example.herewegoagain.hardware;

public class HardwareDTO {
    private String index;
    private String name;
    private Double price;

    public HardwareDTO(String index , String name, Double price) {
        this.index = index;
        this.name = name;
        this.price = price;
    }
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



}

