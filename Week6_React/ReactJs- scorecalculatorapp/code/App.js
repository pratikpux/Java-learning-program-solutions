import React from 'react';
import CalculateScore from './components/CalculateScore';
import './stylesheets/mystyle.css';

function App() {
  return (
    <div className="app-container">
      <h1 className="app-title">Student Management Portal</h1>
      <CalculateScore 
        name="John Doe"
        school="Public School" 
        total="3000"
      />
    </div>
  );
}