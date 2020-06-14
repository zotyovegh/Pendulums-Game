package com.example.pendulumtestjava;

import com.example.pendulumtestjava.fragments.pendulumFragments.repositories.DoublePModelRepo;
import com.example.pendulumtestjava.fragments.pendulumFragments.repositories.SinglePModelRepo;
import com.example.pendulumtestjava.fragments.pendulumFragments.viewModels.Pendulums.DoublePendulumViewModel;
import com.example.pendulumtestjava.fragments.pendulumFragments.viewModels.Pendulums.SinglePendulumViewModel;
import com.example.pendulumtestjava.fragments.pendulumFragments.views.DrawingPathView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class JUnitTest {
    private DrawingPathView drawingView;
    private DoublePendulumViewModel doublePVM;
    private SinglePendulumViewModel singlePVM;
    private DoublePModelRepo doublePModelRepo;
    private SinglePModelRepo singlePModelRepo;

    @Before
    public void setUp() {
        drawingView = new DrawingPathView(null);
        doublePVM = new DoublePendulumViewModel();
        singlePVM = new SinglePendulumViewModel();
        doublePModelRepo = DoublePModelRepo.getInstance();
        singlePModelRepo = SinglePModelRepo.getInstance();
    }

    @Test
    public void testFirstBallGetSizeCorrecter() {
        doublePModelRepo.setM1(40);
        assertEquals(6, doublePVM.getSizeCorrecter(true));
    }

    @Test
    public void testFirstBallGetSizeCorrecterWithZero() {
        doublePModelRepo.setM1(0);
        assertEquals(-10, doublePVM.getSizeCorrecter(true));
    }

    @Test
    public void testSecondBallGetSizeCorrecter() {
        doublePModelRepo.setM2(30);
        assertEquals(2, doublePVM.getSizeCorrecter(false));
    }

    @Test
    public void testSecondBallGetSizeCorrecterWithZero() {
        doublePModelRepo.setM2(0);
        assertEquals(-10, doublePVM.getSizeCorrecter(false));
    }

    @Test
    public void testFirstBallGetBallSize() {
        doublePModelRepo.setM1(40);
        assertEquals(72, doublePVM.getBallSize(true));
    }

    @Test
    public void testFirstBallGetBallSizeWithZero() {
        doublePModelRepo.setM1(0);
        assertEquals(40, doublePVM.getBallSize(true));
    }

    @Test
    public void testSecondBallGetBallSize() {
        doublePModelRepo.setM2(30);
        assertEquals(64, doublePVM.getBallSize(false));
    }

    @Test
    public void testSecondBallGetBallSizeWithZero() {
        doublePModelRepo.setM2(0);
        assertEquals(40, doublePVM.getBallSize(false));
    }

    @Test
    public void testResetDoubleValues() {
        doublePModelRepo.resetValues();

        assertEquals(200, doublePModelRepo.getR1(), 0);
        assertEquals(250, doublePModelRepo.getR2(), 0);
        assertEquals(Math.PI / 2, doublePModelRepo.getA1(), 0);
        assertEquals(Math.PI / 2, doublePModelRepo.getA2(), 0);
        assertEquals((float) 0.98, doublePModelRepo.getG(), 1);
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
    public void testResetSingleValues() {
        singlePModelRepo.resetValues();

        assertEquals(100, singlePModelRepo.getTrace());
        assertEquals((float) 0.981, singlePModelRepo.getGravity(), 1);
        assertEquals((float) 0.999, singlePModelRepo.getDamping(), 0);
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

    @Test
    public void testConvertDifferentSize() {
        float[] array = new float[4];
        array[0] = (float) 4.3;
        array[1] = (float) 3.6;

        ArrayList<Float> list = new ArrayList<>();
        list.add((float) 4.3);

        assertNotEquals(Arrays.toString(array), Arrays.toString(drawingView.convert(list)));
    }

    @Test
    public void testConvertDifferentValue() {
        float[] array = new float[4];
        array[0] = (float) 4.3;

        ArrayList<Float> list = new ArrayList<>();
        list.add((float) 4.9);

        assertNotEquals(Arrays.toString(array), Arrays.toString(drawingView.convert(list)));
    }

    @Test
    public void testConvertWithEmptyArray() {
        float[] array = new float[0];
        ArrayList<Float> list = new ArrayList<>();
        assertArrayEquals(array, drawingView.convert(list), 0);
    }

    @Test
    public void testSinglePositionCals() {
        singlePVM.defineVariables(200, 300, null, null);
        singlePModelRepo.setA(100);
        singlePModelRepo.setR(200);
        singlePVM.calcPositions();
        assertEquals(470, singlePVM.getX(), 0);
        assertEquals(270, singlePVM.getY(), 0);
    }

    @Test
    public void testDoublePositionCals() {
        doublePVM.defineVariables(200, 300, null, null, null);
        doublePModelRepo.setA1(100);
        doublePModelRepo.setA2(100);
        doublePModelRepo.setR1(200);
        doublePModelRepo.setR2(300);
        doublePVM.calcPositions();
        assertEquals(370, doublePVM.getX1(), 0);
        assertEquals(620, doublePVM.getX2(), 0);
        assertEquals(270, doublePVM.getY1(), 0);
        assertEquals(270, doublePVM.getY2(), 0);
    }
}