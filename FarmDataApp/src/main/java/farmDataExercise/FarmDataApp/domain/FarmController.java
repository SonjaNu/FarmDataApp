package farmDataExercise.FarmDataApp.domain;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/farmlist")
public class FarmController {

	@Autowired
	private FarmRepository farmRepository;

	// Filter by month, year, location, sensor type
	@GetMapping("")
	public String listByColumns(Model model, String month, String sensor, String loc, String year) {
		try {
			if (month != null && sensor != null && loc != null && year != null) {
				model.addAttribute("farms", farmRepository.findByMonth(month, sensor, loc, year));

			} else {
				model.addAttribute("farms", farmRepository.findAll());

			}

			return "farmlist";

		} catch (Exception e) {

			return "farmlist";

		}
	}

	// Filter by keyword
	@GetMapping("/word")
	public String listValues(Model model, String keyword) {
		if (keyword != null) {
			model.addAttribute("farms", farmRepository.findByKeyword(keyword));
		} else {
			model.addAttribute("farms", farmRepository.findAll());
		}
		return "farmlist";
	}

	// Filter by keyword by min and max values of the sensor type
	@GetMapping("/between")
	public String listBetween(Model model, String sensor, Double min, Double max) {
		if (sensor != null && min != null && max != null) {
			model.addAttribute("farms", farmRepository.findBetween(sensor, min, max));
		} else {
			model.addAttribute("farms", farmRepository.findAll());
		}
		return "farmlist";
	}

	// return average of the sensor type by month, year and location
	@GetMapping("/average")
	public String listAverages(Model model, String key1, String key2, String key3, String key4) {

		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		try {
			if (key1 != null && key2 != null && key3 != null && key4 != null) {
				model.addAttribute("average", df.format(farmRepository.AVG(key1, key2, key3, key4)));
				model.addAttribute("month", key1);
				model.addAttribute("year", key2);
				model.addAttribute("location", key3);
				model.addAttribute("type", key4);
				return "average";
			} else {
				model.addAttribute("farms", farmRepository.findAll());
			}
			return "farmlist";

		} catch (Exception e) {

			return "exception";
		}

	}

	// Find minimum value of the sensor type by month, year and location
	@GetMapping("/min")
	public String listMin(Model model, String key5, String key6, String key7, String key8) {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		try {
			if (key5 != null && key6 != null && key7 != null && key8 != null) {
				model.addAttribute("min", df.format(farmRepository.findMin(key5, key6, key7, key8)));
				model.addAttribute("month", key5);
				model.addAttribute("year", key6);
				model.addAttribute("location", key7);
				model.addAttribute("type", key8);
				return "min";
			} else {
				model.addAttribute("farms", farmRepository.findAll());
			}
			return "farmlist";

		} catch (Exception e) {

			return "exception";
		}

	}

	// Find maximum value of the sensor type by month, year and location
	@GetMapping("/max")
	public String listMax(Model model, String key1, String key2, String key3, String key4) {

		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		try {
			if (key1 != null && key2 != null && key3 != null && key4 != null) {
				model.addAttribute("max", df.format(farmRepository.findMax(key1, key2, key3, key4)));
				model.addAttribute("month", key1);
				model.addAttribute("year", key2);
				model.addAttribute("location", key3);
				model.addAttribute("type", key4);
				return "max";
			} else {
				model.addAttribute("farms", farmRepository.findAll());
			}
			return "farmlist";
		} catch (Exception e) {

			return "exception";
		}
	}

	// add and save new farm with parameters (id), location, datetime, sensorType,
	// value
	@PostMapping("/save")
	public String saveFarm(@ModelAttribute Farm farm) {
		// save new farm with its info
		farm.setLocation(farm.getLocation());
		farm.setDatetime(farm.getDatetime());
		farm.setSensorType(farm.getSensorType());
		farm.setValue(farm.getValue());
		farmRepository.save(farm);

		return "redirect:/farmlist";
	}

	@GetMapping("/add")
	public String addFarm(Model model) {
		model.addAttribute("farm", new Farm());

		return "addFarm";
	}

}
