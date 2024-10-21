import axios from 'axios';

import { type IOutsourcingPurchasePlan } from '@/shared/model/outsourcing-purchase-plan.model';

const baseApiUrl = 'api/outsourcing-purchase-plans';

export default class OutsourcingPurchasePlanService {
  public find(id: string): Promise<IOutsourcingPurchasePlan> {
    return new Promise<IOutsourcingPurchasePlan>((resolve, reject) => {
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

  public create(entity: IOutsourcingPurchasePlan): Promise<IOutsourcingPurchasePlan> {
    return new Promise<IOutsourcingPurchasePlan>((resolve, reject) => {
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

  public update(entity: IOutsourcingPurchasePlan): Promise<IOutsourcingPurchasePlan> {
    return new Promise<IOutsourcingPurchasePlan>((resolve, reject) => {
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

  public partialUpdate(entity: IOutsourcingPurchasePlan): Promise<IOutsourcingPurchasePlan> {
    return new Promise<IOutsourcingPurchasePlan>((resolve, reject) => {
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
