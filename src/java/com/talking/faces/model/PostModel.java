/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talking.faces.model;

import com.talking.faces.entity.Post;
import com.talking.faces.entity.UserAccount;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Zayar Thant
 */
@Stateless
public class PostModel {

    @PersistenceContext(unitName = "TALKING_FACESPU")
    private EntityManager em;

    public Post createPost(UserAccount userAccount, Date postDate, String url) {
        Post post = new Post(userAccount, postDate, url);
        em.persist(post);
        return post;
    }

    public Post createPost(UserAccount userAccount, String url) {
        Post post = createPost(userAccount, new Date(), url);
        return post;
    }

    public Post getPost(long index) {
        return em.find(Post.class, index);
    }

    public void createPost(Post post) {
        em.persist(post);
    }

    public void deletePost(long index) {
        em.remove(em.find(Post.class, index));
    }

    public List<Post> findPostByUser(UserAccount userAccount, int startingPoint, int limit) {
        TypedQuery<Post> query = em.createQuery("SELECT e FROM Post e Where e.userAccount = ?1 ORDER BY e.postDate DESC", Post.class);
        return query.setParameter(1, userAccount).setFirstResult(startingPoint).setMaxResults(limit).getResultList();
    }

    public List<Post> findPost(int startingPoint, int limit) {
        TypedQuery<Post> query = em.createQuery("SELECT e FROM Post e ORDER BY e.postDate  DESC", Post.class);
        return query.setFirstResult(startingPoint).setMaxResults(limit).getResultList();
    }

    public List<Post> searchPost(String name, int startingPoint, int limit) {
        TypedQuery<Post> query = em.createQuery("SELECT e FROM Post e WHERE e.userAccount.name LIKE ?1 ORDER By e.postDate DESC ", Post.class);
        return query.setParameter(1, "%" + name + "%").setFirstResult(startingPoint).setMaxResults(limit).getResultList();
    }

    public long likePost(long index) {
        return em.find(Post.class, index).increaseLikeCount();
    }

    public long likePost(Post post) {
        return post.increaseLikeCount();
    }

    public long getLikeCount(long index) {
        return em.find(Post.class, index).getLikeCount();
    }

}
