package com.codeTest.analyze_crypto;

public enum Headers {
    CURRENCY(0,"Currency"),
    DATE(1,"Date"),
    OPEN(2,"Open"),
    HIGH(3,"High"),
    LOW(4,"Low"),
    CLOSE(5,"Close"),
    VOLUME(6,"Volume"),
    MARKET_CAP(7,"Market Cap");

    private String name;
    private int index;
    Headers(int index, String name){
        this.index = index;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getIndex(){
        return index;
    }
}
