import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // React Router for navigation
import {jwtDecode }from 'jwt-decode';
import './Login.css';
import Navbar from '../Navbar/Navbar';

const LoginPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate(); // React Router hook for navigation

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:5454/auth/signin', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) {
        throw new Error('Invalid credentials');
      }

      const data = await response.json();
      console.log('Login successful:', data);

      // Save JWT token or user details if necessary
      const jwtToken =data.jwt
      localStorage.setItem('jwt', jwtToken);

        // Decode the JWT token to get the user's role
       const authority=data.role;
  
        
        if (authority === 'TRANSPORT_WORKER') {
          navigate('/');
          navigate('/worker') // Teacher's dashboard route
        } else if (authority === 'STUDENT')
           {
            
          navigate('/');
          navigate('/student/academic'); // Student's route
        } else if(authority === 'TEACHER')
        {
          navigate('/');
          navigate('/teacher'); // Student's route
        } else if(authority === 'FOUNDER_MANAGER') {
          navigate('/');
          navigate('/founder'); // Student's route
        }
        else {
          console.log(authority);
          navigate('/unauthorized'); // Fallback for unknown authority
        }
    } catch (error) {
      console.error('Error during login:', error);
      alert('Login failed: ' + error.message);

      // Redirect back to the login page (optional)
      navigate('/');
    }
  };

  return (
    <div>
      <Navbar/>
    <div className="login-container">
   
      <div className="login-box">
        <h2>Login</h2>

        <form onSubmit={handleLogin}>
          {/* Username input */}
          <div className="input-group">
            <label htmlFor="username">Username</label>
            <input
              type="text"
              id="username"
              placeholder="Enter your username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>

          {/* Password input */}
          <div className="input-group">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              placeholder="Enter your password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>

          {/* Submit button */}
          <button type="submit" className="login-button">
            Login
          </button>
        </form>

        {/* Additional Links */}
        <div className="login-links">
          <a href="/forgot-password">Forgot Password?</a>
          <a onClick={() => navigate("/signup")}>Create an Account</a>
        </div>
      </div>
    </div>
    </div>
  );
};

export default LoginPage;