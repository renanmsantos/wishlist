package br.com.luizalabs.wishlist.domain.model;

import br.com.luizalabs.wishlist.rest.resources.CustomerRequest;
import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection = "customer" )
public class Customer {

    @Id
    private String customerId;

    @NonNull
    private String cpf;

    private String firstName;
    private String lastName;

    public Customer() {}

    public Customer( CustomerRequest request ) {
        this.cpf = request.getCpf();
        if(request.getFirstName() != null)
            this.firstName = request.getFirstName();
        if(request.getLastName() != null)
            this.lastName = request.getLastName();
    }

    public Customer(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    @NonNull
    public String getCpf() {
        return cpf;
    }

    public void setCpf(@NonNull String cpf) {
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
