// src/components/NonAcademicDetails.js
import React from 'react';
import './NonAcad.css'
import Navbar from './Navbar';

const NonAcad = () => {
  const schoolDetails = {
    house: "RED",
    clubs: [
      { name: "Music Club", description: "A club for music lovers.",
        img: "https://images.pexels.com/photos/8192085/pexels-photo-8192085.jpeg?auto=compress&cs=tinysrgb&w=600"
       },
      { name: "Basketball Club", description: "For students interested in sports and athletics.",
        img: "https://images.pexels.com/photos/945471/pexels-photo-945471.jpeg?auto=compress&cs=tinysrgb&w=600"
       },
    ],
    badges: [
      { name: "Prefect", description: "Acad. year 2021-2022" },
      { name: "House Captain", description: "Acad year 2022-2023" },
    ],
    staff: [
    { role: "Principal", name: "Preeti Sinha", contact: "123-456-7890" },
    { role: "Class Teacher", name: "Rajeev Jain", contact: "123-456-7890" },
      { role: "Driver", name: "John Smith", contact: "123-456-7890" },
      { role: "Conductor", name: "Jane Doe", contact: "987-654-3210" },
      { role: "Cleaning Staff", name: "Sam Johnson", contact: "555-666-7777" },
    ],
  };

  return (
    <div>
        <Navbar/>
    <div className="non-academic-details-container">
      {/* Header Section */}
      <section className="non-academic-header">
        <h1>Non-Academic Details</h1>
        <p>Details about our school houses, clubs, badges, and staff.</p>
      </section>

      {/* School Houses Section */}
      <section className="school-houses">
        <h2>MEMBER OF THE {schoolDetails.house} HOUSE</h2>
      </section>

      {/* Clubs Section */}
      <section className="clubs-section">
        <h2>CLUBS</h2>
        <div className="clubs-list">
          {schoolDetails.clubs.map((club, index) => (
            <div key={index} className="club-card">
            <div>
              <h3>{club.name}</h3>
              <p>{club.description}</p>
            </div>
            <img src={club.img}></img>
            </div>
          ))}
        </div>
      </section>

      {/* Badges Section */}
      <section className="badges-section">
        <h2>BADGES</h2>
        <div className="badges-list">
          {schoolDetails.badges.map((badge, index) => (
            <div key={index} className="badge-card">
              <h3>{badge.name}</h3>
              <p>{badge.description}</p>
            </div>
          ))}
        </div>
      </section>

      {/* Staff Section */}
      <section className="clubs-section">
        <h2>STAFF INFORMATION</h2>
        <table className="academic-table">
          <thead>
            <tr>
              <th>Staff</th>
              <th>Name</th>
              <th>Contact</th>
            </tr>
          </thead>
          <tbody>
            {schoolDetails.staff.map((staff, index) => (
              <tr key={index}>
                <td>{staff.role}</td>
                <td>{staff.name}</td>
                <td>{staff.contact}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>
      
    </div>

    </div>
  );
};



export default NonAcad;

