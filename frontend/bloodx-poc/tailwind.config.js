// tailwind.config.js
module.exports = {
  purge: ['./src/**/*.{js,jsx,ts,tsx}', './public/index.html'],
  darkMode: 'class', // Enable dark mode via class
  theme: {
    extend: {
      colors: {
        primary: '#6200ea',
        secondary: '#03dac6',
        background: '#f5f5f5',
        text: '#212121',
      },
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
};
