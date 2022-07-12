package com.notmattlucas.kalah.ui;

import com.notmattlucas.kalah.model.Board;
import com.notmattlucas.kalah.ui.PrettyPrint;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestPrettyPrint {

    @Test
    void shouldPrettyPrintBoard() {
        Board board = Board.create();
        String view = PrettyPrint.board(board);
        assertThat(view).isEqualTo("""
                       Player Two
             | 04 | 04 | 04 | 04 | 04 | 04 |
        (00)                                 (00)
             | 04 | 04 | 04 | 04 | 04 | 04 |
                       Player One
        """, view);
    }

}
