package nus.iss.paf.day12.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class GiphyService {

    private Logger logger = LoggerFactory.getLogger(GiphyService.class);
    //GIPHY_API_KEY <- set the environment variable
    @Value("${giphy.api.key}")
    private String giphyKey;
    
    private final String GIPHY_SEARCH = "https://api.giphy.com/v1/gifs/search";

    public List<String> getGiphs(String search) {
        return getGiphs(search, 10, "pg");
    }

    public List<String> getGiphs(String search, String rating) {
        return getGiphs(search, 10, "rating");
    }

    public List<String> getGiphs(String search, Integer limit) {
        return getGiphs(search, limit, "PG" );
    }
    public ArrayList<String> getGiphs(String search, Integer limit, String rating) {

           // return getGiphs(search);
           
         String url = UriComponentsBuilder.fromUriString(GIPHY_SEARCH)
                .queryParam("api_key", giphyKey)
                .queryParam("q", search)
                .queryParam("limit", limit)
                .queryParam("rating", rating)
                .toUriString();


        RequestEntity<Void> req = RequestEntity
            .get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();
            
        RestTemplate template = new RestTemplate();
                ///String globalImg="test";
                JsonObject object = null;
                ResponseEntity<String> resp = template.exchange(req, String.class);
                try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
                    JsonReader reader = Json.createReader(is);
                    object = reader.readObject();
                } catch(Exception ex){

                    ex.printStackTrace();
                }

                // System.out.println(object.getJsonArray("data"))
                
                // .getJsonObject("images").getJsonObject("fixed_width").getJsonString("url"));
                JsonArray array = object.getJsonArray("data");
                ArrayList<String> giphyUrl = new ArrayList<String>();

                for (int i=0; i<array.size(); i++) {

                    JsonObject q = array.getJsonObject(i);
                        String image = q.getJsonObject("images")
                            .getJsonObject("fixed_width")
                            .getString("url");

                        System.out.printf("\r\r img>>> %s\n", image);

                        giphyUrl.add(image);

                        logger.info("\r\n Image >>> " + image);
                }
                    
                    // System.out.println("URL >>>>>" + giphyUrl);
                return giphyUrl;




    }

}       
           
          



