package ecomarkets.domain.core.product;

import com.google.errorprone.annotations.Immutable;

import ecomarkets.domain.core.Tenant;
import ecomarkets.domain.register.Address;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
@Immutable
public class ProductStock extends PanacheEntity{

    @ManyToOne
    private Tenant tenant;

    @ManyToOne
    private Product product;

    private Double amount;

    @ManyToOne
    private Address address;
    
    public ProductStock(){

    }

    public ProductStock(Tenant tenant,
    Product product,
    Double amount,
    Address address){
        this.tenant = tenant;
        this.product = product;
        this.address = address;
    }

    public Tenant tenant(){
        return this.tenant;
    }
    
    public Product product(){
        return this.product;
    }

    public Double amount(){
        return this.amount;
    }
    
    public Address address(){
        return this.address;
    }

    public void defineTenant(Tenant tenant){
        if(this.id != null){
            throw new IllegalArgumentException("Stock already created!");
        }
        this.tenant = tenant;
    }
    
}
