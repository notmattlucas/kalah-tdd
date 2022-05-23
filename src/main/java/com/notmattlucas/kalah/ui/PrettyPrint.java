package com.notmattlucas.kalah.ui;

import com.notmattlucas.kalah.model.Board;
import com.notmattlucas.kalah.model.House;
import com.notmattlucas.kalah.model.Pit;
import com.notmattlucas.kalah.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface PrettyPrint {

    String TEMPLATE = """
                       Player Two        
             | %02d | %02d | %02d | %02d | %02d | %02d |
        (%02d)                                 (%02d)
             | %02d | %02d | %02d | %02d | %02d | %02d |
                       Player One
        """;

    static String board(Board board) {
        Player player1 = board.getPlayers().player1();
        Player player2 = board.getPlayers().player2();
        List<House> p2Rev = new ArrayList<>(player2.getHouses());

        Collections.reverse(p2Rev);
        List<Pit> pits = new ArrayList<>(p2Rev);
        pits.add(player2.getStore());
        pits.add(player1.getStore());
        pits.addAll(player1.getHouses());

        return String.format(TEMPLATE, pits.stream()
                .map(Pit::count)
                .toList()
                .toArray());
    }

}
