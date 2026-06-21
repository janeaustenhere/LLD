package com.example.atmMachine.controller;


import com.example.atmMachine.model.ATM;
import com.example.atmMachine.model.Card;
import com.example.atmMachine.services.ATMService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/atm")
@RestController
public class ATMController {

    private final ATMService atmService;


    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    @PostMapping("addATMMachine")
    public ResponseEntity<ATM> addATMMchine(@RequestBody ATM atm){

        ATM addedATM = null;
        try{
            addedATM = atmService.addATMMachine(atm);
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(addedATM);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(addedATM);
    }

    @PostMapping("/addCashToATM")
    public ResponseEntity<ATM> addCashToATM(@RequestBody ATM atm){
        ATM addedATM = null;
        try{
            addedATM = atmService.addCashToATM(atm);
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(addedATM);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(addedATM);
    }

    @PostMapping("/startATMState/{atmId}")
    public ResponseEntity<String> startATMState(@PathVariable String atmId, @RequestBody Card card){
        try {
            atmService.startATMState(atmId,card);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body("Operation completed");

    }


}
