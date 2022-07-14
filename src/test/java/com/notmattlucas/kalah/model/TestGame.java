package com.notmattlucas.kalah.model;

import com.notmattlucas.kalah.model.Board;
import com.notmattlucas.kalah.model.Game;
import com.notmattlucas.kalah.model.Player;
import com.notmattlucas.kalah.ui.PrettyPrint;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.notmattlucas.kalah.model.Game.Status.ACTIVE;
import static com.notmattlucas.kalah.model.PlayerNumber.ONE;
import static com.notmattlucas.kalah.model.PlayerNumber.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class TestGame {

    @Test
    void playerOneShouldTakeFirstTurn() {
        Game game = Game.create(Board.create());
        Player player = game.getActivePlayer();
        assertThat(player.num()).isEqualTo(ONE);
    }

    @Test
    void shouldRejectMoveByInactivePlayer() {
        Game game = Game.create(Board.create());
        assertThrows(IllegalStateException.class, () -> game.move(TWO, 1));
    }

    @Test
    void shouldAllowPlayerToSow() {
        Game game = Game.create(Board.create());
        Game.Result result = game.move(ONE, 1);
        var actual =  PrettyPrint.board(result.board());
        assertThat(actual).isEqualTo("""
                       Player Two
             | 04 | 04 | 04 | 04 | 04 | 04 |
        (00)                                 (00)
             | 00 | 05 | 05 | 05 | 05 | 04 |
                       Player One
        """);
        assertThat(result.status()).isEqualTo(ACTIVE);
    }

    @Test
    void shouldAllowAlternateTurns() {
        Game game = Game.create(Board.create());

        Game.Result result = game.move(ONE, 1);
        var actual =  PrettyPrint.board(result.board());
        assertThat(actual).isEqualTo("""
                       Player Two
             | 04 | 04 | 04 | 04 | 04 | 04 |
        (00)                                 (00)
             | 00 | 05 | 05 | 05 | 05 | 04 |
                       Player One
        """);
        assertThat(result.next()).isEqualTo(TWO);

        result = game.move(TWO, 1);
        actual =  PrettyPrint.board(result.board());
        assertThat(actual).isEqualTo("""
                       Player Two
             | 04 | 05 | 05 | 05 | 05 | 00 |
        (00)                                 (00)
             | 00 | 05 | 05 | 05 | 05 | 04 |
                       Player One
        """);
        assertThat(result.next()).isEqualTo(ONE);

        assertThat(result.status()).isEqualTo(ACTIVE);
    }

    @Test
    void shouldGetAnotherTurnWhenLandingInOwnStore() {
        Game game = Game.create(Board.create());
        Game.Result result = game.move(ONE, 3);
        var actual =  PrettyPrint.board(result.board());
        assertThat(actual).isEqualTo("""
                       Player Two
             | 04 | 04 | 04 | 04 | 04 | 04 |
        (00)                                 (01)
             | 04 | 04 | 00 | 05 | 05 | 05 |
                       Player One
        """);
        assertThat(result.next()).isEqualTo(ONE);
        assertThat(result.status()).isEqualTo(ACTIVE);
    }

    @Test
    void shouldCaptureOppositeWhenLandingInOwnEmptyHouse() {
        var board = Board.from(
            List.of(4, 4, 4, 4, 0, 5),
            1,
            List.of(5, 5, 4, 4, 4, 4),
            0
        );
        Game game = Game.create(board);
        var actual =  PrettyPrint.board(board);
        assertThat(actual).isEqualTo("""
                       Player Two
             | 04 | 04 | 04 | 04 | 05 | 05 |
        (00)                                 (01)
             | 04 | 04 | 04 | 04 | 00 | 05 |
                       Player One
        """);

        Game.Result result = game.move(ONE, 1);
        actual =  PrettyPrint.board(result.board());
        assertThat(actual).isEqualTo("""
                       Player Two
             | 04 | 04 | 04 | 04 | 00 | 05 |
        (00)                                 (07)
             | 00 | 05 | 05 | 05 | 00 | 05 |
                       Player One
        """);
    }

    @Test
    void shouldFinishGameWhenOnePlayerHasNoSeeds() {
        var board = Board.from(
                List.of(0, 0, 0, 0, 0, 1),
                24,
                List.of(4, 4, 4, 4, 4, 3),
                0
        );
        Game game = Game.create(board);
        var actual =  PrettyPrint.board(board);
        assertThat(actual).isEqualTo("""
                       Player Two
             | 03 | 04 | 04 | 04 | 04 | 04 |
        (00)                                 (24)
             | 00 | 00 | 00 | 00 | 00 | 01 |
                       Player One
        """);

        Game.Result result = game.move(ONE, 6);
        Board.Players players = board.getPlayers();
        assertThat(players.player1().score()).isEqualTo(25);
        assertThat(players.player2().score()).isEqualTo(23);
        assertThat(result.status()).isEqualTo(Game.Status.PLAYER_ONE_WIN);
    }

    @Test
    void shouldAllowDrawWhenSameSeeds() {
        var board = Board.from(
                List.of(0, 0, 0, 0, 0, 1),
                23,
                List.of(4, 4, 4, 4, 4, 4),
                0
        );
        Game game = Game.create(board);
        var actual =  PrettyPrint.board(board);
        assertThat(actual).isEqualTo("""
                       Player Two
             | 04 | 04 | 04 | 04 | 04 | 04 |
        (00)                                 (23)
             | 00 | 00 | 00 | 00 | 00 | 01 |
                       Player One
        """);

        Game.Result result = game.move(ONE, 6);
        Board.Players players = board.getPlayers();
        assertThat(players.player1().score()).isEqualTo(24);
        assertThat(players.player2().score()).isEqualTo(24);
        assertThat(result.status()).isEqualTo(Game.Status.DRAW);
    }

    @Test
    void shouldPlayCompleteGame() {
        fail();
    }

}
