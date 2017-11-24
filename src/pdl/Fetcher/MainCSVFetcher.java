package pdl.Fetcher;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;

public class MainCSVFetcher {
    public static void main(String [] args) throws IOException {
        long startTime = System.nanoTime();
        String path = "/Users/Abraham/Downloads/fr.openfoodfacts.org.products.csv";

        /*try {
            RandomAccessFile aFile = new RandomAccessFile(path, "r");
            FileChannel inChannel = aFile.getChannel();
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());

            for (int i = 0; i < buffer.limit(); i++) {
                byte read = buffer.get();
                System.out.print((char)read);
            }
            aFile.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        */
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        // process liness
        int i = 0;
        reader.lines().forEach(line -> {
            // process liness
            System.out.println(line);
        });
        long endTime = System.nanoTime();
        long elapsedTimeInMillis = TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS);
        System.out.println("Total elapsed time: " + elapsedTimeInMillis + " ms");
    }
}
