package com.metier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import web.Produit;

public class CatalogueMetierImpl implements ICatalogueMetier {

	@Override
	public void ajouterProduit(Produit produit) {
		Connection connection = SingletonConnection.getInstance();
		String query = "INSERT INTO product(REF_PROD,DESIGNATION,PRIX,QUANTITE) VALUES(?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
			ps.setString(1, produit.getReference());
			ps.setString(2, produit.getDesignation());
			ps.setDouble(3, produit.getPrix());
			ps.setInt(4, produit.getQuantite());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// connection.close(); si on fait on ferme la connextion sur tout les autres

	}

	@Override
	public void deleteProduit(String reference) {
		Connection connection=SingletonConnection.getInstance();
		String query = "DELETE FROM product WHERE REF_PROD=?";
		try {
			PreparedStatement ps=(PreparedStatement) connection.prepareStatement(query);
			
			ps.setString(1, reference);
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	@Override
	public List<Produit> listProduits() {
		// REF_PROD,DESIGNATION,PRIX,QUANTITE)
		Connection connection = SingletonConnection.getInstance();
		String query = "SELECT * FROM product";
		Statement statement;
		List<Produit> listProduits = new ArrayList<Produit>();
		try {
			statement = (Statement) connection.createStatement();

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {

				String ref_pro = resultSet.getString("REF_PROD");
				String designation = resultSet.getString("DESIGNATION");
				double prix = resultSet.getDouble("PRIX");
				int quantite = resultSet.getInt("QUANTITE");
				Produit p = new Produit(ref_pro, designation, prix, quantite);
				listProduits.add(p);

			}
			// connection.close(); si on fait on ferme la connextion sur tout les autres
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listProduits;

	}

	@Override
	public List<Produit> produitParMotCle(String keyword) {

		Connection connection = SingletonConnection.getInstance();
		String query = "SELECT * FROM product WHERE DESIGNATION LIKE ?";
		PreparedStatement ps;
		List<Produit> listProduits = new ArrayList<Produit>();
		try {
			ps = (PreparedStatement) connection.prepareStatement(query);
			ps.setString(1, "%" + keyword + "%");
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {

				String ref_pro = resultSet.getString("REF_PROD");
				String designation = resultSet.getString("DESIGNATION");
				double prix = resultSet.getDouble("PRIX");
				int quantite = resultSet.getInt("QUANTITE");
				Produit p = new Produit(ref_pro, designation, prix, quantite);
				listProduits.add(p);

			}
			// connection.close(); si on fait on ferme la connextion sur tout les autres
			ps.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listProduits;
	}

	@Override
	public Produit getProduit(String reference) {

		Connection connection = SingletonConnection.getInstance();
		String query = "SELECT * FROM product WHERE REF_PROD=?";
		PreparedStatement ps;
		Produit produit = null;
		try {
			ps =  (PreparedStatement) connection.prepareStatement(query);
			ps.setString(1, reference);
			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				produit = new Produit();
				String ref_pro = resultSet.getString("REF_PROD");
				String designation = resultSet.getString("DESIGNATION");
				double prix = resultSet.getDouble("PRIX");
				int quantite = resultSet.getInt("QUANTITE");
				produit.setReference(ref_pro);
				produit.setDesignation(designation);
				produit.setPrix(prix);
				produit.setQuantite(quantite);
			}
			// connection.close(); si on fait on ferme la connextion sur tout les autres
			ps.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (produit == null)
			throw new RuntimeException("Produit "+reference+" Introuvable");// genere une execption non serveillee 
		                                                      // ex non serveillee n est pas detectee par compiler
		                                                      // ex non serveillee extends de class RuntimeException
		return produit;
	}

	@Override
	public void updateProduit(Produit p) {
		// REF_PROD,DESIGNATION,PRIX,QUANTITE)
		Connection connection=SingletonConnection.getInstance();
		String query = "UPDATE product SET DESIGNATION=?,PRIX=?,QUANTITE=? WHERE REF_PROD=?";
		try {
			PreparedStatement ps=(PreparedStatement) connection.prepareStatement(query);
			
			ps.setString(1, p.getDesignation());
			ps.setDouble(2, p.getPrix());
			ps.setInt(3, p.getQuantite());
			ps.setString(4, p.getReference());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
