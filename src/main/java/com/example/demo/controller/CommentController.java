package com.example.demo.controller;

import com.example.demo.domain.Comment;
import com.example.demo.dto.CommentDTO;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{boardId}")
    public ResponseEntity<Long> createComment(
            @PathVariable Long boardId,
            @RequestBody CommentDTO commentDTO
    ) {
        Comment comment = commentService.createComment(boardId, commentDTO);
        return ResponseEntity.ok(comment.getId());
    }
    @GetMapping("/{boardId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long boardId) {
        List<Comment> comments = commentService.getComments(boardId);
        return ResponseEntity.ok(comments);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> removeComment(@PathVariable("id") Long id){
        commentService.deleteComment(id);
        return ResponseEntity.ok(id);
    }
}
