import React, {useState, useEffect} from "react";
import axios from "axios";

function FetchUserList(){

    const[posts,setPosts] = useState([])

    useEffect(() =>{
        axios.get('http://localhost:8081/user/list')
        .then(response =>{
            console.log(response)
        })
        .catch(error =>{
            console.log(error)
        })
    })

    return(
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

export default FetchUserList;