function addReturnMoney() {
    var url = ctx + "/inner/returnMoney/forwardEdit";
    layer.open({
        type: 2,
        title: '新增合同',
        shadeClose: false,
        shade: 0.8,
        area: ['800px', '400px'],
        content: url
    });
}


function isNotBlank(args) {
    var result = false;
    if (args != "" && args != null && args != undefined) {
        result = true;
    }

    return result;
}


function viewDetail(id) {
    var url = ctx + "/inner/returnMoney/detail?id=" + id;
    var title = "回款详情";
    layer.open({
        type: 2,
        title: title,
        shadeClose: false,
        shade: 0.8,
        area: ['80%', '100%'],
        content: url
    });
}

$(function () {
    initDataTable();
});

var CONSTANT = {
    // datatables常量
    DATA_TABLES: {
        DEFAULT_OPTION: { // DataTables初始化选项
            LANGUAGE: {
                sProcessing: "数据加载中...",
                sLengthMenu: "显示 _MENU_ 项结果",
                sZeroRecords: "没有匹配结果",
                sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                sInfoEmpty: "显示第 0 至 0 项结果，共 0 项",
                sInfoFiltered: "(由 _MAX_ 项结果过滤)",
                sInfoPostFix: "",
                sSearch: "本页搜索:",
                sUrl: "",
                sEmptyTable: "抱歉，没有查询到数据",
                sLoadingRecords: "载入中...",
                sInfoThousands: ",",
                oPaginate: {
                    sFirst: "首页",
                    sPrevious: "上页",
                    sNext: "下页",
                    sLast: "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            // 禁用自动调整列宽
            autoWidth: false,
            // 为奇偶行加上样式，兼容不支持CSS伪类的场合
            stripeClasses: ["odd", "even"],
            // 取消默认排序查询,否则复选框一列会出现小箭头
            order: [],
            // 隐藏加载提示,自行处理
            processing: false,
            // 启用服务器端分页
            serverSide: true,
            // 禁用原生搜索
            searching: false
        },
        COLUMN: {
            // 复选框单元格
            CHECKBOX: {
                className: "td-checkbox",
                orderable: false,
                bSortable: false,
                data: "id",
                render: function (data, type, row, meta) {
                    var content = '<input type="checkbox"  value="' + data + '" />';

                    return content;
                }
            }
        },
        // 常用render可以抽取出来，如日期时间、头像等
        RENDER: {
            ELLIPSIS: function (data, type, row, meta) {
                data = data || "";
                return '<span title="' + data + '">' + data + '</span>';
            }
        }
    }


};

function refreshTable() {
    var table = $('#myDataTable').dataTable();
    table.fnDraw(false);
}


function initDataTable() {
    $("#myDataTable").dataTable({
        ajax: {
            type: "GET",
            url: ctx + '/inner/returnMoney/listAjax',
            // 传入已封装的参数
            data: function (data) {
                data.page = data.start / data.length + 1;
                data.size = data.length;
                // 右上角搜索
                delete data.columns;
            },
            dataType: "json",
            dataSrc: function (result) {
                // 后台不实现过滤功能，每次查询均视作全部结果
                result.recordsTotal = result.count;
                result.recordsFiltered = result.count || 0;
                result.data = result.items || {};
                delete result.count;
                delete result.items;
                return result.data;
            }
        },
        "columns": [
            { data: 'code',
                render: function(data, type, returnMoney){
                    return redenerCode('1', returnMoney.code);
                }
            },
            {data: 'code'},
            {data: 'code'},
            {data: 'code'},
            {data: 'code'},
            {data: 'code'},
            {data: 'code'},
            {data: 'code'},
            {data: 'code'},
            {data: 'code'}

        ],
        "bProcessing": true, //DataTables载入数据时，是否显示‘进度’提示
        "bPaginate": true,
        "serverSide": true,
        "searching": false,
        "bFilter": true,
        "sPaginationType": "full_numbers",
        "paging": true,
        "lengthChange": true,
        "info": true,
        "autoWidth": true,
        "bSort": false,
        "oLanguage": CONSTANT.DATA_TABLES.DEFAULT_OPTION.LANGUAGE

    });
}

function redenerCode(id, code){
    var str = "<a href=\"#\" onclick=\"viewDetail('" + id + "');\" style=\"color:#009688;\">" + code + "</a>";
    return str;
 }
