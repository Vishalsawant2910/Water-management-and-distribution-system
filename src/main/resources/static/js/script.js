console.log("script File")

function updateCartItemQuantity(sku, quantity)
{
	$.ajax ({ 
		url: '/cart/items', 
		type: "PUT", 
		dataType: "json",
		contentType: "application/json",
		data : '{ "product" :{ "sku":"'+ sku +'"},"quantity":"'+quantity+'"}',
		complete: function(responseData, status, xhttp){ 
			updateCartItemCount();        	
			location.href = '/cart' 
		}
	});
}

function removeItemFromCart(sku)
{
	$.ajax ({ 
		url: '/cart/items/'+sku, 
		type: "DELETE", 
		dataType: "json",
		contentType: "application/json",
		complete: function(responseData, status, xhttp){ 
			updateCartItemCount();
			location.href = '/cart' 
		}
	});
}
function add_to_cart(pid,pname,price){
	let cart =localStorage.getItem("cart");
	if (cart==null){
		let products=[];
		let product={product: pid, productName:pname, productQuantity:1,productPrice:price};
		products.push(product);
		localStorage.setItem("cart", JSON.stringify(products));
	}
}
/*Next we will implement the CartController handler methods as follows:

@Controller
public class CartController extends JCartSiteBaseController
{
	...
	...
	
	@RequestMapping(value="/cart/items", method=RequestMethod.PUT)
	@ResponseBody
	public void updateCartItem(@RequestBody LineItem item, 
	                            HttpServletRequest request, 
	                            HttpServletResponse response)
	{
		Cart cart = getOrCreateCart(request);
		if(item.getQuantity() <= 0){
			String sku = item.getProduct().getSku();
			cart.removeItem(sku);
		} else {
			cart.updateItemQuantity(item.getProduct(), item.getQuantity());
		}
	}
	
	@RequestMapping(value="/cart/items/{sku}", method=RequestMethod.DELETE)
	@ResponseBody
	public void removeCartItem(@PathVariable("sku") String sku, 
	                            HttpServletRequest request)
	{
		Cart cart = getOrCreateCart(request);
		cart.removeItem(sku);
	}*/

