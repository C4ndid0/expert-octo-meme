import { BaseSevice } from './baseService';

export class UserService extends BaseSevice {
    constructor() {
        super('/user');
    }
}
