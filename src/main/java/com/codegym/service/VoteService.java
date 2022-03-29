package com.codegym.service;

import com.codegym.model.Vote;
import com.codegym.repository.IVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class VoteService implements IVoteService {
    @Autowired
    IVoteRepository voteRepository;


    @Override
    public Page<Vote> findAll(Pageable pageable) {
        return voteRepository.findAll(pageable);
    }

    @Override
    public Optional<Vote> findById(Long id) {
        return voteRepository.findById(id);
    }

    @Override
    public void save(Vote vote) {
        vote.setDate(getCurrentDate());
        voteRepository.save(vote);
    }

    @Override
    public void remove(Vote vote) {
        voteRepository.delete(vote);
    }

    @Override
    public void remove(Long id) {
        voteRepository.deleteById(id);
    }

    @Override
    public Page<Vote> getTodayVotes(Pageable pageable) {
        return voteRepository.findAllByDateEquals(getCurrentDate(), pageable);
    }

    public String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return String.valueOf(now);
    }
}
