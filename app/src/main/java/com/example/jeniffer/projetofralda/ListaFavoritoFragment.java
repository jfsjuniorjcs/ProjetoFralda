package com.example.jeniffer.projetofralda;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jeniffer.projetofralda.database.FraldaDAO;
import com.example.jeniffer.projetofralda.model.Fralda;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ListaFavoritoFragment extends Fragment {

    @Bind(R.id.list_fralda)
    ListView mListView;

    List<Fralda> mFraldas;
    ArrayAdapter<Fralda> mAdapter;
    FraldaDAO mDao;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mDao = new FraldaDAO(getActivity());
        mFraldas =mDao.listar();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lista_favorito, container, false);
        ButterKnife.bind(this, layout);


        mAdapter = new FraldasAdapter(getContext(),mFraldas);
        mListView.setAdapter(mAdapter);
        return layout;
    }


    @OnItemClick(R.id.list_fralda)
    void onItemSelected(int position) {
        Fralda fralda = mFraldas.get(position);
        if (getActivity() instanceof CliqueiNaFraldaListener) {
            CliqueiNaFraldaListener listener = (CliqueiNaFraldaListener) getActivity();
            listener.fraldaFoiClicada(fralda);
        }

    }

}


