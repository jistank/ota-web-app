package com.ota.app.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true) // no two users can have same email (for login)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
    
    private String phoneNumber;
    
    @Column(nullable = false, updatable = false) // this field can never be changed after creation
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;  // default ROLE_CLIENT

    
    public User() {}
    
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();  // createdAt field automatically
    }
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public UserRole getRole() {
	    return role;
	}
	public void setRole(UserRole role) {
	    this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", createdAt=" + createdAt + ", bookings="
				+ bookings + "]";
	}
    
    
}
