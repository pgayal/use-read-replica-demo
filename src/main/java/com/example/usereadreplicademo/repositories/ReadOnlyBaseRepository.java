package com.example.usereadreplicademo.repositories;

import com.example.usereadreplicademo.annotation.ReadOnlyRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This is a base repository for creating the entity specific ready only repository
 *
 * @author pgayal
 * created on 02/20/2022
 */

@NoRepositoryBean
@ReadOnlyRepository
public interface ReadOnlyBaseRepository<T, ID> extends Repository<T, ID> {
    Optional<T> findById(ID var1);

    boolean existsById(ID var1);

    List<T> findAll();

    List<T> findAllById(Iterable<ID> var1);

    long count();
}
