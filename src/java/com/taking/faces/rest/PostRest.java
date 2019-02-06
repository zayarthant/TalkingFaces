/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taking.faces.rest;

import com.talking.faces.entity.Post;
import com.talking.faces.model.PostModel;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/post")
@Produces({"application/xml", "application/json"})
public class PostRest {

    @Inject
    private PostModel post;

    @GET
    @Path("findPost/{startingPoint}/{limit}")
    public List<Post> getList(@PathParam("startingPoint") int startingPoint, @PathParam("limit") int limit) {
        return post.findPost(startingPoint, limit);
    }

}
