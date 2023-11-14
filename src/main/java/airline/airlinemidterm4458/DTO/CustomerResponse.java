package airline.airlinemidterm4458.DTO;

import airline.airlinemidterm4458.model.Customer;

public class CustomerResponse {


    private Long id;

    private String name;
    private String lastname;
    private String username;
    private String password;


    public CustomerResponse(Customer customer)
    {
        this.id = customer.getId();
        this.name = customer.getName();
        this.lastname = customer.getLastname();
        this.username = customer.getUsername();
        this.password = customer.getDecodedPassword();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
