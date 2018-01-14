package br.com.yrsoares.appindtestagio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //lista com os itens
        ListView listView = (ListView) findViewById(R.id.lstItens);

        //instancia da classe AcessoServidor para pegar a string com o JSON do servidor
        AcessoServidor ar = new AcessoServidor();
        String resultado = ar.chamadaGet("https://mystique-v1-submarino.b2w.io/mystique/search?content=smart%20tv%2032&sortBy=moreRelevant&source=neemu");

        try {
            //criacao do array JSON de produtos
            JSONObject geral = new JSONObject(resultado);
            JSONArray arrayprodutos = geral.getJSONArray("products");

            //criacao de uma arraylist de produtos
            List<Produtos> produtos = new ArrayList<Produtos>();

            //percorrrendo a array JSON, para inserir as informacoes no arrayList
            for (int i = 0; i < arrayprodutos.length(); i++) {
                AcessoServidor imagemDown = new AcessoServidor();

                JSONObject p = arrayprodutos.getJSONObject(i);

                Produtos produto = new Produtos(p.getInt("id"), p.getString("name"), p.getDouble("rate"),
                        imagemDown.CarregarImagem(p));
                produtos.add(produto);
            }
            //setando a listview com o layout item_televisores
            final ListaProdutosAdapter produtosAdapter = new ListaProdutosAdapter(this, produtos);
            listView.setAdapter(produtosAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
