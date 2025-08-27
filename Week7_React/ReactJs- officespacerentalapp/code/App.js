import React from 'react';
import './App.css';

function App() {
  // Office space data array
  const officeSpaces = [
    {
      id: 1,
      name: "DBS",
      rent: 50000,
      address: "Chennai",
      image: "https://via.placeholder.com/300x200/cccccc/333333?text=Office+Space+1"
    },
    {
      id: 2,
      name: "Tech Hub",
      rent: 45000,
      address: "Bangalore", 
      image: "https://via.placeholder.com/300x200/cccccc/333333?text=Office+Space+2"
    },
    {
      id: 3,
      name: "Business Center",
      rent: 75000,
      address: "Mumbai",
      image: "https://via.placeholder.com/300x200/cccccc/333333?text=Office+Space+3"
    },
    {
      id: 4,
      name: "Corporate Plaza",
      rent: 35000,
      address: "Hyderabad",
      image: "https://via.placeholder.com/300x200/cccccc/333333?text=Office+Space+4"
    }
  ];

  return (
    <div className="App">
      {/* Page Heading */}
      <header className="app-header">
        <h1>Office Space , at Affordable Range</h1>
      </header>

      {/* Main Office Space Image */}
      <div className="main-image-container">
        <img 
          src="https://via.placeholder.com/400x300/e6e6e6/666666?text=Main+Office+Space"
          alt="Office Space"
          className="main-office-image"
        />
      </div>

      {/* Office Space Listings */}
      <div className="office-listings">
        {officeSpaces.map((office) => (
          <div key={office.id} className="office-card">
            <img 
              src={office.image}
              alt={`${office.name} Office`}
              className="office-image"
            />
            
            <div className="office-details">
              <h2 className="office-name">Name: {office.name}</h2>
              
              <p className={`office-rent ${office.rent < 60000 ? 'rent-low' : 'rent-high'}`}>
                Rent: Rs. {office.rent.toLocaleString()}
              </p>
              
              <p className="office-address">Address: {office.address}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;