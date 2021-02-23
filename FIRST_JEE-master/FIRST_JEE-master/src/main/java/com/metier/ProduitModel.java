package com.metier;

import web.Produit;

import java.util.ArrayList;
import java.util.List;

public class ProduitModel {
	private String keyword;
	private List<Produit> listProduit=new ArrayList<>();
	private Produit produit=new Produit();
	private String errors;
	private String mode="add";
	

	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public List<Produit> getListProduit() {
		return listProduit;
	}
	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	
	}

