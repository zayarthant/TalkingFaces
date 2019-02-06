/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talking.faces.controller;

import com.taking.faces.util.ImageUtil;
import com.talking.faces.entity.UserAccount;
import com.talking.faces.model.UserModel;
import java.io.File;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import javax.inject.Inject;

/**
 *
 * @author Zayar Thant
 */
@Named
@SessionScoped
public class LoginController implements Serializable {

    private String email;
    private String password;

    private String userName;
    private Part profilePath;
    private String profileUrl;
    private long id;

    @Inject
    private UserModel userManager;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Part getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(Part profilePath) {
        this.profilePath = profilePath;
    }

    public LoginController() {

    }

    public boolean isValidUser() {
        if (!email.isEmpty() && !password.isEmpty()) {
            return userManager.loginUserAccount(email, password);
        }
        return false;
    }

    public String login() {

        if (isValidUser()) {
            UserAccount account = userManager.getUerInform(email, password);
            userName = account.getName();
            profileUrl = account.getProfileImage();
            id = account.getId();
            return "timelime.xhtml?faces-redirect=true";
        }

        return null;
    }

    public String register() {
        if (!email.isEmpty() && !password.isEmpty() && !userName.isEmpty() && ImageUtil.isValidImage(profilePath)) {
            profileUrl = ImageUtil.generateImageName(profilePath.getSubmittedFileName());
            String fullProfileUrl = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/image") + File.separator + profileUrl;
            ImageUtil.uploadImage(profilePath, fullProfileUrl);
            UserAccount account = userManager.createUserAccount(userName, password, email, profileUrl);
            id = account.getId();
            return "timelime.xhtml?faces-redirect=true";
        }
        return null;
    }

    public boolean isOwner(long index) {
        return index == id;
    }

}
