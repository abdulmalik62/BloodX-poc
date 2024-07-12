// src/HomePage.tsx
import React from 'react';
import BloodRequestForm from './BloodRequestForm';
import BloodDelivery from './PatientDataPage';
import Navbar from './Navbar';


const HomePage: React.FC = () => {
  return (
    <div className="flex">

      <div className="flex-1 min-h-screen bg-gray-100">
        <Navbar username="Mahalakshmi" />
        <div className="p-8">
          <h1 className="text-4xl font-bold mb-4"></h1>
          <p className="text-lg"></p>
        </div>
        <div>
          {<BloodRequestForm/>}
          {<BloodDelivery/>}
        
        </div>
      </div>
    </div>
    
  );
};

export default HomePage;
