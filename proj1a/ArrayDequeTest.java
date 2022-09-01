import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayDequeTest {

    @Test
    public void testAddSizeEmpty() {
        ArrayDeque<String> dq = new ArrayDeque<>();
        assertEquals(true, dq.isEmpty());


        dq.addFirst("first");
        assertEquals(1, dq.size());
        dq.printDeque();

        dq.addLast("last");
        assertEquals(2, dq.size());
        dq.printDeque();

        String first = dq.removeFirst();
        assertEquals("first", first);
        dq.printDeque();

        String last = dq.removeLast();
        assertEquals("last", last);
        dq.printDeque();

        assertEquals(0, dq.size());
    }

    @Test
    public void testgrowshrink() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            dq.addLast(i);
        }
        for (int i = 0; i < 10; i++) {
            assertEquals(i, (int)dq.get(i));
        }
        dq.printDeque();
        for (int i = -10; i < 0; i++) {
            dq.addFirst(i);
        }
        dq.printDeque();

        for (int i = 0; i < 18; i++) {
            dq.removeFirst();
        }
        assertEquals(2, dq.size());
        dq.printDeque();
    }

}

