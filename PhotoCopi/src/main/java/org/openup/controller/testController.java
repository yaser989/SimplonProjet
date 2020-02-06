package org.openup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class testController {

	
	@GetMapping("/index")
	public String vu() {
		return "index";
	}
	@GetMapping("/sume")
	public String vue() {
		return "sume";
	}
	
	@PostMapping("/index")
	public String fin(Model model, @RequestParam(name = "number", required = false)double number) {
		
		double p = 0.10;
		double pp = 0.09;
		double ppp = 0.08;
		double sum;

			if(number<=10) {
		    sum = number * p;
		    System.out.println(sum);
			model.addAttribute("sum",sum);
			return "sume";

			}
			
			else if (number > 10 && number <=30 ) {
				 sum = number  * pp;
					model.addAttribute("sum",sum);
					System.out.println(sum);
					return "sume";
			}
			else if (number >=31) {
				sum = number * ppp;
				model.addAttribute("sum",sum);
				System.out.println(sum);
				return "sume";
			}
		
		return "redirect:/index";
	}
}
