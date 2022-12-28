package com.jozasmovietube.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jozasmovietube.model.Gif;
import com.jozasmovietube.model.GifDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

@Service
public class GiphyService {

    @Value("${giphy.api.url}")
    private String apiURL;
    @Value("${giphy.api.key}")
    private String apiKey;

    public List<Gif> getSearchResults(String searchString) {

            // set up the url to query the giphy API
        String url = apiURL + apiKey + "&limit=12&q=" + searchString; //where does q come from?

            // class for querying external API's
        RestTemplate restTemplate = new RestTemplate();
            // because we have to "pick" through the nodes to get our info
            // we need to do some setup, including an httpentity object
        HttpEntity<String> httpEntity = new HttpEntity<>("");
            // Object mapper will let us walk through the nodes in the response
        ObjectMapper objectMapper = new ObjectMapper();
            // json node object also needed to walk through the response
        JsonNode jsonNode;

            // make the call to the api using restTemplate.exchange
            // sends back a response entity object of type String
        ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

            // create an empty list of gifs
            // says List on left and Array list on right = Programming to the interface
            // List is an interface, Array list is a concrete class the implements the interface (POLYMORPHISM)
        List<Gif> gifList = new ArrayList<>();


        try {  // needed for the objectMapper.readTree method
            jsonNode = objectMapper.readTree(response.getBody());

            JsonNode root = jsonNode.path("data");

            for (int i = 0; i < root.size(); i++) {
                    // root is the json node starting at 'data'
                    // path (i) says which object in the array we are going to
                    // path("id") says which key are we grabbing the data from
                String id = root.path(i).path("id").asText();
                String title = root.path(i).path("title").asText();

                    //in order to get back just the gif we have to format this
                String giphyUrl = "https://media.giphy.com/media/" + id + "/giphy.gif";
                //Gif gif = new Gif();
                //gif.setId((id));
                //gif.setTitle(title);
                //gif.setUrl(giphyUrl);

                    // ^^^ alternative for this is
                Gif gif = new Gif(id, giphyUrl, title);
                    // they have to be in order
                gifList.add(gif);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return gifList;

    }

    public GifDetail getGifphyDetails(String id){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = new HttpEntity<>("");
        ObjectMapper objectMapper = new ObjectMapper();
        String apiUrl = "https://api.giphy.com/v1/gifs/" + id + "?api_key=" + apiKey;
        ResponseEntity<String> response =
                restTemplate.exchange(apiUrl, HttpMethod.GET, httpEntity, String.class);
        JsonNode jsonNode;

        try {
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode root = jsonNode.path("data");
            //String url = root.path("url").asText(); // pulling info from an object
            String gifId = root.path("id").asText();
            String giphyUrl = "https://media.giphy.com/media/" + id + "/giphy.gif";
            String rating = root.path("rating").asText();
            String description = root.path("user").path("description").asText();
            String title = root.path("title").asText();
            String userNAme = root.path("username").asText();
            int height = root.path("images").path("preview").path("height").asInt();
            int width = root.path("images").path("preview").path("width").asInt();

            return new GifDetail(giphyUrl, gifId, rating, description, title, userNAme, height, width);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }



    }
}
