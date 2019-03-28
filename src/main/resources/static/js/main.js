function getIndex(list,id){
    for(var i=0;i<list.length;i++){
        if(list[i].product_id === id){
            return i;
        }
    }
    return -1;
}
var productApi = Vue.resource('/product{/id}');
Vue.component('product-form',{
    props: ['products','productAtr'],
    data: function () {
        return{
            product_id:'',
        product_name: '',
        description: '',
        price: '',
        category_id: 0
        }
    },
    watch:{
        productAtr: function(newVal,oldVal){
            this.product_id=newVal.product_id;
            this.product_name=newVal.product_name;
            this.description=newVal.description;
            this.price=newVal.price;
            this.category_id=newVal.category_id;

        }
    },
    template:
    '<div>'+
        '<input type="text" placeholder="product_name" v-model="product_name" />'+
        '<input type="text" placeholder="description" v-model="description" />'+
        '<input type="text" placeholder="price" v-model="price" />'+
        '<input type="number" placeholder="category_id" v-model="category_id" />'+
        '<input type="button"  value="Save" @click="save" />'+
    '</div>',
    methods:{
        save: function () {

            var product = {

                            product_name: this.product_name,
                            description: this.description,
                            price: this.price,
                            category_id: this.category_id};
            // noinspection JSAnnotator

            if(this.product_id!=0){
                console.log(this.product_id);
                console.log(this.product_name);
                productApi.update({id: this.product_id}, product).then(response => {
                    var newProduct={
                        product_id: this.product_id,
                        product_name: response.body.product_name,
                        description: response.body.price,
                        price: this.price,
                        category_id: response.body.category_id
                    }


                        var index = getIndex(this.products, this.product_id);
                        this.products.splice(index, 1, newProduct);
                        this.product_id='',
                    this.product_name='',
                        this.description='',
                        this.price='',
                        this.category_id=0
                    }
              )


            }else{
                console.log(this.product_id);
                productApi.save({},product).then(response=>{
                   var newProduct={
                       product_id: response.body.product_id,
                       product_name: response.body.product_name,
                       description: response.body.price,
                       price: this.price,
                       category_id: response.body.category_id
                   }

                    this.products.push(newProduct);
                    this.product_name='',
                    this.description='',
                    this.price='',
                    this.category_id=0

                }
                );


                // productApi.save({},product);
                //newproduct=productApi.save({},product).then(product => this.products.push(newproduct))


                //console.log(newproduct.product_id);
               // window.location.reload();

            }
        }

    }

});
Vue.component('product-row',{
    props:['product', 'editMethod','products'],
    template: '<div><i>({{ product.product_id }})</i> {{ product.product_name }} {{product.description}} {{product.price}} {{product.category_id}}'+
    '<span>' +
        '<input type="button" value="Edit" @click="edit"/>' +
        '<input type="button" value="X" @click="del"/>' +
    '</span>'+
    '</div>',
    methods:{
        edit: function(){
            this.editMethod(this.product);
        },
        del: function () {
            productApi.remove({id: this.product.product_id}).then(result =>{
                if(result.ok){
                    this.products.splice(this.products.indexOf(this.product),1)
                }
            })
        }
    }
});
Vue.component ('products-list',{
    props: ['products'],
    data: function(){
        return{
            product:null
        }
    },

    template:
    '<div>'+
        '<product-form :products="products" :productAtr="product"/>'+
        '<product-row v-for="product in products" :key="product.product_id" :product="product" :editMethod="editMethod" :products="products"/> '+
        '</div>',
    created: function () {
        productApi.get().then(result =>
            result.json().then( data=>
                data.forEach(product => this.products.push(product))

            )
        )
    },
    methods:{
        editMethod: function (product) {
            this.product = product;
        }
    }
});
var app = new Vue({
    el: '#app',
    template: '<products-list :products="products" />',
    data: {
        products: []
    }
});
Vue.component('category-row',{
    props:['category','categories'],
    template: '<div><i>({{ category.id }})</i> {{ category.category_name }} {{ category.description}}'+
    '</div>'
});
var categoryApi = Vue.resource('/category');
Vue.component ('category-list',{
    props: ['categories'],
    data: function(){
        return{
            category:null
        }
    },
    template:
    '<div>'+
    '<category-row v-for="category in categories" :key="category.id" :category="category" :categories="categories"/> '+
    '</div>',
    created: function () {

        categoryApi.get().then(result =>
            result.json().then( data=>
                data.forEach(category => this.categories.push(category))

            )
        )
    }
});
var appCat = new Vue({
    el: '#appCat',
    template: '<category-list :categories="categories" />',
    data: {
        categories: []
    }
});