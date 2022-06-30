package com.mycompany.david.tobrise.tech11.assignment.rest;

import com.mycompany.david.tobrise.tech11.assignment.entity.User;
import com.mycompany.david.tobrise.tech11.assignment.service.PersistenceService;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user-resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    
    @Inject
    PersistenceService persistenceService;
    
    @Path("/users")
    @GET
    public List<User> getAllUsers(){
        return persistenceService.getAllUsers();
    }
      
    @GET
    @Path("/user/{id}")
    public Response getUser(@PathParam("id") Long id, User usr){
        User user = persistenceService.findById(id);
        if(user == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        user.setId(id);
        return Response.ok(user).build();
    }
    
    @POST
    @Path("/new-user")
    public Response saveUser(User usr){
        User user = persistenceService.saveUser(usr);
        return Response.ok(user).build();
    }
  
    @PUT
    @Path("/update-user/{id}")
    public Response updateUser(@PathParam("id") Long id, User usr){
        User user = persistenceService.findById(id);
        
        if(user == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        //user.setId(usr.getId());
        user.setFirstName(usr.getFirstName());
        user.setLastName(usr.getLastName());
        user.setEmail(usr.getEmail());
        user.setBirthDate(usr.getBirthDate());
        return Response.ok(persistenceService.updateUser(user)).build();
    }
   
    @DELETE
    @Path("/delete-user/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        User user = persistenceService.findById(id);
        if(user != null){
            persistenceService.deleteUser(user);
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
        
    }
    
    
}
