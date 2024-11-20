package com.bookStore.BookApp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.BookApp.EntitiyClass.FineEntity;
import com.bookStore.BookApp.Repository.FineRepo;

@Service
public class FineSer {
    

    @Autowired
    private FineRepo fineRepo;


    public List<FineEntity> getAllFine(){
        return fineRepo.findAll();

    }

    public Optional<FineEntity> geFinetbyId(int id){
        if (fineRepo.existsById(id)) {
            return fineRepo.findById(id);
        
        }
        return null;

    }

    public Boolean saveFine(FineEntity fineEntity){
        if (fineEntity != null) {
            fineRepo.save(fineEntity);
            return true;

        }
        return false;
    }

    public Boolean updateFine(int id, FineEntity fineEntity){
        if (id == fineEntity.getFineId()) {
            fineRepo.save(fineEntity);
            return true;
        }
        return false;
    }

    public Boolean deleteFine(int id){
        if (fineRepo.existsById(id)) {
            fineRepo.deleteById(id);
            return true;
        }
        return false;
    }

    
}
