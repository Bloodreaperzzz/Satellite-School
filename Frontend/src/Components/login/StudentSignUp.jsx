import React, { useState } from 'react';
import './StudentSignUp.css';

const StudentSignup = () => {
  const [name, setName] = useState('');
  const [dob, setDob] = useState('');
  const [address, setAddress] = useState({
    street: '',
    locality: '',
    city: '',
    zipcode: '',
  });
  const [gender, setGender] = useState('');
  const [aadhaarNumber, setAadhaarNumber] = useState('');
  const [bloodGroup, setBloodGroup] = useState('');
  const [contactDetails, setContactDetails] = useState({ email: '', phone: '' });
  const [image, setImage] = useState(null);

  // Handle address changes
  const handleAddressChange = (e) => {
    const { id, value } = e.target;
    setAddress((prev) => ({ ...prev, [id]: value }));
  };

  // Handle contact details changes
  const handleContactChange = (e) => {
    const { id, value } = e.target;
    setContactDetails((prev) => ({ ...prev, [id]: value }));
  };

  // Handle image selection
  const handleImageChange = (e) => {
    const file = e.target.files[0];
    setImage(file);
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    const studentData = {
      name,
      dob,
      address,
      gender,
      aadhaarNumber,
      bloodGroup,
      contactDetails
       // In case the backend requires image as raw or Base64, further processing is needed
    };

    try {
      const response = await fetch('http://localhost:5454/auth/student/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(studentData),
      });

      if (response.status === 201) {
        alert('Registration successful!');
      } else {
        const error = await response.text();
        alert('Registration failed: ' + error);
      }
    } catch (error) {
      console.error('Error during registration:', error);
      alert('Registration failed: ' + error.message);
    }
  };

  return (
    <div>
      <div className="banner">
        <img
          src='/Images/home.jpg'
          alt="School Banner"
        />
        <div className="banner-content">
          <h2>Student Signup</h2>
          <p>Create your account to join our academy</p>
        </div>
      </div>
      <div className="signup-container">
        <div className="signup-card">
          <h3>Create New Account</h3>
          <form className="signup-form" onSubmit={handleSubmit}>
            {/* Student Information */}
            <div className="form-group">
              <label htmlFor="name">Name</label>
              <input
                type="text"
                id="name"
                placeholder="Enter your full name"
                value={name}
                onChange={(e) => setName(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="aadhar">Aadhar No</label>
              <input
                type="text"
                id="aadhaarNumber"
                placeholder="Enter your Aadhar number"
                value={aadhaarNumber}
                onChange={(e) => setAadhaarNumber(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="dob">Date of Birth</label>
              <input
                type="date"
                id="dob"
                value={dob}
                onChange={(e) => setDob(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="blood">Blood Group</label>
              <select
                id="bloodGroup"
                value={bloodGroup}
                onChange={(e) => setBloodGroup(e.target.value)}
                required
              >
                <option value="">Select Blood Group</option>
                <option value="A+">A+</option>
                <option value="A-">A-</option>
                <option value="B+">B+</option>
                <option value="B-">B-</option>
                <option value="O+">O+</option>
                <option value="O-">O-</option>
                <option value="AB+">AB+</option>
                <option value="AB-">AB-</option>
              </select>
            </div>
            <div className="form-group">
              <label>Gender</label>
              <div className="gender-options">
                <label>
                  <input
                    type="radio"
                    name="gender"
                    value="Male"
                    onChange={(e) => setGender(e.target.value)}
                  />{' '}
                  Male
                </label>
                <label>
                  <input
                    type="radio"
                    name="gender"
                    value="Female"
                    onChange={(e) => setGender(e.target.value)}
                  />{' '}
                  Female
                </label>
                <label>
                  <input
                    type="radio"
                    name="gender"
                    value="Other"
                    onChange={(e) => setGender(e.target.value)}
                  />{' '}
                  Other
                </label>
              </div>
            </div>
           

            {/* Address Section */}
            <div className="address-group">
              <label>Address</label>
              <div className="address-fields">
                <div className="form-group">
                  <label htmlFor="street">Street</label>
                  <input
                    type="text"
                    id="street"
                    placeholder="Enter your street"
                    value={address.street}
                    onChange={handleAddressChange}
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="locality">Locality</label>
                  <input
                    type="text"
                    id="locality"
                    placeholder="Enter your locality"
                    value={address.locality}
                    onChange={handleAddressChange}
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="city">City</label>
                  <input
                    type="text"
                    id="city"
                    placeholder="Enter your city"
                    value={address.city}
                    onChange={handleAddressChange}
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="zipcode">ZIP Code</label>
                  <input
                    type="text"
                    id="zipcode"
                    placeholder="Enter your ZIP Code"
                    value={address.zipcode}
                    onChange={handleAddressChange}
                  />
                </div>
              </div>
            </div>

            {/* Contact Information */}
            <div className="form-group">
              <label htmlFor="email">Email</label>
              <input
                type="email"
                id="email"
                placeholder="Enter your email"
                value={contactDetails.email}
                onChange={handleContactChange}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="phone">Phone Number</label>
              <input
                type="text"
                id="phone"
                placeholder="Enter your phone number"
                value={contactDetails.phone}
                onChange={handleContactChange}
                required
              />
            </div>
            <button type="submit" className="signup-btn">
              Sign Up
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default StudentSignup;
