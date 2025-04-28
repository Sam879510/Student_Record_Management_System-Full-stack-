package org.Neubaitics.Student_Record_Management_System.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.Neubaitics.Student_Record_Management_System.Model.Student;
import org.Neubaitics.Student_Record_Management_System.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	 	@Autowired
	    private StudentRepo studentRepo;
	 	
	 	private List<Student> students = new ArrayList<>();

	    // ✅ Add methods below this...

	    public void saveStudents() {
	        // Simulation (for now)
	        System.out.println("Students saved to file (simulation).");
	    }

	    public List<Student> loadStudents() {
	        // Simulation (for now)
	        System.out.println("Students loaded from file (simulation).");
	        return students; // ✅ Now students list exists
	    }
	 	
//	 // Fetch a student by ID
//	    public Optional<Student> findById(Integer id) {
//	        return studentRepo.findById(id);
//	    }
	 	
	 	public Student addStudent(Student student) {
	        return studentRepo.save(student);
	    }

	    public List<Student> getAllStudents() {
	        return studentRepo.findAll();
	    }

	    public Optional<Student> findById(Integer id) {
	        for (Student s : students) {
	            if (s.getId() == id) {
	                return Optional.of(s);
	            }
	        }
	        return Optional.empty();
	    }

	    public Student updateStudent(Student student) {
	        return studentRepo.save(student);
	    }

	    public boolean deleteStudent(int id) {
	        if (studentRepo.existsById(id)) {
	            studentRepo.deleteById(id);
	            return true;
	        } else {
	            return false;
	        }
	    }

	}
