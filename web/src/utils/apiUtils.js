import axios from "axios";
export default {
  getDictData(obj, sign){
    axios.get(`/dict/data/sign/${sign}`).then(res => {
      if (res) {
        res.data.forEach(item => obj.push(item));
      }
    });
  },
  createGetUrl(uri, data) {
    let params = [];
    for (let key in data) {
      if (data[key]) {
        params.push(`${key}=${data[key]}`);
      }
    }
    if (params.length) {
      uri += "?";
    }
    return uri + params.join('&');
  }
}