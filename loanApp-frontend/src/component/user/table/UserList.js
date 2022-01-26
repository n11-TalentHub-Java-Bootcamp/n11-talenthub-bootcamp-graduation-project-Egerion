import React, { Component } from 'react'
import axios from 'axios'

class UserList extends Component {

	constructor(props) {
		super(props)

		this.state = { posts: [], errorMsg: ''}
	}

	componentDidMount() {
		axios
			.get('http://localhost:8081/user/list')
			.then(response => {
				console.log(response)
				this.setState({ posts: response.data })
			})
			.catch(error => {
        console.log(error)
        this.setState({errorMsg: 'Error retrieving data'})
		})
	}

	render() {
		const { posts, errorMsg } = this.state
		return (
			// <div>
			// 	Lis of Users
			// 	{posts.length ? posts.map(post => <div key={post.id}>{post.name}</div>): null}
        	// 	{errorMsg ? <div>{errorMsg}</div> : null}
			// </div>
			<div>
            <ul>
                {
                posts.map(data => 
                <li key={data.id}>
                    {data.name}
                </li>)
                }
            </ul>
        </div>
		)
	}
}

export default UserList