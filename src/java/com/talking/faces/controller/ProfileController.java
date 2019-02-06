/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talking.faces.controller;

import com.taking.faces.util.ImageUtil;
import com.talking.faces.model.PostModel;
import com.talking.faces.model.UserModel;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;

/**
 *
 * @author Zayar Thant
 */
@Named(value = "profileController")
@RequestScoped
public class ProfileController implements Serializable {

    @Inject
    private LoginController controller;
    @Inject
    private PostModel postManager;
    @Inject
    private UserModel userManager;
    @Inject
    private PostController postController;

    private Part profilePath;

    public Part getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(Part profilePath) {
        this.profilePath = profilePath;
    }

    public ProfileController() {
    }

    public String logOut() {
        controller.setEmail("");
        controller.setPassword("");
        return "index.xhtml?faces-redirect=true";
    }

    public String onPageLoad() {
        if (!controller.isValidUser()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?faces-redirect=true");
            } catch (IOException ex) {

            }
        }
        return null;
    }

    public String postImage() {
        String profileUrl = ImageUtil.generateImageName(profilePath.getSubmittedFileName());
        String fullProfileUrl = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/post") + File.separator + profileUrl;
        ImageUtil.uploadImage(profilePath, fullProfileUrl);
        postManager.createPost(userManager.getUserById(controller.getId()), profileUrl);
        postController.setStartingPoint(0);
        postController.init();
        return "timelime.xhtml?faces-redirect=true";
    }

}