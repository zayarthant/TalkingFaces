/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talking.faces.model;

import com.talking.faces.entity.Comment;
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
public class CommentModel {

    @PersistenceContext(unitName = "TALKING_FACESPU")
    private EntityManager em;

    public Comment createComment(UserAccount userAccount, Date commentDate, String comm, Post post) {
        Comment comment = new Comment(userAccount, commentDate, comm, post);
        em.persist(comment);
        return comment;
    }

    public Comment createComment(UserAccount userAccount, String comm, Post post) {
        Comment comment = createComment(userAccount, new Date(), comm, post);
        return comment;
    }

    public void createComment(Comment comment) {
        em.persist(comment);
    }

    public void removeComment(long index) {
        em.remove(em.find(Comment.class, index));
    }

    public void removeAllCommentOfAPost(Post post) {
        em.createQuery("DELETE FROM Comment e WHERE e.post = ?1").setParameter(1, post).executeUpdate();
    }

    public Comment findComment(long index) {
        return em.find(Comment.class, index);
    }

    public List<Comment> getCommentList(Post post, int limit) {
        TypedQuery<Comment> query = em.createQuery("SELECT e FROM Comment e Where e.post = ?1 ORDER BY e.commentDate DESC", Comment.class);
        return query.setParameter(1, post).setMaxResults(limit).getResultList();
    }

}
