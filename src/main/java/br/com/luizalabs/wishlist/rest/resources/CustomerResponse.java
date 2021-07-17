package br.com.luizalabs.wishlist.rest.resources;

import br.com.luizalabs.wishlist.domain.model.Customer;

public class CustomerResponse {
    private String customerId;
    private String cpf;
    private String firstName;
    private String lastName;

    public CustomerResponse() {}

    public CustomerResponse(Customer customer ){
        this.customerId = customer.getCustomerId();
        this.cpf = customer.getCpf();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();

    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
}
