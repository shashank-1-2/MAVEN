package com.example;

public class stringReverse {
    public String reverseString(String str){
        if (str == null || str.isEmpty()) {
            return str;
        }

        char[] chArr = str.toCharArray();
        int left = 0;
        int right = str.length()-1;

        while(left < right){
            char ch = chArr[left];
            chArr[left] = chArr[right];
            chArr[right] = ch;
            left++;
            right--;
        }

        return new String(chArr);
    }
}
