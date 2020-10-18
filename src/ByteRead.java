import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteRead {
    public byte[] readBytes(File file) {
        if (!file.exists()) {
            System.out.println("Input[File] is not a file");
            System.exit(0);
        }

        long length = file.length();
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        try {
            InputStream is = new FileInputStream(file);

            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
            is.close();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "[ReadFileBytes]");
        }
        return bytes;
    }
}
