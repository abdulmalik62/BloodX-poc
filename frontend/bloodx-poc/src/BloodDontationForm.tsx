// src/components/BloodDonationForm.tsx
import React, { useState } from 'react';
import {Modal, Button} from 'react-bootstrap';
import './Form.css'; // If you still want to use any custom CSS

interface FormData {
  patientName: string;
  ageGroup: string;
  bloodGroup: string;
  gender: string;
  phoneNumber: string;
  location: string;
  unit: string;
  condition: string;
}

const BloodDonationForm: React.FC = () => {
  const [formData, setFormData] = useState<FormData>({
    patientName: '',
    ageGroup: '',
    bloodGroup: '',
    gender: '',
    phoneNumber: '',
    location: '',
    unit: '',
    condition: '',
  });

  const [showModal, setShowModal] = useState(false);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log(formData);
    setShowModal(true);
  };

  const handleClose = () => {
    setShowModal(false);
  };

  return (
    <>
    <form onSubmit={handleSubmit} className="max-w-lg mx-auto p-6 border border-gray-300 rounded-lg shadow-lg bg-white">
      <h1 className="text-2xl font-bold mb-4">Welcome to the Blood Donation Portal</h1>
      <div className="mb-4">
        <label htmlFor="patientName" className="block text-gray-700 font-bold mb-2">Patient Name:</label>
        <input
          type="text"
          id="patientName"
          name="patientName"
          value={formData.patientName}
          onChange={handleChange}
          maxLength={30}
          pattern="[A-Za-z]+"
          title="Only alphabetical letters are allowed"
          required
          className="w-full px-3 py-2 border border-gray-300 rounded-lg"
        />
      </div>
      <div className="mb-4">
        <label htmlFor="ageGroup" className="block text-gray-700 font-bold mb-2">Age Group:</label>
        <select
          id="ageGroup"
          name="ageGroup"
          value={formData.ageGroup}
          onChange={handleChange}
          required
          className="w-full px-3 py-2 border border-gray-300 rounded-lg"
        >
          <option value="">Select Age Group</option>
          <option value="Child">Child</option>
          <option value="Adult">Adult</option>
          <option value="Senior">Senior</option>
        </select>
      </div>
      <div className="mb-4">
        <label htmlFor="bloodGroup" className="block text-gray-700 font-bold mb-2">Blood Group:</label>
        <select
          id="bloodGroup"
          name="bloodGroup"
          value={formData.bloodGroup}
          onChange={handleChange}
          required
          className="w-full px-3 py-2 border border-gray-300 rounded-lg"
        >
          <option value="">Select Blood Group</option>
          <option value="A+">A+</option>
          <option value="A-">A-</option>
          <option value="B+">B+</option>
          <option value="B-">B-</option>
          <option value="AB+">AB+</option>
          <option value="AB-">AB-</option>
          <option value="O+">O+</option>
          <option value="O-">O-</option>
        </select>
      </div>
      <div className="mb-4">
        <label htmlFor="gender" className="block text-gray-700 font-bold mb-2">Gender:</label>
        <select
          id="gender"
          name="gender"
          value={formData.gender}
          onChange={handleChange}
          required
          className="w-full px-3 py-2 border border-gray-300 rounded-lg"
        >
          <option value="">Select Gender</option>
          <option value="Male">Male</option>
          <option value="Female">Female</option>
          <option value="Other">Other</option>
        </select>
      </div>
      <div className="mb-4">
        <label htmlFor="phoneNumber" className="block text-gray-700 font-bold mb-2">Phone Number:</label>
        <input
          type="tel"
          id="phoneNumber"
          name="phoneNumber"
          value={formData.phoneNumber}
          onChange={handleChange}
          pattern="[0-9]{10}"
          title="Enter a valid 10-digit phone number"
          required
          className="w-full px-3 py-2 border border-gray-300 rounded-lg"
        />
      </div>
      <div className="mb-4">
        <label htmlFor="location" className="block text-gray-700 font-bold mb-2">Location:</label>
        <input
          type="text"
          id="location"
          name="location"
          value={formData.location}
          onChange={handleChange}
          required
          className="w-full px-3 py-2 border border-gray-300 rounded-lg"
        />
      </div>
      <div className="mb-4">
        <label htmlFor="unit" className="block text-gray-700 font-bold mb-2">Unit:</label>s
        <select
          id="unit"
          name="unit"
          value={formData.unit}
          onChange={handleChange}
          required
          className="w-full px-3 py-2 border border-gray-300 rounded-lg"
        >
          <option value="">Select Unit</option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
        </select>
      </div>
      <div className="mb-4">
        <label htmlFor="condition" className="block text-gray-700 font-bold mb-2">Condition/Reason for Blood:</label>
        <textarea
          id="condition"
          name="condition"
          value={formData.condition}
          onChange={handleChange}
          rows={4}
          required
          className="w-full px-3 py-2 border border-gray-300 rounded-lg"
        />
      </div>
      <button type="submit" className="w-full bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-700">
        Submit
      </button>
      <div className="mt-6 p-4 bg-gray-100 border-l-4 border-blue-500">
        <h3 className="text-lg font-bold">Thank You Note</h3>
        <p>
          Thank you for your generosity and willingness to donate blood. Your contribution is invaluable and will help save lives. 
          We, along with the recipients, are extremely grateful for your selflessness and support.
        </p>
      </div>
    </form>

    <Modal show = {showModal} onHide={handleClose} centered>
      <Modal.Header closeButton className="modal-header-centered">
        <Modal.Title className="modal-title-centered"> Submission Successful</Modal.Title>
        
      </Modal.Header>
      <Modal.Body className="modal-body-centered"> Your form has been successfully submitted !</Modal.Body>
      <Modal.Footer className="modal-footer-centered"> 
        <Button variant="primary" onClick={handleClose}>Close</Button>
      </Modal.Footer>
    </Modal>
    </>
  );
};

export default BloodDonationForm;
