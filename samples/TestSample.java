package core;

import apple.laf.JRSUIUtils;

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class TestSample {


    public static void main(String[] args) throws Exception {
        int res = lengthOfLongestSubstring("ababcabcd"); // "abcabcbb" "au"
       // int res = firstNonRepeatingCharacter("abacabad");
        System.out.println(res);
    }


    //longest substring without repeating characters
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int res = 0;
        int a= 0;
        int b = 0;
        if(s.isEmpty())
            return 0;
        if(s.length() == 1 )
            return 1;
        while(b < s.length()){
            if(!set.contains(s.charAt(b))){
                set.add(s.charAt(b));
                if(set.size() > res){
                    res = set.size();
                }
                b++;
            } else {
                set.remove(s.charAt(a));
                a++;
            }
        }
        return res;

    }

    // longest palindromic substring
    private int lo, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;

        for (int i = 0; i < len-1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }

    public String shortestPalindrome(String s) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            count++;
            // s1 = s1 + s.substring(i, i);

        }
        return s;
    }


    //firstNonRepeatingCharacter

    public static char firstNonRepeatingCharacter(String s) {
        int[] char_count = new int[26];
        char arr[] = s.toCharArray();
              for(char c : arr){
                  char_count[c-'a']++;
                  System.out.println("i am founddddddd" + c);
                  System.out.println("i am foundd" + char_count[c-'a']);
              }

              for(char c : arr){
                  if(char_count[c-'a'] == 1){
                      System.out.println("i am found");
                      return c;
                  }
                  //if(char_count[c-'a'] == 1){
                 //     return c;
                //  }
              }

        return ' ';
    }


    /// Group Anagrams
    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> listAnagrams = new ArrayList<>();
        Map<String, List<String>> anagarms = new HashMap<>();

        for(int i = 0; i < strs.length; i++){
            char tempArray[] = strs[i].toCharArray();
            Arrays.sort(tempArray);

            String an = new String(tempArray);
            if(anagarms.containsKey(an)){
                anagarms.get(an).add(strs[i]);
            } else {
                List<String> words = new ArrayList<>();
                words.add(strs[i]);
                anagarms.put(an, words);
            }
        }
        for (String s : anagarms.keySet()) {
            List<String> values = anagarms.get(s);
            listAnagrams.add(values);
        }
        return listAnagrams;
    }





   /* public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        char[] arr = s.toCharArray();
        int res = 0;
        if(s.isEmpty())
            return 0;
        if(s.length() == 1 )
            return 1;
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
            if(set.contains(arr[i])){
                System.out.println(set.size());
                if(set.size() > res){
                    res = set.size();
                }
                set.clear();
            }
            set.add(arr[i]);
            if(set.size() > res){
                res = set.size();
            }
        }

        return res;

    }*/


    static int maximumProductSubarray(int[] arr){
        int currMin = arr[0];
        int currMax = arr[0];
        int result = 0;

        for(int i = 1; i < arr.length ; i++){
            int tmp = currMax * arr[i];
            currMax = Math.max(currMax*arr[i], Math.max(currMin*arr[i] , arr[i]));
            currMin = Math.min(currMin*arr[i], Math.min(arr[i], tmp));

            result = Math.max(currMax, result);
        }
        return result;
    }

    //Kadane algorthm
    static int maximumSumSubarray(int[] arr){ // 1 2 3 -6

        int current_max = arr[0];
        int max_sum = Integer.MIN_VALUE;
        for(int i = 1; i < arr.length; i++){
            max_sum = Math.max(current_max+arr[i], arr[i]);
            current_max = Math.max(current_max, max_sum);

        }
        return max_sum;
    }

    // or Kadane

    public static int maximumSubarraySum(int[] arr) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE; //stores the maximum sum of contiguous subarray found so far
        int currSum = 0; //that stores the maximum sum contiguous subarray ending at current index

        for (int i = 0; i <= n - 1; i++) {
            currSum += arr[i];

            if (currSum > maxSum) {
                maxSum = currSum;
            }

            if (currSum < 0) {
                currSum = 0;
            }
        }

        return maxSum;
    }

    // find the KthLargest ------------------------------------------------------

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue <Integer> heap=new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < nums.length ; i++) {
            heap.add(nums[i]);
        }
        for (int i = 0; i <k-1 ; i++) {
            heap.poll();
        }

       // or
        /*for (int i = 0; i < nums.length ; i++) {
            heap.add(nums[i]);
            if(heap.size()>k) heap.poll();
        } */

        return heap.peek();
    }

// find pairs where sum has doff a-b = k ----------------

    public int findPairs(int[] nums, int k) {
        if(nums.length < 2){
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap();
        for(int i = 0 ;i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }
        int result = 0;

        for(int key : map.keySet()){
            if(k == 0) {
                if(map.get(key) >= 2)
                    result ++;
            } else {
                if (map.containsKey(key + k)) {
                    result++;
                }
            }
        }
        return result;
    }


    // decode string using stack ------------------- 2ab3c --> ababccc

    public String decodeString(String s){
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if ( ch == '[') {
                intStack.push(k);
                strStack.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder tmp = cur;
                cur = strStack.pop();
                for (k = intStack.pop(); k > 0; --k) cur.append(tmp);
            } else cur.append(ch);
        }
        return cur.toString();
    }


    // find the islands ------------------------------------------------------

    public Integer noIslands(char[][] grid){
        if(grid == null || grid.length == 0 )
            return 0;
        int noOfIsland = 0;
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++ ){
                if(grid[i][j] == '1'){
                    noOfIsland = noOfIsland + noOfIslandsDFS(grid, i , j );
                }
            }
        }

      return noOfIsland;
    }

    private int noOfIslandsDFS(char[][] grid, int i , int j){
if( i < 0 || i > grid.length || j < 0 || j > grid[i].length || grid[i][j] == '0' ){
    return 0;
}
        noOfIslandsDFS(grid, i +1 , j) ;
        noOfIslandsDFS(grid, i -1 , j) ;
        noOfIslandsDFS(grid, i  , j + 1) ;
        noOfIslandsDFS(grid, i  , j - 1) ;
        return 1;
    }

//  Java-balanced-brackets-algorithm using dequeue LIFO


    public boolean validParanthese(String str){
        Deque<Character> deque = new LinkedList<>();
        for (char ch: str.toCharArray()) {
            if (ch == '{' || ch == '[' || ch == '(') {
                deque.addFirst(ch);
            } else {
                if (!deque.isEmpty()
                        && ((deque.peekFirst() == '{' && ch == '}')
                        || (deque.peekFirst() == '[' && ch == ']')
                        || (deque.peekFirst() == '(' && ch == ')'))) {
                    deque.removeFirst();
                } else {
                    return false;
                }
            }
        }
        return deque.isEmpty();
    }

    // Returns minimum number of platforms required
    public static int findPlatform(int arr[], int dep[], int n)
    {
        // Sort arrival and departure arrays
        Arrays.sort(arr);
        Arrays.sort(dep);

        // plat_needed indicates number of platforms
        // needed at a time
        int plat_needed = 1, result = 1;
        int i = 1, j = 0;

        // Similar to merge in merge sort to process
        // all events in sorted order
        while (i < n && j < n) {
            // If next event in sorted order is arrival,
            // increment count of platforms needed
            if (arr[i] <= dep[j]) {
                plat_needed++;
                i++;
            }

            // Else decrement count of platforms needed
            else if (arr[i] > dep[j]) {
                plat_needed--;
                j++;
            }

            // Update result if needed
            if (plat_needed > result)
                result = plat_needed;
        }

        return result;
    }




    // car pooling
        public boolean carPooling(int[][] trips, int capacity) {
            // sort the trips based on arrival time
            Arrays.sort(trips, (t1,t2)->Integer.compare(t1[1],t2[1]));
            PriorityQueue<int[]> pq = new PriorityQueue<>((e1,e2)->Integer.compare(e1[2],e2[2]));
            int curPeople = 0;
            for(int i = 0; i<trips.length; i++){
                int[] curTrip = trips[i]; // num of passengers, arrival, dept
                while(!pq.isEmpty() && pq.peek()[1] <= curTrip[1]){
                    curPeople-=pq.poll()[0];
                }
                curPeople += curTrip[0];
                if(curPeople > capacity) return false;
                pq.add(new int[]{curTrip[0], curTrip[2]});
            }
            return true;
        }


                                       // OR
       public boolean carPooling1(int[][] trips, int capacity) {
           Arrays.sort(trips, Comparator.comparingInt(o -> o[1]));
           PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
           for (int[] trip : trips) {
               while (!pq.isEmpty() && pq.peek()[2] <= trip[1]) {
                   capacity += pq.poll()[0];
               }
               if (capacity < trip[0]) {
                   return false;
               }
               pq.add(trip);
               capacity -= trip[0];
           }
           return true;
       }




        ///maximum-sum-of-at-most-two-non-overlapping-intervals-in-a-list-of-intervals-interval-scheduling-problem/


    public static int maxTwoNonOverLapping(int[][] interval) {
        // Sorting the given array on the basis of startTime
        Arrays.sort(interval, (t1, t2) -> Integer.compare(t1[0], t2[0]));
        PriorityQueue<int[]> pq
                = new PriorityQueue<>((e1, e2) -> Integer.compare(e1[1], e2[1])); // and sort on the basis of endTime
        int max = 0;
        int ans = 0;
        // Traversing the given array
        for (int[] e : interval) {
            while (!pq.isEmpty()) {
                if (pq.peek()[0] >= e[0])
                    break;
                int[] qu = pq.remove();
                // Updating max variable
                max = Math.max(max, qu[1]);
            }
            // Updating ans variable
            ans = Math.max(ans, max + e[2]);
            pq.add(new int[]{e[1], e[2]});
        }
        return ans;
    }

    // linked list detect loop

/*    class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }

    void detectLoop(ListNode head)
    {
        Node slow_p = head, fast_p = head;
        int flag = 0;
        while (slow_p != null && fast_p != null
                && fast_p.next != null) {
            slow_p = slow_p.next;
            fast_p = fast_p.next.next;
            if (slow_p == fast_p) {
                flag = 1;
                break;
            }
        }
        if (flag == 1)
            System.out.println("Loop Found");
        else
            System.out.println("No Loop");
    }


    *
    *
    * Add node in the end
    *
    *
     public static Node appendNode(Node head, int key)
    {
        Node current = head;
        Node node = newNode(key);

        // special case for length 0
        if (current == null) {
            head = node;
        }
        else {
            // locate the last node
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }

        return head;
    }
    *
    *
    *
    *
    * reverse linked List
    *  public ListNode reverseList(ListNode head) {
ListNode curr = head;
ListNode prev;
LisNode next;

while( curr != null && curr.next!=nul){

next = curr.next;
curr.next = prev;
prev = curr;
curr = next;
}

head = prev;
return head;

    }
    * */

///// TREES

              // TOP VIEW OF TREE --------------------------------------
    private void printTopView(TreeNode1 root)
    {
        class QueueObj {
            TreeNode1 node;
            int hd;

            QueueObj(TreeNode1 node, int hd)
            {
                this.node = node;
                this.hd = hd;
            }
        }
        Queue<QueueObj> q = new LinkedList<QueueObj>();
        Map<Integer, TreeNode1> topViewMap
                = new TreeMap<Integer, TreeNode1>();

        if (root == null) {
            return;
        }
        q.add(new QueueObj(root, 0));
        System.out.println("The top view of the tree is : ");

        // count function returns 1 if the container
        // contains an element whose key is equivalent
        // to hd, or returns zero otherwise.
        while (!q.isEmpty()) {
            QueueObj tmpNode = q.poll();
            if (!topViewMap.containsKey(tmpNode.hd)) {
                topViewMap.put(tmpNode.hd, tmpNode.node);
            }
            if (tmpNode.node.left != null) {
                q.add(new QueueObj(tmpNode.node.left,
                        tmpNode.hd - 1));
            }
            if (tmpNode.node.right != null) {
                q.add(new QueueObj(tmpNode.node.right,
                        tmpNode.hd + 1));
            }
        }
        for (Map.Entry<Integer, TreeNode1> entry :
                topViewMap.entrySet()) {
            System.out.print(entry.getValue().data + " ");
        }
    }

                  // BOTTOM VIEW OF TREE-----------------------------------------
    private void printBottomView(TreeNode1 root)
    {
        class QueueObj {
            TreeNode1 node;
            int hd;
            QueueObj(TreeNode1 node, int hd)
            {
                this.node = node;
                this.hd = hd;
            }
        }
        Queue<QueueObj> q = new LinkedList<QueueObj>();
        Map<Integer, TreeNode1> map = new TreeMap<Integer, TreeNode1>();

        if (root == null) {
            return;
        }
        q.add(new QueueObj(root, 0));
        System.out.println("The bottom view of the tree is : ");
        while (!q.isEmpty()) {
            QueueObj tmpNode = q.poll();
            map.put(tmpNode.hd, tmpNode.node);
            if (tmpNode.node.left != null) {
                q.add(new QueueObj(tmpNode.node.left, tmpNode.hd - 1));
            }
            if (tmpNode.node.right != null) {
                q.add(new QueueObj(tmpNode.node.right, tmpNode.hd + 1));
            }
        }
        for (Map.Entry<Integer, TreeNode1> entry : map.entrySet()) {
            System.out.print(entry.getValue().data + " ");
        }
    }



    ///// LEFT VIEW OF BST

    private static void printLeftView(TreeNode1 root)
    {
        if (root == null)
            return;

        Queue<TreeNode1> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // number of nodes at current level
            int n = queue.size();

            // Traverse all nodes of current level
            for (int i = 1; i <= n; i++) {
                TreeNode1 temp = queue.poll();
                // Print the left most element at level
                if (i == 1)
                    System.out.print(temp.data + " ");
                // Add left node to queue
                if (temp.left != null)
                    queue.add(temp.left);
                // Add right node to queue
                if (temp.right != null)
                    queue.add(temp.right);
            }
        }
    }

    /// RIGHT VIEW

    void rightView(TreeNode1 root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode1> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            // get number of nodes for each level
            int n = q.size();
            // traverse all the nodes of the current level
            for (int i = 0; i < n; i++) {
                TreeNode1 curr = q.peek();
                q.remove();
                // print the last node of each level
                if (i == n - 1) {
                    System.out.print(curr.data + " ");
                }
                // if left child is not null add it into queue
                if (curr.left != null) {
                    q.add(curr.left);
                }
                // if right child is not null add it into queue
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }
        }
    }

    ////  height of BST --------------

    private int getBSTHeight(TreeNode1 node){
        if(node == null){
            return 0;
        }
        int lHeight = getBSTHeight(node.left);
        int rheight = getBSTHeight(node.right);

        return Math.max(lHeight, rheight) + 1;
    }



//// identical tree ----------------------

    boolean checkIfidenticalTrees(TreeNode1 a, TreeNode1 b)
    {
        /*1. both empty */
        if (a == null && b == null)
            return true;

        /* 2. both non-empty -> compare them */
        if (a != null && b != null)
            return (a.data == b.data
                    && checkIfidenticalTrees(a.left, b.left)
                    && checkIfidenticalTrees(a.right, b.right));

        /* 3. one empty, one not -> false */
        return false;
    }


    //// MIRROR tree ----------------------

    boolean isMirrorTree(TreeNode1 node1, TreeNode1 node2)
    {
        // if both trees are empty, then they are mirror image
        if (node1 == null && node2 == null)
            return true;
        // For two trees to be mirror images, the following three conditions must be true
        // 1.) Their root node's key must be same
        // 2.) left subtree of left tree and right subtree of right tree have to be mirror images
        // 3.) right subtree of left tree and left subtree of right tree have to be mirror images
        if (node1 != null && node2 != null
                && node1.data == node2.data)
            return (isMirrorTree(node1.left, node2.right)
                    && isMirrorTree(node1.right, node2.left));
        return false;
    }


}


// FOR BST
class TreeNode1 {
    int data;
    TreeNode1 left, right;

    public TreeNode1(int data)
    {
        this.data = data;
        left = right = null;
    }
}
