package com.example.homework4.Services;

import com.example.homework4.DTO.AddressInsertDto;
import com.example.homework4.DTO.AddressUpdateDto;
import com.example.homework4.Excptions.ExceptionApiResponse;
import com.example.homework4.Models.Address;
import com.example.homework4.Models.Teacher;
import com.example.homework4.Repositories.AddressRepository;
import com.example.homework4.Repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;
    public void add(AddressInsertDto dto, Errors errors){

        if(errors.hasErrors())
            throw new ExceptionApiResponse(errors.getFieldError().getDefaultMessage());

        Teacher findTeacher = teacherRepository.findById(dto.getTeacher_id()).orElseThrow(() -> new ExceptionApiResponse("id not found"));

        Address address = new Address(findTeacher.getId(), dto.getArea(), dto.getStreet(), dto.getBuildingNumber(), findTeacher);

        addressRepository.save(address);
    }

    public void update(int id, AddressUpdateDto dto, Errors errors){
        if(errors.hasErrors())
            throw new ExceptionApiResponse(errors.getFieldError().getDefaultMessage());

        Address address = addressRepository.findById(id).
                orElseThrow(() -> new ExceptionApiResponse("id not found"));

        address.setArea(dto.getArea());
        address.setStreet(dto.getStreet());
        address.setBuildingNumber(dto.getBuildingNumber());
        addressRepository.save(address);
    }

    public void delete(int id){
        Address address = addressRepository.findById(id).
                orElseThrow(() -> new ExceptionApiResponse("id not found"));
        addressRepository.delete(address);
    }
}
