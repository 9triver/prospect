import axios from 'axios';

import { type IProjectHumanresourcesplan } from '@/shared/model/project-humanresourcesplan.model';

const baseApiUrl = 'api/project-humanresourcesplans';

export default class ProjectHumanresourcesplanService {
  public find(id: string): Promise<IProjectHumanresourcesplan> {
    return new Promise<IProjectHumanresourcesplan>((resolve, reject) => {
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

  public create(entity: IProjectHumanresourcesplan): Promise<IProjectHumanresourcesplan> {
    return new Promise<IProjectHumanresourcesplan>((resolve, reject) => {
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

  public update(entity: IProjectHumanresourcesplan): Promise<IProjectHumanresourcesplan> {
    return new Promise<IProjectHumanresourcesplan>((resolve, reject) => {
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

  public partialUpdate(entity: IProjectHumanresourcesplan): Promise<IProjectHumanresourcesplan> {
    return new Promise<IProjectHumanresourcesplan>((resolve, reject) => {
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
