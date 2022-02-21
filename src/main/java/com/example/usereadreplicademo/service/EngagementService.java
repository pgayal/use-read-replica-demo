package com.example.usereadreplicademo.service;

import com.example.usereadreplicademo.dto.EngagementDTO;
import com.example.usereadreplicademo.mapper.EngagementMapper;
import com.example.usereadreplicademo.model.Engagement;
import com.example.usereadreplicademo.repositories.EngagementReadOnlyRepository;
import com.example.usereadreplicademo.repositories.EngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author pgayal
 * created on 02/20/2022
 */
@Component
public class EngagementService {

    final EngagementRepository engagementRepository;
    final EngagementReadOnlyRepository engagementReadOnlyRepository;
    final EngagementMapper engagementMapper;

    @Autowired
    public EngagementService(EngagementRepository engagementRepository, EngagementReadOnlyRepository engagementReadOnlyRepository, EngagementMapper engagementMapper) {
        this.engagementRepository = engagementRepository;
        this.engagementReadOnlyRepository = engagementReadOnlyRepository;
        this.engagementMapper = engagementMapper;
    }

    public EngagementDTO saveEngagements(EngagementDTO engagementDTO) {
        Engagement saved = engagementRepository.save(engagementMapper.dtoToEntity(engagementDTO));
        return engagementMapper.entityToDTO(saved);
    }

    public List<EngagementDTO> getEngagements() {
        List<Engagement> engagements = engagementReadOnlyRepository.findAll();
        return engagementMapper.entityListToDTOList(engagements);
    }
}
