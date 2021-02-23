package com.metier;


//for testing delete
import web.Produit;

import java.util.List;

class TestMetier {

    public static void main(String[] args) {
        ICatalogueMetier metier = new CatalogueMetierImpl();



        System.out.println("-----------DELETE produit-------------");
        try {
            metier.deleteProduit("REF07");
            List<Produit> listProdst = metier.listProduits();
            for(Produit p:listProdst) {
                System.out.println(p.getDesignation());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }

}