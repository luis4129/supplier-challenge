angular.module("supplier-component").directive('cnpj', () => {
    return {
        require: 'ngModel',
        link: (scope, element, attrs, ngModelController) => {
            attrs.$set('maxlength', 18);
            scope.$watch(attrs['ngModel'], (event) => {
                let value = element
                                .val()
                                .replace(/\D/g,"")
                                .replace(/^(\d{2})(\d)/,"$1.$2")
                                .replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3")
                                .replace(/\.(\d{3})(\d)/,".$1/$2")
                                .replace(/(\d{4})(\d)/,"$1-$2");
                
                element.val(value);
                ngModelController.$setViewValue(value);
            });
        }
    };
});