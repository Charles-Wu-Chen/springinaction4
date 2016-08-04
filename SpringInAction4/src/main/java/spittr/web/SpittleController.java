package spittr.web;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.Spittle;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
	private static final String MAX_LONG_AS_STRING = "" + Long.MAX_VALUE;

	private SpittleRepository spittleRepository;

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}

	// @RequestMapping(method = RequestMethod.GET)
	// public String spittles(Model model) {
	// //default attribute name will be generated as spittleList
	// model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
	// return "spittles";
	// }

	@RequestMapping(method = RequestMethod.GET)
	public String spittles(Model model,
			@RequestParam(value = "max", required = false, defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", required = false, defaultValue = "20") int count) {

		model.addAttribute(spittleRepository.findSpittles(max, count));
		return "spittles";
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String showSpittle(@RequestParam("spittle_id") long spittleId, Model model) {
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";

	}

	@RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
	public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
		
		try {
			Spittle spittle = spittleRepository.findOne(spittleId);
			model.addAttribute(spittle);
			return "spittle";
		}
		catch (EmptyResultDataAccessException e){
			throw new SpittleNotFoundException();
		}
		
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveSpittle(SpittleForm form, Model model) throws DuplicateSpittleException {

		spittleRepository
				.save(new Spittle(null, form.getMessage(), new Date(), form.getLongitude(), form.getLatitude()));
		return "redirect:/spittles";

	}

}