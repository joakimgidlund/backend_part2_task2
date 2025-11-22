package se.yrgo.vehiclelease.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se.yrgo.vehiclelease.domain.Customer;
import se.yrgo.vehiclelease.domain.Vehicle;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    @Query("SELECT c.leasedVehicles FROM Customer c WHERE c.id = :id")
    List<Vehicle> findVehiclesByCustomerId(@Param("id") Long id);

    @Query("SELECT c, v FROM Customer c LEFT JOIN c.leasedVehicles v")
    List<Object[]> findAllUsersAndVehicles();

    Customer findByName(String name);
}
