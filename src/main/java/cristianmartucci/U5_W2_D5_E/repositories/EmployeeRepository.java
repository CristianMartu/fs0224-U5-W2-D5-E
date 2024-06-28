package cristianmartucci.U5_W2_D5_E.repositories;

import cristianmartucci.U5_W2_D5_E.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
