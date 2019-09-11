angular.module("supplier-component").controller("SupplierController", ['$scope', '$rootScope', '$mdDialog', '$mdToast', 'SupplierService', ($scope, $rootScope, $mdDialog, $mdToast, SupplierService) => {

    $scope.findAll = () => {
        SupplierService.findAll().then((response) => {
            $scope.suppliers = response.data;
        }, (response) => {
            $scope.showSimpleToast(response.data.message);
        });
    };

    $scope.insert = (supplier) => {
        SupplierService.insert(supplier).then((response) => {
            $scope.findAll();
        }, (response) => {
            $scope.showSimpleToast(response.data.message);
        });
    
    };

    $scope.update = (id, supplier) => {
        SupplierService.update(id, supplier).then((response) => {
            $scope.findAll();
        }, (response) => {
            $scope.showSimpleToast(response.data.message);
        })
    };

    $scope.delete = (supplier) => {
        SupplierService.delete(supplier.id).then((response) => {
            $scope.findAll();
        }, (response) => {
            $scope.showSimpleToast(response.data.message);
        });
    };

    $scope.showInsertDialog = (ev) => {
        $mdDialog.show({
            targetEvent: ev,
            templateUrl: "../views/supplier.form.html",
            controller: ($scope, $rootScope, $mdDialog) => {
                $scope.closeDialog = (supplier) => {
                    $rootScope.$emit("SupplierInsert", supplier);
                    $mdDialog.hide();
                }
            }
            });
    };

    $scope.showUpdateDialog = (ev, supplier) => {
        $mdDialog.show({
            targetEvent: ev,
            templateUrl: "../views/supplier.form.html",
            locals: {
                supplier: supplier
            },
            controller: ($scope, $rootScope, $mdDialog, supplier) => {
                $scope.supplier = supplier;
                $scope.closeDialog = (supplier) => {
                    $rootScope.$emit("SupplierUpdate", supplier);
                    $mdDialog.hide();
                }
            }
            });
    };
        
    $rootScope.$on("SupplierInsert", (event, supplier) => {
        $scope.insert(supplier);
    });

    $rootScope.$on("SupplierUpdate", (event, supplier) =>{
        $scope.update(supplier.id, supplier);
    });

    $scope.showSimpleToast = (message) => {
        $mdToast.show($mdToast.simple().textContent(message).hideDelay(3000));
    };
    
    $scope.findAll();

}]);