import React from "react";
import CreditService from "../../../api/Credit/CreditService";
import UserService from "../../../api/User/UserService";

class CreditApplyPage extends React.Component{

    constructor(props){
        super(props)

        this.state = {
            userName:"",
            userSurname:"",
            userId:"",
            userPhone:"",
            userSalary:"",
            userAge:"",
            userDateOfBirth:"",
            userGuaranteeAmount:"",
            userGuaranteed:"",
        }
        this.handlerChange = this.handlerChange.bind(this);
        this.handleResponse = this.handleResponse.bind(this);
    }
 
    handlerChange(event) {     

        this.setState({ [event.target.name]: event.target.value })
    }

    handleFormSubmit = (e) => {

        // console.log("name:" + this.state.userName);
        // console.log("surname:" + this.state.userName);
        // console.log("user id: " + this.state.userId);
        // console.log("phone number: " + this.state.userPhone);
        // console.log("salary: " + this.state.userSalary);
        // console.log("Date of Birth: " + this.state.userDateOfBirth);
        // console.log("Guarantee: " + this.state.userGuaranteeAmount);

        let userDate = new Date(this.state.userDateOfBirth).getFullYear();
        this.state.userAge = new Date().getFullYear() - userDate;
         
        let checkBox = document.getElementById('gFeeCBox');
        if(checkBox.checked)
            this.state.userGuaranteed = 1;
        else
            this.state.userGuaranteed = 0;
        e.preventDefault();
        UserService.RegisterUser(this.state)
        .then(CreditService.CreditCreateNew(this.state))
        .then(response => this.handleResponse(response))
        .catch(error => this.handleError(error));  
    }

    handleResponse(response) {

    }

    handleError(error) {
        console.log(error.response)
    }

    onCheckBoxChange(){

        let checkBox = document.getElementById('gFeeCBox');
        if (checkBox.checked)
            document.getElementById('gFeeInput').disabled = false;
        else
            document.getElementById('gFeeInput').disabled = true;
        
        console.log("checkbox change triggered");
    }

    render(){
        return(
            <>
            <div>
                <h1>Credit Apply Page!</h1>
                <form onSubmit={this.handleFormSubmit}>
                    <div className="form-group row col-md-4 offset-md-4">
                        <label>Name</label>
                        <input name="userName" type="text" className="form-control" required={true} onChange={this.handlerChange} placeholder="Enter name"></input>
                    </div>
                    <div className="form-group row col-md-4 offset-md-4">
                        <label>Surname</label>
                        <input name="userSurname" type="text" className="form-control" required={true} onChange={this.handlerChange} placeholder="Enter surname"></input>
                    </div>
                    <div className="form-group row col-md-4 offset-md-4">
                        <label>Identity Number</label>
                        <input name="userId" type="text" className="form-control" required={true} onChange={this.handlerChange} placeholder="Enter user identity number"></input>
                    </div>
                    <div className="form-group row col-md-4 offset-md-4">
                        <label>Phone Number</label>
                        <input name="userPhone" type="tel" className="form-control" required={true} onChange={this.handlerChange} placeholder="+90..."></input>
                    </div>
                    <div className="form-group row col-md-4 offset-md-4">
                        <label>Salary</label>
                        <input name="userSalary" type="number"className="form-control" required={true} onChange={this.handlerChange} placeholder="Enter monthly salary"></input>        
                    </div>
                    <div className="form-group row col-md-4 offset-md-4">
                        <label>Date of Birth</label>
                        <input name="userDateOfBirth" type="date"className="form-control" required={true} onChange={this.handlerChange}></input>        
                    </div>
                    <div className="form-group row col-md-4 offset-md-4">
                        <label>Guarantee</label>
                        <div className="custom-control custom-checkbox" style={{height:"60px"}}>
                            <input type="checkbox" className="custom-control-input" id="gFeeCBox" onClick={this.onCheckBoxChange} style={{float:"left", marginTop:"17px"}} required={false}/>
                            <a>
                                <label className="custom-control-label" htmlFor="gFeeCBox" style={{float:"left", marginTop:"9px", marginLeft:"15px"}}>Add Guarantee Fee</label>
                                <input name="userGuaranteeAmount" id="gFeeInput" style={{marginTop:"10px", width:"280px"}} placeholder="Enter amount" required={false} onChange={this.handlerChange} disabled></input>
                            </a>
                        </div>
                    </div>             
                    <input type="submit" className="btn btn-primary btn-block" value="Submit" style={{color: "white"}}/>   
                </form>  
            </div>
            </>
        )
    }
}

export default CreditApplyPage;