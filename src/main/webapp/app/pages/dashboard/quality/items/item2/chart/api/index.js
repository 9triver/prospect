export async function getDataSource() {
    let dataSource = [
        {
            name:'拟制中',
            value: 2000
        },
        {
            name:'审核中',
            value: 3000
        },
        {
            name:'已驳回',
            value: 2000
        },
        {
            name:'已审核',
            value: 3000
        },
        {
            name:'已发布',
            value: 2000
        }
    ]
    return dataSource
}