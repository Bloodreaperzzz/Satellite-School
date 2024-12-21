import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate
import "./styles.css";
import Navbar from "../Navbar/Navbar";

const StudentRequests = () => {
  const [studentRequests, setStudentRequests] = useState([]); // Initialize with an empty array
  const navigate = useNavigate(); // Initialize useNavigate hook

  // Fetch data from backend
  useEffect(() => {
    const fetchStudentRequests = async () => {
      try {
        const token=localStorage.getItem('jwt');
        const response = await fetch(
          "http://localhost:5454/api/founder_manager/requested-students",
          {
            method: 'GET',
            headers: {
              'Authorization': `Bearer ${token}`, // Add your token here
            },
          }
        );
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const data = await response.json();
        console.log("Fetched Student Requests:", data); // For debugging
        setStudentRequests(Array.isArray(data) ? data : []);
      } catch (error) {
        console.error("Error fetching student requests:", error);
        setStudentRequests([]); // Reset state in case of an error
      }
    };
    

    fetchStudentRequests();
  }, []);

  const acceptStudent = (name, aadharNumber) => {
    alert(`Student request for ${name} accepted!`);
    // Redirect to StudentForm page
    navigate(`/founder/students/${aadharNumber}`);
  };

  return (
    <div>
      <Navbar />
      <div className="requests-container">
        <h2>Student Admission Requests</h2>
        {studentRequests.length === 0 ? (
          <p>No student requests available.</p>
        ) : (
          studentRequests.map((student, index) => (
            <div key={index} className="request-card">
              <p>
                <strong>Name:</strong> {student.name}
              </p>
              <p>
                <strong>Aadhaar:</strong> {student.aadharNumber}
              </p>
              <button
                onClick={() =>
                  acceptStudent(student.name, student.aadharNumber)
                }
              >
                Accept
              </button>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default StudentRequests;
