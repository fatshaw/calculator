package com.fatshaw.learning.refactoring.kata;

import com.fatshaw.learning.refactoring.kata.TestReverse.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;


class MyCalendar {

    TreeMap<Integer, Integer> map = new TreeMap<>();

    public MyCalendar() {

    }

    public boolean book(int start, int end) {
        Integer prev = map.floorKey(start);
        Integer after = map.ceilingKey(start);

        if ((prev == null || map.get(prev) <= start) && (after == null || end <= after)) {
            map.put(start, end);
            return true;
        }
        return false;

    }
}

class RandomizedSet {

    Map<Integer, Integer> map;
    Random random;
    ArrayList<Integer> list;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        map = new HashMap<>();
        random = new Random(System.currentTimeMillis());
        list = new ArrayList<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, list.size());
            list.add(val);
            return true;
        }
        return false;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        if (index != list.size() - 1) {
            list.set(index, list.get(list.size() - 1));
            map.put(list.get(list.size() - 1), index);
        }
        list.remove(list.size() - 1);
        map.remove(val);
        return true;

    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}

class MyCalendarTwo {

    TreeMap<Integer, Integer> map = new TreeMap<>();

    public MyCalendarTwo() {

    }

    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);

        int d = 0;
        for (Integer v : map.values()) {
            d += v;
            if (d == 3) {
                map.put(start, map.get(start) - 1);
                map.put(end, map.get(end) + 1);
                return false;
            }
        }
        return true;
    }
}


class BuildTree {

    public TreeNode buildTree(int[] postorder, int startPostorder, int[] inorder, int startInorder, int endInorder) {
        if (startInorder == endInorder) {
            return null;
        }
        int root = postorder[startPostorder];
        TreeNode treeNode = new TreeNode(root);
        int left = startInorder;
        for (; left < endInorder; left++) {
            if (inorder[left] == root) {
                break;
            }
        }
        treeNode.right = buildTree(postorder, startPostorder - 1, inorder, left + 1, endInorder);
        treeNode.left = buildTree(postorder, startPostorder - endInorder + left, inorder, startInorder, left);
        return treeNode;
    }


    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(postorder, postorder.length - 1, inorder, 0, inorder.length);
    }
}

class Interval {

    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }

    @Override
    public String toString() {
        return "[" + start + "," + end + "]";
    }
}

class NumArray {

    int[] sums;

    public NumArray(int[] nums) {
        sums = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }
}

public class TestReverse {

    @Test
    public void testintersection() {
        assert Arrays.equals(intersection(new int[]{1, 2, 3}, new int[]{1, 2}), new int[]{1, 2});
        assert Arrays.equals(intersection(new int[]{1,2,2,1}, new int[]{2, 2}), new int[]{2});
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int l1 = 0;
        int l2 = 0;
        Set<Integer> result = new HashSet<>();
        while (l1 < nums1.length && l2 < nums2.length) {
            if (nums1[l1] == nums2[l2]) {
                result.add(nums1[l1]);
                l1++;
                l2++;
            } else if (nums1[l1] > nums2[l2]) {
                l2++;
            } else {
                l1++;
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    @Test
    public void testreverseString() {
        assert reverseString("abc").equals("cba");
        assert reverseString("").equals("");
    }

    public String reverseString(String s) {
        char[] c = s.toCharArray();
        int l = 0;
        int r = c.length - 1;
        while (l < r) {
            char t = c[l];
            c[l] = c[r];
            c[r] = t;
            l++;
            r--;
        }
        return String.valueOf(c);
    }

    @Test
    public void testcombinationSum4() {
        assert combinationSum4(new int[]{1, 2, 3}, 4) == 7;
        assert combinationSum4(new int[]{1, 2, 4}, 4) == 6;
        assert combinationSum4(new int[]{1}, 1) == 1;
        assert combinationSum4(new int[]{}, 1) == 0;
        assert combinationSum4(new int[]{3}, 4) == 0;
        assert combinationSum4(new int[]{1, 2, 5}, 4) == 5;

    }

    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int dp[] = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            int j = 0;
            while (j < nums.length && nums[j] <= i) {
                dp[i] += dp[i - nums[j]];
                j++;
            }
        }
        return dp[target];
    }

//    public int combinationSum4Dp(int[] nums, int target, int[] dp) {
//        if (dp[target] >= 0) {
//            return dp[target];
//        }
//
//        int sum = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] <= target) {
//                sum += combinationSum4Dp(nums, target - nums[i], dp);
//            }
//        }
//        dp[target] = sum;
//        return sum;
//    }
//
//    public int combinationSum4(int[] nums, int target) {
//        int[] dp = new int[target + 1];
//        Arrays.fill(dp, -1);
//        dp[0] = 1;
//        return combinationSum4Dp(nums, target, dp);
//    }

    @Test
    public void testnumTrees() {
        assert 1 == numTrees(1);
        assert 2 == numTrees(2);
        assert 5 == numTrees(3);
        assert 14 == numTrees(4);
    }


    public int numTrees(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    @Test
    public void testlongestPalindromeSubseq() {
        assert 3 == longestPalindromeSubseq("aaabbd");
        assert 4 == longestPalindromeSubseq("bbbdb");
    }

    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    @Test
    public void testfindTargetSumWays() {
        assert findTargetSumWays(new int[]{1}, 1) == 1;
        assert findTargetSumWays(new int[]{1}, 2) == 0;
        assert findTargetSumWays(new int[]{1, 2, 2}, 1) == 2;
        assert findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3) == 5;
        assert findTargetSumWays(new int[]{0, 1}, 1) == 2;
        assert findTargetSumWays(new int[]{0, 0, 1}, 1) == 4;
        assert findTargetSumWays(new int[]{0, 0, 0, 0, 0, 0, 1}, 1) == 64;
    }

    public int findTargetSumWays(int[] nums, int S) {
        Map<Integer, Integer> pre = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Map<Integer, Integer> p = new HashMap<>();
            if (i == 0) {
                p.put(nums[i], p.getOrDefault(nums[i], 0) + 1);
                p.put(-nums[i], p.getOrDefault(-nums[i], 0) + 1);
            } else {
                for (Map.Entry<Integer, Integer> pp : pre.entrySet()) {
                    p.put(pp.getKey() + nums[i], p.getOrDefault(pp.getKey() + nums[i], 0) + pp.getValue());
                    p.put(pp.getKey() - nums[i], p.getOrDefault(pp.getKey() - nums[i], 0) + pp.getValue());
                }
            }
            pre = p;
        }
        return pre.getOrDefault(S, 0);
    }

    @Test
    public void testdeleteAndEarn() {
        assert 6 == deleteAndEarn(new int[]{3, 4, 2});
        assert 9 == deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4});

    }

    public int deleteAndEarn(int[] nums) {
        int[] remained = new int[10001];
        for (int i = 0; i < nums.length; i++) {
            remained[nums[i]]++;
        }
        int take = 0;
        int skip = 0;
        int pretake = 0;
        int preskip = 0;
        for (int i = 1; i <= 10000; i++) {
            take = preskip + remained[i] * i;
            skip = Math.max(pretake, preskip);
            preskip = skip;
            pretake = take;
        }
        return Math.max(take, skip);
    }

    @Test
    public void testisSubsequence() {
        assert isSubsequence("abc", "ahbgdc");

        assert !isSubsequence("axc", "ahbgdc");
        assert isSubsequence("ppaab", "paappaab");

    }

    public boolean isSubsequence(String s, String t) {
        int count = 0;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j < t.length()) {
                if (s.charAt(i) == t.charAt(j++)) {
                    count += 1;
                    break;
                }
            }
        }
        return count == s.length();
    }

    @Test
    public void testPredictTheWinner() {
        assert true == PredictTheWinner(new int[]{1});
        assert true == PredictTheWinner(new int[]{0});
        assert true == PredictTheWinner(new int[]{2, 7, 6});

        assert true == PredictTheWinner(new int[]{1, 2, 4, 5});
        assert true == PredictTheWinner(new int[]{1, 3, 2});
        assert true == PredictTheWinner(new int[]{1, 5, 233, 7});

    }


    public boolean PredictTheWinner(int[] nums) {
        int[][] dp = new int[nums.length + 1][nums.length + 1];
        for (int i = nums.length - 1; i >= 0; i--) {
            dp[i][i] = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][nums.length - 1] >= 0;
    }

    @Test
    public void testminSteps() {
        assert minSteps(1) == 0;
        assert minSteps(2) == 2;

        assert minSteps(3) == 3;
        assert minSteps(4) == 4;
        assert minSteps(5) == 5;
        assert minSteps(6) == 5;
        assert minSteps(7) == 7;
        assert minSteps(9) == 6;
        assert minSteps(10) == 7;
        assert minSteps(15) == 8;

    }

    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        int dp[] = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int k = 2; k < i / 2; k++) {
                if (i % k == 0) {
                    dp[i] = dp[i / k] + k;
                    break;
                }
            }
        }
        return dp[n];
    }

    @Test
    public void testcountNumbersWithUniqueDigits() {
        assert countNumbersWithUniqueDigits(0) == 1;
        assert countNumbersWithUniqueDigits(1) == 10;
        assert countNumbersWithUniqueDigits(2) == 91;
        assert countNumbersWithUniqueDigits(3) == 739;
        assert countNumbersWithUniqueDigits(4) == 5275;
        assert countNumbersWithUniqueDigits(5) == 32491;
    }


    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int count = 10;
        int base = 10;
        int unique = 9;
        while (n-- > 1) {
            unique *= (--base);
            count += unique;
        }
        return count;
    }

//    public int countNumbersWithUniqueDigits(int n) {
//        int[] dp = new int[n + 1];
//        int count = 0;
//        for (int i = 1; i <= n; i++) {
//            dp[i] = (int) ((Math.pow(10, i - 1) - Math.pow(10, i - 2) - dp[i - 1]) * (i - 1)
//                + (dp[i - 1]) * 10);
//            count += dp[i];
//        }
//        return  (int) Math.pow(10, n) - count;
//    }

    @Test
    public void testshoppingOffers() {
        assert 14 == shoppingOffers(Arrays.asList(2, 5), Arrays.asList(Arrays.asList(3, 0, 5), Arrays.asList(1, 2, 10)),
            Arrays.asList(3, 2));

        assert 11 == shoppingOffers(Arrays.asList(2, 3, 4),
            Arrays.asList(Arrays.asList(1, 1, 0, 4), Arrays.asList(2, 2, 1, 9)),
            Arrays.asList(1, 2, 1));

        assert 0 == shoppingOffers(Arrays.asList(2, 3, 4),
            Arrays.asList(Arrays.asList(1, 1, 0, 4), Arrays.asList(2, 2, 1, 9)),
            Arrays.asList(0, 0, 0));

        assert 0 == shoppingOffers(Arrays.asList(0, 0, 0),
            Arrays.asList(Arrays.asList(1, 1, 0, 4), Arrays.asList(2, 2, 1, 9)),
            Arrays.asList(1, 1, 1));

        assert 2 == shoppingOffers(Arrays.asList(9, 9),
            Arrays.asList(Arrays.asList(1, 1, 1)),
            Arrays.asList(2, 2));

        assert 10 == shoppingOffers(Arrays.asList(9, 9),
            Arrays.asList(Arrays.asList(1, 2, 1)),
            Arrays.asList(2, 2));

        assert 36 == shoppingOffers(Arrays.asList(9, 9),
            Arrays.asList(Arrays.asList(3, 2, 1)),
            Arrays.asList(2, 2));

        assert 3 == shoppingOffers(Arrays.asList(2, 3),
            Arrays.asList(Arrays.asList(1, 0, 1), Arrays.asList(0, 1, 2)),
            Arrays.asList(1, 1));
    }

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int min = 0;
        for (int i = 0; i < needs.size(); i++) {
            min += price.get(i) * needs.get(i);
        }

        for (List<Integer> supply : special) {
            List<Integer> needCopy = new ArrayList<>(needs);
            int tmp = 0;
            boolean canSupply = true;
            for (int i = 0; i < needCopy.size(); i++) {
                if (supply.get(i) - needCopy.get(i) > 0) {
                    canSupply = false;
                    break;
                }
            }

            if (canSupply) {
                for (int i = 0; i < needCopy.size(); i++) {
                    needCopy.set(i, needCopy.get(i) - supply.get(i));
                }
                tmp += supply.get(supply.size() - 1) + shoppingOffers(price, special, needCopy);
                min = Math.min(min, tmp);
            }
        }

        return min;
    }

    @Test
    public void testintegerBreak() {
        assert 1 == integerBreak(2);
        assert 2 == integerBreak(3);
        assert 4 == integerBreak(4);
        assert 6 == integerBreak(5);
        assert 9 == integerBreak(6);
        assert 12 == integerBreak(7);
        assert 18 == integerBreak(8);
        assert 27 == integerBreak(9);

    }

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 2; j <= (i + 1) / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]));
            }
        }
        return dp[n];
    }

    @Test
    public void testnumberOfArithmeticSlices() {
        assert 3 == numberOfArithmeticSlices(new int[]{1, 2, 3, 4});
        assert 2 == numberOfArithmeticSlices(new int[]{0, 2, 3, 4, 6, 8});
        assert 7 == numberOfArithmeticSlices(new int[]{0, 2, 3, 4, 5, 6, 9, 1, 2, 3});
    }

    @Test
    public void testcountSubstrings() {
        assert 6 == countSubstrings("aaa");
        assert 3 == countSubstrings("aa");
        assert 3 == countSubstrings("abc");
        assert 6 == countSubstrings("aabb");
        assert 6 == countSubstrings("abba");

    }

    public int countSubstrings(String s) {
        char[] chars = new char[s.length() + s.length() + 1];
        chars[0] = '#';
        for (int i = 1; i <= s.toCharArray().length; i++) {
            chars[i * 2 - 1] = s.toCharArray()[i - 1];
            chars[i * 2] = '#';
        }

        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            int left = i - 1;
            int right = i + 1;
            count += chars[i] != '#' ? 1 : 0;
            while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
                if (chars[left] != '#') {
                    count++;
                }
                left--;
                right++;
            }
        }
        return count;
    }

    public int numberOfArithmeticSlices(int[] A) {
        int[] dp = new int[A.length];
        int count = 0;
        for (int j = 2; j < A.length; j++) {
            if (A[j] - A[j - 1] == A[j - 1] - A[j - 2]) {
                dp[j] = dp[j - 1] + 1;
                count += dp[j];
            }
        }
        return count;
    }

    @Test
    public void testsumRange() {
        NumArray array = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        assert 1 == array.sumRange(0, 2);
        assert 3 == array.sumRange(1, 2);
        assert 0 == array.sumRange(2, 4);
        assert -3 == array.sumRange(0, 5);
    }


    @Test
    public void testspiralOrder() {
        assert spiralOrder(new int[][]{
            {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
            },
        }).equals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        assert spiralOrder(new int[][]{
            {
                1, 2, 3
            },
            {4, 5, 6}
        }).equals(Arrays.asList(1, 2, 3, 6, 5, 4));

        assert spiralOrder(new int[][]{
            {
                1
            },
            {
                2
            },
            {
                3
            }, {
            4
        }, {
            5
        }, {6}, {7}, {8}, {9}, {10}
        }).equals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        assert spiralOrder(new int[][]{
            {
                1, 2
            },
            {
                3, 4
            }, {
            5, 6,
        }
        }).equals(Arrays.asList(1, 2, 4, 6, 5, 3));

        assert spiralOrder(new int[][]{
            {
                1, 2, 3, 4
            },
            {
                5, 6, 7, 8
            },
            {
                9, 10, 11, 12
            }
        }).equals(Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7));

        assert spiralOrder(new int[][]{
            {
                1, 2, 3
            },
            {
                5, 6, 7
            },
            {
                9, 10, 11
            },
            {
                12, 13, 14
            }
        }).equals(Arrays.asList(1, 2, 3, 7, 11, 14, 13, 12, 9, 5, 6, 10));
    }

    public List<Integer> spiralOrder(int[][] matrix, int startX) {
        List<Integer> results = new ArrayList<>();
        int row = startX;
        int cow = startX;
        if (row < matrix.length - startX && cow < matrix[row].length - startX) {
            for (; cow < matrix[row].length - startX; cow++) {
                results.add(matrix[row][cow]);
            }
            cow--;
        }
        row++;
        if (row < matrix.length - startX && cow == matrix[row].length - startX - 1) {
            for (; row < matrix.length - startX; row++) {
                results.add(matrix[row][cow]);
            }
            row--;
        }
        cow--;
        if (row == matrix.length - startX - 1 && cow >= startX) {
            for (; cow >= startX; cow--) {
                results.add(matrix[row][cow]);
            }
            cow++;
        }
        row--;
        if (row > startX && cow == startX) {
            for (; row > startX; row--) {
                results.add(matrix[row][cow]);
            }
        }

        return results;
    }


    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            results.addAll(spiralOrder(matrix, i));
        }
        return results;
    }

    @Test
    public void testmaxProduct() {
        assert 6 == maxProduct(new int[]{1, 2, 3});
        assert 6 == maxProduct(new int[]{2, 3, -2, 4});
        assert 0 == maxProduct(new int[]{-2, 0, -1});
        assert 24 == maxProduct(new int[]{-2, 12, -1});
        assert 0 == maxProduct(new int[]{-2, 0, -1});
        assert -2 == maxProduct(new int[]{-2});
        assert 2 == maxProduct(new int[]{2});
        assert 8 == maxProduct(new int[]{1, -2, 2, -2, -2, 1});
        assert 16 == maxProduct(new int[]{1, -2, -2, -2, -2, 1});
        assert 4 == maxProduct(new int[]{1, -2, -2, 0, -2, 1});

    }

    public int maxProduct(int[] nums) {
        int max = nums[0];
        int minNagetive = nums[0];
        int maxPositive = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int tmpMaxPositive = maxPositive;
            maxPositive = Math.max(Math.max(maxPositive * nums[i], nums[i]), minNagetive * nums[i]);
            minNagetive = Math.min(Math.min(minNagetive * nums[i], nums[i] * tmpMaxPositive), nums[i]);
            max = Math.max(max, maxPositive);
        }
        return max;
    }

    @Test
    public void testexist() {
        assert exist(new char[][]{
            {
                'A', 'B', 'C', 'E',
            },
            {
                'S', 'F', 'C', 'S'
            },
            {
                'A', 'D', 'E', 'E'
            }
        }, "ABCCED");

        assert exist(new char[][]{
            {
                'A', 'B', 'C', 'E',
            },
            {
                'S', 'F', 'C', 'S'
            },
            {
                'A', 'D', 'E', 'E'
            }
        }, "SEE");

        assert !exist(new char[][]{
            {
                'A', 'B', 'C', 'E',
            },
            {
                'S', 'F', 'C', 'S'
            },
            {
                'A', 'D', 'E', 'E'
            }
        }, "ABCB");
    }

    public boolean exist(char[][] board, int[][] visited, int x, int y, char[] chars, int startPos) {

        if (startPos >= chars.length) {
            return true;
        }

        if (y > 0 && visited[x][y - 1] == 0 && board[x][y - 1] == chars[startPos]) {
            visited[x][y - 1] = 1;
            if (exist(board, visited, x, y - 1, chars, startPos + 1)) {
                return true;
            }
            visited[x][y - 1] = 0;
        }
        if (y < board[x].length - 1 && visited[x][y + 1] == 0 && board[x][y + 1] == chars[startPos]) {
            visited[x][y + 1] = 1;
            if (exist(board, visited, x, y + 1, chars, startPos + 1)) {
                return true;
            }
            visited[x][y + 1] = 0;
        }

        if (x > 0 && visited[x - 1][y] == 0 && board[x - 1][y] == chars[startPos]) {
            visited[x - 1][y] = 1;
            if (exist(board, visited, x - 1, y, chars, startPos + 1)) {
                return true;
            }
            visited[x - 1][y] = 0;
        }

        if (x < board.length - 1 && visited[x + 1][y] == 0 && board[x + 1][y] == chars[startPos]) {
            visited[x + 1][y] = 1;
            if (exist(board, visited, x + 1, y, chars, startPos + 1)) {
                return true;
            }
            visited[x + 1][y] = 0;
        }

        return false;

    }

    public boolean exist(char[][] board, String word) {
        int[][] visited = new int[board.length][board[0].length];
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == chars[0]) {
                    visited[i][j] = 1;
                    if (exist(board, visited, i, j, chars, 1)) {
                        return true;
                    }
                    visited[i][j] = 0;
                }
            }
        }
        return false;
    }

    public void deleteNode(ListNode node) {
        ListNode tmp = node;
        ListNode pre = node;
        while (tmp != null && tmp.next != null) {
            tmp.val = tmp.next.val;
            pre = tmp;
            tmp = tmp.next;
        }
        pre.next = null;
    }

    @Test
    public void testcountPrimes() {
        assert countPrimes(10) == 4;
    }

    public int countPrimes(int n) {
        int count = 0;
        boolean[] notPrimes = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (notPrimes[i] == false) {
                count++;
                for (int k = 2; i * k < n; k++) {
                    notPrimes[i * k] = true;
                }
            }
        }
        return count;
    }

    @Test
    public void testFindNextPrime() {
        assert 2 == FindNextPrime(1);
        assert 13 == FindNextPrime(11);
        assert 127 == FindNextPrime(121);

    }

    int FindNextPrime(int i) {
        for (int k = i + 1; ; ++k) {
            boolean isprime = true;
            for (int j = 2; j < k; j++) {
                if (k % j == 0) {
                    isprime = false;
                }
            }
            if (isprime) {
                return k;
            }
        }
    }

    @Test
    public void testnextPermutation() {
        int[] nums = new int[]{1, 2, 3};
        int[] exp = new int[]{1, 3, 2};
        nextPermutation(nums);
        assert Arrays.equals(nums, exp);

        nums = new int[]{2, 3, 5, 5};
        exp = new int[]{2, 5, 3, 5};
        nextPermutation(nums);
        assert Arrays.equals(nums, exp);

        nums = new int[]{1, 3, 2};
        exp = new int[]{2, 1, 3};
        nextPermutation(nums);
        assert Arrays.equals(nums, exp);

        nums = new int[]{2, 4, 3, 2};
        exp = new int[]{3, 2, 2, 4};
        nextPermutation(nums);
        assert Arrays.equals(nums, exp);

        nums = new int[]{2, 4, 3, 3, 2, 2};
        exp = new int[]{3, 2, 2, 2, 3, 4};
        nextPermutation(nums);
        assert Arrays.equals(nums, exp);

        nums = new int[]{16, 27, 25, 23, 25, 16, 12, 9, 1, 2, 7, 20, 19, 23, 16, 0, 6, 22, 16, 11, 8, 27, 9, 2, 20, 2,
            13, 7, 25, 29, 12, 12, 18, 29, 27, 13, 16, 1, 22, 9, 3, 21, 29, 14, 7, 8, 14, 5, 0, 23, 16, 1, 20};
        exp = new int[]{16, 27, 25, 23, 25, 16, 12, 9, 1, 2, 7, 20, 19, 23, 16, 0, 6, 22, 16, 11, 8, 27, 9, 2, 20, 2,
            13, 7, 25, 29, 12, 12, 18, 29, 27, 13, 16, 1, 22, 9, 3, 21, 29, 14, 7, 8, 14, 5, 0, 23, 16, 20, 1};
        nextPermutation(nums);
        assert Arrays.equals(nums, exp);

    }


    private void reverseArray(int[] nums, int l, int r) {
        while (l < r) {
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }

    public void nextPermutation(int[] nums) {
        int firstGreater = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstGreater = i;
                break;
            }
        }
        if (firstGreater == -1) {
            reverseArray(nums, 0, nums.length - 1);
            return;
        }

        for (int i = nums.length - 1; i >= firstGreater + 1; i--) {
            if (nums[i] > nums[firstGreater]) {
                int tmp = nums[i];
                nums[i] = nums[firstGreater];
                nums[firstGreater] = tmp;
                break;
            }
        }
        reverseArray(nums, firstGreater + 1, nums.length - 1);
    }

    @Test
    public void testmajorityElement2() {
        assert majorityElement2(new int[]{1, 2, 3, 1, 2, 3, 3}).equals(Arrays.asList(3));
        assert majorityElement2(new int[]{1, 1, 1, 3, 3, 2, 2, 2}).equals(Arrays.asList(1, 2));
        assert majorityElement2(new int[]{1, 2, 3, 4, 1, 2, 1}).equals(Arrays.asList(1));
        assert majorityElement2(new int[]{1, 2, 2, 3, 2, 1, 1, 3}).equals(Arrays.asList(1, 2));

    }

    public List<Integer> majorityElement2(int[] nums) {
        int m1 = 0;
        int m2 = 0;
        int cm1 = 0;
        int cm2 = 0;
        for (int i : nums) {
            if (m1 == i) {
                cm1 += 1;
            } else if (m2 == i) {
                cm2 += 1;
            } else if (cm1 == 0) {
                m1 = i;
                cm1 = 1;
            } else if (cm2 == 0) {
                m2 = i;
                cm2 = 1;
            } else {
                cm1--;
                cm2--;
            }
        }
        List<Integer> results = new ArrayList<>();
        cm1 = 0;
        cm2 = 0;
        for (int i : nums) {
            if (i == m1) {
                cm1++;
            } else if (i == m2) {
                cm2++;
            }
        }
        if (cm1 > nums.length / 3) {
            results.add(m1);
        }
        if (cm2 > nums.length / 3) {
            results.add(m2);
        }
        return results;
    }

    @Test
    public void testcanJump() {
        assert canJump(new int[]{2, 3, 1, 1, 4});
        assert !canJump(new int[]{3, 2, 1, 0, 4});
    }


    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    @Test
    public void testnumFriendRequests() {
        assert numFriendRequests(new int[]{16, 17, 18}) == 2;
        assert numFriendRequests(new int[]{16, 16, 16}) == 6;
        assert numFriendRequests(new int[]{12, 16, 16}) == 2;
        assert numFriendRequests(new int[]{20, 30, 100, 110, 120}) == 3;
        assert numFriendRequests(new int[]{8, 24, 69, 85, 85}) == 4;
        assert numFriendRequests(new int[]{6, 6, 15, 26, 30, 35, 39, 46, 60, 71, 73, 100, 106, 110, 112}) == 29;
        assert numFriendRequests(new int[]{13, 27, 42, 42, 55, 56, 56, 57, 67, 97, 100, 113, 117, 117, 119}) == 48;
    }

    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int count = 0;
        Map<Integer, Integer> counting = new HashMap<>();
        for (int i = 0; i < ages.length; i++) {
            if (0.5 * ages[i] + 7 < ages[i]) {
                counting.put(ages[i], counting.getOrDefault(ages[i], 0) + 1);
            }
        }
        for (int i = ages.length - 1; i > 0; i--) {
            int low = (int) (0.5 * ages[i] + 7 + 1);
            int l = 0;
            int r = i - 1;
            while (l < r) {
                int m = (l + r) / 2;
                if (ages[m] >= low) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            if (low <= ages[l]) {
                count += i - l;
            }
            Integer preCount = counting.get(ages[i]);
            if (preCount != null && preCount > 0) {
                count += preCount - 1;
                counting.put(ages[i], preCount - 1);
            }
        }
        return count;
    }

    @Test
    public void testsearchRange() {
        assert Arrays.equals(new int[]{0, 5}, searchRange(new int[]{1, 1, 1, 1, 1, 1}, 1));
        assert Arrays.equals(new int[]{3, 4}, searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8));
        assert Arrays.equals(new int[]{3, 3}, searchRange(new int[]{5, 7, 7, 8, 10}, 8));
        assert Arrays.equals(new int[]{1, 2}, searchRange(new int[]{5, 7, 7, 8, 10}, 7));
        assert Arrays.equals(new int[]{-1, -1}, searchRange(new int[]{5, 7, 7, 8, 10}, 6));
    }

    public int[] searchRange(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int[] ret = new int[]{
            -1, -1
        };

        if (nums.length == 0) {
            return ret;
        }

        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        if (nums[l] != target) {
            return ret;
        }
        ret[0] = l;

        r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (nums[l - 1] != target) {
            return ret;
        }
        ret[1] = l - 1;
        return ret;
    }

    @Test
    public void testthreeSumClosest() {
        assert 2 == threeSumClosest(new int[]{-1, 2, 1, -4}, 1);
        assert 6 == threeSumClosest(new int[]{1, 2, 3, 4, 5}, 3);
        assert 12 == threeSumClosest(new int[]{1, 2, 3, 4, 5}, 12);
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                if (Math.abs(nums[i] + nums[l] + nums[r] - target) < Math.abs(sum - target)) {
                    sum = nums[i] + nums[l] + nums[r];
                }
                if (sum == target) {
                    return sum;
                }
                if (nums[l] + nums[r] > target - nums[i]) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return sum;
    }

    @Test
    public void testsearch() {
        assert 0 == search(new int[]{1}, 1);
        assert 1 == search(new int[]{1, 2}, 2);
        assert -1 == search(new int[]{1, 2, 4, 5, 6, 0}, -1);
        assert 4 == search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
        assert -1 == search(new int[]{4, 5, 6, 7, 0, 1, 2}, 9);
        assert 0 == search(new int[]{9, 1, 2, 4, 6}, 9);
        assert 1 == search(new int[]{9, 1, 2, 4, 6}, 1);
        assert -1 == search(new int[]{5, 1, 2, 3, 4}, 12);

        assert -1 == search(new int[]{5, 1, 2, 2, 2, 2, 3, 4}, 12);
        assert -1 != search(new int[]{5, 1, 2, 2, 2, 2, 3, 4}, 2);

    }

    @Test
    public void testsearch2() {
        assert search2(new int[]{1}, 1);
        assert search2(new int[]{1, 3, 1, 1, 1}, 3);
        assert search2(new int[]{2, 2, 2, 2, 2, 3, 1, 1, 1}, 3);
        assert search2(new int[]{2, 2, 2, 2, 2, 3, 1, 1, 1}, 1);
        assert search2(new int[]{2, 2, 2, 2, 2, 3, 1, 1, 1}, 2);
        assert search2(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            2);
    }

    public boolean search2(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] == target) {
                return true;
            }
            if (nums[m] == nums[l]) {
                if (target <= nums[r]) {
                    l = m + 1;
                } else {
                    l++;
                }
            } else if (nums[m] > nums[l]) {
                if (nums[l] <= target && target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            } else {
                if (target < nums[l] && nums[m] <= target) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return false;
    }

    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[m] >= nums[l]) {
                if (nums[l] <= target && target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            } else {
                if (target < nums[l] && nums[m] <= target) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return -1;
    }

    @Test
    public void testuniquePathsWithObstacles() {
        assert 2 == uniquePathsWithObstacles(new int[][]{
            {
                0, 0, 0
            },
            {
                0, 1, 0
            },
            {
                0, 0, 0
            }
        });

        assert 1 == uniquePathsWithObstacles(new int[][]{
            {
                0, 1, 0
            },
            {
                0, 1, 0
            },
            {
                0, 0, 0
            }
        });

        assert 0 == uniquePathsWithObstacles(new int[][]{
            {
                0, 1, 0
            },
            {
                1, 1, 0
            },
            {
                0, 0, 0
            }
        });

        assert 0 == uniquePathsWithObstacles(new int[][]{
            {
                0, 1, 0
            },
            {
                1, 1, 0
            },
            {
                0, 0, 1
            }
        });
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int dp[][] = new int[obstacleGrid.length][obstacleGrid[0].length];
        dp[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[i].length; j++) {
                if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                }
                if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                }
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }

    @Test
    public void testmerge2() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(15, 18));
        List<Interval> merged = merge(intervals);
        assert merged.toString().equals("[[1,6], [8,10], [15,18]]") : merged.toString();

        intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(4, 5));
        merged = merge(intervals);
        assert merged.toString().equals("[[1,5]]") : merged.toString();

        intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 5));
        intervals.add(new Interval(3, 15));
        intervals.add(new Interval(6, 7));
        merged = merge(intervals);
        assert merged.toString().equals("[[1,15]]") : merged.toString();
    }

    public List<Interval> merge(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(i -> i.start));
        List<Interval> mergedInterval = new ArrayList<>();
        for (Interval i : intervals) {
            if (mergedInterval.size() == 0) {
                mergedInterval.add(i);
            } else {
                Interval last = mergedInterval.get(mergedInterval.size() - 1);
                if (last.end < i.start) {
                    mergedInterval.add(i);
                }
                if (i.start <= last.end) {
                    last.end = Math.max(last.end, i.end);
                }
            }
        }
        return mergedInterval;
    }

    @Test
    public void testminSubArrayLen() {
        assert 2 == minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
        assert 1 == minSubArrayLen(8, new int[]{2, 3, 5, 1, 8, 11, 2});
        assert 0 == minSubArrayLen(8, new int[]{1, 1});
        assert 4 == minSubArrayLen(10, new int[]{1, 2, 3, 4});

    }

    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0;
        int left = 0;
        int min = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s && left <= i) {
                min = Math.min(min, i - left + 1);
                sum -= nums[left++];
            }
        }
        return min > nums.length ? 0 : min;
    }

    @Test
    public void testsummaryRanges() {
        assert summaryRanges(new int[]{0, 1, 2, 4, 5, 7}).equals(new ArrayList<>(Arrays.asList("0->2", "4->5", "7")));
        System.out.println(summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9}));
        System.out.println(summaryRanges(new int[]{0, 1, 2, 3, 4, 5}));
        System.out.println(summaryRanges(new int[]{0, 2, 4, 6, 8, 11}));
        System.out.println(summaryRanges(new int[]{0, 2, 4, 6, 8, 11, 12}));
        System.out.println(summaryRanges(new int[]{}));
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> results = new ArrayList<>();
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] != nums[i - 1] + 1) {
                if (i - 1 > left) {
                    results.add(nums[left] + "->" + nums[i - 1]);
                } else {
                    results.add(String.valueOf(nums[left]));
                }
                left = i;
            }
        }

        if (left < nums.length - 1) {
            results.add(nums[left] + "->" + nums[nums.length - 1]);
        } else if (left == nums.length - 1) {
            results.add(String.valueOf(nums[left]));
        }

        return results;
    }

    @Test
    public void testnumSubarrayProductLessThanK() {
        assert 4 == numSubarrayProductLessThanK(new int[]{1, 2, 3}, 5);
        assert 0 == numSubarrayProductLessThanK(new int[]{1, 2, 3}, 0);
        assert 8 == numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100);
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;
        int left = 0;
        int product = 1;
        for (int i = 0; i < nums.length; i++) {
            product *= nums[i];
            while (product >= k && left <= i) {
                product /= nums[left++];
            }
            count += i - left + 1;
        }
        return count;
    }

    @Test
    public void testsearchMatrix() {
        assert searchMatrix(new int[][]{
            {
                1, 3, 5, 7
            }, {
            10, 11, 16, 20
        }, {
            23, 30, 34, 50
        }
        }, 3);

        assert !searchMatrix(new int[][]{
            {
                1, 3, 5, 7
            }, {
            10, 11, 16, 20
        }, {
            23, 30, 34, 50
        }
        }, 13);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) {
            return false;
        }
        int cow = matrix[0].length;

        int l = 0;
        int r = row * cow - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (matrix[m / cow][m % cow] == target) {
                return true;
            }
            if (matrix[m / cow][m % cow] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return false;
    }

    @Test
    public void testisIdealPermutation() {
        assert isIdealPermutation(new int[]{1, 0, 2});
        assert !isIdealPermutation(new int[]{1, 2, 0});
        assert !isIdealPermutation(new int[]{2, 1, 0});
        assert isIdealPermutation(new int[]{0, 1, 2, 3, 4});
    }


    public boolean isIdealPermutation(int[] A) {
        for (int i = 0; i < A.length; ++i) {
            if (Math.abs(A[i] - i) > 1) {
                return false;
            }
        }
        return true;
    }

    public void preprintTree(List<Integer> temp, TreeNode node) {
        if (node == null) {
            return;
        }
        temp.add(node.val);
        preprintTree(temp, node.left);
        preprintTree(temp, node.right);
    }

    @Test
    public void testbuildTree() {
        int[] pre = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        TreeNode node = buildTree(pre, inorder);
        List<Integer> temp = new ArrayList<>();
        preprintTree(temp, node);
        assert Arrays.equals(temp.stream().mapToInt(i -> i).toArray(), pre);

        pre = new int[]{1, 2, 5, 8, 9, 3, 7, 10, 11};
        inorder = new int[]{5, 2, 9, 8, 1, 7, 3, 11, 10};
        temp = new ArrayList<>();
        node = buildTree(pre, inorder);
        preprintTree(temp, node);
        assert Arrays.equals(temp.stream().mapToInt(i -> i).toArray(), pre);

    }

    @Test
    public void testbuildTree2() {
        int[] post = new int[]{9, 15, 7, 20, 3};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        TreeNode node = new BuildTree().buildTree(inorder, post);
        List<Integer> temp = new ArrayList<>();
        preprintTree(temp, node);
        assert Arrays.equals(temp.stream().mapToInt(i -> i).toArray(), new int[]{3, 9, 20, 15, 7});

        post = new int[]{5, 15, 7, 2, 8, 12, 11, 4, 1};
        inorder = new int[]{5, 2, 15, 7, 1, 8, 4, 12, 11};
        node = new BuildTree().buildTree(inorder, post);
        temp = new ArrayList<>();
        preprintTree(temp, node);
        assert Arrays.equals(temp.stream().mapToInt(i -> i).toArray(), new int[]{1, 2, 5, 7, 15, 4, 8, 11, 12});
    }


    public TreeNode buildTree(int[] preorder, int startPreorder, int[] inorder, int startInorder, int endInorder) {
        if (startInorder == endInorder) {
            return null;
        }
        int root = preorder[startPreorder];
        TreeNode treeNode = new TreeNode(root);
        int left = startInorder;
        for (; left < endInorder; left++) {
            if (inorder[left] == root) {
                break;
            }
        }
        treeNode.left = buildTree(preorder, startPreorder + 1, inorder, startInorder, left);
        treeNode.right = buildTree(preorder, startPreorder + 1 + left - startInorder, inorder, left + 1, endInorder);
        return treeNode;
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, inorder, 0, inorder.length);
    }

    @Test
    public void testminimumTotal() {
        List<List<Integer>> triangle = new ArrayList<>();
        int[][] array = new int[][]{
            {
                2
            },
            {
                3, 4
            },
            {
                6, 5, 7
            },
            {
                4, 1, 8, 3
            }
        };
        for (int[] a : array) {
            List<Integer> l = IntStream.of(a).boxed().collect(Collectors.toList());
            triangle.add(l);
        }
        assert 11 == minimumTotal(triangle);

        triangle = new ArrayList<>();
        array = new int[][]{
            {
                1
            },
            {
                2, 3
            },
            {
                4, 5, 6
            },
            {
                18, 15, 9, 10
            }
        };
        for (int[] a : array) {
            List<Integer> l = IntStream.of(a).boxed().collect(Collectors.toList());
            triangle.add(l);
        }
        assert 17 == minimumTotal(triangle);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    @Test
    public void testsetZeroes() {
        int[][] matrix = new int[][]{
            {
                1, 1, 1
            },
            {
                1, 0, 1
            },
            {
                1, 1, 1
            }
        };
        setZeroes(matrix);
        int[][] expected = new int[][]{
            {
                1, 0, 1
            },
            {
                0, 0, 0
            },
            {
                1, 0, 1
            }
        };
        for (int i = 0; i < matrix.length; i++) {
            assert Arrays.equals(matrix[i], expected[i]);
        }

        matrix = new int[][]{
            {
                0, 1, 2, 0
            },
            {
                3, 4, 5, 2
            },
            {
                1, 3, 1, 5
            }
        };
        setZeroes(matrix);
        expected = new int[][]{
            {
                0, 0, 0, 0
            },
            {
                0, 4, 5, 0
            },
            {
                0, 3, 1, 0
            }
        };
        for (int i = 0; i < matrix.length; i++) {
            assert Arrays.equals(matrix[i], expected[i]);
        }

        matrix = new int[][]{
            {
                0, 1, 2, 0
            },
            {
                3, 4, -1, 2
            },
            {
                1, 3, 1, 5
            }
        };
        setZeroes(matrix);
        expected = new int[][]{
            {
                0, 0, 0, 0
            },
            {
                0, 4, -1, 0
            },
            {
                0, 3, 1, 0
            }
        };
        for (int i = 0; i < matrix.length; i++) {
            assert Arrays.equals(matrix[i], expected[i]);
        }

        matrix = new int[][]{
            {
                -1
            },
            {
                2
            },
            {
                3
            }
        };
        setZeroes(matrix);
        expected = new int[][]{
            {
                -1
            },
            {
                2
            },
            {
                3
            }
        };
        for (int i = 0; i < matrix.length; i++) {
            assert Arrays.equals(matrix[i], expected[i]);
        }

        matrix = new int[][]{
            {
                -1, -2, 4
            }
        };
        setZeroes(matrix);
        expected = new int[][]{
            {
                -1, -2, 4
            }
        };
        for (int i = 0; i < matrix.length; i++) {
            assert Arrays.equals(matrix[i], expected[i]);
        }
    }

    public void setZeroes(int[][] matrix) {
        boolean fr = false;
        boolean fc = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        fr = true;
                    }
                    if (j == 0) {
                        fc = true;
                    }
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (fr) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
        if (fc) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    @Test
    public void testnumMatchingSubseq() {
        assert 3 == numMatchingSubseq("abcde", new String[]{"a", "bb", "acd", "ace"});
        assert 2 == numMatchingSubseq("nwmswzegbu", new String[]{"mswz", "swzegb", "tpwhdywjst", "dglnzwitub"});
    }

    @Test
    public void testLCS() {
        assert 2 == LCS("abc", "bca");
        assert 3 == LCS("abbbcde", "abed");
        assert 3 == LCS("abbbcde", "abed");
    }

    public int LCS(String a, String b) {
        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 1; i < a.length() + 1; i++) {
            for (int j = 1; j < b.length() + 1; j++) {
                if (as[i - 1] == bs[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[a.length()][b.length()];
    }

    public int numMatchingSubseq(String S, String[] words) {

        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            List<Integer> list = map.getOrDefault(S.charAt(i), new ArrayList<>());
            list.add(i);
            map.put(S.charAt(i), list);
        }

        int count = 0;
        for (String s : words) {
            Set<Integer> indexSet = new HashSet<>();
            List<Integer> indexList = new ArrayList<>();
            for (char c : s.toCharArray()) {
                List<Integer> list = map.get(c);
                if (list == null) {
                    break;
                }
                for (Integer i : list) {
                    if (!indexSet.contains(i) && (indexList.size() == 0 || indexList.get(indexList.size() - 1) < i)) {
                        indexSet.add(i);
                        indexList.add(i);
                        break;
                    }
                }
            }
            if (indexList.size() == s.length()) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void testmaxArea() {
        assert 49 == maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
    }

    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int max = 0;
        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }

    @Test
    public void testremoveDuplicates2() {
        int[] nums = new int[]{1};
        int[] expected = new int[]{1};
        assert 1 == removeDuplicates2(nums);
        assert Arrays.equals(nums, expected);

        nums = new int[]{1, 1, 1, 1};
        expected = new int[]{1, 1, 1, 1};
        assert 2 == removeDuplicates2(nums);
        assert Arrays.equals(nums, expected);

        nums = new int[]{1, 1, 1, 2, 2};
        expected = new int[]{1, 1, 2, 2, 2};
        assert 4 == removeDuplicates2(nums);
        assert Arrays.equals(nums, expected);

        nums = new int[]{1, 1, 1, 2, 2, 3, 3, 3, 4};
        expected = new int[]{1, 1, 2, 2, 3, 3, 4, 3, 4};
        assert 7 == removeDuplicates2(nums);
        assert Arrays.equals(nums, expected);

        nums = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
        expected = new int[]{0, 0, 1, 1, 2, 3, 3, 3, 3};
        assert 7 == removeDuplicates2(nums);
        assert Arrays.equals(nums, expected);

    }

    public int removeDuplicates2(int[] nums) {
        int next = 0;
        for (int n : nums) {
            if (next < 2 || n > nums[next - 2]) {
                nums[next++] = n;
            }
        }
        return next;
    }

    @Test
    public void testgameOfLife() {
        int[][] t = new int[][]{
            {0, 1, 0},
            {0, 0, 1},
            {1, 1, 1},
            {0, 0, 0},
        };
        gameOfLife(t);
        int[][] ex = new int[][]{
            {0, 0, 0},
            {1, 0, 1},
            {0, 1, 1},
            {
                0, 1, 0
            }
        };
        for (int i = 0; i < t.length; i++) {
            assert Arrays.equals(t[i], ex[i]);
        }

        t = new int[][]{
            {0, 1, 1},
            {0, 1, 1},
            {1, 1, 1},
            {0, 0, 0},
        };
        gameOfLife(t);
        ex = new int[][]{
            {0, 1, 1},
            {0, 0, 0},
            {1, 0, 1},
            {0, 1, 0}
        };
        for (int i = 0; i < t.length; i++) {
            assert Arrays.equals(t[i], ex[i]);
        }
    }

    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int count = 0;
                for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, board.length - 1); x++) {
                    for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, board[i].length - 1); y++) {
                        if (x == i && y == j) {
                            continue;
                        }
                        if (board[x][y] == 1) {
                            count += 1;
                        }
                        if (board[x][y] == 3) {
                            count += 1;
                        }
                    }
                }

                if (board[i][j] == 1 && (count < 2 || count > 3)) {
                    board[i][j] = 3;
                }
                if (board[i][j] == 0 && count == 3) {
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
                if (board[i][j] == 3) {
                    board[i][j] = 0;
                }
            }
        }
    }

    @Test
    public void testmaximumSwap() {
        assert 1 == maximumSwap(1);
        assert 21 == maximumSwap(12);
        assert 21 == maximumSwap(21);
        assert 7263 == maximumSwap(2763);
        assert 900000001 == maximumSwap(100000009);
        assert 98863 == maximumSwap(98368);
        assert 9913 == maximumSwap(1993);

        assert 833 == maximumSwap(383);
    }


    public int maximumSwap(int num) {
        char[] chars = Integer.valueOf(num).toString().toCharArray();
        int[] last = new int[10];
        for (int i = 0; i < chars.length; i++) {
            last[chars[i] - '0'] = i;
        }

        for (int i = 0; i < chars.length; i++) {
            for (int d = 9; d > chars[i] - '0'; d--) {
                if (last[d] > i) {
                    int t = last[d];
                    char tmp = chars[t];
                    chars[t] = chars[i];
                    chars[i] = tmp;
                    return Integer.valueOf(String.valueOf(chars));
                }
            }
        }
        return num;
    }

    @Test
    public void testMyCalendarTwo() {
        MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
        assert myCalendarTwo.book(10, 20);
        assert myCalendarTwo.book(50, 60);
        assert myCalendarTwo.book(10, 40);
        assert !myCalendarTwo.book(5, 15);
        assert myCalendarTwo.book(5, 10);
        assert myCalendarTwo.book(25, 55);

        myCalendarTwo = new MyCalendarTwo();
        assert myCalendarTwo.book(24, 40);
        assert myCalendarTwo.book(43, 50);
        assert myCalendarTwo.book(27, 43);
        assert myCalendarTwo.book(5, 21);
        assert !myCalendarTwo.book(30, 40);
        assert !myCalendarTwo.book(14, 29);
        assert myCalendarTwo.book(3, 19);
        assert !myCalendarTwo.book(3, 14);
        assert !myCalendarTwo.book(25, 39);
        assert !myCalendarTwo.book(6, 19);

    }

    @Test
    public void testadvantageCount() {
        assert Arrays.equals(advantageCount(new int[]{2, 7, 11, 15}, new int[]{1, 10, 4, 11}), new int[]{2, 11, 7, 15});
        assert Arrays.equals(advantageCount(new int[]{1, 2, 3, 4}, new int[]{5, 6, 7, 8}), new int[]{4, 3, 2, 1});
        assert Arrays
            .equals(advantageCount(new int[]{12, 24, 8, 32}, new int[]{13, 25, 32, 11}), new int[]{24, 32, 8, 12});
        assert Arrays
            .equals(advantageCount(new int[]{2, 0, 4, 1, 2}, new int[]{1, 3, 0, 0, 2}), new int[]{2, 4, 2, 1, 0});

    }

    public int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < B.length; i++) {
            list.add(new int[]{B[i], i});
        }
        list.sort((o1, o2) -> o2[0] - o1[0]);
        int low = 0;
        int high = A.length - 1;
        int[] result = new int[A.length];
        for (int i = 0; i < list.size(); i++) {
            int index = list.get(i)[1];
            int value = list.get(i)[0];
            if (A[high] > value) {
                result[index] = A[high--];
            } else {
                result[index] = A[low++];
            }
        }
        return result;
    }

    @Test
    public void testfindPeakElement() {
        assert 2 == findPeakElement(new int[]{1, 2, 3, 1});
        assert 1 == findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}) || 5 == findPeakElement(
            new int[]{1, 2, 1, 3, 5, 6, 4});
        assert 5 == findPeakElement(new int[]{1, 2, 3, 4, 5, 6});
        assert 0 == findPeakElement(new int[]{6, 5, 4, 3, 2, 1});
    }

    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

//    public int findPeakElement(int[] nums) {
//        for (int i = 0; i < nums.length - 1; i++) {
//            if (nums[i] > nums[i + 1]) {
//                return i;
//            }
//        }
//        return nums.length - 1;
//    }

    @Test
    public void testsortColors() {
        int[] colors = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(colors);
        Arrays.equals(colors, new int[]{0, 0, 1, 1, 2, 2});
    }

    public void sortColors(int[] nums) {
        int indexOfZero = 0;
        int indexOfOne = 0;
        for (int i = 0; i < nums.length; ++i) {
            int v = nums[i];
            nums[i] = 2;
            if (v < 2) {
                nums[indexOfOne] = 1;
                indexOfOne++;
            }
            if (v == 0) {
                nums[indexOfZero] = 0;
                indexOfZero++;
            }
        }
    }

    @Test
    public void testsubarraySum() {
        assert 2 == subarraySum(new int[]{1, 1, 1}, 2);
        assert 5 == subarraySum(new int[]{1, 1, 1, -1, 2}, 2);
    }

    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            Integer preCount = map.get(sum - k);
            if (preCount != null) {
                count += preCount;
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    @Test
    public void testRandomizedSet() {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.remove(1));
        System.out.println(randomizedSet.insert(2));
        System.out.println(randomizedSet.insert(100000000));
        System.out.println(randomizedSet.insert(-10000000));
        System.out.println(randomizedSet.getRandom());

        System.out.println(randomizedSet.remove(-10000000));
        System.out.println(randomizedSet.insert(-1));
        System.out.println(randomizedSet.insert(-1));

        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.getRandom());
    }

    @Test
    public void testlenLongestFibSubseq() {
        assert 3 == lenLongestFibSubseq(new int[]{1, 2, 3});
        assert 3 == lenLongestFibSubseq(new int[]{1, 2, 3, 4});
        assert 5 == lenLongestFibSubseq(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        assert 3 == lenLongestFibSubseq(new int[]{1, 3, 7, 11, 12, 14, 18});
        assert 7 == lenLongestFibSubseq(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 11, 18, 19, 29});
        assert 5 == lenLongestFibSubseq(new int[]{2, 4, 7, 8, 9, 10, 14, 15, 18, 23, 32, 50});
        assert 5 == lenLongestFibSubseq(new int[]{2, 4, 7, 8, 9, 10, 14, 15, 18, 23, 32, 50});
    }

    public int lenLongestFibSubseq(int[] A) {

        Map<Integer, Integer> map = IntStream.range(0, A.length).boxed()
            .collect(Collectors.toMap(i -> A[i], Function.identity()));

        int[][] dp = new int[A.length][A.length];
        int max = 0;
        for (int i = 2; i < A.length; ++i) {
            for (int j = i - 1; j >= 0; j--) {
                int k = map.getOrDefault(A[i] - A[j], A.length);
                if (k < j) {
                    dp[i][j] = Math.max(3, dp[j][k] + 1);
                }

                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    @Test
    public void testfindMin() {
        assert 1 == findMin(new int[]{1});
        assert 1 == findMin(new int[]{1, 2});
        assert 1 == findMin(new int[]{1, 2, 3});

        assert 1 == findMin(new int[]{3, 4, 5, 1, 2});
        assert 0 == findMin(new int[]{4, 5, 6, 7, 0, 1, 2});
        assert 0 == findMin(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0});
        assert 0 == findMin(new int[]{3, 4, 5, 0, 1, 2});
    }

    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            if (nums[l] < nums[r]) {
                return nums[l];
            }

            int m = (l + r) / 2;
            if (nums[m] > nums[r]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return nums[l];
    }

    @Test
    public void testnumSubarrayBoundedMax() {
        assert 0 == numSubarrayBoundedMax(new int[]{1}, 0, 0);
        assert 1 == numSubarrayBoundedMax(new int[]{1}, 0, 1);
        assert 1 == numSubarrayBoundedMax(new int[]{1, 2}, 0, 1);
        assert 4 == numSubarrayBoundedMax(new int[]{1, 2, 0, 1}, 0, 1);
        assert 3 == numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 2, 3);
        assert 7 == numSubarrayBoundedMax(new int[]{2, 9, 2, 5, 6}, 2, 8);

    }


    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int count = 0;
        int prev = -1;
        int dp = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > R) {
                prev = i;
                dp = 0;
            }
            if (A[i] < L) {
                count += dp;
            }
            if (L <= A[i] && A[i] <= R) {
                dp = i - prev;
                count += dp;
            }
        }

        return count;
    }

//    public int numSubarrayBoundedMax(int[] A, int L, int R) {
//        int count = 0;
//        for (int i = 0; i < A.length; i++) {
//            if (A[i] > R) {
//                continue;
//            }
//            int max = A[i];
//            for (int j = i; j < A.length; j++) {
//                max = Math.max(A[j], max);
//                if (max > R) {
//                    break;
//                }
//                if (L <= max) {
//                    count += 1;
//                }
//            }
//        }
//
//        return count;
//    }

    @Test
    public void testfindLength() {
        assert 2 == findLength(new int[]{1, 2, 3}, new int[]{3, 1, 2});
        assert 1 == findLength(new int[]{1, 2, 3}, new int[]{3, 2, 1});
        assert 1 == findLength(new int[]{1, 2, 3}, new int[]{1});
        assert 3 == findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7});
        assert 2 == findLength(new int[]{0, 1, 1, 1, 1}, new int[]{1, 0, 1, 0, 1});
    }

    public int findLength(int[] A, int[] B) {
        int max = 0;
        int dp[][] = new int[A.length + 1][B.length + 1];
        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = B.length - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    @Test
    public void testtriangleNumber() {
        assert 0 == triangleNumber(new int[]{0, 1, 0});
        assert 1 == triangleNumber(new int[]{0, 1, 1, 1});

        assert 0 == triangleNumber(new int[]{1, 2, 3});
        assert 1 == triangleNumber(new int[]{1, 2, 2});
        assert 3 == triangleNumber(new int[]{2, 2, 3, 4});
        assert 36 == triangleNumber(new int[]{34, 75, 96, 10, 60, 70, 70, 45});
        assert 65 == triangleNumber(new int[]{66, 99, 36, 44, 26, 99, 32, 64, 19, 69});
        assert 3 == triangleNumber(new int[]{2, 5, 6, 7});

    }

    int binarySearch(int nums[], int l, int r, int x) {
        while (r >= l && r < nums.length) {
            int mid = (l + r) / 2;
            if (nums[mid] >= x) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public int triangleNumber(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                k = binarySearch(nums, k, nums.length - 1, nums[i] + nums[j]);
                System.out.println("nums[i] + nums[j] = " + nums[i] + "," + nums[j] + ",k=" + k);
                count += k - j - 1;
            }
        }
        return count;
    }

    @Test
    public void testminPathSum() {

        assert 1 == minPathSum(new int[][]{
            {
                1
            }
        });

        assert 3 == minPathSum(new int[][]{
            {
                1, 2
            }
        });

        assert 7 == minPathSum(new int[][]{
            {
                1, 3, 1,
            },
            {
                1, 5, 1,
            },
            {
                4, 2, 1,
            }
        });
    }


    public int minPathSum(int[][] grid) {
        for (int i = 1; i < grid.length; i++) {
            grid[i][0] = grid[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
            grid[0][i] = grid[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }


    @Test
    public void testgenerateMatrix() {
        int[][] result = generateMatrix(1);
        for (int[] r : result) {
            for (int s : r) {
                System.out.print(s);
            }
        }

        System.out.println("\n----------------");

        result = generateMatrix(2);
        for (int[] r : result) {
            for (int s : r) {
                System.out.print(s);
            }
        }

        System.out.println("\n----------------");

        result = generateMatrix(3);
        for (int[] r : result) {
            for (int s : r) {
                System.out.print(s);
            }
        }

        System.out.println("\n----------------");

        result = generateMatrix(4);
        for (int[] r : result) {
            for (int s : r) {
                System.out.print(s);
            }
        }
    }

    int generateMatrix(int[][] result, int n, int startX, int startNumber) {
        int tmp = startNumber;
        for (int i = startX; i < n - startX; i++) {
            result[startX][i] = ++tmp;
        }
        for (int i = startX + 1; i < n - startX; i++) {
            result[i][n - startX - 1] = ++tmp;
        }
        for (int i = n - startX - 2; i >= startX; i--) {
            result[n - startX - 1][i] = ++tmp;
        }
        for (int i = n - startX - 2; i >= startX + 1; i--) {
            result[i][startX] = ++tmp;
        }
        return tmp;
    }

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int tmp = 0;
        for (int i = 0; i < n; i++) {
            tmp = generateMatrix(result, n, i, tmp);
        }
        return result;
    }

//    @Test
//    public void testleastInterval() {
//        assert 1 == leastInterval(new char[]{'a'}, 2);
//        assert 4 == leastInterval(new char[]{'a', 'a'}, 2);
//        assert 8 == leastInterval(new char[]{'a', 'a', 'b', 'b', 'b'}, 2);
//        assert 8 == leastInterval(new char[]{'a', 'a', 'a', 'b', 'b', 'b'}, 2);
//        assert 8 == leastInterval(new char[]{'a', 'a', 'b', 'b', 'b', 'c'}, 2);
//        assert 4 == leastInterval(new char[]{'a', 'a', 'a', 'b'}, 0);
//        assert 4 == leastInterval(new char[]{'a', 'a', 'a', 'b'}, 1);
//
//
//    }
//
//    public int leastInterval(char[] tasks, int n) {
//        Arrays.sort(tasks);
//        int preMin = 1;
//        int pre = 1;
//        int max = 1;
//        for (int i = 0; i < tasks.length; i++) {
//            if (i > 0 && tasks[i] == tasks[i - 1]) {
//                pre = pre + n + 1;
//                max = Math.max(pre, max);
//                if (preMin + 1 == pre) {
//                    preMin = pre;
//                }
//            }
//            if (i > 0 && tasks[i] != tasks[i - 1]) {
//                pre = preMin + 1;
//                preMin = pre;
//                max = Math.max(pre, max);
//            }
//        }
//        return max;
//    }

    @Test
    public void testMyCalendar() {
        MyCalendar myCalendar = new MyCalendar();
        assert myCalendar.book(1, 2);
        assert myCalendar.book(10, 20);
        assert !myCalendar.book(15, 35);
        assert myCalendar.book(20, 30);
    }

    @Test
    public void testRotate() {

        int[][] matrix = new int[][]{
            {1, 2}, {3, 4}
        };
        rotate(matrix);
        for (int[] s : matrix) {
            for (int j : s) {
                System.out.print(j);
            }
            System.out.println();
        }

        matrix = new int[][]{
            {
                1, 2, 3
            },
            {
                4, 5, 6
            },
            {
                7, 8, 9
            }
        };
        rotate(matrix);
        for (int[] s : matrix) {
            for (int j : s) {
                System.out.print(j);
            }
            System.out.println();
        }

        matrix = new int[][]{
            {
                5, 1, 9, 11
            },
            {
                2, 4, 8, 10
            },
            {
                13, 3, 6, 7
            },
            {
                15, 14, 12, 16
            }
        };
        rotate(matrix);
        for (int[] s : matrix) {
            for (int j : s) {
                System.out.print(j);
            }
            System.out.println();
        }

    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = tmp;
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

//        for (int i = 0; i < n - 1; i++) {
//            for (int j = i; j < n - i - 1; j++) {
//                int startX = i;
//                int startY = j;
//                int pre = matrix[startX][startY];
//                do {
//                    int endX = startY;
//                    int endY = n - 1 - startX;
//                    int tmp = matrix[endX][endY];
//                    matrix[endX][endY] = pre;
//                    pre = tmp;
//                    startX = endX;
//                    startY = endY;
//                } while (startX != i || startY != j);
//
//            }
//        }
    }

    @Test
    public void testUniquePaths() {
        assert 1 == uniquePaths(1, 1);
        assert 1 == uniquePaths(2, 1);
        assert 1 == uniquePaths(3, 1);
        assert 3 == uniquePaths(3, 2);
        assert 28 == uniquePaths(7, 3);

    }

    public int uniquePathsFrom(int startX, int startY, int m, int n) {
        int count = 0;
        if (startX == m && startY == n) {
            count = 1;
        }

        if (startX < m) {
            count += uniquePathsFrom(startX + 1, startY, m, n);
        }

        if (startY < n) {
            count += uniquePathsFrom(startX, startY + 1, m, n);
        }

        return count;
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    @Test
    public void testhasCycle() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        assert !hasCycle(head);

        head.next.next.next.next = head.next;
        assert hasCycle(head);
    }


    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode tortoise = head;
        ListNode hare = head;
        do {
            tortoise = tortoise.next;
            hare = hare.next;
            if (hare != null) {
                hare = hare.next;
            }
        } while (tortoise != null && hare != null && tortoise != hare);

        return hare != null;
    }

    @Test
    public void testdetectCycle() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        ListNode circle = detectCycle(head);
        assert circle == null;

        head.next.next.next.next = head.next;
        circle = detectCycle(head);
        assert circle == head.next;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode tortoise = head;
        ListNode hare = head;
        do {
            tortoise = tortoise.next;
            hare = hare.next;
            if (hare != null) {
                hare = hare.next;
            }
        } while (tortoise != null && hare != null && tortoise != hare);

        ListNode ptr1 = tortoise;
        ListNode ptr2 = head;
        while (ptr1 != ptr2 && ptr1 != null && ptr2 != null) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr1;
    }

    @Test
    public void testfindDuplicate() {
        assert 2 == findDuplicate(new int[]{1, 3, 4, 2, 2});
        assert 3 == findDuplicate(new int[]{3, 1, 3, 4, 2});
    }

    public int findDuplicate(int[] nums) {
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }
        return ptr1;
    }

    @Test
    public void testpartition() {
        List<List<String>> results = partition("aab");
        for (List<String> r : results) {
            System.out.println(r);
        }
        assert results.size() == 2;
    }

    void backtrackPartition(List<List<String>> result, List<String> temp, String s, int start) {
        if (start == s.length()) {
            result.add(new ArrayList<>(temp));
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                temp.add(s.substring(start, i + 1));
                backtrackPartition(result, temp, s, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    public List<List<String>> partition(String s) {
        List<List<String>> results = new ArrayList<>();
        backtrackPartition(results, new ArrayList<>(), s, 0);
        return results;
    }

    @Test
    public void testCombinationSum2() {
        List<List<Integer>> results = combinationSum2(new int[]{1, 2, 3}, 3);
        for (List<Integer> r : results) {
            System.out.println(r);
        }
        assert results.size() == 2;

        results = combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        for (List<Integer> r : results) {
            System.out.println(r);
        }
        assert results.size() == 4;

    }

    void backtrackCombination2(List<List<Integer>> result, List<Integer> temp, int[] nums, int remain, int start) {
        if (remain == 0) {
            result.add(new ArrayList<>(temp));
        }
        if (remain > 0) {
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i - 1]) {
                    // skip duplicates
                    continue;
                }
                temp.add(nums[i]);
                backtrackCombination2(result, temp, nums, remain - nums[i], i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> results = new ArrayList<>();
        backtrackCombination2(results, new ArrayList<>(), candidates, target, 0);
        return results;
    }

    @Test
    public void testCombinationSum() {
        List<List<Integer>> results = combinationSum(new int[]{1, 2, 3}, 3);
        assert results.size() == 3;
        for (List<Integer> r : results) {
            System.out.println(r);
        }

        results = combinationSum(new int[]{2, 3, 6, 7}, 7);
        assert results.size() == 2;
        for (List<Integer> r : results) {
            System.out.println(r);
        }

        results = combinationSum(new int[]{2, 3, 5}, 8);
        assert results.size() == 3;
        for (List<Integer> r : results) {
            System.out.println(r);
        }
    }

    void backtrackCombination(List<List<Integer>> result, List<Integer> temp, int[] nums, int remain, int start) {

        if (remain == 0) {
            result.add(new ArrayList<>(temp));
        }
        if (remain > 0) {
            for (int i = start; i < nums.length; i++) {
                temp.add(nums[i]);
                backtrackCombination(result, temp, nums, remain - nums[i], i);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> results = new ArrayList<>();
        backtrackCombination(results, new ArrayList<>(), candidates, target, 0);
        return results;
    }

    @Test
    public void testpermute() {
        List<List<Integer>> results = permute(new int[]{1, 2, 3});
        assert results.size() == 6;
        for (List<Integer> r : results) {
            System.out.println(r);
        }

        results = permuteUnique(new int[]{1, 1, 2});
        assert results.size() == 3;
        for (List<Integer> r : results) {
            System.out.println(r);
        }
    }

    void backtrackPermute(List<List<Integer>> result, Map<Integer, Integer> temp, int[] nums) {
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp.values()));
        }
        for (int i = 0; i < nums.length; i++) {
            if (temp.containsKey(i)) {
                continue;
            }
            // 对于 1 1 2这种情况，如果第一个位置1已经被使用，则不能再使用第二个位置的1
            if (i > 0 && nums[i] == nums[i - 1] && !temp.containsKey(i - 1)) {
                continue;
            }
            temp.put(i, nums[i]);
            backtrackPermute(result, temp, nums);
            temp.remove(i);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        backtrackPermute(results, new LinkedHashMap<>(), nums);
        return results;
    }

    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        backtrackPermute(results, new LinkedHashMap<>(), nums);
        return results;
    }

    @Test
    public void testcNm() {
        List<List<Integer>> results = subsets(new int[]{1, 2, 3});
        assert results.size() == 8;
        for (List<Integer> r : results) {
            System.out.println(r);
        }
    }

    @Test
    public void testSubsetsWithDup() {
        List<List<Integer>> results = subsetsWithDup(new int[]{1, 2, 3});
        assert results.size() == 8;
        for (List<Integer> r : results) {
            System.out.println(r);
        }

        results = subsetsWithDup(new int[]{1, 2, 2});
        for (List<Integer> r : results) {
            System.out.println(r);
        }
        assert results.size() == 6;

    }

    void backtrackDup(List<List<Integer>> result, List<Integer> temp, int[] nums, int start) {
        result.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            backtrackDup(result, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        backtrackDup(results, new ArrayList<>(), nums, 0);
        return results;
    }


    void backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums, int start) {
        result.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            backtrack(result, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        backtrack(results, new ArrayList<>(), nums, 0);
        return results;
    }

    @Test
    public void testmaxProfit() {
        assert 0 == maxProfit(new int[]{1, 2}, 2);
        assert 2 == maxProfit(new int[]{1, 5}, 2);
        assert 2 == maxProfit(new int[]{3, 1, 5}, 2);

        assert 2 == maxProfit(new int[]{1, 2, 5}, 2);
        assert 8 == maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2);
        assert 13 == maxProfit(new int[]{1, 4, 6, 2, 8, 3, 10, 14}, 3);

    }

    public int maxProfit(int[] prices, int fee) {
        int hold = -prices[0];
        int cash = 0;
        for (int i = 1; i < prices.length; ++i) {
            cash = Math.max(cash, prices[i] + hold - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }


    @Test
    public void testcombinationSum3() {
        List<List<Integer>> result = combinationSum3(1, 1);
        for (List<Integer> r : result) {
            System.out.println(r);
        }
        assert result.size() == 1;
        assert result.get(0).equals(Arrays.asList(1));

        result = combinationSum3(2, 3);
        for (List<Integer> r : result) {
            System.out.println(r);
        }
        assert result.size() == 1;
        assert result.get(0).equals(Arrays.asList(1, 2));

        result = combinationSum3(2, 5);
        for (List<Integer> r : result) {
            System.out.println(r);
        }
        assert result.size() == 2;
        assert result.get(0).equals(Arrays.asList(1, 4));
        assert result.get(1).equals(Arrays.asList(2, 3));

        result = combinationSum3(3, 9);
        for (List<Integer> r : result) {
            System.out.println(r);
        }
        assert result.size() == 3;
        assert result.get(0).equals(Arrays.asList(1, 2, 6));
        assert result.get(1).equals(Arrays.asList(1, 3, 5));
        assert result.get(2).equals(Arrays.asList(2, 3, 4));

        result = combinationSum3(4, 16);
        for (List<Integer> r : result) {
            System.out.println(r);
        }
        assert result.size() == 8;
        assert result.get(0).equals(Arrays.asList(1, 2, 4, 9));
        assert result.get(1).equals(Arrays.asList(1, 2, 5, 8));
        assert result.get(2).equals(Arrays.asList(1, 2, 6, 7));
        assert result.get(3).equals(Arrays.asList(1, 3, 4, 8));
        assert result.get(4).equals(Arrays.asList(1, 3, 5, 7));
        assert result.get(5).equals(Arrays.asList(1, 4, 5, 6));
        assert result.get(6).equals(Arrays.asList(2, 3, 4, 7));
        assert result.get(7).equals(Arrays.asList(2, 3, 5, 6));

        result = combinationSum3(2, 16);
        for (List<Integer> r : result) {
            System.out.println(r);
        }
        assert result.size() == 1;
        assert result.get(0).equals(Arrays.asList(7, 9));

    }

    public List<List<Integer>> combinationSum3Set(int k, int start, int n) {
        List<List<Integer>> combinations = new ArrayList<>();
        for (int i = start; i <= 9 && i <= n; i++) {
            if (k == 1 && n == i) {
                List<Integer> set = new ArrayList<>();
                set.add(i);
                combinations.add(set);
                return combinations;
            }
            List<List<Integer>> rest = combinationSum3Set(k - 1, i + 1, n - i);
            for (List<Integer> c : rest) {
                if (c.size() > 0) {
                    c.add(0, i);
                    combinations.add(c);
                }
            }
        }
        return combinations;
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        return combinationSum3Set(k, 1, n);
    }

    @Test
    public void testmaxChunksToSorted() {
        assert maxChunksToSorted(new int[]{1, 0, 2, 3, 4}) == 4;
        assert maxChunksToSorted(new int[]{4, 3, 2, 1, 0}) == 1;
        assert maxChunksToSorted(new int[]{1, 2, 0, 3}) == 2;
    }

    public int maxChunksToSorted(int[] arr) {
        int ans = 0, max = 0;
        for (int i = 0; i < arr.length; ++i) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                ans++;
            }
        }
        return ans;
    }


    @Test
    public void testarrayNesting() {
        assert 4 == arrayNesting(new int[]{5, 4, 0, 3, 1, 6, 2});
        assert 2 == arrayNesting(new int[]{0, 2, 1});
    }


    public int arrayNesting(int[] nums) {
        int max = 0;
        for (int k = 0; k < nums.length; k++) {
            int result = 0;
            int i = k;

            while (true) {
                if (nums[i] >= 0) {
                    int tmp = nums[i];
                    nums[i] = -1;
                    i = tmp;
                    result += 1;
                } else {
                    break;
                }
            }
            max = Math.max(result, max);
        }
        return max;
    }

    @Test
    public void testproductExceptSelf() {
        int[] ret = productExceptSelf(new int[]{1, 2, 3, 4});
        for (int r : ret) {
            System.out.println(r);
        }
    }


    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    @Test
    public void testfindPoisonedDuration() {
        assert 4 == findPoisonedDuration(new int[]{1, 4}, 2);
        assert 3 == findPoisonedDuration(new int[]{1, 2}, 2);
        assert 5 == findPoisonedDuration(new int[]{1, 4, 5}, 2);
    }

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int coldEndTime = 0;
        int coldtime = 0;
        for (int i = 0; i < timeSeries.length; i++) {
            if (coldEndTime > timeSeries[i]) {
                coldtime += duration - (coldEndTime - timeSeries[i]);
            } else {
                coldtime += duration;
            }
            coldEndTime = timeSeries[i] + duration;
        }
        return coldtime;
    }

    @Test
    public void testcheckPossibility() {
        assert checkPossibility(new int[]{4, 2, 3});
        assert checkPossibility(new int[]{1, 2, 3});
        assert !checkPossibility(new int[]{4, 2, 1});
    }

    public boolean checkPossibility(int[] nums) {
        int p = -1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < nums[i - 1]) {
                if (p != -1) {
                    return false;
                }
                p = i - 1;
            }
        }
        return p == -1 || p == 0 || p == nums.length - 2 ||
            nums[p + 1] > nums[p - 1] || nums[p + 2] > nums[p];
    }

    @Test
    public void testrotate() {
        int[] nums = new int[]{1, 2, 3};
        rotate(nums, 2);
        assert Arrays.equals(nums, new int[]{2, 3, 1});

        nums = new int[]{-1, -100, 3, 99};
        rotate(nums, 2);
        assert Arrays.equals(nums, new int[]{3, 99, -1, -100});

        nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 3);
        assert Arrays.equals(nums, new int[]{5, 6, 7, 1, 2, 3, 4});

        nums = new int[]{1, 2};
        rotate(nums, 3);
        assert Arrays.equals(nums, new int[]{2, 1});
    }


    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverseArray(nums, 0, nums.length - k - 1);
        reverseArray(nums, nums.length - k, nums.length - 1);
        reverseArray(nums, 0, nums.length - 1);
    }

    @Test
    public void testthirdMax() {
        assert thirdMax(new int[]{1, 2, 3}) == 1;
        assert thirdMax(new int[]{1, 2}) == 2;
        assert thirdMax(new int[]{2, 2, 3, 1}) == 1;
        assert thirdMax(new int[]{2, 1, Integer.MIN_VALUE}) == Integer.MIN_VALUE;

    }

    public int thirdMax(int[] nums) {
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        long max3 = Long.MIN_VALUE;

        for (int num : nums) {
            if (max1 == num || max2 == num || max3 == num) {
                continue;
            }

            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
        }

        if (max3 == Long.MIN_VALUE) {
            return (int) max1;
        }

        return (int) max3;
    }


    @Test
    public void testfindPairs() {
        assert findPairs(new int[]{3, 1, 4, 1, 5}, 2) == 2;
        assert findPairs(new int[]{1, 2, 3, 4, 5}, 1) == 4;
        assert findPairs(new int[]{1, 3, 1, 5, 4}, 0) == 1;
    }

    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        Map<Integer, List<Integer>> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = numMap.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            numMap.put(nums[i], list);
        }
        int c = 0;
        for (Map.Entry<Integer, List<Integer>> entry : numMap.entrySet()) {
            if (k == 0) {
                if (entry.getValue().size() >= 2) {
                    c++;
                }
            } else {
                if (numMap.get(entry.getKey() + k) != null) {
                    c++;
                }
            }
        }
        return c;
    }

    @Test
    public void testfindUnsortedSubarray() {
        assert 5 == findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15});
        assert 0 == findUnsortedSubarray(new int[]{1, 2, 3});
        assert 4 == findUnsortedSubarray(new int[]{2, 5, 4, 1, 7});
        assert 5 == findUnsortedSubarray(new int[]{3, 3, 3, 3, 1});
        assert 2 == findUnsortedSubarray(new int[]{1, 3, 2, 3, 3});

    }

    public int findUnsortedSubarray(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < nums[i - 1]) {
                min = Math.min(min, nums[i]);
            }
        }
        for (int i = nums.length - 2; i >= 0; --i) {
            if (nums[i] > nums[i + 1]) {
                max = Math.max(max, nums[i]);
            }
        }

        int l = 0;
        int r = nums.length - 1;
        while (l <= r && nums[l] <= min) {
            l++;
        }
        while (l <= r && nums[r] >= max) {
            r--;
        }
        return r - l + 1;
    }

    @Test
    public void testcanPlaceFlowers() {
        assert canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1);
        assert canPlaceFlowers(new int[]{1, 0, 0}, 1);
        assert !canPlaceFlowers(new int[]{0, 1, 0}, 1);
        assert !canPlaceFlowers(new int[]{0, 1, 0, 0, 1}, 1);
        assert canPlaceFlowers(new int[]{0, 0, 0, 0, 1}, 2);
        assert canPlaceFlowers(new int[]{0}, 1);

    }


    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int c = 0;
        for (int i = 0; i < flowerbed.length; ++i) {

            if (i == 0 && flowerbed[i] == 0 && i + 1 < flowerbed.length && flowerbed[i + 1] == 0) {
                flowerbed[i] = 1;
                c++;
            }

            if (i == 0 && flowerbed[i] == 0 && i + 1 == flowerbed.length) {
                flowerbed[i] = 1;
                c++;
            }

            if (i > 0 && flowerbed[i] == 0 && flowerbed[i - 1] == 0 && (i + 1 == flowerbed.length
                || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                c++;
            }
        }
        return c >= n;
    }

    @Test
    public void testmerge() {
        int[] num1 = new int[]{1, 2, 3, 0, 0, 0};
        merge(num1, 3, new int[]{4, 5, 6}, 3);
        for (int i : num1) {
            System.out.println(i);
        }

        num1 = new int[]{4, 5, 6, 0, 0, 0};
        merge(num1, 3, new int[]{1, 2, 3}, 3);
        for (int i : num1) {
            System.out.println(i);
        }

        num1 = new int[]{1, 3, 5, 0, 0, 0};
        merge(num1, 3, new int[]{2, 4, 6}, 3);
        for (int i : num1) {
            System.out.println(i);
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 && j >= 0) {
            nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    @Test
    public void testcontainsNearbyDuplicate() {
        assert containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3);
        assert containsNearbyDuplicate(new int[]{1, 0, 0, 1, 1, 1}, 2);
        assert !containsNearbyDuplicate(new int[]{1, 0, 2, 3, 1}, 2);
        assert !containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2);
    }


    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }


    @Test
    public void testnumMagicSquaresInside() {
        assert 1 == numMagicSquaresInside(new int[][]{
            {
                4, 3, 8, 4,
            },
            {
                9, 5, 1, 9
            }
            ,
            {
                2, 7, 6, 2
            }
        });

        assert 1 == numMagicSquaresInside(new int[][]{
            {
                4, 3, 8, 4,
            },
            {
                9, 5, 1, 9
            }
            ,
            {
                2, 7, 6, 12
            }
        });
    }

    private boolean magicSquare(int i, int j, int[][] grid) {
        int[] distinctNumbers = new int[9];
        if (i + 2 < grid.length && j + 2 < grid[i].length) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (0 < grid[i + x][j + y] && grid[i + x][j + y] < 10) {
                        distinctNumbers[grid[i + x][j + y] - 1] += 1;
                    }

                }
            }
            for (int x = 0; x < distinctNumbers.length; x++) {
                if (distinctNumbers[x] != 1) {
                    return false;
                }
            }

            if (grid[i][j] + grid[i][j + 1] + grid[i][j + 2] != 15
                || grid[i + 1][j] + grid[i + 1][j + 1] + grid[i + 1][j + 2] != 15
                || grid[i + 2][j] + grid[i + 2][j + 1] + grid[i + 2][j + 2] != 15
                || grid[i][j] + grid[i + 1][j] + grid[i + 2][j] != 15
                || grid[i][j + 1] + grid[i + 1][j + 1] + grid[i + 2][j + 1] != 15
                || grid[i][j + 2] + grid[i + 1][j + 2] + grid[i + 2][j + 2] != 15
                || grid[i][j] + grid[i + 1][j + 1] + grid[i + 2][j + 2] != 15
                || grid[i][j + 2] + grid[i + 1][j + 1] + grid[i + 2][j] != 15) {
                return false;
            }

            return true;
        }

        return false;
    }

    public int numMagicSquaresInside(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length - 2; ++j) {
                count += magicSquare(i, j, grid) ? 1 : 0;
            }
        }
        return count;
    }

    @Test
    public void testremoveDuplicates() {
        assert removeDuplicates(new int[]{1, 1, 2}) == 2;
        assert removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}) == 5;

    }

    public int removeDuplicates(int[] nums) {
        int index = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] != nums[i - 1]) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    @Test
    public void testfindMaxAverage() {
        assert findMaxAverage(new int[]{1, 2, 3, 4}, 2) == 3.5;
        assert findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4) == 12.75;

    }

    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        double max = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
            max = sum;
        }

        for (int i = k; i < nums.length; ++i) {
            sum = sum - nums[i - k] + nums[i];
            max = Math.max(max, sum);
        }
        return max / k;
    }

    @Test
    public void testgetRow() {
        assert getRow(1).size() == 2;
        assert getRow(3).size() == 4;

    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> last = new ArrayList<>();
        for (int i = 0; i <= rowIndex; ++i) {
            List<Integer> current = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    current.add(1);
                } else {
                    current.add(last.get(j - 1) + last.get(j));
                }
            }
            last = current;
        }
        return last;
    }

    @Test
    public void testpivotIndex() {
        assert pivotIndex(new int[]{1, 7, 3, 6, 5, 6}) == 3;
        assert pivotIndex(new int[]{1, 2, 3}) == -1;
        assert pivotIndex(new int[]{-1, -1, -1, 0, 1, 1}) == 0;

    }

    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
        }

        int left = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (left == sum - left - nums[i]) {
                return i;
            }
            left += nums[i];
        }
        return -1;
    }

    @Test
    public void testplusOne() {
        int[] a = plusOne(new int[]{1, 1, 1});
        Arrays.stream(a).forEach(System.out::println);

        a = plusOne(new int[]{1, 1, 2});
        Arrays.stream(a).forEach(System.out::println);

        a = plusOne(new int[]{9, 9, 9, 9});
        Arrays.stream(a).forEach(System.out::println);
    }

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; --i) {
            if (digits[i] < 9) {
                digits[i] += 1;
                return digits;
            }
            digits[i] = 0;
        }

        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    @Test
    public void test() {
        assert searchInsert(new int[]{1, 2, 3, 4}, 3) == 2;
        assert searchInsert(new int[]{1, 2, 3, 4}, 0) == 0;
        assert searchInsert(new int[]{1, 2, 3, 4}, 5) == 4;
        assert searchInsert(new int[]{1}, 1) == 0;
    }

    public int searchInsert(int[] nums, int target) {
        int ret = Arrays.binarySearch(nums, target);
        if (ret >= 0) {
            return ret;
        }
        return -(ret + 1);
    }


    @Test
    public void testgenerate() {
        List<List<Integer>> ret = generate(5);
        for (List<Integer> l : ret) {
            System.out.println(l);
        }
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    list.add(results.get(i - 1).get(j - 1) + results.get(i - 1).get(j));
                }
            }
            results.add(list);
        }
        return results;
    }

    @Test
    public void testremoveElement() {
        assert removeElement(new int[]{1, 2, 4, 5, 5, 6}, 5) == 4;
    }

    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    @Test
    public void testdominantIndex() {
        assert dominantIndex(new int[]{3, 6, 1, 0}) == 1;
    }

    public int dominantIndex(int[] nums) {
        int max = -1;
        int maxIndex = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != max && max < 2 * nums[i]) {
                return -1;
            }
        }
        return maxIndex;
    }

    @Test
    public void testfindLengthOfLCIS() {
        assert findLengthOfLCIS(new int[]{1, 2, 4}) == 3;
        assert findLengthOfLCIS(new int[]{1, 2, 1, 5}) == 2;
        assert findLengthOfLCIS(new int[]{1, 2, 1, 5, 7}) == 3;
        assert findLengthOfLCIS(new int[]{1, 1, 1, 1}) == 1;

    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int cur = 1;
        int max = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[i - 1]) {
                cur += 1;
                max = Math.max(max, cur);
            } else {
                cur = 1;
            }
        }
        return max;
    }

    @Test
    public void testmaximumProduct() {
        assert maximumProduct(new int[]{1, 2, 3}) == 6;
        assert maximumProduct(new int[]{3, 2, 1}) == 6;
        assert maximumProduct(new int[]{-1, -2, -3}) == -6;
        assert maximumProduct(new int[]{-1, -5, 1, 2, 3}) == 15;
        assert maximumProduct(new int[]{-1, -25, 1, 2, 4}) == 100;
        assert maximumProduct(new int[]{-1, -6, 10, 2, 4}) == 80;
    }

    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }

            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }

        }
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }

    @Test
    public void testmissingNumber() {
        assert missingNumber(new int[]{0, 3, 2}) == 1;
        assert missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}) == 8;
    }

    public int missingNumber(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
        }
        int expectedSum = (0 + nums.length) * (nums.length + 1) / 2;
        return expectedSum - sum;
    }

    @Test
    public void testimageSmoother() {
        int[][] ret = imageSmoother(new int[][]{
            {
                1, 1, 1
            },
            {
                1, 1, 1
            },
            {
                1, 1, 1
            }
        });
        for (int[] a : ret) {
            Arrays.stream(a).forEach(i -> System.out.println(i));
            System.out.println("-------");
        }

    }

    public int[][] imageSmoother(int[][] M) {
        int[][] ret = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; ++i) {
            for (int j = 0; j < M[0].length; ++j) {
                double sum = M[i][j];
                int size = 1;
                // up
                if (i - 1 >= 0) {
                    sum += M[i - 1][j];
                    size++;
                }
                // down
                if (i + 1 < M.length) {
                    sum += M[i + 1][j];
                    size++;
                }
                // left
                if (j - 1 >= 0) {
                    sum += M[i][j - 1];
                    size++;
                }
                // right
                if (j + 1 < M[i].length) {
                    sum += M[i][j + 1];
                    size++;
                }
                // up left
                if (i - 1 >= 0 && j - 1 >= 0) {
                    sum += M[i - 1][j - 1];
                    size++;
                }
                // up right
                if (i - 1 >= 0 && j + 1 < M[i - 1].length) {
                    sum += M[i - 1][j + 1];
                    size++;
                }
                // down left
                if (i + 1 < M.length && j - 1 >= 0) {
                    sum += M[i + 1][j - 1];
                    size++;
                }
                // down right
                if (i + 1 < M.length && j + 1 < M[i + 1].length) {
                    sum += M[i + 1][j + 1];
                    size++;
                }
                ret[i][j] = (int) Math.floor(sum / size);
            }
        }
        return ret;
    }

    @Test
    public void testfindShortestSubArray() {
        assert findShortestSubArray(new int[]{1, 1, 2, 2, 1, 2, 2, 4, 4, 4, 4}) == 4;
    }

    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> left = new HashMap<>(), right = new HashMap<>(), count = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int x = nums[i];
            if (left.get(x) == null) {
                left.put(x, i);
            }
            right.put(x, i);
            count.put(x, count.getOrDefault(x, 0) + 1);
        }

        int ans = nums.length;
        int degree = Collections.max(count.values());
        for (int x : count.keySet()) {
            if (count.get(x) == degree) {
                ans = Math.min(ans, right.get(x) - left.get(x) + 1);
            }
        }

        return ans;
    }

    @Test
    public void testtwoSumSorted() {
        int[] ret = twoSumSorted(new int[]{2, 7, 11, 15}, 9);
        assert ret[0] == 1;
        assert ret[1] == 2;

        ret = twoSumSorted(new int[]{2, 8, 11, 19}, 19);
        assert ret[0] == 2;
        assert ret[1] == 3;
    }

    public int[] twoSumSorted(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        int[] ret = new int[2];
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                ret[0] = l + 1;
                ret[1] = r + 1;
                break;
            }
            if (numbers[l] + numbers[r] > target) {
                r--;
            } else if (numbers[l] + numbers[r] < target) {
                l++;
            }
        }
        return ret;
    }

    @Test
    public void testlargeGroupPositions() {
        assert largeGroupPositions("aa").size() == 0;
        assert largeGroupPositions("aaa").size() == 1;
        assert largeGroupPositions("aaabbbb").size() == 2;
        assert largeGroupPositions("abbxxxxzzy").size() == 1;
        assert largeGroupPositions("abbxxxxzzy").get(0).get(0) == 3;
        assert largeGroupPositions("abbxxxxzzy").get(0).get(1) == 6;

    }

    public List<List<Integer>> largeGroupPositions(String S) {
        char[] chars = S.toCharArray();
        int start = 0;
        int end = 0;
        List<List<Integer>> result = new ArrayList<>();

        while (end < chars.length) {
            while (end < chars.length && chars[end] == chars[start]) {
                end++;
            }
            if (end - start > 2) {
                result.add(Arrays.asList(start, end - 1));
            }
            start = end;
        }

        return result;
    }

    @Test
    public void testisOneBitCharacter() {
        assert true == isOneBitCharacter(new int[]{1, 0, 0});
        assert true == isOneBitCharacter(new int[]{1, 0, 0, 0});
        assert false == isOneBitCharacter(new int[]{1, 0, 1, 0});
        assert false == isOneBitCharacter(new int[]{0, 1, 0, 1, 0});

    }

    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        while (i < bits.length - 1) {
            i += bits[i] + 1;
        }
        if (i >= bits.length) {
            return false;
        }
        return true;
    }

    @Test
    public void testMoveZeroes() {
        int[] nums = new int[]{1, 1, 1, 1};
        moveZeroes(nums);
        Arrays.stream(nums).forEach(i -> System.out.println(i));
        System.out.println("--------");
        nums = new int[]{1, 1, 1, 0};
        moveZeroes(nums);
        Arrays.stream(nums).forEach(i -> System.out.println(i));
        System.out.println("--------");
        nums = new int[]{0, 1, 1, 0};
        moveZeroes(nums);
        Arrays.stream(nums).forEach(i -> System.out.println(i));
        System.out.println("--------");

        nums = new int[]{0, 1, 0, 3, 12};
        moveZeroes(nums);
        Arrays.stream(nums).forEach(i -> System.out.println(i));
        System.out.println("--------");

    }

    public void moveZeroes(int[] nums) {
        int nonZeroNumber = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                nums[nonZeroNumber++] = nums[i];
            }
        }
        for (int i = nonZeroNumber; i < nums.length; ++i) {
            nums[i] = 0;
        }
    }


    @Test
    public void testmaxAreaOfIsland() {

        assert 3 == maxAreaOfIsland(new int[][]{
            {
                0, 1,
            },
            {
                1, 1
            }
        });

        assert 5 == maxAreaOfIsland(new int[][]{
            {
                0, 0, 0, 0, 0
            },
            {
                1, 1, 1, 1, 1
            }
        });

        assert 0 == maxAreaOfIsland(new int[][]{
            {
                0, 0, 0, 0, 0
            }
        });

        assert 6 == maxAreaOfIsland(new int[][]{
            {
                0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0
            },
            {
                0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0
            },
            {
                0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0
            },
            {
                0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0
            },
            {
                0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0
            },
            {
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0
            },
            {
                0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0
            },
            {
                0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0
            }
        });
    }

    private int findGround(int[][] grid, int r, int c, int[][] visited) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{r, c});
        int size = 1;
        while (stack.size() > 0) {
            int[] item = stack.pop();
            r = item[0];
            c = item[1];
            if (r - 1 >= 0 && visited[r - 1][c] == 0 && grid[r - 1][c] == 1) {
                stack.push(new int[]{r - 1, c});
                visited[r - 1][c] = 1;
                size++;
            }
            if (r + 1 < grid.length && visited[r + 1][c] == 0 && grid[r + 1][c] == 1) {
                stack.push(new int[]{r + 1, c});
                visited[r + 1][c] = 1;
                size++;
            }
            if (c - 1 >= 0 && visited[r][c - 1] == 0 && grid[r][c - 1] == 1) {
                stack.push(new int[]{r, c - 1});
                visited[r][c - 1] = 1;
                size++;
            }
            if (c + 1 < grid[r].length && visited[r][c + 1] == 0 && grid[r][c + 1] == 1) {
                stack.push(new int[]{r, c + 1});
                visited[r][c + 1] = 1;
                size++;
            }
        }
        return size;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int[][] visited = new int[grid.length][grid[0].length];
        int max = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                if (visited[i][j] == 0) {
                    visited[i][j] = 1;
                    if (grid[i][j] == 1) {
                        max = Math.max(findGround(grid, i, j, visited), max);
                    }
                }
            }
        }
        return max;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int cur = 0;
        int max = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 1) {
                cur++;
                max = Math.max(cur, max);
            } else {
                cur = 0;
            }
        }
        return max;
    }

    @Test
    public void testmatrixReshape() {
        int[][] ret = matrixReshape(new int[][]{
            {
                1, 2
            },
            {
                3, 4
            }
        }, 1, 4);
        for (int[] a : ret) {
            Arrays.stream(a).forEach(i -> System.out.println(i));
            System.out.println("-------");
        }

        ret = matrixReshape(new int[][]{
            {
                1, 2
            },
            {
                3, 4
            }
        }, 2, 4);
        for (int[] a : ret) {
            Arrays.stream(a).forEach(i -> System.out.println(i));
            System.out.println("-------");
        }

        ret = matrixReshape(new int[][]{
            {
                1, 2, 3
            },
            {
                3, 4, 5
            }
        }, 3, 2);
        for (int[] a : ret) {
            Arrays.stream(a).forEach(i -> System.out.println(i));
            System.out.println("-------");
        }
    }

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums[0].length * nums.length != r * c) {
            return nums;
        }

        int[][] ret = new int[r][c];
        int size = 0;
        for (int[] rows : nums) {
            for (int v : rows) {
                ret[size / c][size % c] = v;
                size++;
            }
        }
        return ret;
    }

    @Test
    public void testisToeplitzMatrix() {
        assert true == isToeplitzMatrix(new int[][]{
            {
                1, 2, 3, 4
            },
            {
                5, 1, 2, 3
            },
            {
                9, 5, 1, 2
            }
        });

        assert false == isToeplitzMatrix(new int[][]{
            {
                1, 2
            },
            {
                2, 2
            }

        });

        assert true == isToeplitzMatrix(new int[][]{
            {
                16
            },
            {
                18
            }

        });
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

//    public boolean isToeplitzMatrix(int[][] matrix) {
//        int m = matrix.length;
//        int n = matrix[0].length;
//        for (int i = 0; i < m; i++) {
//            int k = 1;
//            for (int j = i + 1; j < m && k < n; j++) {
//                if (matrix[j][k++] != matrix[i][0]) {
//                    return false;
//                }
//            }
//        }
//
//        for (int i = 1; i < n; i++) {
//            int k = 1;
//            for (int j = i + 1; k < m && j < n; j++) {
//                if (matrix[k++][j] != matrix[0][i]) {
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    @Test
    public void testflipAndInvertImage() {
        int[][] ret = flipAndInvertImage(new int[][]{
            {
                1, 1, 0
            },
            {
                1, 0, 1
            },
            {
                0, 0, 0
            }
        });
        for (int[] a : ret) {
            Arrays.stream(a).forEach(i -> System.out.println(i));
            System.out.println("-------");
        }
    }

    public int[][] flipAndInvertImage(int[][] A) {
        for (int[] a : A) {
            int i = 0;
            int j = a.length - 1;
            while (i < j) {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                a[i] = a[i] == 1 ? 0 : 1;
                a[j] = a[j] == 1 ? 0 : 1;
                i++;
                j--;
            }

            if (i == j) {
                a[i] = a[i] == 1 ? 0 : 1;
            }
        }
        return A;
    }

    @Test
    public void reverse() {
        assert reverseWords("the sky is blue").equals("blue is sky the");
        assert reverseWords("aa       a").equals("a aa");
        assert reverseWords(" a ab ").equals("ab a");
    }

    private void reverseChars(char[] chars, int l, int r) {
        while (l < r) {
            char tmp = chars[l];
            chars[l] = chars[r];
            chars[r] = tmp;
            l++;
            r--;
        }
    }

    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        reverseChars(chars, 0, chars.length - 1);
        int i = 0, j = 0;
        while (j < chars.length) {
            while (i < j || (i < chars.length && chars[i] == ' ')) {
                i++;
            }
            while (j < i || (j < chars.length && chars[j] != ' ')) {
                j++;
            }
            reverseChars(chars, i, j - 1);
        }
        return cleanInnerSpaces(chars);
    }

    private String cleanInnerSpaces(char[] chars) {
        int i = 0;
        int j = 0;
        while (j < chars.length) {
            while (j < chars.length && chars[j] == ' ') {
                j++;
            }
            while (j < chars.length && chars[j] != ' ') {
                chars[i++] = chars[j++];
            }
            while (j < chars.length && chars[j] == ' ') {
                j++;
            }
            if (j < chars.length) {
                chars[i++] = ' ';
            }
        }
        return new String(chars, 0, i);
    }

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder().append(val);
            ListNode tmp = next;
            while (tmp != null) {
                sb.append(tmp.val);
                tmp = tmp.next;
            }
            return sb.toString();
        }

    }

    @Test
    public void testmajorityElement() {
        assert majorityElement(new int[]{1, 2, 2, 2, 2}) == 2;
        assert majorityElement(new int[]{2, 21, 1, 1, 2, 2, 2}) == 2;

    }

    public int majorityElement(int[] nums) {
        int count = 0;
        int result = 0;
        for (int n : nums) {
            if (count == 0) {
                result = n;
            }
            count += (result == n) ? 1 : -1;
        }
        return result;
    }

    @Test
    public void testmostCommonWord() {
        assert "ball"
            .equals(mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"}));
    }

    public String mostCommonWord(String paragraph, String[] banned) {

        String replaced = paragraph.replaceAll("[!?',;.]", "");
        Set<String> banSet = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> map = new HashMap<>();

        int maxCount = 0;
        String max = "";

        for (String s : replaced.split(" ")) {
            String tmp = s.toLowerCase();
            if (!banSet.contains(tmp)) {
                map.put(tmp, map.getOrDefault(tmp, 0) + 1);
                if (map.get(tmp) > maxCount) {
                    max = tmp;
                    maxCount = map.get(tmp);
                }
            }
        }

        return max;
    }

    @Test
    public void testlongestPalindrome() {
        assert longestPalindrome("a").equals("a");
        assert longestPalindrome("aa").equals("aa");
        assert longestPalindrome("ab").equals("a");
        assert longestPalindrome("babad").equals("bab");
        assert longestPalindrome("cbbd").equals("bb");
        assert longestPalindrome("dbabc").equals("bab");

    }

    public String longestPalindrome(String s) {

        StringBuilder sb = new StringBuilder("#");
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i)).append("#");
        }
        s = sb.toString();

        int id = 0;
        String max = "";
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            // manacher's algorithm
            /**
             * mx----j------id------i----mx : dp[i]=mx-i
             * mx------i----id----i------mx : dp[i]=dp[2 * id - i]
             * mx-id-mx------i------: dp[i] = 1
             */
            dp[i] = i < (id + max.length() / 2) ? Math.min(dp[2 * id - i], id + max.length() / 2 - i) : 1;
            String tmp = palindrome(s, i, dp);
            if (tmp.length() > max.length()) {
                max = tmp;
                id = i;
            }
        }

        return max.replace("#", "");

    }

    public String palindrome(String s, int i, int[] dp) {
        while (i - dp[i] >= 0 && i + dp[i] < s.length() && s.charAt(i - dp[i]) == s.charAt(i + dp[i])) {
            dp[i]++;
        }
        return s.substring(i - dp[i] + 1, i + dp[i] - 1);
    }

    @Test
    public void testtrap() {
        assert trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}) == 6;
    }

    public int trap(int[] height) {
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = height.length - 1;
        int total = 0;
        while (left < right) {
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);
            if (leftMax <= rightMax) {
                total += leftMax;
                total -= height[left];
                left++;
            } else {
                total += rightMax;
                total -= height[right];
                right--;
            }
        }
        return total;
    }


    @Test
    public void testfirstMissingPositive() {
        assert firstMissingPositive(new int[]{1, 2, 4}) == 3;
        assert firstMissingPositive(new int[]{1, 2, 0}) == 3;
        assert firstMissingPositive(new int[]{1, 2, 3}) == 4;
        assert firstMissingPositive(new int[]{2, -2, -2, 4}) == 1;
        assert firstMissingPositive(new int[]{-1, -2, -2, 1, 2, 5, 100}) == 3;

    }

    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] <= 0) {
                nums[i] = nums.length + 1;
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] <= 0) {
                continue;
            }

            int n = Math.abs(nums[i]) - 1;
            if (n >= 0 && n < nums.length && nums[n] > 0) {
                nums[n] *= -1;
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        return nums.length + 1;

    }


    @Test
    public void testfindDuplicates() {
        assert 3 == findDuplicates(new int[]{1, 2, 4, 5, 3, 3}).get(0);
        assert 3 == findDuplicates(new int[]{1, 2, 4, 6, 3, 3}).get(0);

    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                res.add(Math.abs(index + 1));
            }
            nums[index] = -nums[index];
        }
        return res;
    }

    @Test
    public void testfindDisappearedNumbers() {
        assert findDisappearedNumbers(new int[]{1, 2, 3, 3, 5}).size() == 1;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ret = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1;
            if (nums[idx] > 0) {
                nums[idx] *= -1;
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0) {
                ret.add(i + 1);
            }
        }

        return ret;
    }

    @Test
    public void testuniqueMorseRepresentations() {
        assert uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}) == 2;
    }

    public int uniqueMorseRepresentations(String[] words) {
        String[] morseMap = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
            ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> morseWord = new HashSet<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (char c : word.toCharArray()) {
                sb.append(morseMap[c - 'a']);
            }
            morseWord.add(sb.toString());
        }
        return morseWord.size();
    }

    @Test
    public void testnumJewelsInStones() {
        assert numJewelsInStones("A", "B") == 0;
        assert numJewelsInStones("A", "A") == 1;
        assert numJewelsInStones("aj", "bbbaJ") == 1;

    }

    public int numJewelsInStones(String J, String S) {
        int count = 0;
        for (char c : J.toCharArray()) {
            for (char d : S.toCharArray()) {
                count += c == d ? 1 : 0;
            }
        }
        return count;
    }

    @Test
    public void testfindLongestChain() {
        assert findLongestChain(new int[][]{
            {
                1, 4
            },
            {
                2, 3
            }
        }) == 1;

        assert findLongestChain(new int[][]{
            {
                1, 2
            },
            {
                3, 4
            }
        }) == 2;

        assert findLongestChain(new int[][]{
            {
                2, 4
            },
            {
                1, 2
            },
            {
                6, 9
            },
            {
                3, 5
            }
        }) == 3;

        assert findLongestChain(new int[][]{
            {
                -1, 1
            },
            {
                -2, 7
            },
            {
                -5, 8
            },
            {
                -3, 8
            },
            {
                1, 3
            },
            {
                -2, 9
            },
            {
                -5, 2
            }
        }) == 1;
    }

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (o1, o2) -> o1[1] != o2[1] ? o1[1] - o2[1] : o1[0] - o2[0]);

        int max = pairs[0][1];
        int length = 1;
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > max) {
                max = pairs[i][1];
                length++;
            }
        }
        return length;
    }


    @Test
    public void testfindKthLargest() {
        assert 6 == findKthLargest(new int[]{1, 2, 4, 5, 6}, 1);
        assert 6 == findKthLargest(new int[]{1, 2, 10, 5, 6}, 2);
        assert 1 == findKthLargest(new int[]{1, 1}, 1);

    }

    public int findKthLargest(int[] nums, int l, int r, int k) {
        int pivot = nums[l];
        int left = l;
        int right = r;
        while (l < r) {
            while (l < r && nums[r] >= pivot) {
                r--;
            }
            if (l < r) {
                nums[l] = nums[r];
                l++;
            }
            while (l < r && nums[l] < pivot) {
                l++;
            }
            if (l < r) {
                nums[r] = nums[l];
                r--;
            }
        }
        nums[l] = pivot;

        int size = right - l + 1;
        if (size == k) {
            return pivot;
        } else if (size > k) {
            return findKthLargest(nums, l + 1, right, k);
        } else {
            return findKthLargest(nums, left, l - 1, k - size);
        }
    }


    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, k);
    }

    @Test
    public void testTwoSum() {
        assert twoSum(new int[]{1, 2, 3}, 2) == null;
        assert twoSum(new int[]{1, 2, 3, 5}, 3)[0] == 0;
        assert twoSum(new int[]{1, 2, 3, 5}, 3)[1] == 1;
        assert twoSum(new int[]{1, 2, 3, 5}, 8)[0] == 2;

    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] ret = null;
        for (int i = 0; i < nums.length; ++i) {
            if (map.get(target - nums[i]) != null) {
                ret = new int[2];
                ret[0] = map.get(target - nums[i]);
                ret[1] = i;
                break;
            } else {
                map.put(nums[i], i);
            }
        }
        return ret;
    }

    @Test
    public void testFourSum() {
        assert fourSum(new int[]{-1, 0, 1, 0}, 0).size() == 1;
        assert 0 == fourSum(new int[]{-1, 0, 1, 0}, 0).get(0).stream().reduce(Integer::sum).get();
        assert fourSum(new int[]{-1, 0, 1, 2}, 2).size() == 1;
        assert fourSum(new int[]{-2, 0, 0, -1, 1, 2}, 0).size() == 3;
    }

    @Test
    public void testthreeSum() {
        assert threeSum(new int[]{-1, 0, 1}).size() == 1;
        assert 0 == threeSum(new int[]{-1, 0, 1}).get(0).stream().reduce(Integer::sum).get();
        assert threeSum(new int[]{-1, 0, 0, 1, 2, -1}).size() == 2;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0;
        List<List<Integer>> result = new ArrayList<>();

        while (i < nums.length - 3) {
            List<List<Integer>> ret = threeSum(nums, i + 1, target - nums[i]);
            if (ret.size() > 0) {
                for (List<Integer> l : ret) {
                    l.add(0, nums[i]);
                }
                result.addAll(ret);
            }
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
            i++;
        }

        return result;
    }


    public List<List<Integer>> threeSum(int[] nums, int start, int target) {
        List<List<Integer>> list = new LinkedList<>();
        for (int i = start; i < nums.length - 2; i++) {
            if (i == start || nums[i] != nums[i - 1]) {
                int low = i + 1;
                int high = nums.length - 1;
                while (low < high) {
                    int sum = target - nums[i];
                    if (nums[low] + nums[high] == sum) {
                        List<Integer> sub = new ArrayList<>();
                        sub.add(nums[i]);
                        sub.add(nums[low]);
                        sub.add(nums[high]);
                        list.add(sub);
                        while (low < high && nums[low] == nums[low + 1]) {
                            low++;
                        }
                        while (low < high && nums[high] == nums[high - 1]) {
                            high--;
                        }
                        low++;
                        high--;
                    } else if (nums[low] + nums[high] < sum) {
                        low++;
                    } else {
                        high--;
                    }
                }
            }
        }
        return list;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return threeSum(nums, 0, 0);
    }

    public List<List<Integer>> threeSumRecursive(int[] nums, int start, int count, int sum) {

        if (count == 0 && sum == 0) {
            List<List<Integer>> list = new ArrayList<>();
            list.add(new ArrayList<>());
            return list;
        }

        if (count == 0 && sum != 0) {
            return null;
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = start; i < nums.length; ++i) {
            List<List<Integer>> includeCur = threeSumRecursive(nums, i + 1, count - 1, sum - nums[i]);
            List<List<Integer>> excludeCur = threeSumRecursive(nums, i + 1, count, sum);
            if (includeCur != null) {
                for (List<Integer> l : includeCur) {
                    l.add(nums[i]);
                }
                result.addAll(includeCur);
            }
            if (excludeCur != null) {
                result.addAll(excludeCur);
            }
        }
        return result;
    }

    @Test
    public void testlongestCommonPrefix() {
        assert longestCommonPrefix(new String[]{"a", "b"}).equals("");
        assert longestCommonPrefix(new String[]{"a", "ab"}).equals("a");
        assert longestCommonPrefix(new String[]{"aa", "a"}).equals("a");
        assert longestCommonPrefix(new String[]{"ab", "ab", "abc"}).equals("ab");
        assert longestCommonPrefix(new String[]{"a", "b", "caaaa"}).equals("");
        assert longestCommonPrefix(new String[]{"flower", "flow", "flight"}).equals("fl");

    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        int i;
        for (String s : strs) {
            if (prefix == "") {
                break;
            }
            for (i = 0; i < prefix.length(); i++) {
                if (i >= s.length() || prefix.charAt(i) != s.charAt(i)) {
                    break;
                }
            }
            prefix = prefix.substring(0, i);
        }

        return prefix;
    }

    @Test
    public void testlargestSumOfAverages() {
        assert 1 == largestSumOfAverages(new int[]{1}, 1);
        assert 1.5 == largestSumOfAverages(new int[]{1, 2}, 1);
        assert 3 == largestSumOfAverages(new int[]{1, 2}, 2);
        assert 7 == largestSumOfAverages(new int[]{1, 2, 4}, 3);
        assert 20 == largestSumOfAverages(new int[]{9, 1, 2, 3, 9}, 3);

    }

    public double largestSumOfAverages(int[] A, int K) {
        double sum[] = new double[A.length + 1];
        double s = 0;
        for (int i = 1; i <= A.length; ++i) {
            sum[i] = s + A[i - 1];
            s += A[i - 1];
        }

        double[][] dp = new double[A.length + 1][K + 1];
        for (int i = 1; i <= A.length; ++i) {
            dp[i][1] = (sum[A.length] - sum[i - 1]) / (A.length - i + 1);
        }

        for (int j = 2; j <= K; j++) {
            for (int i = 1; i <= A.length; ++i) {
                for (int l = i + 1; l <= A.length; l++) {
                    dp[i][j] = Math.max(dp[i][j], (sum[l - 1] - sum[i - 1]) / (l - i) + dp[l][j - 1]);
                }
            }
        }

        return dp[1][K];

    }

    public double recursiveLSA(double[] sum, int[] A, int start, int K) {
        if (K == 1) {
            return (sum[A.length] - sum[start - 1]) / (A.length - start + 1);
        }
        double max = 0;
        for (int i = start; i <= A.length - K + 1; ++i) {
            double avg = (sum[i] - sum[start - 1]) / (i - start + 1);
            max = Math.max(max, avg + recursiveLSA(sum, A, i + 1, K - 1));
        }
        return max;
    }

    @Test
    public void testlengthOfLIS() {
        assert 1 == lengthOfLIS(new int[]{1});
        assert 1 == lengthOfLIS(new int[]{1, 1, 1, 1, 1});
        assert 2 == lengthOfLIS(new int[]{1, 2});
        assert 3 == lengthOfLIS(new int[]{1, 2, 3});
        assert 4 == lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
        assert 4 == lengthOfLIS(new int[]{10, 100, 1, 1001, 5, 7, 24});

    }

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = 0;
        int dp[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int k = Arrays.binarySearch(dp, 0, max, nums[i]);
            if (k < 0) {
                k = -(k + 1);
            }
            dp[k] = nums[i];
            if (k == max) {
                ++max;
            }
        }
        return max;
    }

//    public int lengthOfLIS(int[] nums) {
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }
//
//        int max = 1;
//        int dp[] = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            dp[i] = 1;
//            for (int j = 0; j < i; j++) {
//                if (nums[j] < nums[i]) {
//                    dp[i] = Math.max(dp[i], 1 + dp[j]);
//                    max = Math.max(dp[i], max);
//                }
//            }
//        }
//        return max;
//    }

//    public int lengthOfLIS(int[] nums) {
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }
//
//        int max = 1;
//        int dp[] = new int[nums.length];
//        for (int i = nums.length - 1; i >= 0; i--) {
//            dp[i] = 1;
//            for (int j = nums.length - 1; j > i; j--) {
//                if (nums[j] > nums[i]) {
//                    dp[i] = Math.max(dp[i], 1 + dp[j]);
//                    max = Math.max(dp[i], max);
//                }
//            }
//        }
//        return max;
//    }

    @Test
    public void testreverseList() {
        ListNode head = new ListNode(1);
        head = reverseList(head);
        assert head.toString().equals("1");

        head = new ListNode(1);
        head.next = new ListNode(2);
        head = reverseList(head);
        assert head.toString().equals("21");

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head = reverseList(head);
        assert head.toString().equals("321") : head.toString();

    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = head.next;
        head.next = null;
        ListNode headRest = reverseList(next);
        next.next = head;
        return headRest;

    }

//    public ListNode reverseList(ListNode head) {
//        if (head == null) {
//            return head;
//        }
//
//        ListNode pre = null;
//        ListNode cur;
//        ListNode next;
//        cur = head;
//        next = cur.next;
//        head.next = null;
//        while (cur != null) {
//            if (pre != null) {
//                cur.next = pre;
//            }
//            pre = cur;
//            cur = next;
//            if (cur != null) {
//                next = cur.next;
//            }
//        }
//        return pre;
//    }


    @Test
    public void testlongestValidParentheses() {
        assert longestValidParentheses("()") == 2;
        assert longestValidParentheses("()()") == 4;
        assert longestValidParentheses("())") == 2;
        assert longestValidParentheses("(()") == 2;
        assert longestValidParentheses("()()(()") == 4;
        assert longestValidParentheses("()())") == 4;
        assert longestValidParentheses("()(()") == 2;
        assert longestValidParentheses("()(())") == 6;
        assert longestValidParentheses("())()()") == 4;
        assert longestValidParentheses(")()())") == 4;
        assert longestValidParentheses("(()((((()") == 2;

    }

    public int longestValidParentheses(String s) {
        int[] longest = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == ')' && i - longest[i - 1] - 1 >= 0 && s.charAt(i - longest[i - 1] - 1) == '(') {
                longest[i] =
                    longest[i - 1] + 2 + ((i - longest[i - 1] - 2) >= 0 ? longest[i - longest[i - 1] - 2] : 0);
                max = Math.max(max, longest[i]);
            }
        }
        return max;
    }


    @Test
    public void testRob() {
        assert 1 == rob(new int[]{1});
        assert 2 == rob(new int[]{1, 2});
        assert 6 == rob(new int[]{1, 2, 5});
        assert 107 == rob(new int[]{1, 2, 5, 100, 101});

    }

    public int rob(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[1], nums[0]);
        }

        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        sum[1] = Math.max(nums[1], nums[0]);

        for (int i = 2; i < nums.length; i++) {
            sum[i] = Math.max(sum[i - 1], sum[i - 2] + nums[i]);
        }

        return sum[nums.length - 1];
    }

    @Test
    public void testCheckSubarraySum() {
        assert true == checkSubarraySum(new int[]{23, 2, 4, 6, 8}, 6);
        assert true == checkSubarraySum(new int[]{23, 2, 4, 6, 9}, 6);
        assert true == checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6);
        assert false == checkSubarraySum(new int[]{23, 27, 2}, 3);
        assert false == checkSubarraySum(new int[]{0}, 0);
        assert true == checkSubarraySum(new int[]{0, 0}, 0);
        assert true == checkSubarraySum(new int[]{1, 0, 0}, 0);

    }

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>() {{
            put(0, -1);
        }};

        int runningSum = 0;
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            if (k != 0) {
                runningSum %= k;
            }
            Integer prev = map.get(runningSum);
            if (prev != null) {
                if (i - prev > 1) {
                    return true;
                }
            } else {
                map.put(runningSum, i);
            }
        }
        return false;
    }

    public boolean checkSubarraySum2(int[] nums, int k) {
        if (nums == null) {
            return false;
        }

        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            sum[i] = sum[i - 1] + nums[i];
            if (sum[i] == k || (k != 0 && sum[i] % k == 0)) {
                return true;
            }
        }

        for (int j = sum.length - 1; j >= 2; j--) {
            for (int l = j - 2; l >= 0; l--) {
                int diff = sum[j] - sum[l];
                if (diff == k || (k != 0 && diff % k == 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Test
    public void testmaxSubArray() {
        assert maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6;
        assert maxSubArray(new int[]{-2}) == -2;

    }

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sumEnd = 0;
        for (int n : nums) {
            if (sumEnd < 0) {
                sumEnd = n;
            } else {
                sumEnd = sumEnd + n;
            }
            if (sumEnd > max) {
                max = sumEnd;
            }
        }
        return max;
    }

    @Test
    public void testCostClimbingStairs() {
        assert 2 == minCostClimbingStairs(new int[]{1, 2, 3});
        assert 4 == minCostClimbingStairs(new int[]{1, 12, 3});
        assert 6 == minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1});

    }

    public int minCostClimbingStairs(int[] cost) {
        int[] sum = new int[cost.length + 1];
        for (int i = 2; i < cost.length + 1; i++) {
            sum[i] = Math.min(sum[i - 1] + cost[i - 1], sum[i - 2] + cost[i - 2]);
        }
        return sum[cost.length];
    }


    @Test
    public void testclimbStairs() {
        assert 1 == climbStairs(1);
        assert 2 == climbStairs(2);
        assert 3 == climbStairs(3);
        assert 5 == climbStairs(4);
    }

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int count = 1;
        int count1 = 1;
        int count2 = 2;

        for (int i = 3; i <= n; i++) {
            count = count1 + count2;
            count1 = count2;
            count2 = count;
        }

        return count;
    }

    @Test
    public void testSplitArrayLargestSum() {
        int[] numbers = new int[]{7, 2, 5, 10, 8, 3};
        assert 14 == splitArray(numbers, 3);

        numbers = new int[]{7, 2, 5, 10, 8};
        assert 18 == splitArray(numbers, 2);

    }

    public int splitArray(int[] nums, int m) {
        int sum = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(nums[i], max);
        }

        long l = max;
        long r = sum;
        while (l < r) {
            long mid = (l + r) / 2;
            if (isValid(nums, mid, m)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return (int) l;
    }

    public boolean isValid(int[] nums, long target, int m) {
        int count = 1;
        int total = 0;
        for (int n : nums) {
            total += n;
            if (total > target) {
                total = n;
                count++;
            }
            if (count > m) {
                return false;
            }
        }
        return true;
    }


    @Test
    public void testPartitionKSub() {
        int[] numbers = new int[]{1};
        int k = 1;
        assert true == canPartitionKSubsets(numbers, k) : "only one number";

        numbers = new int[]{1, 3, 4};
        k = 2;
        assert true == canPartitionKSubsets(numbers, k) : "two number";

        numbers = new int[]{1, 3, 5};
        k = 2;
        assert false == canPartitionKSubsets(numbers, k) : "two number cannot divided";

        numbers = new int[]{11, 1};
        k = 3;
        assert false == canPartitionKSubsets(numbers, k) : "two number can be divided but cannot be partitioned";
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int number : nums) {
            sum += number;
        }
        if (sum % k != 0) {
            return false;
        }

        int[] visited = new int[nums.length];
        return partition(nums, visited, 0, k, 0, sum / k);
    }

    public boolean partition(int[] numbers, int[] visited, int startIndex, int numOfSub, int curSum, int target) {
        if (numOfSub == 1) {
            return true;
        }
        if (curSum == target) {
            return partition(numbers, visited, 0, numOfSub - 1, 0, target);
        }
        if (curSum > target) {
            return false;
        }

        for (int i = startIndex; i < numbers.length; ++i) {
            if (visited[i] == 0) {
                visited[i] = 1;
                if (partition(numbers, visited, i + 1, numOfSub, curSum + numbers[i], target)) {
                    return true;
                }
                visited[i] = 0;
            }
        }
        return false;
    }


    @Test
    public void testCountingBits() {
        int n = 140;
        int[] counts = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            counts[i] = counts[i >> 1] + (i & 1);
        }

        assert counts[5] == 2;
        assert counts[64] == 1;
        assert counts[65] == 2;
        assert counts[138] == 3;

    }

    @Test
    public void testTreeBst() {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(9);
        assert true == isBst(treeNode);

        treeNode.left.left = new TreeNode(2);
        treeNode.left.right = new TreeNode(6);

        assert true == isBst(treeNode);

    }

    public boolean isBst(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (root.left != null && root.left.val > root.val) {
            return false;
        }

        if (root.right != null && root.right.val < root.val) {
            return false;
        }

        return isBst(root.left) && isBst(root.right);
    }

    @Test
    public void testTreeSymmetric() {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(2);
        assert true == isSymmetricIterator(treeNode);

        treeNode.left.left = new TreeNode(3);
        treeNode.left.right = new TreeNode(4);
        assert false == isSymmetricIterator(treeNode);

        treeNode.right.left = new TreeNode(4);
        treeNode.right.right = new TreeNode(3);
        assert true == isSymmetricIterator(treeNode);

    }

    public boolean isSymmetricIterator(TreeNode root) {
        if (root == null) {
            return true;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);
        while (stack.size() > 0) {
            TreeNode left = stack.pop();
            TreeNode right = stack.pop();

            if (left == null && right == null) {
                continue;
            }
            if (left == null && right != null) {
                return false;
            }
            if (left != null && right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }

            stack.push(left.right);
            stack.push(right.left);
            stack.push(left.left);
            stack.push(right.right);

        }

        return true;
    }


    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null && right != null) {
            return false;
        }
        if (left != null && right == null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public long findMultiply(long dividend, long divisor) {

        if (dividend < divisor) {
            return 0;
        }

        long sum = divisor;
        long result = 1;
        while ((sum + sum) <= dividend) {
            result += result;
            sum += sum;
        }
        return result + findMultiply(dividend - sum, divisor);
    }

    public int divide(int dividend, int divisor) {
        long result = 0;
        long ldividend = dividend;
        long ldivisor = divisor;
        boolean isNegative = false;

        if (ldividend > 0 && ldivisor > 0) {
            isNegative = false;
            result = findMultiply(ldividend, ldivisor);
        } else if (ldividend < 0 && ldivisor < 0) {
            isNegative = false;
            result = findMultiply(Math.abs(ldividend), Math.abs(ldivisor));
        } else if (ldividend > 0 && ldivisor < 0) {
            isNegative = true;
            result = findMultiply(Math.abs(ldividend), Math.abs(ldivisor));
        } else if (ldividend < 0 && ldivisor > 0) {
            isNegative = true;
            result = findMultiply(Math.abs(ldividend), Math.abs(ldivisor));
        }

        result = isNegative ? -result : result;

        if (result > Integer.MAX_VALUE) {
            result = Integer.MAX_VALUE;
        }

        return (int) result;
    }

    @Test
    public void stringtoint() {

        assert divide(10, 5) == 2;
        assert divide(42, 5) == 8;
        assert divide(11, 5) == 2;

        assert divide(-10, -5) == 2;
        assert divide(-11, -5) == 2;
        assert divide(-9, -5) == 1;

        assert divide(9, -5) == -1;
        assert divide(10, -5) == -2;

        assert divide(10, -5) == -2;
        assert divide(1000000000, -5) == 1000000000 / -5;

        assert divide(Integer.MAX_VALUE, 1) == Integer.MAX_VALUE;
        assert divide(Integer.MIN_VALUE, 1) == Integer.MIN_VALUE;
        assert divide(Integer.MAX_VALUE, -1) == Integer.MIN_VALUE + 1;
        assert divide(Integer.MIN_VALUE, -1) == Integer.MAX_VALUE;
    }

    public String nearestPalindromic(String n) {
        char[] chars = n.toCharArray();
        if (chars.length == 1) {
            return String.valueOf(Integer.valueOf(n) - 1);
        }
        int i = 0, j = chars.length - 1;
        while (j - i >= 1) {
            chars[j] = chars[i];
            j--;
            i++;
        }
        return String.valueOf(chars);
    }

    int reverseint(int x) {
        int isNegative = (x < 0 ? -1 : 1);
        long result = 0;
        while (x != 0) {
            result = (x % 10) + result * 10;
            x = x / 10;
            if (isNegative == -1 && result < Integer.MIN_VALUE) {
                return 0;
            } else if (isNegative == 1 && result > Integer.MAX_VALUE) {
                return 0;
            }
        }
        return (int) result;

    }

    int atoi(String str) {
        char[] chars = str.trim().toCharArray();
        if (chars.length == 0) {
            return 0;
        }

        if (chars[0] != '-' && chars[0] != '+' && (chars[0] < '0' || chars[0] > '9')) {
            return 0;
        }

        int isNegative = (chars[0] == '-' ? -1 : 1);
        boolean isSigned = (chars[0] == '-' || chars[0] == '+');
        long result = 0;
        for (int i = (isSigned ? 1 : 0); i < chars.length; ++i) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                result = (chars[i] - '0') * isNegative + result * 10;
                if (isNegative == -1 && result < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                } else if (isNegative == 1 && result > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
            } else {
                break;
            }
        }
        return (int) result;
    }

}
