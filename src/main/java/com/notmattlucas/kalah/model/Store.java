package com.notmattlucas.kalah.model;

public class Store extends Pit {

    Store(PlayerNumber owner) {
        super(owner, 0);
    }

    public void sow(int i) {
        seeds += i;
    }

    boolean isSowable(PlayerNumber player) {
        return player.equals(owner);
    }

    @Override
    public String toString() {
        return "Store{" +
                "seeds=" + seeds +
                ", owner=" + owner +
                '}';
    }

}
