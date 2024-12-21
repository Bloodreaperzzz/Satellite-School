import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom"; // Import useParams to access URL params
import "./StudentForm.css";
import Navbar from "../Navbar/Navbar";

const StudentForm = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState(""); // To display messages after form submission
  const { aadharNumber } = useParams(); // Access Aadhaar number from URL
  const navigate = useNavigate(); // Initialize useNavigate hook

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!username || !password) {
      setMessage("Please fill in both fields.");
      return;
    }

    try {
      const token = localStorage.getItem("jwt"); // Get JWT token from localStorage

      const response = await fetch(`http://localhost:5454/api/founder_manager/admit-student/${aadharNumber}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`, // Add JWT token in Authorization header
        },
        body: JSON.stringify({ username, password }), // Send username and password in body
      });

      if (!response.ok) {
        throw new Error("Error submitting form!");
      }

      const data = await response.json();
      setMessage(`Form submitted successfully! Username: ${data.username}`);

      // Optional: Redirect to a confirmation page or reset form
      navigate("/founder/students"); // You can adjust this route to fit your app's flow
    } catch (error) {
      setMessage("Error submitting form.");
      console.error("Error:", error);
    }

    // Reset the form after submission
    setUsername("");
    setPassword("");
  };

  return (
    <div>
        <Navbar/>
    <div className="student-form-container">
      <h2>Student Admission Form</h2>
      <form onSubmit={handleSubmit}>
        <div className="input-group">
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Enter username"
          />
        </div>

        <div className="input-group">
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Enter password"
          />
        </div>

        <button type="submit">Submit</button>
      </form>

      {message && <p className="form-message">{message}</p>}
    </div>
    </div>
  );
};

export default StudentForm;
