import React, { useState } from 'react';
import './App.css';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // Login button component
  const LoginButton = (props) => {
    return (
      <button onClick={props.onClick} className="login-btn">
        Login
      </button>
    );
  };

  // Logout button component
  const LogoutButton = (props) => {
    return (
      <button onClick={props.onClick} className="logout-btn">
        Logout
      </button>
    );
  };

  // User greeting component
  const UserGreeting = (props) => {
    const isLoggedIn = props.isLoggedIn;
    if (isLoggedIn) {
      return <UserDetails />;
    }
    return <GuestGreeting />;
  };

  // User details component (when logged in)
  const UserDetails = () => {
    return (
      <div>
        <h2>Welcome back</h2>
        <p>You can now browse flight details and book tickets.</p>
      </div>
    );
  };

  // Guest greeting component (when not logged in)
  const GuestGreeting = () => {
    return (
      <div>
        <h2>Please sign up.</h2>
        <p>Guest users can browse the page where the flight details are displayed, but only logged-in users can book tickets.</p>
      </div>
    );
  };

  // Handle login
  const handleLogin = () => {
    setIsLoggedIn(true);
  };

  // Handle logout
  const handleLogout = () => {
    setIsLoggedIn(false);
  };

  return (
    <div className="App">
      <div className="container">
        <h1>Ticket Booking App</h1>
        
        <div className="auth-section">
          {isLoggedIn ? (
            <LogoutButton onClick={handleLogout} />
          ) : (
            <LoginButton onClick={handleLogin} />
          )}
        </div>

        <div className="greeting-section">
          <UserGreeting isLoggedIn={isLoggedIn} />
        </div>

        <div className="flights-section">
          <h3>Available Flights</h3>
          
          <div className="flight-card">
            <div className="flight-info">
              <p><strong>Flight AI-101</strong></p>
              <p>Delhi → Mumbai</p>
              <p>Departure: 10:30 AM</p>
            </div>
            <div className="flight-booking">
              <p className="price">₹4,500</p>
              <button 
                className={isLoggedIn ? "book-btn" : "book-btn disabled"} 
                disabled={!isLoggedIn}
              >
                {isLoggedIn ? "Book Now" : "Login to Book"}
              </button>
            </div>
          </div>

          <div className="flight-card">
            <div className="flight-info">
              <p><strong>Flight AI-205</strong></p>
              <p>Mumbai → Bangalore</p>
              <p>Departure: 2:15 PM</p>
            </div>
            <div className="flight-booking">
              <p className="price">₹3,200</p>
              <button 
                className={isLoggedIn ? "book-btn" : "book-btn disabled"} 
                disabled={!isLoggedIn}
              >
                {isLoggedIn ? "Book Now" : "Login to Book"}
              </button>
            </div>
          </div>

          <div className="flight-card">
            <div className="flight-info">
              <p><strong>Flight AI-308</strong></p>
              <p>Bangalore → Chennai</p>
              <p>Departure: 6:45 PM</p>
            </div>
            <div className="flight-booking">
              <p className="price">₹2,800</p>
              <button 
                className={isLoggedIn ? "book-btn" : "book-btn disabled"} 
                disabled={!isLoggedIn}
              >
                {isLoggedIn ? "Book Now" : "Login to Book"}
              </button>
            </div>
          </div>
        </div>

        <div className="status">
          Status: {isLoggedIn ? 'Logged In - You can book tickets' : 'Guest User - Browse only'}
        </div>
      </div>
    </div>
  );
}

export default App;