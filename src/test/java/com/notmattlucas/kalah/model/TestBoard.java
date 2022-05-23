package com.notmattlucas.kalah.model;

import com.notmattlucas.kalah.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.notmattlucas.kalah.model.PlayerNumber.ONE;
import static com.notmattlucas.kalah.model.PlayerNumber.TWO;
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
        Board.Players players = board.getPlayers();
        assertThat(players.player1().getNum()).isEqualTo(ONE);
        assertThat(players.player2().getNum()).isEqualTo(TWO);
    }

    @Test
    void housesShouldHaveMutualOpposites() {
        Board board = Board.create();
        Board.Players players = board.getPlayers();
        List<House> housesOne = players.player1().getHouses();
        List<House> housesTwo = players.player2().getHouses();
        assertThat(housesOne.get(0).getOpposite()).isEqualTo(housesTwo.get(5));
        assertThat(housesOne.get(1).getOpposite()).isEqualTo(housesTwo.get(4));
        assertThat(housesOne.get(2).getOpposite()).isEqualTo(housesTwo.get(3));
        assertThat(housesOne.get(3).getOpposite()).isEqualTo(housesTwo.get(2));
        assertThat(housesOne.get(4).getOpposite()).isEqualTo(housesTwo.get(1));
        assertThat(housesOne.get(5).getOpposite()).isEqualTo(housesTwo.get(0));
    }

    @Test
    void allPitsShouldFormACycle() {
        Board board = Board.create();
        Pit first = board.getHouses().get(0);
        Pit pit = first;
        for (int i=0; i<14; i++) {
            pit = pit.next();
        }
        assertThat(pit).isEqualTo(first);
    }

}
