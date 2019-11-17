package com.example.recycleview.utils;

import com.example.recycleview.data.Pokemon;
import com.example.recycleview.data.PokemonShort;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class  PokemonListParse {
    public static List<PokemonShort> parseMachineArray(String jsonStr) {
        ArrayList<PokemonShort> pokemonList = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray jsonArray = jsonObj.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                String url = jsonArray.getJSONObject(i).getString("url");
                String name = jsonArray.getJSONObject(i).getString("name");
                pokemonList.add(new PokemonShort(name,url));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pokemonList;
    }


}
