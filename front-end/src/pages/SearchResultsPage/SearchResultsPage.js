import React, { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import Header from "../../components/Header/Header";
import Footer from "../../components/Footer/Footer";
import SearchBar from "../../components/SearchBar/SearchBar";
import ResultList from "../../components/ResultList/ResultList";
import "./SearchResultsPage.css";

const SearchResultsPage = () => {
  const [searchParams] = useSearchParams();
  const query = searchParams.get("q");
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(true);

 useEffect(() => {
   if (!query) return;

   setLoading(true);
   fetch(`http://localhost:8080/api/search?query=${encodeURIComponent(query)}`)
     .then((res) => res.json())
     .then((data) => {
       if (Array.isArray(data)) {
         setResults(data);
       } else {
         setResults([]);
       }
       setLoading(false);
     })
     .catch((error) => {
       console.error("Error fetching results:", error);
       setLoading(false);
     });
  }, [query]);

  return (
    <div className="search-results-container">
      <Header />
      <div className="search-bar-wrapper">
        <SearchBar />
      </div>

      <div className="results-content">
        <h1 className="results-heading">Search Results for: {query}</h1>
        {loading ? (
          <p className="loading-text">Loading...</p>
        ) : results.length > 0 ? (
          <ResultList results={results} />
        ) : (
          <p className="no-results-text">No results found.</p>
        )}
      </div>
      <Footer />
    </div>
  );
};

export default SearchResultsPage;
