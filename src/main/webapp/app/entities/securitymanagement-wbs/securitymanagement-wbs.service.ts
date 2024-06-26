import axios from 'axios';

import { type ISecuritymanagementWbs } from '@/shared/model/securitymanagement-wbs.model';

const baseApiUrl = 'api/securitymanagement-wbs';

export default class SecuritymanagementWbsService {
  public find(id: string): Promise<ISecuritymanagementWbs> {
    return new Promise<ISecuritymanagementWbs>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public delete(id: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: ISecuritymanagementWbs): Promise<ISecuritymanagementWbs> {
    return new Promise<ISecuritymanagementWbs>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public update(entity: ISecuritymanagementWbs): Promise<ISecuritymanagementWbs> {
    return new Promise<ISecuritymanagementWbs>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public partialUpdate(entity: ISecuritymanagementWbs): Promise<ISecuritymanagementWbs> {
    return new Promise<ISecuritymanagementWbs>((resolve, reject) => {
      axios
        .patch(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
