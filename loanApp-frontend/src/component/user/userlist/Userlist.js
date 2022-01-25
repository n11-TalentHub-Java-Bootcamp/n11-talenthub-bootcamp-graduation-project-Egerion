import React from "react";

class Userlist extends React.Component{

    constructor(){
        super();
    }

    state = {
        userList:[]
    }

    componentDidMount(){
        fetch("http://localhost:8081/user/list")
        .then((response) => response.json())
        .then(userList => {
            this.setState({userList : userList})
        })
    }

    render(){

        console.log("Userlist called.");

        return(
            <Userlist userList={this.state.userList}></Userlist>
        );
    }

}

export default Userlist;