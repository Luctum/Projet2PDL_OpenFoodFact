package pdl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import pdl.Fetcher.ApiFetcher;


import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

public class JacksonTester {
    public static void main(String[] args) throws Exception {
        JacksonTester.formatObjectToJsonString();
        JacksonTester.formatJsonString();
    }

    private static void formatObjectToJsonString() throws IOException {
        Recording recording = new Recording();
        recording.setId(1L);
        recording.setTitle("category of products");
        recording.setReleaseDate(LocalDate.of(1969, Month.JANUARY, 17));
        ;

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String json = mapper.writeValueAsString(recording);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static void formatJsonString() throws Exception {
        String json = new ApiFetcher().getProducts().get(0);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object jsonObject = mapper.readValue(json, Object.class);
            String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
            System.out.println(prettyJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

