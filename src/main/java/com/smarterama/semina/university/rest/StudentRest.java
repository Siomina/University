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

import com.smarterama.semina.university.domain.Student;
import com.smarterama.semina.university.service.ServiceException;
import com.smarterama.semina.university.service.StudentService;

@Path("/students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {
    private StudentService studentService = new StudentService();
	 
    @GET
    public List<Student> findAll() throws ServiceException{
        return studentService.findAll();
	 }
    
    @GET
    @Path("/{id}")
    public Student findOne(@PathParam("id") int id) throws ServiceException{
        return studentService.findOne(id);
    }
    
    @POST
    public Student addStudent(Student student) throws ServiceException{
        return studentService.create(student);
    }
    
    @PUT
    @Path("/{id}")
    public Student updateStudent(Student student, @PathParam("id") int id) throws ServiceException{
    	if (id!=student.getId()) {
    		throw new ServiceException("Cannot update student: requested id mismatch");
    	}
        return studentService.update(student);
    }
    
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) throws ServiceException{
        studentService.delete(id);
    }
}
    
