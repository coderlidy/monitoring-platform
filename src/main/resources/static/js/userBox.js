
// 获取模型
var modal = document.getElementById('id01');

// 鼠标点击模型外区域关闭登录框
window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
}

function addUser(username,password,name,age,gradeName) {
    document.getElementById("usernameID").value=username;
    document.getElementById("passwordID").value=password;
    document.getElementById("nameID").value=name;
    document.getElementById("ageID").value=age;
    document.getElementById("gradeID").value=gradeName;
    document.getElementById('id01').style.display='block';
}

function userBoxSubmit() {
    $.ajax({
        type: "POST",
        url: "/user/update",
        contentType:"application/json",
        data: JSON.stringify({
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
                alert('成功 ');
                setTimeout(function () {
                    console.log(location.href);
                    location.replace(location.href);
                },1000);
            }

        },
        error:function () {
            alert('错误 ');
        }
    });
}
