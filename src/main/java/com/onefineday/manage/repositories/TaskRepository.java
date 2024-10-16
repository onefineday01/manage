package com.onefineday.manage.repositories;

import com.onefineday.manage.models.Task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAllByUserId(long id, Pageable pageable);
}
