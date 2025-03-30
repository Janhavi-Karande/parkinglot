package com.example.parkinglot.services;

import com.example.parkinglot.exceptions.UserNotFoundException;
import com.example.parkinglot.models.Operator;
import com.example.parkinglot.repositories.OperatorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperatorService {
    private OperatorRepository operatorRepository;

    public OperatorService(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    public Operator sigup(String name, String email, String password) {

        // check if operator exist
        Optional<Operator> optionalOperator = operatorRepository.findByEmail(email);

        if(optionalOperator.isPresent()) {
            // if exist ask them to login
            try {
                login(email, password);
            }
            catch (UserNotFoundException e) {
                System.out.println("User not found");
            }
        }

        // if, operator does not exist encode the password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Operator operator = new Operator();
        operator.setName(name);
        operator.setEmail(email);
        operator.setPassword(passwordEncoder.encode(password));

        // save it in database
        return operatorRepository.save(operator);
    }

    public Operator login(String email, String password) throws UserNotFoundException {

        Optional<Operator> optionalOperator = operatorRepository.findByEmail(email);

        if(optionalOperator.isEmpty()) {
            throw new UserNotFoundException("User with email " +email+ " does not exist!!");
        }

        Operator operator = optionalOperator.get();

        // verify the password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(passwordEncoder.matches(password, operator.getPassword())) {
            return operator;
        }

        throw new RuntimeException("Wrong password");
    }

    public Operator getOperator(int operatorId) throws UserNotFoundException {
        Optional<Operator> optionalOperator = operatorRepository.findById(operatorId);
        if(optionalOperator.isEmpty()) {
            throw new UserNotFoundException("Operator with id " +operatorId+ " does not exist.");
        }

        return optionalOperator.get();
    }
}
