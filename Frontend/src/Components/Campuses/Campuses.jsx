import React, { useEffect, useState } from 'react'
import './Campuses.css'
import CampusCard from './CampusCard'
import Navbar from '../Navbar/Navbar'
import axios from 'axios'




const Campuses = () => {

  const[campusList, setCampusList] = useState([]);
  const[loading, setLoading] = useState(true);
  const[error, setError] = useState(null);

  useEffect(() => {
    axios.get('http://localhost:5454/campuses')
    .then((response) => {
      setCampusList(response.data);
      setLoading(false);
    })
    .catch((error) => {
      setError("FAILED");
      setLoading(false);
    })
  }, []);

  return (
    <div>
        <Navbar/>
    <div className="campus-container">
        <div className="mains">
            <img src='/Images/campus.jpg'></img>
            <p>Visit us at our</p>
            <hr></hr>
            <h1>CAMPUSES</h1>
        </div>
    
      {loading? (
        <p>LOADING...</p>
      ) : error? (
        <p>{error}</p>
      ) : (
        <div className='background color- darkgrey px-10 flex flex-wrap items-center justify-around gap-1'>{
           campusList.map((item) => <CampusCard title={item.name} image = {item.image} add={`${item.address.street}, ${item.address.locality}, ${item.address.city}, ${item.address.zipcode}`}/>) 
        }  
        </div>
      )}
    </div>
    </div>
    
  )
}

export default Campuses