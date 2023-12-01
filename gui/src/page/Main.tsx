import './Base.css'
import React from "react";
import MainContent from "../dom/MainContent";
import RightBar from "../dom/RightBar";

const Main : React.FC = () =>{
    return(
       <div className="main">
           <RightBar/>
           <MainContent/>
       </div>
    );
}
export default Main;