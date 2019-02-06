/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talking.faces.controller;

import com.talking.faces.model.PostModel;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Zayar Thant
 */
@Named(value = "likeController")
@RequestScoped
public class LikeController {

    @Inject
    private PostModel postManager;

    public LikeController() {
    }

    public long getLikeCount(long index) {
        return postManager.getLikeCount(index);
    }

    public String likeIncrease(long index) {
        postManager.likePost(index);
        return null;
    }

}
