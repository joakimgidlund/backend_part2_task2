package se.yrgo.vehiclelease.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import se.yrgo.vehiclelease.data.CustomerRepository;
import se.yrgo.vehiclelease.data.VehicleRepository;
import se.yrgo.vehiclelease.domain.Customer;
import se.yrgo.vehiclelease.domain.Vehicle;

@Configuration
public class TestDataInitializer {

    @Bean
    public CommandLineRunner initData(CustomerRepository customerRepo, VehicleRepository vehicleRepo) {
        if(customerRepo.count() > 0) return args -> System.out.println("Data already in dataabse");
        return args -> {
            Customer c1 = new Customer();
            c1.setName("Anders Svensson");
            c1.setPhoneNumber("0730691411");

            Customer c2 = new Customer();
            c2.setName("Maria Johansson");
            c2.setPhoneNumber("0724485520");

            Customer c3 = new Customer();
            c3.setName("Johan Lindgren");
            c3.setPhoneNumber("0768214433");

            Customer c4 = new Customer();
            c4.setName("Elin Karlsson");
            c4.setPhoneNumber("0709132278");

            Customer c5 = new Customer();
            c5.setName("Fredrik Bergström");
            c5.setPhoneNumber("0735529014");

            customerRepo.save(c1);
            customerRepo.save(c2);
            customerRepo.save(c3);
            customerRepo.save(c4);
            customerRepo.save(c5);

            Vehicle v1 = new Vehicle();
            v1.setBrand("Volvo");
            v1.setModel("V70");
            v1.setProductionYear(2006);
            v1.setRegistrationNumber("ABC123");
            v1.setCustomer(c1);

            Vehicle v2 = new Vehicle();
            v2.setBrand("BMW");
            v2.setModel("320i");
            v2.setProductionYear(2014);
            v2.setRegistrationNumber("XYZ789");
            v2.setCustomer(c1);

            Vehicle v3 = new Vehicle();
            v3.setBrand("Toyota");
            v3.setModel("Corolla");
            v3.setProductionYear(2018);
            v3.setRegistrationNumber("JKL456");

            Vehicle v4 = new Vehicle();
            v4.setBrand("Audi");
            v4.setModel("A4");
            v4.setProductionYear(2020);
            v4.setRegistrationNumber("MNO321");
            v4.setCustomer(c3);

            Vehicle v5 = new Vehicle();
            v5.setBrand("Ford");
            v5.setModel("Focus");
            v5.setProductionYear(2012);
            v5.setRegistrationNumber("QWE987");
            v5.setCustomer(c5);

            vehicleRepo.save(v1);
            vehicleRepo.save(v2);
            vehicleRepo.save(v3);
            vehicleRepo.save(v4);
            vehicleRepo.save(v5);

            customerRepo.save(c1);
            customerRepo.save(c2);
            customerRepo.save(c3);
            customerRepo.save(c4);
            customerRepo.save(c5);

            System.out.println("Sample data initialized.");
        };
    }
}
