import axios from 'axios';

import { type IOutsourcingmPurchasePlan } from '@/shared/model/outsourcingm-purchase-plan.model';

const baseApiUrl = 'api/outsourcingm-purchase-plans';

export default class OutsourcingmPurchasePlanService {
  public find(id: number): Promise<IOutsourcingmPurchasePlan> {
    return new Promise<IOutsourcingmPurchasePlan>((resolve, reject) => {
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

  public create(entity: IOutsourcingmPurchasePlan): Promise<IOutsourcingmPurchasePlan> {
    return new Promise<IOutsourcingmPurchasePlan>((resolve, reject) => {
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

  public update(entity: IOutsourcingmPurchasePlan): Promise<IOutsourcingmPurchasePlan> {
    return new Promise<IOutsourcingmPurchasePlan>((resolve, reject) => {
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

  public partialUpdate(entity: IOutsourcingmPurchasePlan): Promise<IOutsourcingmPurchasePlan> {
    return new Promise<IOutsourcingmPurchasePlan>((resolve, reject) => {
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
