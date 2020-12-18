package nl.benkhard.coffeeman.features.machine;

import nl.benkhard.coffeeman.features.customer.Customer;

import javax.persistence.*;

@Entity
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String serialNumber;
    private String brand;
    private String type;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer owner;

}
