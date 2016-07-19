package edgarhu.hihocoder.competition.race9.B;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 列举出所有的路径存放在list中, list中的每一项为一个hashset, 存放了路径切分后的集合
 * 结果是正确的, 但是超时啊 (ㄒoㄒ)/~~
 */
public class Main {

    public static final int ARRAY_LENGTH = 9;
    public static final Integer[] NUMBER_ARRAY = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static final Integer[][] DIRECT_ACCESS_ARRAY = new Integer[][]{
            {2, 6, 8, 5, 4},
            {1, 4, 5, 6, 3, 7, 9},
            {2, 5, 6, 4, 8},
            {1, 2, 5, 8, 7, 3, 9},
            {1, 2, 3, 4, 6, 7, 8, 9},
            {2, 3, 5, 8, 9, 1, 7},
            {4, 5, 8, 2, 6},
            {7, 4, 5, 6, 9, 1, 3},
            {8, 5, 6, 4, 2}
    };
    public static final Integer[][][] CONDITION_ACCESS_ARRAY = new Integer[][][]{
            {{3, 2}, {9, 5}, {7, 4}},
            {{8, 5}},
            {{1, 2}, {7, 5}, {9, 6}},
            {{6, 5}},
            {},
            {{4, 5}},
            {{1, 4}, {3, 5}, {9, 8}},
            {{2, 5}},
            {{7, 8}, {1, 5}, {3, 6}}
    };

    public static HashSet<Integer> ALL_NUMBERS;
    public static HashSet<Integer>[] DIRECT_ACCESS_NUMBERS;
    public static HashMap<Integer, Integer>[] CONDITION_ACCESS_NUMBERS;
    public static List<HashSet<String>> QUERY_LIST;

    static {
        ALL_NUMBERS = new HashSet<Integer>(Arrays.asList(NUMBER_ARRAY));

        DIRECT_ACCESS_NUMBERS = new HashSet[ARRAY_LENGTH];
        for (int i = 0; i < DIRECT_ACCESS_ARRAY.length; i++) {
            DIRECT_ACCESS_NUMBERS[i] = new HashSet<Integer>(Arrays.asList(DIRECT_ACCESS_ARRAY[i]));
        }

        CONDITION_ACCESS_NUMBERS = new HashMap[ARRAY_LENGTH];
        for (int i = 0; i < CONDITION_ACCESS_ARRAY.length; i++) {
            HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
            for (int j = 0; j < CONDITION_ACCESS_ARRAY[i].length; j++) {
                hashMap.put(CONDITION_ACCESS_ARRAY[i][j][0], CONDITION_ACCESS_ARRAY[i][j][1]);
            }
            CONDITION_ACCESS_NUMBERS[i] = hashMap;
        }

        QUERY_LIST = generateQueryList();   //把所有的组合先计算出来
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();

        for (int i = 0; i < times; i++) {
            int lines = in.nextInt();
            Set<String> pathPairs = new HashSet<String>();
            for (int j = 0; j < lines; j++) {
                int big, little, temp;
                big = in.nextInt();
                little = in.nextInt();
                if (big < little) {
                    temp = big;
                    big = little;
                    little = temp;
                }
                pathPairs.add(little + "" + big);
            }

            int count = 0;
            for (HashSet<String> set : QUERY_LIST) {
                if (set.containsAll(pathPairs)) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }




    public static List<HashSet<String>> generateQueryList() {
        List<HashSet<String>> queryList = new LinkedList<HashSet<String>>();
        for (int i = 4; i <= 9; i++) {
            findPath(new Path(), i, queryList);
        }
        return queryList;
    }

    public static void findPath(Path path, int len, List<HashSet<String>> queryList) {
        Set<Integer> nextNums = nextNumbers(path.peek(), path);
        for (Integer num : nextNums) {
            path.push(num);
            if (len == 1) {
                queryList.add(path.pathSet());
            } else {
                findPath(path, len - 1, queryList);
            }
            path.pop();
        }
    }

    public static Set<Integer> nextNumbers(Integer lastNum, Path path) {
        Set<Integer> nextAccessNums = new HashSet<Integer>();      //下一个可以被访问到的数字集
        if (lastNum == null) {
            nextAccessNums.addAll(ALL_NUMBERS);
            return nextAccessNums;
        }

        Set<Integer> directAccessNums = DIRECT_ACCESS_NUMBERS[lastNum - 1];    //可以直接访问到的数字
        Map<Integer, Integer> conditionMap = CONDITION_ACCESS_NUMBERS[lastNum - 1];
        Set<Integer> conditionAccessNums = new HashSet<Integer>();     //可以间接访问到的数字
        for (Integer integer : conditionMap.keySet()) {
            if (path.contains(conditionMap.get(integer))) {
                conditionAccessNums.add(integer);
            }
        }

        for (Integer integer : directAccessNums) {
            if (!path.contains(integer)) {
                nextAccessNums.add(integer);
            }
        }
        for (Integer integer : conditionAccessNums) {
            if (!path.contains(integer)) {
                nextAccessNums.add(integer);
            }
        }
        return nextAccessNums;
    }

    public static class Path {
        List<Integer> list = new LinkedList<Integer>();
        HashSet<Integer> set = new HashSet<Integer>();

        public void push(Integer integer) {
            list.add(integer);
            set.add(integer);
        }

        public Integer pop() {
            if (list.size() == 0) {
                return null;
            }
            Integer integer = list.remove(list.size() - 1);
            set.remove(integer);
            return integer;
        }

        public Integer peek() {
            if (list.size() == 0) {
                return null;
            }
            return list.get(list.size() - 1);
        }

        public HashSet<String> pathSet() {
            HashSet<String> hashSet = new HashSet<String>();
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i - 1) < list.get(i)) {
                    hashSet.add(list.get(i - 1) + "" + list.get(i));
                } else {
                    hashSet.add(list.get(i) + "" + list.get(i - 1));
                }
            }
            return hashSet;
        }

        public boolean contains(Integer integer) {
            return set.contains(integer);
        }
    }

}
