<template>
    <div>
        <div>
            <select name="" id="">
                <option :value="city.code" v-for="city in cities" :key="city.code">{{city.title}}</option>
            </select>
        </div>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>제품번호</th>
                        <th>제품명</th>
                        <th>가격</th>
                        <th>주문수량</th>
                        <th>합계</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="drink in drinkList" :key="drink.drinkId">
                        <td>{{drink.drinkId}}</td>
                        <td>{{drink.drinkName}}</td>
                        <td>{{drink.price}}</td>
                        <td><input type="number" v-model="drink.qty"></td>
                        <td>{{drink.price * drink.qty}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>
<script>
/* eslint-disable */
export default {
    components: {},
    data() {
        return {
            cities: [
                {title: '서울', code: '02'},
                {title: '부산', code: '051'},
                {title: '대구', code: '064'}
            ],
            drinkList: [
                {
                    drinkId: '1',
                    drinkName: '코카콜라',
                    price: 700,
                    qty: 1
                },
                {
                    drinkId: '2',
                    drinkName: '사이다',
                    price: 800,
                    qty: 1
                },
                {
                    drinkId: '3',
                    drinkName: '밀키스',
                    price: 900,
                    qty: 1
                },
                {
                    drinkId: '4',
                    drinkName: 'OJ',
                    price: 1000,
                    qty: 1
                },
            ]
        }
    },
    setup() {},
    created() {},
    mounted() {},
    unmounted() {},
    methods: {}
}
</script>