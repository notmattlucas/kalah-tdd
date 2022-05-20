package com.notmattlucas.kalah;



public class Store extends Pit {

    public Store(PlayerNumber owner) {
        super(owner, 0);
    }

    @Override
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
