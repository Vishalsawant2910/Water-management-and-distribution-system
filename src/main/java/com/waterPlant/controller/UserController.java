package com.waterPlant.controller;

import java.security.Principal;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.waterPlant.dao.OrderRepository;
import com.waterPlant.dao.UserRepository;
import com.waterPlant.exceptions.UserException;
import com.waterPlant.pojo.CartData;
import com.waterPlant.pojo.OrderDetails;
import com.waterPlant.pojo.Orders;
import com.waterPlant.pojo.Product;
import com.waterPlant.pojo.User;
import com.waterPlant.services.OrderDeatilsServiceImpl;
import com.waterPlant.services.OrderServiceImpl;
import com.waterPlant.services.ProductServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@SuppressWarnings("unused")
	@Autowired
	private ProductServiceImpl productService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private OrderServiceImpl orderService;
	@Autowired
	private OrderDeatilsServiceImpl orderDeatilsServiceImpl;
	
	@Autowired
	OrderRepository orderRepository;

	// @Autowired
	// private OrderServiceImpl orderService;

	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("username:" + userName);
		User user = userRepository.getUserByUserName(userName);
		System.out.println("user:" + user);
		model.addAttribute("user", user);
//		model.addAttribute("cartCount", CartData.cart.size());
		model.addAttribute("cartCount", CartData.cart.stream().mapToInt(Product::getQuantityInCart).sum());
	}

	@RequestMapping("/dashboard")
	public String dashboard(Model m, Principal principal) {
//		m.addAttribute("cartCount", CartData.cart.size());
		m.addAttribute("cartCount", CartData.cart.stream().mapToInt(Product::getQuantityInCart).sum());

		System.out.println("In user Dashboard");
		return "user/user_dashboard";
	}

	// your Profile
	// your profile handler
	@RequestMapping("/profile")
	public String yourProfile(Model model) {
//		model.addAttribute("cartCount", CartData.cart.size());
		model.addAttribute("cartCount", CartData.cart.stream().mapToInt(Product::getQuantityInCart).sum());
		model.addAttribute("title", "Profile Page");
		return "user/profile";
	}

	// change password
	@RequestMapping("/password")
	public String passwordChange(Model model) {
		model.addAttribute("title", "password Page");
		return "user/password";
	}

	@RequestMapping(value = "/change_password", method = RequestMethod.POST)
	public String changePassword(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, Principal principal, HttpSession session) {
		System.out.println("OLD PASSWORD " + oldPassword);
		System.out.println("NEW PASSWORD " + newPassword);

		String userName = principal.getName();
		User currentUser = this.userRepository.getUserByUserName(userName);
		System.out.println(currentUser.getPassword());
//		try {
			if (this.passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
				currentUser.setPassword(this.passwordEncoder.encode(newPassword));
				this.userRepository.save(currentUser);
				session.setAttribute("message", new UserException("Password Change Successfully", "alert-success"));

			}
//			session.setAttribute("message", new UserException("Password Change Successfully", "alert-success"));
//		} catch (Exception e) {
//			e.printStackTrace();
			else {
			session.setAttribute("message",
					new UserException("Please Enter correct old password" , "alert-danger"));
			return "redirect:/user/password";
			}
//		}
		return "redirect:/user/password";
	}

//Order Manage-------------------------------------------------------------------------------------------	     

	 //Order Delete
	 @RequestMapping(value="/deleteOrder/{id}", method=RequestMethod.GET) public
	  String deleteOrder(@PathVariable int id,HttpSession session) {
	  System.out.println("User Deleted Order");
	
//	  session.getId();
//	 int user_id= us.getUserId();
	 String rs="user/canclePage";
	 session.setAttribute("message", new UserException("Order canceled Successfully", "alert-success"));
	 
	 List<OrderDetails> ordD = orderDeatilsServiceImpl.getOrdersByOrder_Id(id);
	 for(int i=0;i<ordD.size();i++) {
		 orderDeatilsServiceImpl.deleteOrderDetailsById((int) ordD.get(i).getId());
	 }
	 
	 orderService.deleteOrderById(id); 
	 
	 
	 
	 return rs; }
	 

// cart Mapping-------------------------------------------------------------------------
//
////	 
//	@RequestMapping(value = "/cart", method = RequestMethod.GET)
//	public String cartGet(Model m) {
//		m.addAttribute("cartCount", CartData.cart.size());
//		m.addAttribute("amount", CartData.cart.stream().mapToDouble(Product::getPrice).sum());
//		m.addAttribute("cart", CartData.cart);
//		return "/user/cart";
//	}

//	@RequestMapping(value = "/myorders", method = RequestMethod.GET)
//	public String viewOrders( Model m) {
//		m.addAttribute("title", "Order-WaterPlantManagment");
//		System.out.println("in Order page");
//
//		m.addAttribute("orders", orderService.getAllOrders());
//		return "user/myorders";
//	}
	 
	 @GetMapping("/thanks")
		public String tothanks() {
		 CartData.cart.clear();
			return "user/thanks";
		}

	@RequestMapping(value = "/addOrders", method = RequestMethod.GET)
	public String addOrderGet(Model m) {
		m.addAttribute("title", "Order-WaterPlantManagment");
		System.out.println("in add Order page");

		m.addAttribute("orders", new Orders());
		return "user/addOrdersUser";
	}

	// Order adding process post
	@RequestMapping(value = "/adding_orders", method = RequestMethod.POST)
	public String productAddPost(@ModelAttribute("orders") Orders orders, HttpSession session,Principal principal) {
		
		String userName = principal.getName();
		User user = userRepository.getUserByUserName(userName);
//		
		
		Orders p = new Orders();
		p.setId(orders.getId());
		
//		p.setProduct_id(orders.getProduct_id());
//		p.setUser_id(orders.getUser_id());
		p.setUser_id(user.getUserId());
		System.out.println("userId id"+user.getUserId());
//		p.setQuantity(orders.getQuantity());
		p.setShippingAddress(orders.getShippingAddress());
		p.setShippingDate(orders.getShippingDate());
//		p.setTotal(orders.getTotal());
		p.setTotal(CartData.cart.stream().mapToDouble(Product::getAmountInCart).sum());
		System.out.println("total Amount = "+CartData.cart.stream().mapToDouble(Product::getAmountInCart).sum());
		orderService.addOrder(p);

		System.out.println("DATA:" + p);
		System.out.println("Added to database");
		
		for(int i=0;i<CartData.cart.size();i++) {
			OrderDetails orderDetails=new OrderDetails();
			orderDetails.setAmount(CartData.cart.get(i).getAmountInCart()*CartData.cart.get(i).getQuantityInCart());
			orderDetails.setQuantity(CartData.cart.get(i).getQuantityInCart());
			orderDetails.setProduct(CartData.cart.get(i));
			orderDetails.setOrder(orderService.getLastOrder());
			orderDetails.setProduct__id((int) CartData.cart.get(i).getId());
			orderDetails.setOrder_id(orderService.getLastOrder().getId());
			orderDeatilsServiceImpl.addOderDetails(orderDetails);
			}
		CartData.cart.clear();
		return "redirect:/user/thanks";
	}

	// handler for Order product
	@RequestMapping(value = "/orderPage", method = RequestMethod.GET)
	public String addProductGet(Model m, @PathVariable int id) {
		m.addAttribute("title", "Order-WaterPlantManagment");
		System.out.println("in add Order page");
		m.addAttribute("products", productService.getAllProduct());
		// m.addAttribute("orders", orderService.getOrderById(id));
		// m.addAttribute("product", new Product());
		return "user/orderPage";
	}
	 
	  @RequestMapping(value="/updateOrder/{id}", method=RequestMethod.GET) public
	  String updateOrder(@PathVariable int id, Model m) {
	  System.out.println("Admin Updated Order"); Orders
	  product=orderService.getOrderById(id).get();
	  
//	  Orders p=new Orders();
	  
//	  p.setId(product.getId()); p.setProduct_id(product.getProduct_id());
//	  p.setTotal(p.getTotal()); p.setShippingAddress(p.getShippingAddress());
//	  p.setShippingDate(p.getShippingDate()); p.setQuantity(p.getQuantity());
	  
	  m.addAttribute("orders", product);
	  
	  return "user/addOrdersUser"; }
	  
	  @RequestMapping(value="/UserOrderUpdated", method=RequestMethod.POST) 
	  public String OrderUpdated(@ModelAttribute("orders") Orders orders, Model m) {
	  System.out.println("Admin Updated Order"); 
	  
	  int id =orders.getId();
	  System.out.println(id);
	  Orders product=orderService.getOrderById(id).get();

	  product.setShippingAddress(orders.getShippingAddress());
	  product.setShippingDate(orders.getShippingDate());
//	  p.setQuantity(product.getQuantity());
	  
	  orderRepository.save(product);
//	 
	  System.out.println("order updated succesfully");
	  
	  
	  return "user/user_dashboard"; }
	

	/*
	 * //product adding process post
	 * 
	 * @RequestMapping(value="/adding_order", method=RequestMethod.POST) public
	 * String productAddPost(@ModelAttribute("Order") Order order, HttpSession
	 * session) {
	 * 
	 * Order o=new Order();
	 * 
	 * o.setOrderId(order.getOrderId()); o.setpId(order.getpId());
	 * o.setuId(order.getuId()); o.setQuantity(order.getQuantity());
	 * o.setAmount(order.getAmount());
	 * o.setShippingAddress(order.getShippingAddress());
	 * o.setShippingDate(order.getShippingDate());
	 * 
	 * orderService.addOrder(order);
	 * 
	 * 
	 * System.out.println("DATA:" + order); System.out.println("Added to database");
	 * 
	 * 
	 * return "admin/admin_dashboard"; }
	 */
}
