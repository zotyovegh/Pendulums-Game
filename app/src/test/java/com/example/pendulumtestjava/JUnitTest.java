package com.example.pendulumtestjava;

import com.example.pendulumtestjava.fragments.pendulumFragments.repositories.DoublePModelRepo;
import com.example.pendulumtestjava.fragments.pendulumFragments.repositories.SinglePModelRepo;
import com.example.pendulumtestjava.fragments.pendulumFragments.viewModels.Pendulums.DoublePendulumViewModel;
import com.example.pendulumtestjava.fragments.pendulumFragments.views.DrawingPathView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class JUnitTest {
    private DrawingPathView drawingView;
    private DoublePendulumViewModel doublePVM;
    private DoublePModelRepo doublePModelRepo;
    private SinglePModelRepo singlePModelRepo;

    @Before
    public void setUp()
    {
        drawingView = new DrawingPathView(null);
        doublePVM = new DoublePendulumViewModel();
        doublePModelRepo = DoublePModelRepo.getInstance();
        singlePModelRepo = SinglePModelRepo.getInstance();
    }

    @Test
    public void testFirstBallGetSizeCorrecter() {
        doublePModelRepo.setM1(40);
        assertEquals(6, doublePVM.getSizeCorrecter(true));
    }

    @Test
    public void testSecondBallGetSizeCorrecter() {
        doublePModelRepo.setM2(30);
        assertEquals(2, doublePVM.getSizeCorrecter(false));
    }

    @Test
    public void testFirstBallGetBallSize() {
        doublePModelRepo.setM1(40);
        assertEquals(72, doublePVM.getBallSize(true));
    }

    @Test
    public void testSecondBallGetBallSize() {
        doublePModelRepo.setM2(30);
        assertEquals(64, doublePVM.getBallSize(false));
    }

    @Test
    public void testResetDoubleValues()
    {
        doublePModelRepo.resetValues();

        assertEquals(200, doublePModelRepo.getR1(), 0);
        assertEquals(250, doublePModelRepo.getR2(), 0);
        assertEquals(Math.PI/2, doublePModelRepo.getA1(), 0);
        assertEquals(Math.PI/2, doublePModelRepo.getA2(), 0);
        assertEquals((float)0.98, doublePModelRepo.getG(), 1);
        assertEquals(40, doublePModelRepo.getM1(), 0);
        assertEquals(30, doublePModelRepo.getM2(), 0);
        assertEquals(100, doublePModelRepo.getTrace1());
        assertEquals(100, doublePModelRepo.getTrace2());
        assertEquals(0xFF0000FF, doublePModelRepo.getTrace1Color());
        assertEquals(0xFF000000, doublePModelRepo.getTrace2Color());
        assertEquals(0xFF0000FF, doublePModelRepo.getBall1Color());
        assertEquals(0xFFFF0000, doublePModelRepo.getBall2Color());
        assertFalse(doublePModelRepo.isEndlessTrace1());
        assertFalse(doublePModelRepo.isEndlessTrace2());
        assertFalse(doublePModelRepo.isTrace1On());
        assertTrue(doublePModelRepo.isTrace2On());
        assertFalse(doublePModelRepo.isStop());
    }

    @Test
    public void testResetSingleValues()
    {
        singlePModelRepo.resetValues();

        assertEquals(100, singlePModelRepo.getTrace());
        assertEquals((float)0.981, singlePModelRepo.getGravity(), 1);
        assertEquals((float)0.999, singlePModelRepo.getDamping(), 0);
        assertEquals(300, singlePModelRepo.getR(), 0);
        assertEquals(Math.PI / 2, singlePModelRepo.getA(), 0);
        assertEquals(0xF0000000, singlePModelRepo.getTraceDrawColor());
        assertEquals(0xFFFF0000, singlePModelRepo.getBallDrawColor());
        assertFalse(singlePModelRepo.isEndlessTrace());
        assertTrue(singlePModelRepo.isTraceOn());
        assertFalse(singlePModelRepo.isStop());
    }

    @Test
    public void testConvert() {
        float[] array = new float[4];
        array[0] = (float) 4.3;
        array[1] = (float) 3.6;
        array[2] = (float) 3.4;
        array[3] = (float) 9.7;

        ArrayList<Float> list = new ArrayList<>();
        list.add((float) 4.3);
        list.add((float) 3.6);
        list.add((float) 3.4);
        list.add((float) 9.7);

        assertArrayEquals(array, drawingView.convert(list), 0);
    }
}
