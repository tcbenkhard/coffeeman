package nl.benkhard.coffeeman.features.customer;

import nl.benkhard.coffeeman.features.machine.Machine;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "owner")
    private List<Machine> machines;
}
