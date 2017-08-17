package test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;

import com.sofrecom.myshop.controller.ShopController;
import com.sofrecom.myshop.model.Product;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "file:WebContent/WEB-INF/shop-servlet.xml")
public class ShopTestController {

	@Autowired
	private ShopController controller;

	@Test
	public void testAllProducts() {
		
		ExtendedModelMap model = new ExtendedModelMap();
		String viewName = controller.produits(model);

		Product[] products = (Product[]) model.get("products");
		assertNotNull(products);
		assertEquals("all", (String) model.get("activetab"));
		
		assertEquals("shop", viewName);
	}
	
	@Test
	public void testProductById(){
		
		ExtendedModelMap model = new ExtendedModelMap();
		String viewName = controller.detail(Long.valueOf(2), model);

		Product product = (Product) model.get("phone");
		assertNotNull(product);
		
		assertEquals("detail", viewName);
		
	}
	
	@Test
	public void testIphones(){
		
		ExtendedModelMap model = new ExtendedModelMap();
		String viewName = controller.iphones(Long.valueOf(0), Long.valueOf(2000), model);

		Product[] products = (Product[]) model.get("products");
		assertNotNull(products);
		assertEquals("iphones", (String) model.get("activetab"));
		
		assertEquals("shop", viewName);
		
	}
	
	@Test
	public void testSmartphones(){
		
		ExtendedModelMap model = new ExtendedModelMap();
		String viewName = controller.smartphones(model);

		Product[] products = (Product[]) model.get("products");
		assertNotNull(products);
		assertEquals("smartphones", (String) model.get("activetab"));
		
		assertEquals("shop", viewName);
		
	}
	
	@Test
	public void testTablettes(){
		
		ExtendedModelMap model = new ExtendedModelMap();
		String viewName = controller.tablettes(model);

		Product[] products = (Product[]) model.get("products");
		assertNotNull(products);
		assertEquals("tablettes", (String) model.get("activetab"));
		
		assertEquals("shop", viewName);
		
	}
}