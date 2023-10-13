package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
	@GetMapping("/vie")
public ModelAndView handle() {
	ModelAndView mav = new ModelAndView("view");
	return mav;
}
}
