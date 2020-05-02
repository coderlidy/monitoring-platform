// pages/cranenow/cranenow.js
// //获取服务器上的运动轨迹点 const只是令数组的指针不变，里面的内容还是会变
// const positions=[
//   { longitude: 116.557147,latitude: 39.954112 },
//   { longitude: 116.537147, latitude: 39.954112 },
//   { longitude: 116.547147, latitude: 39.964112 }
// ];
//map的配置
const settings={
  scale: 16,
  enableSatellite: false
};
Page({
  /**
   * 页面的初始数据
   */
  data: {
    setting: settings,
    button:{
      text:"切换为卫星图",
    },
    crane:{
      nowWeight:"",
      maxLiftWeight:"",
      isSafe:""
    },
    longitude: '',
    latitude: '',
    markers: [],
    polyline: [],
  },
  btnClick:function(){
    console.log(settings.enableSatellite);
    if (settings.enableSatellite){
      settings.enableSatellite=false;
      this.setData({
        setting: settings,//使设置生效
        button: {
          text: "切换为卫星地图",
        },
      });
    }else{
      settings.enableSatellite = true;
      this.setData({
        setting: settings,//使设置生效
        button: {
          text: "切换为基础地图",
        }
      });
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    console.log("cranenow");
    console.log(wx.getStorageSync('myCookie'));
    //发送异步请求
    wx.request({
      header: {
        'cookie': wx.getStorageSync('myCookie'),
      },
      url: getApp().globalData.myUrl + '/wx/cranenow/' + getApp().globalData.username,
      success: (result) => {
        console.log(result);
        if (result.statusCode == 200 && result.data != null && result.data.positions!=null){
          console.log(settings);
          this.setData({
            setting: settings,//使设置生效
            longitude:  result.data.positions[0].longitude,
            latitude: result.data.positions[0].latitude,
            markers: [{
              longitude: result.data.positions[0].longitude,
              latitude: result.data.positions[0].latitude,
              iconPath: "./imgs/Marker3_Activated@3x.png",
              width: '34px',
              height: '34px',
            },],
            polyline: [{
              points: result.data.positions,
              color: '#3875FF',
              width: 6,
              dottedLine: false,
              borderWidth: 2
            }],
            crane: {
              nowWeight: result.data.nowWeight,
              maxLiftWeight: result.data.maxLiftWeight,
              isSafe: result.data.nowWeight > result.data.maxLiftWeight?"危险":"安全",
            },
          });
          this.setData({setting: settings});
          console.log(settings);
        } else {
          wx.showToast({
            title: "网络错误！",
            icon: 'none',
            duration: 2000
          });
        }
      },
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})