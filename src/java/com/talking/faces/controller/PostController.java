package com.talking.faces.controller;

import com.taking.faces.util.PostDisplayType;
import com.talking.faces.entity.Post;
import com.talking.faces.model.CommentModel;
import com.talking.faces.model.PostModel;
import com.talking.faces.model.UserModel;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author Zayar Thant
 */
@Named(value = "postController")
@SessionScoped
public class PostController implements Serializable {

    private List<Post> postList;
    private String search;
    private int startingPoint;
    private PostDisplayType displayType;
    private long filterUser;

    @Inject
    private UserModel userManager;
    @Inject
    private PostModel postManager;
    @Inject
    private CommentModel commentManager;

    @PostConstruct
    public void init() {
        isSamePostDisplay(PostDisplayType.TIMELINE);
        displayType = PostDisplayType.TIMELINE;
        postList = postManager.findPost(startingPoint, 10);
    }

    public int getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(int startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String postFilter(long userId) {
        filterUser = userId;
        isSamePostDisplay(PostDisplayType.SPECIFICUSER);
        postList = postManager.findPostByUser(userManager.getUserById(userId), startingPoint, 10);
        displayType = PostDisplayType.SPECIFICUSER;
        return "timelime.xhtml?faces-redirect=true";
    }

    public String searchPost() {
        postList = postManager.searchPost(search, startingPoint, 10);
        isSamePostDisplay(PostDisplayType.SEARCH);
        displayType = PostDisplayType.SEARCH;
        search = "";
        return null;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public PostController() {

    }

    public String DeletePost(long index, long userIndex) {
        commentManager.removeAllCommentOfAPost(postManager.getPost(index));
        postManager.deletePost(index);
        postList = postManager.findPostByUser(userManager.getUserById(userIndex), startingPoint, 10);
        return "timelime.xhtml?faces-redirect=true";
    }

    public String incStartingPoint() {
        startingPoint += 10;
        detactUpdateWay();
        return "timelime.xhtml?faces-redirect=true";
    }

    public String decStartingPoint() {
        startingPoint -= 10;
        detactUpdateWay();
        return "timelime.xhtml?faces-redirect=true";
    }

    private void isSamePostDisplay(PostDisplayType displayType) {
        if (this.displayType != displayType) {
            startingPoint = 0;
        }
    }

    private void detactUpdateWay() {
        switch (displayType) {
            case SEARCH:
                searchPost();
                break;
            case SPECIFICUSER:
                postFilter(filterUser);
                break;
            case TIMELINE:
                init();
                break;
        }
    }

}
