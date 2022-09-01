public class LinkedListDeque<T> {
    public class IntNode{
        public IntNode prev;
        public T item;
        public IntNode next;


        public IntNode(IntNode p, T i, IntNode n){
            prev = p;
            item = i;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new IntNode(null, null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x){
        sentinel = new IntNode(null, null,null);
        IntNode p = new IntNode(sentinel, x, sentinel);
        sentinel.next = p;
        sentinel.prev = p;
        size = 1;
    }

    /** Adds an item of type T to the front of the deque.*/
    public void addFirst(T item){
        size++;
        IntNode p = sentinel;
        p.next = new IntNode(sentinel, item, sentinel.next);
        if(sentinel.next.next == sentinel){
            sentinel.prev = sentinel.next;
        }
    }
    /** Adds an item of type T to the back of the deque.*/
    public void addLast(T item) {
        size++;
        IntNode p = sentinel.prev;
        p.next = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev = p.next;
    }
    /** Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        if(sentinel.next == sentinel){
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
        IntNode p = sentinel;
        while(p.next != sentinel){
            p = p.next;
            System.out.print(p.item+" ");
        }
    }
    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.*/
    public T removeFirst() {
        if(isEmpty()){
            return null;
        }else{
            size--;
            T result = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            return result;
        }
    }
    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.*/
    public T removeLast() {
        if(isEmpty()){
            return null;
        }else{
            size--;
            T result = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            return result;
        }
    }
    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     * Must use iteration, not recursion.*/
    public T get(int index ) {
        if(isEmpty() || index > size - 1 || index < 0 ) {
            return null;
        }else{
            IntNode p = sentinel.next;
            while(p.next != sentinel && index > 0){
                p = p.next;
                index--;
            }
            return p.item;
        }
    }
    public T getRecursive(int index){
        if(isEmpty() || index > size - 1 || index < 0) {
            return null;
        }else{
            return getR(sentinel.next, index);
        }
    }
    private T getR(IntNode l, int i) {
        if (i == 0) {
            return l.item;
        }
        return getR(l.next, i - 1);
    }
}
