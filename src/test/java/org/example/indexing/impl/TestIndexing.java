package org.example.indexing.impl;

import org.example.indexing.IndexApi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Проверяет полный алгоритм преобразования масива строк в массив упорядоченных группы чисел.
 */
public class TestIndexing {
    private IndexApi indexTool;

    @Before
    public void before() {
        indexTool = new IndexTool();
    }

    /**
     * 1 3 4 5
     * 2
     * 3 4
     */
    @Test
    public void indexingTest1() {
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1, 2, 3));
        expected.add(Arrays.asList(1, 2, 4));
        expected.add(Arrays.asList(3, 2, 3));
        expected.add(Arrays.asList(3, 2, 4));
        expected.add(Arrays.asList(4, 2, 3));
        expected.add(Arrays.asList(4, 2, 4));
        expected.add(Arrays.asList(5, 2, 3));
        expected.add(Arrays.asList(5, 2, 4));

        String[] source = {"1,3-5", "2", "3-4"};
        List<List<Integer>> ranges = indexTool.convert(source);

        Assert.assertEquals(expected, indexTool.makeCombinations(ranges));
    }

    @Test
    public void indexingTest2() {
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

        String[] source = {"1,3-5", "3,4", "3-4"};
        List<List<Integer>> ranges = indexTool.convert(source);

        Assert.assertEquals(expected, indexTool.makeCombinations(ranges));
    }

    @Test
    public void indexingEmptyTest() {
        String[] source = {};
        List<List<Integer>> ranges = indexTool.convert(source);

        Assert.assertEquals(new ArrayList<List<Integer>>(), indexTool.makeCombinations(ranges));
    }
}
