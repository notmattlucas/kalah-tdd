package com.notmattlucas.kalah;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.notmattlucas.kalah.PlayerNumber.*;

class TestPlayer {
    @Test
    void playerShouldSowSeedsOnTurn() {
        House last = new House(ONE, 0);
        House middle = new House(ONE,0);
        House first = new House(ONE,2);
        Store end = new Store(ONE);

        first.setNext(middle).setNext(last).setNext(end);

        Player player = new Player(ONE, List.of(first, middle, last), end);
        Pit landed = player.turn(1);

        assertThat(landed).isEqualTo(last);
        assertThat(first.count()).isZero();
        assertThat(middle.count()).isEqualTo(1);
        assertThat(last.count()).isEqualTo(1);
    }

    @Test
    void playerCannotChooseEmptyHouse() {
        assertThrows(IllegalArgumentException.class, () -> {
            Player player = new Player(ONE, List.of(new House(ONE, 0)), new Store(ONE));
            player.turn(1);
        });
    }

    @Test
    void playerCannotChooseHouseBelowRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            Player player = new Player(ONE, List.of(new House(ONE, 0)), new Store(ONE));
            player.turn(0);
        });
    }

    @Test
    void playerCannotChooseHouseAboveRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            Player player = new Player(ONE, List.of(new House(ONE, 0)), new Store(ONE));
            player.turn(2);
        });
    }

    @Test
    void playerCannotSowOpponentsStore() {
        House myHouse = new House(ONE,3);
        Store myStore = new Store(ONE);
        House opponentHouse = new House(TWO,0);
        Store opponentStore = new Store(TWO);

        myHouse.setNext(myStore)
            .setNext(opponentHouse)
            .setNext(opponentStore)
            .setNext(myHouse);

        Player player = new Player(ONE, List.of(myHouse), myStore);
        Pit landed = player.turn(1);

        assertThat(landed).isEqualTo(myHouse);
        assertThat(myHouse.count()).isEqualTo(1);
        assertThat(myStore.count()).isEqualTo(1);
        assertThat(opponentHouse.count()).isEqualTo(1);
        assertThat(opponentStore.count()).isZero();
    }

}
