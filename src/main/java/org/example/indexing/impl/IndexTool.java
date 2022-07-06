package org.example.indexing.impl;

import org.example.indexing.IndexApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IndexTool implements IndexApi {

    public List<List<Integer>> convert(String[] indexes) {
        if (indexes == null || indexes.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        for (String s: indexes) {
            result.add(convertString(s));
        }

        return result;
    }

    /**
     * Конвертирует отдльно взятую строку в числовую последовательность
     * @param indexRange
     *              входящая строка вида "1,3-5"
     * @return
     *              список чисел вида [1, 3, 4, 5]
     */
    List<Integer> convertString(String indexRange) {

        if (indexRange == null || "".equals(indexRange)) {
            return new ArrayList<>();
        }

        String[] stringIndexes = indexRange.split(",");
        List<Integer> result = new ArrayList<>();
        for (String s: stringIndexes) {
            String[] split = s.split("-");
            List<Integer> intRange = Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
            int[] ints = IntStream.range(intRange.get(0), (intRange.size() > 1 ? intRange.get(1) : intRange.get(0)) + 1).toArray();
            result.addAll(Arrays.stream(ints).boxed().collect(Collectors.toList()));
        }

        return result;
    }

    public List<List<Integer>> makeCombinations(List<List<Integer>> ranges) {

        if (ranges == null || ranges.isEmpty()) {
            return new ArrayList<>();
        }

        int[] ind = new int[ranges.size()];

        List<List<Integer>> result = new ArrayList<>();

        while (ind[0] < ranges.get(0).size() && ind[ind.length - 1] < ranges.get(ind.length - 1).size()) {
            List<Integer> internalList = new ArrayList<>();
            for (int i = 0; i < ranges.size(); i++) {
                internalList.add(ranges.get(i).get(ind[i]));
            }
            result.add(internalList);
            ind = reIndex(ranges, ind);
        }

        return result;
    }

    /**
     * Вычисляет необходимую для вывода последовательность элементов.
     * @param list
     * @param ind
     * @return
     */
    int[] reIndex(List<List<Integer>> list, int[] ind) {

        if (list == null || list.isEmpty() || ind == null || ind.length == 0) {
            return new int[0];
        }

        if (ind[ind.length - 1] + 1 < list.get(ind.length - 1).size()) {
            ind[ind.length - 1]++;

            return ind;
        }

        int m = ind.length - 1; // маркер
        while (m >= 0 && ind[m] + 1 >= list.get(m).size()) {
            m--;
        }

        if (m == -1) {
            ind[0]++;
            return ind;
        }

        if (m >= 0 && ind[m] + 1 <= list.get(m).size() - 1) {
            ind[m]++;
            for (int i = m+1; i < ind.length; i++) {
                ind[i] = 0;
            }
        }

        return ind;
    }

}
