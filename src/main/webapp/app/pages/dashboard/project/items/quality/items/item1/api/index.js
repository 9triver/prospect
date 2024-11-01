export async function getDataSource() {
    let dataSource = [
        {
            name:'拟制中',
            value: 30
        },
        {
            name:'已确认',
            value: 28
        },
        {
            name:'待回报',
            value: 26
        },
        {
            name:'已回报',
            value: 24
        },
        {
            name:'已驳回',
            value: 22
        },
        {
            name:'已完成',
            value: 20
        }
    ]
    return dataSource
}