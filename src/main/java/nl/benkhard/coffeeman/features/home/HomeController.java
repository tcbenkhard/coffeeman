package nl.benkhard.coffeeman.features.home;

import nl.benkhard.coffeeman.features.customers.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/fragment/customers/index");
        modelAndView.addObject("customers", customerRepository.findAll());

        return modelAndView;
    }
}
