public class OffByN implements CharacterComparator{
    private int n;

    /** OffByN constructor. */
    public OffByN(int N) {
        this.n = N;
    }
    public boolean equalChars(char x, char y){
        if( x - y == n || y - x == n){
            return true;
        }else{
            return false;
        }
    }
}
