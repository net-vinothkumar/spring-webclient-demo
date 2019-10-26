package com.InterviewDOT.WebClientDemo;

public class Toy {
    private String toyId;
    private String toyName;
    private long price;

    public Toy(String toyId, String toyName, long price) {
        this.toyId = toyId;
        this.toyName = toyName;
        this.price = price;
    }

    public String getToyId() {
        return toyId;
    }

    public void setToyId(String toyId) {
        this.toyId = toyId;
    }

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Toy() {
    }

    @Override
    public String toString() {
        return "Toy{" +
                "toyName='" + toyName + '\'' +
                '}';
    }
}