public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Adds an item of type T to the front of the deque.*/
    public void addFirst(T item){
        if(isFull()){
            expand();
        }
        size++;
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);

    }
    private void expand(){
        T[] newArray = (T []) new Object[size * 2];

        int oldPtr = nextFirst;
        int newPtr = plusOne(nextFirst);
        int count = size;


        while(count > 0){
            oldPtr = plusOne(oldPtr);
            newArray[newPtr] = items[oldPtr];
            count--;
            newPtr++;
        }
        nextLast = newPtr;
        items = newArray;
    }
    /** Adds an item of type T to the back of the deque.*/
    public void addLast(T item) {
        if(isFull()){
            expand();
        }
        size++;
        items[nextLast] = item;
        nextLast = plusOne(nextLast);

    }
    /** Returns true if deque is full, false otherwise.*/
    private boolean isFull() {
        if(size == items.length){
            return true;
        }else{
            return false;
        }
    }
    /** Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }else{
            return false;
        }
    }
    /** Returns true if usage factor >= 25% */
    private boolean usageFactorCheck() {
        if((double)size/(double)items.length >= 0.25){
            return true;
        }else{
            return false;
        }
    }

    /** Returns the number of items in the deque.*/
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.*/
    public void printDeque(){
        System.out.println("print:");

        int count = size;
        for(int start = plusOne(nextFirst); count > 0 ; start = plusOne(start) ){
               System.out.print(items[start] + " ");
               count --;
        }
        System.out.println("");

    }
    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.*/
    public T removeFirst() {

        if(isEmpty()){
            return null;
        }else{
            if(!usageFactorCheck() && items.length >= 16 ){
                contract();
            }
            size--;
            nextFirst = plusOne(nextFirst);
            return items[nextFirst];

        }
    }
    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.*/
    public T removeLast() {
        if(isEmpty()){
            return null;
        }else{
            if(!usageFactorCheck() && items.length >= 16 ){
                contract();
            }
            size--;
            nextLast = minusOne(nextLast);
            return items[nextLast];
        }
    }
    private void contract(){
        T[] newArray = (T []) new Object[size * 2];
        int oldPtr = nextFirst;
        int newPtr = 1;
        int count = size;

        while(count > 0){
            oldPtr = plusOne(oldPtr);
            newArray[newPtr] = items[oldPtr];
            count--;
            newPtr++;
        }
        nextFirst = 0;
        nextLast = newPtr;
        items = newArray;
    }
    private int plusOne(int index){
        //if the index reach the bottom of the array
        if(index == items.length - 1){
            return 0;
        }else{
            index++;
            return index;
        }
    }

    private int minusOne(int index){
        //if the index reach the bottom of the array
        if(index == 0){
            return items.length - 1;
        }else{
            index--;
            return index;
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque! */
    public T get(int index) {
        if(isEmpty() || index > size - 1 || index < 0 ) {
            return null;
        }else{
            int resultIndex = nextFirst;
            while(index>=0){
                resultIndex = plusOne(resultIndex);
                index--;
            }
            return items[resultIndex];
        }
    }
}
