package com.notmattlucas.kalah.model;

import java.util.Optional;

    public class House extends Pit {

    private House opposite;

    House(PlayerNumber owner, int seeds) {
        super(owner, seeds);
    }

    @Override
    public Integer take() {
        int seeds = this.seeds;
        this.seeds = 0;
        return seeds;
    }

    @Override
    public Optional<House> getOpposite() {
        return Optional.ofNullable(opposite);
    }

    public void setOpposite(House opposite) {
        this.opposite = opposite;
    }

    boolean isSowable(PlayerNumber player) {
        return true;
    }

    @Override
    public String toString() {
        return "House{" +
                "seeds=" + seeds +
                ", owner=" + owner +
                '}';
    }

}
