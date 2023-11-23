package com.example.eventService.repository;

import com.example.eventService.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {
    @Override
    Optional<Event> findById(Integer id);

    @Override
    List<Event> findAll();

    @Override
    void deleteById(Integer integer);

    @Override
    <S extends Event> S save(S entity);
}
