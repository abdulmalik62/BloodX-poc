import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './LoginPage.css';


const LoginPage: React.FC = () => {
  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const navigate = useNavigate();

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    // Handle login logic here
    console.log('Email:', email);
    console.log('Password:', password);
    // Assume login is successful
    navigate('/home');
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <div className="login-box w-full max-w-md p-8 bg-white shadow-md rounded-md">
        <div className="flex flex-col items-center mb-6">
          <h1 className="text-3xl">Blood<span className="mr-4xl font-semibold text-red-500">X</span></h1>
        </div>
        <form onSubmit={handleLogin}>
          <div className="mb-4">
            <label htmlFor="email" className="block text-sm font-medium text-gray-700">
              Mobile number
            </label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              className="w-full px-3 py-2 border border-gray-300 rounded mt-1 focus:outline-none focus:ring-2 focus:ring-red-400"
            />
          </div>
          <div className="mb-6">
            <label htmlFor="password" className="block text-sm font-medium text-gray-700">
              Password
            </label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              className="w-full px-3 py-2 border border-gray-300 rounded mt-1 focus:outline-none focus:ring-2 focus:ring-red-400"
            />
          </div>
          <button
            type="submit"
            className="w-full py-2 bg-red-500 text-white rounded hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-400"
          >
            Sign-In
          </button>
        </form>
        <p className="text-xs text-gray-600 mt-4">
          By continuing, you agree to the Blood Donation site's Conditions of Use and Privacy Notice.
        </p>
        <button className="w-full py-2 mt-4 bg-gray-200 text-gray-700 rounded hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-gray-400">
          Create your Blood Donation account
        </button>
      </div>
    </div>
  );
};

export default LoginPage;
