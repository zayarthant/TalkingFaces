package com.talking.faces.entity;

import com.talking.faces.entity.UserAccount;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-22T07:33:11")
@StaticMetamodel(Post.class)
public class Post_ { 

    public static volatile SingularAttribute<Post, UserAccount> userAccount;
    public static volatile SingularAttribute<Post, Date> postDate;
    public static volatile SingularAttribute<Post, Long> likeCount;
    public static volatile SingularAttribute<Post, Long> id;
    public static volatile SingularAttribute<Post, String> url;

}