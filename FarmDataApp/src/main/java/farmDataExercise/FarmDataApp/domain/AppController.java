package farmDataExercise.FarmDataApp.domain;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class AppController {


	
	@RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
	public String main(Model model) {
		return "home";
	}
	

}