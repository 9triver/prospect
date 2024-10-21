import axios from 'axios';

import { type IProjectwbs } from '@/shared/model/projectwbs.model';

const baseApiUrl = 'api/projectwbs';

export default class ProjectwbsService {
  public find(id: string): Promise<IProjectwbs> {
    return new Promise<IProjectwbs>((resolve, reject) => {
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

  public retrieveTree(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {

          // 转换函数：将扁平数据转换为树状结构
          const convertToTree = (flatData: IProjectwbs[]) => {
            // 使用一个Map来存储每个节点的引用
            const map = new Map<number, IProjectwbs>();
            flatData.forEach(item => {
              map.set(item.id, { ...item, children: [] });
            });
            // 构建树形结构
            const tree: IProjectwbs[] = [];
            flatData.forEach(item => {
              const node = map.get(item.id);
              if (item.parentwbsid && map.has(item.parentwbsid)) {
                const parent = map.get(item.parentwbsid);
                if (parent) {
                  parent.children.push(node!);
                } else {
                  tree.push(node!); // 如果没有找到父节点，将其作为顶层节点
                }
              } else {
                tree.push(node!); // 没有父节点的节点作为顶层节点
              }
            });
            return tree;
          };

          //拼接树状结构
          const treeData = convertToTree(res.data);
          res.data = treeData;

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

  public create(entity: IProjectwbs): Promise<IProjectwbs> {
    return new Promise<IProjectwbs>((resolve, reject) => {
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

  public update(entity: IProjectwbs): Promise<IProjectwbs> {
    return new Promise<IProjectwbs>((resolve, reject) => {
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

  public partialUpdate(entity: IProjectwbs): Promise<IProjectwbs> {
    return new Promise<IProjectwbs>((resolve, reject) => {
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
