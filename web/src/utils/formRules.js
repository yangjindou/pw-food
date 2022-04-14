export const rules = [{
  required: true,
  message: '必填项',
}];

export const numberRules = [{
  required: true,
  pattern: new RegExp(/^(([^0][0-9]+|0)\.([0-9])$)|^(([^0][0-9]+|0)$)|^(([1-9]+)\.([0-9])$)|^(([1-9]+)$)/, "g"),
  message: '请输入正确的数字'
}];

export const integerRules = [{
  required: true,
  pattern: new RegExp(/^[1-9]\d*|0$/, "g"),
  message: '请输入正确的整数'
}];
