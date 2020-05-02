// pages/myCraneInfo/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user:{
      name:"",
      username:"",
      age: "",
      gradeName: "",
    },
    crane: {
      carTypeNumber: "",
      maxLiftWeight: "",
      nowWeightCount: "",
      maxWeightCount: "",
      birthday: "",
      useDay: "",
      isRepair:""
    },
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
    //发送异步请求
    wx.request({
      header: {
        'cookie': wx.getStorageSync('myCookie'),
      },
      url: getApp().globalData.myUrl + '/wx/myInfo/' + getApp().globalData.username,
      success: (result) => {
        console.log(result.data);
        if (result.statusCode == 200 && result.data != null && result.data.userDTO != null && result.data.craneDTO != null) {
          console.log(result);
          this.setData({
            user: {
              name: result.data.userDTO.name,
              username: result.data.userDTO.username,
              age: result.data.userDTO.age,
              gradeName: result.data.userDTO.gradeName,
            },
            crane: {
              carTypeNumber: result.data.craneDTO.carTypeNumber,
              maxLiftWeight: result.data.craneDTO.maxLiftWeight,
              nowWeightCount: result.data.craneDTO.nowWeightCount,
              maxWeightCount: result.data.craneDTO.maxWeightCount,
              birthday: result.data.craneDTO.birthday,
              useDay: result.data.craneDTO.useDay,
              isRepair: result.data.craneDTO.nowWeightCount < result.data.craneDTO.maxWeightCount?'否':'是',
            },
          });
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
  btnClick:function(){
    wx.removeStorageSync("myCookie");
    wx.redirectTo({
      url: '../index/index', 
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