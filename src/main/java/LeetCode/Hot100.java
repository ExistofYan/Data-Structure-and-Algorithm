package LeetCode;

import java.util.*;

public class Hot100 {

    //1两数之和
    class Solution1 {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer,Integer> map=new HashMap<>();
            for(int i=0;i<nums.length;i++){
                if(map.containsKey(target-nums[i])){
                    return new int[]{map.get(target-nums[i]),i};
                }
                map.put(nums[i],i);
            }
            return null;
        }
    }

    //2字母异位分组
    class Solution2 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String,List<String>> map=new HashMap<>();
            for(String s:strs){
                char[] chars=s.toCharArray();
                Arrays.sort(chars);
                String str=new String(chars);
                if(map.containsKey(str)){
                    map.get(str).add(s);
                }else{
                    map.put(str,new ArrayList<>(List.of(s)));
                }
            }
            return new ArrayList<>(map.values());
        }
    }

    //3最长连续序列
    class Solution3 {
        public int longestConsecutive(int[] nums) {
            if(nums.length<=1) return nums.length;
            Arrays.sort(nums);
            int max=1;
            int time=1;
            for(int i=0;i<nums.length-1;i++){
                if(nums[i]+1==nums[i+1]){
                    time++;
                    max=Math.max(time,max);
                }else if(nums[i]==nums[i+1]){
                    continue;
                }else{
                    time=1;
                }
            }
            return max;
        }
    }

    //4移动零
    class Solution4 {
        public void moveZeroes(int[] nums) {
            int p=0;
            for(int i=0;i<nums.length;i++){
                p=i;
                while(p<nums.length-1 && nums[p]==0){
                    p++;
                }
                if(nums[i]==0){
                    nums[i]=nums[p];
                    nums[p]=0;
                }
            }
        }
    }

    //5盛最多水的容器
    class Solution5 {
        public int maxArea(int[] height) {
            int i=0;
            int j=height.length-1;
            int area=0;
            while(i<=j){
                area=Math.max(area,(j-i)*Math.min(height[i],height[j]));
                if(height[i]<height[j]){
                    i++;
                }else{
                    j--;
                }
            }
            return area;
        }
    }






}
/*

git add .
git commit -m "leetcode"
git push

 */