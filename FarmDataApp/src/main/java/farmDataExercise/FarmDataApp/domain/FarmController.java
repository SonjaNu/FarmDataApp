package farmDataExercise.FarmDataApp.domain;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@CrossOrigin
@Controller
public class FarmController {
	
	@Autowired
	private FarmRepository farmRepository; 
	
	// Show all farms
    @RequestMapping(value="/farmlist", method = RequestMethod.GET)
    public String farmList(Model model) {	
        model.addAttribute("farms", farmRepository.findAll());  
        return "farmlist";  
    }


}
