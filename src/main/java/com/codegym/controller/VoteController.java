package com.codegym.controller;

import com.codegym.exception.FeedbackContainsBadWordException;
import com.codegym.model.BadWord;
import com.codegym.model.Vote;
import com.codegym.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class VoteController {
    @Autowired
    IVoteService voteService;

    @ExceptionHandler({FeedbackContainsBadWordException.class, })
    public ModelAndView showErrorPage() {
        System.out.println();
        return new ModelAndView("error-bad-word");
    }

    @GetMapping("/home")
    public ModelAndView viewHomePage(@RequestParam(value = "page", required = false) Integer page) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("vote", new Vote());

        if (page == null) page = 0;
        Sort sort = Sort.by("date").ascending();
        Pageable pageable = PageRequest.of(page, 5, sort);

        Page<Vote> todayVotes = voteService.getTodayVotes(pageable);
        modelAndView.addObject("todayVotes", todayVotes);
        return modelAndView;
    }

    @PostMapping("/home")
    public ModelAndView postVoteAndRefresh(@ModelAttribute Vote vote) throws FeedbackContainsBadWordException {

        if (BadWord.check(vote.getComment())){
            throw new FeedbackContainsBadWordException();
        }
        voteService.save(vote);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping ("/like/{voteId}")
    public ModelAndView likeVote(@PathVariable Long voteId){
        Optional<Vote> vote = voteService.findById(voteId);
        if (vote.isPresent()) {
            int likeCount = vote.get().getLikeCount();
            likeCount ++;
            vote.get().setLikeCount(likeCount);
            voteService.save(vote.get());
        }
        return new ModelAndView("redirect:/home");
    }

}
