Page({
  /**
   * 页面的初始数据
   */
  data: {
    flag:""
  },
  formSubmit:function(e){
    var that = this;
    wx.request({
      url: getApp().globalData.myUrl+'/login',
      header: { 'content-type': 'application/json'},
      method: "POST",
      data: {
        username: e.detail.value.username,
        password: e.detail.value.password,
        remember: true,
      },
      dataType:"json",
      success: function(result){
        console.log(result);
        if (result.statusCode==200){
          if (result.data.ifSuccess == 1) {
            wx.showToast({
              title: result.data.message,
              icon: 'success',
              duration: 1000
            });
            //获取cookie
            if (result.header['Set-Cookie'] != '') {
              //处理cookie并缓存
              try{
                wx.setStorageSync('myCookie', 
                  'loginUsername=' + that.dealCookie('loginUsername', result.header['Set-Cookie'])+
                  ";loginPassword=" + that.dealCookie('loginPassword', result.header['Set-Cookie']));
              } catch (e) { 
                console.log(e);
              }
              console.log('loginUsername = ' + that.dealCookie('loginUsername', result.header['Set - Cookie'])+"loginPassword=" + that.dealCookie('loginPassword', result.header['Set-Cookie']));
              //console.log(wx.getStorageSync('myCookie'));
            }
            getApp().globalData.username = e.detail.value.username;
            console.log(getApp().globalData.username);
            wx.switchTab({ url: '../cranenow/cranenow' });
          } else if (result.data.ifSuccess == 0) {
            wx.showToast({
              title: result.data.message,
              icon: 'none',
              duration: 3000
            });
          }
        }else{
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
   * 获取cookie对应key的value
   */
  dealCookie: function (s1,str1) {
    //console.log("-----------");
    var result = "";
    var str=str1+"";
    //console.log(s1 + "      " + str1);
    if (str.indexOf(s1) != -1) {
      for (var i = str.indexOf(s1) + s1.length + 1; i < str.length && str[i] != ';' && str[i] != ','; i++) {
        result += str[i];
      }
    }
    //console.log(result);
    return result;
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    var myCookie = wx.getStorageSync('myCookie');
    console.log(myCookie);
    if (myCookie!=''){
      wx.request({
        url: getApp().globalData.myUrl + '/wx/loginStatus',
        header: { 'content-type': 'application/json' },
        method: "POST",
        data: {
          username: that.dealCookie('loginUsername', myCookie),
          password: that.dealCookie('loginPassword', myCookie),
        },
        dataType: "json",
        success: function (result) {
          console.log(result);
          if (result.statusCode == 200) {
            if (result.data.ifSuccess == 1) {
              wx.switchTab({ url: '../cranenow/cranenow' });
            } 
          } 
        },
      })
    }
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