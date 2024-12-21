import React, { useState, useEffect } from 'react';
import './TransportWorker.css';
import Navbar from './Navbar';

const TransportWorker = () => {
  const [workerName, setWorkerName] = useState('');
  const [vehicleAssigned, setVehicleAssigned] = useState('');
  const [selectedAction, setSelectedAction] = useState('');
  const [locationLink, setLocationLink] = useState('');
  const [sosLocation, setSosLocation] = useState('');
  const [sosReason, setSosReason] = useState('');
  const [feedbackMessage, setFeedbackMessage] = useState('');

  // Assuming JWT token is stored in localStorage or cookies
  const jwtToken = localStorage.getItem('jwt'); // Modify as per your app's auth mechanism

  // Fetch worker details on component mount
  useEffect(() => {
    if (!jwtToken) {
      setFeedbackMessage('Authentication required. Please log in.');
      return;
    }

    // Fetch worker details
    const fetchWorkerDetails = async () => {
      try {
        const response = await fetch('http://localhost:5454/api/users/profile', {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${jwtToken}`,
          },
        });

        if (response.ok) {
          const data = await response.json();
          setWorkerName(data.name); // Assuming the response contains a "name" field
          setVehicleAssigned(data.assignedVehicle.vehicleId); // Modify based on actual response structure
        } else {
          const errorText = await response.text();
          setFeedbackMessage(`Failed to fetch worker details: ${errorText}`);
        }
      } catch (err) {
        setFeedbackMessage(`Error: ${err.message}`);
      }
    };

    fetchWorkerDetails();
  }, [jwtToken]);

  const handleActionSelect = (action) => {
    setSelectedAction(selectedAction === action ? '' : action);
    setFeedbackMessage(''); // Clear feedback on dropdown change
  };

  const handleShareLocation = async () => {
    if (!locationLink) {
      setFeedbackMessage('Please enter a valid location link.');
      return;
    }

    try {
      const response = await fetch('http://localhost:5454/api/transport_worker/send_mail', {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ location:locationLink }),
      });

      if (response.ok) {
        setFeedbackMessage('Location shared successfully!');
        setLocationLink(''); // Clear input after success
      } else {
        const errorText = await response.text();
        setFeedbackMessage(`Failed to share location: ${errorText}`);
      }
    } catch (err) {
      setFeedbackMessage(`Error: ${err.message}`);
    }
  };

  const handleSendSOS = async () => {
    if (!sosLocation || !sosReason) {
      setFeedbackMessage('Please enter both location and reason for SOS.');
      return;
    }

    try {
      const response = await fetch('http://localhost:5454/api/transport_worker/send_SOS', {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${jwtToken}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          location: sosLocation,  // Ensure the backend expects these fields
          reason: sosReason,
        }),
      });

      if (response.ok) {
        setFeedbackMessage('SOS signal sent successfully!');
        setSosLocation(''); // Clear input after success
        setSosReason('');
      } else {
        const errorText = await response.text();
        setFeedbackMessage(`Failed to send SOS: ${errorText}`);
      }
    } catch (err) {
      setFeedbackMessage(`Error: ${err.message}`);
   }
  };

  return (
    <div>
        <Navbar/>
    <div className="transport-worker-container">
      <section className="transport-header">
        <h1>Transport Worker Details</h1>
        <h2>{workerName}</h2>
        <p>Assigned Vehicle: {vehicleAssigned}</p>
      </section>

      <section className="transport-actions">
        <h3>Actions</h3>
        <div className="dropdown-container">
          <button
            className={`dropdown-button ${selectedAction === 'share-location' ? 'active' : ''}`}
            onClick={() => handleActionSelect('share-location')}
          >
            Share Live Location
          </button>
          {selectedAction === 'share-location' && (
            <div className="dropdown-content">
              <label htmlFor="locationLink">Location Link:</label>
              <input
                type="text"
                id="locationLink"
                value={locationLink}
                onChange={(e) => setLocationLink(e.target.value)}
                placeholder="Enter the location link"
              />
              <button className="action-button share-button" onClick={handleShareLocation}>
                Share Location
              </button>
            </div>
          )}
        </div>

        <div className="dropdown-container">
          <button
            className={`dropdown-button ${selectedAction === 'send-sos' ? 'active' : ''}`}
            onClick={() => handleActionSelect('send-sos')}
          >
            Send SOS
          </button>
          {selectedAction === 'send-sos' && (
            <div className="dropdown-content">
              <label htmlFor="sosLocation">Location Link:</label>
              <input
                type="text"
                id="sosLocation"
                value={sosLocation}
                onChange={(e) => setSosLocation(e.target.value)}
                placeholder="Enter the location link"
              />
              <label htmlFor="sosReason">Reason:</label>
              <input
                type="text"
                id="sosReason"
                value={sosReason}
                onChange={(e) => setSosReason(e.target.value)}
                placeholder="Enter the reason"
              />
              <button className="action-button sos-button" onClick={handleSendSOS}>
                Send SOS
              </button>
            </div>
          )}
        </div>

        {feedbackMessage && <p className="feedback-message">{feedbackMessage}</p>}
      </section>
    </div>
    </div>
  );
};

export default TransportWorker;
