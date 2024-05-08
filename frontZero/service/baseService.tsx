import axios from 'axios';

export const axiosInstace = axios.create({
    baseURL: process.env.NEXT_PUBLIC_ZERO_URL_API
});

export class BaseSevice {
    url: string;

    constructor(url: string) {
        this.url = url;
    }

    listAll() {
        return axiosInstace.get(this.url);
    }

    getById(id: number) {
        return axiosInstace.get(this.url + '/' + id);
    }
    insert(object: any) {
        return axiosInstace.post(this.url, object);
    }

    update(object: any) {
        return axiosInstace.put(this.url, object);
    }

    delete(id: number) {
        return axiosInstace.delete(this.url + '/' + id);
    }
}
