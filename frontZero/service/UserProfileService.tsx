import { BaseService } from './baseService';

export class UserProfileService extends BaseService {
    constructor() {
        super('/user-profile');
    }
}
