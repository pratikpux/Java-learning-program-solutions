import React, { useState } from 'react';

const App = () => {
  const [counter, setCounter] = useState(0);
  const [message, setMessage] = useState('');
  const [amount, setAmount] = useState('');
  const [currency, setCurrency] = useState('');
  const [convertedAmount, setConvertedAmount] = useState('');

  // Increment function
  const increment = () => {
    setCounter(counter + 1);
    setMessage('Hello followed by a static message.');
  };

  // Decrement function
  const decrement = () => {
    setCounter(counter - 1);
    setMessage('Hello followed by a static message.');
  };

  // Say Welcome function
  const sayWelcome = (welcomeText) => {
    alert(`${welcomeText}`);
  };

  // OnPress synthetic event
  const handleOnPress = () => {
    alert('I was clicked!');
  };

  // Currency Converter - Handle Submit
  const handleSubmit = () => {
    if (amount && currency === 'INR') {
      // Convert INR to Euro (1 EUR = 90 INR approximately)
      const euroAmount = (parseFloat(amount) / 90).toFixed(2);
      setConvertedAmount(`${amount} INR = ${euroAmount} EUR`);
    } else {
      setConvertedAmount('Please enter amount and select INR currency');
    }
  };

  return (
    <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      <h1>Event Examples App</h1>
      
      {/* Section 1: Increment/Decrement Counter */}
      <div style={{ marginBottom: '30px', padding: '20px', border: '1px solid #ccc', borderRadius: '5px' }}>
        <h2>Counter: {counter}</h2>
        <button 
          onClick={increment}
          style={{ 
            padding: '10px 20px', 
            margin: '5px', 
            backgroundColor: '#4CAF50', 
            color: 'white', 
            border: 'none', 
            borderRadius: '3px',
            cursor: 'pointer'
          }}
        >
          Increment
        </button>
        <button 
          onClick={decrement}
          style={{ 
            padding: '10px 20px', 
            margin: '5px', 
            backgroundColor: '#f44336', 
            color: 'white', 
            border: 'none', 
            borderRadius: '3px',
            cursor: 'pointer'
          }}
        >
          Decrement
        </button>
        {message && <p style={{ color: '#666', marginTop: '10px' }}>{message}</p>}
      </div>

      {/* Section 2: Say Welcome Button */}
      <div style={{ marginBottom: '30px', padding: '20px', border: '1px solid #ccc', borderRadius: '5px' }}>
        <h2>Welcome Function</h2>
        <button 
          onClick={() => sayWelcome('welcome')}
          style={{ 
            padding: '10px 20px', 
            margin: '5px', 
            backgroundColor: '#2196F3', 
            color: 'white', 
            border: 'none', 
            borderRadius: '3px',
            cursor: 'pointer'
          }}
        >
          Say Welcome
        </button>
      </div>

      {/* Section 3: OnPress Synthetic Event */}
      <div style={{ marginBottom: '30px', padding: '20px', border: '1px solid #ccc', borderRadius: '5px' }}>
        <h2>Synthetic Event Example</h2>
        <button 
          onClick={handleOnPress}
          style={{ 
            padding: '10px 20px', 
            margin: '5px', 
            backgroundColor: '#FF9800', 
            color: 'white', 
            border: 'none', 
            borderRadius: '3px',
            cursor: 'pointer'
          }}
        >
          Click Me (OnPress Event)
        </button>
      </div>

      {/* Section 4: Currency Converter */}
      <div style={{ padding: '20px', border: '1px solid #ccc', borderRadius: '5px' }}>
        <h2 style={{ color: '#4CAF50' }}>Currency Converter!!!</h2>
        <div>
          <div style={{ marginBottom: '15px' }}>
            <label style={{ display: 'block', marginBottom: '5px' }}>Amount:</label>
            <input
              type="number"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              style={{
                padding: '8px',
                width: '200px',
                border: '1px solid #ccc',
                borderRadius: '3px'
              }}
              placeholder="Enter amount"
            />
          </div>
          
          <div style={{ marginBottom: '15px' }}>
            <label style={{ display: 'block', marginBottom: '5px' }}>Currency:</label>
            <select
              value={currency}
              onChange={(e) => setCurrency(e.target.value)}
              style={{
                padding: '8px',
                width: '215px',
                border: '1px solid #ccc',
                borderRadius: '3px'
              }}
            >
              <option value="">Select Currency</option>
              <option value="INR">INR (Indian Rupees)</option>
            </select>
          </div>
          
          <button
            onClick={handleSubmit}
            style={{
              padding: '10px 20px',
              backgroundColor: '#2196F3',
              color: 'white',
              border: 'none',
              borderRadius: '3px',
              cursor: 'pointer'
            }}
          >
            Convert
          </button>
        </div>
        
        {convertedAmount && (
          <div style={{ 
            marginTop: '15px', 
            padding: '10px', 
            backgroundColor: '#f0f0f0', 
            borderRadius: '3px' 
          }}>
            <strong>Result: {convertedAmount}</strong>
          </div>
        )}
      </div>
    </div>
  );
};

export default App;