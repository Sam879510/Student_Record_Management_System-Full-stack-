package org.Neubaitics.Student_Record_Management_System.Repo;

import org.Neubaitics.Student_Record_Management_System.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

}
