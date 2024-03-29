public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> dq = new LinkedListDeque<>();
        int len = word.length();
        for (int i = 0; i < len; i++) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    public boolean isPalindrome(String word){
        int len = word.length();
        if(word == null | len == 0 || len == 1){
            return true;
        }else{
            boolean flag = true;
            for (int i = 0; i < len/2; i++) {
                if(word.charAt(i) != word.charAt(len-i-1)) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        int len = word.length();
        if(word == null | len == 0 || len == 1) {
            return true;
        }else{
            boolean flag = true;
            for (int i = 0; i < len/2; i++) {
                if(!cc.equalChars(word.charAt(i),word.charAt(len-i-1))) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
    }
}
