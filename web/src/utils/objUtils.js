export default {
  cloneObject (obj){
    return JSON.parse(JSON.stringify(obj))
  },
  getObjectByKey(obj, ...keys) {
    const res = {};
    keys.forEach(key => {
      if (obj[key]) {
        res[key] = obj[key];
      }
    });
    return res;
  }
}