import React, { useState } from "react";
import "./AddGuardian.css";
import Navbar from "./Navbar";

const AddGuardian = () => {
  // Individual state variables for each field
  const [aadhaar, setAadhaar] = useState("");
  const [name, setName] = useState("");
  const [profession, setProfession] = useState("");
  const [relationship, setRelationship] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem("jwt");
    if (!token) {
      alert("You are not logged in. Please log in to continue.");
      return;
    }
    // Create an object with the current form data
    const guardianData = {
      aadhaar,
      name,
      profession,
      relationship,
      email,
      phone,
    };


    try {
      console.log("Submitting guardian data:", guardianData);
      // Make a POST request to your backend API
      const response = await fetch(
        "http://localhost:5454/api/student/addguardian",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`, // Tell the backend we are sending JSON
          },
          body: JSON.stringify(guardianData), // Send the form data as a JSON string
        }
      );


      if (response.status===201) {
        // Handle success response
        const result = await response.text(); // If the response is in JSON format
        console.log("Guardian Data Submitted:", result);
        alert("Guardian added successfully!");
      } else {
        // Handle failure response
    
        const error = await response.text();
        alert("Error adding guardian: " + error);
      }

      // Reset form after submission (optional)
      setAadhaar("");
      setName("");
      setProfession("");
      setRelationship("");
      setEmail("");
      setPhone("");
    } catch (error) {
      // Handle network or other errors
      console.error("Error", error);
      alert("An error occurred: " + error.message);
    }
  };

  // Return JSX for rendering
  return (
    <div>
      <Navbar/>
    <div className="add-guardian-container">
      <h1>ADD GUARDIAN</h1>
      <form onSubmit={handleSubmit} className="add-guardian-form">
        <div className="form-group">
          <label htmlFor="aadhaar">Aadhar</label>
          <input
            type="text"
            id="aadhaar"
            value={aadhaar}
            onChange={(e) => setAadhaar(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="name">Name</label>
          <input
            type="text"
            id="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="profession">Profession</label>
          <input
            type="text"
            id="profession"
            value={profession}
            onChange={(e) => setProfession(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="relationship">Relationship</label>
          <input
            type="text"
            id="relationship"
            value={relationship}
            onChange={(e) => setRelationship(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="phone">Phone Number</label>
          <input
            type="text"
            id="phone"
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
            required
          />
        </div>

        <button type="submit" className="submit-button">
          Add Guardian
        </button>
      </form>
    </div>
    </div>
  );
};

export default AddGuardian;
