package studentkitchen.studentkitchen.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String profileImage;
    private User user;
    private Set<Recipe> recipe;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profile_id")
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String fName){
        this.firstName = fName;       
    }

    @Column(name = "last_name")
    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lName){
        this.lastName = lName;        
    }

    @Column(name = "profile_image")
    public String getProfileImage(){
        return profileImage;
    }

    public void setProfileImage(String imgPath){
        this.profileImage = imgPath;        
    }

    @Column(name = "phone_number")
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String pNumber){
        this.phoneNumber = pNumber;        
    }

    @Column(name = "address")
    public String getAddress(){
        return address;
    }

    public void setAddress(String add){
        this.address = add;        
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User getUser(){
        return user;
    }
    
    public void setUser(User u){
        this.user = u;
    }


    @ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "profile_recipe", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "recipe_id"))
	public Set<Recipe> getRecipe() {
		return recipe;
	}

	public void setRecipe(Set<Recipe> r) {
		this.recipe = r;
	}

    
}
