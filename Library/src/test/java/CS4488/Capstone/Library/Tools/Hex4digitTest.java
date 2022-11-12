package CS4488.Capstone.Library.Tools;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Hex4digitTest {




    @Test
    void getMiddle2Value() {

        Hex4digit testHex = new Hex4digit("+1400");
        int expected = 64;
        System.out.println("Middle value = " + testHex.getMiddle2Value());
        boolean result = (expected == testHex.getMiddle2Value());
        assertTrue(result);

    }

    @Test
    void getLast2Value() {
        Hex4digit testHex = new Hex4digit("+23de");
        int expected = 222;
        System.out.println("Last value = " + testHex.getLast2Value());
        boolean result = (expected == testHex.getLast2Value());
        assertTrue(result);

    }
}