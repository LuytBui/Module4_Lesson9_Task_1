package com.codegym.service;

import com.codegym.model.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IVoteService extends IGeneralService<Vote> {
    Page<Vote> getTodayVotes(Pageable pageable);
}
