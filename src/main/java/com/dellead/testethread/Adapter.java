package com.dellead.testethread;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Adapter extends ArrayAdapter<Pedido> implements Filterable {

    private final Context context;
    private final List<Pedido> values;
    private ArrayList<Pedido> arraylist;


    public Adapter(Context context, List<Pedido> values) {
        super(context, R.layout.list_pedido, values);
        this.context = context;
        this.values = values;
        this.arraylist = new ArrayList<Pedido>();
        this.arraylist.addAll(values);

    }

    static class ViewHolder {
        TextView textViewpedido;
        TextView textViewvlr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;


        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_pedido, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textViewpedido = (TextView) convertView.findViewById(R.id.txt_codpedido);
            viewHolder.textViewvlr = (TextView) convertView.findViewById(R.id.txt_vlr);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textViewpedido.setText(String.valueOf(values.get(position).getCodigo()));
        viewHolder.textViewvlr.setText(String.valueOf(values.get(position).getValor()));

        return convertView;

    }

}
