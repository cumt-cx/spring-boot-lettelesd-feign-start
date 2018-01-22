package com.littlesd.demo.feign.github;

import javax.ws.rs.*;

@Path("/")
public interface GitHubClientAPI {

    @GET
    @Path("/users/{userName}")
    GitHubUser getUser(@PathParam("userName") String userName);

}
