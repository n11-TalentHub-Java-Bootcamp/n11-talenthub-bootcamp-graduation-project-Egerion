import { Route, Routes } from 'react-router-dom';
import './App.css';
import React from 'react';
import Menu from './component/menu/Menu';
import FetchUserList from './component/user/userlist/FetchUserList';
import UserList from './component/user/table/UserList';

class App extends React.Component {
  render() {

    console.log("main App called");

    return (
      <div className="App">
        <Menu></Menu>
        <UserList></UserList>
      </div>
    );
  }

}

export default App;
