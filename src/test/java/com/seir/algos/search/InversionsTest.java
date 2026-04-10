package com.seir.algos.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InversionsTest {

    @Test
    public void testSortAndInversions1() {

        Inversions.SortAndInversionResult result = Inversions.sortAndInversionResult(new int[]{1, 2, 3,4 ,5});
        assertEquals(0, result.inversionCount);
    }
    @Test
    public void testSortAndInversions2() {

        Inversions.SortAndInversionResult result = Inversions.sortAndInversionResult(new int[]{5, 4, 3, 2, 1});
        assertEquals(10, result.inversionCount);
    }
    @Test
    public void testSortAndInversions3() {

        Inversions.SortAndInversionResult result = Inversions.sortAndInversionResult(new int[]{1, 3, 5, 2, 4, 6});
        assertEquals(3, result.inversionCount);
    }

}
