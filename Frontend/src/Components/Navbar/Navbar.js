import React from 'react';
import './Navbar.css';
import { useNavigate } from 'react-router-dom';
<style>
@import url('https://fonts.googleapis.com/css2?family=Mukta:wght@800&family=Rasa:ital,wght@0,300..700;1,300..700&display=swap');
</style>

const Navbar = () => {
    const navigate = useNavigate();
  return (
    <nav className="navbar">
      {/* School Logo and Name */}
      <div className="navbar-logo">
        <img
          src="https://tse4.mm.bing.net/th?id=OIP.W4uHE8RucHQnUapX0hof7wHaFu&pid=Api&P=0&h=180" // Replace with your actual school logo URL
          alt="School Logo"
          className="logo"
        />
        <h1 className="school-name font family = Mukta" >INVENTURE ACADEMY</h1>
      </div>

      {/* Navbar Links */}
      <div className="navbar-links">
        <ul>
            <li><a onClick={() => navigate("/")}>Home</a></li>
          <li><a onClick={() => navigate("/campuses")} >Campuses</a></li>
          <li><a onClick={() => navigate("/login")} >Login</a></li>
          <li><a >Contact Us</a></li>
          
        </ul>
      </div>
    </nav>
  );
}

export default Navbar;
