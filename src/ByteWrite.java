import java.io.File;
import java.io.FileOutputStream;

public class ByteWrite {
    public boolean writeBytes(byte[] pInput, String pNewFile) throws Exception {
        File file = new File(pNewFile);
        if(!file.exists())
            file.createNewFile();

        try (FileOutputStream fos = new FileOutputStream(pNewFile, true)) {
            fos.write(pInput);
        }
        return true;
    }
}
