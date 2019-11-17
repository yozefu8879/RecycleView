package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recycleview.data.AppDataBaseSingleton;
import com.example.recycleview.data.AppDatabase;
import com.example.recycleview.data.Pokemon;
import com.example.recycleview.data.PokemonShort;
import com.example.recycleview.utils.PokemonListParse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.recycleview.MainActivity.LOG_TAG;
import static com.example.recycleview.utils.NetworkUtils.createUrl;
import static com.example.recycleview.utils.NetworkUtils.makeHttpRequest;

public class PokemonDetailActivity extends AppCompatActivity {

    PokemonShort pokemonShort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
        Parcelable extra = getIntent().getParcelableExtra(MainActivity.EXTRA_DATA);
        if (extra instanceof PokemonShort) {
            pokemonShort = (PokemonShort) extra;
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
    private class GetPokemonsAsyncTask extends AsyncTask<Void, Void, Pokemon> {

        @Override
        protected Pokemon doInBackground(Void... voids) { List<PokemonShort> pokemonList = new ArrayList<>();
            URL url = createUrl("https://pokeapi.co/api/v2/pokemon/rowlet");
            // Hacemos la petición. Ésta puede tirar una exepción.
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);

                pokemonList = PokemonListParse.parseMachineArray(jsonResponse);

            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem making the HTTP request.", e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Pokemon pokemon) {
            super.onPostExecute(pokemon);
        }
    }

}