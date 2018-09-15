package com.softec.manipulandorecycledview;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe simula uma base de dados com 100 linhas
 */
public class ProductsData {

    private List<Product> products;
    private int qtdeItemPage;

    /**
     * popular List de produtos
     */
    public ProductsData() {
        this.products = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            products.add(new Product(i, "Descrição do Product " + i));
        }

    }

    public int getQtdeItemPage() {
        return qtdeItemPage;
    }

    public void setQtdeItemPage(int qtdeItemPage) {
        this.qtdeItemPage = qtdeItemPage;
    }

    /**
     * Retorna todos os produtos
     */
    public  List<Product> getListProdutos(){
        return this.products;
    }

    /**
     * Retorna produtos paginados
     */
    public  List<Product> getListPage(int page){
        List<Product> ps = new ArrayList<>();

        int indexInit = (page - 1) * qtdeItemPage;
        int indexFinal = page * qtdeItemPage;

        if(this.products.size() < indexInit){
            return null;
        }else if(this.products.size() < indexFinal) {
            indexFinal = this.products.size();
        }

        for(int i = indexInit; i < indexFinal; i++) {
            ps.add(this.products.get(i));
        }
        return ps;
    }

    /**
     * Obter um da lista de products pelo index
     */
    public Product getProduct(int i){
        return this.products.get(i);
    }
}
