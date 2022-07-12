package com.notmattlucas.kalah.model;

import com.notmattlucas.kalah.model.House;
import com.notmattlucas.kalah.model.Pit;
import com.notmattlucas.kalah.model.Store;
import org.junit.jupiter.api.Test;

import static com.notmattlucas.kalah.model.PlayerNumber.ONE;
import static org.assertj.core.api.Assertions.assertThat;

public class TestPit {

    @Test
    public void shouldStoreSeedsInHouse() {
        Pit house = new House(ONE, 4);
        assertThat(house.count()).isEqualTo(4);
    }

    @Test
    public void storeShouldBeginEmpty() {
        Pit endZone = new Store(ONE);
        assertThat(endZone.count()).isEqualTo(0);
    }

    @Test
    public void shouldPointToTheNextPit() {
        Pit a = new House(ONE, 4);
        Store b = new Store(ONE);
        House c = new House(ONE, 4);

        a.setNext(b);
        b.setNext(c);

        assertThat(a.next()).isEqualTo(b);
        assertThat(b.next()).isEqualTo(c);
    }

    @Test
    public void shouldBeAbleToTakeSeedsFromHouse() {
        House house = new House(ONE, 4);
        assertThat(house.count()).isEqualTo(4);

        Integer taken = house.take();

        assertThat(taken).isEqualTo(4);
        assertThat(house.count()).isEqualTo(0);
    }

    @Test
    public void shouldBeAbleToSowSeedInHouse() {
        House house = new House(ONE, 0);
        house.sow();
        assertThat(house.count()).isEqualTo(1);
    }

    @Test
    public void shouldBeAbleToSowSeedInStore() {
        Store store = new Store(ONE);
        store.sow();
        assertThat(store.count()).isEqualTo(1);
    }

    @Test
    public void shouldBeAbleToSowMultipleSeedsInStore() {
        Store store = new Store(ONE);
        store.sow(4);
        assertThat(store.count()).isEqualTo(4);
    }

}
