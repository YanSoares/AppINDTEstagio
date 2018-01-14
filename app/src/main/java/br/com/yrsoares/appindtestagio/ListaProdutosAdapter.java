package br.com.yrsoares.appindtestagio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yrsoa on 11/03/2017.
 */

public class ListaProdutosAdapter extends ArrayAdapter<Produtos> {

        private Context context;
        private List<Produtos> produtos = null;

        public ListaProdutosAdapter(Context context,  List<Produtos> produtos) {
            super(context,0, produtos);
            this.produtos = produtos;
            this.context = context;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            Produtos produto = produtos.get(position);

            if(view == null)
                view = LayoutInflater.from(context).inflate(R.layout.item_televisores, null);

            ImageView imageViewProduto = (ImageView) view.findViewById(R.id.imgItem);
            imageViewProduto.setImageBitmap(produto.getImagem());

            TextView textViewNomeProduto = (TextView) view.findViewById(R.id.txtItem);
            textViewNomeProduto.setText(produto.getName());

            return view;
        }
}
