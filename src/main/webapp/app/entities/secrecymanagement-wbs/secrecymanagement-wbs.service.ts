import axios from 'axios';

import { type ISecrecymanagementWbs } from '@/shared/model/secrecymanagement-wbs.model';

const baseApiUrl = 'api/secrecymanagement-wbs';

export default class SecrecymanagementWbsService {
  public find(id: string): Promise<ISecrecymanagementWbs> {
    return new Promise<ISecrecymanagementWbs>((resolve, reject) => {
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

  public create(entity: ISecrecymanagementWbs): Promise<ISecrecymanagementWbs> {
    return new Promise<ISecrecymanagementWbs>((resolve, reject) => {
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

  public update(entity: ISecrecymanagementWbs): Promise<ISecrecymanagementWbs> {
    return new Promise<ISecrecymanagementWbs>((resolve, reject) => {
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

  public partialUpdate(entity: ISecrecymanagementWbs): Promise<ISecrecymanagementWbs> {
    return new Promise<ISecrecymanagementWbs>((resolve, reject) => {
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
