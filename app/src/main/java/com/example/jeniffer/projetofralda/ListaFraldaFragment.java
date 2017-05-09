package com.example.jeniffer.projetofralda;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jeniffer.projetofralda.model.Fralda;
import com.example.jeniffer.projetofralda.model.Tipo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListaFraldaFragment extends Fragment {

    @Bind(R.id.list_fralda)
    ListView mListView;
    @Bind(R.id.swipe)
    SwipeRefreshLayout mSwipe;

    List<Fralda> mFraldas;
    ArrayAdapter<Fralda> mAdapter;
    FraldasTask mtask;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mFraldas = new ArrayList<>();
    }

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFraldas=new ArrayList<>();
        mFraldas.add(new FraldaBuilder().setMarca("Pampers").setModelo("SupremeCare").setTamanho("P").setQuantidade(42).createFralda());
        mFraldas.add(new FraldaBuilder().setMarca("Huggies").setModelo("Menina").setTamanho("P").setQuantidade(36).createFralda());
        mFraldas.add(new FraldaBuilder().setMarca("Sapeca").setModelo("Basic").setTamanho("P").setQuantidade(24).createFralda());
   }*/

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lista_fralda, container, false);
        ButterKnife.bind(this, layout);


        mAdapter = new FraldasAdapter(getContext(),mFraldas);/*new ArrayAdapter<Fralda>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mFraldas);*/
        mListView.setAdapter(mAdapter);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mtask = new FraldasTask();
                mtask.execute();
            }
        });

        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mFraldas.size() == 0 && mtask == null){
            mtask = new FraldasTask();
            mtask.execute();

        }else if (mtask!=null && mtask.getStatus()==AsyncTask.Status.RUNNING){
           showProgress();
        }
    }

    private void showProgress(){
            mSwipe.post(new Runnable() {
                @Override
                public void run() {
                    mSwipe.setRefreshing(true);

                }
            });
    }
    @OnItemClick(R.id.list_fralda)
    void onItemSelected(int position) {
        Fralda fralda = mFraldas.get(position);
        if (getActivity() instanceof CliqueiNaFraldaListener) {
            CliqueiNaFraldaListener listener = (CliqueiNaFraldaListener) getActivity();
            listener.fraldaFoiClicada(fralda);
        }

    }

    class FraldasTask extends AsyncTask<Void, Void, Tipo> {


        protected void OnPreExecute(){
            super.onPreExecute();
            showProgress();
        }
        //qual a funcao do okHttpcliente e para que serve async task
        @Override
        protected Tipo doInBackground(Void... params) {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://dl.dropboxusercontent.com/u/83075312/fraldas.js")
                    .build();

            try {
                //Thread.sleep(5000);
                Response response = client.newCall(request).execute();
                String json = response.body().string();
                Log.d("Jennifer", json);
                Gson gson = new Gson();
                Tipo tp = gson.fromJson(json, Tipo.class);
                return tp;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Tipo tipo) {
            super.onPostExecute(tipo);
            mFraldas.clear();
            if ( tipo != null )
            {
                 mFraldas.addAll(tipo.getFraldas());
            }
            mAdapter.notifyDataSetChanged();
            mSwipe.setRefreshing(false);
            /*if ( tipo != null ){
                mFraldas.clear();
                mFraldas . addAll(tipo.getFraldas());

            }
            mAdapter . notifyDataSetChanged ();
        }*/

        }
    }
}

