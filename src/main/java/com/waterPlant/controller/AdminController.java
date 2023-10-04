package com.waterPlant.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.waterPlant.dao.OrderRepository;
import com.waterPlant.exceptions.UserException;
import com.waterPlant.pojo.OrderDetails;
import com.waterPlant.pojo.Orders;
import com.waterPlant.pojo.Product;
import com.waterPlant.pojo.User;
import com.waterPlant.services.OrderDeatilsServiceImpl;
import com.waterPlant.services.OrderServiceImpl;
import com.waterPlant.services.ProductServiceImpl;
import com.waterPlant.services.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ProductServiceImpl productService;

	@Autowired
	private OrderServiceImpl orderService;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private OrderDeatilsServiceImpl orderDeatilsServiceImpl;

	@Autowired
	OrderRepository orderRepository;
//Admin Dashboard -----------------------------------------------------------------------------------
	@RequestMapping("/dashboard")
	public String dashboard() {

		System.out.println("In user Dashboard");
		return "admin/admin_dashboard";
	}

//Manage Product Page	------------------------------------------------------------------------------
	@RequestMapping(value = "/mproducts", method = RequestMethod.GET)
	public String viewProduct(Model m) {
		m.addAttribute("title", "Product-WaterPlantManagment");
		System.out.println("in Product page");

		m.addAttribute("products", productService.getAllProduct());
		return "admin/mproducts";
	}

	// handler for add product
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String addProductGet(Model m) {
		m.addAttribute("title", "Product-WaterPlantManagment");
		System.out.println("in add Product page");

		m.addAttribute("product", new Product());
		return "admin/addProduct";
	}
	@RequestMapping(value = "/addProductAdmin", method = RequestMethod.GET)
	public String addProductGetAdmin(Model m) {
		m.addAttribute("title", "Product-WaterPlantManagment");
		System.out.println("in add Product page");

		m.addAttribute("product", new Product());
		return "admin/addProductAdmin";
	}

	// product adding process post
	@RequestMapping(value = "/adding_product", method = RequestMethod.POST)
	public String productAddPost(@ModelAttribute("product") Product product, HttpSession session) {

		Product p = new Product();
		p.setId(product.getId());
		p.setProductName(product.getProductName());
		p.setQtyInLtr(product.getQtyInLtr());
		p.setPrice(product.getPrice());
		p.setDescription(product.getDescription());

		productService.addProduct(product);

		System.out.println("DATA:" + product);
		System.out.println("Added to database");
		session.setAttribute("success",new UserException("Your product saved successfully","success"));

		return "admin/successproduct";
	}

	// update product get
				@RequestMapping(value = "/updateProduct/{id}", method = RequestMethod.GET)
			public String updateProduct(@PathVariable long id, Model m) {
				System.out.println("Admin Updated product");
				
				Product product = productService.getProductById(id).get();

				Product p = new Product();

				p.setId(product.getId());
				p.setProductName(product.getProductName());
				p.setQtyInLtr(product.getQtyInLtr());		
				p.setPrice(product.getPrice());
				p.setDescription(product.getDescription());

				m.addAttribute("product", p);

				return "/admin/addProduct";
			}
	// Product Delete
	@RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable long id,HttpSession session) {
		System.out.println("Admin Deleted product");

		productService.deleteProductById(id);
		session.setAttribute("success",new UserException("Delete product successfully","success"));
		return "admin/deleteproduct";
	}

	

//Order Management---------------------------------------------------------------------------
	 @RequestMapping(value="/myorders", method=RequestMethod.GET) public String
	  viewOrders(Model m) { m.addAttribute("title", "Order-WaterPlantManagment");
	 System.out.println("in Order page");
	  m.addAttribute("orders", orderService.getAllOrders()); 
	  return "admin/myorders"; }
	  //handler for add Order
	  @RequestMapping(value="/addOrders", method=RequestMethod.GET) 
	  public String addOrderGet(Model m) { m.addAttribute("title", "Order-WaterPlantManagment");
	  System.out.println("in add Order page");
	  
	  m.addAttribute("orders", new Orders()); 
	  return "admin/addOrders"; }
	  
	  @RequestMapping(value="/addOrdersAdmin", method=RequestMethod.GET) 
	  public String addOrderAdminGet(Model m) { m.addAttribute("title", "Order-WaterPlantManagment");
	  System.out.println("in add Order page");
	  
	  m.addAttribute("orders", new Orders()); 
	  return "admin/addOrdersAdmin"; 
	  }
	  
	 //Order adding process post
	 @RequestMapping(value="/adding_orders", method=RequestMethod.POST) public
	  String productAddPost(@ModelAttribute("orders") Orders orders,  HttpSession session) {
	  Orders p=new Orders(); 
	  p.setId(orders.getId());
//	 p.setProduct_id(orders.getProduct_id()); 
	 p.setUser_id(orders.getUser_id());
//	 p.setQuantity(orders.getQuantity());
	 p.setShippingAddress(orders.getShippingAddress());
	 p.setShippingDate(orders.getShippingDate());
	 p.setTotal(orders.getTotal());
	 
	 orderService.addOrder(orders);
	 
	 
	  
	  System.out.println("DATA:" + orders);
	  System.out.println("Added to database");
	 session.setAttribute("success",new UserException("Your order saved successfully","success"));
	  
	  return "admin/success"; }
	 
	  //Order Delete
	  
	  @RequestMapping(value="/deleteOrder/{id}", method=RequestMethod.GET) public
	  String deleteOrder(@PathVariable int id,HttpSession session) {
	  System.out.println("Admin Deleted Order");
	  
	  List<OrderDetails> ordD = orderDeatilsServiceImpl.getOrdersByOrder_Id(id);
		 for(int i=0;i<ordD.size();i++) {
			 orderDeatilsServiceImpl.deleteOrderDetailsById((int) ordD.get(i).getId());
		 }
	  orderService.deleteOrderById(id); 
	  session.setAttribute("success",new UserException("Your order Deleted successfully","success"));
	  return "admin/deleteorder";
	  }
	  @RequestMapping(value="/updateOrder/{id}", method=RequestMethod.GET) 
	  public String updateOrder(@PathVariable int id, Model m) {
	  System.out.println("Admin Updated Order"); 
	  
	  Orders product=orderService.getOrderById(id).get();
	  
//	  Orders p=new Orders ();
	  
//	  p.setId(product.getId()); 
//	  p.setProduct_id(product.getProduct_id());
//	  p.setTotal(product.getTotal()); 
//	  product.setShippingAddress(product.getShippingAddress());
//	  product.setShippingDate(product.getShippingDate());
//	  p.setQuantity(product.getQuantity());
	  
	  m.addAttribute("orders", product);
	  
	  
	  return "admin/addOrders"; }
	  
	
	 
	  @RequestMapping(value="/OrderUpdated", method=RequestMethod.POST) 
//	  @PutMapping("/OrderUpdated")
	  public String OrderUpdated(@ModelAttribute("orders") Orders orders, Model m) {
	  System.out.println("Admin Updated Order"); 
	  
	  int id =orders.getId();
	  System.out.println(id);
//	  Optional<Orders> optionalOrder = orderRepository.findById(id);
	  Orders product=orderService.getOrderById(id).get();
//	  Orders existingOrder=optionalOrder.get();
//	  
//	  existingOrder.setShippingAddress(orders.getShippingAddress());
//	  existingOrder.setShippingDate(orders.getShippingDate());
	  
//	  Orders p=new Orders ();
	  
//	  p.setId(product.getId()); 
//	  p.setProduct_id(product.getProduct_id());
//	  p.setTotal(product.getTotal()); 
	  product.setShippingAddress(orders.getShippingAddress());
	  product.setShippingDate(orders.getShippingDate());
//	  p.setQuantity(product.getQuantity());
	  
	  orderRepository.save(product);
//	  m.addAttribute("orders", product);
	  System.out.println("order updated succesfully");
	  
	  
	  return "admin/myorders"; }
	
//User Management---------------------------------------------------------------------------
	// user Manage page
	@RequestMapping(value = "/manageUser", method = RequestMethod.GET)
	public String viewUser(Model m) {
		m.addAttribute("title", "adminUser-WaterPlantManagment");
		System.out.println("in User Management page");

		m.addAttribute("users", userService.getAllUser());
		return "admin/manageUser";
	}

	// User Delete
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable int id,HttpSession session) {
		System.out.println("Admin Deleted User");

		userService.deleteUserById(id);
		session.setAttribute("success",new UserException("User Deleted successfully","success"));
		return "admin/deleteuser";
	}

	 //product adding process post
	 
	  @RequestMapping(value="/adding_user", method=RequestMethod.POST) public
	  String userAddPost(@ModelAttribute("user") User user, HttpSession session) {
	  User p=new User(); 
	  p.setUserName(user.getUserName());
	  p.setEmail(user.getEmail()); 
	  p.setMobile(user.getMobile());
	  p.setAddress(user.getAddress()); 
	  p.setPassword(user.getPassword());
	  p.setEnable(true); 
	  p.setRole("ROLE_USER");
	  userService.addUser(user);
	  System.out.println("DATA:" + user); System.out.println("Added to database");
	  session.setAttribute("user",new UserException("Your User saved successfully","user"));
	return "admin/admin_dashboard"; }
	
	// update User get
	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.GET)
	public String updateUser(@PathVariable int id, Model m) {
		System.out.println("Admin Updated User");
		User user = userService.getUserById(id).get();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User p = new User();

		p.setUserName(user.getUserName());
		p.setEmail(user.getEmail());
		p.setMobile(user.getMobile());
		p.setAddress(user.getAddress());
		p.setPassword(user.getPassword());
		p.setEnable(true);
		p.setRole("ROLE_USER");
		
		m.addAttribute("user", p);
		
		return "/signup";
	}
	

}
