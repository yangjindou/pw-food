import axios from "axios";
export default {
  getDictData(obj, sign){
    axios.get(`/dict/data/sign/${sign}`).then(res => {
      if (res) {
        res.data.forEach(item => obj.push(item));
      }
    });
  },
}