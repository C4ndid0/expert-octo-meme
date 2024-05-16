import { BaseService } from './baseService';

export class ProfileService extends BaseService {
    constructor() {
        super('/profile');
    }
}
