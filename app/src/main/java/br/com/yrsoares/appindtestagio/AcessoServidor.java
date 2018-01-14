package br.com.yrsoares.appindtestagio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.StrictMode;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by yrsoa on 10/03/17.
 */
public class AcessoServidor {
    private int  TIMEOUT_MILLISEC = 3000;

    public String chamadaGet(String url)
    {
        HttpClient httpclient = new DefaultHttpClient();

        HttpGet chamadaget = new HttpGet(url);
        String retorno = "";

        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(chamadaget,
                    responseHandler);

            retorno = responseBody;

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Throwable t) {
            Log.i("erro", t.toString());
        }

        return retorno;

    }

    protected Bitmap CarregarImagem(JSONObject j) throws JSONException {

        //recebendo o objeto JSON com os produtos e pegando a url das imagens, posicao "medium"
        JSONArray arrayimages = j.getJSONArray("images");
        JSONObject imgObject = arrayimages.getJSONObject(0);
        String urlImagem = imgObject.getString("medium");

        //fazendo a conexao, enviando a url da imagem e recebendo ela como Bitmap
        try {
            URL url = new URL(urlImagem);
            HttpURLConnection conexao = (HttpURLConnection)
                    url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setDoInput(true);
            conexao.connect();

            InputStream is = conexao.getInputStream();
            Bitmap imagem = BitmapFactory.decodeStream(is);
            return imagem;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
