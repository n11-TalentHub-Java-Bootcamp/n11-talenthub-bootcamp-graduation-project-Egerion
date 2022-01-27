import { Route, Routes } from 'react-router-dom';
import './App.css';
import React from 'react';
import Menu from './component/menu/Menu';
import HomePage from './component/home/HomePage';
import UserListPage from './component/user/UserListPage';
import CreditPage from './component/credit/CreditPage';

class App extends React.Component {
  render() {

    console.log("main App called");

    return (
      <div className="App">
        <Menu></Menu>
        <Routes>
          <Route path="/" exact element={<HomePage></HomePage>}></Route>
          <Route path="/user/list" element={<UserListPage></UserListPage>}></Route>
          <Route path="/user/register" element={"TODO"}></Route>
          <Route path="/user/query" element={<CreditPage></CreditPage>}></Route>
        </Routes>
      </div>
    );
  }
}

export default App;
