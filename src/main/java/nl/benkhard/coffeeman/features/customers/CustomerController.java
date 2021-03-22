package nl.benkhard.coffeeman.features.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("fragment/customers/index");
        List<Customer> customers = customerRepository.findAll();
        mav.addObject("customers", customers);

        return mav;
    }

    @GetMapping("/create")
    public String create() {
        return "fragment/customers/create";
    }

    @PostMapping
    public String create(@ModelAttribute("customer") Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{customerId}")
    public ModelAndView view(@PathVariable("customerId") Long customerId) throws ChangeSetPersister.NotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        ModelAndView mav = new ModelAndView("fragment/customers/view");
        mav.addObject("customer", customer);

        return mav;
    }
}
