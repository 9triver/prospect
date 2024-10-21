import axios from 'axios';

import { type ICommunicationFormDictionary } from '@/shared/model/communication-form-dictionary.model';

const baseApiUrl = 'api/communication-form-dictionaries';

export default class CommunicationFormDictionaryService {
  public find(id: number): Promise<ICommunicationFormDictionary> {
    return new Promise<ICommunicationFormDictionary>((resolve, reject) => {
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

  public create(entity: ICommunicationFormDictionary): Promise<ICommunicationFormDictionary> {
    return new Promise<ICommunicationFormDictionary>((resolve, reject) => {
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

  public update(entity: ICommunicationFormDictionary): Promise<ICommunicationFormDictionary> {
    return new Promise<ICommunicationFormDictionary>((resolve, reject) => {
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

  public partialUpdate(entity: ICommunicationFormDictionary): Promise<ICommunicationFormDictionary> {
    return new Promise<ICommunicationFormDictionary>((resolve, reject) => {
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
