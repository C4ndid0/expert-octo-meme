import { Zero } from '@/types';
import axios from 'axios';

export const axiosInstace = axios.create({
    baseURL: 'http://localhost:8080'
});

export class UserService {
    listAll() {
        return axiosInstace.get('/user');
    }

    insert(user: Zero.User) {
        return axiosInstace.post('/user', user);
    }

    update(user: Zero.User) {
        return axiosInstace.put('/user', user);
    }

    delete(id: number) {
        return axiosInstace.delete('/user/' + id);
    }
}
