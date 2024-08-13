import axios from 'axios';

import { type IQualityObjectives } from '@/shared/model/quality-objectives.model';

type QueryParams = IQualityObjectives;
const baseApiUrl = 'api/quality-objectives';

export default class QualityObjectivesService {
  public find(id: string): Promise<IQualityObjectives> {
    return new Promise<IQualityObjectives>((resolve, reject) => {
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

  
  public query(params: QueryParams): Promise<any> {
    alert("查询参数:::"+JSON.stringify(params));
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/query`, params )
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

  public create(entity: IQualityObjectives): Promise<IQualityObjectives> {
    return new Promise<IQualityObjectives>((resolve, reject) => {
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

  public update(entity: IQualityObjectives): Promise<IQualityObjectives> {
    return new Promise<IQualityObjectives>((resolve, reject) => {
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

  public partialUpdate(entity: IQualityObjectives): Promise<IQualityObjectives> {
    return new Promise<IQualityObjectives>((resolve, reject) => {
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
