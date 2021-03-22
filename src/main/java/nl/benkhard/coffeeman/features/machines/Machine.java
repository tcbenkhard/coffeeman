package nl.benkhard.coffeeman.features.machines;

import lombok.Data;
import nl.benkhard.coffeeman.features.customers.Customer;

import javax.persistence.*;

@Entity
@Data
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String reference;
    private String serialNumber;
    private String brand;
    private String type;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer owner;

}
