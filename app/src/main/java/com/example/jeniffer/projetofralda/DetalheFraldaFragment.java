package com.example.jeniffer.projetofralda;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jeniffer.projetofralda.database.FraldaDAO;
import com.example.jeniffer.projetofralda.model.Fralda;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DetalheFraldaFragment extends Fragment {
    private static final String EXTRA_FRALDA = "param1";


    @Bind(R.id.text_marca)
    TextView mTextMarca;
    @Bind(R.id.text_modelo)
    TextView mTextModelo;
    @Bind(R.id.text_tamanho)
    TextView mTextTamanho;
    @Bind(R.id.text_quantidade)
    TextView mTextoQuantidade;
    @Bind(R.id.image_fralda)
    ImageView mImagemFralda;

    FraldaDAO mDAO;


    private Fralda mFralda;

    public static DetalheFraldaFragment newInstance(Fralda fralda) {
        DetalheFraldaFragment fragment = new DetalheFraldaFragment();
        Bundle args = new Bundle();
        Parcelable p = Parcels.wrap(fralda);
        args.putParcelable(EXTRA_FRALDA,p);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDAO = new FraldaDAO(getActivity());
        if (getArguments() != null) {
            Parcelable p = getArguments().getParcelable(EXTRA_FRALDA);
            mFralda = Parcels.unwrap(p);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhe, container, false);
        ButterKnife.bind(this, view);

        mTextMarca.setText(mFralda.getMarca());
        mTextModelo.setText(mFralda.getModelo());
        mTextTamanho.setText(mFralda.getTamanho());
        mTextoQuantidade.setText(String.valueOf(mFralda.getQuantidade()));
        Glide.with(getActivity()).load(mFralda.getImagemFralda()).into(mImagemFralda);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @OnClick(R.id.fab_favorito)
    public void favoritoClick(){
        if (mDAO.isFavorito(mFralda)){
            mDAO.excluir(mFralda);
        }else {
            mDAO.inserir(mFralda);
        }
    }

    /*@OnClick(R.id.meuBotao)
    public void meuBotao(){
        Toast.makeText(getContext(),"Olá",Toast.LENGTH_SHORT).show();
    }*/

}
