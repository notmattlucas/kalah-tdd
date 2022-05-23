package com.notmattlucas.kalah.model;

public class House extends Pit {

    private House opposite;

    public House(PlayerNumber owner, int seeds) {
        super(owner, seeds);
    }

    public Integer take() {
        int seeds = this.seeds;
        this.seeds = 0;
        return seeds;
    }

    @Override
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

    public House getOpposite() {
        return opposite;
    }

    public void setOpposite(House opposite) {
        this.opposite = opposite;
    }
}
