package com.notmattlucas.kalah.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.notmattlucas.kalah.model.PlayerNumber.ONE;
import static com.notmattlucas.kalah.model.PlayerNumber.TWO;

public class Board {

    public record Players(Player player1, Player player2) {}

    private List<House> houses;

    private List<Store> stores;

    private Players players;

    private Board() {}

    public static Board create() {
        return create(4, 6);
    }

    public static Board create(int seeds, int length) {
        LinkedList<House> housesOne = chain(ONE, seeds, length);
        Store storeOne = new Store(ONE);
        Player playerOne = new Player(ONE, housesOne, storeOne);

        LinkedList<House> housesTwo = chain(TWO, 4, 6);
        Store storeTwo = new Store(TWO);
        Player playerTwo = new Player(TWO, housesTwo, storeTwo);

        mutuallyOpposite(housesOne, housesTwo);
        circular(housesOne, storeOne, housesTwo, storeTwo);

        Board board = new Board();
        board.houses = new ArrayList<>(housesOne);
        board.houses.addAll(housesTwo);

        board.stores = List.of(storeOne, storeTwo);
        board.players = new Players(playerOne, playerTwo);
        return board;
    }

    private static void circular(LinkedList<House> housesOne, Store storeOne, LinkedList<House> housesTwo, Store storeTwo) {
        housesOne.getLast().setNext(storeOne);
        storeOne.setNext(housesTwo.getFirst());
        housesTwo.getLast().setNext(storeTwo);
        storeTwo.setNext(housesOne.getFirst());
    }

    private static void mutuallyOpposite(List<House> housesOne, List<House> housesTwo) {
        for (int i=0; i< housesOne.size(); i++) {
            House one = housesOne.get(i);
            House two = housesTwo.get(housesTwo.size() - i - 1);
            one.setOpposite(two);
            two.setOpposite(one);
        }
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

    private static LinkedList<House> chain(PlayerNumber playerNumber, int seeds, int length) {
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
