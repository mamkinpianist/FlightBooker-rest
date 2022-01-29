package sk.upjs.paz1c.project.FlightBookerrest;

import org.springframework.web.bind.annotation.*;
import sk.upjs.paz1c.project.storage.Customer;
import sk.upjs.paz1c.project.storage.CustomerDao;
import sk.upjs.paz1c.project.storage.DaoFactory;
import sk.upjs.paz1c.project.storage.EntityNotFoundException;

@RestController
@CrossOrigin
@RequestMapping("/customers")
public class CustomerController {
    private CustomerDao customerDao = DaoFactory.INSTANCE.getCustomerDao();

    @GetMapping()
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @PostMapping()
    public Customer save(@RequestBody Customer customer) throws EntityNotFoundException, NullPointerException {
        return customerDao.save(customer);
    }
    @GetMapping("getCustomer/{id}")
    public Customer getById(@PathVariable("id") long id)
    {
        return customerDao.getById(id);
    }

    @GetMapping("getByFlighId/{id}")
    public List<Customer> getByFlightId(@PathVariable("id") long flight_id)
    {
       return customerDao.getByFlightId(flight_id);
    }

    @DeleteMapping("delete/{idCustomer}")
    public Customer delete(@PathVariable("idCustomer") long idCustomer)
    {
        return customerDao.delete(idCustomer);
    }

}
