package com.seir.algos.search;

import java.util.Arrays;

public class Inversions {

    public static class SortAndInversionResult {
        int[] sortedList;
        int inversionCount;
        public SortAndInversionResult(int[] sortedList, int inversionCount) {
            this.sortedList = sortedList;
            this.inversionCount = inversionCount;
        }
        public SortAndInversionResult() {
            this.sortedList = new int[0];
            this.inversionCount = 0;
        }
    }
    public static SortAndInversionResult sortAndInversionResult(int[] list) {
        if (list == null || list.length == 0) {
            return new SortAndInversionResult(list, 0);
        }
        if (list.length == 1) {
            return new SortAndInversionResult(list, 0);
        }
        int count = list.length;
        int middle = list.length / 2;
        SortAndInversionResult lowInversionResult = sortAndInversionResult(Arrays.copyOfRange(list, 0, middle));
        SortAndInversionResult highInversionResult = sortAndInversionResult(Arrays.copyOfRange(list, middle, count));
        SortAndInversionResult jointInversionResult = sortAndInversionResult(lowInversionResult.sortedList, highInversionResult.sortedList);


        return new SortAndInversionResult(jointInversionResult.sortedList,
                lowInversionResult.inversionCount + highInversionResult.inversionCount + jointInversionResult.inversionCount);
    }
    private static SortAndInversionResult sortAndInversionResult(int[] firstList, int[] secondList) {
        SortAndInversionResult result = new SortAndInversionResult();
        if ((firstList == null || firstList.length == 0) && (secondList == null || secondList.length == 0)) {
            return result;
        }
        if (firstList == null || firstList.length == 0) {
            result.sortedList = secondList;
            return result;
        }
        if (secondList == null || secondList.length == 0) {
            result.sortedList = firstList;
            return result;
        }
        int i = 0;
        int j = 0;
        int inversionCount = 0;
        int[] sortedList = new int[firstList.length + secondList.length];
        for (int k = 0; k < firstList.length + secondList.length; k++) {
            if (i == firstList.length && j < secondList.length) {
                sortedList[k] = secondList[j];
                j++;
                continue;
            }
            if (j == secondList.length && i < firstList.length) {
                sortedList[k] = firstList[i];
                i++;
                continue;
            }
            if (firstList[i] > secondList[j]) {
                int iComp = i;
                while (iComp < firstList.length && firstList[iComp] > secondList[j]) {
                    inversionCount++;
                    iComp++;
                }
                sortedList[k] = secondList[j];
                j++;
            } else {
                sortedList[k] = firstList[i];
                i++;
            }
        }

        return new SortAndInversionResult(sortedList, inversionCount);
    }

    private static SortAndInversionResult sortAndInversionResult2(int[] firstList, int[] secondList) {
        SortAndInversionResult result = new SortAndInversionResult();
        if ((firstList == null || firstList.length == 0) && (secondList == null || secondList.length == 0)) {
            return result;
        }
        if (firstList == null || firstList.length == 0) {
            result.sortedList = secondList;
            return result;
        }
        if (secondList == null || secondList.length == 0) {
            result.sortedList = firstList;
            return result;
        }
        int i = 0;
        int j = 0;
        int iComp = 0;
        int inversionCount = 0;
        int[] sortedList = new int[firstList.length + secondList.length];
        for (int k = 0; k < firstList.length + secondList.length; k++) {
            if (i == firstList.length && j < secondList.length) {
                sortedList[k] = secondList[j];
                j++;
                continue;
            }
            if (j == secondList.length && i < firstList.length) {
                sortedList[k] = firstList[i];
                i++;
                continue;
            }
            if (iComp < firstList.length && firstList[iComp] > secondList[0]) {
                for (int l = 0; l < secondList.length; l++) {
                    if (firstList[iComp] > secondList[l]) {
                        inversionCount++;
                    } else {
                        break;
                    }
                }
                //iComp++;
            }
            if (firstList[i] > secondList[j]) {
                sortedList[k] = secondList[j];
                j++;
                iComp++;

            } else {
                sortedList[k] = firstList[i];
                i++;
                iComp++;
            }
        }

        return new SortAndInversionResult(sortedList, inversionCount);
    }

}
