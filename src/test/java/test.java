import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import java.util.*;

public class test {

}

class ListNode{
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    boolean hasNext() {
        return next != null;
    }
}
class Solution {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = null;
        ListNode currentNode = null;
        boolean firstNode = true;
        int carry = 0;
        while(l1 != null || l2 != null || carry>0){
            int value = carry + (l1 == null ? 0 : l1.val) + (l2 == null? 0: l2.val);
            if(value > 9){
                carry = 1;
                value = value-10;
            }else{
                carry = 0;
            }
            ListNode node = new ListNode(value);
            if(firstNode){
                res = node;
                currentNode = node;
                firstNode = false;
            }else{
                currentNode.next = node;
                currentNode = currentNode.next;
            }
            l1 = l1 != null ? l1.next: null;
            l2 = l2 != null ? l2.next: null;
        }
        return res;
    }
    public int lengthOfLongestSubstring(String s) {
        StringBuilder currentString = new StringBuilder();
        int maxStringLength = 0;
        for(char c : s.toCharArray()){
            int index = currentString.indexOf(c+"");
            if(index > -1){
                currentString = new StringBuilder(currentString.substring(index+1, currentString.length()));
                currentString.append(c);
            }else{
                currentString.append(c);
                if(currentString.length() > maxStringLength){
                   maxStringLength = currentString.length();
                }
            }
        }
        return maxStringLength;
    }
    public static int lengthOfLongestSubstring_1(String s) {
        Map lastCharAppearIndexMap = new HashMap<Character, Integer>();
        int res = 0;
        int currentLength = 0;
        int charIndex = 0;
        for(char c : s.toCharArray()){
            if(lastCharAppearIndexMap.containsKey(c)){
                currentLength = Math.min(currentLength + 1 ,charIndex - (int)lastCharAppearIndexMap.get(c));
            }else{
                currentLength++;
            }
            res = Math.max(res, currentLength);
            lastCharAppearIndexMap.put(c, charIndex);
            charIndex++;
        }
        return res;
    }

    public static int breakfastNumber(int[] staple, int[] drinks, int x) {
        int chooice = 0;
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int drinkIndex = drinks.length-1;
        for(int stapleIndex = 0; stapleIndex < staple.length ; stapleIndex++) {
            if(staple[stapleIndex] >= x){
                break;
            }
            int remain = x - staple[stapleIndex];

            for(int index = drinkIndex; index >=0; index --){
                if(drinks[index]<= remain){
                    chooice+= (index +1);
                    drinkIndex = index;
                    break;
                }
            }
        }
        return chooice%1000000007;
    }

    public int caculate(String s){
        int x = 1, y = 0;
        for(char c : s.toCharArray()){
            if (c == 'A'){
                x = 2 * x +y;
            }else{
                y = 2 * y + x;
            }
        }
        return x + y;
    }

    public int findTheLongestSubstring(String s) {
        int res = 0;
        int[] arr = new int[1<<5];
        Arrays.fill(arr, -1);
        int index = 0;
        int state = 0; //state 00000
        arr[0] = -1;
        for (char c : s.toCharArray()){
            switch (c){
                case 'a':
                    state ^= 1<<4;
                    break;
                case 'e':
                    state ^= 1<<3;
                    break;
                case 'i':
                    state ^= 1<<2;
                    break;
                case 'o':
                    state ^= 1<<1;
                    break;
                case 'u':
                    state ^= 1;
                    break;
            }
            if(state != 0 && arr[state] == -1){
                arr[state] = index;
            }else{
                res = Math.max(res, index - arr[state]);
            }
            index ++;
        }
        return res;
    };

    @Test
    public void test(){
        System.out.println(findTheLongestSubstring("yopumzgd"));

//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 2; j++) {
//                System.out.println(i+" & "+j+" = "+ (i&j)+ "; " + i+" | "+j+" = "+ (i|j)+ "; " + i+" ^ "+j+" = "+( i^j));
//            }
//        }

    }



}