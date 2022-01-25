import React from "react";
import { Navbar, Container, Nav, NavDropdown } from 'react-bootstrap/';

class Menu extends React.Component {

    constructor(props){
        super(props)

    }

    render() {    
        return (
            <div className="col-md-6 offset-md-3 ">
            <Navbar bg="light" expand="lg">
                <Container>
                    <Navbar.Brand href="/">Loan App v1</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link href="/register">Register User</Nav.Link>
                            <Nav.Link href="/list">User List</Nav.Link>
                            <Nav.Link href="/query">Check My Credit Status</Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </div>
        );
    }
}

export default Menu;