package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.home;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class HomeViewModelTest {

    private HomeViewModel homeViewModel;
    Long beforeTime = System.currentTimeMillis();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        homeViewModel = new HomeViewModel(beforeTime);
    }

    @Test
    public void calculateTime() {
        Long afterTime = System.currentTimeMillis();
        Long actual = homeViewModel.calculateTime(afterTime, beforeTime, "test");
        Long expected = afterTime - beforeTime;
        assertEquals(expected, actual);
    }
}