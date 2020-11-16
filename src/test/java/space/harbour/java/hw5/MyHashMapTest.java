package space.harbour.java.hw5;

import org.junit.Test;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class MyHashMapTest {

    MyHashMap<String, Integer> hashMap = new MyHashMap<>();

    //1
    @Test
    public void size() {
        hashMap.put("A", 1);
        hashMap.put("B", 2);
        hashMap.put("C", 3);
        hashMap.put("D", 4);
        hashMap.put("E", 5);
        hashMap.put("F", 6);
        hashMap.put("G", 7);
        assertEquals(7, hashMap.size());
    }

    //2
    @Test
    public void isEmpty() {
        assertEquals(0, hashMap.size());
    }

    //3-4
    @Test
    public void containsKey() {
        //test for empty key
        assertFalse(hashMap.containsKey("G"));
        //test for key
        hashMap.put("G", 7);
        assertTrue(hashMap.containsKey("G"));
    }

    //5-6
    @Test
    public void containsValue() {
        //test for empty value
        assertFalse(hashMap.containsValue(7));
        //test for value
        hashMap.put("G", 7);
        assertTrue(hashMap.containsValue(7));
    }

    //7-8
    @Test
    public void get() {
        //test for empty
        assertEquals(null, hashMap.get("G"));
        //test for non-empty
        hashMap.put("G", 7);
        assertEquals(java.util.Optional.of(7), java.util.Optional.ofNullable(hashMap.get("G")));
    }

    //9
    @Test
    public void put() {
        hashMap.put("G", 7);
        assertTrue(hashMap.containsKey("G"));
        assertTrue(hashMap.containsValue(7));
    }

    //10
    @Test
    public void remove() {
        hashMap.put("F", 6);
        hashMap.put("G", 7);
        assertEquals(2, hashMap.size());
        hashMap.remove("G", 7);
        assertEquals(false, hashMap.containsKey("G"));
        assertEquals(1, hashMap.size());
    }

    //11
    @Test
    public void clear() {
        hashMap.put("E", 5);
        hashMap.put("F", 6);
        hashMap.put("G", 7);
        assertEquals(3, hashMap.size());
        hashMap.clear();
        assertEquals(0, hashMap.size());
    }

    //12
    @Test
    public void keySet() {
        hashMap.put("E", 5);
        hashMap.put("F", 6);
        hashMap.put("G", 7);
        Set<String> set = new HashSet<String>();
        set.add("E");
        set.add("F");
        set.add("G");
        assertEquals(set, hashMap.keySet());
    }

    //13
    @Test
    public void replace() {
        hashMap.put("E", 5);
        assertEquals(java.util.Optional.of(5), java.util.Optional.ofNullable(hashMap.get("E")));
        hashMap.replace("E", 1);
        assertEquals(java.util.Optional.of(1), java.util.Optional.ofNullable(hashMap.get("E")));
    }

    //14
    @Test
    public void nullValue() {
        hashMap.put("A", null);
        assertEquals(null, hashMap.get("A"));
    }
    
    //15-16
    @Test(expected = NullPointerException.class)
    public void nullKey() {
        //adding pair with null key
        hashMap.put(null, 1);
        assertTrue(null, hashMap.containsKey(null));
        //getting value with null key
        assertEquals(java.util.Optional.of(1), java.util.Optional.ofNullable(hashMap.get(null)));
    }
}
