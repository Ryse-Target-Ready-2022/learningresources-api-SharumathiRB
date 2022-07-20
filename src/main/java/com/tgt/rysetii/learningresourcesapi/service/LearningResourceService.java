package com.tgt.rysetii.learningresourcesapi.service;

import com.tgt.rysetii.learningresourcesapi.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapi.entity.LearningResourceStatus;
import com.tgt.rysetii.learningresourcesapi.repository.LearningResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
@Component
public class LearningResourceService {

    @Autowired
    LearningResourceRepository learningResourceRepository;

    public List<LearningResource> getLearningResources(){
        return learningResourceRepository.findAll();
    }
    public String saveLearningResources(List<LearningResource> learningResourceList)
    {
        for(LearningResource lr:learningResourceList) {
            if (learningResourceRepository.existsById(lr.getResourceId()))
                return "Sorry ! Resource already exist";
            else {
                learningResourceRepository.save(lr);

            }
        }
        return "Successfully created";

    }
    public String deleteLearningResourcesById(int resourceId){
        if(learningResourceRepository.existsById(resourceId))
        {
            learningResourceRepository.deleteById(resourceId);
            return "deleted successfully";
        }
        else
            return "Sorry ! Invalid resourceId";

    }
    public static double getProfit(LearningResource lr){
       double profitMargin=(lr.getSellingPrice()-lr.getCostPrice())/lr.getSellingPrice();
       return profitMargin;
    }
    public LinkedHashMap getProfitMargin()
    {
        List<LearningResource> learningResourceList=getLearningResources();
        double profitMargin;
        LinkedHashMap LearningRespm=new LinkedHashMap();
        for(LearningResource lr:learningResourceList){
            profitMargin=getProfit(lr);
            LearningRespm.put(lr.getResourceId(),profitMargin);
        }
        return LearningRespm;
    }
    public List<LearningResource> sortLearningResoucesByProfitMargin(){
        List<LearningResource> lr=getLearningResources();
        Collections.sort(lr,new Sorters());
        return lr;
    }
}
