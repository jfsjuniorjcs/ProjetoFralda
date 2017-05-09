package com.example.jeniffer.projetofralda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jeniffer.projetofralda.model.Fralda;

import org.parceler.Parcels;

import butterknife.ButterKnife;

public class DetalheFraldaActivity extends AppCompatActivity {

    /*@Bind(R.id.imageFralda)
    ImageView img;
    @Bind(R.id.textVMarca)
    TextView txt1;
    @Bind(R.id.textVModelo)
    TextView txt2;
    @Bind(R.id.textVTamanho)
    TextView txt3;
    @Bind(R.id.textVQuantidade)
    TextView txt4;*/



    public static final String EXTRA_FRALDA = "fralda";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_fralda);

        ButterKnife.bind(this);

        Fralda fralda = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_FRALDA));
        /*txt1.setText(fralda.getMarca());
        txt2.setText(fralda.getModelo());
        txt3.setText(fralda.getTamanho());
        txt4.setText(String.valueOf(fralda.getQuantidade()));
        Glide.with(this).load(fralda.getImagemFralda()).into(img);
*/

        DetalheFraldaFragment dlf = DetalheFraldaFragment.newInstance(fralda);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detalhe, dlf, "detalhe")
                .commit();

    }

}