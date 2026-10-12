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









}
