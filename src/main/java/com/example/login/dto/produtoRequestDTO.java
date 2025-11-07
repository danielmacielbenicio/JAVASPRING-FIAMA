package com.example.login.dto;


import jakarta.validation.constraints.NotBlank;

public class produtoRequestDTO {


    @NotBlank(message = "tem nome não ?")
    private String name;
    @NotBlank(message = "é gratis?")
    private double price;
    @NotBlank(message = "tem que ter um pelo menos ")
    private double amount;

    public produtoRequestDTO() {
    }

    public produtoRequestDTO(String name, double price, double amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
