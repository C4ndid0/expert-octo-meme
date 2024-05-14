import { BaseService } from './baseService';

export class ResourceService extends BaseService {
    constructor() {
        super('/resource');
    }
}
