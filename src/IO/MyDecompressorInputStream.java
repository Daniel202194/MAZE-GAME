package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream in){
        this.in = in;
    }
    @Override
    public int read() throws IOException {
        return 0;
    }

    @Override


    /**
     * This function get byte array from the file and decompress him.
     *
     * @param b
     *@return int

     */
    public int read(byte[] b) throws IOException {
        for(int i=0;i<24;i++){
            b[i] = ((Integer)in.read()).byteValue();
        }
        int cols = (int)b[4]+(int)b[5]+(int)b[6]+(int)b[7]+512;
        int binaryLength;
        int colNum = cols;
        for(int i=24; i < b.length; i++) {
            Integer num = in.read();

            String numAsBinary = Integer.toBinaryString(num);
            binaryLength = numAsBinary.length();

            if (binaryLength < 8) {

                for (int j = 0; j < 8 - binaryLength && colNum > numAsBinary.length(); j++) {
                    numAsBinary = "0" + numAsBinary;
                }
                colNum = colNum - numAsBinary.length();

            } else {
                colNum -= 8;
            }
            for (int k = 0; k < numAsBinary.length() && i < b.length; k++) {
                b[i++] = Byte.parseByte(String.valueOf(numAsBinary.charAt(k)));
            }
            i--;
            if (colNum <= 0) {
                colNum = cols;
            }
        }
        return 0;
    }
}