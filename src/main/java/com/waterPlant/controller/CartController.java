package com.waterPlant.controller;

import java.security.Principal;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waterPlant.dao.UserRepository;
import com.waterPlant.pojo.Cart;
import com.waterPlant.pojo.CartData;
import com.waterPlant.pojo.Orders;
import com.waterPlant.pojo.Product;
import com.waterPlant.pojo.User;
import com.waterPlant.services.OrderDeatilsServiceImpl;
import com.waterPlant.services.OrderServiceImpl;
import com.waterPlant.services.ProductServiceImpl;

@Controller
@RequestMapping("/user")
public class CartController {

	@Autowired
	ProductServiceImpl productServiceImpl;
	@Autowired
	OrderServiceImpl orderServiceImpl;
	@Autowired
	UserRepository userRepository;

	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id, Model m) {
//		int [] pid=new int[20];

//		boolean exists;
//		System.out.println("in add to cart");
//		CartData.cart.add(productServiceImpl.getProductById(id).get());
//		System.out.println( CartData.cart.get(id).getProductName());

		if (CartData.cart.size() < 1) {
			CartData.cart.add(productServiceImpl.getProductById(id).get());
//			CartData.qt.add(1);
			CartData.cart.get(0).setQuantityInCart(1);
			CartData.cart.get(0).setAmountInCart(CartData.cart.get(0).getPrice());
//			CartData.qt.add(new Cart());
//			CartData.qt.get(0).setQuantity(1);
		} else {
			int i;
			for (i = 0; i < CartData.cart.size(); i++) {
				System.out.println("in for loop");

				if (id == (int) CartData.cart.get(i).getId()) {
					System.out.println("in if");
//					int quantityOfProduct=CartData.qt.get(i).intValue();
//					quantityOfProduct++;
//					int quantityOfProduct=CartData.qt.get(i).getQuantity();
//					quantityOfProduct++;
					int quantityOfProduct=CartData.cart.get(i).getQuantityInCart();
					quantityOfProduct++;
//					CartData.qt.set(i, quantityOfProduct);
//					CartData.qt.get(i).setQuantity(quantityOfProduct);
					CartData.cart.get(i).setQuantityInCart(quantityOfProduct);
//					CartData.cart.get(i).setAmountInCart(CartData.cart.get(i).getPrice()*quantityOfProduct);
//					System.out.println(CartData.qt.get(i).intValue());
					return "redirect:/orderPage";
				}
			}
			CartData.cart.add(productServiceImpl.getProductById(id).get());
//			CartData.qt.add(1);
//			CartData.qt.get(++i).setQuantity(1);
			CartData.cart.get(i).setQuantityInCart(1);
			CartData.cart.get(i).setAmountInCart(CartData.cart.get(i).getPrice());
			System.out.println("in else");
			

		}
		m.addAttribute("cartCount", CartData.cart.stream().mapToInt(Product::getQuantityInCart).sum());
//		CartData.cart.add(productServiceImpl.getProductById(id).get());
		return "redirect:/orderPage";

	}

	@GetMapping("/cart")
	public String cartGet(Model m,Principal principal) {
		String userName = principal.getName();
		User user = userRepository.getUserByUserName(userName);
		m.addAttribute("user", user);
		
//		m.addAttribute("cartCount", CartData.cart.size());
		
		m.addAttribute("cartCount", CartData.cart.stream().mapToInt(Product::getQuantityInCart).sum());
		
		m.addAttribute("amount", CartData.cart.stream().mapToDouble(Product::getAmountInCart).sum());
		m.addAttribute("cart", CartData.cart);
		m.addAttribute("qt", CartData.qt);
		m.addAttribute("products", productServiceImpl.getAllProduct());
		for(int i=0;i<CartData.cart.size();i++) {
			System.out.println(CartData.cart.get(i).getAmountInCart());
			
		}
		return "/user/cart";
	}

	@GetMapping("/removeItem/{id}")
	public String cartItemRemove(@PathVariable int id) {
		CartData.cart.remove(id);
	
		return "redirect:/user/cart";
	}
	@GetMapping("/addQuantity/{id}")
	public String addQuantity(@PathVariable int id) {
		int curentQt=CartData.cart.get(id).getQuantityInCart();
		CartData.cart.get(id).setQuantityInCart(++curentQt);
	
		return "redirect:/user/cart";
	}
	
	@GetMapping("/minusQuantity/{id}")
	public String minusQuantity(@PathVariable int id) {
		int curentQt=CartData.cart.get(id).getQuantityInCart();
		CartData.cart.get(id).setQuantityInCart(--curentQt);
	
		return "redirect:/user/cart";
	}
	/*
	 * @GetMapping("/thanks") public String tothanks() {
	 * 
	 * return "redirect:/thanks"; }
	 */

	@GetMapping("/placeOrder")
	public String toplaceOrder(){
		
		return "redirect:thanks";
	}

}
