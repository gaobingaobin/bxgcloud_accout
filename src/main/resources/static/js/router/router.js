/**
 * Created by yanlin on 2014/5/23.
 */
'use strict';
var myApp = angular.module('myApp', ['ui.router', 'ui.bootstrap']);

myApp.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider

    // HOME STATES AND NESTED VIEWS ========================================
        .state('home', {
            url: '/home',
            templateUrl: 'home/operator_home.html'
        })

        // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
        .state('purchase_in', {
            url: '/purchase_in',
            templateUrl: 'entry/purchase/warehouse_operator_purchase_in.html'
        })

        .state('detach_entry', {
            url: '/detach_entry',
            templateUrl: 'entry/detach/warehouse_operator_detach_entry.html'
        })

        .state('borrow_entry', {
            url: '/borrow_entry',
            templateUrl: 'entry/borrow/warehouse_operator_borrow_entry.html'
        })

        .state('return_entry', {
            url: '/return_entry',
            templateUrl: 'entry/return/warehouse_operator_return_entry.html'
        })

        .state('borrow_out', {
            url: '/borrow_out',
            templateUrl: 'out/borrow/warehouse_operator_borrow_out.html'
        })

        .state('return_out', {
            url: '/return_out',
            templateUrl: 'out/return/warehouse_operator_return_out.html'
        })

        .state('lend_out', {
            url: '/lend_out',
            templateUrl: 'out/lend/warehouse_operator_lend_out.html'
        })

        .state('sale_out', {
            url: '/sale_out',
            templateUrl: 'out/sale/warehouse_operator_sale_out.html'
        })

        .state('discard_out', {
            url: '/discard_out',
            templateUrl: 'out/discard/warehouse_operator_discard_out.html'
        })

        .state('quality_assurance_repair', {
            url: '/quality_assurance_repair',
            templateUrl: 'others/qualityAssuranceRepair/warehouse_operator_quality_assurance_repair.html'
        })

        .state('entrust_repair', {
            url: '/entrust_repair',
            templateUrl: 'others/entrustRepair/warehouse_operator_entrust_repair.html'
        })

        .state('borrow_spare_parts', {
            url: '/borrow_spare_parts',
            //templateUrl:'home/engineer_borrow_return.html'
            templateUrl: 'apply/borrow_spare_parts.html'
        })

        .state('return_spare_parts', {
            url: '/return_spare_parts',
            //templateUrl:'home/engineer_borrow_return.html'
            templateUrl: 'return/return_spare_parts.html'
        })

        .state('spare_parts_allocate', {
            url: '/spare_parts_allocate',
            templateUrl: 'allocate/warehouse_operator_allocate.html'
        })

        .state('barcode_manage', {
            url: '/barcode_manage',
            templateUrl: 'basedata/barcode/warehouse_barcode_manage.html'
        })

        .state('check', {
            url: '/check',
            templateUrl: 'check/warehouse_check_manage.html'
        })
        .state('check_entry', {
            url: '/check_entry',
            templateUrl: 'check/entry/warehouse_check_entry_record.html'
        })
        .state('check_out', {
            url: '/check_out',
            templateUrl: 'check/out/warehouse_check_out_record.html'
        })
        .state('roleManager', {
            url: '/roleManager',
            templateUrl: 'role/toListPage'
        })
        .state('employeeManager', {
            url: '/employeeManager',
            templateUrl: 'employee/toListPage'
        })
    ;
});

var ModalDemoCtrl = function ($scope, $modal, $log) {

    $scope.items = ['item1', 'item2', 'item3'];

    $scope.open = function (template) {

        var modalInstance = $modal.open({
            templateUrl: template,
            controller: ModalInstanceCtrl,
            backdrop: 'static',
            size: 'lg',
            resolve: {
                items: function () {
                    return $scope.items;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $scope.selected = selectedItem;
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };
};

// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $modal service used above.

var ModalInstanceCtrl = function ($scope, $modalInstance, items) {

    $scope.items = items;
    $scope.selected = {
        item: $scope.items[0]
    };

    $scope.ok = function () {
        alert("alert");
        $modalInstance.close($scope.selected.item);

    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};