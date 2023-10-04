package com.waterPlant.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
	
import com.waterPlant.dao.UserRepository;
import com.waterPlant.exceptions.UserException;
import com.waterPlant.pojo.CartData;
import com.waterPlant.pojo.Orders;
import com.waterPlant.pojo.Product;
import com.waterPlant.pojo.User;
import com.waterPlant.services.OrderServiceImpl;
import com.waterPlant.services.ProductServiceImpl;

@Controller
public class HomeController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProductServiceImpl productService;
	
	@Autowired
	private OrderServiceImpl orderService;
	
	
	//Home Handler
	@RequestMapping("/")
	public String home(Model m) {
//		m.addAttribute("cartCount", CartData.cart.size());
		m.addAttribute("cartCount", CartData.cart.stream().mapToInt(Product::getQuantityInCart).sum());
		m.addAttribute("title", "Home-WaterPlantManagment");
		System.out.println("in home page");
		return "home";
	}
	
	//About page Handler
	@RequestMapping("/about")
	public String about(Model m) {
//		m.addAttribute("cartCount", CartData.cart.size());
		m.addAttribute("cartCount", CartData.cart.stream().mapToInt(Product::getQuantityInCart).sum());
		m.addAttribute("title", "About-WaterPlantManagment");
		System.out.println("in about page");
		return "about";
	}
	@RequestMapping("/about1")
	public String about1(Model m) {
//		m.addAttribute("cartCount", CartData.cart.size());
		m.addAttribute("cartCount", CartData.cart.stream().mapToInt(Product::getQuantityInCart).sum());
		m.addAttribute("title", "About-WaterPlantManagment");
		System.out.println("in about page");
		return "about1";
	}
	@RequestMapping("/contact")
	public String contact(Model m){
		m.addAttribute("title", "contact us");
		System.out.println("in contact page");
	    return "contact";
	}
	
	//Product page Handler
		@RequestMapping(value="/products", method=RequestMethod.GET)
		public String viewProduct(Model m) 
		{
			m.addAttribute("title", "Product-WaterPlantManagment");
			System.out.println("in Product page");
			
			m.addAttribute("products", productService.getAllProduct());
			return "/products";
		}
		
		
	//Signup pageHandler
	@RequestMapping("/signup")
	public String signup(Model m) 
	{
		m.addAttribute("title", "signup-WaterPlantManagment");
		System.out.println("in SignUp page");
		m.addAttribute("user", new User());
		return "signup";
	}
	
	//register
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,Model m,HttpSession session) //@ModelAttribute("user") same as html  User is Variable
	{
		 try {
			 user.setRole("ROLE_USER");
			 user.setEnable(true);
			
			// user.setPassword(user.getPassword());
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			 
			 System.out.println("user info:"+user);
			 @SuppressWarnings("unused")
			User result=this.userRepo.save(user);
			 m.addAttribute("user",new User());
			 session.setAttribute("message", new UserException("Registerd Successfully", "alert-success"));
			 return "signup";
			 
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("user", user);
			session.setAttribute("message", new UserException("Something Went Wrong.."+e.getMessage(), "alert-error"));
			return "signup";
		}
		
	}
	
	//Handler for custom login 
	@GetMapping("/signin")
	public String customLogin(Model model)
	{
		model.addAttribute("title", "Login-WaterPlantManagment");
		CartData.cart.clear();
		return "login";
	}
	@GetMapping("/signing")
	public String customLoginAdmin(Model model)
	{
		model.addAttribute("title", "Login-WaterPlantManagment");
		return "loginAdmin";
	}
	
	
	
	@RequestMapping(value="/orderPage", method=RequestMethod.GET)
	public String addProductGet(Model m) 
	{
//		m.addAttribute("cartCount", CartData.cart.size());
		m.addAttribute("cartCount", CartData.cart.stream().mapToInt(Product::getQuantityInCart).sum());
		m.addAttribute("title", "Order-WaterPlantManagment");
		System.out.println("in add Order page");
		m.addAttribute("products", productService.getAllProduct());
		//m.addAttribute("product", new Product());
		return "orderPage";
	}
	
	//orders--------------------------------------------------------------------------------------
	
	  
    
	   //handler for add Order
			@RequestMapping(value="/addOrders", method=RequestMethod.GET)
			public String addOrderGet(Model m) 
			{
				m.addAttribute("title", "Order-WaterPlantManagment");
				System.out.println("in add Order page");
				
				m.addAttribute("orders", new Orders());
				return "thanks";
			}
			
			//Order adding process post
			@RequestMapping(value="/adding_orders", method=RequestMethod.POST)
			public String productAddPost(@ModelAttribute("orders") Orders orders,
					HttpSession session) {
		
				
		
					System.out.println("DATA:" + orders);
					System.out.println("Added to database");
					
		
				return "thanks";
			}	
			/*
			 * @RequestMapping("/cart") public String showCart() {
			 * 
			 * System.out.println("In user Dashboard"); return "thanks"; }
			 */
			/*
			 * @RequestMapping("/thanks") public String thanks(Model m) {
			 * m.addAttribute("title", "About-WaterPlantManagment");
			 * System.out.println("in about page"); return "thanks"; }
			 */
			 
}
