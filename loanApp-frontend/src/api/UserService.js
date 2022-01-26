import axios from "axios";

class UserService{

    getAllUsers(){
        const url = '/user/list';
        return axios.get(url);
    }
}

export default new UserService();