package com.example.managementemployees.Controller;

import com.example.managementemployees.Model.ManagementEmployee;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/ManagementEmployee")
public class ManagementEmployeeController {
    ArrayList<ManagementEmployee> managementEmployees = new ArrayList();

    @GetMapping("/getAll")
    public String getAll() {
        return managementEmployees.toString();
    }


    @PostMapping("/add")
    public String add(@RequestBody @Valid ManagementEmployee managementEmployee) {
        int count = 1;
        for (int i = 0; i < managementEmployees.size(); i++) {
            count++;
        }
        managementEmployee.setId(count);
        managementEmployees.add(managementEmployee);
        return managementEmployee.toString();
    }

    @PutMapping("/update/{id}")
    public String put(@PathVariable int id, @Valid @RequestBody ManagementEmployee managementEmployee) {
        int index = id - 1;
        if (id == 0) {
            return ("Pls enter valid number");
        }
        if (index != managementEmployees.size() - 1 && index > managementEmployees.size() - 1) {
            return ("this id is not found");
        } else {
            int Id = managementEmployees.get(index).getId();
            managementEmployee.setId(Id);
            managementEmployees.set(index, managementEmployee);
            return "Success";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        if (id != managementEmployees.size() - 1 && id > managementEmployees.size() - 1) {
            return ("this Id is not found");
        }
        managementEmployees.remove(id);
        return "Success";
    }

    @PutMapping("/vacationDiscount/{id}")
    public String vacationDiscount(@PathVariable int id, @RequestParam int vacation) {
        int index = id - 1;
        if (id == 0) {
            return ("Pls enter valid number");
        }
        if (index != managementEmployees.size() - 1 && index > managementEmployees.size() - 1) {
            return ("this id is not found");
        } else {
            int Id = managementEmployees.get(index).getId();
            String name = managementEmployees.get(index).getName();
            int age = managementEmployees.get(index).getAge();
            String position = managementEmployees.get(index).getPosition();
            Date employmentYear = managementEmployees.get(index).getEmploymentYear();
            int annualLeave = managementEmployees.get(index).getAnnualLeave();
            if(managementEmployees.get(index).isOnLeave()){
                return ("BadRequest");
            } else if (annualLeave == 0) {
                return ("BadRequest");
            }else {
                managementEmployees.get(index).setId(Id);
                managementEmployees.get(index).setName(name);
                managementEmployees.get(index).setAge(age);
                managementEmployees.get(index).setPosition(position);
                managementEmployees.get(index).setOnLeave(true);
                managementEmployees.get(index).setEmploymentYear(employmentYear);
                managementEmployees.get(index).setAnnualLeave(annualLeave - vacation);
                managementEmployees.set(index, managementEmployees.get(index));
                return "Success";
            }
        }
    }
}
