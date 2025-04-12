package SegemntTree;


import java.util.*;

class Construct{
    public List<Integer> buildTree(List<Integer> list){
        int n = 4* list.size();
        List<Integer> recList = new ArrayList<>();
        for(int i=0;i<n;i++){
            recList.add(-1);
        }
        constructTree(0, list.size()-1, 0, list, recList);
        return recList;
    }

    private Integer constructTree(int i, int j, int idx, List<Integer> list, List<Integer> recList){
        if(i > j) return -1;
        if(i == j) {
            recList.set(idx, list.get(i));
            return list.get(i);
        }
        int mid = i + (j-i)/2;
        int left = constructTree(i, mid, 2*idx+1, list, recList);
        int right = constructTree(mid + 1, j, 2*idx+2, list, recList);
        recList.set(idx, Math.max(left, right));
        return Math.max(left, right);
    }

    public int maxRangeElement(List<Integer> list, int l, int r){
        int i = 0;
        int j = 7;
        return find(i, j, l, r, 0,list);
    }

    private int find(int i, int j, int l, int r, int idx, List<Integer> list){
        if(j < l || i > r){
            return -1;
        }

        if( i >= l && j <= r){
            return list.get(idx);
        }

        int mid = i + (j-i)/2;
        int left = find(i, mid, l, r, 2*idx+1, list);
        int right = find(mid+1, j,l,r, 2*idx+2, list);

        return Math.max(left, right);
    }
}




public class Main{
    public static void main(String[] args) {
        System.out.println("segment tree");
        List<Integer> list = new ArrayList<>();
        Construct construct = new Construct();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);

        List<Integer> segTree = construct.buildTree(list);
        List<Integer> subList = segTree.subList(0,15);
        System.out.println(list);
        System.out.println(subList);

        int ans = construct.maxRangeElement(subList,4,6);

        System.out.println(ans);

    }
}