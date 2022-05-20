package com.notmattlucas.kalah;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.notmattlucas.kalah.PlayerNumber.ONE;
import static com.notmattlucas.kalah.PlayerNumber.TWO;

record Players(Player player1, Player player2) {}

public class Board {

    private List<House> houses;

    private List<Store> stores;

    private Players players;

    private Board() {}

    public static Board create() {
        List<House> housesOne = chain(ONE, 4, 6);
        Store storeOne = new Store(ONE);
        Player playerOne = new Player(ONE, housesOne, storeOne);

        List<House> housesTwo = chain(TWO, 4, 6);
        Store storeTwo = new Store(TWO);
        Player playerTwo = new Player(TWO, housesTwo, storeTwo);

        Board board = new Board();
        board.houses = new ArrayList<>(housesOne);
        board.houses.addAll(housesTwo);

        board.stores = List.of(storeOne, storeTwo);
        board.players = new Players(playerOne, playerTwo);
        return board;
    }

    public List<House> getHouses() {
        return houses;
    }

    public List<Store> getStores() {
        return stores;
    }

    public Players getPlayers() {
        return players;
    }

    private static List<House> chain(PlayerNumber playerNumber, int seeds, int length) {
        LinkedList<House> chain = new LinkedList<>();
        chain.addLast(new House(playerNumber, seeds));
        while (chain.size() < length) {
            House house = new House(playerNumber, seeds);
            chain.getLast().setNext(house);
            chain.addLast(house);
        }
        return chain;
    }

}
