const path = require("path");
const IS_PROD = ['production', 'prod'].includes(process.env.NODE_ENV);

module.exports = {
  devServer: {
    overlay: {
      warnings: true,
      errors: true
    },
    port: process.env.VUE_APP_PORT,
    proxy: {
      "/api": {
        target: "http://localhost:8088",
        changeOrigin: true,
        pathRewrite: {
          "^/api": "/"
        }
      },
      "/pro": {
        target: "http://1.15.38.182/api",
        changeOrigin: true,
        pathRewrite: {
          "^/pro": "/"
        }
      }
    }
  },
  lintOnSave: false, // 关闭js语法检查
  publicPath: process.env.VUE_APP_PUBLIC_PATH,  // 公共路径
  assetsDir: 'static', // 相对于outputDir的静态资源(js、css、img、fonts)目录
  runtimeCompiler: true, // 是否使用包含运行时编译器的 Vue 构建版本
  productionSourceMap: !IS_PROD, // 生产环境的 source map
  parallel: require("os").cpus().length > 1, // 是否为 Babel 或 TypeScript 使用 thread-loader。该选项在系统的 CPU 有多于一个内核时自动启用，仅作用于生产构建。
  pluginOptions: {
    'style-resources-loader': {
      preProcessor: 'less',
      patterns: [
        path.resolve(__dirname, "./src/core/less/global.less")
      ]
    }
  },
  css: {
    loaderOptions: {
      less: {
        modifyVars: require("./src/core/theme"),
        javascriptEnabled: true,
      },
    },
  },
  configureWebpack: {
    performance: {
      hints: 'warning',
      // 入口起点的最大体积
      maxEntrypointSize: 50000000,
      // 生成文件的最大体积
      maxAssetSize: 30000000,
      // 只给出 js 文件的性能提示
      assetFilter: function(assetFilename) {
        return assetFilename.endsWith('.js') || assetFilename.endsWith('.css');
      }
    },
  },
  chainWebpack: (config) => {
    const imagesRule = config.module.rule("images")
    imagesRule
        .use('url-loader')
        .loader('url-loader')
        .tap(options => Object.assign(options, { limit: 6144 }))
  }
}