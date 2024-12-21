import React from "react";
import "./styles.css";
import { useNavigate } from "react-router-dom";
import Navbar from "../Navbar/Navbar";

const HomePage = () => {

const Navigate = useNavigate();
    
  return (
    <div>
        <Navbar/>
    <div className="home-container">      
      <h1>Welcome, Founder</h1>
      <p>Select a section to manage admission requests:</p>
      <div className="home-buttons">
        <button onClick={() => Navigate("/founder/students")}>Student Requests</button>
        <button onClick={() => Navigate("/founder/teachers")}>Teacher Requests</button>
      </div>
    </div>
    </div>
  );
};

export default HomePage;