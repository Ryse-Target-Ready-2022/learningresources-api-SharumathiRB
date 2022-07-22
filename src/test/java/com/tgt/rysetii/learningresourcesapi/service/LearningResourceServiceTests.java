package com.tgt.rysetii.learningresourcesapi.service;

import com.tgt.rysetii.learningresourcesapi.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapi.entity.LearningResourceStatus;
import com.tgt.rysetii.learningresourcesapi.repository.LearningResourceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LearningResourceServiceTests {

    @Mock
    private LearningResourceRepository learningResourceRepository;

    @InjectMocks
    private  LearningResourceService learningResourceService;

    LearningResource learningResource1 = new LearningResource(1311, "Test Name 1", 100.0, 120.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));
    LearningResource learningResource2 = new LearningResource(1312, "Test Name 2", 120.0, 180.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));

     @Test
    public void testProfitMargin(){
      List<LearningResource> learningResourceList=new ArrayList<>(Arrays.asList(learningResource1,learningResource2));
      when(learningResourceRepository.findAll()).thenReturn(learningResourceList);
      Map<Integer,Double> expectedProfitMargins= learningResourceList.stream().collect(Collectors.toMap(LearningResource::getResourceId,LearningResourceService::getProfit));
      Map<Integer,Double> actualProfitMargins=learningResourceService.getProfitMargin();
      assertEquals(expectedProfitMargins, actualProfitMargins, "Wrong profit margins");

    }

    @Test
    public void testSaveLearningResources(){
         List<LearningResource> learningResourceList=new ArrayList<>(Arrays.asList(learningResource1,learningResource2));
        learningResourceService.saveLearningResources(learningResourceList);
       Mockito.verify(learningResourceRepository,times(learningResourceList.size())).save(any(LearningResource.class));

    }

    @Test
    public void testDeleteById(){
         int learningResourceId=1311;
         learningResourceService.deleteLearningResourcesById(learningResourceId);
         verify(learningResourceRepository,Mockito.times(1)).deleteById(learningResourceId);
    }
    @Test
    public void sortTheLearningResourceBasedOnProfitMarginInNonIncreasingOrder(){
        List<LearningResource> learningResources = new ArrayList<>();
        LearningResource learningResource1 = new LearningResource(1311, "Test Name 1", 100.0, 120.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));
        LearningResource learningResource2 = new LearningResource(1312, "Test Name 2", 120.0, 180.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        learningResources.add(learningResource1);
        learningResources.add(learningResource2);

        Collections.sort(learningResources,new Sorters());

        when(learningResourceRepository.findAll()).thenReturn(learningResources);

        List<LearningResource> learningResourcesSorted = learningResourceService.sortLearningResoucesByProfitMargin();

        assertEquals(learningResources, learningResourcesSorted, "The learning resources are not sorted by profit margin");
    }



}
