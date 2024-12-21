import React, { useState, useEffect } from 'react';
import './TeacherDashboard.css';
import Navbar from './Navbar';

const TeacherDashboard = () => {
  const [teacher, setTeacher] = useState({ name: '', qualification: '' });
  const [formData, setFormData] = useState({
    subject: '',
    grade: '',
    studentId: '', // Updated to match GradeDto field
  });
  const [errorMessage, setErrorMessage] = useState('');
  const [successMessage, setSuccessMessage] = useState('');

  // Fetch teacher details from backend
  useEffect(() => {
    const fetchTeacherDetails = async () => {
      try {
        const jwtToken = localStorage.getItem('jwt'); // Assuming JWT token is stored in localStorage
        const response = await fetch('http://localhost:5454/api/users/profile', {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${jwtToken}`,
          },
        });

        if (!response.ok) {
          throw new Error('Failed to fetch teacher details. Please check your credentials.');
        }

        const data = await response.json();
        setTeacher({ name: data.name, qualification: data.qualification });
      } catch (error) {
        setErrorMessage(error.message);
      }
    };

    fetchTeacherDetails();
  }, []);

  // Handle form input changes
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  // Handle form submission
  const handleFormSubmit = async (e) => {
    e.preventDefault();

    const { subject, grade, studentId } = formData;

    // Validation
    if (!subject || !grade || !studentId) {
      setErrorMessage('All fields are required.');
      setSuccessMessage('');
      return;
    }

    setErrorMessage('');
    setSuccessMessage('');

    try {
      const jwtToken = localStorage.getItem('jwt');
      const response = await fetch('http://localhost:5454/api/teacher/assignGrade', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${jwtToken}`,
        },
        body: JSON.stringify({
          studentId, // Updated to send studentId instead of username
          subject,
          grade,
        }),
      });

      if (!response.ok) {
        const data = await response.json();
        throw new Error(data.message || 'Failed to assign grade.');
      }

      setSuccessMessage('Grade assigned successfully!');
      setFormData({ subject: '', grade: '', studentId: '' });
    } catch (error) {
      setErrorMessage(error.message);
    }
  };

  return (
    <div>
        <Navbar/>
    <div className="teacher-dashboard-container">
      <section className="teacher-header">
        <h1>Teacher Dashboard</h1>
        <h2>{teacher.name || 'Loading...'}</h2>
        <p>{teacher.qualification || 'Fetching details...'}</p>
      </section>

      <section className="teacher-info">
        <h3>Assign Grades</h3>
        <form className="assign-grade-form" onSubmit={handleFormSubmit}>
          <div className="form-group">
            <label htmlFor="subject">Subject</label>
            <input
              type="text"
              id="subject"
              name="subject"
              value={formData.subject}
              onChange={handleInputChange}
              placeholder="Enter subject"
            />
          </div>
          <div className="form-group">
            <label htmlFor="grade">Grade</label>
            <input
              type="text"
              id="grade"
              name="grade"
              value={formData.grade}
              onChange={handleInputChange}
              placeholder="Enter grade (e.g., A, B, C)"
            />
          </div>
          <div className="form-group">
            <label htmlFor="studentId">Student ID</label> {/* Updated label */}
            <input
              type="text"
              id="studentId"
              name="studentId"
              value={formData.studentId}
              onChange={handleInputChange}
              placeholder="Enter student ID"
            />
          </div>
          {errorMessage && <p className="error-message">{errorMessage}</p>}
          {successMessage && <p className="success-message">{successMessage}</p>}
          <button type="submit" className="submit-btn">
            Assign Grade
          </button>
        </form>
      </section>
    </div>
    </div>
  );
};

export default TeacherDashboard;
