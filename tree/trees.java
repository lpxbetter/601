package tree;

//import javax.swing.tree.TreeNode;
import apple.laf.JRSUIUtils;
import sun.awt.AWTIcon32_security_icon_yellow16_png;

import java.util.*;

/**
 */
public class trees {
    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        int val;

        TreeNode(int x) {
            this.left =null;
            this.right = null;
            this.parent  =null;
            this.val = x;
        }
    }
    public static void main(String[] args){
        TreeNode root= new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
//        inorder(root);
//        System.out.println(inorderIteratively(root));
        System.out.println(topK(new int[]{10, 9, 6, 7, 11}, 3));
    }
    public static int[] twoSum(int[] arr, int target){
        int i=0;
        int j=arr.length-1;
        while(i<j){
            if(arr[i]+arr[j]==target) return new int[]{i+1,j+1};
            else if(arr[i]+arr[j]>target) j--;
            else i++;
        }
        return new int[]{-1,-1};
    }

    public static TreeNode getKthNode(TreeNode root, int[] A, int k){
        if(root==null) return null;
        TreeNode resL = getKthNode(root.right,A,k);
        if (resL!=null) return resL;
        A[0]++;
        if(A[0]==k) return root;
        return getKthNode(root.left,A,k);
    }
    public static List<Integer> topK(int[] A,int k){
        Queue<Integer> pq=new PriorityQueue<>(k);
        for(int i=0;i<k;i++){
            pq.add(A[i]);
        }
        for(int i=k;i<A.length;i++){
            if(A[i] > pq.peek()){
                pq.poll();
                pq.add(A[i]);
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        while(! pq.isEmpty()){
            res.add(pq.poll());
        }
        return res;
    }
    // common ancestor
    /*
    p.parent==q.parent ;
     */

    //4.6 find next node, in-order
    // left, root, right
    /*
    if n has right node:
        return leftmost node of n.right
    else{
        while(n is right of n.parent){
            n=n.parent;
        }
    }
    if n==null: return null;
    else: return n.parent;
     */
    TreeNode inorderSucc(TreeNode n){
        if(n.right !=null){
            TreeNode cur=n.right;
            while(cur.left!=null){
                cur=cur.left; // go to the most left
            }
            return cur;
        }
        else{
            while(n.parent!=null && n == n.parent.right){
                n= n.parent;
            }
//            if (n.parent==null) return n.parent;
            return n.parent;
        }
    }
    private static ArrayList<LinkedList<TreeNode>> levelTravesal(TreeNode root){
        ArrayList<LinkedList<TreeNode>> res = new ArrayList<LinkedList<TreeNode>>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<TreeNode> item = new LinkedList<>();
        queue.add(root);
        int lastNum=1;
        int curNum=0;
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            item.add(cur);
            lastNum--;
            if(cur.left !=null) {
                queue.add(cur.left);
                curNum++;
            }
            if(cur.right!=null) {
                queue.add(cur.right);
                curNum++;
            }
            if(lastNum==0){
                res.add(item);
                item  = new LinkedList<>();
                lastNum=curNum;
            }
        }
        return res;
    }

    private static TreeNode createMinimalBST(int[] nums){
        return createMinimalBST(nums, 0, nums.length);
    }
    //0-1-2-3-4-5-6
    private static TreeNode createMinimalBST(int[] nums, int l, int r){
        if(l>r) return null;
        int mid = (l+r)/2;
        TreeNode root=new TreeNode(nums[mid]);
        root.left = createMinimalBST(nums, l, mid-1) ;
        root.right = createMinimalBST(nums,mid+1,r);
        return root;
    }

    private static void inorder(TreeNode root){
        if(root == null) return;
        inorder(root.left);
        System.out.print(root.val);
        inorder(root.right);
    }
    private static ArrayList<Integer> inorderIteratively(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
//        if(root==null) return res;
        LinkedList<TreeNode> stack = new LinkedList<>();
//        stack.push(root);
        while ( root !=null || !stack.isEmpty()) {
//            TreeNode root = stack.peek();
            while (root != null) {
                stack.push(root);
                System.out.println(root.val);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            System.out.println(res);
            root = root.right;
        }
        return res;
    }

    private int check(TreeNode root){
        if (root.left==null && root.right == null) return 1;
        int left = check(root.left);
        if (left == -1) return -1;
        int right = check(root.right);
        if(right == -1) return -1;
        if(Math.abs(left-right) > 1) return -1;
        else{
            return Math.max(left,right) + 1;
        }
    }
    /*
     */

    public TreeNode sortedListToBST(int[] arr) {
        return sortedListToBST(arr,0,arr.length-1);
    }
    public TreeNode sortedListToBST(int[] arr, int l ,int r){
        int m =0;
        if(l>r) return null;
        m = (l+r)/2;
        TreeNode root=new TreeNode(arr[m]);
        root.left = sortedListToBST(arr,l,m-1);
        root.right = sortedListToBST(arr,m+1,r);
        return root;
    }

    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    List<String> helper(int n, int m) {
        if (n == 0) return new ArrayList<String>(Arrays.asList(""));
        if (n == 1) return new ArrayList<String>(Arrays.asList("0", "1", "8"));

        List<String> list = helper(n - 2, m);

        List<String> res = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);

            if (n != m) res.add("0" + s + "0");

            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }

        return res;
    }
    private int height(TreeNode root){
        if(root == null) return -1;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
    public int countNodes(TreeNode root){
        int h = height(root);
        if(height(root.right) == h-1){
            return 1<<h + countNodes(root.right);
        }
        else{
            return 1<<(h-1) + countNodes(root.left);
        }
    }

    public int countUnivalSubtrees(TreeNode root) {
        int[] count = new int[1];
        check(root,count);
        return count[0];
    }
    public boolean check(TreeNode root, int[] count){
        if(root==null) return false;
        if(root.left==null && root.right == null) {
            count[0]++;
            return true;
        }
        boolean l = check(root.left, count);
        boolean r = check(root.right, count);
        if(l&&r){
            if(root.left !=null && root.val != root.left.val
                    || root.right !=null && root.val != root.right.val) return false;
            return true;
        }
        return false;
    }

    public int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return maxSum;
    }
    public int helper(TreeNode root){
        if(root==null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        int curSum = Math.max(left,0) + Math.max(right, 0) + Math.max(root.val, 0);//include cur root
        maxSum = Math.max(maxSum, curSum);
        return Math.max(left,0) + Math.max(right, 0) + root.val;
    }
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        int  n = s.length();
        HashMap<Integer,Integer> map =new HashMap<>();
        for(int i=0;i<=n-10;i++){
            String ss = s.substring(i,i+10);
            int key = change(ss);
            if(map.containsKey(key)){
                if(map.get(key)==1) res.add(ss);
                map.put(key, map.get(key)+1);
            }
            else{
                map.put(key,1);
            }
        }
        return res;
    }
    public int change(String s){
        int res =0;
        for(int i=0;i<s.length();i++){
            res *=10;
            switch(s.charAt(i)){
                case 'A': res += 1; break;
                case 'C': res += 2; break;
                case 'G': res += 3; break;
                case 'T': res += 4; break;
            }
        }
        return res;
    }


}
