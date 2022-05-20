package com.notmattlucas.kalah;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static com.notmattlucas.kalah.PlayerNumber.ONE;

public class TestPit {

    @Test
    public void shouldStoreSeedsInHouse() {
        Pit house = new House(ONE, 4);
        assertThat(house.count()).isEqualTo(4);
    }

    @Test
    public void endZoneShouldBeginEmpty() {
        Pit endZone = new Store(ONE);
        assertThat(endZone.count()).isEqualTo(0);
    }

    @Test
    public void shouldPointToTheNextPit() {
        Pit a = new House(ONE, 4);
        Store b = new Store(ONE);
        House c = new House(ONE,4);

        a.setNext(b);
        b.setNext(c);

        assertThat(a.next()).isEqualTo(b);
        assertThat(b.next()).isEqualTo(c);
    }

    @Test
    public void shouldBeAbleToTakeSeedsFromHouse() {
        House house = new House(ONE,4);
        assertThat(house.count()).isEqualTo(4);

        Integer taken = house.take();

        assertThat(taken).isEqualTo(4);
        assertThat(house.count()).isEqualTo(0);
    }

    @Test
    public void shouldBeAbleToSowSeedInHouse() {
        House house = new House(ONE,0);
        house.sow();
        assertThat(house.count()).isEqualTo(1);
    }

    @Test
    public void shouldBeAbleToSowSeedInEndZone() {
        Store store = new Store(ONE);
        store.sow();
        assertThat(store.count()).isEqualTo(1);
    }

}
