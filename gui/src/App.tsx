import React from 'react';
import './App.css';

import {
  BrowserRouter as Router,
  Route,
  Routes
} from 'react-router-dom';
import Main from "./page/Main";
import SignPanel from "./page/SignPanel";

function App() {
  return (
      <Router>
   <Routes>
       <Route path="/" element={<SignPanel/>}/>
       <Route path="/logged-in" element={<Main/>}/>
   </Routes>
      </Router>
  );
}

export default App;
