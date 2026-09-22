// Temporary student data
let students = [
    {
        id: 1,
        name: "Rahul Patil",
        course: "MCA",
        email: "rahul@gmail.com",
        marks: 85
    },
    {
        id: 2,
        name: "Sneha Sharma",
        course: "BCA",
        email: "sneha@gmail.com",
        marks: 90
    }
];


// Display students when page loads
document.addEventListener("DOMContentLoaded", function () {
    loadStudents();
});


// Load students into table
function loadStudents() {

    const tableBody = document.getElementById("studentTableBody");

    tableBody.innerHTML = "";

    if (students.length === 0) {

        tableBody.innerHTML = `
            <tr>
                <td colspan="6" style="text-align:center;">
                    No Students Found
                </td>
            </tr>
        `;

        return;
    }


    students.forEach(function (student) {

        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.course}</td>
            <td>${student.email}</td>
            <td>${student.marks}</td>

            <td>

                <button
                    class="edit-btn"
                    onclick="editStudent(${student.id})">
                    Edit
                </button>

                <button
                    class="delete-btn"
                    onclick="deleteStudent(${student.id})">
                    Delete
                </button>

            </td>
        `;

        tableBody.appendChild(row);
    });
}


// Add / Update Student
document.getElementById("studentForm")
    .addEventListener("submit", function (event) {

        event.preventDefault();


        const id = document.getElementById("studentId").value;

        const name =
            document.getElementById("name").value;

        const course =
            document.getElementById("course").value;

        const email =
            document.getElementById("email").value;

        const marks =
            document.getElementById("marks").value;


        // Update existing student
        if (id !== "") {

            const student = students.find(
                student => student.id == id
            );

            if (student) {

                student.name = name;
                student.course = course;
                student.email = email;
                student.marks = Number(marks);

                alert("Student Updated Successfully");

            }

            cancelUpdate();

        }

        // Add new student
        else {

            const newStudent = {

                id: students.length > 0
                    ? students[students.length - 1].id + 1
                    : 1,

                name: name,
                course: course,
                email: email,
                marks: Number(marks)
            };


            students.push(newStudent);

            alert("Student Added Successfully");

            document.getElementById("studentForm").reset();
        }


        loadStudents();

    });


// Search Student
function searchStudent() {

    const id =
        Number(document.getElementById("searchId").value);

    if (!id) {

        alert("Please Enter Student ID");

        return;
    }


    const student = students.find(
        student => student.id === id
    );


    const tableBody =
        document.getElementById("studentTableBody");

    tableBody.innerHTML = "";


    if (student) {

        tableBody.innerHTML = `

            <tr>

                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.course}</td>
                <td>${student.email}</td>
                <td>${student.marks}</td>

                <td>

                    <button
                        class="edit-btn"
                        onclick="editStudent(${student.id})">
                        Edit
                    </button>

                    <button
                        class="delete-btn"
                        onclick="deleteStudent(${student.id})">
                        Delete
                    </button>

                </td>

            </tr>

        `;

    } else {

        tableBody.innerHTML = `

            <tr>

                <td colspan="6" style="text-align:center;">
                    Student Not Found
                </td>

            </tr>

        `;
    }
}


// Edit Student
function editStudent(id) {

    const student = students.find(
        student => student.id === id
    );


    if (!student) {
        return;
    }


    document.getElementById("studentId").value =
        student.id;

    document.getElementById("name").value =
        student.name;

    document.getElementById("course").value =
        student.course;

    document.getElementById("email").value =
        student.email;

    document.getElementById("marks").value =
        student.marks;


    document.getElementById("formTitle")
        .innerText = "Update Student";

    document.getElementById("submitBtn")
        .innerText = "Update Student";

    document.getElementById("cancelBtn")
        .style.display = "inline-block";


    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
}


// Cancel Update
function cancelUpdate() {

    document.getElementById("studentForm").reset();

    document.getElementById("studentId").value = "";

    document.getElementById("formTitle")
        .innerText = "Add Student";

    document.getElementById("submitBtn")
        .innerText = "Add Student";

    document.getElementById("cancelBtn")
        .style.display = "none";
}


// Delete Student
function deleteStudent(id) {

    const confirmDelete =
        confirm("Are you sure you want to delete this student?");


    if (!confirmDelete) {
        return;
    }


    students = students.filter(
        student => student.id !== id
    );


    alert("Student Deleted Successfully");

    loadStudents();
}