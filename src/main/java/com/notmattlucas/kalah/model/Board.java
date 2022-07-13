package com.notmattlucas.kalah.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public static Board create(int numberOfSeeds, int length) {
        var seeds = Stream.generate(() -> numberOfSeeds).limit(length).toList();
        return from(seeds, 0, seeds, 0);
    }

    public static Board from(List<Integer> p1Houses, int p1Store, List<Integer> p2Houses, int p2Store) {
        LinkedList<House> housesOne = buildHouses(ONE, p1Houses);
        LinkedList<House> housesTwo = buildHouses(TWO, p2Houses);
        mutuallyOpposite(housesOne, housesTwo);

        Store storeOne = new Store(ONE, p1Store);
        Store storeTwo = new Store(TWO, p2Store);

        circular(housesOne, storeOne, housesTwo, storeTwo);

        Player playerOne = new Player(ONE, housesOne, storeOne);
        Player playerTwo = new Player(TWO, housesTwo, storeTwo);

        Board board = new Board();
        board.houses = new ArrayList<>(housesOne);
        board.houses.addAll(housesTwo);
        board.stores = List.of(storeOne, storeTwo);
        board.players = new Players(playerOne, playerTwo);

        return board;
    }

    private static LinkedList<House> buildHouses(PlayerNumber playerNumber, List<Integer> seeds) {
        LinkedList<House> houses = new LinkedList<>();
        houses.addLast(new House(playerNumber, seeds.get(0)));
        while (houses.size() < seeds.size()) {
            House house = new House(playerNumber, seeds.get(houses.size()));
            houses.getLast().setNext(house);
            houses.addLast(house);
        }
        return houses;
    }

    private static void mutuallyOpposite(List<House> housesOne, List<House> housesTwo) {
        for (int i=0; i< housesOne.size(); i++) {
            House one = housesOne.get(i);
            House two = housesTwo.get(housesTwo.size() - i - 1);
            one.setOpposite(two);
            two.setOpposite(one);
        }
    }

    private static void circular(LinkedList<House> housesOne, Store storeOne, LinkedList<House> housesTwo, Store storeTwo) {
        housesOne.getLast().setNext(storeOne);
        storeOne.setNext(housesTwo.getFirst());
        housesTwo.getLast().setNext(storeTwo);
        storeTwo.setNext(housesOne.getFirst());
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
}
