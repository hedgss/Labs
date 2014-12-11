import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;

public class DeleteZeroLabRandomTest {

    public static final int TEST_COUNT = 1000_000;
    public static final int MIN_INPUT_LEN = 0;
    public static final int MAX_INPUT_LEN = 20;
    public static final int MIN_BUFF_SIZE = 1;
    public static final int MAX_BUFF_SIZE = 30;
    public static final int MIN_ELEM_VALUE = 0;
    public static final int MAX_ELEM_VALUE = 3;

    public static void main(String[] args) throws IOException {
        Random rnd = new Random(0);
        for (int ZeroFilter = 0; ZeroFilter < TEST_COUNT; ZeroFilter++) {
            byte[] testData = new byte[MIN_INPUT_LEN + rnd.nextInt(MAX_INPUT_LEN + 1 -
                    MIN_INPUT_LEN)];
            int buffLen = MIN_BUFF_SIZE + rnd.nextInt(MAX_BUFF_SIZE + 1 - MIN_BUFF_SIZE);
            for (int index = 0; index < testData.length; index++) {
                testData[index] = (byte) (MIN_ELEM_VALUE + rnd.nextInt(MAX_ELEM_VALUE + 1 -
                        MIN_ELEM_VALUE));
            }
            ByteArrayOutputStream buffActual = new ByteArrayOutputStream();
            ByteArrayOutputStream buffExpected = new ByteArrayOutputStream();
            // --- 
            ZeroFilter.filterBuff(stream(testData), buffActual, buffLen);
            ZeroFilter.filterElem(stream(testData), buffExpected);
            // --- 
            byte[] actualResult = buffActual.toByteArray();
            byte[] expectedResult = buffExpected.toByteArray();

            if (!Arrays.equals(actualResult, expectedResult)) {
                throw new AssertionError("expectedResult result:" +
                        Arrays.toString(expectedResult) + " but actualResult result:" + Arrays.toString(actualResult)
                        + " for input testData:" + Arrays.toString(testData) + " and buffLen:" + buffLen);
            }
        }
    }

    public static InputStream stream(byte[] array) {
        return new ByteArrayInputStream(array);
    }
} 
