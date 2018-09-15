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
    private ProductsData data;
    private int page = 1;
    private boolean increment = true;
    private ProdutosAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar os dados dos produtos
        data = new ProductsData();
        data.setQtdeItemPage(30);

        // Instanciar Recyclerview
        this.mRecycledView = findViewById(R.id.recycled_produtos);

        // Inicializar Adapter com uma lista de produtos
        this.mAdapter = new ProdutosAdapter(getApplicationContext(), data.getListPage(page++));
        this.mRecycledView.setAdapter(this.mAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycledView.setLayoutManager(llm);

        // Monitorar a se os ultimos itens do Recycled estao sendo exibidos
        mRecycledView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(increment) {
                    LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
                    int totalItemCount = layoutManager.getItemCount();
                    int lastVisible = layoutManager.findLastVisibleItemPosition();

                    boolean endHasBeenReached = lastVisible + 5 >= totalItemCount;
                    if (totalItemCount > 0 && endHasBeenReached) {
                        mAdapter.incrementRecycledView();
                        toast("Incrementou");
                    }
                }
            }
        });

    }

    /**
     * Exibir mensagem na interface
     */
    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Adapter de produtos
     */
    private class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.MyViewHolder> {
        private List<Product> productsListAdapter;
        private LayoutInflater mLayoutInflater;

        private ProdutosAdapter(Context c, List<Product> produtos) {
            this.mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.productsListAdapter = produtos;
        }

        @Override
        public ProdutosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = mLayoutInflater.inflate(R.layout.row_produto_list, parent, false);
            ProdutosAdapter.MyViewHolder myViewHolder = new ProdutosAdapter.MyViewHolder(v);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(ProdutosAdapter.MyViewHolder holder, int position) {

            Product produto = this.productsListAdapter.get(position);

            holder.id.setText(String.valueOf(produto.getId()));
            holder.descriptionTextView.setText(produto.getDescription());
        }

        /**
         * Incremeta itens no RecycledView
         */
        public void incrementRecycledView() {
            List<Product> prods = data.getListPage(page++);

            if (prods != null) {

                int i = getItemCount();
                for (Product p : prods) {
                    this.productsListAdapter.add(p);
                    notifyItemInserted(i++);
                }
                if(data.getQtdeItemPage() > prods.size()){
                    increment = false;
                }
            } else {
                increment = false;
            }
        }

        @Override
        public int getItemCount() {
            return productsListAdapter.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
            TextView id;
            TextView descriptionTextView;


            public MyViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);

                this.id = itemView.findViewById(R.id.text_id_product);
                this.descriptionTextView = itemView.findViewById(R.id.text_description_product);
            }

            @Override
            public void onClick(View view) {
                toast("onClick posição " + getAdapterPosition());
                Log.i("RecycledView Log", "onClick posição " + getAdapterPosition());
            }

            @Override
            public boolean onLongClick(View v) {
                toast("onLongClick posição " + getAdapterPosition());
                Log.i("RecycledView Log", "onLongClick posição " + getAdapterPosition());
                return true;
            }
        }
    }
}
