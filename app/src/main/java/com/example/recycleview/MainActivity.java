package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.recycleview.adapter.PokemonAdapter;
import com.example.recycleview.adapter.PokemonItemListener;
import com.example.recycleview.data.AppDataBaseSingleton;
import com.example.recycleview.data.AppDatabase;
import com.example.recycleview.data.ListDataHelper;
import com.example.recycleview.data.PokemonShort;
import com.example.recycleview.utils.PokemonListParse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.recycleview.utils.NetworkUtils.createUrl;
import static com.example.recycleview.utils.NetworkUtils.makeHttpRequest;

public class MainActivity extends AppCompatActivity implements PokemonItemListener {

    public static String LOG_TAG = MainActivity.class.getSimpleName();

    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(recyclerView.getContext(), 3);

        recyclerView.setLayoutManager(manager);
        adapter = new PokemonAdapter(new ArrayList<PokemonShort>(), this);
        recyclerView.setAdapter(adapter);
    }

    private void updatePokemonList(List<PokemonShort> pokemonShortList) {
        adapter.setItems(pokemonShortList);
    }


    public void addPokemon(View view) {
        Intent intent = new Intent(this, AddPokemonActivity.class);
        startActivity(intent);
    }

    private class GetPokemonsAsyncTask extends AsyncTask<Void, Void, List<PokemonShort>> {
        @Override
        protected List<PokemonShort> doInBackground(Void... voids) {
            // Creamos el objeto URL desde el string que recibimos.


            List<PokemonShort> pokemonList = new ArrayList<>();
            URL url = createUrl("https://pokeapi.co/api/v2/pokemon?limit=86&offset=721");
            // Hacemos la petición. Ésta puede tirar una exepción.
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);

                pokemonList = PokemonListParse.parseMachineArray(jsonResponse);

            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem making the HTTP request.", e);
            }
            return pokemonList;
        }




        @Override
        protected void onPostExecute(List<PokemonShort> pokemonShortList) {
            super.onPostExecute(pokemonShortList);
            if (pokemonShortList != null) {
                updatePokemonList(pokemonShortList);
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        GetPokemonsAsyncTask task =
                new GetPokemonsAsyncTask();
        task.execute();
    }

    public static final String EXTRA_DATA = "EXTRA_DATA";

    @Override
    public void onPokemonClicked(int position) {
        PokemonShort pokemonShort = adapter.getItem(position);
        Intent intent = new Intent(this, PokemonDetailActivity.class);
        intent.putExtra(EXTRA_DATA, pokemonShort);
        startActivity(intent);
    }

}

