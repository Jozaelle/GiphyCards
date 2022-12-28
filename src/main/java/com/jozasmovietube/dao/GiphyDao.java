package com.jozasmovietube.dao;

import com.jozasmovietube.model.GifDetail;

import java.util.List;

public interface GiphyDao {

    //CRUD method to save giphy to database
    GifDetail saveGiphy(GifDetail gif);  // CREATE
   // GifDetail updateGiphy(GifDetail gif); 
   // GifDetail deleteGiphy(GifDetail gif);
    List<GifDetail> getAllGiphys(); // READ

    GifDetail getGiphyById(int id); // READ one fromn db

    
}
