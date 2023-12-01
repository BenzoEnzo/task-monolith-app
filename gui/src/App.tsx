import React from 'react';
import './App.css';

import {
  BrowserRouter as Router,
  Route,
  Routes
} from 'react-router-dom';
import Main from "./page/Main";
import SignPanel from "./page/SignPanel";
import Report from "./page/Report";

function App() {
  return (
      <Router>
   <Routes>
       <Route path="/" element={<SignPanel/>}/>
       <Route path="/logged-in" element={<Main/>}/>
       <Route path="/reports" element={<Report />} />
   </Routes>
      </Router>
  );
}

export default App;
