package com.tgt.rysetii.learningresourcesapi.service;

import com.tgt.rysetii.learningresourcesapi.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapi.entity.LearningResourceStatus;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class LearningResourceService {

    public List<LearningResource> getLearningResources(){
        List<LearningResource> learningResourceList= new ArrayList<>();
        try{
        File f=new File("LearningResources.csv");
        BufferedReader fr=new BufferedReader(new FileReader(f));
            String line;
            while((line=fr.readLine())!=null)
            {
                String[] attributes = line.split(",");
                Integer resourceId=Integer.parseInt(attributes[0]);
                String resourceName=attributes[1];
                Double costPrice=Double.parseDouble(attributes[2]);
                Double sellingPrice=Double.parseDouble(attributes[3]);
                LearningResourceStatus productStatus=LearningResourceStatus.valueOf(attributes[4]);
                LocalDate createdDate=LocalDate.parse(attributes[5]);
                LocalDate publishedDate=LocalDate.parse(attributes[6]);
                LocalDate retiredDate=LocalDate.parse(attributes[7]);
                LearningResource lr=new LearningResource(resourceId,resourceName,costPrice,sellingPrice,productStatus,createdDate,publishedDate,retiredDate);
                learningResourceList.add(lr);
            }
            fr.close();
         }
        catch (Exception e){
            System.out.println(e);
        }
        return learningResourceList;
    }
    public void saveLearningResources(List<LearningResource> learningResource)
    {
        try{
            PrintWriter pw=new PrintWriter(new FileOutputStream("LearningResources.csv",true));
            for(LearningResource learningResource1:learningResource){
                pw.println(learningResource1.getResourceId()+","+learningResource1.getResourceName()+","+learningResource1.getCostPrice()+","+learningResource1.getSellingPrice()+","+learningResource1.getProductStatus()+","+learningResource1.getCreatedDate()+","+learningResource1.getPublishedDate()+","+learningResource1.getRetiredDate());
               //System.out.println(learningResource1);
                pw.flush();
            }
            pw.close();
        }
        catch (Exception e){
            System.out.println(e);
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
