package ArraysStrings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipingxiong on 8/24/15.
 */
/*
Given a sorted integer array without duplicates, return the summary of its ranges.
For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */
public class summaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String > list = new ArrayList<>();

        for(int i=0;i<nums.length;i++){
            int temp = nums[i];
            while( i+1 < nums.length && nums[i+1]==nums[i]+1){
                i++;
            }
            if(nums[i]!=temp){
                list.add(temp+"->"+nums[i]);
            }
            else{
                list.add(""+nums[i]);
            }
        }
        return list;
    }
}
