package com.dellead.testethread;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    Pedido cPedido;
    private ProgressBar mProgress;
    Button btnatualiza, btnlimpa;
    Atualizapedidos task;
    ListView listView;
    List<Pedido> Pedidolist;
    Adapter mAdapter = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnatualiza = findViewById(R.id.bntatualiza);
        btnlimpa = findViewById(R.id.btnlimpa);
        mProgress = findViewById(R.id.progressBar);
        listView = findViewById(R.id.listv);

        btnatualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                task = new Atualizapedidos();
                task.execute();
            }
        });
        btnlimpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder mensagem, mensagem2;
                mensagem = new AlertDialog.Builder(MainActivity.this,android.R.style.Theme_Material_Light_Dialog_Alert);
                mensagem.setTitle("Confirma Limpar a lista");
                mensagem.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (Pedidolist.size() > 0) {
                            Pedidolist.clear();
                            mAdapter = new Adapter(MainActivity.this,Pedidolist);
                            listView.setAdapter(mAdapter);
                        }else{
                            Toast.makeText(MainActivity.this,"A lista ja está limpa",Toast.LENGTH_LONG).show();
                        }

                    }
                });
                mensagem.show();
            }
        });
    }

    private class Atualizapedidos extends AsyncTask<Integer, Integer, Boolean> {

        String retorno = "";
        int pedido;
        Random gerador = new Random();

        @Override
        protected void onPreExecute() {

            mProgress.setProgress(1);
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Obtendo Lista de pedidos...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(Integer... params) {

            Pedidolist = new ArrayList<Pedido>();

            for (int i = 0; i < 10; i++){

                pedido = (gerador.nextInt(100));
                cPedido = new Pedido();
                cPedido.setCodigo(pedido);
                cPedido.setValor(pedido*3);
                Pedidolist.add(cPedido);
                publishProgress(i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            mProgress.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            pDialog.dismiss();

            mProgress.setProgress(mProgress.getMax());
            mAdapter = new Adapter(MainActivity.this,Pedidolist);
            listView.setAdapter(mAdapter);
            Toast.makeText(MainActivity.this, "Lista atualizada", Toast.LENGTH_LONG).show();

        }

    }

    ////////////////////////////////////
}
