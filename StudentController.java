package org.Neubaitics.Student_Record_Management_System.Controller;

import java.util.List;
import java.util.Optional;

import org.Neubaitics.Student_Record_Management_System.Model.Student;
import org.Neubaitics.Student_Record_Management_System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

 // Endpoint for adding a student
    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student); // Assuming you have a method in service
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }
	
 // Endpoint for fetching all students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentService.getAllStudents(); // Assuming you have a method in service
        return ResponseEntity.ok(students);
    }

 // GET endpoint to fetch a student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        Optional<Student> optionalStudent = studentService.findById(id); // get Optional<Student>

        if (optionalStudent.isEmpty()) { // check if empty
            return ResponseEntity.notFound().build();
        }

        Student student = optionalStudent.get(); // get Student object
        return ResponseEntity.ok(student);
    }


    // Update existing student
	@PutMapping("/{id}")
	public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody Student student) {
	    student.setId(id); // Important to set ID
	    studentService.updateStudent(student);
	    return ResponseEntity.status(HttpStatus.OK).body("Data updated successfully");
	}


    // Delete student by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
	    boolean isDeleted = studentService.deleteStudent(id); // Now this will work
	    if (isDeleted) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Data has been deleted");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
	    }
	}
	
	@PostMapping("/save")
    public ResponseEntity<String> saveStudents() {
        studentService.saveStudents();
        return ResponseEntity.ok("Students saved successfully");
    }

    @GetMapping("/load")
    public ResponseEntity<List<Student>> loadStudents() {
        List<Student> students = studentService.loadStudents();
        return ResponseEntity.ok(students);
    }
}
