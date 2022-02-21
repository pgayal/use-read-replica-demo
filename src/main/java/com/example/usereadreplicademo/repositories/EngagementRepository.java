package com.example.usereadreplicademo.repositories;

import com.example.usereadreplicademo.model.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is normal JPA repository supporting both read and with operations
 *
 * @author pgayal
 * created on 02/20/2022
 */
public interface EngagementRepository extends JpaRepository<Engagement, Long> {
}
