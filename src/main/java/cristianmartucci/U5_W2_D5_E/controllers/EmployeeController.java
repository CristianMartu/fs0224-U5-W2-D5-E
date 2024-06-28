package cristianmartucci.U5_W2_D5_E.controllers;

import cristianmartucci.U5_W2_D5_E.entities.Employee;
import cristianmartucci.U5_W2_D5_E.exceptions.BadRequestException;
import cristianmartucci.U5_W2_D5_E.payloads.employees.EmployeeDTO;
import cristianmartucci.U5_W2_D5_E.payloads.employees.EmployeeResponseDTO;
import cristianmartucci.U5_W2_D5_E.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private EmployeeResponseDTO saveAuthor(@RequestBody @Validated EmployeeDTO employeeDTO, BindingResult validationResult){
        if (validationResult.hasErrors()){
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return new EmployeeResponseDTO(this.employeeService.saveEmployee(employeeDTO).getEmployeeId());
    }

    @GetMapping
    private Page<Employee> getAllAuthor(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "employeeId") String sortBy){
        return this.employeeService.getAllEmployee(page, size, sortBy);
    }

    @GetMapping("/{employeeId}")
    private Employee findById(@PathVariable UUID employeeId){
        return this.employeeService.findByID(employeeId);
    }
}
