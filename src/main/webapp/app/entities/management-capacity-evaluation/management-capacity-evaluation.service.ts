import axios from 'axios';

import { type IManagementCapacityEvaluation } from '@/shared/model/management-capacity-evaluation.model';

const baseApiUrl = 'api/management-capacity-evaluations';

export default class ManagementCapacityEvaluationService {
  public find(id: string): Promise<IManagementCapacityEvaluation> {
    return new Promise<IManagementCapacityEvaluation>((resolve, reject) => {
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

  public create(entity: IManagementCapacityEvaluation): Promise<IManagementCapacityEvaluation> {
    return new Promise<IManagementCapacityEvaluation>((resolve, reject) => {
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

  public update(entity: IManagementCapacityEvaluation): Promise<IManagementCapacityEvaluation> {
    return new Promise<IManagementCapacityEvaluation>((resolve, reject) => {
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

  public partialUpdate(entity: IManagementCapacityEvaluation): Promise<IManagementCapacityEvaluation> {
    return new Promise<IManagementCapacityEvaluation>((resolve, reject) => {
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
