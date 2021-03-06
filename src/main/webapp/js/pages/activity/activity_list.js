function addActivity() {
    var url = ctx + "/inner/activity/forwardEdit";
    layer.open({
        type: 2,
        title: '新增市场活动',
        shadeClose: true,
        shade: 0.8,
        area: ['800px', '400px'],
        content: url
    });
}


function editActivity(id) {
    var url = ctx + "/inner/activity/forwardEdit?id=" + id;
    layer.open({
        type: 2,
        title: '编辑市场活动',
        shadeClose: true,
        shade: 0.8,
        area: ['800px', '400px'],
        content: url
    });
}

function addSubCompany(id) {
    var url = ctx + "/inner/company/addSub?id=" + id;
    layer.open({
        type: 2,
        title: '添加子公司信息',
        shadeClose: true,
        shade: 0.8,
        area: ['550px', '400px'],
        content: url
    });
}
function addSuccess() {
    window.location.href = ctx + "/inner/company/list";
}

function queryCompany(page) {
    var url = ctx + "/inner/company/list?page=" + page;
    var level = $("#levelSelect").find("option:selected").val();
    if (isNotBlank(level)) {
        url += "&level=" + level;
    }


    var companyName = $("#txtCompanyName").val();
    if (isNotBlank(companyName)) {
        url += "&companyName=" + companyName;
    }

    window.location.href = url;
}
function isNotBlank(args) {
    var result = false;
    if (args != "" && args != null && args != undefined) {
        result = true;
    }

    return result;
}

function addDepartment(id) {
    var url = ctx + "/inner/department/add?id=" + id;
    layer.open({
        type: 2,
        title: '添加部门信息',
        shadeClose: true,
        shade: 0.8,
        area: ['550px', '400px'],
        content: url
    });
}

function viewDetail(id, activityName) {
    var url = ctx + "/inner/activity/detail?id=" + id;
    var title = "市场活动【" + activityName + "】";
    layer.open({
        type: 2,
        title: title,
        shadeClose: true,
        shade: 0.8,
        area: ['80%', '80%'],
        content: url
    });
}

function transfer(id, activityName) {
    var url = ctx + "/inner/user/selectAllUserActivity?id=" + id + "&name=" + activityName;
    var title = "转移市场活动【" + activityName + "】";
    layer.open({
        type: 2,
        title: title,
        shadeClose: true,
        shade: 0.8,
        area: ['500px', '400px'],
        content: url
    });
}

$(function () {
    /*$(".select2").select2();
     */

    initDataTable();
    initLocation();
    initAllCheckEvt();
});


function initAllCheckEvt() {
    $("#chkAll").change(function () {
        if ($("#chkAll").is(':checked')) {
            $("input:checkbox").each(function () {
                $(this).prop('checked', true);//

            });

        } else {
            $("input:checkbox").removeAttr("checked");
        }
    });

    $("input[type='checkbox']").on("change", function () {
        var idValue = $(this).attr("id");
        if (idValue != "chkAll") {
            if ($("#chkAll").is(':checked')) {
                var chkArray = $("input:checkbox");
                var isCheckedAll = true;
                var size = chkArray.length;
                for (var i = 0; i < size; i++) {
                    var isChkAll = chkArray[i].id;
                    if (isChkAll != "chkAll" && chkArray[i].checked) {
                        isCheckedAll = false;
                        break;
                    }
                }

                if (!isCheckedAll) {
                    $("#chkAll").removeAttr("checked");
                }
            }
        }
    })
}


function getCheckedBox() {
    var chkArr = $("input:checkbox");
    var chkedStr = "";
    $.each(chkArr, function (index, obj) {
        var attrId = $(obj).attr("id");
        if (attrId != "chkAll") {
            if ($(obj).is(':checked')) {
                chkedStr += $(obj).val() + ",";
            }
        }
    });

    if (isNotBlank(chkedStr)) {
        chkedStr = chkedStr.substring(0, chkedStr.length - 1);
    }

    return chkedStr;
}

function batchDelete() {
    var deleteIdArr = getCheckedBox();
    if (isNotBlank(deleteIdArr)) {
        layer.confirm("确定要批量删除市场活动相关信息吗？", {
            closeBtn: false,
            skin: 'layui-layer-molv'
        }, function () {
            var url = ctx + "/inner/activity/batchDeleteById?ids=" + deleteIdArr;
            $.ajax({
                type: "get",
                url: url,
                success: function (result) {
                    if (result == 2) {
                        layer.alert("批量删除市场活动成功", {
                            closeBtn: false,
                            skin: 'layui-layer-molv'
                        }, function () {
                            refreshTable();
                            layer.closeAll();
                        });
                    } else {
                        layer.alert("批量删除市场活动失败", {
                            closeBtn: false,
                            skin: 'layui-layer-molv'
                        });
                    }
                }
            });
        })
    } else {
        layer.alert("请选择需要批量删除的市场活动", {
            closeBtn: false,
            skin: 'layui-layer-molv'
        });
    }
}

function batchTransfer() {
    var idArr = getCheckedBox();
    if (isNotBlank(idArr)) {
        layer.confirm("确定要批量转移市场活动相关信息吗？", {
            closeBtn: false,
            skin: 'layui-layer-molv'
        }, function () {
            var url = ctx + "/inner/user/selectAllUserActivity?id=" + idArr;
            var title = "批量转移市场活动";
            layer.open({
                type: 2,
                title: title,
                shadeClose: true,
                shade: 0.8,
                area: ['500px', '400px'],
                content: url
            });
        })
    } else {
        layer.alert("请选择需要批量转移的市场活动", {
            closeBtn: false,
            skin: 'layui-layer-molv'
        });
    }
}

function renderActivityName(id, name) {
    var str = "<a href=\"#\" onclick=\"viewDetail('" + id + "', '" + name + "');\" style=\"color:#009688;\">" + name + "</a>";
    return str;
}

function getArea(data) {
    var area = "";
    if (isNotBlank(data.provinceName)) {
        area += data.provinceName;
    }

    if (isNotBlank(data.cityName)) {
        area += "-" + data.cityName;
    }

    if (isNotBlank(data.areaName)) {
        area += "-" + data.areaName;
    }

    return area;
}

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

function deleteById(id, name) {
    layer.confirm("确定要删除市场活动【" + name + "】相关信息吗？", {
        closeBtn: false,
        skin: 'layui-layer-molv'
    }, function () {
        var url = ctx + "/inner/activity/deleteById?id=" + id + "&name=" + name;
        $.ajax({
            type: "get",
            url: url,
            success: function (result) {
                if (result == 2) {
                    layer.alert("删除市场活动成功", {
                        closeBtn: false,
                        skin: 'layui-layer-molv'
                    }, function () {
                        refreshTable();
                        layer.closeAll();
                    });
                } else {
                    layer.alert("删除市场活动失败", {
                        closeBtn: false,
                        skin: 'layui-layer-molv'
                    });
                }
            }
        });
    })
}

function deleteById(id, name) {
    layer.confirm("确定要删除市场活动【" + name + "】相关信息吗？", {
        closeBtn: false,
        skin: 'layui-layer-molv'
    }, function () {
        var url = ctx + "/inner/activity/deleteById?id=" + id + "&name=" + name;
        $.ajax({
            type: "get",
            url: url,
            success: function (result) {
                if (result == 2) {
                    layer.alert("删除市场活动成功", {
                        closeBtn: false,
                        skin: 'layui-layer-molv'
                    }, function () {
                        refreshTable();
                        layer.closeAll();
                    });
                } else {
                    layer.alert("删除市场活动失败", {
                        closeBtn: false,
                        skin: 'layui-layer-molv'
                    });
                }
            }
        });
    })
}

function queryActivity() {
    $("#myDataTable").dataTable().fnDestroy();
    var activityName = $("#txtActivityName").val();
    // var provinceId = $("select[name='provinceId']").find("option:selected").val();
    // var cityId = $("select[name='cityId']").find("option:selected").val();
    // var areaId = $("select[name='areaId']").find("option:selected").val();
    $("#myDataTable").dataTable({
        ajax: {
            type: "GET",
            url: ctx + '/inner/activity/listAjax',
            // 传入已封装的参数
            data: function (data) {
                // data.provinceId = provinceId;
                // data.cityId = cityId;
                // data.areaId = areaId;
                data.activityName = activityName;
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
            CONSTANT.DATA_TABLES.COLUMN.CHECKBOX,
            {
                data: 'operate',
                bSortable: false,
                visible: true,
                render: function (data, type, activity) {

                    var operResult = "<div class=\"btn-group\">";
                    operResult += "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">";
                    operResult += "<span class=\"caret\"></span>";
                    operResult += "</button>";
                    operResult += " <ul class=\"dropdown-menu\">";

                    operResult += " <li><a href=\"#\" onclick=\"transfer('" + activity.id + "', '" + activity.activityName + "');\">转移</a></li>";
                    operResult += " <li><a href=\"#\" onclick=\"deleteById('" + activity.id + "', '" + activity.activityName + "');\">删除</a></li>";
                    operResult += " <li><a href=\"#\" onclick=\"editActivity('" + activity.id + "');\">编辑</a></li>";

                    operResult += " </ul>";
                    operResult += " </div>";

                    return operResult;
                }
            },
            {
                data: 'activityName',
                render: function (data, type, activity) {
                    return renderActivityName(activity.id, activity.activityName);
                }
            },
            {data: 'userName'},
            {data: 'activityType'},
            {data: 'activityState'},
            {data: 'activityStartTime'},
            {data: 'activityEndTime'},
            {data: 'invitationPopulation'},
            {data: 'realNum'},
            {data: 'createTime'},
            {data: 'businessType'}
        ],
        "bProcessing": true, //DataTables载入数据时，是否显示‘进度’提示
        "bPaginate": true,
        "serverSide": true,
        "searching": true,
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


function initDataTable() {
    $("#myDataTable").dataTable({
        ajax: {
            type: "GET",
            url: ctx + '/inner/activity/listAjax',
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
            CONSTANT.DATA_TABLES.COLUMN.CHECKBOX,
            {
                data: 'operate',
                bSortable: false,
                visible: true,
                render: function (data, type, activity) {

                    var operResult = "<div class=\"btn-group\">";
                    operResult += "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">";
                    operResult += "<span class=\"caret\"></span>";
                    operResult += "</button>";
                    operResult += " <ul class=\"dropdown-menu\">";

                    operResult += " <li><a href=\"#\" onclick=\"transfer('" + activity.id + "', '" + activity.activityName + "');\">转移</a></li>";
                    operResult += " <li><a href=\"#\" onclick=\"deleteById('" + activity.id + "', '" + activity.activityName + "');\">删除</a></li>";
                    operResult += " <li><a href=\"#\" onclick=\"editActivity('" + activity.id + "');\">编辑</a></li>";

                    operResult += " </ul>";
                    operResult += " </div>";

                    return operResult;
                }
            },
            {
                data: 'activityName',
                render: function (data, type, activity) {
                    return renderActivityName(activity.id, activity.activityName);
                }
            },
            {data: 'userName'},
            {data: 'activityType',
                render: function (data, type, activity) {
                    return getActivityType(activity.activityType)
                }
            },
            {data: 'activityState',
                render: function (data, type, activity) {
                    return getActivityState(activity.activityState)
                }
            },
            {data: 'activityStartTime'},
            {data: 'activityEndTime'},
            {data: 'invitationPopulation'},
            {data: 'realNum'},
            {data: 'createTime'},
            {data: 'businessType',
                render: function (data, type, activity) {
                    return getActivityBusinessType(activity.businessType)
                }
            }
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

function getActivityType(activityType){
    var typeStr = "";
    if(activityType == 1){
        typeStr = "广告";
    }else if(activityType == 2){
        typeStr = "研讨会/会议";
    }else if(activityType == 3){
        typeStr = "电子邮件";
    }else if(activityType == 4){
        typeStr = "电话营销";
    }else if(activityType == 5){
        typeStr = "公共关系";
    }else if(activityType == 6){
        typeStr = "合作伙伴";
    }else if(activityType == 7){
        typeStr = "其它";
    }

    return typeStr;
}

function getActivityState(activityState){
    var stateStr = "";
    if(activityState == 1){
        stateStr = "已计划";
    }else if(activityState == 2){
        stateStr = "进行中";
    }else if(activityState == 3){
        stateStr = "已结束";
    }else if(activityState == 4){
        stateStr = "中止";
    }

    return stateStr;
}
function getActivityBusinessType(businessType){
    var businessTypeStr = "";
    if(businessType == 0){
        businessTypeStr = "默认业务类型";
    }

    return businessTypeStr;
}
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function selectActivity(){
    var url = ctx + "/inner/activity/sampleList";
    layer.open({
        type: 2,
        title : false,
        shadeClose: false,
        closeBtn: 0,
        shade: 0.8,
        area: ['830px', '400px'],
        content: url
    });
}