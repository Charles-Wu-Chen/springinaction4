package com.fabricgroup.spring4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fabricgroup.spring4.component.IPersonService;
@Controller
@RequestMapping("/page")
public class PersonController2 {
	@Autowired
	private IPersonService personService;
	@RequestMapping("/login")
        public String hello(@RequestParam(value="userId", required=false) String userId,
    		@RequestParam(value="location", required=false) String location,
    		Model model) {
		
	        model.addAttribute("msg", "Hello "+personService.getPersonName() );
	        model.addAttribute("userId", userId);
	        model.addAttribute("location", location);
                return "result";
	}
} 