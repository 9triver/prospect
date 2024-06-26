import axios from 'axios';

import { type IFundsmanagementWbs } from '@/shared/model/fundsmanagement-wbs.model';

const baseApiUrl = 'api/fundsmanagement-wbs';

export default class FundsmanagementWbsService {
  public find(id: string): Promise<IFundsmanagementWbs> {
    return new Promise<IFundsmanagementWbs>((resolve, reject) => {
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

  public create(entity: IFundsmanagementWbs): Promise<IFundsmanagementWbs> {
    return new Promise<IFundsmanagementWbs>((resolve, reject) => {
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

  public update(entity: IFundsmanagementWbs): Promise<IFundsmanagementWbs> {
    return new Promise<IFundsmanagementWbs>((resolve, reject) => {
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

  public partialUpdate(entity: IFundsmanagementWbs): Promise<IFundsmanagementWbs> {
    return new Promise<IFundsmanagementWbs>((resolve, reject) => {
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
