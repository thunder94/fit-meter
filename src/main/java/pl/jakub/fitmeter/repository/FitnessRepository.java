package pl.jakub.fitmeter.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.jakub.fitmeter.model.Fitness;
import pl.jakub.fitmeter.model.User;

@Repository
public interface FitnessRepository extends CrudRepository<Fitness, Long>{
	List<Fitness> findByUser(User user);
}
