package com.talking.faces.entity;

import com.talking.faces.entity.Post;
import com.talking.faces.entity.UserAccount;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-22T07:33:11")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, Post> post;
    public static volatile SingularAttribute<Comment, UserAccount> userAccount;
    public static volatile SingularAttribute<Comment, Date> commentDate;
    public static volatile SingularAttribute<Comment, String> comment;
    public static volatile SingularAttribute<Comment, Long> id;

}