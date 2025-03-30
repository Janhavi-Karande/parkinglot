package com.example.parkinglot.services;

import com.example.parkinglot.exceptions.InvalidLicenseNumberException;
import com.example.parkinglot.models.Owner;
import com.example.parkinglot.repositories.OwnerRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class OwnerService {
    private OwnerRepository ownerRepository;

    private final int currentYear = Year.now().getValue();
    private final String licenseRegex = "^[A-Z]{2}\\s?[0-9]{2}\\s?(1939|19[4-9]\\d|20[0-" +((currentYear /10) %10)+ "]\\d|202[0-" +(currentYear % 10)+ "])[0-9]{7}$";

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    //method to get owner object
    public Owner getOwner(String name, String licenseNumber) throws InvalidLicenseNumberException {

        Optional<Owner> optionalOwner = ownerRepository.findByName(name);
        Owner owner;

        System.out.println("License number = " +licenseNumber);
        if(optionalOwner.isPresent()){
            owner = optionalOwner.get();
        }
        else{
            owner = new Owner();
            owner.setName(name);
            if(checkLicenseNumber(licenseNumber)){
                owner.setLicenseNumber(licenseNumber);
            }
            else
                throw new InvalidLicenseNumberException("License number " +licenseNumber+ " is not valid.");

            ownerRepository.save(owner);
        }


        return owner;
    }

    // method to check license number
    public boolean checkLicenseNumber(String licenseNumber){

        return Pattern.matches(licenseRegex, licenseNumber);
    }
}
