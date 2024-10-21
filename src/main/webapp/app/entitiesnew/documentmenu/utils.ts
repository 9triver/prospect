import { type IDocumentmenuTreeNode, type IDocumentmenu } from '@/shared/model/documentmenu.model';

// 列表结构数据改为树结构数据
const convertToTree = (listData: IDocumentmenu[]) => {
    // 使用一个Map来存储每个节点的引用
    const map = new Map<string, IDocumentmenuTreeNode>();
    listData.forEach(item => {
        if (item.menuid) {
            map.set(item.menuid, { ...item, children: [] });
        }
    });

    // 构建树形结构
    const tree: IDocumentmenuTreeNode[] = [];
    listData.forEach(item => {
        if (item.menuid) {
            const node = map.get(item.menuid);
            if (item.parentmenuid && map.has(item.parentmenuid)) {
                const parent = map.get(item.parentmenuid);
                if (parent) {
                    parent.children.push(node!);
                } else {
                    tree.push(node!); // 如果没有找到父节点，将其作为顶层节点
                }
            } else {
                tree.push(node!); // 没有父节点的节点作为顶层节点
            }
        }
    });

    return tree;
};
// 树结构数据改为列表结构数据
const convertToList = (treeData: IDocumentmenuTreeNode) => {
    let data:IDocumentmenu[] = []
    const func = (_treeData: IDocumentmenuTreeNode) => {
        if (_treeData.children.length == 0) {
            data.push({
                ..._treeData
            })
        } else {
            if (_treeData.children && _treeData.children.length > 0) {
                _treeData.children.forEach(item => {
                    func(item)
                })
            }
        }
    }
    if(treeData){
        func(treeData)
      }
    return data
}


export { convertToTree, convertToList }