package pl.jakub.fitmeter.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import pl.jakub.fitmeter.model.Fitness;
import pl.jakub.fitmeter.model.User;
import pl.jakub.fitmeter.repository.FitnessRepository;

@Service
public class FitnessService {
	
	private FitnessRepository fitnessRepository;
	private UserService UserService;
	
	@Autowired
	public FitnessService(FitnessRepository fitnessRepository, UserService UserService) {
		this.fitnessRepository = fitnessRepository;
		this.UserService = UserService;
	}
	
	public void saveOrUpdateFitness(Fitness fitness) {
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = UserService.findById(loggedUser.getId());
		fitness.setUser(user);
		fitnessRepository.save(fitness);
	}
	
	public void delete(Long id) {
		fitnessRepository.deleteById(id);
	}
	
	public List<Fitness> findByUser() {
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = UserService.findById(loggedUser.getId());
		List<Fitness> fitnesses = new ArrayList<>();
		fitnessRepository.findByUser(user).forEach(fitnesses::add);
		return fitnesses;
	}
	
	public Fitness findById(Long id) {
		Fitness fitness =  fitnessRepository.findById(id).orElse(null);
		if(fitness != null) {
			fitness.setKilograms(fitness.getKilograms().setScale(1, BigDecimal.ROUND_FLOOR));
			fitness.setCalories(fitness.getCalories().setScale(0, BigDecimal.ROUND_FLOOR));
		}
		return fitness;
	}
	
}
