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

    //7无重复字符的最长子串
    class Solution7 {
        public int lengthOfLongestSubstring(String s) {
            if(s==null) return 0;
            Map<Integer,Character> map=new HashMap<>();
            int i=0;
            int j=0;
            int maxLength=0;
            int currentLength=0;
            char[] chars=s.toCharArray();
            while(j<chars.length){
                if(map.containsValue(chars[j])){//包含chars[j]
                    while(map.get(i)!=chars[j]){
                        map.remove(i);//删除之前的元素
                        i++;//i指针往前移动
                    }
                    map.remove(i);//等于的那个也删掉
                    i++;//向前移
                    currentLength=j-i;//现有长度，因为j已经++了在21行，j-1+i+1 -> j+i
                }else{
                    map.put(j,chars[j]);
                    j++;
                    currentLength++;
                }
                maxLength=Math.max(maxLength,currentLength);
            }
            return maxLength;
        }
    }





}
/*

git add .
git commit -m "leetcode"
git push

 */