package com.notmattlucas.kalah;

import org.junit.jupiter.api.Test;

class TestGame {

    @Test
    void playerOneShouldTakeFirstTurn() {
        Game game = Game.create(Board.create());
    }

    @Test
    void shouldRejectMoveByInactivePlayer() {

    }



}
