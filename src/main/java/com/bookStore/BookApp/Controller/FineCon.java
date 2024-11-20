package com.bookStore.BookApp.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookStore.BookApp.EntitiyClass.FineEntity;
import com.bookStore.BookApp.Service.FineSer;

@RestController
@RequestMapping("/fine")
public class FineCon {

    @Autowired
    private FineSer fineSer;


    @GetMapping
    public List<FineEntity> getAll(){
        return fineSer.getAllFine();
    }

    @GetMapping("/{id}")
    public Optional<FineEntity> getFinebyId(@PathVariable int id){
        return fineSer.geFinetbyId(id);
    }

    
    @PostMapping
    public Boolean saveFine(@RequestBody FineEntity fineEntity){
        if (fineEntity != null) {
            fineSer.saveFine(fineEntity);
            return true;
        }
        return false;

    }

    @PutMapping("/{id}")
    public Boolean updateFine(@PathVariable int id, @RequestBody FineEntity fineEntity){
        if (id == fineEntity.getFineId()) {
            fineSer.updateFine(id, fineEntity);
            return true;

        }
        return false;

    }

    @DeleteMapping("/{id}")
    public Boolean deleteFine(@PathVariable int id){
        fineSer.deleteFine(id);
        return true;
    }
    
}
