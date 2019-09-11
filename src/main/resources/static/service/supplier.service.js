angular
    .module("supplier-component")
    .service('SupplierService', ['$http', function ($http) {

        this.findAll = () => {
            return $http({
                method: 'GET',
                url: 'suppliers'
            });
        };

        this.insert = (supplier) => {
            return $http({
                method: 'POST',
                url: 'suppliers',
                data: supplier
            });
        };

        this.findById = (id) => {
            return $http({
                method: 'GET',
                url: 'suppliers/' + id
            });
        };
        
        this.update = (id, supplier) => {
            return $http({
                method: 'PUT',
                url: 'suppliers/' + id,
                data: supplier
            })
        };

        this.delete = (id) => {
            return $http({
                method: 'DELETE',
                url: 'suppliers/' + id
            })
        };

    }]);