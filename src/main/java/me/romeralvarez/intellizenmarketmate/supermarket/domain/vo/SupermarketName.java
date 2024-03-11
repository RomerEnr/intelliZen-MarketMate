package me.romeralvarez.intellizenmarketmate.supermarket.domain.vo;

public class SupermarketName {
    private final String name;

    private SupermarketName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
