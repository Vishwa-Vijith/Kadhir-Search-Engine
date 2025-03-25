import React from "react";
import "./Footer.css";

const Footer = () => {
  const year = new Date();
  return (
    <footer className="kadhir-footer">
      <p>
        &copy; {year.getFullYear()} KADHIR Search Engine. All rights reserved.
      </p>
    </footer>
  );
};

export default Footer;
