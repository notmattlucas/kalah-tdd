package com.notmattlucas.kalah;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestPlayer {

    @Test
    public void playerShouldSowSeedsOnTurn() {
        House last = new House(0);
        House middle = new House(0);
        House first = new House(2);
        EndZone end = new EndZone();

        first.setNext(middle);
        middle.setNext(last);
        last.setNext(end);

        Player player = new Player(List.of(first, middle, last), end);
        Pit landed = player.turn(1);

        assertThat(landed).isEqualTo(last);
        assertThat(first.count()).isEqualTo(0);
        assertThat(middle.count()).isEqualTo(1);
        assertThat(last.count()).isEqualTo(1);
    }

    @Test
    public void playerCannotChooseEmptyHouse() {
        assertThrows(IllegalArgumentException.class, () -> {
            Player player = new Player(List.of(new House(0)), new EndZone());
            player.turn(1);
        });
    }

    @Test
    public void playerCannotChooseHouseBelowRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            Player player = new Player(List.of(new House(0)), new EndZone());
            player.turn(0);
        });
    }

    @Test
    public void playerCannotChooseHouseAboveRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            Player player = new Player(List.of(new House(0)), new EndZone());
            player.turn(2);
        });
    }

}
