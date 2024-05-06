import axios from 'axios';

export const axiosInstace = axios.create({
    baseURL: 'http://localhost:8080'
});

export class UserService {
    listAll() {
        return axiosInstace.get('/user');
    }
}
