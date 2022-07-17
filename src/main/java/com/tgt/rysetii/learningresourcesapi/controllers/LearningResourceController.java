package com.tgt.rysetii.learningresourcesapi.controllers;

import com.tgt.rysetii.learningresourcesapi.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapi.entity.LearningResourceStatus;
import com.tgt.rysetii.learningresourcesapi.repository.LearningResourceRepository;
import com.tgt.rysetii.learningresourcesapi.service.LearningResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LearningResourceController {

    @Autowired
    private LearningResourceRepository repo;
    @Autowired
    private LearningResourceService lrs;

    @GetMapping("/")
    public List<LearningResource> getResources(){

        return lrs.getLearningResources();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createAccount(@RequestBody List<LearningResource> lrList)
    {
        for(LearningResource lr:lrList) {
            if (repo.existsById(lr.getResourceId()))
                return "Sorry ! Resource already exist";
            else {
                lrs.saveLearningResources(lr);

            }
        }
        return "Successfully created";
    }
    @DeleteMapping("/learningresource/{resourceId}")
    public String deleteAccount(@PathVariable int resourceId)
    {
        if(repo.existsById(resourceId))
        {
            lrs.deleteLearningResourcesById(resourceId);
            return "deleted successfully";
        }
        else
            return "Sorry ! Invalid resourceId";

    }

}
