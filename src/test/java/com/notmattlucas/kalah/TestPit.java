package com.notmattlucas.kalah;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestPit {

    @Test
    public void shouldStoreSeedsInHouse() {
        Pit house = new House(4);
        assertThat(house.count()).isEqualTo(4);
    }

    @Test
    public void endZoneShouldBeginEmpty() {
        Pit endZone = new EndZone();
        assertThat(endZone.count()).isEqualTo(0);
    }

    @Test
    public void shouldPointToTheNextPit() {
        Pit a = new House(4);
        EndZone b = new EndZone();
        House c = new House(4);

        a.setNext(b);
        b.setNext(c);

        assertThat(a.next()).isEqualTo(b);
        assertThat(b.next()).isEqualTo(c);
    }

    @Test
    public void shouldBeAbleToTakeSeedsFromHouse() {
        House house = new House(4);
        assertThat(house.count()).isEqualTo(4);

        Integer taken = house.take();

        assertThat(taken).isEqualTo(4);
        assertThat(house.count()).isEqualTo(0);
    }

    @Test
    public void shouldBeAbleToSowSeedInHouse() {
        House house = new House(0);
        house.sow();
        assertThat(house.count()).isEqualTo(1);
    }

    @Test
    public void shouldBeAbleToSowSeedInEndZone() {
        EndZone endZone = new EndZone();
        endZone.sow();
        assertThat(endZone.count()).isEqualTo(1);
    }

}
