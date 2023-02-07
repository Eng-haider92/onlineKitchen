package studentkitchen.studentkitchen.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;



@Entity
@Table(name="user")
public class User {

    private Long id;
    private String username;
    private String email;
    private String password;
    private int active;
    private Set<Role> roles;
    private Set<Recipe> recipe;
    private Profile profile;
    private Set<Rating> rating;

    public User(){
        
    }

    public User(Long id, String username, String email,String password, int active){
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = active;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="user_id")
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Column(name = "username")
    @NotEmpty(message = "Please provide your username")
    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }


    @Column(name = "email")
    @Email(message = "please Enter valid name")
    @NotEmpty(message = "Please provide your Email Address")
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Column(name = "password")
    @Length(min = 5 , message = "*Your password must have at least 5 characters")
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Column(name = "active")
    public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

    @ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

    @OneToOne(mappedBy = "user")
    public Profile getProfile(){
        return profile;
    }

    public void setProfile(Profile p){
        this.profile = p;
    }

    @OneToMany(mappedBy = "user")
    public Set<Recipe> getRecipe(){
         return recipe;
    }
    
    public void setRecipe(Set<Recipe> r){
        this.recipe = r;
    }

    @OneToMany(mappedBy = "user")
    public Set<Rating> getRating(){
        return rating;
    }

    public void setRating(Set<Rating> r){
        this.rating = r;
    }

}
