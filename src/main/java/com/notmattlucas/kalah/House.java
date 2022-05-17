package com.notmattlucas.kalah;

public class House extends Pit {

    public House(int seeds) {
        super(seeds);
    }

    public Integer take() {
        int seeds = this.seeds;
        this.seeds = 0;
        return seeds;
    }

}
