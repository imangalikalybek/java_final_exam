var app = angular.module('aitu-project', []);

app.controller('ProductCtrl', function($scope, $http) {

    $scope.signin = {};
    $scope.productList = [];
    $scope.orderProductList = {};
    $scope.orderitemList = [];
    $scope.categoryName = [];

    $scope.getCategory = function () {
        $http({
            url: 'http://127.0.0.1:8081/category',
            method: "GET",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json"
            }
        })
            .then(function (response) {
                    console.log("SUCCESS");
                    console.log(response);
                    $scope.categoryName = response.data;
                },
                function (response) { // optional
                    console.log("ERROR");
                    console.log(response);
                });
    };
    $scope.getCategory();



    $scope.signIn = function (auth) {
        console.log(auth);
        $http({
            url: 'http://127.0.0.1:8081/signin',
            method: 'POST',
            data: {
                'login': auth.login,
                'password': auth.password
            },
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json"
            }
        }).then(function (response) {
            console.log("SUCCESS");
            console.log(response);
            $scope.signin = response.data;
            $scope.me();

        }, function (response) {
            console.log("ERROR");
            $scope.signin = {};
            console.log(response);
            $scope.me();
        })
    };

    $scope.customerMessage = '';
    $scope.shop = {};
    $scope.me = function () {
        $http({
            url: 'http://127.0.0.1:8081/customers/me',
            method: 'GET',
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json",
                "auth": $scope.signin.token
            }
        }).then(function (response) {
            $scope.shop = response.data;
            $scope.shopMessage = 'Hello ';
        }, function (response) {
            $scope.shop = {};
            $scope.shopMessage = 'Login or Password is incorrect!';
        })
    };


    $scope.getProductsByCategory = function (category_id) {
        $http({
            url: 'http://127.0.0.1:8081/product/cate/' + category_id,
            method: "GET",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json"
            }
        })
            .then(function (response) {
                    console.log("SUCCESS");
                    console.log(response);
                    $scope.productList = response.data;
                },
                function (response) {

                    console.log("ERROR");

                    console.log(response);
                });
    };
    $scope.getProducts = function () {
        $http({
            url: 'http://127.0.0.1:8081/product',
            method: "GET",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json"
            }
        })
            .then(function (response) {
                    console.log("SUCCESS");
                    console.log(response);
                    $scope.productList = response.data;
                },
                function (response) { // optional
                    console.log("ERROR");
                    console.log(response);
                });
    };
    $scope.removeProduct = function (product) {
        delete $scope.orderProductList[product.id];
    }

    $scope.getProducts();
    $scope.addProduct = function (product) {
        if ($scope.orderProductList[product.id] === undefined) {
            $scope.orderProductList[product.id] = {
                id: product.id,
                category_id: product.category_id,
                name: product.name,
                price: product.price,
                description: product.description,
                quantity: 0
            };
        }
        $scope.orderProductList[product.id].quantity = $scope.orderProductList[product.id].quantity + 1;
    }

    $scope.messageorder = '';
    $scope.SendOrder = function () {
        if (Object.keys($scope.signin).length === 0) {
            $scope.messageorder = 'Authorize!';
        } else {
            if (Object.keys($scope.orderProductList).length === 0) {
                $scope.messageorder = 'Empty order!';
            } else {
                $scope.messageorder = 'Your order is accepted!';
                $scope.createOrder($scope.token);
                console.log($scope.signin);
            }
        }

    }
    let reorganize = function () {
        $scope.productsID = [];
        $scope.productsQuantity = [];
        $scope.productsPrice = [];
        angular.forEach($scope.orderProductList, function (value) {
            $scope.productsID.push(value.id);
            if ($scope.productsQuantity[value.id] === undefined) {
                $scope.productsQuantity[value.id] = value.quantity;
            }
            if ($scope.productsPrice[value.id] === undefined) {
                $scope.productsPrice[value.id] = value.price;
            }
        });
    }

    $scope.createOrder = function (token) {
        $http({
            url: 'http://127.0.0.1:8081/ordder',
            method: 'POST',
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json",
                "shop_id": $scope.shop.id,
            }
        }).then(function (response) {
            $scope.token = response.data.token;
            console.log($scope.token);
            $scope.createOrderItem($scope.signin.token);
            $scope.orderProductList = {};
        }, function (response) {
            console.log(response);
        })
    }
    $scope.createOrderItem = function (token) {
        reorganize();
        $http({
            url: 'http://127.0.0.1:8081/orderItem/create',
            method: 'POST',
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json",
                "shop_id": $scope.shop.id,
                "product_id": $scope.productsID,
                "quantity": $scope.productsQuantity,
                "price": $scope.productsPrice
            }
        }).then(function (response) {
            console.log(response);
        }, function (response) {
            console.log(response);
        })
    }


    $scope.SeeOrder = function () {
        if (Object.keys($scope.signin).length === 0) {
            $scope.messageorderitem = 'Authorize!';
        } else {
            $scope.getorderLists($scope.signin.token);
        }
    }
    $scope.messageorderitem = '';

    $scope.getorderLists = function (token) {
        $http({
            url: 'http://127.0.0.1:8081/orderItem/auth',
            method: "GET",
            headers: {
                "shop_id": $scope.shop.id,
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json"
            }
        })
            .then(function (response) {
                    console.log("SUCCESS");
                    console.log(response);
                    $scope.orderitemList = response.data;
                },
                function (response) {
                    console.log("ERROR");
                    console.log(response);
                });
    };


});