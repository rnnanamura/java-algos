package com.seir.algos.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringUtils {

    public static boolean isPalindrome(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        char[] chars = str.toCharArray();
        int firstIdx = 0;
        int secondIdx = str.length() - 1;
        while (firstIdx < secondIdx) {
            if (chars[firstIdx] != chars[secondIdx]) {
                return false;
            }
            firstIdx++;
            secondIdx--;
        }

        return true;
    }

    public static List<String> findAcronyms(String base, String value) {
        List<String> result = new ArrayList<>();
        if (base == null || base.isEmpty() || value == null || value.isEmpty()) {
            return result;
        }
        Map<Character, Integer> baseMap = new HashMap<>();
        for (char c: base.toCharArray()) {
            baseMap.put(c, baseMap.getOrDefault(c, 0) + 1);
        }
        int baseSize = base.length();
        Map<Character, Integer> currentMap = new HashMap<>();
        List<Character> acronym = new ArrayList<>();
        for (char c: value.toCharArray()) {
            if (baseMap.containsKey(c)) {
                currentMap.put(c, currentMap.getOrDefault(c, 0) + 1);
                if (currentMap.get(c) > baseMap.get(c)) {
                    currentMap.clear();
                } else {
                    acronym.add(c);
                    if (acronym.size() == baseSize) {
                        result.add(acronym.stream().map(String::valueOf).collect(Collectors.joining()));
                        acronym.clear();
                        currentMap.clear();
                    }
                }
            } else {
                currentMap.clear();
                acronym.clear();
            }
        }
        return result;
    }

    public static String longestSubstringNonRepeting(String value) {
        StringBuilder result = new StringBuilder();
        if (value == null || value.isEmpty()) {
            return result.toString();
        }
        Map<Character, Integer> charCountMap = new HashMap<>();
        int start = 0;
        int end = 0;
        int maxLength = 0;
        while (end < value.length()) {
            char currentChar = value.charAt(end);
            if (charCountMap.containsKey(currentChar)) {
                if (end - start > maxLength) {
                    maxLength = end - start;
                    result = new StringBuilder(value.substring(start, end));
                }
                charCountMap.clear();
                start = end;
            }
            end++;
            charCountMap.put(currentChar, 1);
        }
        if (!charCountMap.isEmpty() && end - start > maxLength) {
            result = new StringBuilder(value.substring(start, end));
        }
        return result.toString();
    }
}
