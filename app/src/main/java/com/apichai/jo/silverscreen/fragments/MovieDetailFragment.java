package com.apichai.jo.silverscreen.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apichai.jo.silverscreen.R;
import com.apichai.jo.silverscreen.api.ApiClient;
import com.apichai.jo.silverscreen.api.MovieService;
import com.apichai.jo.silverscreen.models.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailFragment extends Fragment {
    @BindView(R.id.movie_detail_title)
    public TextView movieTitle;

    @BindView(R.id.movie_detail_poster)
    public ImageView moviePoster;

    @BindView(R.id.movie_detail_overview)
    public TextView movieOverview;

    @BindString(R.string.api_error)
    String apiErrorMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        ButterKnife.bind(this, view);

        int id = getActivity().getIntent().getExtras().getInt("id");
        MovieService service = ApiClient.getClient().create(MovieService.class);
        Call<Movie> call = service.getMovieDetail(id, ApiClient.getDefaultQueryParams());

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                movieTitle.setText(movie.getTitle());
                movieOverview.setText(movie.getOverview());
                Picasso.with(getContext()).load(movie.getPosterPath("medium")).into(moviePoster);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(getContext(), apiErrorMessage, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
