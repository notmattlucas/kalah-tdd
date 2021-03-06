package com.notmattlucas.kalah.model;

public class Game {

    public enum Status {

        ACTIVE,

        DRAW,

        PLAYER_ONE_WIN,

        PLAYER_TWO_WIN

    }

    public record Result(Status status, PlayerNumber next, Board board) {}

    private Board board;

    private Player player;

    private Status status;

    public static Game create(Board board) {
        Game game = new Game();
        game.board = board;
        game.player = board.getPlayers().player1();
        game.status = Status.ACTIVE;
        return game;
    }

    public Result move(PlayerNumber num, int house) {
        if (!player.num().equals(num)) {
            throw new IllegalStateException(String.format("Player %s cannot take their turn yet", num));
        }
        Pit landed = player.turn(house);
        if (player.complete()) {
            otherPlayer().finish();
            status = declareWinner();
        }
        player = nextPlayer(landed);
        return new Result(status, player.num(), board);
    }

    private Status declareWinner() {
        Board.Players players = board.getPlayers();
        int score1 = players.player1().score();
        int score2 = players.player2().score();
        if (score1 > score2) {
            return Status.PLAYER_ONE_WIN;
        }
        if (score2 > score1) {
            return Status.PLAYER_TWO_WIN;
        }
        return Status.DRAW;
    }

    public Player nextPlayer(Pit landed) {
        if (landed.equals(player.store())) {
            return player;
        }
        return otherPlayer();
    }

    private Player otherPlayer() {
        Board.Players players = board.getPlayers();
        return switch (player.num()) {
            case ONE -> players.player2();
            case TWO -> players.player1();
        };
    }

    public Player getActivePlayer() {
        return player;
    }

}
