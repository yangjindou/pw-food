module.exports = {
  presets: [
    '@vue/cli-plugin-babel/preset'
  ],
  //配置babel-plugin-import
  plugins: [
    [
      "import",
      {libraryName: "ant-design-vue", libraryDirectory: "es", style: true}
    ],
  ]
};
