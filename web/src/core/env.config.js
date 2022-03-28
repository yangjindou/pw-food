const configs = {
  development: {
    baseUrl:'http://localhost:8088'
  },
  production: {
    baseUrl:'http://localhost:8082'
  },
}
//根据process.env.NODE_ENV选择相应的配置
const config = configs[process.env.NODE_ENV]
export default config;