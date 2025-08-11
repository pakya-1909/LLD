package DigitalWallet;

import java.util.*;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        Set<Integer> set = new HashSet<>();
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        for (int i : nums) {
            set.add(i);
        }

        solve(set, list, ans);

        return ans;
    }

    public void solve(Set<Integer> set, List<Integer> list, List<List<Integer>> ans) {
        if (set.size() == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        Set<Integer> tempSet = new HashSet<>(set);

        for (int i : tempSet) {
            int num = i;
            set.remove(i);
            list.add(num);
            solve(set, list, ans);
            list.remove(list.size() - 1);
            set.add(num);
        }

        return;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = new int[1];
        nums[0] = 1;
        // nums[1] = 1;
        // nums[2] = 3;
        List<List<Integer>> ans = sol.permute(nums);

        System.out.println(ans.toString());
    }
}
