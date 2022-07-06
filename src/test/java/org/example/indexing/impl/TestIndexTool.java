package org.example.indexing.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Модульные
 */
public class TestIndexTool {

    private static String range = "1-5,7,9-11";
    private IndexTool indexTool;

    @Before
    public void before() {
        indexTool = new IndexTool();
    }

    /**
     * Проверяет преобразование отдельной строки
     */
    @Test
    public void convertLineTest() {
        String range = "1-5,7,9-11";
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 7, 9, 10, 11);

        Assert.assertEquals(expected, indexTool.convertString(range));
    }

    /**
     * Проверяет преобразование отдельной строки, состоящей из одного эелемента
     */
    @Test
    public void convertLineOneValueTest() {
        String range = "1";
        List<Integer> expected = Arrays.asList(1);

        Assert.assertEquals(expected, indexTool.convertString(range));
    }

    /**
     * Проверяет преобразование отдельной пустой строки
     */
    @Test
    public void convertLineEmptyTest() {
        String range = "";
        List<Integer> expected = new ArrayList<>();

        Assert.assertEquals(expected, indexTool.convertString(range));
    }

    /**
     * Проверяет обработку при передаче null.
     */
    @Test
    public void convertLineNullTest() {
        Assert.assertEquals(new ArrayList<>(), indexTool.convertString(null));
    }

    /**
     * Проверяет пребразование массива числовых последовательностей в результирующие упорядоченные
     * группы элементов. На примере массива:
     * <p>
     * 1, 3, 4, 5<br>
     * 3, 4<br>
     * 3, 4
     * </p>
     */
    @Test
    public void makeRangesTest() {
        List<List<Integer>> list = new ArrayList<>();
        list.add(IntStream.of(1, 3, 4, 5).boxed().collect(Collectors.toList()));
        list.add(IntStream.of(3, 4).boxed().collect(Collectors.toList()));
        list.add(IntStream.of(3, 4).boxed().collect(Collectors.toList()));

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1, 3, 3));
        expected.add(Arrays.asList(1, 3, 4));
        expected.add(Arrays.asList(1, 4, 3));
        expected.add(Arrays.asList(1, 4, 4));
        expected.add(Arrays.asList(3, 3, 3));
        expected.add(Arrays.asList(3, 3, 4));
        expected.add(Arrays.asList(3, 4, 3));
        expected.add(Arrays.asList(3, 4, 4));
        expected.add(Arrays.asList(4, 3, 3));
        expected.add(Arrays.asList(4, 3, 4));
        expected.add(Arrays.asList(4, 4, 3));
        expected.add(Arrays.asList(4, 4, 4));
        expected.add(Arrays.asList(5, 3, 3));
        expected.add(Arrays.asList(5, 3, 4));
        expected.add(Arrays.asList(5, 4, 3));
        expected.add(Arrays.asList(5, 4, 4));

        Assert.assertEquals(expected, indexTool.makeCombinations(list));
    }

    /**
     * Проверяет пребразование массива числовых последовательностей, состоящего из одного элемента
     */
    @Test
    public void makeRangesOneElementTest() {
        List<List<Integer>> list = new ArrayList<>();
        list.add(IntStream.of(1).boxed().collect(Collectors.toList()));

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1));

        Assert.assertEquals(expected, indexTool.makeCombinations(list));
    }

    @Test
    public void testReIndex() {
        List<List<Integer>> sourceList = new ArrayList<>();
        sourceList.add(IntStream.of(1, 3, 4, 5).boxed().collect(Collectors.toList()));
        sourceList.add(IntStream.of(1, 3, 5).boxed().collect(Collectors.toList()));
        sourceList.add(IntStream.of(2, 3).boxed().collect(Collectors.toList()));
        sourceList.add(IntStream.of(3, 4).boxed().collect(Collectors.toList()));
        int[] ind = {0,1,1,1};

        int[] expected = {0, 2, 0, 0};
        IndexTool indexTool = new IndexTool();
        Assert.assertArrayEquals(expected, indexTool.reIndex(sourceList, ind));
    }

    @Test
    public void testFullReIndex() {
        List<List<Integer>> sourceList = new ArrayList<>();
        sourceList.add(IntStream.of(1, 3, 4, 5).boxed().collect(Collectors.toList()));
        sourceList.add(IntStream.of(1, 3, 5).boxed().collect(Collectors.toList()));
        sourceList.add(IntStream.of(2, 3).boxed().collect(Collectors.toList()));
        sourceList.add(IntStream.of(3, 4).boxed().collect(Collectors.toList()));
        int[] ind = {3,2,1,1};

        int[] expected = {4, 2, 1, 1};
        IndexTool indexTool = new IndexTool();
        Assert.assertArrayEquals(expected, indexTool.reIndex(sourceList, ind));
    }

    @Test
    public void testEmptyReIndex() {
        List<List<Integer>> sourceList = new ArrayList<>();
        int[] ind = {};

        int[] expected = {};
        IndexTool indexTool = new IndexTool();
        Assert.assertArrayEquals(expected, indexTool.reIndex(sourceList, ind));
    }
}
