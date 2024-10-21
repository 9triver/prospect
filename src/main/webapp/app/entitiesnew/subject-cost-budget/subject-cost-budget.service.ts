import axios from 'axios';

import { type ISubjectCostBudget } from '@/shared/model/subject-cost-budget.model';

const baseApiUrl = 'api/subject-cost-budgets';

export default class SubjectCostBudgetService {
  public find(id: number): Promise<ISubjectCostBudget> {
    return new Promise<ISubjectCostBudget>((resolve, reject) => {
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

  public create(entity: ISubjectCostBudget): Promise<ISubjectCostBudget> {
    return new Promise<ISubjectCostBudget>((resolve, reject) => {
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

  public update(entity: ISubjectCostBudget): Promise<ISubjectCostBudget> {
    return new Promise<ISubjectCostBudget>((resolve, reject) => {
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

  public partialUpdate(entity: ISubjectCostBudget): Promise<ISubjectCostBudget> {
    return new Promise<ISubjectCostBudget>((resolve, reject) => {
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
