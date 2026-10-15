package LeetCode;

import java.util.*;

public class Hot100 {

    //1两数之和
    class Solution1 {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    return new int[]{map.get(target - nums[i]), i};
                }
                map.put(nums[i], i);
            }
            return null;
        }
    }

    //2字母异位分组
    class Solution2 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String s : strs) {
                char[] chars = s.toCharArray();
                Arrays.sort(chars);
                String str = new String(chars);
                if (map.containsKey(str)) {
                    map.get(str).add(s);
                } else {
                    map.put(str, new ArrayList<>(List.of(s)));
                }
            }
            return new ArrayList<>(map.values());
        }
    }

    //3最长连续序列
    class Solution3 {
        public int longestConsecutive(int[] nums) {
            if (nums.length <= 1) return nums.length;
            Arrays.sort(nums);
            int max = 1;
            int time = 1;
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] + 1 == nums[i + 1]) {
                    time++;
                    max = Math.max(time, max);
                } else if (nums[i] == nums[i + 1]) {
                    continue;
                } else {
                    time = 1;
                }
            }
            return max;
        }
    }

    //4移动零
    class Solution4 {
        public void moveZeroes(int[] nums) {
            int p = 0;
            for (int i = 0; i < nums.length; i++) {
                p = i;
                while (p < nums.length - 1 && nums[p] == 0) {
                    p++;
                }
                if (nums[i] == 0) {
                    nums[i] = nums[p];
                    nums[p] = 0;
                }
            }
        }
    }

    //5盛最多水的容器
    class Solution5 {
        public int maxArea(int[] height) {
            int i = 0;
            int j = height.length - 1;
            int area = 0;
            while (i <= j) {
                area = Math.max(area, (j - i) * Math.min(height[i], height[j]));
                if (height[i] < height[j]) {
                    i++;
                } else {
                    j--;
                }
            }
            return area;
        }
    }

    //6三数之和

    //7接雨水

    //8无重复字符的最长子串
    class Solution8 {
        public int lengthOfLongestSubstring(String s) {
            if (s == null) return 0;
            Map<Integer, Character> map = new HashMap<>();
            int i = 0;
            int j = 0;
            int maxLength = 0;
            int currentLength = 0;
            char[] chars = s.toCharArray();
            while (j < chars.length) {
                if (map.containsValue(chars[j])) {//包含chars[j]
                    while (map.get(i) != chars[j]) {
                        map.remove(i);//删除之前的元素
                        i++;//i指针往前移动
                    }
                    map.remove(i);//等于的那个也删掉
                    i++;//向前移
                    currentLength = j - i;//现有长度，因为j已经++了在21行，j-1+i+1 -> j+i
                } else {
                    map.put(j, chars[j]);
                    j++;
                    currentLength++;
                }
                maxLength = Math.max(maxLength, currentLength);
            }
            return maxLength;
        }
    }

    //9找到字符串中所有字母异位词
    class Solution9 {
        public List<Integer> findAnagrams(String s, String p) {
            if (s == null || s.length() < p.length()) return new ArrayList<>();
            int[] forp = new int[26];
            int[] fors = new int[26];
            char[] charsp = p.toCharArray();
            for (char c : charsp) {
                forp[c - 'a']++;
            }
            int front = 0;
            int aft = p.length();
            ArrayList<Integer> list = new ArrayList<>();
            char[] charss = s.toCharArray();
            for (int i = 0; i < p.length(); i++) {
                fors[charss[i] - 'a']++;
            }
            if (Arrays.equals(forp, fors)) {
                list.add(0);
            }
            while (aft < s.length()) {
                fors[charss[front] - 'a']--;
                front++;
                fors[charss[aft] - 'a']++;
                if (Arrays.equals(forp, fors)) {
                    list.add(front);
                }
                aft++;
            }
            return list;
        }
    }

    //10和为 K 的子数组，卡住了，目前还没理解透彻
    class Solution10 {
        public int subarraySum(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            int times = 0;
            int sum = 0;
            map.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                sum = sum + nums[i];
                times = times + map.getOrDefault(sum - k, 0);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return times;
        }
    }

    //11滑动窗口最大值

    //12最小覆盖字串

    //13最大子数组和
    class Solution13 {
        public int maxSubArray(int[] nums) {
            int current = 0;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                current = Math.max(current + nums[i], nums[i]);
                max = Math.max(max, current);
            }
            return max;
        }
    }

    //14合并区间
    class Solution14 {
        public int[][] merge(int[][] intervals) {
            if (intervals == null) return new int[0][0];
            Arrays.sort(intervals, (k, v) -> k[0] - v[0]);
            List<int[]> list = new ArrayList<>();
            for (int[] x : intervals) {
                if (list.isEmpty() || list.get(list.size() - 1)[1] < x[0]) {
                    list.add(x);
                }
                if (list.get(list.size() - 1)[1] >= x[0]) {
                    list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], x[1]);
                }
            }
            return list.toArray(new int[list.size()][]);
        }
    }

    //15轮转数组
    class Solution15 {
        public void rotate(int[] nums, int k) {
            if (k == nums.length) return;
            int size = k % nums.length;
            int[] copy1 = new int[size];
            System.arraycopy(nums, nums.length - size, copy1, 0, size);
            int[] copy2 = new int[nums.length - size];
            System.arraycopy(nums, 0, copy2, 0, nums.length - size);


            System.arraycopy(copy1, 0, nums, 0, copy1.length);
            System.arraycopy(copy2, 0, nums, size, copy2.length);
        }
    }

    //16除了自身以外数组的乘积
    class Solution16 {
        public int[] productExceptSelf(int[] nums) {
            if (nums == null || nums.length == 0) return new int[nums.length];
            int[] arr = new int[nums.length];
            arr[0] = 1;
            for (int i = 1; i < nums.length; i++) {
                arr[i] = nums[i - 1] * arr[i - 1];
            }
            int r = 1;
            for (int i = nums.length - 1; i >= 0; i--) {
                arr[i] = arr[i] * r;
                r = r * nums[i];
            }
            return arr;
        }
    }

    //17缺失的第一个正数
    class Solution17 {
        public int firstMissingPositive(int[] nums) {
            Set<Integer> set = new TreeSet<>();
            for (int x : nums) {
                if (x <= 0) continue;
                set.add(x);
            }
            int n = 1;
            for (Integer x : set) {
                if (x != n) {
                    return n;
                }
                n++;
            }
            return n;
        }
    }

    //18矩阵置零
    class Solution18 {
        public void setZeroes(int[][] matrix) {
            boolean rowHasZero = false;
            boolean columnHasZero = false;
            for (int x : matrix[0]) {
                if (x == 0) {
                    rowHasZero = true;
                    break;
                }
            }
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][0] == 0) {
                    columnHasZero = true;
                    break;
                }
            }
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }
            for (int i = 1; i < matrix[0].length; i++) {
                if (matrix[0][i] == 0) {
                    for (int j = 0; j < matrix.length; j++) {
                        matrix[j][i] = 0;
                    }
                }
            }
            for (int i = 1; i < matrix.length; i++) {
                if (matrix[i][0] == 0) Arrays.fill(matrix[i], 0);
            }
            if (rowHasZero == true) {
                Arrays.fill(matrix[0], 0);
            }
            if (columnHasZero == true) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][0] = 0;
                }
            }
        }
    }

    //19螺旋矩阵

    //20旋转图像

    //21搜索二维矩阵II

    //22相交链表
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public class Solution22 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode pointerA = headA;
            ListNode pointerB = headB;
            while (pointerA != pointerB) {
                if (pointerA == null) {
                    pointerA = headB;
                } else {
                    pointerA = pointerA.next;
                }
                if (pointerB == null) {
                    pointerB = headA;
                } else {
                    pointerB = pointerB.next;
                }
            }
            return pointerA;
        }
    }

    //23螺旋矩阵
    class Solution23 {
        public List<Integer> spiralOrder(int[][] matrix) {
            int left=0;
            int right=matrix[0].length-1;
            int top=0;
            int bottom=matrix.length-1;
            List<Integer> list=new ArrayList<>();
            while(left<=right && top <= bottom){
                for(int i=left;i<=right;i++){
                    list.add(matrix[top][i]);
                }
                top++;
                for(int i=top;i<=bottom;i++){
                    list.add(matrix[i][right]);
                }
                right--;
                if(top<=bottom){
                    for(int i=right;i>=left;i--){
                        list.add(matrix[bottom][i]);
                    }
                    bottom--;
                }
                if(right>=left){
                    for(int i=bottom;i>=top;i--){
                        list.add(matrix[i][left]);
                    }
                    left++;
                }

            }
            return list;
        }
    }

    //24旋转图像
    class Solution24 {
        public void rotate(int[][] matrix) {
            List<Integer> list=new ArrayList<>();
            for(int i=0;i<matrix[0].length;i++){
                for(int j=matrix.length-1;j>=0;j--){
                    list.add(matrix[j][i]);
                }
            }
            int index=0;
            for(int i=0;i<matrix.length;i++){
                for(int j=0;j<matrix[0].length;j++){
                    matrix[i][j]=list.get(index);
                    index++;
                }
            }
        }
    }


}
/*

git add .
git commit -m "leetcode"
git push

 */