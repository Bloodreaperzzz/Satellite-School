import React, { useState, useEffect } from 'react';
import './AcademicDetails.css';
import Navbar from './Navbar';

const AcademicDetails = () => {
  const [studentName, setStudentName] = useState(''); // State for student's name
  const [marks, setMarks] = useState([]); // State to hold marks data
  const [loading, setLoading] = useState(true); // State to handle loading status
  const [error, setError] = useState(null); // State to handle errors

  useEffect(() => {
    
    const token = localStorage.getItem('jwt'); // or get it from context
    if (!token) {
      setError('JWT token is missing');
      setLoading(false);
      return;
    }

    const fetchMarks = async () => {
      try {
        const response = await fetch('http://localhost:5454/api/student/marks', {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${token}`, // Corrected template string
          },
        });

        if (!response.ok) {
          const errorText = await response.text(); // Read error response as text for debugging
          console.error('Server error response:', errorText);
          throw new Error(`Failed to fetch marks: ${response.statusText}`);
        }

        const data = await response.json(); 
        console.log("hello");// Use response.json() to handle the JSON response
        console.log('Raw Response:', data); // Log the response for debugging

        const { marks, name } = data; // Destructure the response into student name and marks

        setStudentName(name); // Set student name
        setMarks(marks || []); // Ensure marks is always an array
      } catch (err) {
        setError(err.message); // Handle any errors
      } finally {
        setLoading(false); // Set loading to false once the request is complete
      }
    };

    fetchMarks();
  }, []); // Empty array means this effect runs once on component mount

  // If loading, show a loading message
  if (loading) {
    return <div>Loading...</div>;
  }

  // If there's an error, display the error message
  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div>
      <Navbar />
      <div className="academic-details-container">
        <section className="academic-header">
          <h1>Student Academic Details</h1>
          <h2>{studentName}</h2>
          <p>10th, 2023</p>
        </section>

        <section className="academic-info">
          <h3>Subjects and Grades</h3>
          <table className="academic-table">
            <thead>
              <tr>
                <th>Subject</th>
                <th>Grade</th>
              </tr>
            </thead>
            <tbody>
              {marks.length > 0 ? (
                marks.map((mark, index) => (
                  <tr key={index}>
                    <td>{mark.subject}</td>
                    <td>{mark.grade}</td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="2">No marks available</td>
                </tr>
              )}
            </tbody>
          </table>
        </section>

        {/* GPA and Attendance Section */}
        <section className="gpa-attendance">
          <div className="gpa">
            <h3>Annual Percentage</h3>
            <p>9.4</p>
          </div>
          <div className="attendance">
            <h3>Attendance</h3>
            <p>95%</p>
          </div>
        </section>
      </div>
    </div>
  );
};

export default AcademicDetails;
