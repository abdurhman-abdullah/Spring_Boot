package com.example.bankmanagement.Controller;

import com.example.bankmanagement.Model.BankManagement;
import com.example.bankmanagement.Response.MessageResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/BankManagement")
public class BankManagementController {
    ArrayList<BankManagement> bankManagements = new ArrayList<>();

    @GetMapping("/getAll")
    public Object getAll(){
        return bankManagements.toString();
    }

    @PostMapping("/add")
    public MessageResponse add(@RequestBody BankManagement bankManagement){
        int count = 1;
        for (int i = 0; i < bankManagements.size(); i++){
            count = bankManagements.get(i).getID() + 1;
        }
        bankManagement.setID(count);
        bankManagements.add(bankManagement);
        return new MessageResponse("Success");
    }

    @PutMapping("/update/{index}")
    public MessageResponse put(@PathVariable int index, @RequestBody BankManagement bankManagement){
        if (index != bankManagements.size()-1 && index > bankManagements.size()-1){
            return new MessageResponse("this number is not found");
        }
        int id = bankManagements.get(index).getID();
        bankManagement.setID(id);
        bankManagements.set(index, bankManagement);
        return new MessageResponse("Success");
    }

    @DeleteMapping("/delete/{index}")
    public MessageResponse delete(@PathVariable int index){
        if (index != bankManagements.size()-1 && index > bankManagements.size()-1){
            return new MessageResponse("this number is not found");
        }
        bankManagements.remove(index);
        return new MessageResponse("Success");
    }

    @PutMapping("/depositMoney/{id}")
    public MessageResponse depositMoney(@PathVariable int id, @RequestParam String deposit){
        for (int i = 0; i < bankManagements.size(); i++){
            if(bankManagements.get(i).getID() == id) {
                String balance = bankManagements.get(i).getBalance();
                int newBalance = Integer.parseInt(balance) + Integer.parseInt(deposit);
                bankManagements.get(i).setBalance(String.valueOf(newBalance));
                bankManagements.set(i, bankManagements.get(i));
                return new MessageResponse("Success");
            }
        }
        return new MessageResponse("this id is not found");
    }

    @PutMapping("/Withdraw/{id}")
    public MessageResponse withdrawMoney(@PathVariable int id, @RequestParam String deposit){
        for (int i = 0; i < bankManagements.size(); i++){
            if(bankManagements.get(i).getID() == id) {
                String balance = bankManagements.get(i).getBalance();
                int newBalance = Integer.parseInt(balance) - Integer.parseInt(deposit);
                bankManagements.get(i).setBalance(String.valueOf(newBalance));
                bankManagements.set(i, bankManagements.get(i));
                return new MessageResponse("Success");
            }
        }
        return new MessageResponse("this id is not found");
    }

}
