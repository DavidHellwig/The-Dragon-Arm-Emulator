package CS4488.Capstone.Library.Tools;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HexadecimalConverterTest {

    ArrayList<Integer> testNumbers;
    ArrayList<char[]> testHexes;

    void init(){
        if (testHexes == null){
            testHexes = new ArrayList<>();
        } else { testHexes.clear();}
        if (testNumbers == null){
            testNumbers = new ArrayList<>();
        } else {testNumbers.clear();}
    }

    void populateNumbers(ArrayList<Integer> list){
        list.add(0);
        list.add(1);
        list.add(16);
        list.add(256);
        list.add(4096);
        list.add(65535);
//        list.add(-1);
//        list.add(-16);
//        list.add(-256);
//        list.add(-4096);
//        list.add(-65536);
//        list.add(MockDataGenerator.getRandomHexValue4());
//        list.add(MockDataGenerator.getRandomHexValue4());
//        list.add(MockDataGenerator.getRandomHexValue4());
//        list.add(MockDataGenerator.getRandomHexValue4());
    }
    void populateHexes(ArrayList<char[]> list){
        char[] hex = new char[4];
        hex[0] = '0';
        hex[1] = '0';
        hex[2] = '0';
        hex[3] = '0';
        list.add(hex.clone());
        hex[3] = '1';
        list.add(hex.clone());

        hex[2] = '1';
        hex[3] = '0';
        list.add(hex.clone());

        hex[1] = '1';
        hex[2] = '0';
        list.add(hex.clone());

        hex[0] = '1';
        hex[1] = '0';
        list.add(hex.clone());
        hex[0] = 'f';
        hex[1] = 'f';
        hex[2] = 'f';
        hex[3] = 'f';
        list.add(hex.clone());
//        list.add(-1);
//        list.add(-16);
//        list.add(-256);
//        list.add(-4096);
//        list.add(-65536);
//        list.add(MockDataGenerator.getRandomHexChar4());
//        list.add(MockDataGenerator.getRandomHexChar4());
//        list.add(MockDataGenerator.getRandomHexChar4());
//        list.add(MockDataGenerator.getRandomHexChar4());
    }

    @Test
    void integratedTest(){
        init();
        int trails = 100;

        for (int i=0; i<trails; i++){
            testNumbers.add(MockDataGenerator.getRandomHexValue4());
            testHexes.add(HexadecimalConverter.decimalToHex(testNumbers.get(i).intValue()));
        }
        boolean check = true;

        System.out.println("\nTesting " + trails + " Decimals to Hex");
        int convertBack;
        for (int i=0; i<trails; i++){
            System.out.println("Num: " + testNumbers.get(i));
            System.out.println("Hex: " + new String(testHexes.get(i)));
            convertBack = HexadecimalConverter.hexToDecimal(testHexes.get(i));
            System.out.println("Convert: " + convertBack);
            check = testNumbers.get(i).equals(convertBack);
            System.out.println(check);
            if (!check){
                break;
            }
        }

        //assertTrue(check);

        init();
        for (int i=0; i<trails; i++){
            testHexes.add(MockDataGenerator.getRandomHexChar5());
            testNumbers.add(HexadecimalConverter.hexToDecimal(testHexes.get(i)));
        }

        System.out.println("\nTesting " + trails + " Hexes to Decimals");
        char[] charBack = HexadecimalConverter.makeBlankChar5();
        for (int i=0; i<trails; i++){
            System.out.println("Hex: " + new String(testHexes.get(i)));
            System.out.println("Num: " + testNumbers.get(i));

            charBack = HexadecimalConverter.decimalToHex(testNumbers.get(i));
            System.out.println("Convert: " + new String(charBack));

            for (int j=0; j<5; j++){
                check = (charBack[j] == testHexes.get(i)[j]);
            }
            System.out.println(check);
            if (!check){
                break;
            }
        }

        assertTrue(check);


    }

    @Test
    void minAndMaxConversionTest(){
        String max = new String(HexadecimalConverter.decimalToHex(HexadecimalConverter.MAX));
        String min = new String(HexadecimalConverter.decimalToHex(HexadecimalConverter.MIN));
        System.out.println("Max Num: " + HexadecimalConverter.MAX);
        System.out.println("Max Hex: " + max);
        int a = HexadecimalConverter.hexToDecimal(max.toCharArray());
        System.out.println("Undo: " + a);
        System.out.println("Min Num: " + HexadecimalConverter.MIN);
        System.out.println("Min Hex: " + min);
        int b =HexadecimalConverter.hexToDecimal(min.toCharArray());
        System.out.println("Undo: " + b);
        boolean check;
        check = (HexadecimalConverter.MAX == a);
        check = (HexadecimalConverter.MIN == b);

        assertTrue(check);
    }

    @Test
    void hexToDecimal() {
        init();
        populateHexes(testHexes);
        ArrayList<Integer> answers = new ArrayList<>();
        populateNumbers(answers);
        for (char[] c : testHexes){
            testNumbers.add(HexadecimalConverter.hexToDecimal(c));
        }
        boolean areEqual = true;
        for (int i=0; i<testNumbers.size(); i++){
            areEqual = (testNumbers.get(i).equals(answers.get(i)));
            System.out.println(testNumbers.get(i) + " is " + answers.get(i) + areEqual);

        }
        assertTrue(areEqual);

    }

    @Test
    void decimalToHex() {
        init();
        testNumbers.clear();
        testHexes.clear();
        populateNumbers(testNumbers);
        ArrayList<char[]> answers = new ArrayList<>();
        populateHexes(answers);
        for (Integer n : testNumbers){
            testHexes.add(HexadecimalConverter.decimalToHex(n));
        }
        boolean areEqual = true;
        for (int i=0; i<testHexes.size(); i++){
            for (int j=0; j<testHexes.get(i).length-1; j++){
                areEqual = (testHexes.get(i)[j] == answers.get(i)[j]);
            }
        }
        assertTrue(areEqual);
    }

    @Test
    void hex4digitValueWrap(){
        int margin = 10;
        int a = HexadecimalConverter.MAX + margin;
        int b = HexadecimalConverter.MIN - margin;
        boolean check;
        int result = 0;

        int expectation = margin;
        System.out.println("X: " + margin + " E(x): " + expectation);
        result = HexadecimalConverter.hex4digitValueWrap(margin);
        check = (expectation == result );
        System.out.println("F(x): " + result);


        expectation = HexadecimalConverter.MAX;
        System.out.println("X: " + expectation + " E(x): " + expectation);
        result = HexadecimalConverter.hex4digitValueWrap(HexadecimalConverter.MAX);
        check = (expectation == result);
        System.out.println("F(x): " + result);

        expectation = HexadecimalConverter.MIN;
        System.out.println("X: " + expectation + " E(x): " + expectation);
        result = HexadecimalConverter.hex4digitValueWrap(HexadecimalConverter.MIN);
        check = (expectation == result);
        System.out.println("F(x): " + result);

        expectation = HexadecimalConverter.MIN + margin;
        System.out.println("X: " +  a + " E(x): " + expectation);
        result = HexadecimalConverter.hex4digitValueWrap(a);
        check = (expectation == result);
        System.out.println("F(x): " + result);

        expectation = HexadecimalConverter.MAX - margin;
        System.out.println("X: " + b + " E(x): " + expectation);
        result = HexadecimalConverter.hex4digitValueWrap(b);
        check = (expectation == result);
        System.out.println("F(x): " + result);

        assertTrue(check);
    }

    @Test
    void cleanCharHex() {
        char[] test1Small = new char[2];
        char[] test2Perfect = new char[5];
        char[] test3Big = new char[7];
        char[] test4Bad = new char[5];
        char[] expectation = new char[5];
        boolean check = true;

        test1Small[0] = '1';
        test1Small[0] = '2';

        test2Perfect[0] = '-';
        test2Perfect[1] = '1';
        test2Perfect[2] = '2';
        test2Perfect[3] = '3';
        test2Perfect[4] = 'e';

        test3Big[0] = '-';
        test3Big[1] = 'a';
        test3Big[2] = 'b';
        test3Big[3] = '1';
        test3Big[4] = '2';
        test3Big[5] = '3';
        test3Big[6] = 'e';

        test4Bad[0] = 'j';
        test4Bad[1] = 'k';
        test4Bad[2] = 'l';
        test4Bad[3] = '1';
        test4Bad[4] = '2';

        // This is the expectation for both test 1 and 4;
        expectation[0] = '+';
        expectation[1] = '0';
        expectation[2] = '0';
        expectation[3] = '1';
        expectation[4] = '2';

        test1Small = HexadecimalConverter.cleanCharHex(test1Small);
        test4Bad = HexadecimalConverter.cleanCharHex(test4Bad);

        for (int i=0; i<5; i++){
            check = (test1Small[i] == expectation[i]);
            check = (test4Bad[i] == expectation[i]);
        }

        // now for test 2 and 3
        expectation[0] = '-';
        expectation[1] = '1';
        expectation[2] = '2';
        expectation[3] = '3';
        expectation[4] = 'e';

        test2Perfect = HexadecimalConverter.cleanCharHex(test2Perfect);
        test3Big = HexadecimalConverter.cleanCharHex(test3Big);

        for (int i=0; i<5; i++){
            check = (test2Perfect[i] == expectation[i]);
            check = (test3Big[i] == expectation[i]);
        }

        assertTrue(check);
    }


    @Test
    void makeBlankChar5() {
        char[] blank = HexadecimalConverter.makeBlankChar5();
        boolean check = (blank[0] == '+');
        for (int i=1; i<5; i++){
            check = (blank[i] == '0');
        }
        assertTrue(check);
    }
}