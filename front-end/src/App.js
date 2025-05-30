import React from "react";
import HomePage from "./pages/HomePage/HomePage";
import SearchResultsPage from "./pages/SearchResultsPage/SearchResultsPage";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/search" element={<SearchResultsPage />} />
      </Routes>
    </Router>
  );
};

export default App;
