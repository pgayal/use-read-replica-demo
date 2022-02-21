package com.example.usereadreplicademo.repositories;

import com.example.usereadreplicademo.annotation.ReadOnlyRepository;
import com.example.usereadreplicademo.model.Engagement;

import java.util.List;

/**
 * This is read only repository for the Engagement entity.
 * <p>
 * It extends ReadOnlyBaseRepository to restrict create, update, delete methods
 * and also has the ReadOnlyRepository annotation to connect to appropriate data source
 *
 * @author pgayal
 * created on 02/20/2022
 */
@ReadOnlyRepository
public interface EngagementReadOnlyRepository extends ReadOnlyBaseRepository<Engagement, Long> {
    List<Engagement> findAllByEngagementType(Long engagementType);
}
