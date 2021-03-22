package nl.benkhard.coffeeman.features.customers;

import lombok.Data;
import nl.benkhard.coffeeman.features.machines.Machine;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String streetName;

    private String houseNumber;

    private String postalCode;

    private String city;

    private String phoneNumber;

    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Machine> machines;
}
