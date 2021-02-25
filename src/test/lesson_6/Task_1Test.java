package test.lesson_6;

import com.evgeny_k.lesson_6.Task_1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;

@RunWith(Parameterized.class)
public class Task_1Test {
    private static final Logger log = LoggerFactory.getLogger(Task_1.class);


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 2, 3, 4, 3, 7, 2}, new int[]{3, 7, 2}, false},
                {new int[]{1, 2, 3, 4, 3, 4, 2}, new int[]{2}, false},
                {new int[]{1, 2, 3, 4}, new int[]{}, false},
                {new int[]{1, 2, 3}, new int[]{}, true},
        });
    }

    private int inputArray[];
    private int expectedArray[];
    private boolean expectExceptionFired;

    public Task_1Test(int[] input, int[] expected, boolean exceptionFired) {
        this.inputArray = input;
        this.expectedArray = expected;
        this.expectExceptionFired = exceptionFired;
    }


    @Test
    public void test1() {
        if (!expectExceptionFired) {
            assertArrayEquals("Проверим равенство массивов: ", Task_1.arrayAfterFour(inputArray), expectedArray);
            log.info("Для массива: " + Arrays.toString(inputArray) + " получили массив: " + Arrays.toString(Task_1.arrayAfterFour(inputArray)));
        }
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionTask1() {
        if (expectExceptionFired)
            Task_1.arrayAfterFour(inputArray);
        else
            throw new RuntimeException("Все ок");
    }
}
