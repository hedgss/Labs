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

    public static void filterElem(InputStream in, OutputStream out) throws IOException {
        while (true) {
            int value = in.read();
            if (value > 0) {
                out.write(value);
            } else if (value == -1) {
                break;
            }
        }
    }

    public static void filterBuff(InputStream src, OutputStream dst, int buffSize) throws IOException {
        // System.err.println("buffSize="+buffSize);
        final int ZEROS_STATE = 0;
        final int NUMBERS_STATE = 1;
        byte[] buff = new byte[buffSize];
        int count;
        while ((count = src.read(buff)) != -1) {
            // System.err.println("count="+count);
            int state = ZEROS_STATE;
            int fromIndex = -1;
            for (int index = 0; index < count; index++) {
                byte elem = buff[index];
                switch (state) {
                    case ZEROS_STATE:
                        if (elem == 0) {
                            //NOP
                        } else {
                            fromIndex=index;
                            state = NUMBERS_STATE;
                        }
                        break;
                    case NUMBERS_STATE:
                        if (elem == 0) {
                            // Write to dst stream
                            dst.write(buff,fromIndex,index-fromIndex);
                            state = ZEROS_STATE;
                        } else {
                            //NOP
                        }
                        break;
                }
            }
            if (state == ZEROS_STATE) {
                //NOP
            }
            if (state == NUMBERS_STATE) {
                // Write to dst stream
                // System.err.println("fromIndex="+fromIndex+"    buff.length="+buff.length);
                dst.write(buff, fromIndex, count - fromIndex );
            }
        }
    }
}
