package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Admin;
import com.example.demo.model.Product;
import com.example.demo.service.AdminService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.PurchaseService;
import com.example.demo.service.UserService;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private CategoryService catService;
	@Autowired
	private ProductService prodService;
	@Autowired
	private UserService userService;
	@Autowired
	private PurchaseService purService;

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping("/admin/category")
	public ModelAndView viewCategory(HttpServletRequest req) {
		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		if (username != null && username.equals("admin")) {
			ModelAndView mod = new ModelAndView("viewcate");
			mod.addObject("category", catService.getAllCategory());
			return mod;
		}
		ModelAndView mod = new ModelAndView("AdminLogin");
		mod.addObject("admin", new Admin());
		return mod;
	}

	@GetMapping("/admin/category/{name}")
	public ModelAndView viewProdByCategory(@PathVariable("name") String cateName, HttpServletRequest req) {
		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		if (username != null && username.equals("admin")) {
			ModelAndView mod = new ModelAndView("viewprodcate");
			mod.addObject("prods", prodService.getProdByCategoryName(cateName));
			return mod;
		}
		ModelAndView mod = new ModelAndView("AdminLogin");
		mod.addObject("admin", new Admin());
		return mod;
	}

	@PostMapping("/admin/login")
	public ModelAndView authenticate(@ModelAttribute Admin admin, HttpServletRequest req) {
		if (adminService.authenticateAdmin(admin.getUsername(), admin.getPassword())) {
			HttpSession session = req.getSession();
			session.setAttribute("username", admin.getUsername());
			ModelAndView model = new ModelAndView("AdminDashboard");
			return model;
		}
		ModelAndView model = new ModelAndView("AdminLogin");
		return model;
	}

	@GetMapping("/adminlogin")
	public ModelAndView adminlogin() {
		ModelAndView mod = new ModelAndView("AdminLogin");
		mod.addObject("admin", new Admin());
		return mod;
	}

	@GetMapping("/admin/products")
	public ModelAndView addProduct(HttpServletRequest req) {
		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		if (username != null && username.equals("admin")) {
			ModelAndView mod = new ModelAndView("product_add");
			mod.addObject("prod", new Product());
			mod.addObject("category", catService.getAllCategory());
			return mod;
		}
		ModelAndView mod = new ModelAndView("AdminLogin");
		mod.addObject("admin", new Admin());
		return mod;
	}

	@GetMapping("/admin/product/view")
	public ModelAndView viewProducts(HttpServletRequest req) {

		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		if (username != null && username.equals("admin")) {
			ModelAndView mod = new ModelAndView("viewprod");
			mod.addObject("prods", prodService.getAllProducts());

			return mod;
		}
		ModelAndView mod = new ModelAndView("AdminLogin");
		mod.addObject("admin", new Admin());
		return mod;
	}

	@PostMapping("/admin/product/add")
	public ModelAndView saveProdcut(@ModelAttribute Product prod, HttpServletRequest req, RedirectAttributes red) {
		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		String message = "";
		if (username != null && username.equals("admin")) {
			ModelAndView mod = new ModelAndView("redirect:/admin/products");
			prod.setDateAdded(new java.util.Date());
			if (prodService.addProduct(prod)) {
				message = "Product Saved Successfully";
			} else {
				message = "Product not saved";
			}
			red.addFlashAttribute("message", message);
			return mod;
		}
		ModelAndView mod = new ModelAndView("AdminLogin");
		mod.addObject("admin", new Admin());
		return mod;
	}

	@GetMapping("/admin/manageprod")
	public ModelAndView manageProducts(HttpServletRequest req) {
		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		if (username != null && username.equals("admin")) {
			ModelAndView mod = new ModelAndView("ManageProducts");
			return mod;
		}
		ModelAndView mod = new ModelAndView("AdminLogin");
		mod.addObject("admin", new Admin());
		return mod;

	}

	@GetMapping("/admin/products/delete")
	public ModelAndView deleteProducts(HttpServletRequest req) {
		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		if (username != null && username.equals("admin")) {
			ModelAndView mod = new ModelAndView("delprod");
			mod.addObject("prods", prodService.getAllProducts());
			return mod;
		}
		ModelAndView mod = new ModelAndView("AdminLogin");
		mod.addObject("admin", new Admin());
		return mod;
	}

	@PostMapping("/admin/product/del")
	public ModelAndView delProducts(HttpServletRequest req) {
		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		if (username != null && username.equals("admin")) {
			ModelAndView mod = new ModelAndView("delprod");
			prodService.deleteProductById(Integer.parseInt(req.getParameter("prodid")));
			mod.addObject("prods", prodService.getAllProducts());
			return mod;
		}
		ModelAndView mod = new ModelAndView("AdminLogin");
		mod.addObject("admin", new Admin());
		return mod;
	}

	@GetMapping("/admin/logout")
	public ModelAndView logout(HttpServletRequest req) {
		HttpSession ses = req.getSession(false);
		if (ses != null)
			ses.invalidate();

		ModelAndView model = new ModelAndView("redirect:/adminlogin");
		return model;
	}

	@GetMapping("/admin/userslist")
	public ModelAndView getUsersList(HttpServletRequest req) {
		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		if (username != null && username.equals("admin")) {
			ModelAndView mod = new ModelAndView("UsersList");
			ses.setAttribute("users", userService.getAllUsers());
			return mod;
		}
		ModelAndView mod = new ModelAndView("AdminLogin");
		mod.addObject("admin", new Admin());
		return mod;
	}

	@GetMapping("/admin/dashboard")
	public ModelAndView dashboard(HttpServletRequest req) {
		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		if (username != null && username.equals("admin")) {
			ModelAndView mod = new ModelAndView("AdminDashboard");
			return mod;
		}
		ModelAndView mod = new ModelAndView("AdminLogin");
		mod.addObject("admin", new Admin());
		return mod;
	}

	@GetMapping("/admin/purchasereport/date")
	public ModelAndView purchaseReportByDate(HttpServletRequest req) {
		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		if (username != null && username.equals("admin")) {
			ModelAndView mod = new ModelAndView("purchasereportbydate");
			return mod;
		}
		ModelAndView mod = new ModelAndView("AdminLogin");
		mod.addObject("admin", new Admin());
		return mod;
	}

	@PostMapping("/admin/purchasereport/date")
	public ModelAndView purchaseReportDate(HttpServletRequest req, RedirectAttributes red) throws ParseException {
		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");

		String message = "";
		if (username != null && username.equals("admin")) {

			ModelAndView mod = new ModelAndView("redirect:/admin/purchasereport/date");
			ses.setAttribute("purchase", purService.getOrderByDate((String) req.getParameter("purchasedate")));
			return mod;

		}
		ModelAndView mod = new ModelAndView("AdminLogin");
		mod.addObject("admin", new Admin());
		return mod;
	}

	@GetMapping("/admin/purchasereport/category")
	public ModelAndView purchaseReportByCategory(HttpServletRequest req) {
		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		if (username != null && username.equals("admin")) {
			ModelAndView mod = new ModelAndView("purchasereportbycate");
			ses.setAttribute("category", catService.getAllCategory());
			return mod;
		}
		ModelAndView mod = new ModelAndView("AdminLogin");
		mod.addObject("admin", new Admin());
		return mod;
	}

	@PostMapping("/admin/purchasereport/category")
	public ModelAndView purchaseReportCategory(HttpServletRequest req, RedirectAttributes red) throws ParseException {
		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
        System.out.println((String)req.getParameter("catename"));
		if (username != null && username.equals("admin")) {

			ModelAndView mod = new ModelAndView("redirect:/admin/purchasereport/category");
			ses.setAttribute("purchase", purService.getOrdersByCategorty((String) req.getParameter("catename")));
			return mod;

		}
		ModelAndView mod = new ModelAndView("AdminLogin");
		mod.addObject("admin", new Admin());
		return mod;
	}

}
