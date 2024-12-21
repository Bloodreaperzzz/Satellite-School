import React from 'react';
import { useNavigate } from 'react-router-dom';
import './SignUpPage.css';

const SignupSelector = () => {
  const navigate = useNavigate();

  return (
    <div className="selector-container">
      <h1>Select Signup Type</h1>
      <div className="buttons-container">
        <button className="selector-button" onClick={() => navigate('/signup/student')}>
          Sign Up as Student
        </button>
        <button className="selector-button" onClick={() => navigate('/signup/teacher')}>
          Sign Up as Teacher
        </button>
      </div>
    </div>
  );
};

export default SignupSelector;