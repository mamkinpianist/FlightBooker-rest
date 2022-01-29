package sk.upjs.paz1c.project.FlightBookerrest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.upjs.paz1c.project.storage.*;

import java.util.List;

@RestController
@RequestMapping("flights")
public class FlightController {
    private FlightDao flightDao = DaoFactory.INSTANCE.getFlightDao();
    private CustomerDao customerDao = DaoFactory.INSTANCE.getCustomerDao();

    @GetMapping
    public List<Flight> getAll() {
        return flightDao.getAll();
    }

    @PostMapping("/getByAirport")
    public List<Flight> getByAirport(@RequestBody Airport airport) {
        return flightDao.getByAirport(airport);
    }

    @PostMapping
    Flight save(@RequestBody Flight flight) throws EntityNotFoundException {
        return flightDao.save(flight);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id)
    {
        boolean delete = flightDao.delete(id);
        if(delete)
            return new ResponseEntity<Void>(HttpStatus.OK);
        else
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/isFull")
    public boolean isFull(@RequestBody Flight flight)
    {
        return flightDao.isFull(flight);
    }
    @GetMapping("/fromAtoB")
    List<Flight> fromAtoB(@RequestParam String from, @RequestParam String where)
    {
        return flightDao.fromAtoB(from,where);
    }
    @PostMapping("/insertCustomers/{flightId}")
   public void insertCustomers(@RequestBody List<Customer> customers, @PathVariable long flightId)
    {
       flightDao.insertCustomers(customers,flightId);
       new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/isCustomerInFlight/{customer}")
    public boolean isCustomerInFlight(@PathVariable long customer,@RequestBody Flight flight)
    {
        Customer customer1 = customerDao.getById(customer);
        return flightDao.isCustomerInFlight(customer1,flight);
    }
}
