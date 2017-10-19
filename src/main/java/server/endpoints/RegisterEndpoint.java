package server.endpoints;

import com.google.gson.Gson;
import server.controllers.StudentController;
import server.models.Student;
import server.providers.StudentTable;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


@Path("/register")
public class RegisterEndpoint {

    StudentController studentController = new StudentController();
    StudentTable studentTable = new StudentTable();

    @POST
    @Produces("Application/json")
    public Response register(String jsonStudent) throws Exception {

        Gson gson = new Gson();
        Student student;
        try {
            student = gson.fromJson(jsonStudent, Student.class);
        } catch (IllegalArgumentException e) {
            System.out.print(e.getMessage());
            return Response.status(400).entity("første try i createStudent virker ikke").build();
        }

        try {
            studentController.verifyStudentCreation(student.getFirstName(), student.getLastName(), student.getPassword(), student.getEmail());
        } catch (IllegalArgumentException ee) {
            System.out.print(ee.getMessage());
            //Bør måske ændres til at user ikke kunne verifies pga forkert info om fornavn, efternavn, kodeord eller email
            return Response.status(400).entity("andet try i createStudent virker ikke").build();
        }
        try {
            studentTable.addStudent(student);
        } catch (SQLException e) {
            return Response.status(501).type("text/plain").entity("The user already exits").build();
        }

        return Response.status(200).entity("{message\":\"Success! Student created\"}").build();
    }
}
