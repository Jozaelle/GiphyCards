package com.jozasmovietube.controller;

import com.jozasmovietube.model.Gif;
import com.jozasmovietube.model.GifDetail;
import com.jozasmovietube.service.GiphyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GiphyController {

        @Autowired
        GiphyService giphyService;

        @GetMapping("/giphy")
        public List<Gif> returnAllGiphys(@RequestParam String query){
                return giphyService.getSearchResults(query);

        }
        @GetMapping("/detail/{id}")
        public GifDetail getDetails(@PathVariable String id){
                return giphyService.getGifphyDetails(id);
        }

}
