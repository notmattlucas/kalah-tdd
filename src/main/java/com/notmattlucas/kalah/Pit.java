package com.notmattlucas.kalah;

abstract class Pit {

    protected int seeds;

    protected Pit next;

    public Pit(int seeds) {
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
        return this;
    }

    public void sow() {
        this.seeds++;
    }

    public boolean isEmpty() {
        return this.seeds == 0;
    }

}
