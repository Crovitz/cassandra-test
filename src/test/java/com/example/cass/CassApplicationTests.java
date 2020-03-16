package com.example.cass;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CassApplicationTests {

    @Test
    void contextLoads() {
        assertThat(true).isTrue();
    }
}
