import axios from 'axios';

import { type IOutsourcingmPurchaseExecute } from '@/shared/model/outsourcingm-purchase-execute.model';

const baseApiUrl = 'api/outsourcingm-purchase-executes';

export default class OutsourcingmPurchaseExecuteService {
  public find(id: number): Promise<IOutsourcingmPurchaseExecute> {
    return new Promise<IOutsourcingmPurchaseExecute>((resolve, reject) => {
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

  public delete(id: number): Promise<any> {
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

  public create(entity: IOutsourcingmPurchaseExecute): Promise<IOutsourcingmPurchaseExecute> {
    return new Promise<IOutsourcingmPurchaseExecute>((resolve, reject) => {
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

  public update(entity: IOutsourcingmPurchaseExecute): Promise<IOutsourcingmPurchaseExecute> {
    return new Promise<IOutsourcingmPurchaseExecute>((resolve, reject) => {
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

  public partialUpdate(entity: IOutsourcingmPurchaseExecute): Promise<IOutsourcingmPurchaseExecute> {
    return new Promise<IOutsourcingmPurchaseExecute>((resolve, reject) => {
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
