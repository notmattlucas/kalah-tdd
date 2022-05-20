package com.notmattlucas.kalah;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.notmattlucas.kalah.PlayerNumber.ONE;
import static com.notmattlucas.kalah.PlayerNumber.TWO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TestBoard {

    @Test
    void boardShouldHaveTwelveHouses() {
        Board board = Board.create();
        List<House> houses = board.getHouses();
        Map<PlayerNumber, List<House>> sides = houses.stream().collect(Collectors.groupingBy(House::getOwner));
        assertThat(sides.get(ONE).size()).isEqualTo(6);
        assertThat(sides.get(TWO).size()).isEqualTo(6);
    }

    @Test
    void boardShouldHaveTwoStores() {
        Board board = Board.create();
        List<Store> stores = board.getStores();
        Map<PlayerNumber, List<Store>> sides = stores.stream().collect(Collectors.groupingBy(Store::getOwner));
        assertThat(sides.get(ONE).size()).isEqualTo(1);
        assertThat(sides.get(TWO).size()).isEqualTo(1);
    }

    @Test
    void boardShouldHaveTwoPlayers() {
        Board board = Board.create();
        Players players = board.getPlayers();
        assertThat(players.player1().getNum()).isEqualTo(ONE);
        assertThat(players.player2().getNum()).isEqualTo(TWO);
    }

    @Test
    void housesShouldHaveMutualOpposites() {
        Board board = Board.create();
        Players players = board.getPlayers();
        List<House> housesOne = players.player1().getHouses();
        List<House> housesTwo = players.player2().getHouses();
        for (int i = 0; i < housesOne.size(); i++) {
            var houseOne = housesOne.get(i);
            var houseTwo = housesTwo.get(housesTwo.size() - i - 1);
            assertThat(houseOne.getOpposite()).isEqualTo(houseTwo);
            assertThat(houseTwo.getOpposite()).isEqualTo(houseOne);
        }
    }

}
