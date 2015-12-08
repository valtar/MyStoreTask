import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CircleDependenciesTest {

    @org.junit.Test
    public void testRollArray() throws Exception {
        List<Integer> l1 = Arrays.asList(3, 1, 2);
        List<Integer> l2 = Arrays.asList(1, 2, 3);

        List<Integer> roll = CircleDependencies.rollArray(1, l1);

        assertEquals(l2, roll);
    }
}