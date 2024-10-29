export async function getDataSource() {
    let dataSource = [
        {
            title: '计划总数',
            value: '220',
            icon: 'PlanTotal',
            notStart: '100',
            process: '50',
            isEnd: '40',
            overDue: '30'
        },
        {
            title: '总体级WBS',
            value: '120',
            icon: 'WBS',
            notStart: '50',
            process: '50',
            isEnd: '10',
            overDue: '10'
        },
        {
            title: '任务包',
            value: '50',
            icon: 'WorkBag',
            notStart: '10',
            process: '20',
            isEnd: '20',
            overDue: '0'
        }
    ]
    return dataSource
}