package com.ihor.productspec.service;

import com.ihor.productspec.model.*;
import com.ihor.productspec.repository.UsabilityRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsabilityService {
    private final UsabilityRepository usabilityRepository;

    public UsabilityService(final UsabilityRepository usabilityRepository) {
        this.usabilityRepository = usabilityRepository;
    }

    public List<DisentanglementModel> performStructuralUsability(boolean saveToDB) {
        var res = usabilityRepository.getStructuralUsability();
        if(saveToDB) {
            usabilityRepository.addAll(res);
        }
        return res;
    }

    public List<TotalDisentanglementModel> performTotalUsability() {
        return usabilityRepository.getTotalUsability();
    }
}
