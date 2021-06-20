package com.example.fs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class MockTest {
    /**
     * スタティックなメソッドをmockにする
     */
    @Test
    public void staticMockTest() {
        LocalDate of = LocalDate.of(2020, 4, 10); //mock作る前にインスタンスを作らないとダメ
        MockedStatic<LocalDate> mockedStatic = Mockito.mockStatic(LocalDate.class);
        mockedStatic.when(LocalDate::now).thenReturn(of);

        LocalDate now = LocalDate.now();
        assertNull(LocalDate.of(2020, 4, 10)); //mockにするとnullにになるらしい

        assertEquals(2020, of.getYear());
        assertEquals(4, of.getMonthValue());
        assertEquals(10, of.getDayOfMonth());
    }
}
