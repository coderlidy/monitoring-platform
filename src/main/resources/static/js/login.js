function login() {
    console.log();
    $.ajax({
        type: "POST",
        url: "/login",
        contentType:"application/json",
        data: JSON.stringify({
            "username":document.getElementById("inputUsername").value,
            "password":document.getElementById("inputPassword").value,
            "remember":$('#checkboxId').prop("checked"),
        }),
        dataType: "json",
        success: function (userBoxResult) {
            console.log(userBoxResult);
            //var userBox=JSON.parse(data);//因为@ResponseBody回来的就是JSON对象，JSON.parse是将JSON字符串转为JSON对象
            if(userBoxResult.ifSuccess==1){
                document.getElementById("resultId").innerText=userBoxResult.message;
                setTimeout(function () {
                    console.log(location.href);
                    location.replace("/first");
                },1000);
            }else{
                document.getElementById("resultId").innerText=userBoxResult.message;
            }
        },
        error:function () {
            alert('网络错误!');
        }
    });
}