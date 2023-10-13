package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.CartForm;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.model.Purchase;
import com.example.demo.model.PurchaseItems;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.ProductService;
import com.example.demo.service.PurchaseService;
import com.example.demo.service.UserService;

@Controller
@Valid
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private ProductService prodService;

	@Autowired
	private PurchaseService purService;

	@GetMapping("/userlogin")
	public ModelAndView userLoginPage(HttpServletRequest req) {
		ModelAndView mod = new ModelAndView("UserLogin");
		mod.addObject("user", new User());
		return mod;
	}

	@PostMapping("/user/login")
	public ModelAndView userLogin(@NonNull @ModelAttribute User user, HttpServletRequest req) {
		HttpSession ses = req.getSession();

		if (userService.authenticateUser(user)) {
			ses.setAttribute("username", user.getUsername());

			List<Product> products = prodService.getAllProducts();
			CartForm cartForm = new CartForm();
			List<CartItem> cartItems = new ArrayList<CartItem>();
			for (Product pro : products) {
				CartItem cartItem = new CartItem();
				cartItem.setProductId(pro.getProductId());
				cartItem.setProductName(pro.getProductName());
				cartItem.setQty(0);
				cartItem.setPrice(pro.getPrice());
				cartItems.add(cartItem);
			}
			cartForm.setCartItems(cartItems);
			ModelAndView mod = new ModelAndView("UserDashboard");
			mod.addObject("cartform", cartForm);
			return mod;
		}
		ModelAndView mod = new ModelAndView("redirect:/userlogin");
		return mod;

	}

	@GetMapping("/user/cart")
	public ModelAndView userCartPage(HttpServletRequest req) {
		HttpSession ses = req.getSession();

		if (ses != null && userService.getAllUsers().stream()
				.filter(e -> e.getUsername().equals(ses.getAttribute("username"))).count() > 0) {

			List<Product> products = prodService.getAllProducts();
			CartForm cartForm = new CartForm();
			List<CartItem> cartItems = new ArrayList<CartItem>();
			for (Product pro : products) {
				CartItem cartItem = new CartItem();
				cartItem.setProductId(pro.getProductId());
				cartItem.setProductName(pro.getProductName());
				cartItem.setQty(0);
				cartItem.setPrice(pro.getPrice());
				cartItems.add(cartItem);
			}
			cartForm.setCartItems(cartItems);
			ModelAndView mod = new ModelAndView("UserDashboard");
			mod.addObject("cartform", cartForm);
			return mod;
		}
		ModelAndView mod = new ModelAndView("redirect:/userlogin");
		return mod;
	}

	@PostMapping("/user/cart")
	public ModelAndView userCart(@NonNull @ModelAttribute("cartform") CartForm cartForm, HttpServletRequest req) {

		HttpSession ses = req.getSession();

		if (ses != null && userService.getAllUsers().stream()
				.filter(e -> e.getUsername().equals(ses.getAttribute("username"))).count() > 0) {
			List<CartItem> cartItems = cartForm.getCartItems();
			Purchase purchase = new Purchase();
			Set<PurchaseItems> purchaseItems = new HashSet<PurchaseItems>();
			int total = 0;
			for (CartItem item : cartItems) {
				if (item.getQty() > 0) {
					PurchaseItems purItem = new PurchaseItems();
					purItem.setProductId(item.getProductId());
					purItem.setProductName(item.getProductName());
					purItem.setQty(item.getQty());
					purchaseItems.add(purItem);
					total += (item.getQty() * item.getPrice());
				}
			}
			purchase.setPurchaseDate(new java.util.Date());
			purchase.setTotalAmt(total);
			purchase.setUserId((String) ses.getAttribute("username"));
			purchase.setPurchaseItems(purchaseItems);

			ModelAndView mod = new ModelAndView("Payment");
			req.setAttribute("purchase", purchase);
			return mod;
		}
		ModelAndView mod = new ModelAndView("redirect:/userlogin");
		return mod;
	}

	@PostMapping("/user/payment")
	public ModelAndView makePayment(HttpServletRequest req) {

		HttpSession ses = req.getSession();

		if (ses != null && userService.getAllUsers().stream()
				.filter(e -> e.getUsername().equals(ses.getAttribute("username"))).count() > 0) {
			if (req.getParameter("cardnumber") != null && req.getParameter("holdername") != null) {

				purService.savePurchase((Purchase) ses.getAttribute("purchase"));
				ses.removeAttribute("purchase");
				ModelAndView mod = new ModelAndView("redirect:/user/orders");
				return mod;

			}
		}
		ModelAndView mod = new ModelAndView("redirect:/userlogin");
		return mod;
	}

	@GetMapping("/user/orders")
	public ModelAndView viewOrdersPage(HttpServletRequest req) {
		HttpSession ses = req.getSession();

		if (ses != null && userService.getAllUsers().stream()
				.filter(e -> e.getUsername().equals(ses.getAttribute("username"))).count() > 0) {

			ModelAndView mod = new ModelAndView("orders");
			ses.setAttribute("orders", purService.getOrdersByUserId((String) ses.getAttribute("username")));
			return mod;

		}
		ModelAndView mod = new ModelAndView("redirect:/userlogin");
		return mod;

	}

	@GetMapping("/user/logout")
	public ModelAndView logout(HttpServletRequest req) {

		HttpSession ses = req.getSession(false);
		if (ses != null) {
			ses.invalidate();
		}
		ModelAndView mod = new ModelAndView("redirect:/userlogin");
		return mod;
	}
	
	@GetMapping("/user/signup")
	public ModelAndView signup(HttpServletRequest req) {
	
		ModelAndView mod = new ModelAndView("UserSignup");
		mod.addObject("user", new User());
		return mod;
	}
	@PostMapping("/user/signup")
	public ModelAndView userSignup(@ModelAttribute User user, HttpServletRequest req,RedirectAttributes red) {
		if(user!=null) {
			if(userService.addUser(user)) {
				ModelAndView mod = new ModelAndView("redirect:/userlogin");
				red.addFlashAttribute("message","User created.Please login");
				return mod;
			}
		}
		ModelAndView mod = new ModelAndView("redirect:/user/signup");
		red.addFlashAttribute("message","User not created.Please enter the correct values");
		return mod;
	}
	
	@GetMapping("/")
	public ModelAndView homePage(HttpServletRequest req) {
		ModelAndView mod = new ModelAndView("index");
		return mod;
	}

}
