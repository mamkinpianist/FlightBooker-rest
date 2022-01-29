package sk.upjs.paz1c.project.FlightBookerrest;

import org.springframework.web.bind.annotation.*;
import sk.upjs.paz1c.project.storage.Airport;
import sk.upjs.paz1c.project.storage.AirportDao;
import sk.upjs.paz1c.project.storage.DaoFactory;
import sk.upjs.paz1c.project.storage.EntityNotFoundException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("airports")
public class AirportController {
    private AirportDao airportDao = DaoFactory.INSTANCE.getAirportDao();

    @GetMapping("/getByCity/{city}")
    public Set<String> getByCity(@PathVariable(value = "city") String city)
    {
        return airportDao.getByCity(city);
    }

    @GetMapping("/allcountries")
    public Set<String> getAllCountries()
    {
        return airportDao.getAllCountries();
    }
    @RequestMapping(value = "/getCityByCountry",method = RequestMethod.GET)
    public Set<String> getCityByCountry(@RequestParam(value="country") String country)
    {
        return airportDao.getCityByCountry(country);
    }
    @GetMapping()
    public List<Airport> getAll(){
        return airportDao.getAll();
    }
    @GetMapping("getAirport/{id}")
    public Airport getById(@PathVariable("id") long id) throws EntityNotFoundException
    {
        return airportDao.getById(id);
    }
    @PostMapping
     public Airport save(@RequestBody Airport airport) throws EntityNotFoundException, NullPointerException
    {
        return airportDao.save(airport);
    }

    @DeleteMapping("{id}")
    Airport delete(@PathVariable("id") long idAirport) throws EntityNotFoundException, NullPointerException
    {
        return airportDao.delete(idAirport);
    }
}
