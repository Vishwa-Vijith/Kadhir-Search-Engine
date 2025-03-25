import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faMagnifyingGlass,
  faArrowRight,
  faTimesCircle,
} from "@fortawesome/free-solid-svg-icons";
import "./SearchBar.css";

const SearchBar = () => {
  const [query, setQuery] = useState("");
  const navigate = useNavigate();

  const handleSearch = (e) => {
    if (e.key === "Enter" && query.trim()) {
      navigate(`/search?query=${encodeURIComponent(query)}`);
    }
  };

  return (
    <div className="kadhir-search-container">
      <FontAwesomeIcon icon={faMagnifyingGlass} className="search-icon-left" />

      <input
        type="text"
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        onKeyDown={handleSearch}
        placeholder="Search for technology updates..."
        className="kadhir-search-bar"
      />

      {query ? (
        <FontAwesomeIcon
          icon={faTimesCircle}
          className="search-icon-right cancel-icon"
          onClick={() => setQuery("")} 
        />
      ) : (
        <FontAwesomeIcon icon={faArrowRight} className="search-icon-right" />
      )}
    </div>
  );
};

export default SearchBar;
