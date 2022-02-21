package com.example.usereadreplicademo.mapper;

import com.example.usereadreplicademo.dto.EngagementDTO;
import com.example.usereadreplicademo.model.Engagement;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author pgayal
 * created on 02/20/2022
 */
@Mapper(componentModel = "spring")
public interface EngagementMapper {

    EngagementDTO entityToDTO(Engagement engagement);

    Engagement dtoToEntity(EngagementDTO engagementDTO);

    List<EngagementDTO> entityListToDTOList(List<Engagement> engagements);

    List<Engagement> dtoListToEntityList(List<EngagementDTO> dtos);
}
