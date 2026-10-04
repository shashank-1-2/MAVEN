package com.example;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/student")
public class StudentResource {

    private StudentRepository repo = new StudentRepository();

    // 1. GET All Students: http://localhost:8080/userapp/api/student
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudents() {
        System.out.println("Fetching all student details...");
        return repo.getStudents();
    }

    // 2. GET PathParam (By ID): http://localhost:8080/userapp/api/student/1
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") int id) {
        System.out.println("Fetching student with ID: " + id);
        Student student = repo.getStudent(id);

        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("{\"error\": \"Student with ID " + id + " not found\"}")
                           .build();
        }
        return Response.ok(student).build();
    }

    // 3. GET QueryParam (Filter): http://localhost:8080/userapp/api/student/filter?minGrade=85.0
    @GET
    @Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudentsByGrade(
            @DefaultValue("0.0") @QueryParam("minGrade") double minGrade) {
        System.out.println("Filtering students with grade >= " + minGrade);

        List<Student> filtered = new ArrayList<>();
        for (Student s : repo.getStudents()) {
            if (s.getGrade() >= minGrade) {
                filtered.add(s);
            }
        }
        return filtered;
    }

    // 4. POST Create Student: http://localhost:8080/userapp/api/student
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStudent(Student s1) {
        System.out.println("Creating student: " + s1.getName());
        repo.creat(s1);

        URI location = URI.create("/student/" + s1.getId());
        return Response.created(location).entity(s1).build();
    }

    //5. PUT Update Student : http://localhost:8080/userapp/api/student/{id}
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(Student s1) {
        System.out.println("Updating student with ID: " + s1.getId());

        boolean updated = repo.update(s1);

        if (!updated) {
            return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Student with ID " + s1.getId() + " not found to update\"}")
                        .build();
        }

        return Response.ok(s1).build();
    }
}