import axios from "axios";

class CreditService{

    CreditQueryResult(userId, DateOfBirth){

        console.log("CreditQueryResult entered");
        const url = 'http://localhost:8081/credit/' + userId + '/' + DateOfBirth;

        return axios.get(url);
    }
}

export default new CreditService();