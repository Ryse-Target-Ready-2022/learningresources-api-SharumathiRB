package com.tgt.rysetii.learningresourcesapi.service;

import com.tgt.rysetii.learningresourcesapi.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapi.repository.LearningResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

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
            learningResourceRepository.deleteById(resourceId);
            return "deleted successfully";

    }
    public static double getProfit(LearningResource lr){
       double profitMargin=(lr.getSellingPrice()-lr.getCostPrice())/lr.getSellingPrice();
       return profitMargin;
    }
    public Map<Integer, Double> getProfitMargin()
    {
        List<LearningResource> learningResourceList=getLearningResources();
        Map<Integer, Double> LearningResourceMap=new LinkedHashMap<>();
       LearningResourceMap= learningResourceList.stream().collect(Collectors.toMap(LearningResource::getResourceId,LearningResourceService::getProfit));
        return LearningResourceMap;
    }
    public List<LearningResource> sortLearningResoucesByProfitMargin(){
        List<LearningResource> lr=getLearningResources();
        Collections.sort(lr,new Sorters());
        return lr;
    }
}
