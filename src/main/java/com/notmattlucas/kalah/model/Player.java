package com.notmattlucas.kalah.model;

import java.util.List;

public class Player {

    private final List<House> houses;

    private final Store store;

    private final PlayerNumber num;

    public Player(PlayerNumber num, List<House> houses, Store store) {
        this.num = num;
        this.houses = houses;
        this.store = store;
    }

    public Pit turn(int houseNum) {
        House house = getHouse(houseNum);
        checkHasSeeds(house);
        return takeTurn(house);
    }

    private void checkHasSeeds(House house) {
        if (house.isEmpty()) {
            throw new IllegalArgumentException("House must have seeds to take turn");
        }
    }

    private Pit takeTurn(House house) {
        Integer seeds = house.take();
        Pit pit = house;
        while (seeds > 0) {
            pit = pit.next();
            if (pit.isSowable(num)) {
                seeds--;
                pit.sow();
            };
        }
        return pit;
    }

    private House getHouse(int houseNum) {
        if (houseNum < 1 || houseNum > houses.size()) {
            throw new IllegalArgumentException("House number must be between 1 and " + houses.size());
        }
        return this.houses.get(houseNum - 1);
    }

    public PlayerNumber getNum() {
        return num;
    }

    public List<House> getHouses() {
        return houses;
    }

    public Store getStore() {
        return store;
    }

}
