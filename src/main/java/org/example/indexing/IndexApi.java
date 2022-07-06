package org.example.indexing;

import java.util.List;

public interface IndexApi {
    /**
     * Конвертирует массив строк в массив последовательностей чисел.
     * @param indexes
     *              входящий массив индексов вида {"1,3-5", "2", "3-4"}
     * @return
     *              двумерный список вида [[1, 3, 4, 5], [2], [3,4]]
     */
    List<List<Integer>> convert(String[] indexes);

    /**
     * Возвращает всевозможные уникальные упорядоченные группы элементов полученных массивов чисел.
     * @param ranges
     *              двумерный список вида [[1, 3, 4, 5], [2], [3,4]]
     * @return
     *              сочетания элементов полученных массивов чисел вида: </br>
     *              [[1, 2, 3], [1, 2, 4], [3, 2, 3], [3, 2, 4], [4, 2, 3], [4, 2, 4], [5, 2, 3], [5, 2, 4]]
     */
    List<List<Integer>> makeCombinations(List<List<Integer>> ranges);
}
