export default {
  randomCode(length = 10) {
    const vertify = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    let sd = '';
    for (let i = 0; i < length; i++) {
      //向下取整
      const random = Math.floor(Math.random() * (vertify.length));
      //1：初始化验证码 空字符  res长度为6
      sd += vertify[random];
    }
    return sd;
  }
}
