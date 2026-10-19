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
    class Solution19 {
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

    //20旋转图像
    class Solution20 {
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

    //21搜索二维矩阵II
    class Solution21 {
        public boolean searchMatrix(int[][] matrix, int target) {
            int right=matrix[0].length-1;
            for(int i=0;i<matrix.length;i++){
                if(matrix[i][0]==target || matrix[i][right]==target){
                    return true;
                }else if(matrix[i][0]<target && matrix[i][right]>target){
                    int p1=0;
                    int p2=right;
                    while(p1<=p2){
                        int mid=(p1+p2)>>>1;
                        if(matrix[i][mid]==target){
                            return true;
                        }else if(matrix[i][mid]<target){
                            p1=mid+1;
                        }else{
                            p2=mid-1;
                        }
                    }
                }else if(matrix[i][0]>target){
                    break;
                }
            }
            return false;
        }
    }

    //22相交链表
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x, ListNode head) {
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

    //23反转链表
    class Solution23 {
        public ListNode reverseList(ListNode head) {
            if(head==null || head.next==null) return head;
            ListNode first=new ListNode(-1,head);
            ListNode p1=head;
            ListNode p2=p1.next;
            while(p2!=null){
                ListNode node=p2.next;
                ListNode afterFirst=first.next;
                first.next=p2;
                p2.next=afterFirst;
                p1.next=node;
                p2=node;
            }
            return first.next;
        }
    }

    //24回文链表
    class Solution24 {
        public boolean isPalindrome(ListNode head) {
            if(head==null || head.next==null) return true;
            ListNode pointer=head;
            List<Integer> list=new ArrayList<>();
            while(pointer!=null){
                list.add(pointer.val);
                pointer=pointer.next;
            }
            int i=0;
            int j=list.size()-1;
            while(i<=j){
                if(list.get(i)==list.get(j)){
                    i++;
                    j--;
                }else {return false;}
            }
            return true;
        }
    }

    //25环形链表
    public class Solution25 {
        public boolean hasCycle(ListNode head) {
            if(head==null) return false;
            ListNode fast=head;
            ListNode slow=head;
            while(fast!=null && fast.next!=null){
                fast=fast.next.next;
                slow=slow.next;
                if(slow==fast){
                    return true;
                }
            }
            return false;
        }
    }

    //26环形链表 II
    public class Solution26 {
        public ListNode detectCycle(ListNode head) {
            if(head==null || head.next==null) return null;
            ListNode fast=head;
            ListNode slow=head;
            boolean iscircle=false;
            while(fast!=null && fast.next!=null){
                fast=fast.next.next;
                slow=slow.next;
                if(slow==fast) {iscircle=true;break;}
            }
            if(iscircle==true){
                fast=head;
                while(fast!=slow){
                    fast=fast.next;
                    slow=slow.next;
                }
                return fast;
            }
            return null;
        }
    }

    //27合并两个有序链表
    class Solution27 {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if(list1==null) return list2;
            if(list2==null) return list1;
            Queue<ListNode> q=new PriorityQueue<>((k,v)->k.val-v.val);
            ListNode p1=list1;
            ListNode p2=list2;
            while(p1!=null){
                q.add(p1);
                p1=p1.next;
            }
            while(p2!=null){
                q.offer(p2);
                p2=p2.next;
            }
            ListNode head=new ListNode(Integer.MIN_VALUE,null);
            ListNode p=head;
            while(!q.isEmpty()){
                ListNode node=q.poll();
                node.next=null;
                p.next=node;
                p=p.next;
            }
            return head.next;
        }
    }

    //28两数相加

    //29删除链表的倒数第 N 个结点
    class Solution29 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if(n==0 || head==null) return head;
            ListNode p=head;
            int num=0;
            while(p!=null){
                p=p.next;
                num++;
            }
            p=head;
            if(num<n) return head;
            int index=num-n;
            if(index==0){
                return head.next;
            }
            for(int i=1;i<index;i++){
                p=p.next;
            }
            ListNode delete=p.next;
            ListNode node=delete.next;
            p.next=node;
            return head;
        }
    }

    //30两两交换链表中的节点
    class Solution30 {
        public ListNode swapPairs(ListNode head) {
            if(head==null || head.next==null) return head;
            ListNode first=new ListNode(-1,head);
            ListNode p1=first;
            ListNode p2=head;
            ListNode p3=head.next;
            while(p3!=null){
                ListNode p4=p3.next;
                //p1 p2 p3 p4
                p1.next=p3;
                p3.next=p2;
                p2.next=p4;
                //p1 p3 p2 p4
                if(p4!=null){
                    p3=p4.next;
                    p2=p4;
                    p1=p1.next.next;
                }else{
                    break;
                }
            }
            return first.next;
        }
    }

    //31 K 个一组翻转链表
    class Solution31 {
        public ListNode reverseKGroup(ListNode head, int k) {
            if(head==null || head.next==null || k <= 1) return head;
            ListNode first=new ListNode(-1,head);
            ListNode p1=first;
            ListNode p2=head;
            ListNode p3=head;
            while(p3!=null){
                for(int i=1;p3!=null && i<k;i++){
                    p3=p3.next;
                }
                if(p3==null){
                    break;
                }
                ListNode p4=p3.next;
                p3.next=null;
                p1.next=reverseListNode(p2);
                //p1 p3 ....... p2 p4
                p2.next=p4;
                p1=p2;
                p3=p4;
                p2=p4;
            }
            return first.next;
        }
        private ListNode reverseListNode(ListNode head){
            if(head==null || head.next==null) return head;
            ListNode first=new ListNode(-1,head);
            ListNode p2=head.next;
            ListNode p1=head;
            //first p1 p2 p3 _ _ _
            while(p2!=null){
                ListNode p3=p2.next;
                ListNode afterFirst=first.next;
                first.next=p2;
                p2.next=afterFirst;
                p1.next=p3;
                //first p2 p1 p3
                p2=p3;
            }
            return first.next;
        }
    }

    //32随机链表的复制

    //33排序链表
    class Solution33 {
        public ListNode sortList(ListNode head) {
            if(head==null ||head.next==null) return head;
            Queue<ListNode> q=new PriorityQueue<>((x,y)->x.val-y.val);
            ListNode p=head;
            while(p!=null){
                q.offer(p);
                p=p.next;
            }
            ListNode first=new ListNode(Integer.MIN_VALUE,null);
            ListNode pointer=first;
            while(!q.isEmpty()){
                ListNode node=q.poll();
                pointer.next=node;
                node.next=null;
                pointer=pointer.next;
            }
            return first.next;
        }
    }

    //34合并 K 个升序链表
    class Solution34 {
        public ListNode mergeKLists(ListNode[] lists) {
            Queue<ListNode> q=new PriorityQueue<>((x,y)->x.val-y.val);
            for(ListNode x : lists){
                ListNode pointer=x;
                while(pointer!=null){
                    q.offer(pointer);
                    pointer=pointer.next;
                }
            }
            ListNode first=new ListNode(Integer.MIN_VALUE,null);
            ListNode p=first;
            while(!q.isEmpty()){
                ListNode node=q.poll();
                p.next=node;
                node.next=null;
                p=p.next;
            }
            return first.next;
        }
    }

    //35二叉树的中序遍历
//     Definition for a binary tree node.
     public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }
    class Solution35 {
        public List<Integer> inorderTraversal(TreeNode root) {
            if(root==null) return new ArrayList<Integer>();
            ArrayList<Integer> list=new ArrayList<>();
            travel(root,list);
            return list;
        }
        private void travel(TreeNode root, ArrayList<Integer> list){
            if(root==null) return;
            travel(root.left,list);
            list.add(root.val);
            travel(root.right,list);
        }
    }

    //36二叉树的最大深度
    class Solution36 {
        public int maxDepth(TreeNode root) {
            return maxDepth1(root);
        }
        private int maxDepth1(TreeNode root){
            if(root==null) return 0;
            int depth=0;
            depth=Math.max(maxDepth(root.left)+1, maxDepth(root.right)+1);
            return depth;
        }
    }

    //37翻转二叉树
    class Solution37 {
        public TreeNode invertTree(TreeNode root) {
            if(root==null) return null;
            TreeNode t=root.right;
            root.right=root.left;
            root.left=t;
            return new TreeNode(root.val,invertTree(root.left),invertTree(root.right));
        }
    }

    //38对称二叉树
    class Solution38 {
        public boolean isSymmetric(TreeNode root) {
            if(root==null) return true;
            return isMirror(root.left,root.right);
        }
        private boolean isMirror(TreeNode left,TreeNode right){
            if(left==null && right==null) return true;
            if(left==null || right==null) return false;
            if(left.val!=right.val) return false;
            return isMirror(left.left,right.right) && isMirror(left.right,right.left);
        }
    }

    //39二叉树的直径 ——> 目前理解不了！！！！
    class Solution39 {
        private int d=0;
        public int diameterOfBinaryTree(TreeNode root) {
            maxDiameter(root);
            return d;
        }
        private int maxDiameter(TreeNode root){
            if(root==null) return 0;
            int leftDepth=maxDiameter(root.left);
            int rightdepth=maxDiameter(root.right);
            d=Math.max(d,leftDepth+rightdepth);
            return Math.max(leftDepth,rightdepth)+1;
        }
    }

    //40二叉树的层序遍历
    class Solution40 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            if(root==null) return new ArrayList<>();
            List<List<Integer>> list1=new ArrayList<>();
            Queue<TreeNode> q=new ArrayDeque<>();
            q.offer(root);
            while(!q.isEmpty()){
                List<Integer> list2=new ArrayList<>();
                int size=q.size();
                for(int i=0;i<size;i++){
                    TreeNode node=q.poll();
                    list2.add(node.val);
                    if(node.left!=null) q.offer(node.left);
                    if(node.right!=null) q.offer(node.right);
                }
                list1.add(list2);
            }
            return list1;
        }
    }

    //41将有序数组转换为二叉搜索树 *
    class Solution41 {
        public TreeNode sortedArrayToBST(int[] nums) {
            return build(nums,0,nums.length-1);
        }
        private TreeNode build(int[] nums,int lower,int higher){
            if(lower > higher) return null;
            int middle = lower + (higher - lower)/2;
            TreeNode root=new TreeNode(nums[middle]);
            root.left=build(nums,lower,middle-1);
            root.right=build(nums,middle+1,higher);
            return root;
        }
    }

    //42验证二叉搜索树 *
    class Solution42 {
        public boolean isValidBST(TreeNode root) {
            return travel(root,Long.MIN_VALUE,Long.MAX_VALUE);
        }
        private boolean travel(TreeNode root,long lower,long higher){
            if(root==null) return true;
            if(lower >= root.val) return false;
            if(higher <= root.val) return false;
            return travel(root.left,lower,root.val) && travel(root.right,root.val,higher);
        }
    }

    //43二叉搜索树中第 K 小的元素
    class Solution43 {
        public int kthSmallest(TreeNode root, int k) {
            List<Integer> list=new ArrayList<>();
            travel(root,list);
            return list.get(k-1);
        }
        private void travel(TreeNode tree,List<Integer> list){
            if(tree==null) return;
            travel(tree.left,list);
            list.add(tree.val);
            travel(tree.right,list);
        }
    }

    //44二叉树的右视图
    class Solution44 {
        public List<Integer> rightSideView(TreeNode root) {
            if(root==null) return new ArrayList<>();
            List<Integer> list=new ArrayList<>();
            Queue<TreeNode> q=new ArrayDeque<>();
            q.offer(root);
            while(!q.isEmpty()){
                int size=q.size();
                for(int i=0;i<size;i++){
                    TreeNode node=q.poll();
                    if(i == size-1) list.add(node.val);
                    if(node.left!=null) q.offer(node.left);
                    if(node.right!=null) q.offer(node.right);
                }
            }
            return list;
        }
    }









}
/*

git add .
git commit -m "leetcode"
git push

 */