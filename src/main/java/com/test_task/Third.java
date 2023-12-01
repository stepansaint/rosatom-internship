package com.test_task;


public class Third { // 3.2 is Palindrome
    
    public static void main(String[] args) {
        String str = "_Madam_";
        boolean isCaseSensitive = false;
        
        System.out.println(isPalindrome(str, isCaseSensitive));
    }
    
    /**
     * Checks if the specified <code>String</code> is palindrome, considering upper and lower cases, or not.
     *
     * @param str             <code>String</code> to be tested
     * @param isCaseSensitive whether <code>str</code> should be tested case-sensitive (upper and lower cases) or not
     * @return true if <code>str</code> is palindrome
     */
    public static boolean isPalindrome(String str, boolean isCaseSensitive) {
        StringBuilder leftToRight = makeStringBuilder(str, isCaseSensitive);
        StringBuilder rightToLeft = new StringBuilder(leftToRight).reverse();
        
        return leftToRight.compareTo(rightToLeft) == 0;
    }
    
    /**
     * Makes <code>StringBuilder</code> from the specified <code>str</code>, using both upper and lower cases, or not.
     *
     * @param str             <code>String</code> to be converted to <code>StringBuilder</code>
     * @param isCaseSensitive if <code>StringBuilder</code> should contain upper and lower case characters, or not
     * @return <code>StringBuilder</code> made from <code>str</code>
     */
    private static StringBuilder makeStringBuilder(String str, boolean isCaseSensitive) {
        StringBuilder chars = new StringBuilder();
        
        if (isCaseSensitive) {
            str.chars()
                    .forEach(chars::appendCodePoint);
        } else {
            str.chars()
                    .map(Character::toLowerCase)
                    .forEach(chars::appendCodePoint);
        }
        
        return chars;
    }
}
