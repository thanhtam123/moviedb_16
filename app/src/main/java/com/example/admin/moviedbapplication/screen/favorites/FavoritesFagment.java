package com.example.admin.moviedbapplication.screen.favorites;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.MovieRepository;
import com.example.admin.moviedbapplication.data.source.local.MovieDatabase;
import com.example.admin.moviedbapplication.data.source.local.MovieLocalDataSource;
import com.example.admin.moviedbapplication.data.source.remote.MovieRemoteDataSource;
import com.example.admin.moviedbapplication.screen.detail.DetailActivity;
import com.example.admin.moviedbapplication.utils.Utils;

import java.util.List;

/**
 * Created by TamTT on 8/22/2018.
 */

public class FavoritesFagment extends Fragment implements FavoritesContract.View,
        FavoritesAdapter.OnItemClickListener {

    private FavoritesContract.Presenter mPresenter;
    private FavoritesAdapter mAdapter;

    public static FavoritesFagment newInstance() {
        return new FavoritesFagment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(),
                MovieLocalDataSource.getinstance(MovieDatabase.getInstance(getContext()).movieDao()));
        mPresenter = new FavoritesPresenter(this, movieRepository);
        mPresenter.loadListMovie();
    }

    @Override
    public void displayFavMovies(List<Movie> movies) {
        RecyclerView recyclerFavorites = getView().findViewById(R.id.recycler_favorite);
        recyclerFavorites.setHasFixedSize(true);
        recyclerFavorites.setLayoutManager(
                new LinearLayoutManager(getContext()));
        mAdapter = new FavoritesAdapter(getContext(), movies);
        mAdapter.setListener(this);
        recyclerFavorites.setAdapter(mAdapter);
    }

    @Override
    public void updateData(String id) {
        mAdapter.removeItem(id);
    }

    @Override
    public void onMovieClick(Movie movie) {
        if (Utils.isOnline(getContext())){
            startActivity(DetailActivity.getMovieIntent(getContext(), movie));
        }
        else{
            Toast.makeText(getContext(), getString(R.string.msg_no_internet), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRemoveClick(String id) {
        initAlertDialog(id);
    }

    public void initAlertDialog(final String id) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(getString(R.string.msg_confirm_delete));
        alertDialog.setMessage(getResources().getString(R.string.msg_delete_favorite));
        alertDialog.setIcon(R.drawable.ic_launcher_background);
        alertDialog.setPositiveButton(getResources().getText(R.string.delete),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        mPresenter.deleteMovie(id);
                    }
                });
        alertDialog.setNegativeButton(getResources().getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
