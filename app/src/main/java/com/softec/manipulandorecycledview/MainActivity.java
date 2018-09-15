package com.softec.manipulandorecycledview;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycledView;
    private ProdutosDados dados;
    ProdutosAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dados = new ProdutosDados();

        this.mRecycledView = findViewById(R.id.recycled_produtos);

        // Preencher Recyclerview de Produtos
        this.mAdapter = new ProdutosAdapter(getApplicationContext(), dados.getListProdutos());
        this.mRecycledView.setAdapter(this.mAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycledView.setLayoutManager(llm);

        mRecycledView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();

                boolean endHasBeenReached = lastVisible + 5 >= totalItemCount;
                if (totalItemCount > 0 && endHasBeenReached) {
                    mAdapter.incrementRecyclerView();
                    toast("Incrementou");
                }
            }
        });

    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.MyViewHolder> {
        private List<Produto> produtosList;
        private LayoutInflater mLayoutInflater;

        private ProdutosAdapter(Context c, List<Produto> produtos) {
            mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.produtosList = produtos;
        }

        @Override
        public ProdutosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = mLayoutInflater.inflate(R.layout.row_produto_list, parent, false);
            ProdutosAdapter.MyViewHolder myViewHolder = new ProdutosAdapter.MyViewHolder(v);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(ProdutosAdapter.MyViewHolder holder, int position) {

            Produto produto = this.produtosList.get(position);

            holder.id.setText(String.valueOf(produto.getId()));
            holder.descricaoTextView.setText(produto.getDescricao());
        }

        public void incrementRecyclerView() {
            Log.i("Log_teste", "incremento ");
            List<Produto> prods = dados.getListIncrement();

            int i = getItemCount();
            for (Produto p : prods) {
                this.produtosList.add(p);
                notifyItemInserted(i++);
            }

        }

        @Override
        public int getItemCount() {
            return produtosList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView id;
            TextView descricaoTextView;


            public MyViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                this.id = itemView.findViewById(R.id.text_id_produto);
                this.descricaoTextView = itemView.findViewById(R.id.text_descricao_produto);
            }

            @Override
            public void onClick(View view) {
                toast("posição " + getAdapterPosition());
                Log.i("RecycledView Log", "posição " + getAdapterPosition());
            }
        }
    }
}
