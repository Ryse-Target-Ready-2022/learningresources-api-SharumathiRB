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
    LearningResourceRepository repo;

    public List<LearningResource> getLearningResources(){
        return repo.findAll();
    }
    public void saveLearningResources(List<LearningResource> learningResource1)
    {
        for(LearningResource l1:learningResource1)
        {
            repo.save(l1);
        }
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
