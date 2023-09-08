package com.example.demo.contoller;

import com.example.demo.model.Message;
import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @PostMapping("hello")
    public String hello(@RequestParam("name") String nombre) {
        return "Hola " + nombre;
    }


    @PostMapping("sum")
    public int sum(@RequestParam("A") int A, @RequestParam("B") int B) {
        return (A+B);
    }

    @PutMapping("mult/{Alfa}/{B}") //mult/8/10
    public int mult(@PathVariable("Alfa") int a, @PathVariable("B") int b){
        return a*b;
    }

    @DeleteMapping("power/{base}")
    public int power(@PathVariable("base") int base, @RequestHeader("exp") int exp, @RequestHeader("User-Agent") String agent){
        System.out.println(">>>>>>>>");
        System.out.println(agent);
        return (int) Math.pow(base, exp);
    }


    @PostMapping("user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        System.out.println(user.getUsername());
        if(user.getUsername().equals("admin") && user.getPassword().equals("123456")){
            return ResponseEntity.status(200).body(
                    new Message(
                            "Loggeado exitosamente, felicitaciones"
                    )
            );
        }else{
            return ResponseEntity.status(400).body(
                    new Message(
                            "El usuario o contraseña no son correctos"
                    )
            );
        }

    }



}

