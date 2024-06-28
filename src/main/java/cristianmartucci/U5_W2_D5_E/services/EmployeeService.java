package cristianmartucci.U5_W2_D5_E.services;

import cristianmartucci.U5_W2_D5_E.entities.Employee;
import cristianmartucci.U5_W2_D5_E.exceptions.NotFoundException;
import cristianmartucci.U5_W2_D5_E.payloads.employees.EmployeeDTO;
import cristianmartucci.U5_W2_D5_E.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> getAllEmployee(int pageNumber, int pageSize, String sortBy){
        if(pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return employeeRepository.findAll(pageable);
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee(employeeDTO.username(), employeeDTO.name(), employeeDTO.surname(), employeeDTO.email());
        employee.setAvatar("https://ui-avatars.com/api/?name=" + employee.getName() + "+" +employee.getSurname());
        return this.employeeRepository.save(employee);
    }

    public Employee findByID(UUID employeeId){
        return this.employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException(employeeId));
    }
}

