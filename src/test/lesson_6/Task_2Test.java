package test.lesson_6;

import com.evgeny_k.lesson_6.Task_2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class Task_2Test {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 2, 3, 7}, true},
                {new int[]{2, 3, 4, 2}, true},
                {new int[]{2, 3, 5}, false},
        });
    }

    private int inputArray[];
    private boolean expectedResult;

    public Task_2Test(int[] input, boolean expectedResult) {
        this.inputArray = input;
        this.expectedResult = expectedResult;
    }


    @Test
    public void test1() {
        assertEquals("Проверим есть ли 1 или 4: ", Task_2.find4Or1(inputArray), expectedResult);
    }

}