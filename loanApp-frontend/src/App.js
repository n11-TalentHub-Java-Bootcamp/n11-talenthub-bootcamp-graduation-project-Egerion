import { Route, Routes } from 'react-router-dom';
import './App.css';
import React from 'react';
import Menu from './component/menu/Menu';
import Userlist from './component/user/userlist/Userlist';

class App extends React.Component {
  render() {

    console.log("main App called");

    return (
      <div className="App">
        <h1>Hello World 2</h1>
        <Menu></Menu>
      </div>
    );
  }

}

export default App;
