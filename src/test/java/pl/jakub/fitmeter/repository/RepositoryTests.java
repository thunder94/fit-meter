package pl.jakub.fitmeter.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.jakub.fitmeter.model.Fitness;
import pl.jakub.fitmeter.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RepositoryTests {

    private static final String NAME = "Jakub";
    private static final String USERNAME = "thunder94";
    private static final String EMAIL = "test@test.pl";
    private static final String PASSWORD = "password123";
    
	private static final BigDecimal KILOGRAMS = BigDecimal.valueOf(75);
	private static final BigDecimal CALORIES = BigDecimal.valueOf(3000);
    private static final LocalDate DATE = LocalDate.now();
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private FitnessRepository fitnessRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
    	
    	//--------------------USER TEST ------------------------------------------
    	
        //given
        User user = new User();
        user.setName(NAME);
        user.setUsername(USERNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);

        //when
        userRepository.save(user);

        //then
        Assert.assertNotNull(user.getId());
        User savedUser = userRepository.findById(user.getId()).orElse(null);
        Assert.assertEquals((Long) 1L, savedUser.getId());
        Assert.assertEquals(USERNAME, savedUser.getUsername());
        Assert.assertEquals(EMAIL, savedUser.getEmail());
        Assert.assertEquals(PASSWORD, savedUser.getPassword());
        
        //--------------------FITNESS TEST ------------------------------------------
        
        //given
        Fitness fitness = new Fitness();
        fitness.setKilograms(KILOGRAMS);
        fitness.setCalories(CALORIES);
        fitness.setDate(DATE);
        fitness.setUser(savedUser);
        
        //when
        fitnessRepository.save(fitness);
        
        //then
        Assert.assertNotNull(fitness.getId());
        Fitness savedFitness = fitnessRepository.findById(fitness.getId()).orElse(null);
        Assert.assertEquals((Long) 1L, savedFitness.getId());
        Assert.assertEquals(KILOGRAMS.compareTo(savedFitness.getKilograms()), 0);
        Assert.assertEquals(CALORIES.compareTo(savedFitness.getCalories()), 0);
        Assert.assertEquals(DATE, savedFitness.getDate());
        Assert.assertEquals(savedUser.getId(), savedFitness.getUser().getId());
       
    }
}