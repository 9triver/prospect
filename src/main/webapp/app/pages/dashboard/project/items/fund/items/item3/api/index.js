export async function getData(){
    let dataSource = [
        {
            title: "2024-04",
            purchase: 86,//零星支付
            share: 216,//分摊费用
            work: 127,//事务费
            outSource: 23,//外包费用
        },
        {
            title: "2024-05",
            purchase: 59,
            share: 101,
            work: 185,
            outSource: 28
        },
        {
            title: "2024-06",
            purchase: 10,
            share: 175,
            work: 201,
            outSource: 29
        },
        {
            title: "2024-07",
            purchase: 250,
            share: 147,
            work: 128,
            outSource: 26
        },
        {
            title: "2024-08",
            purchase: 30,
            share: 161,
            work: 124,
            outSource: 65
        },
        {
            title: "2024-09",
            purchase: 173,
            share: 52,
            work: 64,
            outSource: 54
        }
    ];
    return dataSource
}