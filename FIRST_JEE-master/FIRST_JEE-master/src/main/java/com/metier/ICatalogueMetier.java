package com.metier;

import web.Produit;

import java.util.List;



public interface ICatalogueMetier {
	void ajouterProduit(Produit produit);
	void deleteProduit(String  reference);
	List<Produit> listProduits();
	List<Produit> produitParMotCle(String keyword);
	Produit getProduit(String reference);
	void updateProduit(Produit p);
	


}
