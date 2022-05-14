package com.notmattlucas.kalah;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestPit {

    @Test
    public void shouldStoreSeeds() {
        Pit pit = new Pit(4);
        assertThat(pit.count()).isEqualTo(4);
    }

}
