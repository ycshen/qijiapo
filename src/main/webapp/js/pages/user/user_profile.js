/**
 * Created by shenyuchuan on 2017/3/29.
 */
function isBlank(args){
    var result = false;
    if(args == "" || args == null || args == undefined){
        result = true;
    }

    return result;
}
function isNotBlank(args){
    var result = false;
    if(args != "" && args != null && args != undefined){
        result = true;
    }

    return result;
}

function modifyInfo(){
    var email = $("#txtEmail").val();
    if(isBlank(email)){
        layer.alert("邮箱地址不能为空",{closeBtn: false,
            skin: 'layui-layer-molv'
        });
        return;
    }

    var signature = $("#txtSignature").val();
    var school = $("#txtSchool").val();
    var education = $("#selectEducation").find("option:selected").val();
    var obj = new Object();
    obj.signature = signature;
    obj.school = school;
    obj.education = education;
    obj.email = email;
    var data = JSON.stringify(obj);
    layer.confirm("是否确定要修改个人信息？",{closeBtn: false,
        skin: 'layui-layer-molv'
    }, function(){
        var url = ctx + "/inner/user/modifyInfo";
        $.ajax({
            type: "post",
            url: url,
            contentType: "application/json",
            data: data,
            success: function(result){
                if(result == 2){
                    layer.alert("修改个人信息成功",{closeBtn: false,
                        skin: 'layui-layer-molv'
                    }, function(){
                        layer.closeAll();
                        window.location.href = ctx + "/inner/user/profile";
                    });
                }else{
                    layer.alert("修改个人信息失败",{closeBtn: false,
                        skin: 'layui-layer-molv'
                    });
                }
            }
        });
    })
}


function resetPass(){
    var oldPass = $("#txtOldPassword").val();
    var newPass = $("#txtNewPass").val();
    var confirmNewPass = $("#txtConfirmNewPass").val();

    if(isBlank(oldPass)){
        layer.alert("原始密码不能为空",{closeBtn: false,
            skin: 'layui-layer-molv'
        });
        return;
    }

    if(isBlank(newPass)){
        layer.alert("新密码不能为空",{closeBtn: false,
            skin: 'layui-layer-molv'
        });
        return;
    }

    if(isBlank(confirmNewPass)){
        layer.alert("确认新密码不能为空",{closeBtn: false,
            skin: 'layui-layer-molv'
        });
        return;
    }

    if(newPass != confirmNewPass ){
        layer.alert("两次输入的密码不一致！",{closeBtn: false,
            skin: 'layui-layer-molv'
        });
        return;
    }

    layer.confirm("是否确定要重置密码？",{closeBtn: false,
        skin: 'layui-layer-molv'
    }, function(){
        var url = ctx + "/inner/user/resetPwd?oldPass=" + oldPass +"&newPass=" + newPass +"&confirmNewPass=" + confirmNewPass;
        $.ajax({
            type: "get",
            url: url,
            success: function(result){
                layer.alert(result,{closeBtn: false,
                    skin: 'layui-layer-molv'
                }, function(){
                    layer.closeAll();
                    window.location.href = ctx + "/inner/user/profile";
                });
            }
        });
    })
}