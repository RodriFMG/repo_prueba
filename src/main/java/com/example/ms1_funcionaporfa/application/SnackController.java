package com.example.ms1_funcionaporfa.application;
import com.example.ms1_funcionaporfa.Service.CafeService;
import com.example.ms1_funcionaporfa.Service.SnackService;
import com.example.ms1_funcionaporfa.Domain.Cafe;
import com.example.ms1_funcionaporfa.Domain.Snack;

//import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Tag(name = "Snack", description = "Endpoints de snack")
//@Api(value = "Controlador de Snack", description = "Crud de snack")
@RestController
@RequestMapping("/snack")
@CrossOrigin(origins = "http://localhost:19006")
public class SnackController {

    @Autowired
    private SnackService snackService;

    @GetMapping
    public ResponseEntity<List<Snack>> AllSnack(){
        List<Snack> cafe = snackService.getAllSnack();
        return new ResponseEntity<>(cafe,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> PostSnack(@RequestBody Snack snack){
        snackService.saveSnack(snack);
        return ResponseEntity.status(201).body("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSnack(@PathVariable Long id, @RequestBody Snack snack){
        Optional<Snack> updatedSnack = snackService.updateSnack(id,snack);
        return updatedSnack.isPresent() ? ResponseEntity.status(200).body("Updated") :
                ResponseEntity.status(404).body("Not Found");

    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchSnack(@PathVariable Long id, @RequestBody Snack snack){
        Optional<Snack> patchSnack = snackService.patchSnack(id,snack);
        return patchSnack.isPresent() ? ResponseEntity.status(200).body("Updated partially") :
                ResponseEntity.status(404).body("Not Found");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSnack(@PathVariable Long id) {
        Optional<Snack> deletedSnack = snackService.deleteSnack(id);
        return deletedSnack.isPresent() ? ResponseEntity.status(200).body("Deleted") : ResponseEntity.status(404).body("Not Found");
    }
}
