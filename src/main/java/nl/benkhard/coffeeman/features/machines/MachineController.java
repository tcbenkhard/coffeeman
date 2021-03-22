package nl.benkhard.coffeeman.features.machines;

import nl.benkhard.coffeeman.features.customers.Customer;
import nl.benkhard.coffeeman.features.customers.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/machines")
public class MachineController {

    @Autowired
    MachineRepository machineRepository;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("fragment/machines/index");
        List<Machine> machines = machineRepository.findAll();
        mav.addObject("machines", machines);

        return mav;
    }

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("customerId") Long customerId) throws Exception {
        ModelAndView mav = new ModelAndView("fragment/machines/create");
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new Exception("Customer does not exist"));
        mav.addObject("customer", customer);

        return mav;
    }

    @PostMapping
    public String create(@ModelAttribute("machine") Machine machine) {
        machineRepository.save(machine);
        return "redirect:/machines";
    }

    @PostMapping("/search")
    public RedirectView query(@RequestParam String query, RedirectAttributes attributes) {
        List<Machine> machines = machineRepository.query(query);
        RedirectView redirect = new RedirectView("/machines/results", true);
        attributes.addFlashAttribute("results", machines);
        return redirect;
    }

    @GetMapping("/results")
    public ModelAndView results(@ModelAttribute("results") List<Machine> results) {
        ModelAndView mav = new ModelAndView("fragment/machines/index");
        mav.addObject("machines", results);

        return mav;
    }

}
