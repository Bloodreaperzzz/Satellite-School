import React from 'react';
import './Home.css';
import Navbar from '../Navbar/Navbar';

const Home = () => {
  return (
    <div>
    <Navbar/>
    <div className="home-container">
    
      <section className="home-banner">
        <div className="home-banner-content">
        <img src='/Images/home.jpg'></img> 
          <h2>Welcome to Inventure Academy</h2>
          <p>Empowering the next generation of leaders through quality education.</p>
        </div>
      </section>

      <section className="home-school-details">
        <h3>About Our School</h3>
        <p>CMS Schools have been a beacon of academic excellence for over 50 years. We offer a diverse and inclusive environment for students to grow and thrive. Our campuses are equipped with state-of-the-art facilities, dedicated faculty, and a strong commitment to student success.</p>
      </section>
    </div>
    </div>
  );
}

export default Home;
