package com.smarterama.semina.university.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.smarterama.semina.university.domain.Group;
import com.smarterama.semina.university.service.GroupService;
import com.smarterama.semina.university.service.ServiceException;

@Path("/groups")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GroupRest {
    private GroupService groupService = new GroupService();
		 
    @GET
    public List<Group> findAll() throws ServiceException {
        return groupService.findAll();
    }
	    
    @GET
    @Path("/{id}")
    public Group findOne(@PathParam("id") int id) throws ServiceException {
        return groupService.findOne(id);
    }
	    
    @POST
    public Group addGroup(Group group) throws ServiceException {
        return groupService.create(group);
    }
	    
    @PUT
    @Path("/{id}")
    public Group updateGroup(Group group, @PathParam("id") int id) throws ServiceException{
    	if (id!=group.getId()) {
    		throw new ServiceException("Cannot update group: requested id mismatch");
    	}
        return groupService.update(group);
    }
	    
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) throws ServiceException{
    	groupService.delete(id);
    }
}
	    
