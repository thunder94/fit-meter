package pl.jakub.fitmeter.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "fitness")
public class Fitness {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User user;
	
	private BigDecimal kilograms;
	private BigDecimal calories;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public BigDecimal getKilograms() {
		return kilograms;
	}

	public void setKilograms(BigDecimal kilograms) {
		this.kilograms = kilograms;
	}

	public BigDecimal getCalories() {
		return calories;
	}

	public void setCalories(BigDecimal calories) {
		this.calories = calories;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
