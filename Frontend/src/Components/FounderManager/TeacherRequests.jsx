import React, { useState } from "react";
import "./styles.css";
import Navbar from "../Navbar/Navbar";

const TeacherRequests = () => {
  const [teacherRequests, setTeacherRequests] = useState([
    { id: 1, name: "Mr. Adams", subject: "Math" },
    { id: 2, name: "Ms. Brown", subject: "English" },
  ]);

  const acceptTeacher = (id) => {
    setTeacherRequests((prev) => prev.filter((request) => request.id !== id));
    alert("Teacher request accepted!");
  };

  return (
    <div>
      <Navbar/>
    <div className="requests-container">
      <h2>Teacher Admission Requests</h2>
      {teacherRequests.length === 0 ? (
        <p>No teacher requests.</p>
      ) : (
        teacherRequests.map((teacher) => (
          <div key={teacher.id} className="request-card">
            <p>
              <strong>Name:</strong> {teacher.name}
            </p>
            <p>
              <strong>Subject:</strong> {teacher.subject}
            </p>
            <button onClick={() => acceptTeacher(teacher.id)}>Accept</button>
          </div>
        ))
      )}
    </div>
    </div>
  );
};

export default TeacherRequests;