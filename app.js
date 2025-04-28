const baseUrl = "http://localhost:8080";  // Ensure the correct backend URL

// Add Student
function addStudent() {
    const id = document.getElementById('id').value;
    const name = document.getElementById('name').value;
    const age = document.getElementById('age').value;
    const course = document.getElementById('course').value;

    const student = { id, name, age, course };

    fetch(`${baseUrl}/student/add`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(student)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to add student');
        }
        return response.json();
    })
    .then(data => {
        console.log('Student added:', data);
        alert('Student added successfully');
        viewStudents(); // Refresh the list after adding a student
    })
    .catch(error => {
        console.error('Error adding student:', error);
    });
}

// Show Add Student Form
function showAddStudentForm() {
    const form = document.querySelector('form');
    form.style.display = form.style.display === 'none' ? 'block' : 'none';
}

// View All Students
function viewStudents() {
    fetch(`${baseUrl}/student`)
        .then(response => response.json())
        .then(data => {
            if (Array.isArray(data)) {
                const tableBody = document.getElementById('students-table').getElementsByTagName('tbody')[0];
                tableBody.innerHTML = ""; // Clear previous data
                data.forEach(student => {
                    const row = tableBody.insertRow();
                    row.innerHTML = `
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.age}</td>
                        <td>${student.course}</td>
                        <td>
                            <button onclick="editStudent(${student.id})">Edit</button>
                            <button onclick="deleteStudent(${student.id})">Delete</button>
                        </td>
                    `;
                });
            } else {
                console.error('Expected an array, but got:', data);
            }
        })
    .catch(error => console.error('Error fetching students:', error));
}


// Function to fetch and display the student details for editing
function editStudent(id) {
    fetch(`${baseUrl}/student/${id}`) // <-- here you use fetch with id
        .then(response => {
            if (!response.ok) {
                throw new Error('Something went wrong');
            }
            return response.json();
        })
        .then(student => {
            document.getElementById('id').value = student.id;
            document.getElementById('name').value = student.name;
            document.getElementById('age').value = student.age;
            document.getElementById('course').value = student.course;
            // Show the form if hidden
            showAddStudentForm();
        })
        .catch(error => console.error('Error fetching student by ID:', error));
}


function searchStudentById() {
    const id = document.getElementById('searchId').value;
    if (!id) {
        alert('Please enter an ID to search.');
        return;
    }

    fetch(`${baseUrl}/student/${id}`)
        .then(response => {
            if (!response.ok) {
                if (response.status === 404) {
                    throw new Error('Student not found');
                } else {
                    throw new Error('Something went wrong');
                }
            }
            return response.json();
        })
        .then(student => {
            alert(`Student Found:\nID: ${student.id}\nName: ${student.name}\nAge: ${student.age}\nCourse: ${student.course}`);
        })
        .catch(error => {
            console.error('Error fetching student by ID:', error);
            alert(error.message); // Proper alert
        });
}



// Delete Student
function deleteStudent(id) {
    fetch(`${baseUrl}/student/${id}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            alert('Student Deleted Successfully');
            viewStudents(); // Refresh list
        } else {
            alert('Failed to delete student');
        }
    })
    .catch(error => console.error('Error deleting student:', error));
}


// Save Data to File
function saveToFile() {
    fetch(`${baseUrl}/student/save`, {
        method: 'POST'
    })
    .then(response => {
        if (response.ok) {
            alert('Data saved successfully');
        } else {
            alert('Failed to save data');
        }
    })
    .catch(error => console.error('Error saving data:', error));
}

// Load Data from File
function loadFromFile() {
    fetch(`${baseUrl}/student/load`)
    .then(response => response.json())
    .then(() => {
        alert('Data loaded successfully');
        viewStudents(); // Refresh the list after loading
    })
    .catch(error => console.error('Error loading data:', error));
}

// Add button click event
document.getElementById('addStudentBtn').addEventListener('click', addStudent);

// Load students when page loads
// document.addEventListener('DOMContentLoaded', viewStudents);
// When "View Students" button is clicked
document.getElementById('viewStudentsBtn').addEventListener('click', viewStudents);

