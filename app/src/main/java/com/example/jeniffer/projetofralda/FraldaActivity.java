package com.example.jeniffer.projetofralda;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.jeniffer.projetofralda.model.Fralda;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FraldaActivity extends AppCompatActivity
        implements CliqueiNaFraldaListener {

    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    @Bind(R.id.tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.toolbar)
    android.support.v7.widget.Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fralda);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mViewPager.setAdapter(new FraldaPager(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);//so vai exibir se informarmos o titulo de cada aba
    }


    class FraldaPager extends FragmentPagerAdapter {

        public FraldaPager(FragmentManager fm) {
            super(fm);
        }

         @Override
            public Fragment getItem(int position) {
                if (position == 0) return new ListaFraldaFragment();
                return new ListaFavoritoFragment();
            }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position==0) return getString(R.string.aba_web);
            return getString(R.string.aba_favoritos);
        }

        @Override
            public int getCount() {
                return 2;
            }
        }


    public void fraldaFoiClicada(Fralda fralda) {
        if (getResources().getBoolean(R.bool.tablet)) {
            DetalheFraldaFragment dlf = DetalheFraldaFragment.newInstance(fralda);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detalhe, dlf, "detalhe")
                    .commit();
        } else {
            Intent it = new Intent(this, DetalheFraldaActivity.class);
            Parcelable p = Parcels.wrap(fralda);
            it.putExtra(DetalheFraldaActivity.EXTRA_FRALDA, p);
            startActivity(it);
        }

    }

}


