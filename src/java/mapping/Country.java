package mapping;
// Generated Jul 18, 2023 10:03:05 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Country generated by hbm2java
 */
public class Country  implements java.io.Serializable {


     private int id;
     private String name;
     private Set users = new HashSet(0);

    public Country() {
    }

	
    public Country(int id) {
        this.id = id;
    }
    public Country(int id, String name, Set users) {
       this.id = id;
       this.name = name;
       this.users = users;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set getUsers() {
        return this.users;
    }
    
    public void setUsers(Set users) {
        this.users = users;
    }




}

