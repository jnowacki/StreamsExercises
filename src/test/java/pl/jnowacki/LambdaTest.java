package pl.jnowacki;

import org.junit.Test;

public class LambdaTest {


    @Test
    public void testPrintAllArguments(){

        PentaInterface myPentaInterface = (a, b, c, d, e) -> {
            return String.format("%d, %d, %d, %d, %d, ", a, b, c, d, e);
        };

        //za pomoca klasy anonimowej
        PentaInterface myPentaInterface2 = new PentaInterface() {
            @Override
            public String doSthWith5Arguments(int a, int b, int c, int d, int e) {
                return String.format("%d, %d, %d, %d, %d, ", a, b, c, d, e);
            }
        };

        testDoSth(myPentaInterface);
        testDoSth(myPentaInterface2);
    }

    @Test
    public void testPrintSumOfArguments(){

        PentaInterface myPentaInterface = (a, b, c, d, e) -> {
            return String.valueOf(a + b + c + d + e);
        };

        testDoSth(myPentaInterface);
    }

    @Test
    public void testPrettyPrintSumOfArguments(){

        PentaInterface myPentaInterface = (a, b, c, d, e) -> {
            int sum = a + b + c + d + e;
            return String.format("%d + %d + %d + %d + %d = %d", a, b, c, d, e, sum);
        };

        testDoSth(myPentaInterface);
    }

    private void testDoSth(PentaInterface inter) {
        System.out.println(inter.doSthWith5Arguments(2, 5, 3, 6, 6));
    }
}
