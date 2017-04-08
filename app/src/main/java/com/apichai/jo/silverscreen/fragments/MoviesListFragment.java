package com.apichai.jo.silverscreen.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apichai.jo.silverscreen.R;
import com.apichai.jo.silverscreen.adapters.MoviesAdapter;
import com.apichai.jo.silverscreen.api.ApiClient;
import com.apichai.jo.silverscreen.api.MovieService;
import com.apichai.jo.silverscreen.models.Movie;
import com.apichai.jo.silverscreen.models.MoviesList;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesListFragment extends Fragment {
    @BindView(R.id.movies_list)
    RecyclerView moviesList;

    @BindString(R.string.api_error)
    String apiErrorMessage;

    private GridLayoutManager mGridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_list, container, false);

        ButterKnife.bind(this, view);

        MovieService service = ApiClient.getClient().create(MovieService.class);
        Call<MoviesList> call = service.getPopularMovies(ApiClient.getDefaultQueryParams());

        call.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                moviesList.setHasFixedSize(true);

                mGridLayoutManager = new GridLayoutManager(getContext(), 2);
                moviesList.setLayoutManager(mGridLayoutManager);

                List<Movie> movies = response.body().getResults();
                MoviesAdapter moviesAdapter = new MoviesAdapter(getContext(), movies);
                moviesList.setAdapter(moviesAdapter);
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                Toast.makeText(getContext(), apiErrorMessage, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
