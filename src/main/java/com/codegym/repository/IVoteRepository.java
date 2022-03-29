package com.codegym.repository;

import com.codegym.model.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IVoteRepository extends PagingAndSortingRepository<Vote, Long> {

    @Query(value = "select * from Vote where date = date(:date)", nativeQuery = true)
    Page<Vote> findAllByDateEquals (@Param(value = "date") String date, Pageable pageable);
}
