package by.bsuir.group172301.matskevich.tour.entity;

import java.util.Objects;

/**
 * User
 */
public class User extends Entity {

    /**
     * username
     */
    private String username;

    /**
     * hashed password
     */
    private String password;

    /**
     * role name
     */
   private Role role;
	
	private String email;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String fullAddress;    
    

    /**
     * Default constructor
     */
    public User(){

    }


    /**
     * get Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * set Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * get Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * set Username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * get Role
     */
    public Role getRole() {
        return role;
    }

    /**
     * set Role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

   
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + Objects.hashCode(this.role);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.firstName);
        hash = 97 * hash + Objects.hashCode(this.lastName);
        hash = 97 * hash + Objects.hashCode(this.dateOfBirth);
        hash = 97 * hash + Objects.hashCode(this.fullAddress);
        return hash;
    }



    @Override
    public String toString(){
        return String.format("User[id: %d, username: %s, role: %s]", getId(), getUsername(), getRole().getRolename());
    }
}
