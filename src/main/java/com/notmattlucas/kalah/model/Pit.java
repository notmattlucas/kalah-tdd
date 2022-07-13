package com.notmattlucas.kalah.model;

import java.util.Optional;

public abstract class Pit {

    protected int seeds;

    private Pit next;

    protected final PlayerNumber owner;

    Pit(PlayerNumber owner, int seeds) {
        this.owner = owner;
        this.seeds = seeds;
    }

    public Integer count() {
        return seeds;
    }

    public Pit next() {
        return next;
    }

    public Pit setNext(Pit next) {
        this.next = next;
        return next;
    }

    public void sow() {
        this.seeds++;
    }

    public PlayerNumber getOwner() {
        return owner;
    }

    abstract boolean isSowable(PlayerNumber player);

    public boolean isEmpty() {
        return this.seeds == 0;
    }

    public Optional<House> getOpposite() {
        return Optional.empty();
    }

    public Integer capture() {
        if (this.getOpposite().isEmpty()) {
            return 0;
        }
        return this.getOpposite().get().take();
    }

    public Integer take() {
        return 0;
    }

}
