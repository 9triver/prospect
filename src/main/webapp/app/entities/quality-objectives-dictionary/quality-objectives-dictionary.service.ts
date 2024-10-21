import axios from 'axios';

import { type IQualityObjectivesDictionary } from '@/shared/model/quality-objectives-dictionary.model';

const baseApiUrl = 'api/quality-objectives-dictionaries';

export default class QualityObjectivesDictionaryService {
  public find(id: number): Promise<IQualityObjectivesDictionary> {
    return new Promise<IQualityObjectivesDictionary>((resolve, reject) => {
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

  public create(entity: IQualityObjectivesDictionary): Promise<IQualityObjectivesDictionary> {
    return new Promise<IQualityObjectivesDictionary>((resolve, reject) => {
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

  public update(entity: IQualityObjectivesDictionary): Promise<IQualityObjectivesDictionary> {
    return new Promise<IQualityObjectivesDictionary>((resolve, reject) => {
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

  public partialUpdate(entity: IQualityObjectivesDictionary): Promise<IQualityObjectivesDictionary> {
    return new Promise<IQualityObjectivesDictionary>((resolve, reject) => {
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
