import React from "react";
import "./ResultList.css";

const ResultList = ({ results }) => {
  return (
    <div className="result-list">
      {results.map((result, index) => (
        <div
          key={index}
          className="result-card"
          onClick={() => (window.location.href = result.url)}
        >
          <h2
            className="result-title"
            style={{ cursor: "pointer", color: "#007BFF" }}
          >
            {result.title}
          </h2>
          <p className="result-snippet">{result.snippet}</p>
          <p className="result-date">
            {result.publishDate} {result.publishTime}
          </p>
        </div>
      ))}
    </div>
  );
};

export default ResultList;
