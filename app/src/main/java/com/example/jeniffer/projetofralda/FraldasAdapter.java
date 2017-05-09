package com.example.jeniffer.projetofralda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jeniffer.projetofralda.model.Fralda;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jeniffer on 17/05/2016.
 */
public class FraldasAdapter extends ArrayAdapter<Fralda> {
    public FraldasAdapter(Context context, List<Fralda>fraldas) {

        super(context,0,fraldas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fralda fralda = getItem(position);
        ViewHolder viewHolder;

        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_fralda, parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.txt.setText(fralda.marca);
        viewHolder.txt1.setText(fralda.modelo);
        Glide.with(getContext()).load(fralda.getImagemFralda()).into(viewHolder.img);
        /*ImageView imageView = (ImageView)convertView.findViewById(R.id.image_fralda);
        TextView text_marca = (TextView)convertView.findViewById(R.id.text_marca);
        TextView text_modelo = (TextView)convertView.findViewById(R.id.text_modelo);

        text_marca.setText(fralda.getMarca());
        text_modelo.setText(fralda.getModelo());*/

        return convertView;

   }
    static class ViewHolder{
        @Bind(R.id.image_fralda)
        ImageView img;
        @Bind(R.id.text_marca)
        TextView txt;
        @Bind(R.id.text_tamanho)
        TextView txt1;

        public ViewHolder(View parent) {
            ButterKnife.bind(this,parent);
            parent.setTag(this);
        }
    }
}
