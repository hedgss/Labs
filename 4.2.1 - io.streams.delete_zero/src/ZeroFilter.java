/**
 * io.streams.delete_zero
 * Реализовать метод filter класса ZeroFilter
 * Работа" метода заключается в том, что он копирует вcе _ненулевые_ байты из src в dst.
 * Чтение-запись производится буферами разметом buffSize.
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ZeroFilter {

    public static void filter(InputStream src, OutputStream dst, int buffSize) throws IOException {
        final int ZEROS_STATE = 0;
        final int NUMBERS_STATE = 1;
        byte[] buff = new byte[buffSize];
        int count;
        while ((count = src.read(buff)) != -1) {
            int state = ZEROS_STATE;
            int fromIndex = -1;
            for (int index = 0; index < count; index++) {
                byte elem = buff[index];
                switch (state) {
                    case ZEROS_STATE:
                        if (elem == 0) {
                            ...
                        } else {
                            ...
                        }
                        break;
                    case NUMBERS_STATE:
                        if (elem == 0) {
                            ...
                        } else {
                            ...
                        }
                        break;
                }
            }
            if (state == ZEROS_STATE) {
                ...
            }
            if (state == NUMBERS_STATE) {
                ...
            }
        }
    }
}
