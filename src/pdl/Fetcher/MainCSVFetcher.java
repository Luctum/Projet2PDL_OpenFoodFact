package pdl.Fetcher;

import com.opencsv.CSVReader;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class MainCSVFetcher {
    public static void main(String [] args) throws IOException {
        long startTime = System.nanoTime();
        String path = "/Users/Abraham/Downloads/fr.openfoodfacts.org.products.csv";

        /* Read Test One Slow ~27 minutes  */
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

        /* Read Test OpenCSV Test ~17min */
        /*CSVReader csvReader = new CSVReader(new FileReader(path));
        String [] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            System.out.println(nextLine[0] + "etc...");
        }
        */

        /* Read Test one much faster ~31 seconds  */
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        // process liness
        int i = 0;
        reader.lines().forEach(line -> {
            // process liness
        });
        */

        /* Read Test Java Stream ~41 seconds */
        /*Path file = Paths.get("/Users/Abraham/Downloads/fr.openfoodfacts.org.products.csv");

        try{
            //Java 8: Stream class
            Stream<String> lines = Files.lines(file, StandardCharsets.UTF_8 );

            for( String line : (Iterable<String>) lines::iterator )
            {
                System.out.println(line);
            }

        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        */

        long endTime = System.nanoTime();
        long elapsedTimeInMillis = TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS);
        System.out.println("Total elapsed time: " + elapsedTimeInMillis + " ms");
    }
}
