import moment from 'moment'


export interface TreeNode {
    id: string;
    name: string;
    fileType: 'folder' | 'file';
    createTime: string;
    createUserId: string,
    createDepId: string,
    children?: TreeNode[];
}
export async function getTreeData(): Promise<TreeNode[]> {
    let treeData: TreeNode[] = [
        {
            id: "2121",
            name: '香烟 WiFi 啤酒',
            fileType: 'folder',
            createTime: getRandomDate(),
            createUserId: 'admin',
            createDepId: '',
            children: [
                {
                    id: "2121",
                    name: '香烟',
                    fileType: 'folder',
                    createTime: getRandomDate(),
                    createUserId: 'admin',
                    createDepId: '',
                    children: [
                        {
                            id: "2121",
                            name: '煊赫门',
                            fileType: 'file',
                            createTime: getRandomDate(),
                            createUserId: 'admin',
                            createDepId: ''
                        },
                        {
                            id: "2121",
                            name: 'ESSE双爆珠',
                            fileType: 'file',
                            createTime: getRandomDate(),
                            createUserId: 'admin',
                            createDepId: ''
                        }
                    ],
                },
                {
                    id: "2121",
                    name: '后端开发技术',
                    fileType: 'folder',
                    createTime: getRandomDate(),
                    createUserId: 'admin',
                    createDepId: '',
                    children: [
                        {
                            id: "2121",
                            name: 'Java编程技术',
                            fileType: 'file',
                            createTime: getRandomDate(),
                            createUserId: 'admin',
                            createDepId: ''
                        },
                        {
                            id: "2121",
                            name: 'Python编程技术',
                            fileType: 'file',
                            createTime: getRandomDate(),
                            createUserId: 'admin',
                            createDepId: ''
                        }
                    ],
                },
                {
                    id: "2121",
                    name: '数据库',
                    fileType: 'folder',
                    createTime: getRandomDate(),
                    createUserId: 'admin',
                    createDepId: '',
                    children: [
                        {
                            id: "2121",
                            name: '关系型数据库',
                            fileType: 'folder',
                            createTime: getRandomDate(),
                            createUserId: 'admin',
                            createDepId: '',
                            children: [
                                {
                                    id: "2121",
                                    name: 'MySQL',
                                    fileType: 'file',
                                    createTime: getRandomDate(),
                                    createUserId: 'admin',
                                    createDepId: ''
                                },
                                {
                                    id: "2121",
                                    name: 'Oracle',
                                    fileType: 'file',
                                    createTime: getRandomDate(),
                                    createUserId: 'admin',
                                    createDepId: ''
                                }
                            ],
                        },
                        {
                            id: "2121",
                            name: '非关系型数据库',
                            fileType: 'folder',
                            createTime: getRandomDate(),
                            createUserId: 'admin',
                            createDepId: '',
                            children: [
                                {
                                    id: "2121",
                                    name: 'Redis',
                                    fileType: 'file',
                                    createTime: getRandomDate(),
                                    createUserId: 'admin',
                                    createDepId: ''
                                },
                                {
                                    id: "2121",
                                    name: 'Elasticsearch',
                                    fileType: 'file',
                                    createTime: getRandomDate(),
                                    createUserId: 'admin',
                                    createDepId: ''
                                }
                            ],
                        }
                    ],
                },
                {
                    id: "2121",
                    name: 'AI人工智能',
                    fileType: 'file',
                    createTime: getRandomDate(),
                    createUserId: 'admin',
                    createDepId: ''
                },
            ],
        },
        {
            id: "2121",
            name: '火腿 iPad 泡面',
            fileType: 'file',
            createTime: getRandomDate(),
            createUserId: 'admin',
            createDepId: ''
        }
    ]
    console.log('treeData', treeData)
    return treeData
}


function getRandomDate(): string {
    // 设置两个日期范围
    const startDate = moment('2023-01-01', 'YYYY-MM-DD');
    const endDate = moment('2023-12-31', 'YYYY-MM-DD');

    // 生成随机时间
    function randomDate(start:any, end:any) {
        return start.clone().add(Math.floor(Math.random() * (end.diff(start, 'days') + 1)), 'days');
    }

    // 输出随机日期
    return randomDate(startDate, endDate).format('YYYY-MM-DD HH:mm:ss')
}