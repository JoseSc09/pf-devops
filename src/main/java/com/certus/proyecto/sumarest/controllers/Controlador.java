package com.certus.proyecto.sumarest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controlador {

    @GetMapping("/")
    public String Home(){
        return "Ejemplo GET: /suma?a=5&b=2 <br>response: 7";
    }
    @GetMapping("/suma")
    public ResponseEntity<?> Suma(@RequestParam Integer a,@RequestParam Integer b){
        Integer resultado = a + b;
        return new ResponseEntity(resultado, HttpStatus.OK);
    }
}
