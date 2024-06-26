import axios from 'axios';

import { type IOutsourcingmanagementWbs } from '@/shared/model/outsourcingmanagement-wbs.model';

const baseApiUrl = 'api/outsourcingmanagement-wbs';

export default class OutsourcingmanagementWbsService {
  public find(id: string): Promise<IOutsourcingmanagementWbs> {
    return new Promise<IOutsourcingmanagementWbs>((resolve, reject) => {
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

  public create(entity: IOutsourcingmanagementWbs): Promise<IOutsourcingmanagementWbs> {
    return new Promise<IOutsourcingmanagementWbs>((resolve, reject) => {
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

  public update(entity: IOutsourcingmanagementWbs): Promise<IOutsourcingmanagementWbs> {
    return new Promise<IOutsourcingmanagementWbs>((resolve, reject) => {
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

  public partialUpdate(entity: IOutsourcingmanagementWbs): Promise<IOutsourcingmanagementWbs> {
    return new Promise<IOutsourcingmanagementWbs>((resolve, reject) => {
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
