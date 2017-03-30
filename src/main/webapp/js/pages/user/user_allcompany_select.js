function cancelEdit() {
    window.parent.layer.closeAll();
}

function isBlank(args) {
    var result = false;
    if (args == "" || args == null || args == undefined) {
        result = true;
    }

    return result;
}

$(function () {
    $(".select2").select2();
})

function transferCompetitor(competitorId) {
    layer.confirm("确定要转移竞争对手相关信息吗？", {
        closeBtn: false,
        skin: 'layui-layer-molv'
    }, function () {
        var userId = $("select[name='userId']").val();
        if (isBlank(userId)) {
            layer.alert("请选择新负责人", {closeBtn: false, skin: 'layui-layer-molv'});
            return;
        }

        var transferType = $("#hidTransferType").val();
        var url = ctx + "/inner/competitor/transferCompetitor?transferType=" + transferType + "&userId=" + userId + "&competitorId=" + competitorId;
        $.ajax({
            cache: true,
            type: "get",
            url: url,
            async: false,
            success: function (data) {
                if (data == 2) {
                    var position = $("#btnPositionName").html();
                    layer.alert('转移竞争对手成功', {closeBtn: false, skin: 'layui-layer-molv'}, function (index) {
                        window.parent.refreshTable();
                        window.parent.layer.closeAll();

                    });
                } else {
                    layer.alert("转移竞争对手失败！", {closeBtn: false, skin: 'layui-layer-molv'});
                }
            }
        });
    })


}
function transferSalesLeads(salesLeadsId) {
    layer.confirm("确定要转移销售线索相关信息吗？", {
        closeBtn: false,
        skin: 'layui-layer-molv'
    }, function () {
        var userId = $("select[name='userId']").val();
        if (isBlank(userId)) {
            layer.alert("请选择新负责人", {closeBtn: false, skin: 'layui-layer-molv'});
            return;
        }

        var transferType = $("#hidTransferType").val();
        var url = ctx + "/inner/salesLeads/transferSalesLeads?transferType=" + transferType + "&userId=" + userId + "&salesLeadsId=" + salesLeadsId;
        $.ajax({
            cache: true,
            type: "get",
            url: url,
            async: false,
            success: function (data) {
                if (data == 2) {
                    var position = $("#btnPositionName").html();
                    layer.alert('转移销售线索成功', {closeBtn: false, skin: 'layui-layer-molv'}, function (index) {
                        window.parent.refreshTable();
                        window.parent.layer.closeAll();

                    });
                } else {
                    layer.alert("转移销售线索失败！", {closeBtn: false, skin: 'layui-layer-molv'});
                }
            }
        });
    })


}

function transferContract(contractId) {
    layer.confirm("确定要转移合同相关信息吗？", {
        closeBtn: false,
        skin: 'layui-layer-molv'
    }, function () {
        var userId = $("select[name='userId']").val();
        if (isBlank(userId)) {
            layer.alert("请选择新负责人", {closeBtn: false, skin: 'layui-layer-molv'});
            return;
        }

        var transferType = $("#hidTransferType").val();
        var url = ctx + "/inner/contract/transferContract?transferType=" + transferType + "&userId=" + userId + "&contractId=" + contractId;
        $.ajax({
            cache: true,
            type: "get",
            url: url,
            async: false,
            success: function (data) {
                if (data == 2) {
                    var position = $("#btnPositionName").html();
                    layer.alert('转移合同成功', {closeBtn: false, skin: 'layui-layer-molv'}, function (index) {
                        window.parent.refreshTable();
                        window.parent.layer.closeAll();

                    });
                } else {
                    layer.alert("转移合同失败！", {closeBtn: false, skin: 'layui-layer-molv'});
                }
            }
        });
    })


}
function transferActivity(activityId) {
    layer.confirm("确定要转移市场活动相关信息吗？", {
        closeBtn: false,
        skin: 'layui-layer-molv'
    }, function () {
        var userId = $("select[name='userId']").val();
        if (isBlank(userId)) {
            layer.alert("请选择新负责人", {closeBtn: false, skin: 'layui-layer-molv'});
            return;
        }

        var transferType = $("#hidTransferType").val();
        var url = ctx + "/inner/activity/transferActivity?transferType=" + transferType + "&userId=" + userId + "&activityId=" + activityId;
        $.ajax({
            cache: true,
            type: "get",
            url: url,
            async: false,
            success: function (data) {
                if (data == 2) {
                    var position = $("#btnPositionName").html();
                    layer.alert('转移市场活动成功', {closeBtn: false, skin: 'layui-layer-molv'}, function (index) {
                        window.parent.refreshTable();
                        window.parent.layer.closeAll();

                    });
                } else {
                    layer.alert("转移市场活动失败！", {closeBtn: false, skin: 'layui-layer-molv'});
                }
            }
        });
    })
}
