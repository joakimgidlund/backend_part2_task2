package se.yrgo.vehiclelease.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.yrgo.vehiclelease.data.CustomerRepository;
import se.yrgo.vehiclelease.data.VehicleRepository;
import se.yrgo.vehiclelease.domain.Customer;
import se.yrgo.vehiclelease.domain.Vehicle;
import se.yrgo.vehiclelease.dto.CustomerDTO;
import se.yrgo.vehiclelease.dto.VehicleDTO;

@RestController
public class Controller {
    private CustomerRepository customerRepo;
    private VehicleRepository vehicleRepo;

    public Controller(CustomerRepository customerRepo, VehicleRepository vehicleRepo) {
        this.customerRepo = customerRepo;
        this.vehicleRepo = vehicleRepo;
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestParam String name, @RequestParam String phone) {
        Customer newCustomer = new Customer();
        newCustomer.setName(name);
        newCustomer.setPhoneNumber(phone);
        customerRepo.save(newCustomer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/vehicles")
    public ResponseEntity<Vehicle> createVehicle(@RequestParam String registrationNumber, @RequestParam String brand,
            @RequestParam String model, @RequestParam int productionYear) {
        Vehicle newVehicle = new Vehicle();
        newVehicle.setBrand(brand);
        newVehicle.setModel(model);
        newVehicle.setRegistrationNumber(registrationNumber);
        newVehicle.setProductionYear(productionYear);
        vehicleRepo.save(newVehicle);
        return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
    }

    @PostMapping("/vehicle-to-customer")
    public ResponseEntity<String> setCustomerToVehicle(@RequestParam Long customerId, @RequestParam Long vehicleId) {
        Customer c = customerRepo.findById(customerId).orElse(null);
        Vehicle v = vehicleRepo.findById(vehicleId).orElse(null);

        if (c != null && v != null) {
            v.setCustomer(c);
            vehicleRepo.save(v);
            return new ResponseEntity<>("Vehicle added to customer", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid customer or vehicle", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/customer-id")
    public ResponseEntity<Long> getCustomerIdByName(@RequestParam String name) {
        Customer customer = customerRepo.findByName(name);
        return new ResponseEntity<>(customer.getId(), HttpStatus.FOUND);
    }

    @GetMapping("/customers")
    public List<CustomerDTO> getCustomers() {
        List<Object[]> all = customerRepo.findAllUsersAndVehicles();
        Map<Long, CustomerDTO> customerMap = new HashMap<>();

        for (Object[] result : all) {
            Customer customer = (Customer) result[0];
            Vehicle vehicle = (Vehicle) result[1];

            CustomerDTO customerDTO = customerMap.getOrDefault(customer.getId(), new CustomerDTO());
            customerDTO.setId(customer.getId());
            customerDTO.setName(customer.getName());
            customerDTO.setPhoneNumber(customer.getPhoneNumber());

            if (vehicle != null) {
                VehicleDTO vehicleDTO = new VehicleDTO();
                vehicleDTO.setId(vehicle.getId());
                vehicleDTO.setBrand(vehicle.getBrand());
                vehicleDTO.setModel(vehicle.getModel());
                vehicleDTO.setRegistrationNumber(vehicle.getRegistrationNumber());
                vehicleDTO.setProductionYear(vehicle.getProductionYear());
                vehicleDTO.setCustomerId(vehicle.getCustomer() != null ? vehicle.getCustomer().getId() : null);

                customerDTO.getVehicles().add(vehicleDTO);
            }

            customerMap.put(customer.getId(), customerDTO);
        }
        return new ArrayList<>(customerMap.values());
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleDTO>> getVehicles() {
        List<VehicleDTO> dtos = vehicleRepo.findAll().stream()
                .map(v -> new VehicleDTO(
                        v.getId(),
                        v.getRegistrationNumber(),
                        v.getBrand(),
                        v.getModel(),
                        v.getProductionYear(),
                        v.getCustomer() != null ? v.getCustomer().getId() : null))
                .toList();
        return new ResponseEntity<>(dtos, HttpStatus.FOUND);
    }

    @GetMapping("/vehicles-by-brand")
    public ResponseEntity<List<VehicleDTO>> getVehiclesByBrand(@RequestParam String brand) {
                List<VehicleDTO> dtos = vehicleRepo.findByBrand(brand).stream()
                .map(v -> new VehicleDTO(
                        v.getId(),
                        v.getRegistrationNumber(),
                        v.getBrand(),
                        v.getModel(),
                        v.getProductionYear(),
                        v.getCustomer() != null ? v.getCustomer().getId() : null))
                .toList();

        return new ResponseEntity<>(dtos, HttpStatus.FOUND);
    }
}
