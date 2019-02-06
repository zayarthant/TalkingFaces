/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talking.faces.controller;

import com.talking.faces.entity.Comment;
import com.talking.faces.model.CommentModel;
import com.talking.faces.model.PostModel;
import com.talking.faces.model.UserModel;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Zayar Thant
 */
@Named(value = "commentController")
@SessionScoped
public class CommentController implements Serializable {

    @Inject
    private CommentModel commentManager;
    @Inject
    private LoginController controller;
    @Inject
    private PostModel postManager;
    @Inject
    private UserModel userManager;

    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CommentController() {
    }

    public void createComment(long index) {
        commentManager.createComment(userManager.getUserById(controller.getId()), comment, postManager.getPost(index));
        comment = "";
    }
    
    public List<Comment> getCommentList(long index){
        return commentManager.getCommentList(postManager.getPost(index), 50);
    }

}
