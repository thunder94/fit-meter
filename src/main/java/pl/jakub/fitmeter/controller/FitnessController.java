package pl.jakub.fitmeter.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.jakub.fitmeter.model.Fitness;
import pl.jakub.fitmeter.service.FitnessService;

@Controller
@RequestMapping("/fitness")
public class FitnessController {

	private FitnessService fitnessService;
	
	@Autowired
	public FitnessController(FitnessService fitnessService) {
		this.fitnessService = fitnessService;
	}
	
	@GetMapping
	public ModelAndView showFitness(ModelAndView modelAndView, Fitness fitness) {
		modelAndView.addObject("fitness", fitness);
		modelAndView.addObject("fitnesses", fitnessService.findByUser());
		modelAndView.setViewName("fitness");
		return modelAndView;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editFitness(ModelAndView modelAndView, @PathVariable Long id) {
		Fitness fitness = fitnessService.findById(id);
		modelAndView.addObject("fitness", fitness);
		modelAndView.addObject("edit", true);
		modelAndView.setViewName("fitness");
		return modelAndView;
	}
	
	@GetMapping("/delete/{id}")
	public String deleteFitness(@PathVariable Long id) {
		fitnessService.delete(id);
		return "redirect:/fitness";
	}
	
	@PostMapping
	public ModelAndView saveFitness(ModelAndView modelAndView, @Valid Fitness fitness, BindingResult bindingResult) {
		fitnessService.saveOrUpdateFitness(fitness);
		modelAndView.setViewName("redirect:/fitness");
		return modelAndView;
	}
	
}
