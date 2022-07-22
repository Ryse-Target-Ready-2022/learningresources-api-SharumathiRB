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
@RequestMapping("/learningresources/v1")
public class LearningResourceController {

    @Autowired
    private LearningResourceRepository learningResourceRepository;
    @Autowired
    private LearningResourceService learningResourceService;

    @GetMapping("/")
    public List<LearningResource> getResources(){

        return learningResourceService.getLearningResources();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createResource(@RequestBody List<LearningResource> learningResourceList)
    {
            return learningResourceService.saveLearningResources(learningResourceList);
    }
    @DeleteMapping("/learningresource/{resourceId}")
    public String deleteResource(@PathVariable int resourceId) {

           return learningResourceService.deleteLearningResourcesById(resourceId);
    }
}
