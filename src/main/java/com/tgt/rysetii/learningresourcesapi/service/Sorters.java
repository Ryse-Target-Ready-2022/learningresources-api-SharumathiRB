package com.tgt.rysetii.learningresourcesapi.service;

import com.tgt.rysetii.learningresourcesapi.entity.LearningResource;

import java.util.Comparator;
import java.util.stream.Collector;

public class Sorters implements Comparator {
    public int compare(Object obj1,Object obj2)
    {
        LearningResource lr1=(LearningResource) obj1;
        LearningResource lr2=(LearningResource) obj2;
        double p1=LearningResourceService.getProfit(lr1);
       double p2= LearningResourceService.getProfit(lr2);
        if(p1>p2)
            return 1;
        else if(lr1==lr2)
            return 0;
        else
            return -1;
    }
}
