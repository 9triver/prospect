import axios from 'axios';

import { type IOfficers } from '@/shared/model/officers.model';
type QueryParams = IOfficers;
const baseApiUrl = 'api/officers';

export default class OfficersService {
  public find(id: string): Promise<IOfficers> {
    return new Promise<IOfficers>((resolve, reject) => {
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

  public create(entity: IOfficers): Promise<IOfficers> {
    alert("查询参数:::"+JSON.stringify(entity));
    return new Promise<IOfficers>((resolve, reject) => {
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

  public update(entity: IOfficers): Promise<IOfficers> {
    return new Promise<IOfficers>((resolve, reject) => {
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

  public partialUpdate(entity: IOfficers): Promise<IOfficers> {
    return new Promise<IOfficers>((resolve, reject) => {
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
