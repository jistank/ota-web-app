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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();  // createdAt field automatically
    }
    
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", createdAt=" + createdAt + ", bookings="
				+ bookings + "]";
	}
}
