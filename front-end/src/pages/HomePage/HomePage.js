import React from "react";
import SearchBar from "../../components/SearchBar/SearchBar";
import Footer from "../../components/Footer/Footer";
import "./HomePage.css";

const HomePage = () => {
  return (
    <div className="home-container">
      <div className="content">
        <h1 className="app-title">Kadhir Search Engine</h1>
        <SearchBar />
        <p className="attraction-text">
          Discover the latest technology trends, research papers, and news in
          one place.
        </p>
      </div>
      <Footer />
    </div>
  );
};

export default HomePage;
