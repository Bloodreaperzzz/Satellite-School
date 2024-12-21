import React from "react";
import "./TeacherSignUpPage.css";

const TeacherSignUpPage = () => {
  return (
    <div>
      {/* Banner Section */}
      <div className="banner">
        <img 
          src='/Images/home.jpg'
          alt="School Banner" 
        />
        <div className="banner-content">
          <h2>Teacher Sign Up</h2>
          <p>Fill out the form to join Inventure Academy as a teacher</p>
        </div>
      </div>

      {/* Sign Up Form */}
      <div className="signup-container">
        <div className="signup-card">
          <h3>Teacher Registration Form</h3>
          <form className="signup-form">
            <div className="form-group">
              <label htmlFor="name">Full Name</label>
              <input type="text" id="name" name="name" required />
            </div>

            <div className="form-group">
              <label htmlFor="dob">Date of Birth</label>
              <input type="date" id="dob" name="dob" required />
            </div>

            <div className="form-group">
              <label htmlFor="gender">Gender</label>
              <select id="gender" name="gender" required>
                <option value="">Select Gender</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
              </select>
            </div>

            <div className="form-group">
              <label htmlFor="bloodGroup">Blood Group</label>
              <input type="text" id="bloodGroup" name="bloodGroup" required />
            </div>

            <div className="form-group">
              <label htmlFor="aadhar">Aadhar Number</label>
              <input type="text" id="aadhar" name="aadhar" required />
            </div>

          

            <div className="form-group">
              <label htmlFor="address">Address</label>
              <textarea id="address" name="address" rows="4" required></textarea>
            </div>

            <div className="form-group">
              <label htmlFor="qualification">Qualification</label>
              <input type="text" id="qualification" name="qualification" required />
            </div>

            <div className="form-group">
              <button type="submit" className="signup-btn">Submit</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default TeacherSignUpPage;
