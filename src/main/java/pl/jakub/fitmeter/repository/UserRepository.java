package pl.jakub.fitmeter.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.jakub.fitmeter.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
     User findByEmail(String email);
     User findByUsername(String username);
}