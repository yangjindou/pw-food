import {
	http
} from "./http.js";

export default {
  getDictData(sign){
    return http.get(`/dict/data/sign/${sign}`);
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