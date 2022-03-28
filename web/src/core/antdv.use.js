import Vue from "vue";
import 'ant-design-vue/dist/antd.less';
import {
  Button, message, Avatar, Dropdown, Icon, ConfigProvider, Form, Row, Col, Select, Input,
  Upload, Menu, Checkbox, Breadcrumb, Table, DatePicker, Empty, Modal, Switch
} from 'ant-design-vue';
Vue.use(Button);
Vue.use(Avatar);
Vue.use(Dropdown);
Vue.use(Icon);
Vue.use(ConfigProvider);
Vue.use(Form);
Vue.use(Row);
Vue.use(Col);
Vue.use(Select);
Vue.use(Input);
Vue.use(Upload);
Vue.use(Menu);
Vue.use(Checkbox);
Vue.use(Breadcrumb);
Vue.use(Table);
Vue.use(DatePicker);
Vue.use(Empty);
Vue.use(Modal);
Vue.use(Switch);
Vue.prototype.$message = message;