import React from 'react';

const CalculateScore = (props) => {
  return (
    <div className="score-container">
      <h2>Student Details:</h2>
      <div className="student-info">
        <div className="detail-row">
          <span className="label">Name:</span>
          <span className="value">{props.name}</span>
        </div>
        <div className="detail-row">
          <span className="label">School:</span>
          <span className="value">{props.school}</span>
        </div>
        <div className="detail-row">
          <span className="label">Total:</span>
          <span className="value">{props.total}</span>
        </div>
      </div>
    </div>
  );
};

export default CalculateScore;
