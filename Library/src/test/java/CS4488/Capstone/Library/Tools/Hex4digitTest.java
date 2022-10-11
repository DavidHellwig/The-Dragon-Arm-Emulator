package CS4488.Capstone.Library.Tools;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Hex4digitTest {

    ArrayList<Integer> testNumbers;
    ArrayList<char[]> testHexes;

    @Before
    void init(){
        char[] hex = new char[4];
        hex[0] = '0';
        hex[1] = '0';
        hex[2] = '0';
        hex[3] = '0';

        testNumbers = new ArrayList<>();
        testHexes = new ArrayList<>();

        testNumbers.add(0);
        testHexes.add(hex.clone());

        testNumbers.add(1);
        hex[3] = '1';
        testHexes.add(hex.clone());

        testNumbers.add(16);
        hex[3] = '0';
        hex[2] = '1';
        testHexes.add(hex.clone());

        testNumbers.add(256);
        hex[2] = '0';
        hex[1] = '1';
        testHexes.add(hex.clone());

        testNumbers.add(4096);
        hex[1] = '0';
        hex[0] = '1';
        testHexes.add(hex.clone());

        testNumbers.add(32767);
        hex[0] = 'f';
        hex[1] = 'f';
        hex[2] = 'f';
        hex[3] = 'f';
        testHexes.add(hex.clone());






    }



    private void hexToDecimalTrial(Random random){
        
    }
    @Test
    void hexToDecimal() {
        Random random = new Random();

        for (int i = 0; i<10; i++){
            hexToDecimalTrial(random);
        }

    }

    @Test
    void decimalToHex() {
//        boolean result;
//
//
//
//
//        result = (0 ==)

    }

    @Test
    void hexChar() {
        boolean result = (('0' == Hex4digit.hexChar(0))
                && ('1' == Hex4digit.hexChar(1))
                && ('2' == Hex4digit.hexChar(2))
                && ('3' == Hex4digit.hexChar(3))
                && ('4' == Hex4digit.hexChar(4))
                && ('5' == Hex4digit.hexChar(5))
                && ('6' == Hex4digit.hexChar(6))
                && ('7' == Hex4digit.hexChar(7))
                && ('8' == Hex4digit.hexChar(8))
                && ('9' == Hex4digit.hexChar(9))
                && ('a' == Hex4digit.hexChar(10))
                && ('b' == Hex4digit.hexChar(11))
                && ('c' == Hex4digit.hexChar(12))
                && ('d' == Hex4digit.hexChar(13))
                && ('e' == Hex4digit.hexChar(14))
                && ('f' == Hex4digit.hexChar(15))
        );
        assertTrue(result);
    }

    @Test
    void hexValue() {
        boolean result = ((0 == Hex4digit.hexValue('0'))
                && (1 == Hex4digit.hexValue('1'))
                && (2 == Hex4digit.hexValue('2'))
                && (3 == Hex4digit.hexValue('3'))
                && (4 == Hex4digit.hexValue('4'))
                && (5 == Hex4digit.hexValue('5'))
                && (6 == Hex4digit.hexValue('6'))
                && (7 == Hex4digit.hexValue('7'))
                && (8 == Hex4digit.hexValue('8'))
                && (9 == Hex4digit.hexValue('9'))
                && (10 == Hex4digit.hexValue('a'))
                && (11 == Hex4digit.hexValue('b'))
                && (12 == Hex4digit.hexValue('c'))
                && (13 == Hex4digit.hexValue('d'))
                && (14 == Hex4digit.hexValue('e'))
                && (15 == Hex4digit.hexValue('f'))
        );
        assertTrue(result);
    }


    @Test
    void getMiddle2Value() {
    }

    @Test
    void getLast2Value() {
    }
}