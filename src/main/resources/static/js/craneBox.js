
// 获取模型
var modal = document.getElementById('id01');

// 鼠标点击模型外区域关闭登录框
window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
}
var sqlFlag=1;//1-insert,2-delete,3-update
var craneId;
function addCrane(id,carNumber,carTypeNumber,username,maxLiftWeight,nowWeightCount,maxWeightCount,birthday,useDay,isAdd) {
    sqlFlag=isAdd;
    if(sqlFlag!=1){
        document.getElementById("btnUserBoxDelete").style.display='block';
    }else {
        document.getElementById("btnUserBoxDelete").style.display='none';
    }
    craneId=id;
    document.getElementById("carNumberId").value=carNumber;
    document.getElementById("carTypeNumberId").value=carTypeNumber;
    document.getElementById("usernameId").value=username;
    document.getElementById("maxLiftWeightId").value=maxLiftWeight;
    document.getElementById("nowWeightCountId").value=nowWeightCount;
    document.getElementById("maxWeightCountId").value=maxWeightCount;
    document.getElementById("birthdayId").value=birthday;
    document.getElementById("useDayId").value=useDay;
    document.getElementById('id01').style.display='block';
}
function craneBoxDelete() {
    sqlFlag=2;
    craneBox();
}
function craneBoxUpdate() {
    craneBox();
}
function craneBox() {
    //JSON.stringify()把json字符串转为json对象
    $.ajax({
        type: "POST",
        url: sqlFlag==1?"/crane/insert":sqlFlag==2?"/crane/delete":"/crane/update",
        contentType:"application/json",
        data: JSON.stringify({
            "id":craneId,
            "carNumber":document.getElementById("carNumberId").value,
            "carTypeNumber":document.getElementById("carTypeNumberId").value,
            "username":document.getElementById("usernameId").value,
            "maxLiftWeight": document.getElementById("maxLiftWeightId").value,
            "nowWeightCount":document.getElementById("nowWeightCountId").value,
            "maxWeightCount": document.getElementById("maxWeightCountId").value,
            "birthday": document.getElementById("birthdayId").value,
            "useDay": document.getElementById("useDayId").value
        }),
        dataType: "json",
        success: function (craneResult) {
            console.log(craneResult);
            //var craneBox=JSON.parse(data);//因为@ResponseBody回来的就是JSON对象，JSON.parse是将JSON字符串转为JSON对象
            if(craneResult.ifSuccess==1){
                alert(craneResult.message);
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
