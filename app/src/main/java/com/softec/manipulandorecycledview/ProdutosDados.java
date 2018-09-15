package com.softec.manipulandorecycledview;

import java.util.ArrayList;
import java.util.List;

public class ProdutosDados {

    private List<Produto> produtos;

    public ProdutosDados() {
        this.produtos = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            produtos.add(new Produto(i, "Descrição do Produto " + i));
        }
    }

    public  List<Produto> getListProdutos(){
        return this.produtos;
    }

    public  List<Produto> getListIncrement(){
        List<Produto> ps = new ArrayList<>();
        for(int i = this.produtos.size(); i < this.produtos.size()+50; i++) {
            ps.add(new Produto(i, "Descrição do Produto " + i));
        }
        return ps;
    }

    public Produto getProdudo(int i){
        return this.produtos.get(i);
    }
}
