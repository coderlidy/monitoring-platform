
// 获取模型
var modal = document.getElementById('id01');

// 鼠标点击模型外区域关闭登录框
window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
}
var sqlFlag=1;//1-insert,2-delete,3-update
var uesrId;
function addUser(id,username,password,name,age,gradeName,isAdd) {
    sqlFlag=isAdd;
    if(sqlFlag!=1){
        document.getElementById("btnUserBoxDelete").style.display='block';
    }else {
        document.getElementById("btnUserBoxDelete").style.display='none';
    }

    uesrId=id;
    document.getElementById("usernameID").value=username;
    document.getElementById("passwordID").value=password;
    document.getElementById("nameID").value=name;
    document.getElementById("ageID").value=age;
    document.getElementById("gradeID").value=gradeName;
    document.getElementById('id01').style.display='block';
}
function userBoxDelete() {
    sqlFlag=2;
    userBox();
}
function userBoxUpdate() {
    userBox();
}
function userBox() {
    //JSON.stringify()把json字符串转为json对象
    $.ajax({
        type: "POST",
        url: sqlFlag==1?"/user/insert":sqlFlag==2?"/user/delete":"/user/update",
        contentType:"application/json",
        data: JSON.stringify({
            "id":uesrId,
            "username":document.getElementById("usernameID").value,
            "password":document.getElementById("passwordID").value,
            "name":document.getElementById("nameID").value,
            "gradeName": document.getElementById("gradeID").value,
            "age":document.getElementById("ageID").value
        }),
        dataType: "json",
        success: function (userBoxResult) {
            console.log(userBoxResult);
            //var userBox=JSON.parse(data);//因为@ResponseBody回来的就是JSON对象，JSON.parse是将JSON字符串转为JSON对象
            if(userBoxResult.ifSuccess==1){
                alert(userBoxResult.message);
                setTimeout(function () {
                    console.log(location.href);
                    location.replace(location.href);
                },1000);
            }
        },
        error:function () {
            alert('网络错误!');
        }
    });
}
