package web;

import com.metier.CatalogueMetierImpl;
import com.metier.ICatalogueMetier;
import com.metier.ProduitModel;
import web.Produit;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControleurServlet extends HttpServlet {
	ICatalogueMetier metier;

	@Override
	public void init() throws ServletException {
		metier = new CatalogueMetierImpl();
	                                           }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	                                                                                                             }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action=req.getParameter("action");
		ProduitModel model = new ProduitModel();
		req.setAttribute("model", model);
		if(action!=null) {
			if(action.equals("search")) {
				
				String keyword = (String) req.getParameter("keyword");
				List<Produit> listProduit = metier.produitParMotCle(keyword);
				model.setListProduit(listProduit);
				model.setKeyword(keyword);
				
			}
			else if(action.equals("save")) {
				try {
				String ref=(String) req.getParameter("ref");
				String des=(String) req.getParameter("des");
				double prix=Double.parseDouble((String) req.getParameter("prix"));
				int quantite=Integer.parseInt((String) req.getParameter("quantite"));
				model.getProduit().setReference(ref);
				model.getProduit().setDesignation(des);;
				model.getProduit().setPrix(prix);;
				model.getProduit().setQuantite(quantite);
				String mode=(String) req.getParameter("mode");
				model.setMode(mode);
				if(model.getMode().equals("add")) {
					metier.ajouterProduit(model.getProduit());
				}else if(model.getMode().equals("edit")){
					metier.updateProduit(model.getProduit());
				}	
				
				}catch (Exception e) {
					model.setErrors(e.getMessage());
				}
				finally {
					model.setListProduit(metier.listProduits());
				}
//				Produit produit=new Produit(ref,des,prix,quantite);
				
//				model.setListProduit(metier.listProduits());
//				
			}
			else if(action.equals("edit")) {
				String reference=req.getParameter("ref");
				Produit p=metier.getProduit(reference);
				model.setProduit(p);
			
				model.setListProduit(metier.listProduits());
				model.setMode("edit");
			}
			else if(action.equals("delete")) {
				String reference=req.getParameter("ref");
				metier.deleteProduit(reference);
				model.setListProduit(metier.listProduits());
			}
		}
		req.setAttribute("produitList", model);
		req.getRequestDispatcher("VueProduit.jsp").forward(req, resp);
		
	}

}
